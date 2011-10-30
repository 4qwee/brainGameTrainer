package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.AnswerSide;
import org.i4qwee.chgk.trainer.model.AnswerState;
import org.i4qwee.chgk.trainer.model.GameState;

import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 30.10.11
 * Time: 20:36
 */
public class ScoreManagerSingleton extends Observable
{
    private static ScoreManagerSingleton ourInstance = new ScoreManagerSingleton();

    private int leftScore;
    private int rightScore;
    private int price = 1;
    private AnswerState answerState = AnswerState.NOBODY_ANSWERED;
    private AnswerSide answerSide;

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

    public AnswerState getAnswerState()
    {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState)
    {
        this.answerState = answerState;
    }

    public AnswerSide getAnswerSide()
    {
        return answerSide;
    }

    public void setAnswerSide(AnswerSide answerSide)
    {
        this.answerSide = answerSide;

        setChanged();
        notifyObservers(answerSide);
    }

    public int getLeftScore()
    {
        return leftScore;
    }

    public int getRightScore()
    {
        return rightScore;
    }

    public void answer(boolean isCorrect)
    {
        if (isCorrect)
        {
            switch (answerSide)
            {
                case LEFT:
                    leftScore += price;
                    break;
                case RIGHT:
                    rightScore += price;
                    break;
            }

            price = 1;
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
                    answerState = AnswerState.NOBODY_ANSWERED;
                    GameStateSingleton.getInstance().setGameState(GameState.FINISHED);
                    price++;
                    break;
            }
        }
    }
}
