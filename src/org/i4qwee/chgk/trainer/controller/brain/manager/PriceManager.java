package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.PriceListener;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 10:39
 */
public class PriceManager extends Manager<PriceListener>
{
    private static final PriceManager instance = new PriceManager();

    private int price = 1;

    private PriceManager()
    {
    }

    public static PriceManager getInstance()
    {
        return instance;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
        notifyListeners();
    }

    @Override
    protected void notifyListeners()
    {
        for (PriceListener listener : listeners)
            listener.onPriceChanged(price);
    }
}
