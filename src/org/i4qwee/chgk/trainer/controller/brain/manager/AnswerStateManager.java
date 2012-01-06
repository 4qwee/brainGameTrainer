package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.AnswerStateListener;
import org.i4qwee.chgk.trainer.model.enums.AnswerState;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 14:12
 */
public class AnswerStateManager extends Manager<AnswerStateListener>
{
    private static final AnswerStateManager instance = new AnswerStateManager();

    private AnswerState answerState = AnswerState.NOBODY_ANSWERED;

    private AnswerStateManager()
    {
    }

    public static AnswerStateManager getInstance()
    {
        return instance;
    }

    public AnswerState getAnswerState()
    {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState)
    {
        this.answerState = answerState;
        notifyListeners();
    }

    @Override
    protected void notifyListeners()
    {
        for (AnswerStateListener listener : listeners)
            listener.onAnswerStateChanged(answerState);
    }
}
