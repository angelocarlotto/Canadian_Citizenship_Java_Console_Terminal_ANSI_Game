
/**
* this class will represent each character on the game.
* @author Angelo Carlotto, Geraldo Beiro, Henrique, Shabnam.
* Date: Mar 16th, 2024.
* Time: 9:30PM
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class GameCharacter {

    private String characterName;
    private String country;
    public static int defaultAmontOfLife = 30;
    private int characterLife = defaultAmontOfLife;
    private int characterAttack = 20;
    private int characterDefense = 8;
    /**
     * holds the initial X position of the character on the screen
     */
    private int initX;
    /**
     * holds the initial Y position of the character on the screen
     */
    private int initY;

    /**
     * The file names for each file
     */
    private String fileName1;
    private String fileName2;
    private String fileName3Defeated;
    private String fileName4Won;

    /**
     * this array 3x3 will hold the first image of the character
     */
    private int[][][] image1 = new int[][][] {};

    /**
     * this array 3x3 will hold the second image of the character
     */
    private int[][][] image2 = new int[][][] {};

    /**
     * this array 3x3 will hold the defeted image of the character
     */
    private int[][][] image3Defeated = new int[][][] {};

    /**
     * this array 3x3 will hold the winning image of the character
     */
    private int[][][] image4Won = new int[][][] {};

    /**
     * where the program can find the files imges for the characters
     */
    private static String defaultDataArrayFolder = "arrayData/";

    public GameCharacter(String fileName1, String fileName2, String fileName3, String fileName4) {
        this("", fileName1, fileName2, fileName3, fileName4);
    }

    public GameCharacter(String characterName, String fileName1, String fileName2, String fileName3, String fileName4) {

        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.fileName3Defeated = fileName3;
        this.fileName4Won = fileName4;
        this.characterName = characterName;
    }

    /**
     * @return int
     */
    public int getCharacterLife() {
        return characterLife;
    }

    /**
     * @param characterLife
     */
    public void setCharacterLife(int characterLife) {
        this.characterLife = characterLife;
    }

    public String getCharacterName() {
        return "\033[31m" + characterName + "\033[0m";
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterAttack() {
        return characterAttack;
    }

    public void setCharacterAttack(int characterAttack) {
        this.characterAttack = characterAttack;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method helps to load the images stored on .txt files into arrayx 3x3
     */
    public void loadImagesCharacter() {
        this.image1 = GameCharacter.readFileIntoArray(fileName1);
        this.image2 = GameCharacter.readFileIntoArray(fileName2);
        this.image3Defeated = GameCharacter.readFileIntoArray(fileName3Defeated);
        this.image4Won = GameCharacter.readFileIntoArray(fileName4Won);
    }

    /**
     * This method draw on the screen the character on the especific x,y position
     * 
     * @param x
     * @param y
     */
    public void drawCharacter1OnTheScreen(int x, int y) {
        initY = y;
        initX = x;
        this.drawCharacter1OnTheScreen();
    }

    /**
     * This method decrease the character's life based on the oponent attack
     * 
     * @param attacke
     * @return returns the ammount of life lose
     */
    public int decreaseLife(int attacke) {
        int loseLife = characterDefense - attacke;
        characterLife += loseLife > 0 ? 0 : loseLife;

        return loseLife;
    }

    /**
     * This method draw on the screen the character on previous defined x,y position
     * 
     * @param x x position on the screen
     * @param y y position on the screen
     */
    public void drawCharacter1OnTheScreen(int[][][] randomArray) {

        DisplayManager.printCharacter_RGB(randomArray, initY, initX);
        this.characterLife = this.characterLife > 0 ? this.characterLife : 0;
        DisplayManager.printAtPosition(
                getCharacterName() + ":(" + String.valueOf(characterLife) + ")" + "#".repeat(this.characterLife / 10),
                initX, initY);
    }

    /**
     * This method draw on the screen the character on previous defined x,y position
     * .
     * This method use a random object to random display the image between the image
     * 1 and 2 to give moviment.
     * If the life of the character is below zero, the image display will be
     * defeated one.
     * 
     * @param x x position on the screen
     * @param y y position on the screen
     */
    public void drawCharacter1OnTheScreen() {
        Random randomAux = new Random();
        int[][][] randomArray = characterLife <= 0 ? image3Defeated : randomAux.nextBoolean() ? image1 : image2;
        drawCharacter1OnTheScreen(randomArray);
    }

    /**
     * this method clean the screen exactly on the previous reaginon it was draw
     */
    public void eraseCharacterFromScreen() {
        DisplayManager.cleanArea_RGB(40, 25, initY, initX);
    }

    /**
     * change the position of the draw character on the screen
     * 
     * @param x
     * @param y
     */
    public void updatePosition(int x, int y) {
        eraseCharacterFromScreen();
        initX = x;
        initY = y;
        drawCharacter1OnTheScreen();

    }

    /**
     * draw the character on thw winning position
     */
    public void updateWinningPosition() {
        eraseCharacterFromScreen();
        drawCharacter1OnTheScreen(image4Won);

    }

    /**
     * this method load the files content into arrayx 3x3
     * 
     * @param fileName
     * @return
     */
    private static int[][][] readFileIntoArray(String fileName) {

        String filePath = GameCharacter.defaultDataArrayFolder + fileName;
        try {
            FileReader input = new FileReader(filePath);
            int cx = -1;
            int lengthContent = 0;
            do {
                cx = input.read();
                lengthContent++;
            } while (cx > 0);

            input.close();

            char[] contentFile = new char[lengthContent];
            input = new FileReader(filePath);
            input.read(contentFile);
            input.close();
            int totalLines = 0;

            for (char c : contentFile) {
                if (c == '\n')
                    totalLines++;
            }

            String[] linesData = new String[totalLines + 1];
            int countLineAux = 0;
            linesData[countLineAux] = "";
            for (char c : contentFile) {

                if (c == '\n') {
                    countLineAux++;
                    linesData[countLineAux] = "";
                } else
                    linesData[countLineAux] += c;
            }

            int totalColumns = linesData[0].split("#").length;

            int[][][] newArray = new int[totalLines][totalColumns][5];

            for (int i = 0; i < totalLines; i++) {
                String[] xxx = linesData[i].split("#");
                for (int j = 0; j < xxx.length; j++) {
                    String fff = xxx[j].replace(" ", "");
                    String[] values = fff.split(",");
                    newArray[i][j][0] = Integer.valueOf(values[0]);
                    newArray[i][j][1] = Integer.valueOf(values[1]);
                    newArray[i][j][2] = Integer.valueOf(values[2]);
                    newArray[i][j][3] = Integer.valueOf(values[3]);
                    newArray[i][j][4] = Integer.valueOf(values[4]);

                }
            }

            return newArray;

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

}
