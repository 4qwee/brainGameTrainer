package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;

import javax.swing.*;
import java.awt.event.*;

public class NewGameDialog extends AbstractDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftName;
    private JTextField rightName;
    private JTextField roundsCount;

    public NewGameDialog(JFrame owner)
    {
        super(owner);

        setResizable(false);
        setSize(350, 200);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK()
    {
        ScoreManagerSingleton.getInstance().setNames(leftName.getText(), rightName.getText());
        GameStateSingleton.getInstance().setMaxRoundsCount(Integer.parseInt(roundsCount.getText()));
        ScoreManagerSingleton.getInstance().newGame();

        dispose();
    }

    private void onCancel()
    {
        dispose();
    }
}
