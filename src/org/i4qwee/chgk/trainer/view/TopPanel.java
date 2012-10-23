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
class TopPanel extends AbstractPanel
{
    private static final int MAX_HEIGHT = 30;

    public TopPanel()
    {
        setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, MAX_HEIGHT));

        QuestionCostLabel questionCostLabel = new QuestionCostLabel();
        add(questionCostLabel);

        add(Box.createHorizontalStrut(10));

        RoundsLabel roundsLabel = new RoundsLabel();
        add(roundsLabel);

        add(Box.createHorizontalStrut(10));

        add(FalseStartLabel.getInstance());

        add(Box.createHorizontalGlue());

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                NewGameDialog.getInstance().showDialog();
            }
        });
        add(newGameButton);
    }
}
