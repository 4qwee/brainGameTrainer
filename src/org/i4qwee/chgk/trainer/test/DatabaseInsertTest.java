package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.database.DatabaseConnector;
import org.i4qwee.chgk.trainer.controller.database.ShowTournaments;

import java.sql.SQLException;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 18:10
 */
public class DatabaseInsertTest
{
    public static void main(String[] args)
    {
//        Tournament tournament = new Tournament("title", (short) 1, Type.CHGK, "copyright", "info", "url", "editors", new Date(2011, 9, 5));
//        DatabaseManager.insertTournament(tournament);
//
//        Question question = new Question(1, 1, (short) 1, Type.CHGK, "question", "answer", "pass criteria", "authors", "sources", "comments");
//        DatabaseManager.insertQuestion(question);

        try
        {
//            new ShowQuestions(DatabaseConnector.getConnection().createStatement()).run();
            new ShowTournaments(DatabaseConnector.getConnection().createStatement()).run();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
