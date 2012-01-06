package org.i4qwee.chgk.trainer.controller.brain.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 11:20
 */
public abstract class Manager<T>
{
    List<T> listeners = new ArrayList<T>();

    public void addListener(T listener)
    {
        listeners.add(listener);
    }

    protected abstract void notifyListeners();
}
