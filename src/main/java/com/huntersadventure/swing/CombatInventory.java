package com.huntersadventure.swing;

import com.huntersadventure.gameobjects.Characters;
import com.huntersadventure.gameobjects.Item;

import javax.swing.*;
import java.awt.*;

public class CombatInventory {

    public static void testCombatInventory(GamePage GUI, Characters p1) {

        DefaultListModel<String> panelList = new DefaultListModel<>();

        GUI.inventoryPanel.removeAll();
        JList<String> displayList = new JList<>(panelList);
//        DefaultListCellRenderer renderer = (DefaultListCellRenderer) displayList.getCellRenderer();
//        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (Item item : p1.getInventory()
        ) {
            panelList.addElement(item.getName() + ": "+ item.getDescription());
        }

        displayList.setBounds(100, 50, 400, 800);
        displayList.setBackground(Color.black);
        displayList.setForeground(Color.white);
        displayList.setFont(new Font("Serif", Font.BOLD, 20));
//        GUI.inventoryPanel.setFont(new java.awt.Font("Tacoma", 0, 44));

        JLabel label = new JLabel("Inventory", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 33));
        label.setForeground(Color.white);
        label.setBounds(10, 70, 577, 10);
        GUI.inventoryPanel.add(label);
        GUI.inventoryPanel.add(displayList);

    }

}