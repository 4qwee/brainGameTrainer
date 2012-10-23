package org.i4qwee.chgk.trainer.new_brain.states;

import org.i4qwee.chgk.trainer.model.enums.AnswerSide;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/3/12
 * Time: 11:16 PM
 */
public abstract class State
{
    public void doAction(){}

    public void doAnswer(AnswerSide answerSide){}

    public void doCancel(){}

    public void doNewGame(){}
}
