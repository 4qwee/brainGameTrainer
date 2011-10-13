package org.i4qwee.chgk.trainer.controller.database;

import static org.i4qwee.chgk.trainer.controller.database.DatabaseConstants.*;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 0:43
 */
public class DatabaseScripts
{
    public static final String CREATE_QUESTIONS_TABLE = "create table " + QUESTIONS_TABLE + "(\n" +
            QUESTION_ID_COLUMN + " integer not null, \n" +
            QUESTION_PARENT_ID_COLUMN + " integer not null, \n" +
            QUESTION_NUMBER_COLUMN + " smallint not null, \n" +
            QUESTION_TYPE_COLUMN + " smallint not null, \n" +
            QUESTION_QUESTION_COLUMN + " varchar(4000) not null, \n" +
            QUESTION_ANSWER_COLUMN + " varchar(4000) not null, \n" +
            QUESTION_PASS_CRITERIA_COLUMN + " varchar(500), \n" +
            QUESTION_AUTHORS_COLUMN + " varchar(500), \n" +
            QUESTION_SOURCES_COLUMN + " varchar(500), \n" +
            QUESTION_COMMENTS_COLUMN + " varchar(2000), \n" +
            "constraint " + QUESTIONS_PRIMARY_KEY_CONSTRAINT + " primary key (" + QUESTION_ID_COLUMN + "), \n" +
            "constraint " + QUESTIONS_FOREIGN_KEY_CONSTRAINT + " foreign key (" + QUESTION_PARENT_ID_COLUMN + ") references " + TOURNAMENTS_TABLE + "(" + TOURNAMENT_ID_COLUMN + ") on delete cascade on update cascade \n" +
            ")";

    public static final String CREATE_QUESTIONS_PARENT_ID_INDEX = "create index " + QUESTIONS_PARENT_ID_INDEX + " on " + QUESTIONS_TABLE + "(" + QUESTION_PARENT_ID_COLUMN + ");";
    public static final String CREATE_QUESTIONS_TYPE_INDEX = "create index " + QUESTIONS_TYPE_INDEX + " on " + QUESTIONS_TABLE + "(" + QUESTION_TYPE_COLUMN + ");";

    public static final String REMOVE_QUESTIONS_TABLE = "drop table " + QUESTIONS_TABLE;

    public static final String CREATE_TOURNAMENTS_TABLE = "create table " + TOURNAMENTS_TABLE + "(\n" +
            TOURNAMENT_ID_COLUMN + " integer not null, \n" +
            TOURNAMENT_PARENT_ID_COLUMN + " integer not null, \n" +
            TOURNAMENT_TITLE_COLUMN + " varchar(200) not null, \n" +
            TOURNAMENT_QUESTIONS_NUMBER_COLUMN + " smallint not null, \n" +
            TOURNAMENT_COPYRIGHT_COLUMN + " varchar(200), \n" +
            TOURNAMENT_INFO_COLUMN + " varchar(500), \n" +
            TOURNAMENT_URL_COLUMN + " varchar(200), \n" +
            TOURNAMENT_EDITORS_COLUMN + " varchar(200), \n" +
            TOURNAMENT_PLAYED_AT_COLUMN + " timestamp, " +
            "constraint " + TOURNAMENTS_PRIMARY_KEY_CONSTRAINT + " primary key(" + TOURNAMENT_ID_COLUMN + ") \n" +
            ")";

    public static final String CREATE_TOURNAMENTS_PARENT_ID_INDEX = "create index " + TOURNAMENTS_PARENT_ID_INDEX + " on " + TOURNAMENTS_TABLE + "(" + TOURNAMENT_PARENT_ID_COLUMN + ");";
    public static final String CREATE_TOURNAMENTS_TITLE_INDEX = "create index " + TOURNAMENTS_TITLE_INDEX + " on " + TOURNAMENTS_TABLE + "(" + TOURNAMENT_TITLE_COLUMN + ");";
    public static final String CREATE_TOURNAMENTS_PLAYED_AT_INDEX = "create index " + TOURNAMENTS_PLAYED_AT_INDEX + " on " + TOURNAMENTS_TABLE + "(" + TOURNAMENT_PLAYED_AT_COLUMN + ");";


    public static final String REMOVE_TOURNAMENTS_TABLE = "drop table " + TOURNAMENTS_TABLE;

    public static final String INSERT_QUESTION = "insert into " + QUESTIONS_TABLE + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TOURNAMENT = "insert into " + TOURNAMENTS_TABLE + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
