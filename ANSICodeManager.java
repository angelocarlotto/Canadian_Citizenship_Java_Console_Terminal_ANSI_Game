/**
 * Guide to thhe ANSI Code https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797 used to develop this class
 */
public class ANSICodeManager {
    public static char escCode = '\033';
    public static String backgroundColorDefault = "48;5;255m";

    public static void printOneSpaceWithDefaultBackGroundColor() {
        System.out.printf("%c[%s", ANSICodeManager.escCode, ANSICodeManager.backgroundColorDefault);// cursor 0x0
        ANSICodeManager.printOneSpace();
    }

    public static void printOneSpace() {
        System.out.printf(" ");// cursor 0x0
    }
    
    public static void setCursorToHomePosition() {
        System.out.printf("%c[H", ANSICodeManager.escCode);// cursor 0x0
    }

    public static void cleanWholeTerminal() {

        System.out.printf("%c[2J", ANSICodeManager.escCode);// clean terminal
    }

    public static void disableCursorIndicator() {
        System.out.printf("%c[?25l", ANSICodeManager.escCode);// disable cursor
    }

    public static void enableCursorIndicator() {
        System.out.printf("%c[?25h", ANSICodeManager.escCode);// disable cursor
    }

    public static void resetAllStyleAndColorMode() {
        System.out.printf("%c[0m", ANSICodeManager.escCode);// disable cursor

    }

    public static void writeRedText(String text) {
        System.out.printf("%c[31m%s", ANSICodeManager.escCode, text);// disable cursor
        ANSICodeManager.resetAllStyleAndColorMode();
    }

    public static void setCustomCursorPosition(int x, int y) {
        System.out.printf("%c[%d;%dH", ANSICodeManager.escCode, y, x);// disable cursor
    }

    public static void set256TextColor(int colorIndex) {
        System.out.printf("%c[38;5;%dm", ANSICodeManager.escCode, colorIndex);// disable cursor
    }

    public static void set256TextBackGroundColor(int colorIndex) {
        System.out.printf("%c[48;5;%dm", ANSICodeManager.escCode, colorIndex);// disable cursor
    }

    public static void setRGBTextBackGroundColor(int r, int g, int b) {
        System.out.printf("%c[48;2;%d;%d;%dm", ANSICodeManager.escCode, r, g, b);// disable cursor
    }
}
