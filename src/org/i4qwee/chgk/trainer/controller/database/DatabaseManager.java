package org.i4qwee.chgk.trainer.controller.database;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.parse.CheckQuestionsForImagesTask;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Tournament;
import org.i4qwee.chgk.trainer.model.Type;
import org.i4qwee.chgk.trainer.model.UnknownTypeError;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 15:20
 */
public class DatabaseManager
{
    private static PreparedStatement insertQuestionStatement;
    private static PreparedStatement insertTournamentStatement;
    private static PreparedStatement getRandomQuestionsStatement;
    private static Logger logger = Logger.getLogger(DatabaseManager.class);

    static
    {
        try
        {
            insertQuestionStatement = DatabaseConnector.getConnection().prepareStatement(DatabaseScripts.INSERT_QUESTION);
            insertTournamentStatement = DatabaseConnector.getConnection().prepareStatement(DatabaseScripts.INSERT_TOURNAMENT);
            getRandomQuestionsStatement = DatabaseConnector.getConnection().prepareStatement(DatabaseScripts.GET_RANDOM_QUESTIONS);
        }
        catch (SQLException e)
        {
            logger.error("Cannot create statements!", e);
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
            logger.error("Cannot insert question!");
            logger.error("Question: " + question.getQuestion());
            logger.error(null, e);
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
            logger.error("Cannot insert tournament!");
            logger.error("Tournament: " + tournament.getFilename());
            logger.error(null, e);

            return 0;
        }
    }

    public static void insertQuestions(List<Question> questions, int parentId)
    {
        new CheckQuestionsForImagesTask(questions).run();

        for (Question question : questions)
            insertQuestion(question, parentId);
    }

    public static List<Question> getRandomQuestions(int count, Type type)
    {
        List<Question> questionList = new ArrayList<Question>(count);

        try
        {
            getRandomQuestionsStatement.setShort(1, type.getShortType());
            getRandomQuestionsStatement.setInt(2, count);

            ResultSet questionsResultSet = getRandomQuestionsStatement.executeQuery();

            while (questionsResultSet.next())
            {
                Question newQuestion = new Question();

                newQuestion.setId(questionsResultSet.getInt(1));
                newQuestion.setParentId(questionsResultSet.getInt(2));
                newQuestion.setNumber(questionsResultSet.getShort(3));
                newQuestion.setType(Type.parseType(questionsResultSet.getShort(4)));
                newQuestion.setQuestion(questionsResultSet.getString(5));
                newQuestion.setAnswer(questionsResultSet.getString(6));
                newQuestion.setPassCriteria(questionsResultSet.getString(7));
                newQuestion.setAuthors(questionsResultSet.getString(8));
                newQuestion.setSources(questionsResultSet.getString(9));
                newQuestion.setComments(questionsResultSet.getString(10));

                questionList.add(newQuestion);
            }
        }
        catch (SQLException e)
        {
            logger.error("Cannot get questions!");
            logger.error(null, e);
        }
        catch (UnknownTypeError unknownTypeError)
        {
            logger.error("Unknown question type!");
            logger.error(null, unknownTypeError);
        }

        return questionList;
    }
}
