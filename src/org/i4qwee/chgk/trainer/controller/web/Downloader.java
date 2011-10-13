package org.i4qwee.chgk.trainer.controller.web;

import org.i4qwee.chgk.trainer.model.ApplicationConstants;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 23:31
 */
public class Downloader
{
    public static String downloadURL2String(String urlS)
    {
        try
        {
            URL url = new URL(urlS);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String result = "";
            String buffer;

            while ((buffer = reader.readLine()) != null)
                result += buffer + "\n";

            return result;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public static InputStream downloadURL2Stream(String urlS)
    {
        try
        {
            URL url = new URL(urlS);

            return url.openStream();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void downloadImage(String urlS, String filename) throws IOException
    {
        URL url = new URL(urlS);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") +
                ApplicationConstants.IDEA_FILEPATH_HACK + "/imgdata/" + filename);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, 1 << 24);
    }
}
