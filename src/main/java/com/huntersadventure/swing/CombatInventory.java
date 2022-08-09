package com.huntersadventure.swing;

import com.huntersadventure.gameobjects.Characters;
import com.huntersadventure.gameobjects.Item;

import javax.swing.*;
import java.awt.*;

public class CombatInventory {
    static JFrame frame;

    public static void testCombatInventory(Characters p1) {
        frame = new JFrame("Current inventory");
        DefaultListModel<String> l1 = new DefaultListModel<>();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (Item item : p1.getInventory()
        ) {
            l1.addElement(item.getName());
        }

        JList<String> list = new JList<>(l1);
        JScrollPane scrollPane = new JScrollPane(list);

        list.setBounds(100, 100, 200, 200);


        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(200, 200);
        frame.setVisible(true);
    }

}