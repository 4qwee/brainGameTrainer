package org.i4qwee.chgk.trainer.controller.database;

import org.i4qwee.chgk.trainer.model.ApplicationConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 23:50
 */
public class DatabaseConnector
{
    private static Connection connection;

    static
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + ApplicationConstants.IDEA_FILEPATH_HACK + "\\main.db");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }
}
