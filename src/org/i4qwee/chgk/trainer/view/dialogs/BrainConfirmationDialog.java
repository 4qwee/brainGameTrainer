package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class BrainConfirmationDialog extends AbstractDialog implements Observer
{
    private JPanel contentPane;
    private JButton correctButton;
    private JButton incorrectButton;
    private JLabel messageLabel;

    public BrainConfirmationDialog()
    {
        GameStateSingleton.getInstance().addObserver(this);

        setResizable(false);

        setContentPane(contentPane);
        getRootPane().setDefaultButton(correctButton);

        correctButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCorrect();
            }
        });

        incorrectButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onIncorrect();
            }
        });

// call onIncorrect() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onIncorrect();
            }
        });

// call onIncorrect() on LEFT
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCorrect();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

// call onIncorrect() on RIGHT
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onIncorrect();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCorrect()
    {
        ScoreManagerSingleton.getInstance().answer(true);
        dispose();
    }

    private void onIncorrect()
    {
        ScoreManagerSingleton.getInstance().answer(false);
        dispose();
    }

    public void update(Observable o, Object arg)
    {
        switch (GameStateSingleton.getInstance().getGameState())
        {
            case PAUSED:

                String name = ScoreManagerSingleton.getInstance().getAnswersName();

                if (name != null && !name.equals(""))
                    messageLabel.setText(ScoreManagerSingleton.getInstance().getAnswersName() + ", правильно?");
                else
                    messageLabel.setText("Правильно?");

                showDialog();
                break;
        }
    }
}
