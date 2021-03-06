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
 * Time: 9:03 PM
 */
public class GetQuestionsFromMySqlDatabase implements GetQuestionsFromDatabaseStrategy
{
    private static final Logger LOGGER = Logger.getLogger(GetQuestionsFromMySqlDatabase.class);

    private final String hostname;
    private final String databaseName;
    private final String username;
    private final String password;

    public GetQuestionsFromMySqlDatabase(String hostname, String databaseName, String username, String password)
    {
        this.hostname = hostname;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    @Override
    public PreparedStatement getStatement() throws SQLException
    {
        Connection connection = getConnection();

        if (connection != null)
            return connection.prepareStatement("select QuestionId, ParentId, Number, TypeNum, Question, Answer, " +
                    "PassCriteria, Authors, Sources, Comments from Questions where TypeNum=? order by rand() limit ?");
        else
            return null;
    }

    private Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + hostname + "/" + databaseName + "?user=" + username + "&password=" + password);
        }
        catch (ClassNotFoundException e)
        {
            handleError("Cannot find MySQL driver!", e);
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
