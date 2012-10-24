package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.new_brain.preferences.BrainPreferences;
import org.i4qwee.chgk.trainer.new_brain.preferences.PreferencesNames;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import java.awt.event.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class NewGameDialog extends AbstractDialog
{
    private static final NewGameDialog instance = new NewGameDialog();

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftNameField;
    private JTextField rightNameField;
    private JTextField roundsCountField;

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

        loadPreferences();
    }

    private void loadPreferences()
    {
        Preferences preferences = BrainPreferences.getPreferences();
        if (preferences != null)
        {
            String leftName = preferences.get(PreferencesNames.LEFT_NAME, "");
            String rightName = preferences.get(PreferencesNames.RIGHT_NAME, "");
            String maxRounds = preferences.get(PreferencesNames.MAX_SCORE, "0");

            leftNameField.setText(leftName);
            rightNameField.setText(rightName);
            roundsCountField.setText(maxRounds);

            NamesManager.getInstance().setNames(leftName, rightName);
            RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRounds));
        }

    }

    private void onOK()
    {
        String leftName = this.leftNameField.getText();
        String rightName = this.rightNameField.getText();
        String maxRound = roundsCountField.getText();

        NamesManager.getInstance().setNames(leftName, rightName);
        RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRound));
        ScoreManagerSingleton.getInstance().newGame();

        savePreferrences(leftName, rightName, maxRound);

        dispose();
    }

    private void savePreferrences(String leftName, String rightName, String maxRound)
    {
        Preferences preferences = BrainPreferences.getPreferences();
        preferences.put(PreferencesNames.LEFT_NAME, leftName);
        preferences.put(PreferencesNames.RIGHT_NAME, rightName);
        preferences.put(PreferencesNames.MAX_SCORE, maxRound);

        try
        {
            preferences.flush();
        }
        catch (BackingStoreException e)
        {
            e.printStackTrace();
        }
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
