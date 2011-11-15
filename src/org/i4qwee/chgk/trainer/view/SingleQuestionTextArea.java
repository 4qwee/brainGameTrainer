package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 14:02
 */
public class SingleQuestionTextArea extends JTextArea
{
    public SingleQuestionTextArea()
    {
        super();

        setFont(DefaultUIProvider.getQuestionFont());
        setLineWrap(true);
        setWrapStyleWord(true);
        setEditable(false);
        setBorder(DefaultUIProvider.getDefaultEmptyEtchedEmptyBorder());
        setMargin(new Insets(20, 20, 20, 20));
        setFocusable(false);
    }

    public void paintComponent(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(new GradientPaint(0, 0, Color.white, 0, getHeight(), new Color(212, 212, 212)));
        graphics2D.fillRect(5, 5, getWidth() - 10, getHeight() - 10);//todo remove this hardcode

        super.paintComponent(graphics);
    }
}
