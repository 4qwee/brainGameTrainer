package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.QuestionListener;
import org.i4qwee.chgk.trainer.model.Question;

/**
 * User: 4qwee
 * Date: 15.01.12
 * Time: 12:47
 */
public class QuestionManager extends Manager<QuestionListener>
{
    private static final QuestionManager instance = new QuestionManager();

    private Question question;

    private QuestionManager()
    {
    }

    public static QuestionManager getInstance()
    {
        return instance;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
        notifyListeners();
    }

    private void notifyListeners()
    {
        for (QuestionListener listener : listeners)
            listener.onQuestionChange(question);
    }
}
