import java.util.Scanner;

/**
 * This class is responsable to run the game
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class GameRun {
        private  static Scanner scanner = new Scanner(System.in);
        private static DisplayManager displayManager = new DisplayManager(60, 200, scanner);

        /**
         * @param args
         */
        public static void main(String[] args) {

                displayManager.runGame();
        }

}
