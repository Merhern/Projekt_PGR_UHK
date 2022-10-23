package raster;

import model.Line;
import model.Point;
import model.Polygon;

public class PolygonRasterizer {

    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {

        for (int i = 1; i < polygon.getCount(); i++){
            //ignoruj polygon o velikost 1

            //definujeme první spoj (bod před)

            Point point0 = polygon.getPoint(i - 1);
            //koncový spoj
            Point point1 = polygon.getPoint(i);

            //spojení
            lineRasterizer.rasterize(new Line(point0.getX(), point0.getY(), point1.getX(), point1.getY()));
            lineRasterizer.rasterize(new Line(point0.getX(), point0.getY(), point1.getX(), point1.getY()));

        }

        //spojení s začátkem polygonu
        Point point0 = polygon.getPoint(0);
        Point point1 = polygon.getPoint(polygon.getCount() - 1);
        lineRasterizer.rasterize(new Line(point0.getX(), point0.getY(), point1.getX(), point1.getY()));


    }
}
