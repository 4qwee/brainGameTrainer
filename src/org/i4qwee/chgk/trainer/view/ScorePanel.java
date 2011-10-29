package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:09
 */
public class ScorePanel extends JPanel
{
    public static final int MAX_HEIGHT = 100;

    SingleScorePanel leftScorePanel;
    SingleScorePanel rightScorePanel;

    public ScorePanel(TimerButtonPanel timerButtonPanel)
    {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));

        leftScorePanel = new SingleScorePanel();
        rightScorePanel = new SingleScorePanel();

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
}
