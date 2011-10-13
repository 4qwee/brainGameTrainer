package org.i4qwee.chgk.trainer.controller.parse;

/**
 * User: 4qwee
 * Date: 07.09.11
 * Time: 19:49
 */
public class ParseConstants
{
    public static final String TOURNAMENT_LINK_REGEX = "<a href=\"/tour/(.*?)\">(.*?)</a>";
    public static final String FILENAME_REGEX = "(.*?)\\.[A-Za-z]+";
    public static final String IMG_REGEX = "\\(pic:\\s(.+)\\)";

    public static final String ALL_QUESTIONS_START = "^<ul>";
    public static final String ALL_QUESTIONS_END = "</ul>$";
    public static final String TOURNAMENT_XML_PATH = "http://db.chgk.info/dbxml.php?tour=";
    public static final String IMG_PATH = "http://chgk.zaba.ru/images/db/";

    public static final String TOURNAMENT_TAG = "tournament";
    public static final String TOUR_TAG = "tour";
    public static final String QUESTION_TAG = "question";

    public static final String TOURNAMENT_ID_TAG = "Id";
    public static final String PARENT_ID_TAG = "ParentId";
    public static final String TITLE_TAG = "Title";
    public static final String QUESTIONS_NUMBER_TAG = "QuestionsNum";
    public static final String COPYRIGHT_TAG = "Copyright";
    public static final String INFO_TAG = "Info";
    public static final String URL_TAG = "URL";
    public static final String FILENAME_TAG = "FileName";
    public static final String EDITORS_TAG = "Editors";
    public static final String PLAYED_AT_TAG = "PlayedAt";

    public static final String QUESTION_ID_TAG = "QuestionId";
    public static final String NUMBER_TAG = "Number";
    public static final String TYPE_TAG = "Type";
    public static final String QUESTION_BODY_TAG = "Question";
    public static final String ANSWER_TAG = "Answer";
    public static final String PASS_CRITERIA_TAG = "PassCriteria";
    public static final String AUTHORS_TAG = "Authors";
    public static final String SOURCES_TAG = "Sources";
    public static final String COMMENTS_TAG = "Comments";
}
