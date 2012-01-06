package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.AnswerSideListener;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 13:41
 */
public class AnswerSideManager extends Manager<AnswerSideListener>
{
    private static final AnswerSideManager instance = new AnswerSideManager();

    private AnswerSide answerSide = AnswerSide.LEFT;

    private final NamesManager namesManager = NamesManager.getInstance();

    private AnswerSideManager()
    {
    }

    public static AnswerSideManager getInstance()
    {
        return instance;
    }

    public AnswerSide getAnswerSide()
    {
        return answerSide;
    }

    public void setAnswerSide(AnswerSide answerSide)
    {
        this.answerSide = answerSide;
        notifyListeners();
    }

    public String getAnswersName()
    {
        switch (answerSide)
        {
            case LEFT:
                return namesManager.getLeftName();
            case RIGHT:
                return namesManager.getRightName();
            default:
                return null;
        }
    }

    @Override
    protected void notifyListeners()
    {
        for (AnswerSideListener listener : listeners)
            listener.onAnswerSideChanged(answerSide);
    }
}
