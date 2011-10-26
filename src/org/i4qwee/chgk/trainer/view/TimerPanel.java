package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:59
 */
public class TimerPanel extends JPanel
{
    private static final Color BORDER_COLOR = Color.darkGray;
    private static final int BORDER_THICKNESS = 3;
    private static final Color BACKGROUND_COLOR = Color.lightGray;
    private static final int TIMER_DELAY = 13;
    private static final String INIT_TEXT = "0:00:000";

    private JLabel timeLabel;
    private Timer timer;
    private int time;

    public TimerPanel()
    {
        Border lineBorder = BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS);
        Border emptyBorder = BorderFactory.createEmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS);
        setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

        setBackground(BACKGROUND_COLOR);
        setOpaque(true);

        BoxLayout verticalBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(verticalBoxLayout);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setOpaque(true);

        add(Box.createVerticalGlue());
        add(centerPanel);
        add(Box.createVerticalGlue());

        timeLabel = new JLabel(INIT_TEXT);
        timeLabel.setFont(new Font("Courier", Font.BOLD, 50));

        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(timeLabel);
        centerPanel.add(Box.createHorizontalGlue());

        timer = new Timer(TIMER_DELAY, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                time += TIMER_DELAY;
                setTimeLabelText();
            }
        });
        }

    private void setTimeLabelText()
    {
        long tmpTime = time % 10L;
        String timeText = Long.toString(tmpTime);
        tmpTime = (time % 100L - time % 10L) / 10L;
        timeText = Long.toString(tmpTime) + timeText;
        tmpTime = (time % 1000L - time % 100L) / 100L;
        timeText = ":" + Long.toString(tmpTime) + timeText;
        tmpTime = (time % 10000L - time % 1000L) / 1000L;
        timeText = Long.toString(tmpTime) + timeText;
        tmpTime = (time % 60000L - time % 10000L) / 10000L;
        timeText = ":" + Long.toString(tmpTime) + timeText;
        tmpTime = (time % 600000L - time % 60000L) / 60000L;
        timeText = Long.toString(tmpTime) + timeText;

        timeLabel.setText(timeText);
    }

    public void timerStart()
    {
        timer.start();
    }

    public void timerStop()
    {
        timer.stop();
    }

    public void timerRestart()
    {
        timer.stop();
        time = 0;
        timeLabel.setText(INIT_TEXT);
    }
}
