package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.parse.ParseConstants;
import org.i4qwee.chgk.trainer.controller.parse.ParserAllTask;
import org.i4qwee.chgk.trainer.controller.web.Downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: 4qwee
 * Date: 09.09.11
 * Time: 8:17
 */
public class ParserTest
{
    public static void main(String[] args)
    {
//        Parser.parse(Downloader.downloadURL2String("http://db.chgk.info/tree"));
//        String source = "AUTHORS\n" +
//                "fariz96.txt\n" +
//                "ASKEROV\n" +
//                "BBURDA\n" +
//                "druz.txt\n" +
//                "knat06br.33\n" +
//                "belsc02.2";
//        Pattern pattern = Pattern.compile(ParseConstants.TOUR_FOR_LOAD_REGEX);
//        Matcher matcher = pattern.matcher(source);
//
//        while (matcher.find())
//            System.out.println();
//
//        List<String> strings = new ArrayList<String>();
//        strings.add("AUTHORS");
//        strings.add("fariz96.txt");
//        strings.add("knat06br.33");
//        strings.add("belsc02.2");
//
//        for(String s : strings)
//            System.out.println(s.matches(ParseConstants.TOUR_FOR_LOAD_REGEX));

        new ParserAllTask().run();
    }
}
