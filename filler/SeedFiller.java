package filler;

import raster.Raster;
import raster.RasterBufferedImage;

public class SeedFiller implements Filler {

    private final int x, y;
    private final int fillColor, backroundColor;

    private Raster raster;

    public SeedFiller(int x, int y, int fillColor, int backroundColor, Raster raster) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.backroundColor = backroundColor;
        this.raster = raster;
    }

    @Override
    public void fill() {

        seedFill(x, y);

    }

    public void seedFill(int x, int y) {

        //načti barvu z pixele
        int pixelColor = raster.getPixel(x, y);

        //pokud barva pixelu není barva pozadí --> konec
        if (backroundColor != pixelColor)
            return;

        //obarvím pixel
        raster.setPixel(x, y, fillColor);

        //rekurzivně zavolám seedfill pro 4 sousedy
        seedFill(x,y+1);
        seedFill(x,y-1);
        seedFill(x-1,y);
        seedFill(x+1,y);

    }

}
