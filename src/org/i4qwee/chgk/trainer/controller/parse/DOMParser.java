package org.i4qwee.chgk.trainer.controller.parse;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.web.Downloader;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Tournament;
import org.i4qwee.chgk.trainer.model.enums.Type;
import org.i4qwee.chgk.trainer.model.UnknownTypeError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static org.i4qwee.chgk.trainer.controller.parse.ParseConstants.*;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 10:30
 */
public class DOMParser
{
    private Document document;
    private Element rootElement;

    private static Logger logger = Logger.getLogger(DOMParser.class);

    public DOMParser(String source) throws SAXException
    {
        try
        {
            rootElement = DocumentBuilderFactoryImpl.newInstance()
                    .newDocumentBuilder()
                    .parse(Downloader.downloadURL2Stream(source))
                    .getDocumentElement();
        }
        catch (IOException e)
        {
            logger.error(null, e);
        }
        catch (ParserConfigurationException e)
        {
            logger.error(null, e);
        }
    }

    private Tournament getTournament(Element element) throws AssertionError
    {
        String title = getTextValue(element, TITLE_TAG);
        short questionsNumber = getShortValue(element, QUESTIONS_NUMBER_TAG);
        String copyright = getTextValue(element, COPYRIGHT_TAG);
        String info = getTextValue(element, INFO_TAG);
        String url = getTextValue(element, URL_TAG);
        String filename = getTextValue(element, FILENAME_TAG);

        if (filename == null)
            throw new AssertionError("tour " + title + " is missing");

        if (filename.matches(FILENAME_REGEX))
            filename = filename.substring(0, filename.length() - 4);

        String editors = getTextValue(element, EDITORS_TAG);
        Date playedAt = getDateValue(element, PLAYED_AT_TAG);

        return new Tournament(title, questionsNumber, copyright, info, url, filename, editors, playedAt);
    }

    public Tournament getMainTournament()
    {
        return getTournament(rootElement);
    }

    public List<Tournament> getToursForLoad()
    {
        NodeList nodeList = rootElement.getElementsByTagName(TOUR_TAG);
        List<Tournament> tournaments = new LinkedList<Tournament>();

        if (nodeList != null && nodeList.getLength() > 0)
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Tournament tournament = getTournament((Element) nodeList.item(i));

                tournaments.add(tournament);
            }

        return tournaments;
    }

    public List<Question> getQuestions()
    {
        NodeList nodeList = rootElement.getElementsByTagName(QUESTION_TAG);
        List<Question> questions = new LinkedList<Question>();

        try
        {
            if (nodeList != null && nodeList.getLength() > 0)
                for (int i = 0; i < nodeList.getLength(); i++)
                    questions.add(getQuestion((Element) nodeList.item(i)));
        }
        catch (UnknownTypeError unknownTypeError)
        {
            unknownTypeError.printStackTrace();
        }

        return questions;
    }

    private Question getQuestion(Element element) throws UnknownTypeError
    {
        short number = getShortValue(element, NUMBER_TAG);
        Type type = getTypeValue(element, TYPE_TAG);
        String question = getTextValue(element, QUESTION_BODY_TAG);
        String answer = getTextValue(element, ANSWER_TAG);
        String passCriteria = getTextValue(element, PASS_CRITERIA_TAG);
        String authors = getTextValue(element, AUTHORS_TAG);
        String sources = getTextValue(element, SOURCES_TAG);
        String comments = getTextValue(element, COMMENTS_TAG);

        return new Question(number, type, question, answer, passCriteria, authors, sources, comments);
    }

    private String getTextValue(Element element, String tagName)
    {
        String textValue = null;

        NodeList nodeList = element.getElementsByTagName(tagName);

        if (nodeList != null && nodeList.getLength() > 0
                && nodeList.item(0) != null && nodeList.item(0).getFirstChild() != null)
            textValue = nodeList.item(0).getFirstChild().getNodeValue();

        return textValue;
    }

    private Integer getIntValue(Element element, String tagName)
    {
        return Integer.parseInt(getTextValue(element, tagName));
    }

    private Short getShortValue(Element element, String tagName)
    {
        try
        {
            return Short.parseShort(getTextValue(element, tagName));
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    private Date getDateValue(Element element, String tagName)
    {
        String textValue = getTextValue(element, tagName);

        return textValue == null ? null : Date.valueOf(textValue);
    }

    private Type getTypeValue(Element element, String tagName) throws UnknownTypeError
    {
        String textValue = getTextValue(element, tagName);

        return textValue == null ? null : Type.parseType(textValue.charAt(0));
    }
}
