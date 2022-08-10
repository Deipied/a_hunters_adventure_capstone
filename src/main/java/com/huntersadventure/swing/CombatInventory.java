package com.huntersadventure.swing;

import com.huntersadventure.gameobjects.Characters;
import com.huntersadventure.gameobjects.Item;

import javax.swing.*;
import java.awt.*;

public class CombatInventory {

    public static void testCombatInventory(GamePage GUI, Characters p1) {

        DefaultListModel<String> panelList = new DefaultListModel<>();

        GUI.middleLeftPanel.removeAll();
        JList<String> displayList = new JList<>(panelList);
        for (Item item : p1.getInventory()
        ) {
            panelList.addElement(item.getName());
        }

        displayList.setBounds(100, 100, 200, 200);

        GUI.middleLeftPanel.setFont(new java.awt.Font("Tacoma", 0, 44));
        GUI.middleLeftPanel.add(displayList);

    }

}