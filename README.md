# PictureLab
Methods by Milav and Runbier

All PictureLab methods are in `PixLab/src/PictureLab.java`

## Methods
* [X] Chromakey
* [X] Stegonagraphy
* [X] Random Color Grain
* [X] Color Masking


## Writeups

### Chromakey
The chromakey method greenscreens two images. This is the same technique used in video productions.

Our method takes two images as input: one background and one foreground. It replaces all green pixels in the foreground image (the greenscreen) with the corresponding pixels from the background image.

**Important Notes**
- **Color Distance Check**: The `colorDistance(Color.GREEN)<180` condition ensures only pixels close to green `(0,255,0)` are replaced. This threshold can be changed based on the images for optimal results.

- **Array Bounds Safety**: 
