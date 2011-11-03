package org.i4qwee.chgk.trainer.view.dialogs;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.view.DefaultUIProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 16:51
 */
public class BrainConfirmationDialog extends AbstractDialog implements Observer
{
    private Logger logger = Logger.getLogger(BrainConfirmationDialog.class);

    @SuppressWarnings({"FieldCanBeLocal"})
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
    private JLabel label;

    public BrainConfirmationDialog(JFrame owner)
    {
        super(owner);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        GameStateSingleton.getInstance().addObserver(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        label = new JLabel();

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

        setContentPane(mainPanel);

        setSize(300, 100);
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
            case PAUSED:

                String name = ScoreManagerSingleton.getInstance().getAnswersName();

                if (name != null && !name.equals(""))
                    label.setText(ScoreManagerSingleton.getInstance().getAnswersName() + ", правильно?");
                else
                    label.setText("Правильно?");

                showDialog();
                break;
            default:
                logger.error("Unsupported game state: " + GameStateSingleton.getInstance().getGameState());
        }
    }
}
