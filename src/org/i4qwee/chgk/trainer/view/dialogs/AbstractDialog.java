package org.i4qwee.chgk.trainer.view.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 02.11.11
 * Time: 23:39
 */
public abstract class AbstractDialog extends JDialog
{
    AbstractDialog()
    {
        super();

        setAlwaysOnTop(true);
        setUndecorated(true);
    }

    public void showDialog()
    {
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

        pack();

        setLocation(centerPoint.x - getWidth() / 2, centerPoint.y - getHeight() / 2);

        setVisible(true);
    }
}
