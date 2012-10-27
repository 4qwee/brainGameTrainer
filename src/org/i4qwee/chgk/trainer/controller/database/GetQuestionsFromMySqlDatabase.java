package org.i4qwee.chgk.trainer.controller.database;

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
    public GetQuestionsFromMySqlDatabase()
    {

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
            return DriverManager.getConnection("jdbc:mysql://localhost/questions?user=chgk_user&password=password");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
