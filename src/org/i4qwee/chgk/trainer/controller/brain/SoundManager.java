package org.i4qwee.chgk.trainer.controller.brain;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * User: 4qwee
 * Date: 31.10.11
 * Time: 8:44
 */
public class SoundManager
{
    private static final Logger LOGGER = Logger.getLogger(SoundManager.class);
    private static final SoundManager ourInstance = new SoundManager();

    private Clip startClip;
    private Clip warnClip;
    private Clip overClip;

    public static SoundManager getInstance()
    {
        return ourInstance;
    }

    private SoundManager()
    {
        try
        {
            startClip = initSound("/sounds/start.wav");
            warnClip = initSound("/sounds/warn.wav");
            overClip = initSound("/sounds/over.wav");
        }
        catch (UnsupportedAudioFileException e)
        {
            handleError("Wrong audio file!", e);
        }
        catch (IOException e)
        {
            handleError("Cannot read audio file!", e);
        }
        catch (LineUnavailableException e)
        {
            handleError("Problem with audio file!", e);
        }
    }

    private void handleError(String message, Exception e)
    {
        MessageDialog.getInstance().showError(message);
        LOGGER.error(message, e);
    }

    private Clip initSound(String source) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream startAudioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(source));
        DataLine.Info startInfo = new DataLine.Info(Clip.class, startAudioInputStream.getFormat());
        Clip clip = (Clip) AudioSystem.getLine(startInfo);
        clip.open(startAudioInputStream);

        return clip;
    }

    public void playWarnSound()
    {
        warnClip.start();
    }

    public void playOverSound()
    {
        overClip.start();
    }

    public void playStartSound()
    {
        startClip.start();
    }

    public void resetSounds()
    {
        startClip.stop();
        startClip.setMicrosecondPosition(0);
        warnClip.stop();
        warnClip.setMicrosecondPosition(0);
        overClip.stop();
        overClip.setMicrosecondPosition(0);
    }
}
