package org.i4qwee.chgk.trainer.model.events;

/**
 * User: 4qwee
 * Date: 01.11.11
 * Time: 10:26
 */
public class PriceChangedEvent
{
    private int price;

    public PriceChangedEvent(int price)
    {
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }
}
