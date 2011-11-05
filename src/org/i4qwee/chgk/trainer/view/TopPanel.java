package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.view.dialogs.NewGameDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 02.11.11
 * Time: 21:44
 */
public class TopPanel extends AbstractPanel
{
    public static final int MAX_HEIGHT = 30;
    private NewGameDialog newGameDialog;

    public TopPanel(JFrame parentFrame)
    {
        setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));

        QuestionCostPanel questionCostPanel = new QuestionCostPanel();
        add(questionCostPanel);

        add(Box.createHorizontalGlue());

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newGameDialog.showDialog();
            }
        });
        add(newGameButton);

        newGameDialog = new NewGameDialog();
    }
}
