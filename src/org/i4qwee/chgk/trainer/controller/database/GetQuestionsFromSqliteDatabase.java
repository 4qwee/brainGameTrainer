package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/27/12
 * Time: 8:58 PM
 */
public class GetQuestionsFromSqliteDatabase implements GetQuestionsFromDatabaseStrategy
{
    private static final Logger LOGGER = Logger.getLogger(GetQuestionsFromSqliteDatabase.class);

    public GetQuestionsFromSqliteDatabase()
    {

    }

    @Override
    public PreparedStatement getStatement() throws SQLException
    {
        Connection connection = getConnection();

        if (connection != null)
            return connection.prepareStatement("select * from questions where type=? order by random() limit ?");
        else
            return null;
    }

    private Connection getConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/main.db");
        }
        catch (ClassNotFoundException e)
        {
            handleError("Cannot find SQLite driver!", e);
        }
        catch (SQLException e)
        {
            handleError("Cannot connect to database!", e);
        }

        return null;
    }

    private void handleError(String message, Exception e)
    {
        MessageDialog.getInstance().showError(message);
        LOGGER.error(message, e);
    }

}
