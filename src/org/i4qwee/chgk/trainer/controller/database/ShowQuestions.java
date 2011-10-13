package org.i4qwee.chgk.trainer.controller.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 18:38
 */
public class ShowQuestions implements Runnable
{
    private Statement statement;

    public ShowQuestions(Statement statement)
    {
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from " + DatabaseConstants.QUESTIONS_TABLE);

            while (resultSet.next())
                System.out.println(resultSet.getInt(DatabaseConstants.QUESTION_ID_COLUMN));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
