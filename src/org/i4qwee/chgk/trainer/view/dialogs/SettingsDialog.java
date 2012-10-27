package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.controller.database.GetQuestionsFromMySqlDatabase;
import org.i4qwee.chgk.trainer.new_brain.preferences.BrainPreferences;
import org.i4qwee.chgk.trainer.new_brain.preferences.PreferencesNames;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SettingsDialog extends AbstractDialog
{
    private static final SettingsDialog instance = new SettingsDialog();

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftNameField;
    private JTextField rightNameField;
    private JTextField roundsCountField;
    private JComboBox dataSourceCombo;

    private SettingsDialog()
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

    private void selectDataSource(int selectedIndex)
    {
        switch (selectedIndex)
        {
            case 0:
                DatabaseManager.setGetQuestionsFromDatabaseStrategy(new GetQuestionsFromMySqlDatabase());
                break;
            case 1:
                DatabaseManager.setGetQuestionsFromDatabaseStrategy(new GetQuestionsFromMySqlDatabase());
                break;
            default:
                throw new NotImplementedException();
        }

    }

    private void loadPreferences()
    {
        Preferences preferences = BrainPreferences.getPreferences();
        if (preferences != null)
        {
            String leftName = preferences.get(PreferencesNames.LEFT_NAME, "");
            String rightName = preferences.get(PreferencesNames.RIGHT_NAME, "");
            String maxRounds = preferences.get(PreferencesNames.MAX_SCORE, "0");
            int selectedDatasource = Integer.parseInt(preferences.get(PreferencesNames.DATASOURCE, "0"));

            leftNameField.setText(leftName);
            rightNameField.setText(rightName);
            roundsCountField.setText(maxRounds);
            dataSourceCombo.setSelectedIndex(selectedDatasource);
            selectDataSource(selectedDatasource);

            NamesManager.getInstance().setNames(leftName, rightName);
            RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRounds));
        }

    }

    private void onOK()
    {
        String leftName = this.leftNameField.getText();
        String rightName = this.rightNameField.getText();
        String maxRound = roundsCountField.getText();
        int selectedDatasource = dataSourceCombo.getSelectedIndex();

        NamesManager.getInstance().setNames(leftName, rightName);
        RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRound));
        selectDataSource(selectedDatasource);

        savePreferences(leftName, rightName, maxRound, selectedDatasource);

        dispose();
    }

    private void savePreferences(String leftName, String rightName, String maxRound, int selectedDatasource)
    {
        Preferences preferences = BrainPreferences.getPreferences();
        preferences.put(PreferencesNames.LEFT_NAME, leftName);
        preferences.put(PreferencesNames.RIGHT_NAME, rightName);
        preferences.put(PreferencesNames.MAX_SCORE, maxRound);
        preferences.put(PreferencesNames.DATASOURCE, maxRound);
        preferences.put(PreferencesNames.DATASOURCE, String.valueOf(selectedDatasource));

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

    public static SettingsDialog getInstance()
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
