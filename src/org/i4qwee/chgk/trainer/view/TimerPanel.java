package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

    public static final String INIT_TEXT = "0:00:000";
    private static final String FONT_NAME = "Courier";

    private JLabel timeLabel;

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
        timeLabel.setFont(new Font(FONT_NAME, Font.BOLD, 50));

        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(timeLabel);
        centerPanel.add(Box.createHorizontalGlue());
    }

    public JLabel getTimeLabel()
    {
        return timeLabel;
    }
}
