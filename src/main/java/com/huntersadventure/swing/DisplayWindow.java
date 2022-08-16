package com.huntersadventure.swing;

import com.huntersadventure.client.Main;
import com.huntersadventure.game.Combat;
import com.huntersadventure.game.GameController;
import com.huntersadventures.settings.Setting;
import com.huntersadventures.settings.SettingJRadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.huntersadventure.swing.Music.musicLocation;

public class DisplayWindow {
    public JFrame window = new JFrame();
    JPanel titlePanel, startButtonPanel;
    JLabel titleBanner;
    JButton startButton, settingsButton;
    Font startFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font settingsFont = new Font("Times New Roman", Font.PLAIN, 20);
    ImageIcon titleBannerSrc, finalImage;
    Image resizeImage, resizedImage;
    GamePage GUI;

    // gamepage fields
    public JPanel topPanel, inventoryPanel, dialogPanel, mapPanel, textPanel;
    JLabel map;
    ImageIcon mapSrc, mapImg;
    Image resizeMap, resizedMap;
    Container container;
    public JTextArea mainText;
    Font normalFont = (new Font("Times New Roman", Font.PLAIN, 24));
    JTextField test = new JTextField(20);
    public String text = "";
    JScrollPane scrollPane;


    public void loadSplashPage() {
        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        //resize and set title img
        titleBannerSrc = new ImageIcon(ClassLoader.getSystemResource("GameText/titleBanner.jpg"));
        resizeImage = titleBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(resizedImage);
        titleBanner = new JLabel(finalImage);

        //menu
        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

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

        // startButton panel holds the start button that will be added to the layout panel
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
        window.add(titlePanel);
        window.add(startButtonPanel);
        window.setVisible(true);

        ActionListener actionListener = e -> {
            Setting setting = new Setting();
            new SettingJRadioButton(setting);
        };

        settingsButton.addActionListener(actionListener);

        settings.add(settingsButton);

        //JButton action Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getContentPane().removeAll();
                loadGamePage();
                window.revalidate();
                window.repaint();
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

    public GamePage getGUI() {
        return GUI;
    }

    public void loadGamePage() {
        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        container = window.getContentPane();

        //top panel
        topPanel = new JPanel(new GridLayout(0, 3));
        topPanel.setBounds(10, 10, 1175, 55);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        topPanel.setBackground(Color.black);
        container.add(topPanel);

        //inventory panel
        inventoryPanel = new JPanel(new GridLayout(0, 1));
        inventoryPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
//       BoxLayout experimentLayout = new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS);
//       inventoryPanel.setLayout(experimentLayout);
        inventoryPanel.setBounds(10, 70, 577, 350);
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        inventoryPanel.setBackground(Color.black);
        inventoryPanel.setPreferredSize(new Dimension(600, 500));
        scrollPane = new JScrollPane(inventoryPanel);
        scrollPane.setAlignmentY(Component.CENTER_ALIGNMENT);
        scrollPane.setBounds(10, 70, 577, 350);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

        //map image src
        mapSrc = new ImageIcon(ClassLoader.getSystemResource("GameText/map.png"));
        resizeMap = mapSrc.getImage();
        resizedMap = resizeMap.getScaledInstance(550, 330, Image.SCALE_SMOOTH);
        mapImg = new ImageIcon(resizedMap);
        map = new JLabel(mapImg);

        //map
        mapPanel = new JPanel();
        mapPanel.setBounds(590, 70, 595, 350);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        mapPanel.setBackground(Color.black);
        mapPanel.add(map);
        container.add(mapPanel);

        //dialog
        dialogPanel = new JPanel();
        dialogPanel.setBounds(10, 425, 1175, 350);
        dialogPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        dialogPanel.setBackground(Color.black);
        container.add(dialogPanel);

        mainText = new JTextArea("Welcome to the Hunter's Adventure!\n" +
                "Would you like to hear the back story? (y/n)");
        mainText.setBounds(20, 500, 1000, 250);
        mainText.setBackground(Color.black);
        mainText.setForeground(Color.white);
        mainText.setFont(normalFont);
        mainText.setLineWrap(true);
        mainText.setWrapStyleWord(true);
        mainText.setEditable(false);

        dialogPanel.add(mainText);

        //text panel
        textPanel = new JPanel();
        textPanel.setBounds(10, 780, 1175, 65);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        textPanel.setBackground(Color.black);
        window.setVisible(true);
        container.add(textPanel);

        test.setBackground(Color.darkGray);
        test.setFont(normalFont);
        test.setBorder(map.getBorder());
        test.setForeground(Color.white);

        JLabel label = new JLabel("TYPE HERE ---->");
        label.setForeground(Color.white);
        label.setFont(normalFont);
        label.setLabelFor(test);

        textPanel.add(label);
        textPanel.add(test);

        // action listener and threading for player input
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = test.getText();
                test.setText("");

                synchronized (GameController.class) {
                    GameController.class.notifyAll();
                }

                synchronized (Combat.class) {
                    Combat.class.notifyAll();
                }
            }
        });
    }

    JPanel youWinPanel, startAgainPanel, quitPanel;
    JLabel youWinBanner;
    JButton startAgainButton, quitButton;
    Font font = new Font("Times New Roman", Font.PLAIN, 50);
    ImageIcon youWinBannerSrc, finalYouWinBannerSrc;
