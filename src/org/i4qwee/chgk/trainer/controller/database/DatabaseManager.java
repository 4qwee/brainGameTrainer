package org.i4qwee.chgk.trainer.controller.database;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.i4qwee.chgk.trainer.controller.parse.CheckQuestionsForImagesTask;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Tournament;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 15:20
 */
public class DatabaseManager
{
    private static PreparedStatement insertQuestionStatement;
    private static PreparedStatement insertTournamentStatement;

    static
    {
        try
        {
            insertQuestionStatement = DatabaseConnector.getConnection().prepareStatement(DatabaseScripts.INSERT_QUESTION);
            insertTournamentStatement = DatabaseConnector.getConnection().prepareStatement(DatabaseScripts.INSERT_TOURNAMENT);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertQuestion(Question question, int parentId)
    {
        try
        {
            insertQuestionStatement.setInt(1, IdGenerator.getNextQuestionId());
            insertQuestionStatement.setInt(2, parentId);
            insertQuestionStatement.setShort(3, question.getNumber());
            insertQuestionStatement.setShort(4, question.getType().getShortType());
            insertQuestionStatement.setString(5, question.getQuestion());
            insertQuestionStatement.setString(6, question.getAnswer());
            insertQuestionStatement.setString(7, question.getPassCriteria());
            insertQuestionStatement.setString(8, question.getAuthors());
            insertQuestionStatement.setString(9, question.getSources());
            insertQuestionStatement.setString(10, question.getComments());

            insertQuestionStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static int insertTournament(Tournament tournament, int parentId)
    {
        try
        {
            int tournamentId = IdGenerator.getNextTournamentId();

            insertTournamentStatement.setInt(1, tournamentId);
            insertTournamentStatement.setInt(2, parentId);
            insertTournamentStatement.setString(3, tournament.getTitle());
            insertTournamentStatement.setShort(4, tournament.getQuestionsNumber());
            insertTournamentStatement.setString(5, tournament.getCopyright());
            insertTournamentStatement.setString(6, tournament.getInfo());
            insertTournamentStatement.setString(7, tournament.getUrl());
            insertTournamentStatement.setString(8, tournament.getEditors());
            insertTournamentStatement.setDate(9, tournament.getPlayedAt());

            insertTournamentStatement.executeUpdate();

            return tournamentId;
        }
        catch (SQLException e)
        {
            e.printStackTrace();

            return 0;
        }
    }

    public static void insertQuestions(List<Question> questions, int parentId)
    {
        new CheckQuestionsForImagesTask(questions).run();

        for (Question question : questions)
            insertQuestion(question, parentId);
    }
}
