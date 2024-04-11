/**
 * Guide to thhe ANSI Code
 * https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797 used to develop
 * this class
 * 
 * This class translate all the ANSI code to a Java form
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class ANSICodeManager {
    public static char escCode = '\033';
    public static String backgroundColorDefault = "48;5;255m";

    /**
     * Helps to menage the screen. This method will print an empty space whose background color is the default color.
     */
    public static void printOneSpaceWithDefaultBackGroundColor() {
        System.out.printf("%c[%s", ANSICodeManager.escCode, ANSICodeManager.backgroundColorDefault);// cursor 0x0
        ANSICodeManager.printOneSpace();
    }

     /**
     * Helps to menage the screen. This method will print an empty space.
     */
    public static void printOneSpace() {
        System.out.printf(" ");// cursor 0x0
    }

    /**
     * Will set the position of the cursor to the position 0x0
     */
    public static void setCursorToHomePosition() {
        System.out.printf("%c[H", ANSICodeManager.escCode);// cursor 0x0
    }

    /**
     * Will clean the whole terminal
     */
    public static void cleanWholeTerminal() {

        System.out.printf("%c[2J", ANSICodeManager.escCode);// clean terminal
    }

    /**
     * This method helpt tunr the cursos invisible. Useful when we just want to draw something on the screen.
     */
    public static void disableCursorIndicator() {
        System.out.printf("%c[?25l", ANSICodeManager.escCode);// disable cursor
    }

    /**
     * This method helpt tunr the cursos visible. Good when we espect th user enter some information.
     */
    public static void enableCursorIndicator() {
        System.out.printf("%c[?25h", ANSICodeManager.escCode);// enable cursor
    }

    /**
     * This makes sure any formating code previous used will be disconsidered from this point on.
     */
    public static void resetAllStyleAndColorMode() {
        System.out.printf("%c[0m", ANSICodeManager.escCode);

    }

    
    /** 
     * Helps build a string with color Red
     * @param text a number
     * @return String reciived as parameter added the ANSI Scape Code to make it be display RED on the terminal
     */
    public static String buildRedText(int text) {
        return buildRedText(String.valueOf(text));
    }
    
     /** 
     * Helps build a string with color Red
     * @param text a message
     * @return String reciived as parameter added the ANSI Scape Code to make it be display RED on the terminal
     */
    public static String buildRedText(String text) {
        StringBuilder builder = new StringBuilder();
        builder.append(ANSICodeManager.escCode);
        builder.append("[31m");
        builder.append(text);
        builder.append(ANSICodeManager.escCode);
        builder.append("[0m");
       
        return builder.toString();
    }

    
    /** 
     * Set the cursosr to aa especific position on the terminal. Helps to draw.
     * @param x
     * @param y
     */
    public static void setCustomCursorPosition(int x, int y) {
        System.out.printf("%c[%d;%dH", ANSICodeManager.escCode, y, x);
    }

    
    /** 
     * Set the color of the text to a especific color whose value vary from 0 to 256 which is Gray Scale.
     * @param colorIndex
     */
    public static void set256TextColor(int colorIndex) {
        System.out.printf("%c[38;5;%dm", ANSICodeManager.escCode, colorIndex);
    }

    /**
     * Set the backgroundcolor of the text to a especific color whose value vary from 0 to 256  which is Gray Scale.
     * @param colorIndex
     */
    public static void set256TextBackGroundColor(int colorIndex) {
        System.out.printf("%c[48;5;%dm", ANSICodeManager.escCode, colorIndex);
    }

    /**
     * This method help to set the background color using the RGB color, much more accuaried . But, there is some terminal that this technic/method are not supported.
     * @param r
     * @param g
     * @param b
     */
    public static void setRGBTextBackGroundColor(int r, int g, int b) {
        System.out.printf("%c[48;2;%d;%d;%dm", ANSICodeManager.escCode, r, g, b);
    }
}
