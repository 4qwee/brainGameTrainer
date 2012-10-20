package org.i4qwee.chgk.trainer.new_brain.actionlisteners;

import org.i4qwee.chgk.trainer.model.enums.AnswerSide;
import org.i4qwee.chgk.trainer.new_brain.states.State;
import org.i4qwee.chgk.trainer.new_brain.states.StateManager;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/16/12
 * Time: 10:36 PM
 */
public class MouseListener implements AWTEventListener
{
    @Override
    public void eventDispatched(AWTEvent awtEvent)
    {
        if (awtEvent.getID() != MouseEvent.MOUSE_PRESSED)
            return;

        MouseEvent mouseEvent = (MouseEvent) awtEvent;
        int button = mouseEvent.getButton();
        State state = StateManager.getInstance().getState();

        if (button == MouseEvent.BUTTON1)
            state.doAnswer(AnswerSide.LEFT);
        else if (button == MouseEvent.BUTTON3)
            state.doAnswer(AnswerSide.RIGHT);
    }
}
