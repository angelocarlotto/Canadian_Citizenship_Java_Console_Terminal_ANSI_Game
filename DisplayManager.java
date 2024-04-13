
import java.util.Random;
import java.util.Scanner;

/**
 * This class is responsable to hold method that will help how to draw elements
 * on the terminal
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class DisplayManager {
        public static Random random = new Random();
        public static int pointInitY = 0;
        public static int pointInitX = 0;
        public static int height = 30;
        public static int width = 100;
        public static int maxTimeToDigitDelay = 50;

        private Scanner scanner;

        private GameCharacter mainCharacter = null;
        private GameCharacter mosnter1 = null;
        private GameCharacter mosnter2 = null;
        private GameCharacter mosnter3 = null;
        private GameCharacter mosnter4 = null;

        /**
         * this array will hold all the mosnters. Also will help the code understand how
         * many levels there is on the game
         * 
         */
        private GameCharacter[] monstersSlashLevels;

        /**
         * this array tell us how many recovery items there is on the game
         */
        private String[] recoveryItemsDefault = new String[] { "1-Voluntary Job (restores 10 points)",
                        "2-Part-time Job (restores 20 points)",
                        "3-Full-time Job (restores 30 points)" };
        /**
         * this array will help us understand which item was used or not
         */
        private boolean[] recoveryItemUsed = new boolean[] { false, false, false };

        private ANSICodeManager ansiCodeManager = new ANSICodeManager();

        public DisplayManager() {
        }

        public DisplayManager(int height, int width, Scanner scanner) {
                DisplayManager.height = height;
                DisplayManager.width = width;
                this.scanner = scanner;

                this.mainCharacter = new GameCharacter("hero1_hero_frame1_RGB.txt", "hero1_hero_frame2_RGB.txt",
                                "hero1_hero_frame3_RGB.txt", "hero1_hero_frame4_RGB.txt");
                this.mosnter1 = new GameCharacter("CIC Wolf", "monster1_monster_frame1_RGB.txt",
                                "monster1_monster_frame2_RGB.txt", "monster1_monster_frame3_RGB.txt",
                                "monster1_monster_frame3_RGB.txt");
                this.mosnter2 = new GameCharacter("IRCC Imigration", "monster2_monster_frame1_RGB.txt",
                                "monster2_monster_frame2_RGB.txt", "monster2_monster_frame3_RGB.txt",
                                "monster2_monster_frame3_RGB.txt");
                this.mosnter3 = new GameCharacter("Big Heade from College",
                                "monster3_monster_frame1_RGB.txt",
                                "monster3_monster_frame2_RGB.txt", "monster3_monster_frame3_RGB.txt",
                                "monster3_monster_frame3_RGB.txt");
                this.mosnter4 = new GameCharacter("Canadian Citzenship",
                                "monster4_monster_frame1_RGB.txt",
                                "monster4_monster_frame2_RGB.txt", "monster4_monster_frame3_RGB.txt",
                                "monster4_monster_frame3_RGB.txt");

                monstersSlashLevels = new GameCharacter[] { mosnter1, mosnter2, mosnter3, mosnter4 };
        }

        public void runGame() {

                init(height, width);
                int meanY = (DisplayManager.height / 2) - 5;
                int meanX = (DisplayManager.width / 2) - 25;
                String anwser = "";
                MessageBox msgCenterScreen = null;
                try {

                        mainCharacter.loadImagesCharacter();
                        for (GameCharacter characterAux : monstersSlashLevels)
                                characterAux.loadImagesCharacter();

                        ansiCodeManager.enableCursorIndicator();
                        // ==================MESSAGE ASKING FOR THE PLAYER NAME====================
                        do {
                                msgCenterScreen = messageBox("Please enter your character's name:",
                                                meanY, meanX);

                                anwser += scanner.nextLine();
                                mainCharacter.setCharacterName(anwser);
                                msgCenterScreen.clean();
                        } while (anwser.isEmpty());
                        // ==================MESSAGE ASKING FOR THE PLAYER COUNTRY====================
                        do {
                                msgCenterScreen = messageBox("Please enter your nationality:",
                                                meanY,
                                                meanX);
                                anwser = "";
                                anwser += scanner.nextLine();
                                mainCharacter.setCountry(anwser);
                                msgCenterScreen.clean();
                        } while (anwser.isEmpty());

                        // ==================MESSAGE EXPLANING THE GAME====================
                        msgCenterScreen = messageBox(
                                        "Great, " + mainCharacter.getCharacterName()
                                                        + "! Here are your starting attribsutes:" +
                                                        "\n - Life Points:["
                                                        + String.valueOf(GameCharacter.defaultAmontOfLife)
                                                        + "] (These will keep you alive in battles)"
                                                        +
                                                        "\n - Attack: [10] (Your standard attack strength)" +
                                                        "\n - Defense: [8] (Your ability to resist attacks)" +
                                                        "\n - Recovery Items: " +
                                                        "\n    Voluntary Job (restores 10 points)" +
                                                        "\n    Part-time Job (restores 20 points)" +
                                                        "\n    Fulltime Job (restores 30 points)" +
                                                        "\n\nPress enter to continue",
                                        meanY, meanX, true);

                        scanner.nextLine();
                        msgCenterScreen.clean();

                        // ======================ENCOURAGE MESSAGE BEFORE THE BEGINING OF THE
                        // GAME================
                        msgCenterScreen = messageBox(
                                        "With courage and strategy, you'll navigate through the trials ahead." +
                                                        "\nAre you ready to start" +
                                                        "\nyour journey? (" + ansiCodeManager.buildRedText("Yes")
                                                        + "/No)"
                                                        +
                                                        "\n\nPress enter to continue",
                                        meanY, meanX, true);
                        anwser = "";
                        anwser = scanner.nextLine();
                        msgCenterScreen.clean();

                        if (anwser.equalsIgnoreCase("no")) {
                                // ==================THE PLAYER CHOOSE TO DO NOT PLAY THE GAME: WHAT A
                                // BUMMER====================
                                msgCenterScreen = messageBox(ansiCodeManager.buildRedText(
                                                "GAME OVER!!!\n\n\nYou've being deported to "
                                                                + mainCharacter.getCountry()),
                                                meanY, meanX);

                        } else {
                                // =================THE GAME WILL BEGING PART 1=====================
                                // GameCharacter mainCharacter = GameRun.hero;
                                GameCharacter monster;
                                int levelCounter = 0;

                                boolean timeToMainCharacterPlay = true;
                                int heroPositionY = DisplayManager.height - 40;
                                int heroPositionX = 30;
                                int monsterPositionY = 10;
                                int monsterPositionX = 150;
                                MessageBox msgTitleBattleScreen = null;

                                // ==================THE GAME WILL BEGING PART 2====================
                                // start battles while level counter is lower then the number os levels and the
                                // main character is greater then zero
                                while (levelCounter < monstersSlashLevels.length
                                                && mainCharacter.getCharacterLife() > 0) {
                                        monster = monstersSlashLevels[levelCounter];

                                        // this check is to prevent the null point exception on the firt turn
                                        if (msgTitleBattleScreen != null)
                                                msgTitleBattleScreen.clean();

                                        msgTitleBattleScreen = messageBox(
                                                        "Battle " + String.valueOf(levelCounter + 1) + "/"
                                                                        + String.valueOf(
                                                                                        monstersSlashLevels.length),
                                                        4,
                                                        (DisplayManager.width / 2) - 10);

                                        mainCharacter.updatePosition(heroPositionX, heroPositionY);
                                        monster.updatePosition(monsterPositionX, monsterPositionY);

                                        // ==================GET THE ESPECIFIC MESSAGE TO THE BEGINING OF EACH
                                        // LEVEL====================
                                        switch (levelCounter) {
                                                case 0:
                                                        msgCenterScreen = messageBox(
                                                                        "Your journey begins now,"
                                                                                        + mainCharacter.getCharacterName()
                                                                                        + "!" +
                                                                                        "\nAs you step onto the path of trials, your resolve to become"
                                                                                        +
                                                                                        "\n a Canadian citizen will be tested."
                                                                                        +
                                                                                        "\nAhead lies your first challenge, It's time to prove your strength."
                                                                                        +
                                                                                        "\n\n"
                                                                                        + monster.getCharacterName()
                                                                                        + " stands in your way" +
                                                                                        "\n\nPress enter to continue",
                                                                        meanY,
                                                                        (DisplayManager.width / 2) - 35, true);
                                                        break;

                                                case 1:
                                                        msgCenterScreen = messageBox(
                                                                        "With " + ansiCodeManager
                                                                                        .buildRedText("CIC Wolf")
                                                                                        + " defeated, you advance to the next stage of your journey."
                                                                                        +
                                                                                        "\nEach victory brings you closer to your goal of Canadian citizenship."
                                                                                        +
                                                                                        "\nYou've successfully navigated the initial trial, "
                                                                                        +
                                                                                        "\nproving your strength and determination."
                                                                                        +
                                                                                        "\n\nNow, you're facing a new enemy "
                                                                                        + ansiCodeManager.buildRedText(
                                                                                                        "IRCC Immigration")
                                                                                        + "."
                                                                                        +
                                                                                        "\n\nPrepare yourself, "
                                                                                        + mainCharacter.getCharacterName()
                                                                                        + ". The next challenge awaits."
                                                                                        +
                                                                                        "\n\nPress enter to continue",

                                                                        meanY,
                                                                        (DisplayManager.width / 2) - 35, true);
                                                        break;

                                                case 2:
                                                        msgCenterScreen = messageBox(
                                                                        "With your Immigrational status secured," +
                                                                                        "\nyou move onto proving your skills and knowledge in a"
                                                                                        +
                                                                                        "\nCanadian educational institution."
                                                                                        +
                                                                                        "\nTest your skills against "
                                                                                        + ansiCodeManager.buildRedText(
                                                                                                        "Big Headed from College")
                                                                                        + "."
                                                                                        +
                                                                                        "\n\nPress enter to continue",

                                                                        meanY,
                                                                        (DisplayManager.width / 2) - 35, true);
                                                        break;

                                                case 3:
                                                        msgCenterScreen = messageBox(
                                                                        "You hear a voice echoing with the depth of lakes and the"
                                                                                        +
                                                                                        "\nwhisper of the northern winds."
                                                                                        +
                                                                                        "\n\n \"Welcome, brave soul. You've journeyed far and faced"
                                                                                        +
                                                                                        "\ntrials with courage and wisdom."
                                                                                        +
                                                                                        "\nBut to truly embrace the mantle of Canadian citizenship,"
                                                                                        +
                                                                                        "\nyou must prove your understanding and commitment to the"
                                                                                        +
                                                                                        "\nvalues that bind us as a nation."
                                                                                        +
                                                                                        "\nRespect for the rule of law, the rights and freedoms of all,"
                                                                                        +
                                                                                        "\nand the desire to contribute to a society that is"
                                                                                        +
                                                                                        "\ninclusive and ever-thriving.\""
                                                                                        +
                                                                                        "\n\nAre you prepared to take this final step and defeat me, as your "
                                                                                        + ansiCodeManager.buildRedText(
                                                                                                        "Final Boss")
                                                                                        + "?"
                                                                                        +
                                                                                        "\n\nPress enter to continue",

                                                                        meanY,
                                                                        (DisplayManager.width / 2) - 35, true);
                                                        break;

                                        }

                                        scanner.nextLine();
                                        msgCenterScreen.clean();
                                        // ===================INITIATE THE BATTLE===================
                                        // now beggins the especific battle from each leve
                                        do {
                                                // the main character always start the batle
                                                if (timeToMainCharacterPlay) {

                                                        msgCenterScreen = messageBox(
                                                                        "Press enter to " + mainCharacter
                                                                                        .getCharacterName()
                                                                                        + "\nRoll the dices and Play " +
                                                                                        "\n\nEnter o to chose a recovery item",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40);

                                                } else {

                                                        msgCenterScreen = messageBox(
                                                                        "Press enter to " + monster.getCharacterName()
                                                                                        + "\nRoll the dices and Play" +
                                                                                        "\n\nEnter o to chose a recovery item",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 10);

                                                }
                                                anwser = "";
                                                anwser += scanner.nextLine();
                                                msgCenterScreen.clean();
                                                // enter the letter o to enter the option mode
                                                if (anwser.equalsIgnoreCase("o") && timeToMainCharacterPlay) {

                                                        String message = "Recovery Items: ";
                                                        for (int i = 0; i < recoveryItemsDefault.length; i++) {

                                                                if (!recoveryItemUsed[i])
                                                                        message += "\n" + recoveryItemsDefault[i];
                                                        }
                                                        message += "\n\nPress enter to continue";

                                                        msgCenterScreen = messageBox(message,
                                                                        20,
                                                                        (DisplayManager.width / 2) - 10);

                                                        anwser = "";
                                                        anwser += scanner.nextLine();
                                                        if (anwser.equalsIgnoreCase("1")
                                                                        || anwser.equalsIgnoreCase("2")
                                                                        || anwser.equalsIgnoreCase("3")) {

                                                                if (anwser.equalsIgnoreCase("1")) {
                                                                        mainCharacter.setCharacterLife(
                                                                                        mainCharacter.getCharacterLife()
                                                                                                        + 10);
                                                                        recoveryItemUsed[0] = true;
                                                                } else if (anwser.equalsIgnoreCase("2")) {
                                                                        mainCharacter.setCharacterLife(
                                                                                        mainCharacter.getCharacterLife()
                                                                                                        + 20);
                                                                        recoveryItemUsed[1] = true;
                                                                } else if (anwser.equalsIgnoreCase("3")) {
                                                                        mainCharacter.setCharacterLife(
                                                                                        mainCharacter.getCharacterLife()
                                                                                                        + 30);
                                                                        recoveryItemUsed[2] = true;
                                                                }
                                                        }

                                                        mainCharacter.updatePosition(heroPositionX,
                                                                        heroPositionY);
                                                        msgCenterScreen.clean();
                                                } else {
                                                        if (timeToMainCharacterPlay) {

                                                                // rolling the dice
                                                                int diceValue = random.nextInt(1, 6);
                                                                int totalAttack = diceValue
                                                                                + mainCharacter.getCharacterAttack();
                                                                int demage = monster.decreaseLife(totalAttack);

                                                                msgCenterScreen = messageBox(
                                                                                "Your dice roll adds "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(
                                                                                                                                diceValue)
                                                                                                + " to your attack, \ntotaling "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(
                                                                                                                                totalAttack)
                                                                                                + "." +
                                                                                                "\nYour attack breaks through,\n dealing  "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(demage)
                                                                                                + " damage to "
                                                                                                + monster.getCharacterName()
                                                                                                +
                                                                                                "\n\nPress enter to continue",
                                                                                20,
                                                                                (DisplayManager.width / 2) - 40);

                                                        } else {
                                                                // rolling the dice
                                                                int diceValue = random.nextInt(1, 6);
                                                                int totalAttack = diceValue
                                                                                + monster.getCharacterAttack();
                                                                int demage = mainCharacter.decreaseLife(totalAttack);

                                                                msgCenterScreen = messageBox(
                                                                                monster.getCharacterName()
                                                                                                + " roll adds "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(
                                                                                                                                diceValue)
                                                                                                + " to your attack, \ntotaling "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(
                                                                                                                                totalAttack)
                                                                                                + ".\n"
                                                                                                +
                                                                                                "Your attack breaks through,\n dealing  "
                                                                                                + ansiCodeManager
                                                                                                                .buildRedText(demage)
                                                                                                + " damage to "
                                                                                                + mainCharacter.getCharacterName()
                                                                                                +
                                                                                                "\n\nPress enter to continue",
                                                                                20,
                                                                                (DisplayManager.width / 2) - 10);
                                                        }
                                                        mainCharacter.updatePosition(heroPositionX,
                                                                        heroPositionY);
                                                        monster.updatePosition(monsterPositionX,
                                                                        monsterPositionY);
                                                        // this alternate between who gonna plays next
                                                        timeToMainCharacterPlay = !timeToMainCharacterPlay;

                                                        scanner.nextLine();
                                                        msgCenterScreen.clean();
                                                }
                                                // while either monster or main character has positive life, keep going
                                                // to another turn.
                                        } while (monster.getCharacterLife() > 0
                                                        && mainCharacter.getCharacterLife() > 0);

                                        // change of level or game over
                                        // if the main character lose, it is game over
                                        if (mainCharacter.getCharacterLife() > 0) {
                                                mainCharacter.updateWinningPosition();

                                                if (levelCounter < monstersSlashLevels.length - 1) {
                                                        // ===================PLAYER WON LEVEL 1,2 and
                                                        // 3===================
                                                        msgCenterScreen = messageBox(
                                                                        "Congratulations! " + monster.getCharacterName()
                                                                                        + "’s life points have dropped to 0."
                                                                                        +
                                                                                        "\nYou've won this battle and can"
                                                                                        +
                                                                                        "\nmove forward on your journey."
                                                                                        +
                                                                                        "\n\nPress Enter to continue....",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40, true);

                                                        // always the main character will be the firt to play on every
                                                        // turn
                                                        timeToMainCharacterPlay = true;

                                                        // increase the level
                                                        levelCounter++;

                                                        scanner.nextLine();
                                                        msgCenterScreen.clean();
                                                } else {
                                                        // ===================PLAYER WON LEVEL 4===================
                                                        msgCenterScreen = messageBox(
                                                                        "Congratulations! " + monster.getCharacterName()
                                                                                        + "’s life points have dropped to 0."
                                                                                        +
                                                                                        "\n\nAfter defeating the final monster, you've proven your"
                                                                                        +
                                                                                        "\ndetermination and strength."
                                                                                        +
                                                                                        "\n\nA certificate of Canadian Citizenship is granted to you,"
                                                                                        +
                                                                                        "\n"
                                                                                        + mainCharacter.getCharacterName()
                                                                                        + ", in recognition of your perseverance and"
                                                                                        +
                                                                                        "\ncourage."
                                                                                        +
                                                                                        "\n\nCongratulations! Welcome to your new home."
                                                                                        + "\n\nPress Enter to continue....",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40, true);

                                                        scanner.nextLine();
                                                        msgCenterScreen.clean();

                                                        // ==================ASKING IF THE PLKAYER WANTS TO PLAY AGAIN
                                                        // IN CASE WINNING===================
                                                        msgCenterScreen = messageBox(
                                                                        "Would you like to PLAY again? (Yes/"
                                                                                        + ansiCodeManager.buildRedText(
                                                                                                        "No")
                                                                                        + ")"
                                                                                        + "\n\nPress Enter to continue....",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40);

                                                        anwser = "";
                                                        anwser += scanner.nextLine();
                                                        msgCenterScreen.clean();

                                                        if (anwser.equalsIgnoreCase("yes")) {
                                                                // reset levelCounter to first level, level 0
                                                                levelCounter = 0;
                                                                timeToMainCharacterPlay = true;
                                                                // reset recovery items
                                                                recoveryItemUsed = new boolean[] { false, false,
                                                                                false };

                                                                // reset life of all characters
                                                                mainCharacter.setCharacterLife(
                                                                                GameCharacter.defaultAmontOfLife);
                                                                for (GameCharacter character : monstersSlashLevels) {
                                                                        character.setCharacterLife(
                                                                                        GameCharacter.defaultAmontOfLife);
                                                                }
                                                        } else {
                                                                // always the main character will be the firt to play on
                                                                // every turn
                                                                timeToMainCharacterPlay = true;

                                                                // increase the level
                                                                levelCounter++;

                                                                scanner.nextLine();
                                                                msgCenterScreen.clean();
                                                        }

                                                }

                                        }
                                        // if the mosnter loses it goes to another turn on the next level.
                                        else {
                                                // ===================PLAYER LOOSE===================
                                                msgCenterScreen = messageBox(
                                                                "Unfortunately, your life points have reached 0." +
                                                                                "\n\nIt's GAME OVER." +
                                                                                "\n\n But don't lose hope; every path has its setbacks."
                                                                                +
                                                                                "\n\nPress Enter to continue....",
                                                                20,
                                                                (DisplayManager.width / 2) - 40);
                                                scanner.nextLine();
                                                msgCenterScreen.clean();

                                                // ==================ASKING IF THE PLKAYER WANTS TO PLAY AGAIN IN CASE
                                                // LOSING===================
                                                msgCenterScreen = messageBox(
                                                                "Would you like to PLAY again? (Yes/"
                                                                                + ansiCodeManager.buildRedText("No")
                                                                                + ")"
                                                                                + "\n\nPress Enter to continue....",
                                                                20,
                                                                (DisplayManager.width / 2) - 40);

                                                anwser = "";
                                                anwser += scanner.nextLine();
                                                msgCenterScreen.clean();

                                                if (anwser.equalsIgnoreCase("yes")) {
                                                        // reset levelCounter to first level, level 0
                                                        levelCounter = 0;
                                                        timeToMainCharacterPlay = true;
                                                        // reset recovery items
                                                        recoveryItemUsed = new boolean[] { false, false, false };

                                                        // reset life of all characters
                                                        mainCharacter.setCharacterLife(
                                                                        GameCharacter.defaultAmontOfLife);
                                                        for (GameCharacter character : monstersSlashLevels) {
                                                                character.setCharacterLife(
                                                                                GameCharacter.defaultAmontOfLife);
                                                        }
                                                }
                                        }
                                }
                        }

                        ansiCodeManager.cleanWholeTerminal();
                        msgCenterScreen = messageBox(
                                        "\"Do your best, in the condition you have, while you don't have better conditions to do even better!\""
                                                        + "\n by Mário Sérgio Cortella"
                                                        + "\n\nProfessor:"
                                                        + "\n     Nadine Bakri"
                                                        + "\n\nGroup Members:"
                                                        + "\n     Shabnam Iskandarova"
                                                        + "\n     Henrique Goncalves"
                                                        + "\n     Geraldo Beiro Neto"
                                                        + "\n     Angelo Carlotto",
                                        20,
                                        (DisplayManager.width / 2) - 40, true);

                        while (true) {
                                try {

                                        Thread.sleep(500);
                                } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                }
                        }

                } catch (Exception x) {
                        // System.out.println(x.getMessage());
                        throw x;
                }

        }

        /**
         * this method initiate the terminal by appling the background color and
         * positioning the cursor.
         * 
         * @param height height of the screen
         * @param width  widht of the screen
         */
        public void init(int height, int width) {

                DisplayManager.height = height;
                DisplayManager.width = width;
                ansiCodeManager.setCursorToHomePosition();
                ansiCodeManager.cleanWholeTerminal();
                ansiCodeManager.disableCursorIndicator();

                for (int i = 0; i < DisplayManager.height; i++) {
                        for (int j = 0; j < DisplayManager.width; j++) {
                                ansiCodeManager.printOneSpaceWithDefaultBackGroundColor();
                        }
                        System.out.println();
                }

        }

        /**
         * This method helps to print the image stored on a 3x3 array on the
         * screen/terminal
         * 
         * @param arrayCharacter40x25
         * @param startPostY
         * @param startPosX
         */
        public void printCharacter_RGB(int[][][] arrayCharacter40x25, int startPostY, int startPosX) {
                int postY = startPostY;
                int postX = startPosX;
                for (int row = 0; row < arrayCharacter40x25.length; row++) {
                        ansiCodeManager.setCustomCursorPosition(postX, postY + row);
                        for (int col = 0; col < arrayCharacter40x25[0].length; col++) {
                                if (arrayCharacter40x25[row][col][3] == 0) {
                                        ansiCodeManager.printOneSpaceWithDefaultBackGroundColor();
                                } else {
                                        ansiCodeManager.setRGBTextBackGroundColor(arrayCharacter40x25[row][col][0],
                                                        arrayCharacter40x25[row][col][1],
                                                        arrayCharacter40x25[row][col][2]);
                                        ansiCodeManager.printOneSpace();
                                }
                        }
                        System.out.println();
                }

        }

        /**
         * this method helps clean the region previous used to draw something opn the
         * terminal/screen
         * 
         * @param height
         * @param width
         * @param startPostY
         * @param startPosX
         */
        public void cleanArea_RGB(int height, int width, int startPostY, int startPosX) {
                int postY = startPostY;
                int postX = startPosX;
                for (int row = 0; row < height; row++) {

                        ansiCodeManager.setCustomCursorPosition(postX, postY + row);
                        for (int col = 0; col < width; col++) {
                                ansiCodeManager.printOneSpaceWithDefaultBackGroundColor();
                        }
                        System.out.println();
                }

        }

        /**
         * will print a box message on the screen with optional delay
         * 
         * @param msg
         * @param startPointY
         * @param startPointX
         * @param withDelay   if treu the message will emulate someone typing on the
         *                    screen
         * @return Message object that can be clened later on the game
         */
        public MessageBox messageBox(String msg, int startPointY, int startPointX, boolean withDelay) {
                MessageBox msgBOx = new MessageBox(startPointX, startPointY, 4, msg);
                msgBOx.draw(false);
                return msgBOx;
        }

        /**
         * Will help to print a box message on the screen without delay
         * 
         * @param msg
         * @param startPointY
         * @param startPointX
         * @return Message object that can be clened later on the game
         */
        public MessageBox messageBox(String msg, int startPointY, int startPointX) {
                return messageBox(msg, startPointY, startPointX, false);
        }

}
