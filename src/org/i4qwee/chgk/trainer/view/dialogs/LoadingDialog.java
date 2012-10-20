package org.i4qwee.chgk.trainer.view.dialogs;

import org.i4qwee.chgk.trainer.model.ApplicationConstants;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 05.11.11
 * Time: 14:57
 */
public class LoadingDialog extends AbstractDialog
{

    public LoadingDialog()
    {
        super();

        setUndecorated(true);
        setModal(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300, 250));
        mainPanel.setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());

        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);

        mainPanel.add(Box.createVerticalGlue());

        ImageIcon loaderImage = new ImageIcon(ApplicationConstants.APPLICATION_PATH + "/img/loader.gif");
        JLabel imageLabel = new JLabel(loaderImage);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);

        mainPanel.add(Box.createVerticalGlue());

        JLabel messageLabel = new JLabel("Подождите. Обращаюсь к базе..");
        messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(messageLabel);
        
        mainPanel.add(Box.createVerticalStrut(10));

        setContentPane(mainPanel);

        setSize(300, 250);
    }
}
