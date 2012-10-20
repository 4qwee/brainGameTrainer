package org.i4qwee.chgk.trainer.controller.time;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.brain.SoundManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.GameStateManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.TimeManager;
import org.i4qwee.chgk.trainer.model.enums.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 27.10.11
 * Time: 7:23
 */
public class Timer
{
    private static final Timer instance = new Timer();

    private static final int TIMER_DELAY = 13;
    private static final int WARN_TIME = 15000;
    private static final int OVER_TIME = 20000;

    private javax.swing.Timer timer;

    private boolean warnPlayed = false;

    private final GameStateManager gameStateManager = GameStateManager.getInstance();
    private final TimeManager timeManager = TimeManager.getInstance();
    private final SoundManager soundManager = SoundManager.getInstance();
    private final ScoreManagerSingleton scoreManager = ScoreManagerSingleton.getInstance();

    public Timer()
    {
        timer = new javax.swing.Timer(TIMER_DELAY, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int time = timeManager.getTime();

                timeManager.setTime(time + TIMER_DELAY);

                if (!warnPlayed && time > WARN_TIME)
                {
                    soundManager.playWarnSound();
                    warnPlayed = true;
                }

                if (time > OVER_TIME)
                {
                    soundManager.playOverSound();
                    gameStateManager.setGameState(GameState.FINISHED);

                    timeManager.setTime(OVER_TIME);
                    scoreManager.noOneAnswered();
                }
            }
        });
    }

    public static Timer getInstance()
    {
        return instance;
    }

    public void start()
    {
        timer.start();
    }

    public void stop()
    {
        timer.stop();
    }

    public void restart()
    {
        timer.stop();
        timeManager.setTime(0);
        warnPlayed = false;
    }
}
