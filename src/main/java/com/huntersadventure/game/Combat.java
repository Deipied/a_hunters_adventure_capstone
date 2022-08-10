package com.huntersadventure.game;

import com.huntersadventure.gameobjects.Characters;
import com.huntersadventure.gameobjects.Item;
import com.huntersadventure.swing.GamePage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Combat {
    boolean combatEnd = false;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public Characters enemyEncounter(Characters enemy, Characters p1, GamePage GUI) {
        Characters loser = null;
        String enemyName = enemy.getName();
        String input;
        String output;

        try {
            TimeUnit.MILLISECONDS.sleep(750);
            GUI.mainText.setText("You have entered combat with " + enemyName);
            GUI.mainText.append("\n" + enemy.getDescription());
            while (p1.getHealth() > 0 && enemy.getHealth() > 0) {
                TimeUnit.MILLISECONDS.sleep(750);
                GUI.mainText.append("\nPlayer health & shield: " + p1.getHealth() +
                        ", " + p1.getShield());
                GUI.mainText.append("\nEnemy health & shield: " + enemy.getHealth() +
                        ", " + enemy.getShield());
                GUI.mainText.append("\nWhat would you like to use?");
                GUI.mainText.append("\n>");
//                input = in.readLine();
                synchronized (Combat.class) {
                    Combat.class.wait();
                }
                input = GUI.text;
                output = inputParse(input);
                if (itemInInventory(input, p1)) {
                    TimeUnit.MILLISECONDS.sleep(750);
                    Item itemUsed = getItemFromName(input, p1);
                    playerMoveResolution(itemUsed, enemy, p1, GUI);
                    TimeUnit.MILLISECONDS.sleep(750);
                } else if (input.equals("punch")) {
                    TimeUnit.MILLISECONDS.sleep(750);
                    playerPunchResolution(p1, enemy, GUI);
                    TimeUnit.MILLISECONDS.sleep(750);
                } else {
                    TimeUnit.MILLISECONDS.sleep(750);
                    System.out.println("You can't use that");
                    System.out.println("type in a weapon, or punch");
                    TimeUnit.MILLISECONDS.sleep(750);
                }
            }
            GUI.mainText.setText("COMBAT END");
            if (p1.getHealth() <= 0) {
                GUI.mainText.append("\nResult: YOU HAVE DIED\n");
                loser = p1;
            } else {
                GUI.mainText.append("\nResult: ENEMY HAS DIED\n");
                loser = enemy;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return loser;
    }

    private void playerPunchResolution(Characters p1, Characters enemy, GamePage GUI) throws InterruptedException {
        int enemyAttackDmg = enemy.getDamage();

        GUI.mainText.setText("You punch dealing 5 dmg");
        if (enemy.getShield() > 0) {
            enemy.setShield(enemy.getShield() - 5);
        } else {
            enemy.setHealth(enemy.getHealth() - 5);
        }
        TimeUnit.MILLISECONDS.sleep(750);
//        GUI.mainText.append("\nEnemy health & shield: " + enemy.getHealth() + ", " + enemy.getShield());
//        TimeUnit.MILLISECONDS.sleep(750);

        if (enemy.getHealth() > 0) {
            GUI.mainText.append("\n" + enemy.getName() + " attacks you dealing " + enemyAttackDmg + " damage");
            if (p1.getShield() > 0) {
                p1.setShield(p1.getShield() - enemyAttackDmg);
            } else {
                p1.setHealth(p1.getHealth() - enemyAttackDmg);
            }
        } else {
            GUI.mainText.append("\n" + enemy.getDefeat());
        }
        TimeUnit.MILLISECONDS.sleep(750);
    }

    public void playerMoveResolution(Item item, Characters enemy, Characters p1, GamePage GUI) throws InterruptedException {
        int playerAttackDmg = item.getValue();
        int enemyAttackDmg = enemy.getDamage();

        int newEnemyHealth = enemy.getHealth() - playerAttackDmg;
        int newPlayerHealth = p1.getHealth() - enemyAttackDmg;
        int newEnemyShield = enemy.getShield() - playerAttackDmg;
        int newPlayerShield = p1.getShield() - enemyAttackDmg;

        GUI.mainText.setText("You use your " + item.getName() + " and deal " + playerAttackDmg + " damage");
        if (enemy.getShield() > 0) {
            enemy.setShield(newEnemyShield);
        } else {
            enemy.setHealth(newEnemyHealth);
        }
//        GUI.mainText.append("\nEnemy health & shield: " + enemy.getHealth() + ", " + enemy.getShield());
        TimeUnit.MILLISECONDS.sleep(750);

        if (enemy.getHealth() > 0) {
            GUI.mainText.append("\n" +enemy.getName() + " attacks you dealing " + enemyAttackDmg + " damage");
            if (p1.getShield() > 0) {
                p1.setShield(newPlayerShield);
            } else {
                p1.setHealth(newPlayerHealth);
            }
        } else {
            GUI.mainText.append("\n" +enemy.getDefeat());
        }
        TimeUnit.MILLISECONDS.sleep(750);
    }

    public boolean itemInInventory(String itemName, Characters p1) {
        boolean res = false;
        for (Item targetItem : p1.getInventory()) {
            if (targetItem.getName().equals(itemName)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public Item getItemFromName(String itemName, Characters p1) {
        Item res = null;
        for (Item targetItem : p1.getInventory()) {
            if (targetItem.getName().equals(itemName)) {
                res = targetItem;
            }
        }
        return res;
    }

    public String inputParse(String input) {
        return input.trim().toLowerCase();
    }

}