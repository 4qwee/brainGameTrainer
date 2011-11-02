package org.i4qwee.chgk.trainer.model.events;

/**
 * User: 4qwee
 * Date: 02.11.11
 * Time: 21:30
 */
public class NamesChangedEvent
{
    private String leftName;
    private String rightName;

    public NamesChangedEvent(String leftName, String rightName)
    {
        this.leftName = leftName;
        this.rightName = rightName;
    }

    public String getLeftName()
    {
        return leftName;
    }

    public String getRightName()
    {
        return rightName;
    }
}
