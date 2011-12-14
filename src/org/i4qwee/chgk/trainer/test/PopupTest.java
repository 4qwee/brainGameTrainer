package org.i4qwee.chgk.trainer.test;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 14.12.11
 * Time: 22:37
 */
public class PopupTest extends JFrame
{
    public PopupTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new GridLayout(1, 1));
        setContentPane(layeredPane);

        JButton button = new JButton("some button");
        layeredPane.add(button, JLayeredPane.POPUP_LAYER);

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new PopupTest();
    }
}
