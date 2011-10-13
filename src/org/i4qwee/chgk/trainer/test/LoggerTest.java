package org.i4qwee.chgk.trainer.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.i4qwee.chgk.trainer.model.ApplicationConstants;

/**
 * User: 4qwee
 * Date: 07.10.11
 * Time: 8:39
 */
public class LoggerTest
{
    private static Logger logger = Logger.getLogger(LoggerTest.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure(System.getProperty("user.dir") + ApplicationConstants.IDEA_FILEPATH_HACK + "log4j.prop");

        logger.warn("Hello world!");
    }
}
