
import java.util.Random;
import java.util.Scanner;

public class GameRun {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static GameCharacter hero = new GameCharacter("Imigrante Joe", 20, DisplayManager.height - 42, "hero1_hero_frame1_RGB.txt",
            "hero1_hero_frame2_RGB.txt", new int[] { 1, 2, 3, 4 });
    static GameCharacter mosnter1 = new GameCharacter("CIC", 200 - 30 * 4, 10,
            "monster1_monster_frame1_RGB.txt", "monster1_monster_frame2_RGB.txt", new int[] { 1 });
    static GameCharacter mosnter2 = new GameCharacter("Imigration", 200 - 30 * 3, 10,
            "monster2_monster_frame1_RGB.txt", "monster2_monster_frame2_RGB.txt", new int[] { 2 });
    static GameCharacter mosnter3 = new GameCharacter("College", 200 - 30 * 2, 10,
            "monster3_monster_frame1_RGB.txt", "monster3_monster_frame2_RGB.txt", new int[] { 3 });
    static GameCharacter mosnter4 = new GameCharacter("Citzenship", 200 - 30, 10,
            "monster4_monster_frame1_RGB.txt", "monster4_monster_frame2_RGB.txt", new int[] { 4 });

    static GameCharacter[] chacters = { hero, mosnter1, mosnter2, mosnter3, mosnter4 };

    public static void main(String[] args) {

        try {

            DisplayManager.init();

            for (GameCharacter x : chacters)
                x.loadImagesCharacter();


            Random x = new Random();

            int columnCount = mosnter4.getInitX();
            while (true) {
                try {
                    GameCharacter charPrev = chacters[x.nextInt(0, 5)];
                    charPrev.updatePosition(x.nextInt(0, 175), x.nextInt(10, DisplayManager.height - 38));
                    Thread.sleep(500);
                    charPrev.eraseFromScreen();
                    if (columnCount > 0)
                        columnCount--;
                    else
                        columnCount = DisplayManager.width - 25;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (

        Exception x) {
            System.out.println(x.getMessage());
        }
    }

}
