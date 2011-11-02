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
public class QuestionCostPanel extends JPanel implements Observer
{
    private static final String INIT_TEXT = "Цена вопроса: ";
    private JLabel priceLabel;

    public QuestionCostPanel()
    {
        ScoreManagerSingleton.getInstance().addObserver(this);

        priceLabel = new JLabel(INIT_TEXT + ScoreManagerSingleton.getInstance().getPrice());
        priceLabel.setFont(DefaultUIProvider.getQuestionPriceFont());
//        priceLabel.setBorder(DefaultUIProvider.getDefaultEmptyBorder());

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(priceLabel);
    }

    public void update(Observable o, Object arg)
    {
        if (arg != null && arg instanceof PriceChangedEvent)
            priceLabel.setText(INIT_TEXT + ((PriceChangedEvent) arg).getPrice());
    }
}
