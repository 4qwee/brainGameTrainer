package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.model.enums.AnswerState;
import org.i4qwee.chgk.trainer.model.enums.GameState;
import org.i4qwee.chgk.trainer.model.events.NamesChangedEvent;
import org.i4qwee.chgk.trainer.model.events.PriceChangedEvent;

import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 30.10.11
 * Time: 20:36
 */
public class ScoreManagerSingleton extends Observable implements Observer
{
    private static ScoreManagerSingleton ourInstance = new ScoreManagerSingleton();

    private int leftScore;
    private int rightScore;

    private String leftName;
    private String rightName;

    private int price = 1;

    private AnswerState answerState = AnswerState.NOBODY_ANSWERED;
    private AnswerSide answerSide;

    public static ScoreManagerSingleton getInstance()
    {
        return ourInstance;
    }

    private ScoreManagerSingleton()
    {
        GameStateSingleton.getInstance().addObserver(this);
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

    public int getPrice()
    {
        return price;
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
                    setChanged();
                    break;
            }
        }
    }

    public void update(Observable o, Object arg)
    {
        if (arg != null && arg instanceof GameState)
        {
            switch ((GameState) arg)
            {
                case WAIT_START_TIMER:
                    notifyObservers(new PriceChangedEvent(price));
                    break;
            }
        }
    }

    public void setNames(String leftName, String rightName)
    {
        this.leftName = leftName;
        this.rightName = rightName;

        setChanged();
        notifyObservers(new NamesChangedEvent(leftName, rightName));
    }

    public String getLeftName()
    {
        return leftName;
    }

    public String getRightName()
    {
        return rightName;
    }

    public void newGame()
    {
        leftScore = rightScore = 0;
        setChanged();
        notifyObservers();
    }
}
