package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:19
 */
public class DefaultUIProvider
{
    private static final int MARGIN = 5;
    private static final String FONT_NAME = "Courier";
    private static final int DISPLAY_FONT_SIZE = 50;
    private static final int QUESTION_FONT_SIZE = 20;
    private static final int QUESTION_PRICE_FONT_SIZE = 13;

    private static final Border defaultEmptyBorder = BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN);
    private static final Border defaultEmptyEtchedBorder = BorderFactory.createCompoundBorder(defaultEmptyBorder, BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    private static final Border defaultEmptyEtchedEmptyBorder = BorderFactory.createCompoundBorder(defaultEmptyEtchedBorder, defaultEmptyBorder);
    private static final Font displayFont = new Font(FONT_NAME, Font.BOLD, DISPLAY_FONT_SIZE);
    private static final Font questionFont = new Font(FONT_NAME, Font.PLAIN, QUESTION_FONT_SIZE);
    private static final Font questionPriceFont = new Font(FONT_NAME, Font.BOLD, QUESTION_PRICE_FONT_SIZE);

    public static Border getDefaultEmptyBorder()
    {
        return defaultEmptyBorder;
    }

    public static Border getDefaultEmptyEtchedBorder()
    {
        return defaultEmptyEtchedBorder;
    }

    public static Font getDisplayFont()
    {
        return displayFont;
    }

    public static Font getQuestionFont()
    {
        return questionFont;
    }

    public static Border getDefaultEmptyEtchedEmptyBorder()
    {
        return defaultEmptyEtchedEmptyBorder;
    }

    public static Font getQuestionPriceFont()
    {
        return questionPriceFont;
    }
}
