package org.i4qwee.chgk.trainer.controller.brain.manager;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/23/12
 * Time: 9:12 AM
 */
public class MainWindow
{
    private static JFrame mainWindow;

    public static JFrame getMainWindow()
    {
        return mainWindow;
    }

    public static void setMainWindow(JFrame mainWindow)
    {
        MainWindow.mainWindow = mainWindow;
    }
}
