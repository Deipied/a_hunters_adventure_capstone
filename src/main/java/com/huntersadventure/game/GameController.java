package com.huntersadventure.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.huntersadventure.gameobjects.*;
import com.huntersadventure.jsonparser.Json;

import com.huntersadventure.swing.*;
import com.huntersadventures.settings.Setting;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Character Controller not yet in use. Below contains just an example.
 */

public class GameController {
    //    public static final String ANSI_RESET = "\u001B[0m";  //resets text color back to default value.
//    public static final String cyan = "\033[1;36m";
//    public static final String yellow = "\033[1;33m";
//    public static final String red = "\033[1;31m";
    boolean gameEnd = true;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Characters p1 = new Characters();
    Characters miniboss1 = new Characters();
    Characters miniboss2 = new Characters();
    Characters finalboss = new Characters();
    Combat combat = new Combat();
    List<Location> townMap = new ArrayList<>();
    List<Item> gameItems = new ArrayList<>();
    ArrayList<String> playerInventory = new ArrayList<>();

    List<String> commands = new ArrayList<>(Arrays.asList(
            "look", "help", "quit"));

    List<String> preparatoryCommands = new ArrayList<>(Arrays.asList(
            "get", "go", "use", "talk", "attack"));

    List<String> direction = new ArrayList<>(Arrays.asList(
            "north", "south", "west", "east"));


    // Items in the room or from NPCs
    List<Location> items = new ArrayList<>();

    // GUI related instantiation
    DisplayWindow GUI = new DisplayWindow();
    InfoDisplay topDisplay = new InfoDisplay();
//    Setting setting = new Setting();

//    public void checkSetting(Setting setting) {
//        if (Setting.getDifficulty().equalsIgnoreCase("hard")) {
//            System.out.println("DIFFICULTY HARD");
//            p1.setHealth(50);
//            miniboss1.setHealth(5000);
//        } else if (setting.getDifficulty().equalsIgnoreCase("easy")) {
//            System.out.println("DIFFICULTY EASY");
//        } else {
//            System.out.println("bad");
//        }
//    }

    public GameController() throws IOException {
    }

    public void run() throws IOException, InterruptedException {
        GUI.loadSplashPage();
        generateItems();
        generateMap();
        createPlayer(townMap);
//        checkSetting(setting);
        startPrompt();
        setGameEnd(false);
        topDisplay.infoDisplay(GUI, p1);
        startGame();
    }

