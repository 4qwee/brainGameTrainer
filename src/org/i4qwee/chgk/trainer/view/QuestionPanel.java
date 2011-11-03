package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.model.Question;

import javax.swing.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 10:24
 */
public class QuestionPanel extends AbstractPanel
{
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;

    public QuestionPanel()
    {
        setFocusable(false);

        questionTextArea = new SingleQuestionTextArea();
        answerTextArea = new SingleQuestionTextArea();

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
