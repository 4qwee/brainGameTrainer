package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 9:22
 */
public class GetQuestionsTest extends JFrame
{
    public GetQuestionsTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);
        getContentPane().setLayout(boxLayout);

        final JLabel getLabel = new JLabel();
        getContentPane().add(getLabel);

        JButton getButton = new JButton("Get");
        getButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                getLabel.setText("Getting...");
                DatabaseManager.getRandomQuestions(50, Type.BRAIN);
                getLabel.setText("Successfully get!");
            }
        });
        getContentPane().add(getButton);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GetQuestionsTest();
    }
}
