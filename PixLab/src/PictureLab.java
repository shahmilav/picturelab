import java.awt.*;

public class PictureLab {
    public static String PATH = "PixLab/images/";

    public static void main(String[] args) {
        colorfulNoise("SPIDERMAN DOPE (1).jpg", 210, false);
        colorfulNoise("SPIDERMAN DOPE (2).jpg", 210, false);
        colorfulNoise("SPIDERMAN DOPE (3).jpg", 210, false);
        colorfulNoise("SPIDERMAN DOPE (4).jpg", 210, false);
        colorfulNoise("SPIDERMAN DOPE (5).jpg", 210, true);
        chromakey("forest.jpg", "monkes.jpg");
        colorMask("ferrari.jpg", 100, Color.RED);
        Picture encoded = stenographyEncode("femaleLionAndHall.jpg");
        stenographyDecode(encoded);
    }

    /**
     * Encodes a message (msg.jpg) in a given image.
     *
     * @param fileName the image to encode a message in
     * @return the picture with the encoded message
     */
    public static Picture stenographyEncode(String fileName) {
        Picture picture = new Picture(PATH + fileName);
        Picture message = new Picture(PATH + "msg.jpg");

        Pixel[][] msgPixels = message.getPixels2D();
        Pixel[][] pixels2D = picture.getPixels2D();

        for (Pixel[] pixels : pixels2D) {
            for (Pixel pixel : pixels) {
                if (pixel.getBlue() % 2 == 0) {
                    pixel.setBlue(pixel.getBlue() + 1);
                }
            }
        }

        for (int i = 0; i < msgPixels.length; i++) {
            for (int j = 0; j < msgPixels[i].length; j++) {
                if (msgPixels[i][j].colorDistance(Color.BLACK) < 50) {
                    pixels2D[i][j].setBlue(pixels2D[i][j].getBlue() + 1);
                }
            }
        }

        picture.explore();
        return picture; // return the encoded picture
    }


    /**
     * Decodes an image with a hidden message.
     *
     * @param picture the picture to decode.
     */
    public static void stenographyDecode(Picture picture) {
        Pixel[][] pixels2D = picture.getPixels2D();
        for (Pixel[] pixels : pixels2D) {
            for (Pixel pixel : pixels) {

                // Check if the blue in the pixel is odd. This means the message is not here and we color it black.
                if (pixel.getBlue() % 2 == 1) {
                    pixel.setColor(Color.BLACK);
                } else {
                    // otherwise, the pixel is part of the message and is colored red.
                    pixel.setColor(Color.RED);
                }
            }
        }

        picture.explore();
    }


    /**
     * Replaces a green-screen with a background in an image with a background.
     *
     * @param backgroundFile file for the image background
     * @param foregroundFile file for the green-screen image
     */
    public static void chromakey(String backgroundFile, String foregroundFile) {
        Picture picture = new Picture(PATH + foregroundFile);
        Picture background = new Picture(PATH + backgroundFile);

        Pixel[][] oldPixels2D = picture.getPixels2D();
        Pixel[][] bgPixels2D = background.getPixels2D();

        // Ensure we use the smaller array to avoid ArrayIndexOutOfBounds
        int smallerLength = Math.min(oldPixels2D.length, bgPixels2D.length);
        int smallerCols = Math.min(oldPixels2D[0].length, bgPixels2D[0].length);

        for (int x = 0; x < smallerLength; x++) {
            for (int y = 0; y < smallerCols; y++) {
                Pixel oldPixel = oldPixels2D[x][y];

                if (oldPixel.colorDistance(Color.GREEN) < 180) {
                    Pixel newPixel = bgPixels2D[x][y];
                    oldPixel.setColor(newPixel.getColor());
                }
            }
        }

        picture.explore();
    }


    /**
     * Applies a mask over all pixels in the range of the given color.
     *
     * @param file     the file of the image.
     * @param distance parameter used to tune method; smaller methods mean it is more specific.
     * @param color    the color to find
     */
    public static void colorMask(String file, int distance, Color color) {

        Picture picture = new Picture(PATH + file);

        for (Pixel[] pixels : picture.getPixels2D()) {
            for (Pixel pixel : pixels) {
                if (pixel.colorDistance(color) > distance) {
                    // pixel color is not in range
                    pixel.setColor(new Color(0, 0, 0));
                } else {
                    // pixel color is in range
                    pixel.setColor(new Color(255, 255, 255));
                }
            }
        }
        picture.explore();
    }

    /**
     * Applies colorful noise to an image file by randomly altering the RGB values of each pixel within a specified grain range.
     *
     * @param file  the name of the image file
     * @param grain the range within which the random noise will alter the RGB values of each pixel
     */
    public static void colorfulNoise(String file, int grain, boolean greenscreen) {
        Picture picture = new Picture(PATH + file);

        for (Pixel[] pixels : picture.getPixels2D()) {
            for (Pixel pixel : pixels) {
                if (greenscreen) if (pixel.colorDistance(Color.GREEN) < 190) continue;

                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();

                int rn = (int) (Math.random() * grain) + (r - grain / 2);
                int gn = (int) (Math.random() * grain) + (g - grain / 2);
                int bn = (int) (Math.random() * grain) + (b - grain / 2);


                // Ensure the RGB values remain between 0 and 255
                rn = Math.max(0, Math.min(255, rn));
                bn = Math.max(0, Math.min(255, bn));
                gn = Math.max(0, Math.min(255, gn));

                pixel.setColor(new Color(rn, gn, bn));
            }
        }
//        picture.write(PATH + "/GENERATED" + file.split(" ")[2]);

        picture.explore();
    }
}
