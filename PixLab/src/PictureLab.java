package PixLab.PixLab.src;

import java.awt.*;

public class PictureLab {

    public static String PATH = "src/PixLab/PixLab/images/";

    public static void main(String[] args) {
        colorfulNoise("gorge.jpg", 255 /*Grain 255 for maximum noise!*/);
        chromakey("redwood2.jpg", "blue-mark.jpg");
        maskColor("ferrari.jpg", 100, Color.RED);
        Picture p = stenographyEncode("femaleLionAndHall.jpg");
        stenographyDecode(p);
    }

    /**
     * Encodes a message (msg.jpg) in a given image.
     *
     * @param fileName the image to encode a message in
     * @return the picture with the encoded message
     */
    public static Picture stenographyEncode(String fileName) {
        Picture p = new Picture(PATH + fileName);
        Picture m = new Picture(PATH + "msg.jpg");

        Pixel[][] pixels2D = p.getPixels2D();
        for (Pixel[] pixels : pixels2D) {
            for (Pixel pixel : pixels) {
                if (pixel.getBlue() % 2 == 0) {
                    pixel.setBlue(pixel.getBlue() + 1);
                }
            }
        }

        Pixel[][] mPixels = m.getPixels2D();
        for (int i = 0; i < mPixels.length; i++) {
            for (int j = 0; j < mPixels[i].length; j++) {
                if (mPixels[i][j].colorDistance(Color.BLACK) < 50) {
                    pixels2D[i][j].setBlue(pixels2D[i][j].getBlue() + 1);
                }
            }
        }

        p.explore();
        return p;
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

        int smallerLength = Math.min(oldPixels2D.length, bgPixels2D.length);
        int smallerCols = Math.min(oldPixels2D[0].length, bgPixels2D[0].length);

        for (int x = 0; x < smallerLength; x++) {
            for (int y = 0; y < smallerCols; y++) {
                Pixel oldPixel = oldPixels2D[x][y];

                if (oldPixel.colorDistance(Color.GREEN) < 50) {
                    Pixel newPixel = bgPixels2D[x][y];
                    oldPixel.setColor(newPixel.getColor());
                }
            }
        }

        picture.explore();
        background.explore();
    }


    /**
     * Applies a mask over all pixels in the range of the given color.
     *
     * @param file     the file of the image.
     * @param distance parameter used to tune method; smaller methods mean it is more specific.
     * @param color    the color to find
     */
    public static void maskColor(String file, int distance, Color color) {
        Picture picture = new Picture(PATH + file);
        picture.explore();

        for (Pixel[] pixels : picture.getPixels2D()) {
            for (Pixel pixel : pixels) {
                if (pixel.colorDistance(color) > distance) {
                    pixel.setColor(new Color(0, 0, 0));
                } else {
                    pixel.setColor(new Color(255, 255, 255));
                }
            }

        }
        picture.explore();
    }

    /**
     * Applies colorful noise to an image file by randomly altering the RGB values of each pixel within a specified grain range.
     *
     * @param file  the name of the image file located in the PATH" directory
     * @param grain the range within which the random noise will alter the RGB values of each pixel; must be a positive integer
     */
    public static void colorfulNoise(String file, int grain) {
        Picture picture = new Picture(PATH + file);
        picture.explore();

        for (Pixel[] pixels : picture.getPixels2D()) {
            for (Pixel pixel : pixels) {

                int r = pixel.getRed();
                int g = pixel.getGreen();
                int b = pixel.getBlue();

                int rn = (int) (Math.random() * grain) + (r - grain / 2);
                int gn = (int) (Math.random() * grain) + (g - grain / 2);
                int bn = (int) (Math.random() * grain) + (b - grain / 2);

                rn = Math.min(255, rn);
                gn = Math.min(255, gn);
                bn = Math.min(255, bn);

                rn = Math.max(0, rn);
                bn = Math.max(0, bn);
                gn = Math.max(0, gn);

                pixel.setColor(new Color(rn, gn, bn));
            }
        }
        picture.explore();
    }


}
