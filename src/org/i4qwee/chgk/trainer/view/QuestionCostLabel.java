package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.ScoreManagerSingleton;
import org.i4qwee.chgk.trainer.model.events.PriceChangedEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 01.11.11
 * Time: 9:48
 */
public class QuestionCostLabel extends JLabel implements Observer
{
    private static final String INIT_TEXT = "Цена вопроса: ";

    public QuestionCostLabel()
    {
        ScoreManagerSingleton.getInstance().addObserver(this);

        setText(INIT_TEXT + ScoreManagerSingleton.getInstance().getPrice());
        setFont(DefaultUIProvider.getQuestionPriceFont());
//        priceLabel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());
    }

    public void update(Observable o, Object arg)
    {
        if (arg != null && arg instanceof PriceChangedEvent)
            setText(INIT_TEXT + ((PriceChangedEvent) arg).getPrice());
    }
}
