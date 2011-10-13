package org.i4qwee.chgk.trainer.test;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.i4qwee.chgk.trainer.controller.parse.CheckQuestionsForImagesTask;
import org.i4qwee.chgk.trainer.controller.parse.DOMParser;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.Tournament;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 10:26
 */
public class DomParserTest
{
    public static void main(String[] args)
    {
        DOMParser parser = new DOMParser("http://db.chgk.info/dbxml.php?tour=mgp1011.1");

//        System.out.println(parser.getMainTournament().toString());
//        System.out.println("=====");
//
//        for (Question question : parser.getQuestions())
//        {
//            System.out.println(question.toString());
//            System.out.println("***");
//        }
//
//        for (Tournament tournament : parser.getTours())
//        {
//            System.out.println(tournament.toString());
//            System.out.println("***");
//        }

        new CheckQuestionsForImagesTask(parser.getQuestions()).run();
    }
}
