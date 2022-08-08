package com.huntersadventures.settings;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingJRadioButton extends JFrame {

    public SettingJRadioButton(Setting setting) {

        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton easy = new JRadioButton(String.valueOf(SettingType.EASY));
        JRadioButton hard = new JRadioButton(String.valueOf(SettingType.HARD));

        ButtonGroup nameGroup = new ButtonGroup();
        JRadioButton vader = new JRadioButton(String.valueOf(SettingType.VADER));
        JRadioButton potter = new JRadioButton(String.valueOf(SettingType.POTTER));

        ActionListener actionListener = new ActionListener() {
            String currentDiffValue = "";

            @Override
            public void actionPerformed(ActionEvent e) {
//                TODO: add a guard clause
//                if (!e.getActionCommand().equals(SettingType.EASY.toString()) && !e.getActionCommand().equals(SettingType.HARD.toString())) {
//                    return;
//                }
                if (e.getActionCommand().equals(SettingType.EASY.toString()) || e.getActionCommand().equals(SettingType.HARD.toString())) {
                    currentDiffValue = e.getActionCommand().toLowerCase();
                    setting.setDifficulty(currentDiffValue);
                }
                if (e.getActionCommand().equals(SettingType.VADER.toString()) || e.getActionCommand().equals(SettingType.POTTER.toString())) {
                    currentDiffValue = e.getActionCommand().toLowerCase();
                    setting.setName(e.getActionCommand().toLowerCase());
                }
            }
        };

        JLabel difLabel = new JLabel(String.valueOf(SettingType.DIFFICULTY));
        JLabel charLabel = new JLabel(String.valueOf(SettingType.CHARACTER));

        //preset radioButtons
        easy.setSelected(true);
        vader.setSelected(true);

        nameGroup.add(vader);
        nameGroup.add(potter);
        panel.add(charLabel);
        panel.add(vader);
        panel.add(potter);

        difficultyGroup.add(easy);
        difficultyGroup.add(hard);
        panel.add(difLabel);
        panel.add(easy);
        panel.add(hard);

        //close button
        JButton finishButton = new JButton("Finished");
        finishButton.setSize(0, 0);
        finishButton.addActionListener(e -> {
            frame.dispose();
        });
        panel.add(finishButton);

        hard.addActionListener(actionListener);
        vader.addActionListener(actionListener);
        potter.addActionListener(actionListener);
        easy.addActionListener(actionListener);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}