package org.i4qwee.chgk.trainer.controller.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 18:46
 */
public class ShowTournaments implements Runnable
{
    private Statement statement;

    public ShowTournaments(Statement statement)
    {
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            ResultSet resultSet = statement.executeQuery("select * from " + DatabaseConstants.TOURNAMENTS_TABLE);

            while (resultSet.next())
                System.out.println(resultSet.getString(DatabaseConstants.TOURNAMENT_TITLE_COLUMN));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
