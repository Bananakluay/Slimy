package sound;

import java.io.*;
import javax.sound.sampled.*;

public enum Sound {
    JUMP("TrashGame/res/sound/effect/jump.wav"), // jump sound
    DEAD("TrashGame/res/sound/effect/hitHurt.wav"), // dead sound
    EXPLOSION("TrashGame/res/sound/effect/explosion.wav"), // explosion sound
    BLIP_ON("TrashGame/res/sound/effect/blipOn.wav"),
    BLIP_OFF("TrashGame/res/sound/effect/blipOff.wav");

    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    Sound(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            File file = new File(soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play(Boolean loop) {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop(); // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning

            clip.start(); // Start playing
            if (loop)// Loop if loop parameter is true
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }

    public void stop() // stop playing and rewind to be played again from the beginning
    {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void mute() // don't play sounds(Mute Sound is selected from Options menu)
    {
        volume = Volume.MUTE;
    }
}