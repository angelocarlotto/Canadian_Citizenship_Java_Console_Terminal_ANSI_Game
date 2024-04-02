
import java.util.Random;

public class DisplayManager {
    public static char escCode = '\033';
    public static Random random = new Random();
    public static int pointInitY = 0;
    public static int pointInitX = 0;
    public static int height = 60;
    public static int width = 200;
    public static int maxTimeToDigitDelay = 100;
    public static String backgroundColorDefault = "48;5;255m";

    public static void setCursor(int y, int x) {
        print(String.format("%c[%d;%df", escCode, y, x));
    }

    public static void resetCursos(int numRow) {
        setCursor(numRow, 2);
    }

    public static void resetCursor() {
        setCursor(2, 2);
    }

    public static void drawLineClcok() {

    }

    public static void init(){

        System.out.printf("%c[H", DisplayManager.escCode);// cursor 0x0
        System.out.printf("%c[2J", DisplayManager.escCode);//clean terminal
        System.out.printf("%c[?25l", DisplayManager.escCode);//disable cursor

        for (int i = 0; i < DisplayManager.height; i++) {
            for (int j = 0; j < DisplayManager.width; j++) {
                System.out.printf("%c[%s ", DisplayManager.escCode, DisplayManager.backgroundColorDefault);//draw a space with a background color
            }
            System.out.println();
        }

    }

    public static void drawLine(int numberLine) {
        print(ConsoleColors.RESET);

        String[] array = { ConsoleColors.BLUE_BACKGROUND, ConsoleColors.CYAN_BACKGROUND,
                ConsoleColors.GREEN_BACKGROUND, ConsoleColors.PURPLE_BACKGROUND, ConsoleColors.RED_BACKGROUND };
        print(array[random.nextInt(0, 5)]);

        setCursor(numberLine, 0);
        print(width);
    }

    public static void drawLine(String color, int numberLine) {
        print(ConsoleColors.RESET);

        print(color);
        setCursor(numberLine, 0);
        print(width);
        print(ConsoleColors.RESET);
    }

    public static void drawCollumn(int numberColumn) {
        print(ConsoleColors.RESET);
        String[] array = { ConsoleColors.BLUE_BACKGROUND, ConsoleColors.CYAN_BACKGROUND,
                ConsoleColors.GREEN_BACKGROUND, ConsoleColors.PURPLE_BACKGROUND, ConsoleColors.RED_BACKGROUND };

        drawCollumn(array[random.nextInt(0, 5)], numberColumn);

    }

    public static void drawCollumn(String color, int numberColumn) {
        print(ConsoleColors.RESET);

        print(color);
        for (int i = 0; i < height; i++) {
            setCursor(i, numberColumn);
            print();
        }

    }

    public static void cleanFrame(int rowNumber) {
        print(ConsoleColors.RESET);

        print(ConsoleColors.BLACK_BACKGROUND);
        setCursor(rowNumber, 1);
        for (int row = 1; row < height - 2; row++) {
            for (int col = 1; col < width - 2; col++) {
                print();
            }
        }
    }

