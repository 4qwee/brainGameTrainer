package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 14:02
 */
public class SingleQuestionTextArea extends JEditorPane
{
    public SingleQuestionTextArea()
    {
        super();

        setFont(DefaultUIProvider.getQuestionFont());
        setEditorKit(new HTMLEditorKit());
        setEditable(false);
        setBorder(DefaultUIProvider.getDefaultEmptyEtchedEmptyBorder());
        setMargin(new Insets(20, 20, 20, 20));
        setFocusable(false);
        setAlignmentX(JEditorPane.LEFT_ALIGNMENT);
        putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    }

    public void paintComponent(Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setPaint(new GradientPaint(0, 0, Color.white, 0, getHeight(), new Color(212, 212, 212)));
        graphics2D.fillRect(5, 5, getWidth() - 10, getHeight() - 10);//todo remove this hardcode

        super.paintComponent(graphics);
    }
}
