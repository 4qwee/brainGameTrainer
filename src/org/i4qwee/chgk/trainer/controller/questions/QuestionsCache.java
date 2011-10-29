package org.i4qwee.chgk.trainer.controller.questions;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Type;

import java.util.List;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:43
 */
public class QuestionsCache
{
    private static Logger logger = Logger.getLogger(QuestionsCache.class);

    private static int position;
    private static List<Question> questions;

    public static Question getNextQuestion()
    {
        if (questions == null || ++position >= questions.size())
        {
            position = 0;
            questions = DatabaseManager.getRandomQuestions(50, Type.BRAIN); //todo remove this hardcode
        }

        return questions.get(position);
    }
}
