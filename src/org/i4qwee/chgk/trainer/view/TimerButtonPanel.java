package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.listener.TimeListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:59
 */
public class TimerButtonPanel extends AbstractPanel implements TimeListener
{
    private static final String INIT_TEXT = "0:00:000";

    private final JButton timeButton;

    public TimerButtonPanel()
    {
        timeButton = new JButton(INIT_TEXT);
        timeButton.setFont(DefaultUIProvider.getDisplayFont());

        setLayout(new BorderLayout());
        add(timeButton, BorderLayout.CENTER);

        setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        TimeManager.getInstance().addListener(this);
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);
        timeButton.addMouseListener(mouseListener);
    }

    public void onTimeChanged(int time)
    {
        timeButton.setText(TimeManager.getTimeString(time));
    }
}
