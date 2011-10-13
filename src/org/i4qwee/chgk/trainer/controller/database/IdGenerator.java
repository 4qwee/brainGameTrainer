package org.i4qwee.chgk.trainer.controller.database;

/**
 * User: 4qwee
 * Date: 05.10.11
 * Time: 0:45
 */
public class IdGenerator
{
    private static int currentTournamentId = 0;
    private static int currentQuestionId = 0;

    public static int getCurrentTournamentId()
    {
        return currentTournamentId;
    }

    public static int getCurrentQuestionId()
    {
        return currentQuestionId;
    }

    public static int getNextTournamentId()
    {
        return ++currentTournamentId;
    }

    public static int getNextQuestionId()
    {
        return ++currentQuestionId;
    }
}
