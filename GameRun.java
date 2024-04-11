
import java.util.Random;
import java.util.Scanner;

/**
 * This class is responsable to run the game
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class GameRun {
        static Scanner scanner = new Scanner(System.in);
        static Random random = new Random();

        static GameCharacter hero = new GameCharacter("hero1_hero_frame1_RGB.txt", "hero1_hero_frame2_RGB.txt",
                        "hero1_hero_frame3_RGB.txt", "hero1_hero_frame4_RGB.txt");
        static GameCharacter mosnter1 = new GameCharacter("CIC Wolf", "monster1_monster_frame1_RGB.txt",
                        "monster1_monster_frame2_RGB.txt", "monster1_monster_frame3_RGB.txt",
                        "monster1_monster_frame3_RGB.txt");
        static GameCharacter mosnter2 = new GameCharacter("IRCC Imigration", "monster2_monster_frame1_RGB.txt",
                        "monster2_monster_frame2_RGB.txt", "monster2_monster_frame3_RGB.txt",
                        "monster2_monster_frame3_RGB.txt");
        static GameCharacter mosnter3 = new GameCharacter("Big Heade from College", "monster3_monster_frame1_RGB.txt",
                        "monster3_monster_frame2_RGB.txt", "monster3_monster_frame3_RGB.txt",
                        "monster3_monster_frame3_RGB.txt");
        static GameCharacter mosnter4 = new GameCharacter("Canadian Citzenship", "monster4_monster_frame1_RGB.txt",
                        "monster4_monster_frame2_RGB.txt", "monster4_monster_frame3_RGB.txt",
                        "monster4_monster_frame3_RGB.txt");

        /**
         * this array will hold all the mosnters. Also will help the code understand how
         * many levels there is on the game
         * 
         */
        static GameCharacter[] monstersSlashLevels = { mosnter1, mosnter2, mosnter3, mosnter4 };

        /**
         * this array tell us how many recovery items there is on the game
         */
        static String[] recoveryItemsDefault = new String[] { "1-Voluntary Job (restores 10 points)",
                        "2-Part-time Job (restores 20 points)",
                        "3-Full-time Job (restores 30 points)" };
        /**
         * this array will help us understand which item was used or not
         */
        static boolean[] recoveryItemUsed = new boolean[] { false, false, false };

        /**
         * @param args
         */
        public static void main(String[] args) {
                DisplayManager.init(60, 200);
                int meanY = (DisplayManager.height / 2) - 5;
                int meanX = (DisplayManager.width / 2) - 25;
                String anwser = "";
                MessageBox msgCenterScreen = null;
                try {

                        hero.loadImagesCharacter();
                        for (GameCharacter characterAux : monstersSlashLevels)
                                characterAux.loadImagesCharacter();

                        ANSICodeManager.enableCursorIndicator();
                        // ==================MESSAGE ASKING FOR THE PLAYER NAME====================
                        do {
                                msgCenterScreen = DisplayManager.messageBox("Please enter your character's name:",
                                                meanY, meanX);

                                anwser += scanner.nextLine();
                                hero.setCharacterName(anwser);
                                msgCenterScreen.clean();
                        } while (anwser.isEmpty());
                        // ==================MESSAGE ASKING FOR THE PLAYER COUNTRY====================
                        do {
                                msgCenterScreen = DisplayManager.messageBox("Please enter your nationality:",
                                                meanY,
                                                meanX);
                                anwser = "";
                                anwser += scanner.nextLine();
                                hero.setCountry(anwser);
                                msgCenterScreen.clean();
                        } while (anwser.isEmpty());

                        // ==================MESSAGE EXPLANING THE GAME====================
                        msgCenterScreen = DisplayManager.messageBox(
                                        "Great, " + hero.getCharacterName() + "! Here are your starting attribsutes:" +
                                                        "\n - Life Points:[100] (These will keep you alive in battles)"
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
                        msgCenterScreen = DisplayManager.messageBox(
                                        "With courage and strategy, you'll navigate through the trials ahead." +
                                                        "\nAre you ready to start" +
                                                        "\nyour journey? (" + ANSICodeManager.buildRedText("Yes")
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
                                msgCenterScreen = DisplayManager.messageBox(ANSICodeManager.buildRedText(
                                                "GAME OVER!!!\n\n\nYou've being deported to " + hero.getCountry()),
                                                meanY, meanX);

                        } else {
                                // =================THE GAME WILL BEGING PART 1=====================
                                GameCharacter mainCharacter = GameRun.hero;
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
                                while (levelCounter < monstersSlashLevels.length && hero.getCharacterLife() > 0) {
                                        monster = GameRun.monstersSlashLevels[levelCounter];

                                        // this check is to prevent the null point exception on the firt turn
                                        if (msgTitleBattleScreen != null)
                                                msgTitleBattleScreen.clean();

                                        msgTitleBattleScreen = DisplayManager.messageBox(
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
                                                        msgCenterScreen = DisplayManager.messageBox(
                                                                        "Your journey begins now,"
                                                                                        + hero.getCharacterName()
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
                                                        msgCenterScreen = DisplayManager.messageBox(
                                                                        "With " + ANSICodeManager
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
                                                                                        + ANSICodeManager.buildRedText(
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
                                                        msgCenterScreen = DisplayManager.messageBox(
                                                                        "With your Immigrational status secured," +
                                                                                        "\nyou move onto proving your skills and knowledge in a"
                                                                                        +
                                                                                        "\nCanadian educational institution."
                                                                                        +
                                                                                        "\nTest your skills against "
                                                                                        + ANSICodeManager.buildRedText(
                                                                                                        "Big Headed from College")
                                                                                        + "."
                                                                                        +
                                                                                        "\n\nPress enter to continue",

                                                                        meanY,
                                                                        (DisplayManager.width / 2) - 35, true);
                                                        break;

                                                case 3:
                                                        msgCenterScreen = DisplayManager.messageBox(
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
                                                                                        + ANSICodeManager.buildRedText(
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

                                                        msgCenterScreen = DisplayManager.messageBox(
                                                                        "Press enter to " + mainCharacter
                                                                                        .getCharacterName()
                                                                                        + "\nRoll the dices and Play " +
                                                                                        "\n\nEnter o to chose a recovery item",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40);

                                                } else {

                                                        msgCenterScreen = DisplayManager.messageBox(
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

                                                        msgCenterScreen = DisplayManager.messageBox(message,
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

                                                                msgCenterScreen = DisplayManager.messageBox(
                                                                                "Your dice roll adds "
                                                                                                + ANSICodeManager
                                                                                                                .buildRedText(
                                                                                                                                diceValue)
                                                                                                + " to your attack, \ntotaling "
                                                                                                + ANSICodeManager
                                                                                                                .buildRedText(
                                                                                                                                totalAttack)
                                                                                                + "." +
                                                                                                "\nYour attack breaks through,\n dealing  "
                                                                                                + ANSICodeManager
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

                                                                msgCenterScreen = DisplayManager.messageBox(
                                                                                monster.getCharacterName()
                                                                                                + " roll adds "
                                                                                                + ANSICodeManager
                                                                                                                .buildRedText(
                                                                                                                                diceValue)
                                                                                                + " to your attack, \ntotaling "
                                                                                                + ANSICodeManager
                                                                                                                .buildRedText(
                                                                                                                                totalAttack)
                                                                                                + ".\n"
                                                                                                +
                                                                                                "Your attack breaks through,\n dealing  "
                                                                                                + ANSICodeManager
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

                                                if (levelCounter < monstersSlashLevels.length - 2) {
                                                        // ===================PLAYER WON LEVEL 1,2 and
                                                        // 3===================
                                                        msgCenterScreen = DisplayManager.messageBox(
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
                                                } else {
                                                        // ===================PLAYER WON LEVEL 4===================
                                                        msgCenterScreen = DisplayManager.messageBox(
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
                                                                                        +
                                                                                        "\n\nPress Enter to continue....",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 40, true);
                                                }

                                                // always the main character will be the firt to play on every turn
                                                timeToMainCharacterPlay = true;

                                                // increase the level
                                                levelCounter++;

                                                scanner.nextLine();
                                                msgCenterScreen.clean();

                                        }
                                        // if the mosnter loses it goes to another turn on the next level.
                                        else {
                                                // ===================PLAYER LOOSE===================
                                                msgCenterScreen = DisplayManager.messageBox(
                                                                "Unfortunately, your life points have reached 0." +
                                                                                "\n\nIt's GAME OVER." +
                                                                                "\n\n But don't lose hope; every path has its setbacks."
                                                                                +
                                                                                "\n\n Would you like to try again? (Yes/"
                                                                                + ANSICodeManager.buildRedText("No")
                                                                                + ")",
                                                                20,
                                                                (DisplayManager.width / 2) - 40);
                                                anwser = "";
                                                anwser += scanner.nextLine();
                                                msgCenterScreen.clean();

                                                if (anwser.equalsIgnoreCase("yes")) {
                                                        // reset levelCounter to first level, level 0
                                                        levelCounter = 0;

                                                        // reset recovery items
                                                        recoveryItemUsed = new boolean[] { false, false, false };

                                                        // reset life of all characters
                                                        mainCharacter.setCharacterLife(
                                                                        GameCharacter.defaultAmontOfLife);
                                                        for (GameCharacter character : GameRun.monstersSlashLevels) {
                                                                character.setCharacterLife(
                                                                                GameCharacter.defaultAmontOfLife);
                                                        }
                                                }
                                        }
                                }
                        }

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

}
