package org.i4qwee.chgk.trainer.controller.brain.manager;

import org.i4qwee.chgk.trainer.controller.brain.listener.TimeListener;

/**
 * User: 4qwee
 * Date: 09.01.12
 * Time: 0:53
 */
public class TimeManager extends Manager<TimeListener>
{
    private static final TimeManager instance = new TimeManager();

    private int time = 0;

    private TimeManager()
    {
    }

    public static TimeManager getInstance()
    {
        return instance;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
        notifyListeners();
    }

    private void notifyListeners()
    {
        for (TimeListener listener : listeners)
            listener.onTimeChanged(time);
    }

    public static String getTimeString(int time)
    {
        long tmpTime = time % 10L;
        String timeText = Long.toString(tmpTime);
        tmpTime = (time % 100L - time % 10L) / 10L;
        timeText = Long.toString(tmpTime) + timeText;
        tmpTime = (time % 1000L - time % 100L) / 100L;
        timeText = ":" + Long.toString(tmpTime) + timeText;
        tmpTime = (time % 10000L - time % 1000L) / 1000L;
        timeText = Long.toString(tmpTime) + timeText;
        tmpTime = (time % 60000L - time % 10000L) / 10000L;
        timeText = ":" + Long.toString(tmpTime) + timeText;
        tmpTime = (time % 600000L - time % 60000L) / 60000L;
        timeText = Long.toString(tmpTime) + timeText;

        return timeText;
    }
}
