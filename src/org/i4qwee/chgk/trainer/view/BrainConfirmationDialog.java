package org.i4qwee.chgk.trainer.view;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;

import javax.swing.*;
import java.awt.event.*;
import java.lang.management.ThreadInfo;
import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 16:51
 */
public class BrainConfirmationDialog extends JDialog implements Observer
{
    Logger logger = Logger.getLogger(BrainConfirmationDialog.class);
    private JFrame owner;

    private KeyListener keyListener = new KeyAdapter()
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                correct();
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                incorrect();
        }
    };

    public BrainConfirmationDialog(JFrame owner)
    {
        super(owner, ModalityType.MODELESS);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.owner = owner;

        GameStateSingleton.getInstance().addObserver(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());
        add(mainPanel);

        BoxLayout mainBoxLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainBoxLayout);

        JLabel label = new JLabel("Ответ правильный?");

        mainPanel.add(label);
        mainPanel.add(Box.createHorizontalGlue());

        JButton yesButton = new JButton("Да");
        yesButton.addKeyListener(keyListener);
        yesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                correct();
            }
        });

        JButton noButton = new JButton("Нет");
        noButton.addKeyListener(keyListener);
        noButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                incorrect();
            }
        });

        mainPanel.add(yesButton);
        mainPanel.add(noButton);

        setSize(220, 100);
        setResizable(false);
    }

    private void correct()
    {
        ScoreManagerSingleton.getInstance().answer(true);
        setVisible(false);
    }

    private void incorrect()
    {
        ScoreManagerSingleton.getInstance().answer(false);
        setVisible(false);
    }

    public void update(Observable o, Object arg)
    {
        switch (GameStateSingleton.getInstance().getGameState())
        {
            case INIT:
            case WAIT_START_TIMER:
            case RUNNING:
            case FINISHED:
                break;
            case PAUSED:
                setLocation(owner.getX() + (owner.getWidth() - getWidth()) / 2, owner.getY() + (owner.getHeight() - getHeight()) / 2);
                setVisible(true);
                break;
            default:
                logger.error("Unsupported game state: " + GameStateSingleton.getInstance().getGameState());
        }
    }
}
