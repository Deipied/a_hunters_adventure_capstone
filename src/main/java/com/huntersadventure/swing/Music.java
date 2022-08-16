package com.huntersadventure.swing;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.net.URL;

public class Music {

    //musicFile must be in .wav format
    public static final URL musicLocation = ClassLoader.getSystemResource("GameText/Now-We-Ride.wav");
    static Clip clip;
    static long clipTime;

    public static void playMusic(URL musicLocation){
        try
        {
           URL musicPath = new URL(String.valueOf(Music.musicLocation)) ;
               AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
               clip = AudioSystem.getClip();
               clip.open(audioInput);
               clip.start();
               clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void pauseMusic(){
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public static void resumeMusic(){
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }

    public static void stopMusic(){
        clip.stop();
    }


    public static void main(String[] args) {
        Music music = new Music();
        music.playMusic(musicLocation);

    }
}