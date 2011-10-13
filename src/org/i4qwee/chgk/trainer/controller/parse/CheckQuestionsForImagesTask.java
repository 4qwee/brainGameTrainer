package org.i4qwee.chgk.trainer.controller.parse;

import org.i4qwee.chgk.trainer.controller.web.Downloader;
import org.i4qwee.chgk.trainer.model.Question;

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
            checkStringForImage(question.getQuestion());
            checkStringForImage(question.getAnswer());
        }
    }

    private void checkStringForImage(String source)
    {
        Matcher matcher = pattern.matcher(source);

        if (matcher.find())
        {
            String imageName = source.substring(matcher.start(1), matcher.end(1));
            Downloader.downloadImage(ParseConstants.IMG_PATH + imageName, imageName);
        }
    }
}
