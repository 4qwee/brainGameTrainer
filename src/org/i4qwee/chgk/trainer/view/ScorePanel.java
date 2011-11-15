package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.model.enums.GameState;
import org.i4qwee.chgk.trainer.model.events.NamesChangedEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:09
 */
public class ScorePanel extends AbstractPanel implements Observer
{
    public static final int MAX_HEIGHT = 100;

    SingleScorePanel leftScorePanel;
    SingleScorePanel rightScorePanel;

    public ScorePanel(TimerButtonPanel timerButtonPanel)
    {
        super();
        ScoreManagerSingleton.getInstance().addObserver(this);
        GameStateSingleton.getInstance().addObserver(this);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));

        leftScorePanel = new SingleScorePanel(AnswerSide.LEFT);
        rightScorePanel = new SingleScorePanel(AnswerSide.RIGHT);

        add(leftScorePanel);
        add(timerButtonPanel);
        add(rightScorePanel);
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);
        leftScorePanel.addMouseListener(mouseListener);
        rightScorePanel.addMouseListener(mouseListener);
    }

    public void update(Observable o, Object arg)
    {
        if (arg != null)
        {
            if (arg instanceof AnswerSide)
            {
                switch ((AnswerSide) arg)
                {
                    case LEFT:
                        selectSingleScorePanel(AnswerSide.LEFT);
                        break;
                    case RIGHT:
                        selectSingleScorePanel(AnswerSide.RIGHT);
                        break;
                }
            }
            else if (arg instanceof GameState)
            {
                if (arg == GameState.FINISHED || arg == GameState.RUNNING)
                    removeSelection();
            }
            else if (arg instanceof NamesChangedEvent)
            {
                setNames(((NamesChangedEvent) arg).getLeftName(), ((NamesChangedEvent) arg).getRightName());
            }
        }
        else
        {
            leftScorePanel.setScore(ScoreManagerSingleton.getInstance().getLeftScore());
            rightScorePanel.setScore(ScoreManagerSingleton.getInstance().getRightScore());
        }
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

    public void setNames(String leftName, String rightName)
    {
        leftScorePanel.setName(leftName);
        rightScorePanel.setName(rightName);
    }
}
