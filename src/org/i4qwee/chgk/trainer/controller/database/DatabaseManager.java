package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.UnknownTypeError;
import org.i4qwee.chgk.trainer.model.enums.Type;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 15:20
 */
public class DatabaseManager
{
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class);

    private static GetQuestionsFromDatabaseStrategy getQuestionsFromDatabaseStrategy = new GetQuestionsFromSqliteDatabase();

    public static List<Question> getRandomQuestions(int count, Type type)
    {
        List<Question> questionList = new ArrayList<Question>(count);

        try
        {
            PreparedStatement getRandomQuestionsStatement = getQuestionsFromDatabaseStrategy.getStatement();

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
            handleError("Cannot get questions from database!", e);
        }
        catch (UnknownTypeError e)
        {
            handleError("Unknown error on getting questions!", e);
        }

        return questionList;
    }

    private static void handleError(String message, Throwable e)
    {
        MessageDialog.getInstance().showError(message);
        LOGGER.error(message, e);
    }


    public static void setGetQuestionsFromDatabaseStrategy(GetQuestionsFromDatabaseStrategy getQuestionsFromDatabaseStrategy)
    {
        DatabaseManager.getQuestionsFromDatabaseStrategy = getQuestionsFromDatabaseStrategy;
    }
}
