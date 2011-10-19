package org.i4qwee.chgk.trainer;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.database.DatabaseConnector;
import org.i4qwee.chgk.trainer.controller.database.DatabaseCreator;
import org.i4qwee.chgk.trainer.controller.database.DatabaseRemover;
import org.i4qwee.chgk.trainer.controller.parse.ParserAllTask;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 16:56
 */
public class FullDownloadDatabase
{
    private static Logger logger = Logger.getLogger(FullDownloadDatabase.class);

    public static void main(String[] args)
    {
        try
        {
            Statement removeStatement = DatabaseConnector.getConnection().createStatement();
            new DatabaseRemover(removeStatement).run();
            removeStatement.close();

            Statement createStatement = DatabaseConnector.getConnection().createStatement();
            new DatabaseCreator(createStatement).run();
            createStatement.close();
        }
        catch (SQLException e)
        {
            logger.error(null, e);
        }

        new ParserAllTask().run();
    }
}
