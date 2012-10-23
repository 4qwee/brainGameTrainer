package org.i4qwee.chgk.trainer.test;

import com.alee.laf.WebLookAndFeel;
import org.i4qwee.chgk.trainer.new_brain.actionlisteners.*;
import org.i4qwee.chgk.trainer.view.BrainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:15
 */
public class BrainPanelTest extends JFrame
{
    public BrainPanelTest() throws HeadlessException
    {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        new BrainPanelTest();
    }
}
