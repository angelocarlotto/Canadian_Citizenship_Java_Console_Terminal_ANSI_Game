/*THIS IS THE BLUEPRINT CLASS FOR THE MAIN CHARACTER WHICH IS GOING TO BE PARTIALLY CUSTOMIZED BY THE USER
IN THIS CLASS ALL ATTRIBUTES WILL BE DECLARED AND METHODS WILL BE SET FOR BATTLES
ALSO RANDOM VALUES WILL BE ADDED TO ATTRIBUTES IN ORDER TO DEVELOP COMBAT MOMENTS

Authors: Angelo Carlotto, Geraldo Beiro, Henrique, Shabnam.
Date: Mar 16th, 2024.
Time: 9:30PM
*/

import java.io.FileReader;
import java.io.FileWriter;

public class GameCharacter {

    private String characterName;
    private String country;

    private int characterLife;
    private int characterStrength;
    private int characterDefense;

    private int initX;

    private int initY;
    private String fileName1;
    private String fileName2;
    private int[][][] image1;
    private int[][][] image2;

    private int[] level;

    public GameCharacter(int initX, int initY, String fileName1, String fileName2) {

        this.initX = initX;
        this.initY = initY;
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.characterLife = 100;
        this.characterStrength = 30;
        this.characterDefense = 10;
        this.level = new int[] {};
        image2 = new int[][][] {};
        image1 = new int[][][] {};

    }

    public GameCharacter(String characterName, int initX, int initY, String fileName1, String fileName2, int[] level) {

        this.initX = initX;
        this.initY = initY;
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.characterName = characterName;
        this.characterLife = 100;
        this.characterStrength = 30;
        this.characterDefense = 10;
        this.level = level;
        image2 = new int[][][] {};
        image1 = new int[][][] {};

    }

    public void loadImagesCharacter() {
        this.image1 = GameCharacter.readFileIntoArray(fileName1);
        this.image2 = GameCharacter.readFileIntoArray(fileName2);
    }

    public void draw1() {

        DisplayManager.printCharacter_RGB(image1, initY, initX);

    }

    public void draw2() {

        DisplayManager.printCharacter_RGB(image2, initY, initX);

    }

    public void eraseFromScreen() {
        DisplayManager.cleanArea_RGB(40, 25, initY, initX);
    }

    public void updatePosition(int x, int y) {
        // DisplayManager.cleanArea_RGB(40, 25, initY, initX);
        eraseFromScreen();
        initX = x;
        initY = y;
        DisplayManager.printCharacter_RGB(image1, initY, initX);
    }

    public void updatePosition2(int x, int y) {
        DisplayManager.cleanArea_RGB_v2(40, 25, initY, initX);
        initX = x;
        initY = y;
        DisplayManager.printCharacter_RGB(image2, initY, initX);
    }

    public static void saveArrayIntoFile(String fileName, int[][][] array) {
        try {
            FileWriter output = new FileWriter(fileName);

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    output.write("");
                    output.write(String.valueOf(array[i][j][0]) + ", ");
                    output.write(String.valueOf(array[i][j][1]) + ", ");
                    output.write(String.valueOf(array[i][j][2]) + ", ");
                    output.write(String.valueOf(array[i][j][3]) + ", ");
                    output.write(String.valueOf(array[i][j][4]));
                    if (j == array[0].length - 1)
                        output.write(" ");
                    else
                        output.write("# ");
                }
                output.write("\n");
            }
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static int[][][] readFileIntoArray(String fileName) {

        try {
            FileReader input = new FileReader(fileName);
            int cx = -1;
            int lengthContent = 0;
            do {
                cx = input.read();
                lengthContent++;
            } while (cx > 0);

            input.close();

            char[] contentFile = new char[lengthContent];
            input = new FileReader(fileName);
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

    public int getInitX() {
        return initX;
    }

    public void setInitX(int initX) {
        this.initX = initX;
    }

    public int getInitY() {
        return initY;
    }

    public void setInitY(int initY) {
        this.initY = initY;
    }

    public int[][][] getImage1() {
        return image1;
    }

    public void setImage1(int[][][] image1) {
        this.image1 = image1;
    }

    public int[][][] getImage2() {
        return image2;
    }

    public void setImage2(int[][][] image2) {
        this.image2 = image2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // setters
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setCharacterLife(int characterLife) {
        this.characterLife = characterLife;
    }

    public void setCharacterStrength(int characterStrength) {
        this.characterStrength = characterStrength;
    }

    public void setCharacterDefense(int characterDefense) {
        this.characterDefense = characterDefense;
    }

    // getters
    public String getCharacterName() {
        return characterName;
    }

    public int getCharacterLife() {
        return characterLife;
    }

    public int getCharacterStrength() {
        return characterStrength;
    }

    public int getCharacterDefense() {
        return characterDefense;
    }

}
