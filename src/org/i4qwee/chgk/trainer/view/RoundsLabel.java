package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.questions.GameStateSingleton;
import org.i4qwee.chgk.trainer.model.events.RoundChangedEvent;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 06.11.11
 * Time: 10:57
 */
public class RoundsLabel extends JLabel implements Observer
{
    public static final String INIT_TEXT = "Раунд ";
    public static final String ADDITIONAL_OF_TEXT = " из ";

    public RoundsLabel()
    {
        GameStateSingleton.getInstance().addObserver(this);

        setText(INIT_TEXT + 1);
        setFont(DefaultUIProvider.getQuestionPriceFont());
    }

    public void update(Observable o, Object arg)
    {
        if (arg != null && arg instanceof RoundChangedEvent)
        {
            String text = INIT_TEXT + ((RoundChangedEvent) arg).getRound();

            if (((RoundChangedEvent) arg).getMaxRoundsCount() != 0)
                text += ADDITIONAL_OF_TEXT + ((RoundChangedEvent) arg).getMaxRoundsCount();

            setText(text);
        }
    }
}
