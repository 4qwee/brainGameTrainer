package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.model.Question;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 10:24
 */
public class QuestionPanel extends AbstractPanel
{
    private JEditorPane questionTextArea;
    private JEditorPane answerTextArea;

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

        answerTextArea.setText(prepareAnswer(question));

        if (hideAnswer)
            answerTextArea.setVisible(false);
    }

    private String prepareAnswer(Question question)
    {
        String answerString = "<html>" + "<b>Ответ: </b>" + question.getAnswer();

        if (question.getComments() != null)
            answerString += "<br/><br/>" + "<b>Комментарий: </b>" + question.getComments();

        return answerString;
    }

    public void setMaximumSize(Dimension maximumSize)
    {
        super.setMaximumSize(maximumSize);

        questionTextArea.setMaximumSize(maximumSize);
        answerTextArea.setMaximumSize(maximumSize);
    }
}
