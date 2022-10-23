package raster;

public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {

        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;

        //umožní tvořit úsečky i u záporných souřadnic
        if (x1 > x2){

            int save = x1;
            x1 = x2;
            x2 = save;

        }

        //vypočítá všechny body úsečky
        for (int x = x1; x < x2; x++) {
            float y = k * x + q;
            raster.setPixel(x, Math.round(y), 0xffff00);

        }

    }
}
