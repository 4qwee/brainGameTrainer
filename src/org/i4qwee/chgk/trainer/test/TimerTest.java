package org.i4qwee.chgk.trainer.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: 4qwee
 * Date: 25.10.11
 * Time: 21:40
 */
public class TimerTest extends JFrame
{
    public TimerTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(boxLayout);

        final JLabel timeLabel = new JLabel("time");
        getContentPane().add(timeLabel);

        final Integer[] time = {0};

        final Timer timer = new Timer(10, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timeLabel.setText(String.valueOf(time[0] += 10));
            }
        });

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timer.start();
            }
        });
        getContentPane().add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timer.stop();
            }
        });
        getContentPane().add(stopButton);

        setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException
    {
        new TimerTest();
    }
}
