package org.i4qwee.chgk.trainer.new_brain.states;

import org.i4qwee.chgk.trainer.controller.brain.SoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.AnswerSideManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.PriceManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.ScoreManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.TimeManager;
import org.i4qwee.chgk.trainer.controller.time.Timer;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.view.dialogs.BrainConfirmationDialog;
import org.i4qwee.chgk.trainer.view.dialogs.NewGameDialog;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/16/12
 * Time: 10:58 PM
 */
public class TimeIsRunningOut extends State
{
    private AnswerSide answerSide = null;
    private boolean isRunning = false;

    public TimeIsRunningOut(AnswerSide answerSide)
    {
        this.answerSide = answerSide;
    }

    public void doNewGame()
    {
        Timer.getInstance().stop();
        NewGameDialog.getInstance().showDialog();
    }

    public void doAnswer(AnswerSide answerSide)
    {
        if (this.answerSide == answerSide || isRunning)
            return;
        isRunning = true;

        AnswerSideManager answerSideManager = AnswerSideManager.getInstance();
        answerSideManager.setAnswerSide(answerSide);
        PriceManager priceManager = PriceManager.getInstance();
        Timer timer = Timer.getInstance();

        timer.stop();
        boolean isCorrect = new BrainConfirmationDialog().showConfirmationDialog();
        answerSideManager.setAnswerSide(null);

        if (isCorrect)
        {
            ScoreManager.getInstance().increaseScore(answerSide, priceManager.getPrice());
            priceManager.setPrice(1);
            goNext();
        }
        else
        {
            if (this.answerSide == null)
            {
                this.answerSide = answerSide;
                timer.start();
                isRunning = false;
            }
            else
            {
                priceManager.setPrice(priceManager.getPrice() + 1);
                goNext();
            }
        }
    }

    private void goNext()
    {
        StateManager.getInstance().setState(new EndRound());
    }
}
