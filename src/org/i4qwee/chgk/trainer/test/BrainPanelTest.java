package org.i4qwee.chgk.trainer.test;

import com.alee.laf.WebLookAndFeel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.i4qwee.chgk.trainer.controller.brain.manager.MainWindow;
import org.i4qwee.chgk.trainer.new_brain.actionlisteners.KeyboardDispatcher;
import org.i4qwee.chgk.trainer.view.BrainPanel;
import org.i4qwee.chgk.trainer.view.dialogs.SettingsDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:15
 */
public class BrainPanelTest extends JFrame
{
    private static final Logger LOGGER = Logger.getLogger(BrainPanelTest.class);

    public BrainPanelTest() throws HeadlessException
    {
        super();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        setLayeredPane(layeredPane);

        final BrainPanel brainPanel = new BrainPanel();
        layeredPane.add(brainPanel, JLayeredPane.DEFAULT_LAYER);

        layeredPane.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Component component = e.getComponent();
                brainPanel.setSize(component.getSize());
                brainPanel.validate();
            }
        });

        pack();

        setVisible(true);
        setSize(800, 600);//unity hack

        setFullScreen();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyboardDispatcher());

        Toolkit.getDefaultToolkit().addAWTEventListener(new org.i4qwee.chgk.trainer.new_brain.actionlisteners.MouseListener(), AWTEvent.MOUSE_EVENT_MASK);

        PropertyConfigurator.configure(getClass().getResource("/config/log4j.properties"));

        try
        {
            setIconImage(ImageIO.read(getClass().getResource("/img/icon.png")));
        }
        catch (IOException e)
        {
            LOGGER.error("Cannot get icon!", e);
        }

        setTitle("Буйный лосось");
    }

    private void setFullScreen()
    {
        setExtendedState(Frame.MAXIMIZED_BOTH);

        removeNotify();
        setUndecorated(true);
        addNotify();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException
    {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(WebLookAndFeel.class.getName());

        BrainPanelTest brainPanelTest = new BrainPanelTest();
        MainWindow.setMainWindow(brainPanelTest);
        SettingsDialog.getInstance();
    }
}
