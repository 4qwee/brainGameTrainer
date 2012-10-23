package org.i4qwee.chgk.trainer.new_brain.states;

import org.i4qwee.chgk.trainer.controller.brain.SoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.AnswerSideManager;
import org.i4qwee.chgk.trainer.controller.time.Timer;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.view.FalseStartLabel;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;
import org.i4qwee.chgk.trainer.view.dialogs.NewGameDialog;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/16/12
 * Time: 10:10 PM
 */
public class QuestionLoaded extends State
{
    @Override
    public void doAnswer(AnswerSide answerSide)
    {
        AnswerSideManager answerSideManager = AnswerSideManager.getInstance();
        answerSideManager.setAnswerSide(answerSide);
        MessageDialog.getInstance().show(answerSideManager.getAnswersName() + ", фальстарт!");
        answerSideManager.setAnswerSide(null);
        FalseStartLabel.getInstance().setFalseStart(answerSide);

        StateManager.getInstance().setState(new TimeIsRunningOut(answerSide));
        startGame();
    }

    @Override
    public void doAction()
    {
        StateManager.getInstance().setState(new TimeIsRunningOut(null));
        startGame();
    }

    private void startGame()
    {
        Timer.getInstance().start();
        SoundManager.getInstance().playStartSound();
    }

    @Override
    public void doNewGame()
    {
        NewGameDialog.getInstance().showDialog();
    }
}
