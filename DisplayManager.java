
import java.util.Random;

public class DisplayManager {
    public static Random random = new Random();
    public static int pointInitY = 0;
    public static int pointInitX = 0;
    public static int height = 30;
    public static int width = 100;
    public static int maxTimeToDigitDelay = 100;

    public static void init(int height, int width) {

        DisplayManager.height=height;
        DisplayManager.width=width;
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

    public static void drawLine(int numberLine) {
        ANSICodeManager.resetAllStyleAndColorMode();

        String[] array = { ConsoleColors.BLUE_BACKGROUND, ConsoleColors.CYAN_BACKGROUND,
                ConsoleColors.GREEN_BACKGROUND, ConsoleColors.PURPLE_BACKGROUND, ConsoleColors.RED_BACKGROUND };
        print(array[random.nextInt(0, 5)]);

        ANSICodeManager.setCustomCursorPosition(0, numberLine);
        print(width);
    }

    public static void drawLine(String color, int numberLine) {
        // print(ConsoleColors.RESET);
        ANSICodeManager.resetAllStyleAndColorMode();
        print(color);
        ANSICodeManager.setCustomCursorPosition(0, numberLine);
        print(width);
        ANSICodeManager.resetAllStyleAndColorMode();
    }

    public static void drawCollumn(int numberColumn) {
        ANSICodeManager.resetAllStyleAndColorMode();
        String[] array = { ConsoleColors.BLUE_BACKGROUND, ConsoleColors.CYAN_BACKGROUND,
                ConsoleColors.GREEN_BACKGROUND, ConsoleColors.PURPLE_BACKGROUND, ConsoleColors.RED_BACKGROUND };

        drawCollumn(array[random.nextInt(0, 5)], numberColumn);

    }

    public static void drawCollumn(String color, int numberColumn) {
        ANSICodeManager.resetAllStyleAndColorMode();

        print(color);
        for (int i = 0; i < height; i++) {
            // setCursor(i, numberColumn);
            ANSICodeManager.setCustomCursorPosition(numberColumn, i);
            ANSICodeManager.printOneSpace();
        }

    }

    public static void cleanFrame(int rowNumber) {
        ANSICodeManager.resetAllStyleAndColorMode();

        print(ConsoleColors.BLACK_BACKGROUND);
        // setCursor(rowNumber, 1);
        ANSICodeManager.setCustomCursorPosition(1, rowNumber);
        for (int row = 1; row < height - 2; row++) {
            for (int col = 1; col < width - 2; col++) {
                ANSICodeManager.printOneSpace();
            }
        }
    }

    public static void cleanFrame() {
        ANSICodeManager.resetAllStyleAndColorMode();

        print(ConsoleColors.BLACK_BACKGROUND);
        ANSICodeManager.setCustomCursorPosition(1, 1);
        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                ANSICodeManager.printOneSpace();
            }
        }
    }

    public static void drawFrameWindow(String color) {

        ANSICodeManager.resetAllStyleAndColorMode();
        print(color);
        for (int i = 0; i < width; i++) {
            ANSICodeManager.setCustomCursorPosition(pointInitX + i, pointInitY);
            // print();
            ANSICodeManager.printOneSpace();

            ANSICodeManager.setCustomCursorPosition(pointInitX + i, pointInitY + height);
            ANSICodeManager.printOneSpace();

        }

        for (int i = 0; i < height; i++) {
            ANSICodeManager.setCustomCursorPosition(pointInitX, pointInitY + i);
            // print();
            ANSICodeManager.printOneSpace();

            ANSICodeManager.setCustomCursorPosition(pointInitX + width, pointInitY + i);
            // print();
            ANSICodeManager.printOneSpace();

        }
        ANSICodeManager.resetAllStyleAndColorMode();
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

    public static void printAtPosition(String msg,int x, int y) {
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

    public static void cleanArea_RGB(int[][][] arrayCharacter40x25, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < arrayCharacter40x25.length; row++) {

            ANSICodeManager.setCustomCursorPosition(postX, postY + row);
            for (int col = 0; col < arrayCharacter40x25[0].length; col++) {
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
            }
            System.out.println();
        }

    }

    public static void drawBox(int startPointY, int startPointX, int lengthString) {
        ANSICodeManager.resetAllStyleAndColorMode();
        int msgLength = lengthString;
        int paddingLeftOrRight = 3;
        int paddintTopOrBotton = 2;
        int legnthBorderTopAndButton = msgLength + paddingLeftOrRight * 2;
        int boxHight = 4;

        System.out.printf("%c[40m ", ANSICodeManager.escCode);

        // draw borders top and button of the box
        for (int i = 0; i < legnthBorderTopAndButton; i++) {
            ANSICodeManager.setCustomCursorPosition(startPointX + i, startPointY);
            ANSICodeManager.printOneSpace();

            ANSICodeManager.setCustomCursorPosition(startPointX + i, startPointY + boxHight);
            ANSICodeManager.printOneSpace();
        }

        for (int i = 0; i <= boxHight; i++) {

            ANSICodeManager.setCustomCursorPosition(startPointX, startPointY + i);
            ANSICodeManager.printOneSpace();

            ANSICodeManager.setCustomCursorPosition(startPointX + legnthBorderTopAndButton, startPointY + i);
            ANSICodeManager.printOneSpace();
        }

        ANSICodeManager.setCustomCursorPosition(startPointX + paddingLeftOrRight, startPointY + paddintTopOrBotton);

        ANSICodeManager.resetAllStyleAndColorMode();
    }
    public static MessageBox messageBox(String msg, int startPointY, int startPointX,boolean withDelay) {
        MessageBox msgBOx=new MessageBox(startPointX, startPointY, 4, startPointX, msg);
        msgBOx.draw(withDelay);
        return msgBOx;
    }
    public static MessageBox messageBox(String msg, int startPointY, int startPointX) {
       return messageBox(msg, startPointY, startPointX, false);
    }

    
}
