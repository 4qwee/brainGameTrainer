package org.i4qwee.chgk.trainer.new_brain.states;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.brain.SoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.ScoreManager;
import org.i4qwee.chgk.trainer.controller.time.Timer;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/20/12
 * Time: 4:58 PM
 */
public class EndRound extends NoQuestionsLoaded
{
    private boolean isRunning = false;

    @Override
    public void doAction()
    {
        if (isRunning)
            return;

        isRunning = true;

        RoundManager roundManager = RoundManager.getInstance();
        ScoreManager scoreManager = ScoreManager.getInstance();
        MessageDialog messageDialog = MessageDialog.getInstance();
        NamesManager namesManager = NamesManager.getInstance();

        int nextRound = roundManager.getRound() + 1;
        int maxRound = roundManager.getMaxRound();

        if (maxRound > 0 && nextRound > maxRound)
        {
            if (scoreManager.getLeftScore() > scoreManager.getRightScore())
                messageDialog.show(namesManager.getLeftName() + " выиграл!");
            else if (scoreManager.getLeftScore() < scoreManager.getRightScore())
                messageDialog.show(namesManager.getRightName() + " выиграл!");
            else
                messageDialog.show("Ничья!");

            ScoreManagerSingleton.getInstance().newGame();
        }
        else
        {
            roundManager.setRound(nextRound);
        }

        SoundManager.getInstance().resetSounds();
        Timer.getInstance().restart();

        super.doAction();
    }
}
