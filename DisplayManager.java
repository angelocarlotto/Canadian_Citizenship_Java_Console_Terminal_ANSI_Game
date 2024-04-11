
import java.util.Random;

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

    /**
     * this method initiate the terminal by appling the background color and positioning the cursor.
     * @param height height of the screen
     * @param width widht of the screen
     */
    public static void init(int height, int width) {

        DisplayManager.height = height;
        DisplayManager.width = width;
        ANSICodeManager.setCursorToHomePosition();
        ANSICodeManager.cleanWholeTerminal();
        ANSICodeManager.disableCursorIndicator();

        for (int i = 0; i < DisplayManager.height; i++) {
            for (int j = 0; j < DisplayManager.width; j++) {
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
            }
            System.out.println();
        }

    }

    public static void print(String msg, int timesEmptySpace) {
        print(msg, timesEmptySpace, false);
    }

    public static void print(String msg, int timesEmptySpace, boolean withDelayOnTyping) {

        if (withDelayOnTyping) {
            for (int i = 0; i < msg.length(); i++) {
                System.out.print(msg.charAt(i));

                try {

                    Thread.sleep(random.nextInt(1, maxTimeToDigitDelay));
                } catch (Exception e) {
                }
            }
        } else {
            System.out.print(msg);

        }
    }

    public static void print(int timesEmptySpace) {
        print(" ", timesEmptySpace);
    }

    public static void print(String msg) {
        print(msg, 1);
    }

    public static void printAtPosition(String msg, int x, int y) {
        ANSICodeManager.setCustomCursorPosition(x, y);
        print(msg, 1);
    }

    public static void printCharacter_RGB(int[][][] arrayCharacter40x25, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < arrayCharacter40x25.length; row++) {
            ANSICodeManager.setCustomCursorPosition(postX, postY + row);
            for (int col = 0; col < arrayCharacter40x25[0].length; col++) {
                if (arrayCharacter40x25[row][col][3] == 0) {
                    ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
                } else {
                    ANSICodeManager.setRGBTextBackGroundColor(arrayCharacter40x25[row][col][0],
                            arrayCharacter40x25[row][col][1], arrayCharacter40x25[row][col][2]);
                    ANSICodeManager.printOneSpace();
                }
            }
            System.out.println();
        }

    }

    public static void cleanArea_RGB(int height, int width, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < height; row++) {

            // System.out.printf("%c[%d;%df", DisplayManager.escCode, postY + row, postX);
            ANSICodeManager.setCustomCursorPosition(postX, postY + row);
            for (int col = 0; col < width; col++) {
                // System.out.printf("%c[48;5;241m ", DisplayManager.escCode);
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
            }
            System.out.println();
        }

    }

    public static void cleanArea_RGB_v2(int height, int width, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX + width;
        for (int row = 0; row < height; row++) {

            ANSICodeManager.setCustomCursorPosition(postX, postY + row);
            for (int col = width - 1; col < width; col++) {
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
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
     * @param withDelay
     * @return Message object that can be clened later on the game
     */
    public static MessageBox messageBox(String msg, int startPointY, int startPointX, boolean withDelay) {
        MessageBox msgBOx = new MessageBox(startPointX, startPointY, 4, msg);
        msgBOx.draw(withDelay);
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
    public static MessageBox messageBox(String msg, int startPointY, int startPointX) {
        return messageBox(msg, startPointY, startPointX, false);
    }

}
