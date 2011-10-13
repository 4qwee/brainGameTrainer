package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.database.DatabaseConnector;
import org.i4qwee.chgk.trainer.controller.database.DatabaseCreator;

import java.sql.SQLException;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 12:56
 */
public class DatabaseCreateTest
{
    public static void main(String[] args)
    {
        try
        {
            new DatabaseCreator(DatabaseConnector.getConnection().createStatement()).run();
//            new DatabaseRemover(DatabaseConnector.getConnection().createStatement()).run();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
