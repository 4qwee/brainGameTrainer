package org.i4qwee.chgk.trainer.controller.brain;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 10:39
 */
public class PriceManager
{
    private static final PriceManager instance = new PriceManager();

    private int price = 1;

    private List<PriceListener> listeners = new ArrayList<PriceListener>();

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

    private void notifyListeners()
    {
        for (PriceListener listener : listeners)
            listener.onPriceChanged(price);
    }

    public void addListener(PriceListener listener)
    {
        listeners.add(listener);
    }
}
