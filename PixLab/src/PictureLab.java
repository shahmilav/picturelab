package PixLab.PixLab.src;

import java.awt.*;

public class PictureLab {
    public static void main(String[] args) {
        grainy("milav_backyard.jpg", 250);
        chromakey(new Picture("src/PixLab/PixLab/images/moon-surface.jpg"));
    }

    public static void chromakey(Picture newPic) {
        Picture pic = new Picture("src/PixLab/PixLab/images/blue-mark.jpg");

        Pixel[][] toPixels = pic.getPixels2D();
        Pixel[][] fromPixels = newPic.getPixels2D();
        for (int row = 0; row < pic.getHeight(); row++) {
            for (int col = 0; col < pic.getWidth(); col++) {

                Pixel newP = toPixels[row][col];
                if (newP.getBlue() > newP.getRed()) {
                    Pixel oldP = fromPixels[row][col];
                    newP.setColor(oldP.getColor());
                }
            }
        }

        pic.explore();
        newPic.explore();

    }

    public static void grainy(String file, int grain) {
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
