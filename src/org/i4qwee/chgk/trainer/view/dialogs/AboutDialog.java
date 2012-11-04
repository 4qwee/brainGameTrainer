package org.i4qwee.chgk.trainer.view.dialogs;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class AboutDialog extends AbstractDialog
{
    private static final Logger LOGGER = Logger.getLogger(AboutDialog.class);

    private JPanel contentPane;
    private JButton buttonOK;
    private JEditorPane aboutTextPane;

    public AboutDialog()
    {
        contentPane.setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        try
        {
            aboutTextPane.setPage(getClass().getResource("/config/about.html"));
        }
        catch (IOException e)
        {
            LOGGER.error("Cannot get about page!", e);
        }

        aboutTextPane.setFont(DefaultUIProvider.getQuestionFont());
        aboutTextPane.addHyperlinkListener(new HyperlinkListener()
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
                    {
                        try
                        {
                            desktop.browse(e.getURL().toURI());
                        }
                        catch (Exception e1)
                        {
                            LOGGER.error("Cannot open URL!", e1);
                        }
                    }
                }
            }
        });

        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onOK();
            }
        });

        setSize(400, 300);
    }

    private void onOK()
    {
        dispose();
    }
}
