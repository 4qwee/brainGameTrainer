package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManager;

import javax.swing.*;
import java.awt.event.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 16:51
 */
public class BrainConfirmationDialog extends JDialog
{
    private boolean isLeft;
    private ScoreManager scoreManager;

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

    public BrainConfirmationDialog(JFrame owner, boolean isLeft, ScoreManager scoreManager)
    {
        super(owner, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.isLeft = isLeft;
        this.scoreManager = scoreManager;

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
        setLocation(owner.getX() + (owner.getWidth() - getWidth()) / 2, owner.getY() + (owner.getHeight() - getHeight()) / 2);
        setVisible(true);
    }

    private void correct()
    {
        scoreManager.answer(true, isLeft);
        dispose();
    }

    private void incorrect()
    {
        scoreManager.answer(false, isLeft);
        dispose();
    }
}
