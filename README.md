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
Our chromakey method replaces all pixels close to the color green `Colors.GREEN=(0,255,0)`. In specific, it finds all pixels in `foregroundImage` with a color distance <180 from `Colors.GREEN`, and replaces those with the corresponding pixel in `backgroundImage`. This creates a greenscreen effect.
