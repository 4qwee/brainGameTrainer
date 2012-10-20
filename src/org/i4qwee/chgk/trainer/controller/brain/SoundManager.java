package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.brain.listener.GameStateListener;
import org.i4qwee.chgk.trainer.controller.brain.manager.GameStateManager;
import org.i4qwee.chgk.trainer.model.ApplicationConstants;
import org.i4qwee.chgk.trainer.model.enums.GameState;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * User: 4qwee
 * Date: 31.10.11
 * Time: 8:44
 */
public class SoundManager
{
    private static SoundManager ourInstance = new SoundManager();

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
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    private Clip initSound(String source) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File startFile = new File(ApplicationConstants.APPLICATION_PATH + source);
        AudioInputStream startAudioInputStream = AudioSystem.getAudioInputStream(startFile);
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
