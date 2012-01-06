package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.model.enums.AnswerState;
import org.i4qwee.chgk.trainer.model.enums.GameState;
import org.i4qwee.chgk.trainer.model.events.NamesChangedEvent;

import java.util.Observable;

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

    private String leftName;
    private String rightName;

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

    private int getPrice()
    {
        return PriceManager.getInstance().getPrice();
    }

    private void setPrice(int price)
    {
        PriceManager.getInstance().setPrice(price);
    }

    public void answer(boolean isCorrect)
    {
        if (isCorrect)
        {
            switch (answerSide)
            {
                case LEFT:
                    leftScore += getPrice();
                    break;
                case RIGHT:
                    rightScore += getPrice();
                    break;
            }

            setPrice(1);
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
        setPrice(getPrice() + 1);
    }

    public void setNames(String leftName, String rightName)
    {
        this.leftName = leftName;
        this.rightName = rightName;

        setChanged();
        notifyObservers(new NamesChangedEvent(leftName, rightName));
    }

    public String getAnswersName()
    {
        switch (answerSide)
        {
            case LEFT:
                return leftName;
            case RIGHT:
                return rightName;
            default:
                return null;
        }
    }

    public void newGame()
    {
        GameStateSingleton.getInstance().setGameState(GameState.INIT);
        GameStateSingleton.getInstance().resetRoundsCount();
        leftScore = rightScore = 0;
        setChanged();
        notifyObservers();

        setPrice(1);
    }

    public String getLeftName()
    {
        return leftName;
    }

    public String getRightName()
    {
        return rightName;
    }
}
