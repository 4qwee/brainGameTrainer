package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;

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

    private String hostname;
    private String databaseName;
    private String username;
    private String password;

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
            LOGGER.error("Cannot find MySQL driver!", e);
        }
        catch (SQLException e)
        {
            LOGGER.error("Cannot connect to database!", e);
        }

        return null;
    }
}
