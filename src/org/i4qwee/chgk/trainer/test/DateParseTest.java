package org.i4qwee.chgk.trainer.test;

import java.sql.Date;

/**
 * User: 4qwee
 * Date: 10.09.11
 * Time: 14:21
 */
public class DateParseTest
{
    public static void main(String[] args)
    {
        String source = "2001-02-24";

        Date date = Date.valueOf(source);

        System.out.println(date.toString());
    }
}
