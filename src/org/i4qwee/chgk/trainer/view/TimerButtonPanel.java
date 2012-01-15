package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.listener.TimeListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.TimeManager;
import org.i4qwee.chgk.trainer.controller.time.TimeButtonActionListener;

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
    public static final String INIT_TEXT = "0:00:000";

    private final TimeManager timeManager = TimeManager.getInstance();

    private JButton timeButton;

    public TimerButtonPanel()
    {
        timeButton = new JButton(INIT_TEXT);
        timeButton.setFont(DefaultUIProvider.getDisplayFont());
        timeButton.addActionListener(new TimeButtonActionListener());

        setLayout(new BorderLayout());
        add(timeButton, BorderLayout.CENTER);

        setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        timeManager.addListener(this);
    }

    public JButton getTimeButton()
    {
        return timeButton;
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
