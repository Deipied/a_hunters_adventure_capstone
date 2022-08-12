package com.huntersadventure.swing;
import com.huntersadventure.gameobjects.Characters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoDisplay {
    JPanel infoPanel = new JPanel();
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JLabel locationLabel, locationName, hpLabel, hpLabelNum, shieldLabel, shieldLabelNum;

    public void infoDisplay(GamePage GUI, Characters p1) {
        locationLabel = new JLabel();
        locationLabel.setFont(normalFont);
        locationLabel.setForeground(Color.white);
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationLabel.setText("Location: " + p1.getLocation().getName());

        hpLabel = new JLabel();
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        hpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        hpLabel.setText("HP: " + String.valueOf(p1.getHealth()));

        shieldLabel = new JLabel();
        shieldLabel.setFont(normalFont);
        shieldLabel.setForeground(Color.white);
        shieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shieldLabel.setText("Shield: " + String.valueOf(p1.getShield()));

        GUI.topPanel.add(locationLabel);
        GUI.topPanel.add(hpLabel);
        GUI.topPanel.add(shieldLabel);
    }

    public void refresh(Characters p1) {
        locationLabel.setText("Location: " + p1.getLocation().getName());
        hpLabel.setText("HP: " + String.valueOf(p1.getHealth()));
        shieldLabel.setText("Shield: " + String.valueOf(p1.getShield()));
    }

}