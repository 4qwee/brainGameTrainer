package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.listener.AnswerSideListener;
import org.i4qwee.chgk.trainer.controller.brain.listener.NamesListener;
import org.i4qwee.chgk.trainer.controller.brain.listener.ScoreListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.AnswerSideManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.ScoreManager;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:09
 */
public class ScorePanel extends AbstractPanel implements ScoreListener, NamesListener, AnswerSideListener
{
    public static final int MAX_HEIGHT = 100;

    private final SingleScorePanel leftScorePanel;
    private final SingleScorePanel rightScorePanel;

    private final ScoreManager scoreManager = ScoreManager.getInstance();


    public ScorePanel(TimerButtonPanel timerButtonPanel)
    {
        super();
        AnswerSideManager.getInstance().addListener(this);

        scoreManager.addListener(this);
        NamesManager namesManager = NamesManager.getInstance();
        namesManager.addListener(this);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));

        leftScorePanel = new SingleScorePanel(AnswerSide.LEFT);
        rightScorePanel = new SingleScorePanel(AnswerSide.RIGHT);

        add(leftScorePanel);
        add(timerButtonPanel);
        add(rightScorePanel);
    }

    private void selectSingleScorePanel(AnswerSide answerSide)
    {
        if (answerSide == AnswerSide.LEFT)
            setSelected(leftScorePanel);
        else if (answerSide == AnswerSide.RIGHT)
            setSelected(rightScorePanel);
    }

    private void setSelected(SingleScorePanel singleScorePanel)
    {
        singleScorePanel.setSelected(true);
    }

    private void removeSelection()
    {
        leftScorePanel.setSelected(false);
        rightScorePanel.setSelected(false);
    }

    void setNames(String leftName, String rightName)
    {
        leftScorePanel.setName(leftName);
        rightScorePanel.setName(rightName);
    }

    public void onScoreChanged()
    {
        leftScorePanel.setScore(scoreManager.getLeftScore());
        rightScorePanel.setScore(scoreManager.getRightScore());
    }

    public void onNamesChanged(String leftName, String rightName)
    {
        setNames(leftName, rightName);
    }

    public void onAnswerSideChanged(AnswerSide answerSide)
    {
        if (answerSide == null)
        {
            removeSelection();
            return;
        }

        switch (answerSide)
        {
            case LEFT:
                selectSingleScorePanel(AnswerSide.LEFT);
                break;
            case RIGHT:
                selectSingleScorePanel(AnswerSide.RIGHT);
                break;
        }
    }
}
