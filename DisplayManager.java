
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
     * this method initiate the terminal by appling the background color and
     * positioning the cursor.
     * 
     * @param height height of the screen
     * @param width  widht of the screen
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

    /**
     * This method helps to print the image stored on a 3x3 array on the
     * screen/terminal
     * 
     * @param arrayCharacter40x25
     * @param startPostY
     * @param startPosX
     */
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

    /**
     * this method helps clean the region previous used to draw something opn the
     * terminal/screen
     * 
     * @param height
     * @param width
     * @param startPostY
     * @param startPosX
     */
    public static void cleanArea_RGB(int height, int width, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < height; row++) {

            ANSICodeManager.setCustomCursorPosition(postX, postY + row);
            for (int col = 0; col < width; col++) {
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
     * @param withDelay if treu the message will emulate someone typing on the screen
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
