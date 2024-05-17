package PixLab.PixLab.src;

import PixLab.PixLab.src.Picture;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class
 * methods.  Uncomment the methods and the code
 * in the main to test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {
    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("src/PixLab/PixLab/images/beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    public static void testKeepOnlyBlue() {
        Picture beach = new Picture("src/PixLab/PixLab/images/beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }

    public static void testKeepOnlyRed() {
        Picture beach = new Picture("src/PixLab/PixLab/images/beach.jpg");
        beach.explore();
        beach.keepOnlyRed();
        beach.explore();
    }

    public static void testKeepOnlyGreen() {
        Picture beach = new Picture("src/PixLab/PixLab/images/beach.jpg");
        beach.explore();
        beach.keepOnlyGreen();
        beach.explore();
    }


    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorVertical() {
        Picture caterpillar = new Picture("src/PixLab/PixLab/images/caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    public static void testMirrorDiag() {
        Picture caterpillar = new Picture("src/PixLab/PixLab/images/caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorDiagonal();
        caterpillar.explore();
    }

    public static void testFixUnderwater() {
        Picture water = new Picture("src/PixLab/PixLab/images/water.jpg");
        water.explore();
        water.bumpRed();
        water.edgeDetection(40);
        water.explore();

    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("src/PixLab/PixLab/images/temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.removeMan();
        temple.explore();
    }

    /**
     * Method to test the collage method
     */
    public static void testCollage() {
        Picture canvas = new Picture("src/PixLab/PixLab/images/640x480.jpg");
        canvas.createCollage();
        canvas.explore();
    }

    public static void motorcylce() {
        Picture red = new Picture("src/PixLab/PixLab/images/redMotorcycle.jpg");
        Picture blue = new Picture("src/PixLab/PixLab/images/blueMotorcycle.jpg");
        red.motorcycleCopy(blue);
        red.explore();
    }

    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("src/PixLab/PixLab/images/swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /**
     * Main method for testing.  Every class can have a main
     * method in Java
     */
    public static void main(String[] args) {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run
//    testZeroBlue();
//    testKeepOnlyBlue();
//    testKeepOnlyRed();
//    testKeepOnlyGreen();
//    testNegate();
        //testGrayscale();
//    testFixUnderwater();
//    testMirrorVertical();
        motorcylce();
//        testMirrorDiag();
//    testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}
