package org.i4qwee.chgk.trainer.new_brain.actionlisteners;

import org.i4qwee.chgk.trainer.new_brain.states.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/3/12
 * Time: 11:04 PM
 */
public class KeyboardDispatcher implements KeyEventDispatcher
{
    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent)
    {
        if (keyEvent.getID() != KeyEvent.KEY_PRESSED)
            return false;

        if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
            StateManager.getInstance().getState().doAction();
        else if (keyEvent.getKeyCode() == KeyEvent.VK_N && (keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)
            StateManager.getInstance().getState().doNewGame();

        return false;
    }
}
