package org.i4qwee.chgk.trainer.controller.brain;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.model.GameState;
import org.i4qwee.chgk.trainer.model.GameStateSingleton;
import org.i4qwee.chgk.trainer.view.BrainConfirmationDialog;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 12:26
 */
public class BrainMouseListener extends MouseAdapter
{
    private static Logger logger = Logger.getLogger(BrainMouseListener.class);

    private JFrame parentFrame;
    private ScoreManager scoreManager;

    public BrainMouseListener(JFrame parentFrame, ScoreManager scoreManager)
    {
        this.parentFrame = parentFrame;
        this.scoreManager = scoreManager;
    }

    public void mousePressed(MouseEvent event)
    {
        switch (GameStateSingleton.getInstance().getGameState())
        {
            case INIT:
                break;
            case WAIT_START_TIMER:
                if (event.getButton() == MouseEvent.BUTTON1)
                {
                    JOptionPane.showMessageDialog(parentFrame, "Фальстарт слева!", "", JOptionPane.PLAIN_MESSAGE);
                    scoreManager.answer(false, true);
                }
                else if (event.getButton() == MouseEvent.BUTTON3)
                {
                    JOptionPane.showMessageDialog(parentFrame, "Фальстарт справа!", "", JOptionPane.PLAIN_MESSAGE);
                    scoreManager.answer(false, false);
                }

                break;
            case RUNNING:
                GameStateSingleton.getInstance().setGameState(GameState.PAUSED);

                if (event.getButton() == MouseEvent.BUTTON1)
                    new BrainConfirmationDialog(parentFrame, true, scoreManager);
                else if (event.getButton() == MouseEvent.BUTTON3)
                    new BrainConfirmationDialog(parentFrame, false, scoreManager);

                break;
            case PAUSED:
                break;
            case FINISHED:
                break;
            default:
                logger.error("Unsupported game state: " + GameStateSingleton.getInstance().getGameState());
        }
    }
}
