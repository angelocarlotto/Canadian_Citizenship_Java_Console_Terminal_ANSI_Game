import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This classe is the blue print to all box messages printed on the terminal.
 * Also, this class holds methods that will properly help either draw the
 * message on the screen and also clean the previous occuped area on the screen.
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class MessageBox {
    /**
     * this property holds the X position on the screen to position the message box
     */
    private int x;
    /**
     * this property holds the Y position on the screen to position the message box
     */
    private int y;

    /**
     * the height of the box that will surrend the text message
     */
    private int height;
    /**
     * The message that will be displayed
     */
    private String message;

    /**
     * this represent the amont time will random to display each character on the screen
     */
    private static int maxTimeToDigitDelay = 50;
    private static Random random = new Random();
    private int paddingLeftOrRight = 3;
    private int paddintTopOrBotton = 2;

    public MessageBox(int x, int y, int height, String message) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.message = message;
    }

    /**
     * Return the message attibuted to the object
     * 
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message attributed to the object
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method is responsable to clean the area previus used to display the
     * message
     */
    public void clean() {

        String textWithoutANSI = getStringWithOutANSIScapeCode();

        String[] msgsLines = textWithoutANSI.split("\n");

        int maxLength = 0;
        for (String linha : msgsLines) {
            if (linha.length() > maxLength) {
                maxLength = linha.length();
            }
        }

        int legnthBorderTopAndButton = maxLength + paddingLeftOrRight * 2;
        ANSICodeManager.set256TextBackGroundColor(40);
        height += msgsLines.length;
        for (int iy = y; iy <= height + y; iy++) {
            for (int ix = x; ix <= legnthBorderTopAndButton + x; ix++) {
                ANSICodeManager.setCustomCursorPosition(ix, iy);
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
            }
        }
    }

    /**
     * Method responsable to efectively draw a proper message on the screen
     * 
     * @param withDelay when set to true, the message will be displayed one char at
     *                  a time with a random delay between each character
     */
    public void draw(boolean withDelay) {

        ANSICodeManager.resetAllStyleAndColorMode();

        String textWithoutANSI = getStringWithOutANSIScapeCode();

        /**
         * this code give the ability to write texts with the special character to break lines
         */
        String[] msgsLines2 = textWithoutANSI.split("\n");
        int maxLength = 0;
        for (String linha : msgsLines2) {
            if (linha.length() > maxLength) {
                maxLength = linha.length();
            }
        }

        String[] msgsLines = this.message.split("\n");
        int legnthBorderTopAndButton = maxLength + paddingLeftOrRight * 2;

        // draw borders top and button of the box
        height += msgsLines.length;
        for (int i = x; i < legnthBorderTopAndButton + x; i++) {
            ANSICodeManager.setCustomCursorPosition(i, y);
            ANSICodeManager.printOneSpace();

            ANSICodeManager.setCustomCursorPosition(i, y + height);
            ANSICodeManager.printOneSpace();
        }

        for (int i = y; i <= height + y; i++) {

            // draw border left of the box
            ANSICodeManager.setCustomCursorPosition(x, i);
            ANSICodeManager.printOneSpace();

            // draw border right of the box
            ANSICodeManager.setCustomCursorPosition(x + legnthBorderTopAndButton, i);
            ANSICodeManager.printOneSpace();
        }

        ANSICodeManager.resetAllStyleAndColorMode();
        if (withDelay) {
            for (int l = 0; l < msgsLines.length; l++) {

                ANSICodeManager.setCustomCursorPosition(x + paddingLeftOrRight, y + paddintTopOrBotton + l);

                for (int i = 0; i < msgsLines[l].length(); i++) {

                    System.out.print(msgsLines[l].charAt(i));
                    try {
                        Thread.sleep(random.nextInt(1, maxTimeToDigitDelay));
                    } catch (Exception e) {
                    }

                }
            }
        } else {
            for (int l = 0; l < msgsLines.length; l++) {

                ANSICodeManager.setCustomCursorPosition(x + paddingLeftOrRight, y + paddintTopOrBotton + l);
                ANSICodeManager.print(msgsLines[l]);
            }
        }
        ANSICodeManager.setCustomCursorPosition(x + paddingLeftOrRight, y + paddintTopOrBotton + msgsLines.length);
        ANSICodeManager.resetAllStyleAndColorMode();

    }

    /**
     * Will remove all ANSI Scape codes from the string.
     * @return String without containing ANSI scape codes as long it wont be printed
     *         on the screen it can be count on the lenght of the message
     */
    private String getStringWithOutANSIScapeCode() {
        String textWithANSI = this.message;

        Pattern pattern = Pattern.compile("\033\\[[0-9;]+m");
        Matcher matcher = pattern.matcher(textWithANSI);
        String textWithoutANSI = matcher.replaceAll("");
        return textWithoutANSI;
    }
}
