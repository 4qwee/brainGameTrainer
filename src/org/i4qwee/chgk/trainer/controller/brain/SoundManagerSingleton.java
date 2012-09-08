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
public class SoundManagerSingleton implements GameStateListener
{
    private static SoundManagerSingleton ourInstance = new SoundManagerSingleton();

    private Clip startClip;
    private Clip warnClip;
    private Clip overClip;

    @SuppressWarnings({"FieldCanBeLocal"})
    private boolean needStartSound = true;

    public static SoundManagerSingleton getInstance()
    {
        return ourInstance;
    }

    private SoundManagerSingleton()
    {
        GameStateManager.getInstance().addListener(this);

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

    public void onGameStageChanged(GameState gameState)
    {
        switch (gameState)
        {
            case WAIT_START_TIMER:
                needStartSound = true;
                startClip.setMicrosecondPosition(0);
                warnClip.setMicrosecondPosition(0);
                overClip.setMicrosecondPosition(0);
                break;
            case RUNNING:
                startClip.start();
                needStartSound = false;
                break;
        }
    }
}