    public static void cleanFrame() {
        print(ConsoleColors.RESET);

        print(ConsoleColors.BLACK_BACKGROUND);
        setCursor(1, 1);
        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                print();
            }
        }
    }

    public static void drawFrameWindow(String color) {

        print(ConsoleColors.RESET);
        print(color);
        for (int i = 0; i < width; i++) {
            setCursor(pointInitY, pointInitX + i);
            print();

            setCursor(pointInitY + height, pointInitX + i);
            print();

        }

        for (int i = 0; i < height; i++) {
            setCursor(pointInitY + i, pointInitX);
            print();

            setCursor(pointInitY + i, pointInitX + width);
            print();

        }
        print(ConsoleColors.RESET);
    }

    public static void print(String msg, int timesEmptySpace) {
        /*
         * for (int i = 0; i < msg.length(); i++) {
         * print(msg.charAt(i));
         * System.out.print(msg.charAt(i));
         * try {
         * Thread.sleep(random.nextInt(1, maxTimeToDigitDelay));
         * } catch (Exception e) {
         * }
         * }
         */
        print(msg, timesEmptySpace, false);
        // for (int i = 0; i < timesEmptySpace; i++)
        // System.out.print(msg);
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

        // for (int i = 0; i < timesEmptySpace; i++)
        // System.out.print(msg);
    }

    public static void print(int timesEmptySpace) {
        print(" ", timesEmptySpace);
    }

    public static void print() {
        String msg = " ";
        print(msg);
    }

    public static void print(String msg) {
        print(msg, 1);
    }

    public static void printCharacter_RGB(int[][][] arrayCharacter40x25, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        // System.out.printf("%c[2J", 0x1b);
        for (int row = 0; row < arrayCharacter40x25.length; row++) {
            System.out.printf("%c[%d;%df", DisplayManager.escCode, postY + row, postX);
            for (int col = 0; col < arrayCharacter40x25[0].length; col++) {
                if (arrayCharacter40x25[row][col][3] == 0) {
                    // System.out.printf("%c[1C", 0x1b);
                    System.out.printf("%c[%s ", DisplayManager.escCode, DisplayManager.backgroundColorDefault);
                } else {
                    System.out.printf("%c[48;2;%d;%d;%dm ", DisplayManager.escCode,
                            arrayCharacter40x25[row][col][0],
                            arrayCharacter40x25[row][col][1], arrayCharacter40x25[row][col][2]);
                    // System.out.printf("%3d", arrayCharacter40x25[row][col][3]);
                }
            }
            System.out.println();
        }

    }

    public static void cleanArea_RGB(int height, int width, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < height; row++) {

            System.out.printf("%c[%d;%df", DisplayManager.escCode, postY + row, postX);
            for (int col = 0; col < width; col++) {
                // System.out.printf("%c[48;5;241m ", DisplayManager.escCode);
                System.out.printf("%c[%s ", DisplayManager.escCode, DisplayManager.backgroundColorDefault);
            }
            System.out.println();
        }

    }

    public static void cleanArea_RGB_v2(int height, int width, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX+width;
        for (int row = 0; row < height; row++) {

            System.out.printf("%c[%d;%df", DisplayManager.escCode, postY + row, postX);//position of the cursor
            for (int col = width-1; col < width; col++) {
                System.out.printf("%c[%s ", DisplayManager.escCode, DisplayManager.backgroundColorDefault);
            }
            System.out.println();
        }

    }

    public static void cleanArea_RGB(int[][][] arrayCharacter40x25, int startPostY, int startPosX) {
        int postY = startPostY;
        int postX = startPosX;
        for (int row = 0; row < arrayCharacter40x25.length; row++) {

            System.out.printf("%c[%d;%df", DisplayManager.escCode, postY + row, postX);
            for (int col = 0; col < arrayCharacter40x25[0].length; col++) {
                // System.out.printf("%c[48;5;241m ", DisplayManager.escCode);
                System.out.printf("%c[%s ", DisplayManager.escCode, DisplayManager.backgroundColorDefault);
            }
            System.out.println();
        }

    }

    public static void drawBox(int startPointY, int startPointX, int lengthString) {
        print(ConsoleColors.RESET);
        int msgLength = lengthString;
        int paddingLeftOrRight = 3;
        int paddintTopOrBotton = 2;
        int legnthBorderTopAndButton = msgLength + paddingLeftOrRight * 2;
        int boxHight = 4;

        System.out.printf("%c[40m ", DisplayManager.escCode);

        // draw borders top and button of the box
        for (int i = 0; i < legnthBorderTopAndButton; i++) {
            setCursor(startPointY, startPointX + i);
            print();

            setCursor(startPointY + boxHight, startPointX + i);
            print();
        }

        for (int i = 0; i <= boxHight; i++) {

            // draw border left of the box
            setCursor(startPointY + i, startPointX);
            print();

            // draw border right of the box
            setCursor(startPointY + i, startPointX + legnthBorderTopAndButton);
            print();
        }

        setCursor(startPointY + paddintTopOrBotton, startPointX + paddingLeftOrRight);

        print(ConsoleColors.RESET);
    }

    public static void messageBox(String msg, int startPointY, int startPointX, boolean withDelay) {
        print(ConsoleColors.RESET);
        int msgLength = msg.length();
        int paddingLeftOrRight = 3;
        int paddintTopOrBotton = 2;
        int legnthBorderTopAndButton = msgLength + paddingLeftOrRight * 2;
        int boxHight = 4;

        System.out.printf("%c[40m ", DisplayManager.escCode);

        // draw borders top and button of the box
        for (int i = 0; i < legnthBorderTopAndButton; i++) {
            setCursor(startPointY, startPointX + i);
            print();

            setCursor(startPointY + boxHight, startPointX + i);
            print();
        }

        for (int i = 0; i <= boxHight; i++) {

            // draw border left of the box
            setCursor(startPointY + i, startPointX);
            print();

            // draw border right of the box
            setCursor(startPointY + i, startPointX + legnthBorderTopAndButton);
            print();
        }

        setCursor(startPointY + paddintTopOrBotton, startPointX + paddingLeftOrRight);

        print(ConsoleColors.RESET);
        System.out.printf("%c[32;47m ", DisplayManager.escCode);
        if (withDelay) {
            for (int i = 0; i < msg.length(); i++) {
                // print(msg.charAt(i));

                System.out.print(msg.charAt(i));
                try {
                    Thread.sleep(random.nextInt(1, maxTimeToDigitDelay));
                } catch (Exception e) {
                }

            }
        } else {
            print(msg);
        }
        print(ConsoleColors.RESET);
    }

    public static void messageBox(String msg, int startPointY, int startPointX) {
        messageBox(msg, pointInitY, pointInitX, true);

    }
}
