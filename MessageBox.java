import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author shabnam, geraldo, henrique, angelo
 */
public class MessageBox {
    private int x;
    private int y;
    private int height;
    private int width;
    private String message;
   

    private static int maxTimeToDigitDelay = 50;
    private static Random random = new Random();
    private int paddingLeftOrRight = 3;
    private int paddintTopOrBotton = 2;

    public MessageBox(int x, int y, int height, int width, String message) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void clean() {
        
        String textWithANSI = this.message;//"you step onto the \033[31mpath\033[0m of trials, your resolve ";

        Pattern pattern = Pattern.compile("\033\\[[0-9;]+m");
        Matcher matcher = pattern.matcher(textWithANSI);
        String textWithoutANSI = matcher.replaceAll("");

        
        String[] msgsLines = textWithoutANSI.split("\n");


        int maxLength = 0;
        for (String linha : msgsLines) {
            if (linha.length() > maxLength) {
                maxLength = linha.length();
            }
        }
        int msgLength = maxLength;// this.message.length();

        int legnthBorderTopAndButton = msgLength + paddingLeftOrRight * 2;
        ANSICodeManager.set256TextBackGroundColor(40);
        for (int iy = y; iy <= height + y; iy++) {
            for (int ix = x; ix <= legnthBorderTopAndButton + x; ix++) {
                ANSICodeManager.setCustomCursorPosition(ix, iy);
                ANSICodeManager.printOneSpaceWithDefaultBackGroundColor();
            }
        }
    }
    public void draw(){
        draw(false);
    }
    public void draw(boolean withDelay) {

        ANSICodeManager.resetAllStyleAndColorMode();

        String textWithANSI = this.message;

        Pattern pattern = Pattern.compile("\033\\[[0-9;]+m");
        Matcher matcher = pattern.matcher(textWithANSI);
        String textWithoutANSI = matcher.replaceAll("");

        
        String[] msgsLines2 = textWithoutANSI.split("\n");
        int maxLength = 0;
        for (String linha : msgsLines2) {
            if (linha.length() > maxLength) {
                maxLength = linha.length();
            }
        }
        
        String[] msgsLines = this.message.split("\n");
        int msgLength = maxLength;// this.message.length();
        int legnthBorderTopAndButton = msgLength + paddingLeftOrRight *2;

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

                for (int i = 0; i < msgLength; i++) {

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
                DisplayManager.print(msgsLines[l]);
            }
        }
        ANSICodeManager.setCustomCursorPosition(x + paddingLeftOrRight, y + paddintTopOrBotton + msgsLines.length);
        ANSICodeManager.resetAllStyleAndColorMode();

    }
}
