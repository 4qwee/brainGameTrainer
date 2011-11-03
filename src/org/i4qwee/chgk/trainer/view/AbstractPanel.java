package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 03.11.11
 * Time: 22:13
 */
public abstract class AbstractPanel extends JPanel
{
    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);

        for (Component component : getComponents())
        {
            component.addMouseListener(mouseListener);
        }
    }
}
