package org.i4qwee.chgk.trainer.view.dialogs;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.brain.manager.NamesManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.controller.database.GetQuestionsFromMySqlDatabase;
import org.i4qwee.chgk.trainer.controller.database.GetQuestionsFromSqliteDatabase;
import org.i4qwee.chgk.trainer.new_brain.preferences.BrainPreferences;
import org.i4qwee.chgk.trainer.new_brain.preferences.PreferencesNames;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class SettingsDialog extends AbstractDialog
{
    private static final Logger LOGGER = Logger.getLogger(SettingsDialog.class);

    private static final SettingsDialog instance = new SettingsDialog();

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField leftNameField;
    private JTextField rightNameField;
    private JTextField roundsCountField;
    private JComboBox dataSourceCombo;
    private JTextField hostnameTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JPanel advancedMySQLSettings;
    private JTextField databaseNameTextField;
    private JButton testConnectionButton;
    private JButton aboutButton;

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

        dataSourceCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean advancedSettingsVisible = dataSourceCombo.getSelectedIndex() == 1;

                advancedMySQLSettings.setVisible(advancedSettingsVisible);
                SettingsDialog.this.pack();
            }
        });

        testConnectionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MessageDialog messageDialog = MessageDialog.getInstance();

                try
                {
                    if (getMySQLQuestionsStrategy().getStatement() != null)
                        messageDialog.show("Всё работает!");
                }
                catch (SQLException e1)
                {
                    handleError("Cannot connect to database!", e1);
                }
            }
        });

        ImageIcon aboutIcon = new ImageIcon(getClass().getResource("/img/about.png"));
        aboutButton.setIcon(aboutIcon);

        aboutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new AboutDialog().showDialog();
            }
        });

        loadPreferences();
    }

    private void handleError(String message, Exception e)
    {
        MessageDialog.getInstance().showError(message);
        LOGGER.error(message, e);
    }

    private void selectDataSource()
    {
        switch (dataSourceCombo.getSelectedIndex())
        {
            case 0:
                DatabaseManager.setGetQuestionsFromDatabaseStrategy(new GetQuestionsFromSqliteDatabase());
                break;
            case 1:
                DatabaseManager.setGetQuestionsFromDatabaseStrategy(getMySQLQuestionsStrategy());
                break;
            default:
                throw new NotImplementedException();
        }

    }

    private GetQuestionsFromMySqlDatabase getMySQLQuestionsStrategy()
    {
        return new GetQuestionsFromMySqlDatabase(hostnameTextField.getText(),
                databaseNameTextField.getText(), usernameTextField.getText(), passwordTextField.getText());
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
            dataSourceCombo.setSelectedIndex(Integer.parseInt(preferences.get(PreferencesNames.DATASOURCE, "0")));
            hostnameTextField.setText(preferences.get(PreferencesNames.HOSTNAME, "localhost"));
            databaseNameTextField.setText(preferences.get(PreferencesNames.MYSQL_DATABASE, ""));
            usernameTextField.setText(preferences.get(PreferencesNames.MYSQL_USERNAME, ""));
            passwordTextField.setText(preferences.get(PreferencesNames.MYSQL_PASSWORD, ""));

            selectDataSource();

            NamesManager.getInstance().setNames(leftName, rightName);
            RoundManager.getInstance().setMaxRound(Integer.parseInt(maxRounds));
        }

    }

    private void onOK()
    {
        NamesManager.getInstance().setNames(this.leftNameField.getText(), this.rightNameField.getText());
        RoundManager.getInstance().setMaxRound(Integer.parseInt(roundsCountField.getText()));
        selectDataSource();

        savePreferences();

        dispose();
    }

    private void savePreferences()
    {
        Preferences preferences = BrainPreferences.getPreferences();
        preferences.put(PreferencesNames.LEFT_NAME, this.leftNameField.getText());
        preferences.put(PreferencesNames.RIGHT_NAME, this.rightNameField.getText());
        preferences.put(PreferencesNames.MAX_SCORE, roundsCountField.getText());
        preferences.put(PreferencesNames.DATASOURCE, String.valueOf(dataSourceCombo.getSelectedIndex()));
        preferences.put(PreferencesNames.HOSTNAME, hostnameTextField.getText());
        preferences.put(PreferencesNames.MYSQL_DATABASE, databaseNameTextField.getText());
        preferences.put(PreferencesNames.MYSQL_USERNAME, usernameTextField.getText());
        preferences.put(PreferencesNames.MYSQL_PASSWORD, passwordTextField.getText());

        try
        {
            preferences.flush();
        }
        catch (BackingStoreException e)
        {
            handleError("Cannot save settings", e);
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
