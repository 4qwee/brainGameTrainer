package org.i4qwee.chgk.trainer.controller.brain;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.brain.manager.AnswerSideManager;
import org.i4qwee.chgk.trainer.controller.brain.manager.GameStateManager;
import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.model.enums.GameState;

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

    private final AnswerSideManager answerSideManager = AnswerSideManager.getInstance();
    private final GameStateManager gameStateManager = GameStateManager.getInstance();

    public BrainMouseListener()
    {
    }

    public void mousePressed(MouseEvent event)
    {
        switch (gameStateManager.getGameState())
        {
            case INIT:
                break;
            case WAIT_START_TIMER:

                setAnswerSide(event);

                String name = answerSideManager.getAnswersName();

                if (name != null && !name.equals(""))
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), name + ", фальстарт!", "", JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Фальстарт!", "", JOptionPane.PLAIN_MESSAGE);

                ScoreManagerSingleton.getInstance().setFalseStart();
                break;

            case RUNNING:

                setAnswerSide(event);

                gameStateManager.setGameState(GameState.PAUSED);

                break;
            case PAUSED:
                break;
            case FINISHED:
                break;
            default:
                logger.error("Unsupported game state: " + gameStateManager.getGameState());
        }
    }

    private void setAnswerSide(MouseEvent event)
    {
        if (event.getButton() == MouseEvent.BUTTON1)
            answerSideManager.setAnswerSide(AnswerSide.LEFT);
        else if (event.getButton() == MouseEvent.BUTTON3)
            answerSideManager.setAnswerSide(AnswerSide.RIGHT);
    }
}
