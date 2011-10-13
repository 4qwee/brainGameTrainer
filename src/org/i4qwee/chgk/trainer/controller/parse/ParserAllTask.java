package org.i4qwee.chgk.trainer.controller.parse;

import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.controller.web.Downloader;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.i4qwee.chgk.trainer.controller.parse.ParseConstants.*;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 13:30
 */
public class ParserAllTask implements Runnable
{
    private Logger logger = Logger.getLogger(ParserAllTask.class.toString());
    private List<String> rootUrls = new ArrayList<String>();

    public void run()
    {
        try
        {
            FileHandler fileHandler = new FileHandler("fulldownload.log");
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

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
            logger.fine("Processing " + url);
            processTournament(url, 0);
        }
    }

    private void processTournament(String url, int parentId)
    {
        DOMParser parser = new DOMParser(TOURNAMENT_XML_PATH + url);

        int tournamentId =  DatabaseManager.insertTournament(parser.getMainTournament(), parentId);
        DatabaseManager.insertQuestions(parser.getQuestions(), tournamentId);

        for (Tournament tournament : parser.getToursForLoad())
        {
            logger.fine("Processing tour " + tournament.getFilename() + " of tournament " + url);

            if (isInBanList(tournament.getFilename()))
            {
                logger.log(Level.WARNING, "duplicate " + tournament.getFilename() + " in " + parser.getMainTournament().getFilename());
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
