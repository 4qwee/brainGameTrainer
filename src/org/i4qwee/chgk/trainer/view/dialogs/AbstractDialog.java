package org.i4qwee.chgk.trainer.view.dialogs;

import javax.swing.*;

/**
 * User: 4qwee
 * Date: 02.11.11
 * Time: 23:39
 */
public abstract class AbstractDialog extends JDialog
{
    private JFrame owner;

    public AbstractDialog(JFrame owner)
    {
        super(owner, ModalityType.MODELESS);
        this.owner = owner;
    }

    public void showDialog()
    {
        pack();
        setLocation(owner.getX() + (owner.getWidth() - getWidth()) / 2, owner.getY() + (owner.getHeight() - getHeight()) / 2);
        setVisible(true);
    }
}
