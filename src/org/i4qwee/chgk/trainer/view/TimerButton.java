package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:59
 */
public class TimerButton extends JPanel
{
    public static final String INIT_TEXT = "0:00:000";
    private static final String FONT_NAME = "Courier";
    public static final int MARGIN = 5;

    private JButton timeButton;
    private org.i4qwee.chgk.trainer.controller.time.Timer timer;

    public TimerButton()
    {
        timeButton = new JButton(INIT_TEXT);
        timeButton.setFont(new Font(FONT_NAME, Font.BOLD, 50));

        setLayout(new BorderLayout());
        add(timeButton, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        Border emptyBorder = BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN);
        setBorder(emptyBorder);

        timer = new org.i4qwee.chgk.trainer.controller.time.Timer(this);
    }

    public JButton getTimeButton()
    {
        return timeButton;
    }

    public org.i4qwee.chgk.trainer.controller.time.Timer getTimer()
    {
        return timer;
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);
        timeButton.addMouseListener(mouseListener);
    }
}
