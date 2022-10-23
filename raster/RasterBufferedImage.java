package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {
    private BufferedImage img;

    public RasterBufferedImage(final int width, final int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        img.setRGB(x, y, color);
    }

    @Override
    public int getPixel(int x, int y) {

        //vrátí hodnotu RGB u zadaného bodu v souřadnici pixelů
        return img.getRGB(x, y);
    }

    @Override
    public void clear() {
        img = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,img.getWidth(), img.getHeight());

    }

    public BufferedImage getImg() {
        return img;
    }
}
