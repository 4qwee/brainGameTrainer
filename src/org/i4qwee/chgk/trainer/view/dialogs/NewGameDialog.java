package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.new_brain.actionlisteners.*;
import org.i4qwee.chgk.trainer.new_brain.preferences.PreferencesNames;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class NewGameDialog extends AbstractDialog
{
    private static final NewGameDialog instance = new NewGameDialog();

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftName;
    private JTextField rightName;
    private JTextField roundsCount;

    private NewGameDialog()
    {
        super();

        setResizable(false);
        setSize(350, 200);

        contentPane.setBorder(DefaultUIProvider.getDefaultEmptyEtchedEmptyBorder());

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
        String leftName = this.leftName.getText();
        String rightName = this.rightName.getText();
        String maxRound = roundsCount.getText();

        NamesManager.getInstance().setNames(leftName, rightName);
        RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRound));
        ScoreManagerSingleton.getInstance().newGame();

        Properties properties = System.getProperties();
        properties.setProperty(PreferencesNames.LEFT_NAME, leftName);
        properties.setProperty(PreferencesNames.RIGHT_NAME, rightName);
        properties.setProperty(PreferencesNames.MAX_SCORE, maxRound);

        try
        {
            properties.store(new FileOutputStream(PreferencesNames.FILE_NAME), null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        dispose();
    }

    private void onCancel()
    {
        dispose();
    }

    public static NewGameDialog getInstance()
    {
        return instance;
    }

    public void showDialog()
    {
        org.i4qwee.chgk.trainer.new_brain.actionlisteners.MouseListener.setHandle(false);
        super.showDialog();
        org.i4qwee.chgk.trainer.new_brain.actionlisteners.MouseListener.setHandle(true);
    }
}
