package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.time.Timer;
import org.i4qwee.chgk.trainer.view.TimerButtonPanel;

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

        final TimerButtonPanel timerButtonPanel = new TimerButtonPanel();
        getContentPane().add(timerButtonPanel);

        final Timer timer = new org.i4qwee.chgk.trainer.controller.time.Timer();

        JPanel buttonsPanel = new JPanel();
        getContentPane().add(buttonsPanel);

        BoxLayout buttonsBoxLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
        buttonsPanel.setLayout(buttonsBoxLayout);

        Font font = new Font("Courier", Font.PLAIN, 15);

        JButton startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timer.start();
            }
        });
        buttonsPanel.add(startButton);

        createHStrut(buttonsPanel);

        JButton stopButton = new JButton("Stop");
        stopButton.setFont(font);
        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timer.stop();
            }
        });
        buttonsPanel.add(stopButton);

        createHStrut(buttonsPanel);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(font);
        restartButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timer.restart();
            }
        });
        buttonsPanel.add(restartButton);

        setSize(300, 300);
        setVisible(true);
    }

    private void createHStrut(JPanel buttonsPanel)
    {
        buttonsPanel.add(Box.createHorizontalStrut(10));
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        new TimerPanelTest();
    }
}
