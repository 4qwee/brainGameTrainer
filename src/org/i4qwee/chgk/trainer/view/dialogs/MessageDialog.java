package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 08.01.12
 * Time: 22:58
 */
public class MessageDialog extends AbstractDialog
{
    private static final MessageDialog instance = new MessageDialog();

    private JLabel messageLabel = null;

    private MessageDialog()
    {
        super();
        
        setModal(true);
        
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        
        BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        
        contentPane.setLayout(boxLayout);
        
        contentPane.setBorder(DefaultUIProvider.getDefaultEmptyEtchedEmptyBorder());

        messageLabel = new JLabel("");
        messageLabel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());
        messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        contentPane.add(messageLabel);
        
        contentPane.add(Box.createVerticalStrut(30));
        
        JButton okButton = new JButton("Ага");
        okButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                MessageDialog.this.dispose();
            }
        });
        contentPane.add(okButton);
    }

    public static MessageDialog getInstance()
    {
        return instance;
    }

    public void show(String message)
    {
        messageLabel.setIcon(null);
        showBasicDialogs(message);
    }

    private void showBasicDialogs(String message)
    {
        messageLabel.setText(message);

        instance.showDialog();
    }

    public void showError(String message)
    {
        messageLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        showBasicDialogs(message);
    }
}
