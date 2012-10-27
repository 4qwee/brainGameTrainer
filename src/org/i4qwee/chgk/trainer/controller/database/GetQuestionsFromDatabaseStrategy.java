package org.i4qwee.chgk.trainer.controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/27/12
 * Time: 8:55 PM
 */
public interface GetQuestionsFromDatabaseStrategy
{
    PreparedStatement getStatement() throws SQLException;
}