    public void generateItems() {

        try {
            Json json = new Json();
            JsonNode badgeNode = json.parse(json.getResourceStream("/items/badge.json"));
            JsonNode silverNode = json.parse(json.getResourceStream("/items/arrows.json"));
            JsonNode boxNode = json.parse(json.getResourceStream("/items/locker.json"));
            JsonNode potionNode = json.parse(json.getResourceStream("/items/potion.json"));
            JsonNode mapNode = json.parse(json.getResourceStream("/items/map.json"));
            JsonNode bowNode = json.parse(json.getResourceStream("/items/bow.json"));
            JsonNode keyNode = json.parse(json.getResourceStream("/items/key.json"));
            JsonNode swordNode = json.parse(json.getResourceStream("/items/sword.json"));
            JsonNode shieldNode = json.parse(json.getResourceStream("/items/shield.json"));
            JsonNode necklaceNode = json.parse(json.getResourceStream("/items/necklace.json"));

            Item badge = json.fromJson(badgeNode, Item.class);
            Item arrows = json.fromJson(silverNode, Item.class);
            Item locker = json.fromJson(boxNode, Item.class);
            Item potion = json.fromJson(potionNode, Item.class);
            Item map = json.fromJson(mapNode, Item.class);
            Item bow = json.fromJson(bowNode, Item.class);
            Item key = json.fromJson(keyNode, Item.class);
            Item sword = json.fromJson(swordNode, Item.class);
            Item shield = json.fromJson(shieldNode, Item.class);
            Item necklace = json.fromJson(necklaceNode, Item.class);

            // Index : 0
            gameItems.add(badge);
            // Index : 1
            gameItems.add(arrows);
            // Index : 2
            gameItems.add(locker);
            // Index : 3
            gameItems.add(potion);
            // Index : 4
            gameItems.add(map);
            // Index : 5
            gameItems.add(bow);
            // Index : 6
            gameItems.add(key);
            // Index : 7
            gameItems.add(sword);
            // Index : 8
            gameItems.add(shield);
            // Index : 9
            gameItems.add(necklace);

            // Assign shield to a variable.
            Item shieldItem = gameItems.get(8);
            Item necklaceItem = gameItems.get(9);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateMap() {
        try {
            Json json = new Json();

            JsonNode gtNode = json.parse(json.getResourceStream("/locations/guardtower.json"));
            JsonNode tgNode = json.parse(json.getResourceStream("/locations/towngate.json"));
            JsonNode bsNode = json.parse(json.getResourceStream("/locations/blacksmith.json"));
            JsonNode ahNode = json.parse(json.getResourceStream("/locations/abandonedhouse.json"));
            JsonNode innNode = json.parse(json.getResourceStream("/locations/inn.json"));
            JsonNode farmNode = json.parse(json.getResourceStream("/locations/farmland.json"));
            JsonNode forestNode = json.parse(json.getResourceStream("/locations/forest.json"));
            JsonNode checkpointNode = json.parse(json.getResourceStream("/locations/checkpoint.json"));
            JsonNode dungeon1Node = json.parse(json.getResourceStream("/locations/dungeon1.json"));
            JsonNode dungeon2Node = json.parse(json.getResourceStream("/locations/dungeon2.json"));

            Location inn = json.fromJson(innNode, Location.class);
            Location blackSmith = json.fromJson(bsNode, Location.class);
            Location guardTower = json.fromJson(gtNode, Location.class);
            Location abandonedHouse = json.fromJson(ahNode, Location.class);
            Location townGate = json.fromJson(tgNode, Location.class);
            Location farmLand = json.fromJson(farmNode, Location.class);
            Location forest = json.fromJson(forestNode, Location.class);
            Location checkpoint = json.fromJson(checkpointNode, Location.class);
            Location dungeon1 = json.fromJson(dungeon1Node, Location.class);
            Location dungeon2 = json.fromJson(dungeon2Node, Location.class);

            // Location index: 0
            townMap.add(inn);
            // Location index: 1
            townMap.add(blackSmith);
            // Location index: 2
            townMap.add(guardTower);
            // Location index: 3
            townMap.add(abandonedHouse);
            // Location index: 4
            townMap.add(townGate);
            // Location index: 5
            townMap.add(farmLand);
            // Location index: 6
            townMap.add(forest);
            // Location index: 7
            townMap.add(checkpoint);
            // Location index: 8
            townMap.add(dungeon1);
            // Location index: 9
            townMap.add(dungeon2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(List<Location> map) {
        try {
            Json json = new Json();
            JsonNode playerNode = json.parse(json.getResourceStream("/characters/player.json"));
            JsonNode miniBoss1Node = json.parse(json.getResourceStream("/characters/miniboss1.json"));
            JsonNode miniBoss2Node = json.parse(json.getResourceStream("/characters/miniboss2.json"));
            JsonNode finalBossNode = json.parse(json.getResourceStream("/characters/finalboss.json"));

            p1 = json.fromJson(playerNode, Characters.class);
            miniboss1 = json.fromJson(miniBoss1Node, Characters.class);
            miniboss2 = json.fromJson(miniBoss2Node, Characters.class);
            finalboss = json.fromJson(finalBossNode, Characters.class);

            p1.setLocation(map.get(0));
            miniboss1.setLocation(map.get(1));
            miniboss2.setLocation(map.get(8));
            finalboss.setLocation(map.get(9));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() throws IOException, InterruptedException {
        GUI.mainText.setText("Welcome to the game!" +
                "\nType 'help' to display commands available.");
        String output;
        String input;

        // Prompt for input until the user enters "quit". If the user enters quit, the game will exit.
        do {
//            System.out.println("Enter a command below.");
//            System.out.println(">");
//            input = in.readLine();
            GUI.mainText.append("\nEnter a command below.  " +
                    ">");
            synchronized (GameController.class) {
                GameController.class.wait();
            }
            input = GUI.text;
            output = runCommand(input);
            GUI.mainText.setText(output);
        } while (!gameEnd);
    }

    private String help() {
        return "Here are the basic commands:"
                + "\ngo <direction>   : move in the specified direction"
                + "\nlook                 : displays room name, description, items in room, and NPC"
                + "\nget <item>         : pick up the specified item in the room"
                + "\ndrop <item>       : drops the specified item in the room"
                + "\ntalk <NPC>       : attempt to talk to the specified NPC."
                + "\nattack <enemy> : start combat with enemy"
                + "\nhelp               : display commands available"
                + "\nquit               : exit the game and return to menu";
    }

//    public void printBanner() {
//        try {
//            Json json = new Json();
//            BufferedReader reader;
//            reader = new BufferedReader(new InputStreamReader(json.getResourceStream("/GameText/banner.txt")));
//            String line = reader.readLine();
//            while (line != null) {
//                System.out.println(red + line + ANSI_RESET);
//                // read next line
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void printMap() {
//        try {
//            Json json = new Json();
//            BufferedReader reader;
//            reader = new BufferedReader(new InputStreamReader(json.getResourceStream("/GameText/gamemap.txt")));
//            String line = reader.readLine();
//            while (line != null) {
//                System.out.println(red + line + ANSI_RESET);
//                // read next line
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void printIntro() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Json json = new Json();
            JsonNode introNode = json.parse(new File("src/main/resources/GameText/Intro.json"));
            for (JsonNode node : introNode.get("intro")) {
                stringBuilder.append(node.fields().next().getValue().asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GUI.mainText.setText(String.valueOf(stringBuilder));
    }


    /**
     * Fixes any case(toLowerCase()) or whitespace(trim()) issues
     * Checks for valid user input
     */
    public String runCommand(String inputString) throws IOException {
        List<String> userInputs;
        String output = "ok";
        String lowerCaseInput = inputString.trim().toLowerCase();
        if (lowerCaseInput.equals("")) {
            output = "Please enter a command.";
        } else {
            userInputs = userInputs(lowerCaseInput);
            output = parseCommand(userInputs);
        }
        return output;
    }


    private static List<String> userInputs(String lowerCaseInput) {
        String delims = "[ \t,.:;?!\"']+";
        String[] words = lowerCaseInput.split(delims);

        List<String> strlist = new ArrayList<>(Arrays.asList(words));
        return strlist;
    }

    /**
     * parseCommand checks wordlist size and calls processSingleCommand,
     * processTwoCommand or lets user know their wordlist is invalid
     */
    private String parseCommand(List<String> wordlist) throws IOException {
        String message;
        if (wordlist.size() == 1) {
            message = processSingleCommand(wordlist);
        } else if (wordlist.size() == 2) {
            if (wordlist.get(0).equals("grab") || wordlist.get(0).equals("take")) {
                wordlist.set(0, "get");
            }
            message = processTwoCommand(wordlist);
        } else if (wordlist.size() == 3) {
            if (wordlist.get(0).equals("pick") && wordlist.get(1).equals("up")) {
                wordlist.set(1, wordlist.get(2));
            } else if ((wordlist.get(0).equals("talk") && wordlist.get(1).equals("to"))) {
                wordlist.set(1, wordlist.get(2));
            }
            message = processTwoCommand(wordlist);
        } else {
            message = "Invalid command.";
        }
        return message;
    }

    /**
     * processSingleCommand takes in an input as a List
     * and checks String 0 for keywords allowed in List<String> commands
     */
    private String processSingleCommand(List<String> wordlist) {
        String commandOne;
        String message = "";

        commandOne = wordlist.get(0);
        if (!commands.contains(commandOne)) {
            message = commandOne + " is not a known verb! ";
        } else {
            switch (commandOne) {
                case "help":
                    message = help();
                    break;
                case "quit":
                    message = "QUITING GAME";
                    setGameEnd(true);
                    GUI.window.setVisible(false);
                    GUI.window.dispose();
                    break;
                case "look":
                    StringBuilder inventory = new StringBuilder();

                    for (int i = 0; i < p1.getInventory().size(); i++) {
                        if (p1.getInventory().get(i).getName().equals("bow") || p1.getInventory().get(i)
                                .getName().equals("arrows")) {
                            inventory.append(i + 1).append(". ").append(p1.getInventory().get(i).getName())
                                    .append(" - ").append(p1.getInventory().get(i).getDescription())
                                    .append(" - Arrows remain: ").append(p1.getInventory()
                                            .get(i).getValue()).append("\n");
                        } else {
                            inventory.append(i + 1).append(". ").append(p1.getInventory().get(i)
                                    .getName()).append(" - ").append(p1.getInventory().get(i)
                                    .getDescription()).append("\n");
                        }
                    }

                    message =
                            "You are in the " + p1.getLocation().getName() + "\n" + p1.getLocation().getDescription() + "\n" +
                                    "Items available in the room: " + p1.getLocation().getItems() + "\n";
                    break;

                default:
                    message = commandOne + " (not yet implemented)";
                    break;
            }
        }
        return message;
    }

    /**
     * processTwoCommand takes in two word input as a List
     * and checks String 0 and 1 for prep command and location
     */
    private String processTwoCommand(List<String> wordlist) {

        String commandOne;
        String commandTwo;

        boolean addShield = false;
        boolean itemInInventory = p1.getInventory().stream().anyMatch(i -> i.getName().equals(wordlist.get(1)));
        boolean itemInGameItems = gameItems.stream().anyMatch(i -> i.getName().equals(wordlist.get(1)));
        boolean itemInRoomItems = p1.getLocation().getItems().stream().anyMatch(i -> i.equals(wordlist.get(1)));

        String message = "";

        commandOne = wordlist.get(0);
        commandTwo = wordlist.get(1);

        if (!preparatoryCommands.contains(commandOne)) {
            message = commandOne + " is not a valid preparatory command.";
        }

        if (commandOne.equalsIgnoreCase("talk")) {
            if (p1.getLocation().getName().equalsIgnoreCase("Blacksmith Shop")) {
                if (commandTwo.equalsIgnoreCase("blacksmith")) {
                    message = NPC.initBlacksmith();
                } else if (commandTwo != "blacksmith") {
                    return "That person isn't here!";
                }
            }
            if (p1.getLocation().getName().equals("Guard Tower")) {
                return "There is nobody here!";
            }
            if (p1.getLocation().getName().equals("Forbidden Forest")) {
                if (commandTwo.equalsIgnoreCase("Ranger")) {
                    message = NPC.initRanger();
                } else if (commandTwo != "ranger") {
                    return "That person isn't here!";
                }
            }
            if (p1.getLocation().getName().equals("Town Gate")) {
                if (commandTwo.equalsIgnoreCase("guard")) {
                    message = NPC.initGuard();
                } else if (commandTwo != "guard") {
                    return "That person isn't here!";
                }
            }
            if (p1.getLocation().getName().equals("Abandoned Checkpoint")) {
                if (commandTwo.equalsIgnoreCase("Bandit") && miniboss1.getLocation() != null) {
                    message = NPC.initBandit();
                } else {
                    return "That enemy isn't here!";
                }
            }
            if (p1.getLocation().getName().equals("Farmland")) {
                return "There are no people in the Farmlands!";
            }
            if (p1.getLocation().getName().equals("Abandoned House")) {
                return "There is nobody left in this house. It's been abandoned for some time.";
            }
            if (p1.getLocation().getName().equals("Inn")) {
                return "The Inn is empty. There haven't been travelers in the town lately.";
            }
            if (p1.getLocation().getName().equals("Dungeon Entrance")) {
                if (commandTwo.equalsIgnoreCase("Faceless") && miniboss2.getLocation() != null) {
                    message = NPC.initFaceless();
                } else {
                    return "That enemy is not here!";
                }
            }
            if (p1.getLocation().getName().equals("Dungeon")) {
                if (commandTwo.equalsIgnoreCase("Man-Eater") && finalboss.getLocation() != null) {
                    message = NPC.initManEater();
                } else {
                    return "That enemy is not here!";
                }
            }

        }

        if (commandOne.equals("go")) {

            boolean hasNoBadge = p1.getInventory().stream().noneMatch((i -> i.getName().equals("badge")));

            // TODO: When combat with the bandit is completed, add gameItems "necklace" to player's inventory
            boolean noNecklace = p1.getInventory().stream().noneMatch(i -> i.getName().equals("necklace"));

            switch (commandTwo) {
                case "north":
                    goNorth();
                    message = "Your current location is the " + p1.getLocation().getName()
                            + ".\n";
                    break;

                case "south":
                    if (p1.getLocation().getName().equals("Forbidden Forest") && noNecklace) {
                        message = "You need to prove your combat skills to enter the Dungeon.\n" +
                                "Help the Ranger to defeat the Bandit and gain their trust.";
                    } else {
                        goSouth();
                        message = "Your current location is the " + p1.getLocation().getName()
                                + ".\n";
                    }
                    break;

                case "west":
                    if (p1.getLocation().getName().equals("Town Gate") && hasNoBadge) {
                        message = "The guardsman asks you to display the badge to exit the town gate. \n";
                    } else {
                        goWest();
                        message = "Your current location is the " + p1.getLocation().getName()
                                + ".\n";
                    }
                    break;

                case "east":
                    if (p1.getLocation().getName().equals("Town Gate") && hasNoBadge) {
                        message = "The guardsman asks you to display the badge to exit the town gate. \n";
                    } else {
                        goEast();
                        message = "Your current location is the " + p1.getLocation().getName()
                                + ".\n";
                    }
                    break;

                default:
                    message = "Invalid direction.";
                    break;
            }
            topDisplay.refresh(p1);
        }

        if (commandOne.equals("get")) {
            boolean hasNoBow = p1.getInventory().stream().noneMatch((i -> i.getName().equals("bow")));

            if (commandTwo.equals("locker") && p1.getLocation().getItems().contains("locker")) {
                return "Hmmmmm. The locker is fixed to the wall, and you need a key to open it.";

            } else if (commandTwo.equals("badge") && itemInRoomItems && hasNoBow) {
                return "The guardsman requires you to have a weapon before issuing you a badge \n";

            } else if (itemInRoomItems) {
                if (itemInGameItems) {
                    if (commandTwo.equals("key") && miniboss1.getLocation() != null) {
                        return "Bandit guards the key, you must kill him first";
                    } else if (commandTwo.equals("necklace") && miniboss1.getLocation() != null) {
                        return "You must defeat the Bandit first.";
                    } else if (commandTwo.equals("arrows") && miniboss2.getLocation() != null) {
                        return "faceless body language dissuades you from picking up the arrows";
                    } else {
                        p1.getInventory().add(gameItems.stream()
                                .filter(i -> i.getName().equals(commandTwo))
                                .findFirst().orElse(null));
                        p1.getLocation().getItems().remove(commandTwo);
                        CombatInventory.testCombatInventory(GUI, p1);
                        return "You pick up the " + commandTwo + ".";
                    }
                }
            } else {
                message = "There is no " + commandTwo + " here.";
            }
        }

        if (commandOne.equals("use")) {
            if (p1.getInventory().isEmpty()) {
                return "You have no items to use.";
            } else {
                if (itemInInventory) {
                    if (commandTwo.equals("potion")) {
//                        p1.setHealth(p1.getHealth() + Objects.requireNonNull(p1.getInventory().stream()
//                                .filter(i -> i.getName().equals(commandTwo))
//                                .findFirst().orElse(null)).getValue());
                        p1.setHealth(100);
                        p1.getInventory().remove(Objects.requireNonNull(p1.getInventory().stream()
                                .filter(i -> i.getName().equals(commandTwo))
                                .findFirst().orElse(null)));
                        CombatInventory.testCombatInventory(GUI, p1);
                        topDisplay.refresh(p1);
                        return "You use the potion and gain " + "FULL"
                                + " health.";


                    } else if (commandTwo.equals("arrows")) {
                        for (Item bow : p1.getInventory()) {
                            if (bow.getName().equals("bow")) {
                                bow.setValue(bow.getValue() + Objects.requireNonNull(p1.getInventory().stream()
                                        .filter(i -> i.getName().equals(commandTwo))
                                        .findFirst().orElse(null)).getValue());

                                p1.getInventory().remove(Objects.requireNonNull(p1.getInventory().stream()
                                        .filter(i -> i.getName().equals(commandTwo))
                                        .findFirst().orElse(null)));
                                CombatInventory.testCombatInventory(GUI, p1);
                                return "You use the arrows and add them to the bow.";
                            }
                        }

                    } else if (commandTwo.equals("key") && p1.getLocation().getItems().contains("locker")) {
//                        GUI.mainText.append("\nWoW! It is an armor that can protect you from the monsters!");
                        addShield = true;
                        p1.getInventory().remove(Objects.requireNonNull(p1.getInventory().stream()
                                .filter(i -> i.getName().equals(commandTwo))
                                .findFirst().orElse(null)));
                        CombatInventory.testCombatInventory(GUI, p1);
                        return "WoW! It is an armor that can protect you from the monsters!";
//                    } else if (commandTwo.equals("map")) {
//                        printMap();

                    } else if (commandTwo.equals("sword") || commandTwo.equals("bow")) {
                        return "You can only use the sword or the bow during combat.";

                    } else if (commandTwo.equals("shield")) {
                        p1.setShield(p1.getShield() + Objects.requireNonNull(p1.getInventory().stream()
                                .filter(i -> i.getName().equals(commandTwo))
                                .findFirst().orElse(null)).getValue());
                        p1.getInventory().remove(Objects.requireNonNull(p1.getInventory().stream()
                                .filter(i -> i.getName().equals(commandTwo))
                                .findFirst().orElse(null)));
                        CombatInventory.testCombatInventory(GUI, p1);
                        topDisplay.refresh(p1);
                        return "You just equipped a shield with " + "50"
                                + " shield protection.";

                    } else {
                        return "You cannot use that item.";
                    }
                } else {
                    message = "You do not have that item.";
                }

                if (addShield) {
                    p1.getInventory().add(gameItems.get(8));
                    CombatInventory.testCombatInventory(GUI, p1);
                }
            }
        }

        if (commandOne.equals("drop")) {
            if (p1.getInventory().isEmpty()) {
                return "There is nothing to drop.";
            } else {
                if (itemInInventory) {
                    p1.getLocation().getItems().add(commandTwo);
                    p1.getInventory().remove(Objects.requireNonNull(p1.getInventory().stream()
                            .filter(i -> i.getName().equals(commandTwo))
                            .findFirst().orElse(null)));
                    CombatInventory.testCombatInventory(GUI, p1);
                    return "You drop the " + commandTwo + ".";
                } else {
                    message = "You do not have that item.";
                }
            }
        }

        // if verb is attack
        if (commandOne.equals("attack")) {
            // if second word is matching and enemy's location matches yours
            // then call combat

            if (commandTwo.equalsIgnoreCase("Bandit") && p1.getLocation() == miniboss1.getLocation()) {
                attackEnemy(miniboss1);
            } else if (commandTwo.equalsIgnoreCase("Faceless") && p1.getLocation() == miniboss2.getLocation()) {
                attackEnemy(miniboss2);
            } else if (commandTwo.equalsIgnoreCase("man-eater") && p1.getLocation() == finalboss.getLocation()) {
                attackEnemy(finalboss);
            } else {
                return "That enemy is not here!";
            }
        }

        return message;
    }

    public void movePlayer(Characters player, Location location) {
        player.setLocation(location);
    }

    public void attackEnemy(Characters enemy) {
        Characters loser = combat.enemyEncounter(enemy, p1, GUI, topDisplay);

        if (loser != null && loser != p1) {
            loser.setLocation(null);
            if (loser == finalboss) {
                gameWin();
            }
        } else if (loser == p1) {
            GUI.mainText.append("\nYou died to an enemy");
            gameOver();
        }
    }


    public int moveTo(Characters player, Direction direction) {
        Location location = player.getLocation();
        int exit;

        switch (direction) {
            case NORTH:
                exit = location.getNorth();
                break;
            case SOUTH:
                exit = location.getSouth();
                break;
            case WEST:
                exit = location.getWest();
                break;
            case EAST:
                exit = location.getEast();
                break;
            default:
                exit = Direction.NOEXIT;
                break;
        }
        if (exit != Direction.NOEXIT) {
            movePlayer(player, townMap.get(exit));
        }
        return exit;
    }

    public void movePlayerTo(Direction direction) {
        if (moveTo(p1, direction) == Direction.NOEXIT) {
            GUI.mainText.append("\nNo Exit");
        }
    }

    private void goNorth() {
        movePlayerTo(Direction.NORTH);
    }

    private void goSouth() {
        movePlayerTo(Direction.SOUTH);
    }

    private void goWest() {
        movePlayerTo(Direction.WEST);
    }

    private void goEast() {
        movePlayerTo(Direction.EAST);
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void gameOver() {
        GUI.mainText.append("\nYOU HAVE DIED");
        setGameEnd(true);
        GUI.loadYouDied();
    }

    public void gameWin() {
        GUI.mainText.append("\nYOU HAVE WON THE GAME CONGRATS");
        setGameEnd(true);
        GUI.loadYouWin();
    }

    public void startPrompt() throws IOException, InterruptedException {

        boolean keepGoing = true;
        while (keepGoing) {
//            String input = in.readLine();
            synchronized (GameController.class) {
                GameController.class.wait();
            }
            String input = GUI.text;
            if (input.equals("y")) {
                printIntro();
                keepGoing = true;
            } else if (input.equals("n")) {
                keepGoing = false;
            } else {
                GUI.mainText.append("\nInvalid input. Please try again and use y or n.");
            }
        }
    }

}

