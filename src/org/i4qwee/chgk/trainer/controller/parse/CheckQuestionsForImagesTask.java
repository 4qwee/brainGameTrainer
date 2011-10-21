package org.i4qwee.chgk.trainer.controller.parse;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.web.Downloader;
import org.i4qwee.chgk.trainer.model.Question;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: 4qwee
 * Date: 04.10.11
 * Time: 14:38
 */
public class CheckQuestionsForImagesTask implements Runnable
{
    private Logger logger = Logger.getLogger(CheckQuestionsForImagesTask.class);

    private List<Question> questions;
    private Pattern pattern = Pattern.compile(ParseConstants.IMG_REGEX);

    public CheckQuestionsForImagesTask(List<Question> questions)
    {


        this.questions = questions;
    }

    public void run()
    {
        for (Question question : questions)
        {
            try
            {
                checkStringForImage(question.getQuestion());
                checkStringForImage(question.getAnswer());
            }
            catch (IOException e)
            {
                logger.warn("Image not found! " + e);
            }
        }
    }

    private void checkStringForImage(String source) throws IOException
    {
        Matcher matcher = pattern.matcher(source);

        while (matcher.find())
        {
            String imageName = source.substring(matcher.start(1), matcher.end(1));
            Downloader.downloadImage(ParseConstants.IMG_PATH + imageName, imageName);
        }
    }
}
