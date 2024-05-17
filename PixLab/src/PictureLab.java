package PixLab.PixLab.src;

import java.awt.*;

public class PictureLab {
    public static void main(String[] args) {
        randomImageNoise("gorge.jpg", 250);
        chromakey(new Picture("src/PixLab/PixLab/images/moon-surface.jpg"));
    }

    public static void chromakey(Picture background) {
        Picture pic = new Picture("src/PixLab/PixLab/images/blue-mark.jpg");

        Pixel[][] oldPixels2D = pic.getPixels2D();
        Pixel[][] bgPixels2D = background.getPixels2D();
        for (int x = 0; x < pic.getHeight(); x++) {
            for (int y = 0; y < pic.getWidth(); y++) {

                Pixel oldPixel = oldPixels2D[x][y];
                if (oldPixel.getBlue() > oldPixel.getRed()) {
                    Pixel newPixel = bgPixels2D[x][y];
                    oldPixel.setColor(newPixel.getColor());
                }
            }
        }

        pic.explore();
        background.explore();
    }

    public static void randomImageNoise(String file, int grain) {
        Picture p = new Picture("src/PixLab/PixLab/images/" + file);
        p.explore();

        Pixel[][] pixels2D = p.getPixels2D();
        for (Pixel[] pixels : pixels2D) {
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
        p.explore();
    }
}
