package org.i4qwee.chgk.trainer.controller.brain;

/**
 * User: 4qwee
 * Date: 02.11.11
 * Time: 21:26
 */
public class RoundsManagerSingleton
{
    private static RoundsManagerSingleton ourInstance = new RoundsManagerSingleton();

    public static RoundsManagerSingleton getInstance()
    {
        return ourInstance;
    }

    private RoundsManagerSingleton()
    {

    }


}
