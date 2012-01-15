package org.i4qwee.chgk.trainer.controller.brain.listener;

import org.i4qwee.chgk.trainer.model.Question;

/**
 * User: 4qwee
 * Date: 15.01.12
 * Time: 12:46
 */
public interface QuestionListener
{
    void onQuestionChange(Question question);
}