//    Image resizeImage, resizedImage;
//    Container container;

    public void loadYouWin() {
        window.getContentPane().removeAll();

        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        container = window.getContentPane();

        //ToDo: panel holds you win image
        youWinPanel = new JPanel();
        youWinPanel.setBounds(100, 100, 1000, 350);
        youWinPanel.setBackground(Color.black);
        container.add(youWinPanel);

        //ToDo: resize image
        youWinBannerSrc = new ImageIcon(ClassLoader.getSystemResource("GameText/youWin.jpg"));
        resizeImage = youWinBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(900, 400, Image.SCALE_SMOOTH);
        finalYouWinBannerSrc = new ImageIcon(resizedImage);

        //ToDo: Start again panel and button
        startAgainPanel = new JPanel();
        startAgainPanel.setBounds(400, 500, 400, 100);
        startAgainPanel.setBackground(Color.black);

        startAgainButton = new JButton("START AGAIN");
        startAgainButton.setBackground(Color.black);
        startAgainButton.setForeground(Color.white);
        startAgainButton.setBorderPainted(false);
        startAgainButton.setFont(font);

        //ToDo: Quit panel and button
        quitPanel = new JPanel();
        quitPanel.setBounds(400, 600, 400, 150);
        quitPanel.setBackground(Color.black);

        quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.white);

        quitButton.setBorderPainted(false);
        quitButton.setFont(font);

        //ToDo: Add all panels and buttons to GameOverWindow
        youWinBanner = new JLabel(finalYouWinBannerSrc);
        youWinPanel.add(youWinBanner);
        window.add(youWinPanel);
        startAgainPanel.add(startAgainButton);
        window.add(startAgainPanel);
        quitPanel.add(quitButton);
        window.add(quitPanel);
        window.setVisible(true);

        //JButton action Listener on start again button // calls game page
//        startAgainButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                window.setVisible(false);
//                window.dispose();
//                try {
//                    window.revalidate();
//                    GameController gameController = new GameController();
//                    gameController.run();
//                    window.setVisible(true);
//                    window.getContentPane().setBackground(Color.black);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });

        //JButton action Listener on quit button // calls splash page
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                window.dispose();
            }
        });

        window.revalidate();
        window.repaint();
    }

    //You died GUI
    JPanel gameOverPanel;
    JLabel gameOverBanner;
    Font diedFont = new Font("Times New Roman", Font.PLAIN, 50);
    ImageIcon gameOverBannerSrc, finalGameOverBannerSrc;

    public void loadYouDied() {
        window.getContentPane().removeAll();
        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        container = window.getContentPane();

        //ToDo: panel holds you died image
        gameOverPanel = new JPanel();
        gameOverPanel.setBounds(100, 100, 1000, 350);
        gameOverPanel.setBackground(Color.black);
        container.add(gameOverPanel);

        //ToDo: resize image
        gameOverBannerSrc = new ImageIcon(ClassLoader.getSystemResource("GameText/youDied.png"));
        resizeImage = gameOverBannerSrc.getImage();
        resizedImage = resizeImage.getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        finalGameOverBannerSrc = new ImageIcon(resizedImage);

        //ToDo: Start again panel and button
        startAgainPanel = new JPanel();
        startAgainPanel.setBounds(400, 500, 400, 100);
        startAgainPanel.setBackground(Color.black);

        startAgainButton = new JButton("START AGAIN");
        startAgainButton.setBackground(Color.black);
        startAgainButton.setForeground(Color.white);

        startAgainButton.setBorderPainted(false);
        startAgainButton.setFont(diedFont);

        //ToDo: Quit panel and button
        quitPanel = new JPanel();
        quitPanel.setBounds(400, 600, 400, 150);
        quitPanel.setBackground(Color.black);

        quitButton = new JButton("QUIT");
        quitButton.setBackground(Color.black);
        quitButton.setForeground(Color.white);

        quitButton.setBorderPainted(false);
        quitButton.setFont(diedFont);

        //ToDo: Add all panels and buttons to GameOverWindow
        gameOverBanner = new JLabel(finalGameOverBannerSrc);
        gameOverPanel.add(gameOverBanner);
        window.add(gameOverPanel);
        startAgainPanel.add(startAgainButton);
        window.add(startAgainPanel);
        quitPanel.add(quitButton);
        window.add(quitPanel);
        window.setVisible(true);

        //JButton action Listener on start again button // resets game and goes to splash page
//        startAgainButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                window.setVisible(false);
//                window.dispose();
//                try {
//                    window.revalidate();
//                    GameController gameController = new GameController();
//                    gameController.run();
//                    window.setVisible(true);
//                    window.getContentPane().setBackground(Color.black);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            }
//        });

        //JButton action Listener on quit button // exits the game
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                window.dispose();
            }
        });
        window.revalidate();
        window.repaint();
    }

    public static void main(String[] args) {
        DisplayWindow window = new DisplayWindow();
        window.loadSplashPage();
    }
}
