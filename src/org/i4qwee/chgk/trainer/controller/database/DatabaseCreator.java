package org.i4qwee.chgk.trainer.controller.database;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 12:18
 */
public class DatabaseCreator implements Runnable
{
    private Statement statement;
    private Logger logger = Logger.getLogger(DatabaseCreator.class);

    public DatabaseCreator(Statement statement)
    {
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            createTournamentsTable();
            createQuestionsTable();
        }
        catch (SQLException e)
        {
            logger.error("Cannot create tables!", e);
        }
    }

    private void createQuestionsTable() throws SQLException
    {
        statement.executeUpdate(DatabaseScripts.CREATE_QUESTIONS_TABLE);
        statement.executeUpdate(DatabaseScripts.CREATE_QUESTIONS_TYPE_INDEX);
        statement.executeUpdate(DatabaseScripts.CREATE_QUESTIONS_PARENT_ID_INDEX);
    }

    private void createTournamentsTable() throws SQLException
    {
        statement.executeUpdate(DatabaseScripts.CREATE_TOURNAMENTS_TABLE);
        statement.executeUpdate(DatabaseScripts.CREATE_TOURNAMENTS_PARENT_ID_INDEX);
        statement.executeUpdate(DatabaseScripts.CREATE_TOURNAMENTS_TITLE_INDEX);
        statement.executeUpdate(DatabaseScripts.CREATE_TOURNAMENTS_PLAYED_AT_INDEX);
    }
}
