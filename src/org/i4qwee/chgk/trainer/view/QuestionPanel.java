package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.model.Question;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 10:24
 */
public class QuestionPanel extends JPanel
{
    private static final String FONT_NAME = "Courier";
    private static final int FONT_SIZE = 20;
    private static final int MARGIN = 5;

    private JTextArea questionTextArea;
    private JTextArea answerTextArea;

    public QuestionPanel()
    {
        Font font = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border emptyBorder = BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN);
        Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, etchedBorder);

        questionTextArea = new JTextArea();
        questionTextArea.setFont(font);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBorder(compoundBorder);

        answerTextArea = new JTextArea();
        answerTextArea.setFont(font);
        answerTextArea.setLineWrap(true);
        answerTextArea.setWrapStyleWord(true);
        answerTextArea.setEditable(false);
        answerTextArea.setBorder(compoundBorder);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        add(questionTextArea);
        add(Box.createVerticalStrut(10));
        add(answerTextArea);
    }

    public void setQuestion(Question question)
    {
        setQuestion(question, true);
    }

    public void setQuestion(Question question, boolean hideAnswer)
    {
        questionTextArea.setText(question.getQuestion());

        answerTextArea.setText(question.getAnswer());

        if (hideAnswer)
            answerTextArea.setVisible(false);
    }
}
