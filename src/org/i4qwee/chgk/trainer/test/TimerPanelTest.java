package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.view.TimerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 23:10
 */
public class TimerPanelTest extends JFrame
{
    public TimerPanelTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(boxLayout);

        final TimerPanel timerPanel = new TimerPanel();
        getContentPane().add(timerPanel);

        JPanel buttonsPanel = new JPanel();
        getContentPane().add(buttonsPanel);

        BoxLayout buttonsBoxLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
        buttonsPanel.setLayout(buttonsBoxLayout);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timerPanel.timerStart();
            }
        });
        buttonsPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timerPanel.timerStop();
            }
        });
        buttonsPanel.add(stopButton);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timerPanel.timerRestart();
            }
        });
        buttonsPanel.add(restartButton);

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        new TimerPanelTest();
    }
}
