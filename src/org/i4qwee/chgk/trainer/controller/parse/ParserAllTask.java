package org.i4qwee.chgk.trainer.controller.parse;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.ApplicationConstants;
import org.i4qwee.chgk.trainer.model.Tournament;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

import static org.i4qwee.chgk.trainer.controller.parse.ParseConstants.TOURNAMENT_XML_PATH;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 13:30
 */
public class ParserAllTask implements Runnable
{
    private static Logger logger = Logger.getLogger(ParserAllTask.class);
    private ArrayList<String> rootUrls = new ArrayList<String>();
    private ArrayList<String> bannedUrls = new ArrayList<String>();

    public void run()
    {
        PropertyConfigurator.configure(System.getProperty("user.dir") + ApplicationConstants.IDEA_FILEPATH_HACK + "log4j.prop");

        fillRootUrls();

        bannedUrls = (ArrayList<String>) rootUrls.clone();
        bannedUrls.add("SPB-DT");

//        for (String url : rootUrls)
//        {
//            logger.info("Processing " + url);
//            processTournament(url, 0);
//        }

        for (int i = 4; i < rootUrls.size(); i++)
        {
            logger.info("Processing " + rootUrls.get(i));
            processTournament(rootUrls.get(i), 0);
        }
    }

    private void fillRootUrls()
    {
        rootUrls.add("AUTHORS");
        rootUrls.add("INTER");
        rootUrls.add("SINHR");
        rootUrls.add("NEPOLN");
        rootUrls.add("REGION");
        rootUrls.add("INET");
        rootUrls.add("R100");
        rootUrls.add("TELE");
        rootUrls.add("TREN");
        rootUrls.add("TEMA");
        rootUrls.add("ERUDITK");
        rootUrls.add("EF");
        rootUrls.add("BESKR");
        rootUrls.add("SVOYAK");
        rootUrls.add("fariz96");
    }

    private void processTournament(String url, int parentId)
    {
        DOMParser parser = null;

        try
        {
            parser = new DOMParser(TOURNAMENT_XML_PATH + url);
        }
        catch (SAXException e)
        {
            logger.warn("Invalid XML in " + url);
            return;
        }

        int tournamentId =  DatabaseManager.insertTournament(parser.getMainTournament(), parentId);
        DatabaseManager.insertQuestions(parser.getQuestions(), tournamentId);

        List<Tournament> tours = null;

        try
        {
            tours = parser.getToursForLoad();
        }
        catch (AssertionError e)
        {
            logger.warn(e.getMessage());
            return;
        }

        if(tours.size() == 0 && parser.getQuestions().size() == 0)
        {
            logger.warn("no data in " + url);
            return;
        }

        for (Tournament tournament : tours)
        {
            if (isInBanList(tournament.getFilename()))
            {
                logger.warn("duplicate " + tournament.getFilename() + " in " + parser.getMainTournament().getFilename());
                continue;
            }
            
            logger.info("Processing tour " + tournament.getFilename() + " of tournament " + url);

            processTournament(tournament.getFilename(), tournamentId);
        }
    }

    private boolean isInBanList(String url)
    {
        for (String rootUrl : bannedUrls)
        {
            if (url != null && url.equals(rootUrl))
                return true;
        }

        return false;
    }

    private void checkHardcodedErrors(Tournament tournament)
    {

    }
}
