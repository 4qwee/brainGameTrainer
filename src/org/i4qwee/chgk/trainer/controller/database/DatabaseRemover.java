package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import static org.i4qwee.chgk.trainer.controller.database.DatabaseScripts.*;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 13:02
 */
public class DatabaseRemover implements Runnable
{
    private Statement statement;
    private static Logger logger = Logger.getLogger(DatabaseRemover.class);

    public DatabaseRemover(Statement statement)
    {
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            statement.executeUpdate(REMOVE_QUESTIONS_TABLE);
            statement.executeUpdate(REMOVE_TOURNAMENTS_TABLE);
        }
        catch (SQLException e)
        {
            logger.error("Cannot remove tables!", e);
        }
    }
}
