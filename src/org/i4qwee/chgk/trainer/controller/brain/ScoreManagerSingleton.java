package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.brain.manager.*;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.enums.AnswerState;
import org.i4qwee.chgk.trainer.model.enums.GameState;

import java.util.Observable;

/**
 * User: 4qwee
 * Date: 30.10.11
 * Time: 20:36
 */
public class ScoreManagerSingleton extends Observable
{
    private static ScoreManagerSingleton ourInstance = new ScoreManagerSingleton();

    private AnswerState answerState = AnswerState.NOBODY_ANSWERED;

    private final ScoreManager scoreManager = ScoreManager.getInstance();
    private final PriceManager priceManager = PriceManager.getInstance();
    private final RoundManager roundManager = RoundManager.getInstance();
    private final AnswerSideManager answerSideManager = AnswerSideManager.getInstance();

    public static ScoreManagerSingleton getInstance()
    {
        return ourInstance;
    }

    private ScoreManagerSingleton()
    {
    }

    public void setFalseStart()
    {
        answerState = AnswerState.ONE_ANSWERED;
        GameStateSingleton.getInstance().setGameState(GameState.RUNNING);
    }

    public void answer(boolean isCorrect)
    {
        if (isCorrect)
        {
            switch (answerSideManager.getAnswerSide())
            {
                case LEFT:
                    scoreManager.increaseLeftScore(priceManager.getPrice());
                    break;
                case RIGHT:
                    scoreManager.increaseRightScore(priceManager.getPrice());
                    break;
            }

            priceManager.setPrice(1);
            answerState = AnswerState.NOBODY_ANSWERED;
            GameStateSingleton.getInstance().setGameState(GameState.FINISHED);
            setChanged();
            notifyObservers();
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
                    noOneAnswered();
                    break;
            }
        }
    }

    public void noOneAnswered()
    {
        answerState = AnswerState.NOBODY_ANSWERED;
        GameStateSingleton.getInstance().setGameState(GameState.FINISHED);
        priceManager.setPrice(priceManager.getPrice() + 1);
    }

    public void newGame()
    {
        GameStateSingleton.getInstance().setGameState(GameState.INIT);
        roundManager.setRound(1);

        scoreManager.setLeftScore(0);
        scoreManager.setRightScore(0);

        priceManager.setPrice(1);
    }
}
