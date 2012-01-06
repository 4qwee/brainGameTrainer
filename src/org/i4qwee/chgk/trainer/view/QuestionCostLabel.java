package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.PriceListener;
import org.i4qwee.chgk.trainer.controller.brain.PriceManager;

import javax.swing.*;

/**
 * User: 4qwee
 * Date: 01.11.11
 * Time: 9:48
 */
public class QuestionCostLabel extends JLabel implements PriceListener
{
    private static final String INIT_TEXT = "Цена вопроса: ";

    public QuestionCostLabel()
    {
        PriceManager.getInstance().addListener(this);

        setText(INIT_TEXT + PriceManager.getInstance().getPrice());
        setFont(DefaultUIProvider.getQuestionPriceFont());
//        priceLabel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());
    }

    public void onPriceChanged(int price)
    {
        setText(INIT_TEXT + price);
    }
}
