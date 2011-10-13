package org.i4qwee.chgk.trainer.controller.database;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 0:31
 */
public class DatabaseConstants
{
    public static final String QUESTIONS_TABLE = "QUESTIONS";
    public static final String TOURNAMENTS_TABLE = "TOURNAMENTS";

    public static final String QUESTION_ID_COLUMN = "ID";
    public static final String QUESTION_PARENT_ID_COLUMN = "PARENT_ID";
    public static final String QUESTION_NUMBER_COLUMN = "NUMBER";
    public static final String QUESTION_TYPE_COLUMN = "TYPE";
    public static final String QUESTION_QUESTION_COLUMN = "QUESTION";
    public static final String QUESTION_ANSWER_COLUMN = "ANSWER";
    public static final String QUESTION_PASS_CRITERIA_COLUMN = "PASS_CRITERIA";
    public static final String QUESTION_AUTHORS_COLUMN = "AUTHORS";
    public static final String QUESTION_SOURCES_COLUMN = "SOURCES";
    public static final String QUESTION_COMMENTS_COLUMN = "COMMENTS";
    public static final String QUESTIONS_PRIMARY_KEY_CONSTRAINT = "PK_QUESTIONS";
    public static final String QUESTIONS_FOREIGN_KEY_CONSTRAINT = "FK_QUESTIONS_TOURNAMENTS";
    public static final String QUESTIONS_PARENT_ID_INDEX = "IDX_QUESTIONS_PARENT_ID";
    public static final String QUESTIONS_TYPE_INDEX = "IDX_QUESTIONS_TYPE";

    public static final String TOURNAMENT_ID_COLUMN = "ID";
    public static final String TOURNAMENT_PARENT_ID_COLUMN = "PARENT_ID";
    public static final String TOURNAMENT_TITLE_COLUMN = "TITLE";
    public static final String TOURNAMENT_QUESTIONS_NUMBER_COLUMN = "QUESTIONS_NUMBER";
    public static final String TOURNAMENT_COPYRIGHT_COLUMN = "COPYRIGHT";
    public static final String TOURNAMENT_INFO_COLUMN = "INFO";
    public static final String TOURNAMENT_URL_COLUMN = "URL";
    public static final String TOURNAMENT_EDITORS_COLUMN = "EDITORS";
    public static final String TOURNAMENT_PLAYED_AT_COLUMN = "PLAYED_AT";
    public static final String TOURNAMENTS_PRIMARY_KEY_CONSTRAINT = "PK_TOURNAMENTS";
    public static final String TOURNAMENTS_PARENT_ID_INDEX = "IDX_TOURNAMENTS_PARENT_ID";
    public static final String TOURNAMENTS_TITLE_INDEX = "IDX_TOURNAMENTS_TITLE";
    public static final String TOURNAMENTS_PLAYED_AT_INDEX = "IDX_TOURNAMENTS_PLAYED_AT";
}
