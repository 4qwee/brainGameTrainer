package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.enums.Type;
import org.i4qwee.chgk.trainer.view.QuestionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 10:45
 */
public class QuestionPanelTest extends JFrame
{
    public QuestionPanelTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final QuestionPanel questionPanel = new QuestionPanel();
        JButton getButton = new JButton("get");
        getButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                List<Question> questions = DatabaseManager.getRandomQuestions(10, Type.BRAIN);
                questionPanel.setQuestion(questions.get(0), false);
            }
        });

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(boxLayout);
        getContentPane().add(questionPanel);
        getContentPane().add(getButton);

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new QuestionPanelTest();
    }
}
