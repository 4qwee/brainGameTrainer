package org.i4qwee.chgk.trainer.controller.parse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.ApplicationConstants;
import org.i4qwee.chgk.trainer.model.Tournament;
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
    private List<String> rootUrls = new ArrayList<String>();

    public void run()
    {
        PropertyConfigurator.configure(System.getProperty("user.dir") + ApplicationConstants.IDEA_FILEPATH_HACK + "log4j.prop");

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

        for (String url : rootUrls)
        {
            logger.info("Processing " + url);
            processTournament(url, 0);
        }
    }

    private void processTournament(String url, int parentId)
    {
        DOMParser parser = null;

        try
        {
            parser = new DOMParser(TOURNAMENT_XML_PATH + url);
        }
        catch (SAXParseException e)
        {
            logger.warn("Invalid XML in " + url);
            return;
        }

        int tournamentId =  DatabaseManager.insertTournament(parser.getMainTournament(), parentId);
        DatabaseManager.insertQuestions(parser.getQuestions(), tournamentId);

        for (Tournament tournament : parser.getToursForLoad())
        {
            logger.info("Processing tour " + tournament.getFilename() + " of tournament " + url);

            if (isInBanList(tournament.getFilename()))
            {
                logger.warn("duplicate " + tournament.getFilename() + " in " + parser.getMainTournament().getFilename());
                continue;
            }

            processTournament(tournament.getFilename(), tournamentId);
        }
    }

    private boolean isInBanList(String url)
    {
        for (String rootUrl : rootUrls)
        {
            if (url != null && url.equals(rootUrl))
                return true;
        }

        return false;
    }
}
