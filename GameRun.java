
import java.util.Random;
import java.util.Scanner;

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

        static GameCharacter[] monstersSlashLevels = { mosnter1, mosnter2, mosnter3, mosnter4, mosnter1, mosnter2 };

        public static void main(String[] args) {

                try {

                        DisplayManager.init(60, 200);

                        hero.loadImagesCharacter();
                        for (GameCharacter x : monstersSlashLevels)
                                x.loadImagesCharacter();

                        ANSICodeManager.enableCursorIndicator();
                        MessageBox msg = DisplayManager.messageBox("Please enter your character's name:",
                                        DisplayManager.height / 2, DisplayManager.width / 2);

                        String anwser = "";
                        anwser += scanner.nextLine();
                        hero.setCharacterName(anwser);
                        msg.clean();
                        msg = DisplayManager.messageBox("Please enter your nationality:", DisplayManager.height / 2,
                                        DisplayManager.width / 2);

                        anwser = "";
                        anwser += scanner.nextLine();
                        hero.setCountry(anwser);
                        msg.clean();

                        msg = DisplayManager.messageBox(
                                        "Great, " + hero.getCharacterName() + "! Here are your starting attribsutes:" +
                                                        "\n - Life Points:[100] (These will keep you alive in battles)"
                                                        +
                                                        "\n - Attack: [10] (Your standard attack strength)" +
                                                        "\n - Defense: [8] (Your ability to resist attacks)" +
                                                        "\n - Recovery Items: " +
                                                        "\n    Voluntary Job (restores 10 points)" +
                                                        "\n    Part-time Job (restores 20 points)" +
                                                        "\n    Fulltime Job (restores 30 points)" +
                                                        "\n" +
                                                        "\nPress enter to continue",
                                        DisplayManager.height / 2, DisplayManager.width / 2);
                        anwser = "";
                        anwser += scanner.nextLine();
                        msg.clean();
                        msg = DisplayManager.messageBox(
                                        "With courage and strategy, you'll navigate through the trials ahead." +
                                                        "\nAre you ready to start" +
                                                        "\nyour journey? (\033[4;31mYes\033[0m/No)",
                                        DisplayManager.height / 2, DisplayManager.width / 2);
                        anwser = "";
                        anwser += scanner.nextLine();
                        msg.clean();

                        if (anwser.equalsIgnoreCase("no")) {

                                msg = DisplayManager.messageBox(
                                                "\033[31mGAME OVER\n\n\nYou've being deported to " + hero.getCountry()
                                                                + "\033[0m",
                                                DisplayManager.height / 2, DisplayManager.width / 2);

                        } else {

                                GameCharacter imigrante = GameRun.hero;
                                GameCharacter monster;
                                int level = 0;

                                boolean timeToHeroPlay = true;
                                int heroPositionY = 60 - 40;
                                int heroPositionX = 30;
                                int monsterPositionY = 10;
                                int monsterPositionX = 150;

                                while (level < monstersSlashLevels.length) {
                                        monster = GameRun.monstersSlashLevels[level];
                                        msg = DisplayManager.messageBox(
                                                        "Battle " + String.valueOf(level + 1) + "/"
                                                                        + String.valueOf(
                                                                                        monstersSlashLevels.length),
                                                        4,
                                                        DisplayManager.width / 2);
                                        imigrante.updatePosition(heroPositionX, heroPositionY);
                                        monster.updatePosition(monsterPositionX, monsterPositionY);

                                        msg = DisplayManager.messageBox(
                                                        "Your journey begins now," + hero.getCharacterName() + "!" +
                                                                        "\nAs you step onto the path of trials, your resolve to become\n a Canadian citizen will be tested."
                                                                        +
                                                                        "\nAhead lies your first challenge, It's time to prove your strength.\n\nCIC Wolf stands in your way",
                                                        DisplayManager.height / 2, (DisplayManager.width / 2) - 35);

                                        anwser += scanner.nextLine();
                                        msg.clean();

                                        do {

                                                if (timeToHeroPlay) {

                                                        msg = DisplayManager.messageBox(
                                                                        "Press enter to " + imigrante.getCharacterName()
                                                                                        + " Play",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 10);

                                                } else {

                                                        msg = DisplayManager.messageBox(
                                                                        "Press enter to " + monster.getCharacterName()
                                                                                        + " Play",
                                                                        20,
                                                                        (DisplayManager.width / 2) - 10);

                                                }
                                                anwser = "";
                                                anwser += scanner.nextLine();

                                                if (timeToHeroPlay) {

                                                        monster.decreaseLife(random.nextInt(0,
                                                                        imigrante.getCharacterAttack()));
                                                } else {

                                                        imigrante.decreaseLife(random.nextInt(0,
                                                                        monster.getCharacterAttack()));
                                                }

                                                imigrante.updatePosition(heroPositionX, heroPositionY);
                                                monster.updatePosition(monsterPositionX, monsterPositionY);

                                                timeToHeroPlay = !timeToHeroPlay;

                                                msg.clean();

                                        } while (monster.getCharacterLife() > 0 && imigrante.getCharacterLife() > 0);
                                        // change of level or game orver
                                        if (imigrante.getCharacterLife() > 0) {
                                                imigrante.updateWinningPosition();
                                        } else {
                                                msg = DisplayManager.messageBox(
                                                                "GAME OVER",
                                                                20,
                                                                (DisplayManager.width / 2) - 10);

                                        }
                                        timeToHeroPlay = true;
                                        anwser = "";
                                        anwser += scanner.nextLine();
                                        level++;
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
                        System.out.println(x.getMessage());
                }
        }

}
