package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.view.dialogs.LoadingDialog;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 05.11.11
 * Time: 15:14
 */
public class LoadingDialogTest extends JFrame
{
    public LoadingDialogTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new LoadingDialog();

        setSize(100, 100);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new LoadingDialogTest();
    }
}
