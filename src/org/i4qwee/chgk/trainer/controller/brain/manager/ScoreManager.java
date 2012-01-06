package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.ScoreListener;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 11:18
 */
public class ScoreManager extends Manager<ScoreListener>
{
    private static final ScoreManager instance = new ScoreManager();

    private int leftScore = 0;
    private int rightScore = 0;

    private ScoreManager()
    {
    }

    public static ScoreManager getInstance()
    {
        return instance;
    }

    public int getLeftScore()
    {
        return leftScore;
    }

    public void setLeftScore(int leftScore)
    {
        this.leftScore = leftScore;
        notifyListeners();
    }

    public void increaseLeftScore(int score)
    {
        setLeftScore(getLeftScore() + score);
    }

    public int getRightScore()
    {
        return rightScore;
    }

    public void setRightScore(int rightScore)
    {
        this.rightScore = rightScore;
        notifyListeners();
    }

    public void increaseRightScore(int score)
    {
        setRightScore(getRightScore() + score);
    }

    @Override
    protected void notifyListeners()
    {
        for (ScoreListener listener : listeners)
            listener.onScoreChanged(leftScore, rightScore);
    }
}
