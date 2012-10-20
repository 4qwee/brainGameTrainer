package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.listener.RoundListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.RoundManager;

import javax.swing.*;

/**
 * User: 4qwee
 * Date: 06.11.11
 * Time: 10:57
 */
public class RoundsLabel extends JLabel implements RoundListener
{
    private static final String INIT_TEXT = "Раунд ";
    private static final String ADDITIONAL_OF_TEXT = " из ";

    public RoundsLabel()
    {
        RoundManager.getInstance().addListener(this);

        setText(INIT_TEXT + 1);
        setFont(DefaultUIProvider.getQuestionPriceFont());
    }

    public void onRoundChanged(int round, int maxRound)
    {
        String text = INIT_TEXT + round;

        if (maxRound != 0)
            text += ADDITIONAL_OF_TEXT + maxRound;

        setText(text);
    }
}
