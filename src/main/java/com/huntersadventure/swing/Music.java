package com.huntersadventure.swing;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

public class Music {

    //musicFile must be in .wav format
    public static final String musicLocation = "src/main/resources/GameText/Now-We-Ride.wav";
    static Clip clip;
    static long clipTime;

    public static void playMusic(String musicLocation){
        try
        {
           File musicPath = new File(Music.musicLocation) ;
           if (musicPath.exists()){
               AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
               clip = AudioSystem.getClip();
               clip.open(audioInput);
               clip.start();
               clip.loop(Clip.LOOP_CONTINUOUSLY);
           }
           else{
               System.out.println("Can't find music file");
           }
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