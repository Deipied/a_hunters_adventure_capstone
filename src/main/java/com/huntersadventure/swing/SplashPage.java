package com.huntersadventure.swing;

import com.huntersadventures.settings.Setting;
import com.huntersadventures.settings.SettingJRadioButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.huntersadventure.swing.Music.musicLocation;


public class SplashPage {

    JFrame titleWindow;
    JPanel menuPanel, titlePanel, startButtonPanel;
    JLabel titleBanner;
    JButton startButton, settingsButton, musicButton;
    Font startFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font settingsFont = new Font("Times New Roman", Font.PLAIN, 15);
    ImageIcon titleBannerSrc, finalImage;
    Image resizeImage, resizedImage;
    Container container;

    GamePage GUI;

    public GamePage getGUI() {
        return GUI;
    }

    public void setGUI(GamePage GUI) {
        this.GUI = GUI;
    }

    public SplashPage() {
        //JFrame window
        titleWindow = new JFrame("A Hunters Adventure");
        titleWindow.setSize(1200, 900);
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.getContentPane().setBackground(Color.black);
        titleWindow.setLayout(null);
        titleWindow.setLocationRelativeTo(null);
        container = titleWindow.getContentPane();

        //resize and set title img
        titleBannerSrc = new ImageIcon(ClassLoader.getSystemResource("GameText/titleBanner.jpg"));
        resizeImage = titleBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(resizedImage);
        titleBanner = new JLabel(finalImage);

        //menu
        JMenuBar menuBar = new JMenuBar();
        titleWindow.setJMenuBar(menuBar);

        JMenu sound = new JMenu("Sound");
        menuBar.add(sound);

        JMenuItem soundOn = new JMenuItem("Play");
        sound.add(soundOn);

        JMenuItem soundPause = new JMenuItem("Pause");
        sound.add(soundPause);

        JMenuItem soundResume = new JMenuItem("Resume");
        sound.add(soundResume);

        JMenuItem soundOff = new JMenuItem("Stop");
        sound.add(soundOff);


        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);

        //title panel - holds game title
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 1000, 350);
        titlePanel.setBackground(Color.black);
        titlePanel.add(titleBanner);

        //startButton panel holds the start button that will be added to the layout panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(450, 600, 250, 150);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFont(startFont);
        startButtonPanel.add(startButton);

        //Settings button
        settingsButton = new JButton("Game settings");
        settingsButton.setBackground(Color.white);
        settingsButton.setForeground(Color.black);
        settingsButton.setBorderPainted(false);
        settingsButton.setFont(settingsFont);
        settingsButton.setBounds(900, 10, 115, 30);

        //add panels to game window
        titleWindow.add(titlePanel);
        titleWindow.add(startButtonPanel);
        titleWindow.setVisible(true);

        //Settings JButton ActionListener
        ActionListener actionListener = e -> {
            Setting setting = new Setting();
            new SettingJRadioButton(setting);
        };
        settingsButton.addActionListener(actionListener);

        settings.add(settingsButton);

        //Start JButton action Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleWindow.setVisible(false);
                getGUI();
            }
        });

        soundOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music music = new Music();
                music.playMusic(musicLocation);
            }
        });

        //actionListener on pause
        soundPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music.pauseMusic();
            }
        });

        //actionListener on resume
        soundResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music.resumeMusic();
            }
        });

        //actionListener on stop
        soundOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Music.stopMusic();
            }
        });
    }

    //for test - remove later
    public static void main(String[] args) {
        new SplashPage();
    }
}



