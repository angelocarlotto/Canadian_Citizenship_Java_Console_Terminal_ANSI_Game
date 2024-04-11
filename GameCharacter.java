/**
 * THIS IS THE BLUEPRINT CLASS FOR THE MAIN CHARACTER WHICH IS GOING TO BE PARTIALLY CUSTOMIZED BY THE USER
* IN THIS CLASS ALL ATTRIBUTES WILL BE DECLARED AND METHODS WILL BE SET FOR BATTLES 
* ALSO RANDOM VALUES WILL BE ADDED TO ATTRIBUTES IN ORDER TO DEVELOP COMBAT MOMENTS
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
    private int characterLife=30;
    private int characterAttack=20;
    private int characterDefense=8;
    private int initX;
    private int initY;
    private String fileName1;
    private String fileName2;
    private String fileName3Defeated;
    private String fileName4Won;
    private String priorBattleMessage;
    private String endBattleMessage;

   

    private int[][][] image1 = new int[][][] {};
    private int[][][] image2 = new int[][][] {};
    private int[][][] image3Defeated = new int[][][] {};
    private int[][][] image4Won = new int[][][] {};

    private static String defaultDataArrayFolder = "arrayData/";

    public GameCharacter(String fileName1, String fileName2, String fileName3,String fileName4) {
        this("", fileName1, fileName2, fileName3,fileName4);
    }

    public GameCharacter(String characterName, String fileName1, String fileName2, String fileName3,String fileName4) {

        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        this.fileName3Defeated = fileName3;
        this.fileName4Won = fileName4;
        this.characterName = characterName;
    }
    public int getCharacterLife() {
        return characterLife;
    }

    public void setCharacterLife(int characterLife) {
        this.characterLife = characterLife;
    }
    public String getPriorBattleMessage() {
        return priorBattleMessage;
    }

    public void setPriorBattleMessage(String initialMessage) {
        this.priorBattleMessage = initialMessage;
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

    public void loadImagesCharacter() {
        this.image1 = GameCharacter.readFileIntoArray(fileName1);
        this.image2 = GameCharacter.readFileIntoArray(fileName2);
        this.image3Defeated = GameCharacter.readFileIntoArray(fileName3Defeated);
        this.image4Won = GameCharacter.readFileIntoArray(fileName4Won);
    }

    public void drawCharacter1OnTheScreen(int x, int y) {
        initY = y;
        initX = x;
        this.drawCharacter1OnTheScreen();
    }

    public int decreaseLife(int attacke) {
        int loseLife = characterDefense - attacke;
        characterLife += loseLife > 0 ? 0 : loseLife;

        return loseLife;
    }

    public void drawCharacter1OnTheScreen(int[][][] randomArray){

        DisplayManager.printCharacter_RGB(randomArray, initY, initX);
        this.characterLife=this.characterLife>0?this.characterLife:0;
        DisplayManager.printAtPosition(
                getCharacterName() + ":(" + String.valueOf(characterLife) + ")" + "#".repeat(this.characterLife / 10),
                initX, initY);
    }
    public void drawCharacter1OnTheScreen() {
        Random randomAux = new Random();
        int[][][] randomArray = characterLife <= 0 ? image3Defeated : randomAux.nextBoolean() ? image1 : image2;
        drawCharacter1OnTheScreen(randomArray);
    }

    public void eraseCharacterFromScreen() {
        DisplayManager.cleanArea_RGB(40, 25, initY, initX);
    }

    public void updatePosition(int x, int y) {
        eraseCharacterFromScreen();
        initX = x;
        initY = y;
        drawCharacter1OnTheScreen();

    }

    public void updateWinningPosition() {
        eraseCharacterFromScreen();
        drawCharacter1OnTheScreen(image4Won);

    }

    public void updatePosition2(int x, int y) {
        DisplayManager.cleanArea_RGB_v2(40, 25, initY, initX);
        initX = x;
        initY = y;
        DisplayManager.printCharacter_RGB(image2, initY, initX);

    }

    public static void saveArrayDataIntoFile(String fileName, int[][][] array) {
        try {
            FileWriter output = new FileWriter(GameCharacter.defaultDataArrayFolder + fileName);

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

    public int getInitX() {
        return initX;
    }
}
