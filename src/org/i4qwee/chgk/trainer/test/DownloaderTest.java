package org.i4qwee.chgk.trainer.test;

import org.i4qwee.chgk.trainer.controller.web.Downloader;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 23:41
 */
public class DownloaderTest
{
    public static void main(String[] args)
    {
        Downloader.downloadImage("http://chgk.zaba.ru/images/db/20110042.jpg", "20110042.jpg");
    }
}
