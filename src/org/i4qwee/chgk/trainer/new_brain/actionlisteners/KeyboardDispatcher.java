package org.i4qwee.chgk.trainer.new_brain.actionlisteners;

import org.i4qwee.chgk.trainer.new_brain.states.State;
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

        int keyCode = keyEvent.getKeyCode();
        State state = StateManager.getInstance().getState();

        if (keyCode == KeyEvent.VK_SPACE)
            state.doAction();
        else if (keyCode == KeyEvent.VK_ESCAPE)
            state.doCancel();
        else if (keyCode == KeyEvent.VK_N && (keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)
            state.doNewGame();

        return false;
    }
}
