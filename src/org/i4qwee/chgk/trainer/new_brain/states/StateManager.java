package org.i4qwee.chgk.trainer.new_brain.states;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/3/12
 * Time: 10:48 PM
 */
public class StateManager
{
    private static final StateManager ourInstance = new StateManager();

    private State state = new NoQuestionsLoaded();

    public static StateManager getInstance()
    {
        return ourInstance;
    }

    private StateManager()
    {
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }
}
