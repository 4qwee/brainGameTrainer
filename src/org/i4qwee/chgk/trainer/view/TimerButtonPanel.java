package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:59
 */
public class TimerButtonPanel extends AbstractPanel
{
    public static final String INIT_TEXT = "0:00:000";

    private JButton timeButton;

    public TimerButtonPanel()
    {
        timeButton = new JButton(INIT_TEXT);
        timeButton.setFont(DefaultUIProvider.getDisplayFont());

        setLayout(new BorderLayout());
        add(timeButton, BorderLayout.CENTER);

        setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        new org.i4qwee.chgk.trainer.controller.time.Timer(this);
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
}
