package org.i4qwee.chgk.trainer.test;

/**
 * User: 4qwee
 * Date: 15.10.11
 * Time: 16:19
 */
public class ExceptionTest
{
    public static void main(String[] args)
    {
        try
        {
            doError();
        }
        catch (ArithmeticException e)
        {
            System.out.println("error!");
        }
    }

    private static void doError()
    {
        int i = 3 / 0;
    }
}
