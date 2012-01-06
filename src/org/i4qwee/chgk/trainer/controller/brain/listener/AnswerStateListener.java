package org.i4qwee.chgk.trainer.controller.brain.listener;

import org.i4qwee.chgk.trainer.model.enums.AnswerState;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 14:11
 */
public interface AnswerStateListener
{
    void onAnswerStateChanged(AnswerState answerState);
}
