package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:59
 */
public class TimerButton extends JPanel
{
    public static final String INIT_TEXT = "0:00:000";
    private static final String FONT_NAME = "Courier";

    private JButton timeButton;

    public TimerButton()
    {
        timeButton = new JButton(INIT_TEXT);
        timeButton.setFont(new Font(FONT_NAME, Font.BOLD, 50));

        setLayout(new BorderLayout());
        add(timeButton, BorderLayout.CENTER);
    }

    public JButton getTimeButton()
    {
        return timeButton;
    }
}
