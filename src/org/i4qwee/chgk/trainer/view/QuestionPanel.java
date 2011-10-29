package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.model.Question;
import sun.plugin2.message.JavaScriptReleaseObjectMessage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 10:24
 */
public class QuestionPanel extends JPanel
{
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;

    public QuestionPanel()
    {
        questionTextArea = new JTextArea();
        questionTextArea.setFont(DefaultUIProvider.getQuestionFont());
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());

        answerTextArea = new JTextArea();
        answerTextArea.setFont(DefaultUIProvider.getQuestionFont());
        answerTextArea.setLineWrap(true);
        answerTextArea.setWrapStyleWord(true);
        answerTextArea.setEditable(false);
        answerTextArea.setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());

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

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);
        questionTextArea.addMouseListener(mouseListener);
        answerTextArea.addMouseListener(mouseListener);
    }
}
