package org.i4qwee.chgk.trainer.test;

import com.alee.laf.WebLookAndFeel;
import org.i4qwee.chgk.trainer.view.BrainPanel;

import javax.swing.*;
import java.awt.*;

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

        BrainPanel brainPanel = new BrainPanel(this);
        add(brainPanel);

        pack();
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException
    {
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(WebLookAndFeel.class.getName());

        new BrainPanelTest();
    }
}
