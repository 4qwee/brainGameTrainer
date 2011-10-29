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
}
