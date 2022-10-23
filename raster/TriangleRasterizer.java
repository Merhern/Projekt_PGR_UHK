package raster;

import model.Line;
import model.Point;
import model.Polygon;

public class TriangleRasterizer {

    private LineRasterizer lineRasterizer;

    public TriangleRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {

        for (int i = 0; i < polygon.getCount(); i++) {
            if(i == 2) {
                //definujeme první spoj (bod před)
                Point point0 = polygon.getPoint(i - 2);
                //koncový spoj
                Point point1 = polygon.getPoint(i - 1);

                Point pointTop = polygon.getPoint(i);

                //spojení
                lineRasterizer.rasterize(new Line(point0.getX(), point0.getY(), point1.getX(), point1.getY()));
                lineRasterizer.rasterize(new Line(point1.getX(), point1.getY(), pointTop.getX(), pointTop.getY()));
                lineRasterizer.rasterize(new Line(pointTop.getX(), pointTop.getY(), point0.getX(), point0.getY()));


            }
        }

    }
}
