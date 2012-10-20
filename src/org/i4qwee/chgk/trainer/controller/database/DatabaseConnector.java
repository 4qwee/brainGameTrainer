package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 23:50
 */
class DatabaseConnector
{
    @SuppressWarnings("CanBeFinal")
    private static Connection connection;

    static
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/main.db");
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
