package images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ReadPixels {

    public static void main(String[] args) throws IOException {
        // Specify the path to your PNG image file
        String filePath = "images/monster4_1.png";

        // Read the image
        BufferedImage image = ImageIO.read(new File(filePath));

        // Get image width and height
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a 2D int array to store pixel values (0-255)
        int[][][] pixelValues = new int[height][height][5];

        // Loop through each pixel and read its color value (ARGB)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the ARGB color value of the pixel
                int rgb = image.getRGB(x, y);

                // Extract alpha (transparency), red, green, and blue values (0-255)
                int alpha = (rgb >> 24) & 0xff;
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                int grayscaleValue = (red + green + blue) / 3;

                // Store the grayscale value in the 2D array
                pixelValues[y][x][0] = red;
                pixelValues[y][x][1] = green;
                pixelValues[y][x][2] = blue;
                pixelValues[y][x][3] = alpha;
                pixelValues[y][x][4] = grayscaleValue;
            }
        }

        // Access and use the pixel values in the pixelValues array
        // ... (e.g., print grayscale values, manipulate pixels, etc.)

        System.out.println("Image dimensions: " + width + "x" + height);

        // Print the first 5x5 grayscale values (optional)
        for (int y = 0; y < height; y++) {
            System.out.print("{");
            for (int x = 0; x < width; x++) {
                // System.out.print(pixelValues[y][x]!=0?"*":" ");
                // System.out.print(pixelValues[y][x] + ",");
                System.out.printf("{%3d,%3d,%3d,%3d,%3d} ", pixelValues[y][x][0], pixelValues[y][x][1],
                        pixelValues[y][x][2],
                        pixelValues[y][x][3], pixelValues[y][x][4]);
                if (x < width - 1)
                    System.out.printf(",");
                // System.out.printf("%3d,", pixelValues[y][x][4]);

            }
            
            System.out.println("}");

            if (y < height - 1)
                System.out.printf(",");
        }

    }

    public static void main33(String[] args) throws IOException {
        // Specify the path to your PNG image file
        String filePath = "Game/monster5.png";

        // Read the image
        BufferedImage image = ImageIO.read(new File(filePath));

        // Get image width and height
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a 2D int array to store pixel values (0-255)
        int[][] pixelValues = new int[height][width];

        // Loop through each pixel and read its color value (ARGB)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the ARGB color value of the pixel
                int rgb = image.getRGB(x, y);

                // Extract alpha (transparency), red, green, and blue values (0-255)
                int alpha = (rgb >> 24) & 0xff;
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                // Combine red, green, and blue values into a single integer (grayscale)
                // Alternatively, you can store each color value (alpha, red, green, blue)
                // in separate arrays depending on your needs.
                int grayscaleValue = (red + green + blue) / 3;

                // Store the grayscale value in the 2D array
                pixelValues[y][x] = grayscaleValue;
            }
        }

        // Access and use the pixel values in the pixelValues array
        // ... (e.g., print grayscale values, manipulate pixels, etc.)

        System.out.println("Image dimensions: " + width + "x" + height);

        // Print the first 5x5 grayscale values (optional)
        for (int y = 0; y < height; y++) {
            System.out.print("{");
            for (int x = 0; x < width; x++) {
                // System.out.print(pixelValues[y][x]!=0?"*":" ");
                // System.out.print(pixelValues[y][x] + ",");
                System.out.printf("%3d, ", pixelValues[y][x]);

            }
            System.out.println("},");
        }

    }
}