package org.i4qwee.chgk.trainer.controller.brain;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.model.AnswerState;
import org.i4qwee.chgk.trainer.model.GameState;
import org.i4qwee.chgk.trainer.model.GameStateSingleton;
import org.i4qwee.chgk.trainer.view.ScorePanel;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 18:32
 */
public class ScoreManager
{
    public static Logger logger = Logger.getLogger(ScoreManager.class);

    private int currentPrice = 1;
    private ScorePanel scorePanel;
    private AnswerState answerState = AnswerState.NOBODY_ANSWERED;

    public ScoreManager(ScorePanel scorePanel)
    {
        this.scorePanel = scorePanel;
    }

    public void answer(boolean isCorrect, boolean isLeft)
    {
        if (isCorrect)
        {
            if (isLeft)
                scorePanel.addScore2Left(currentPrice);
            else
                scorePanel.addScore2Right(currentPrice);

            currentPrice = 1;
            GameStateSingleton.getInstance().setGameState(GameState.FINISHED);
            answerState = AnswerState.NOBODY_ANSWERED;
        }
        else
        {
            switch (answerState)
            {
                case NOBODY_ANSWERED:
                    answerState = AnswerState.ONE_ANSWERED;
                    GameStateSingleton.getInstance().setGameState(GameState.RUNNING);
                    break;
                case ONE_ANSWERED:
                    currentPrice++;
                    GameStateSingleton.getInstance().setGameState(GameState.FINISHED);
                    break;
                default:
                    logger.error("Unsupported answer state: " + answerState);
            }
        }
    }
}
