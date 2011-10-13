package org.i4qwee.chgk.trainer;

import org.i4qwee.chgk.trainer.controller.database.*;
import org.i4qwee.chgk.trainer.controller.parse.ParserAllTask;
import org.i4qwee.chgk.trainer.controller.web.Downloader;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 16:56
 */
public class FullDownloadDatabase
{
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
            e.printStackTrace();
        }

        new ParserAllTask().run();
    }
}
