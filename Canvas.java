import filler.Filler;
import filler.SeedFiller;
import model.Line;
import model.Point;
import model.Polygon;
import raster.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Canvas {
    private final JFrame frame;
    private final JPanel panel;
    private final Raster raster;
    private final LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;
    private final TriangleRasterizer triangleRasterizer;
    //lze měnit barvu s šířkou a výškou
    int fillColor;

    private SeedFiller seedFill;

    public Canvas(int width, int height, int fillColor)
    {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("PGR - Krejzl");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new LineRasterizerGraphics(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        triangleRasterizer = new TriangleRasterizer(lineRasterizer);

        Polygon polygon = new Polygon();

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(((RasterBufferedImage)raster).getImg(), 0,0, null);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

                raster.clear();
                if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
                    polygon.addPoint(point);
                }

                polygonRasterizer.rasterize(polygon);

                if (mouseEvent.getButton() == MouseEvent.BUTTON3){

                    Filler seedFiller = new SeedFiller(mouseEvent.getX(), mouseEvent.getY(), fillColor, Color.black.getRGB(), raster);
                    seedFiller.fill();

                }

                panel.repaint();
            }

        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent c) {
                raster.clear();
                Polygon polygon = new Polygon();
                panel.repaint();
            }
        } );

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent t) {
                Polygon triangle = new Polygon();

                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {

                        raster.clear();
                        if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
                            Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
                            triangle.addPoint(point);
                        }

                        triangleRasterizer.rasterize(triangle);

                        if (mouseEvent.getButton() == MouseEvent.BUTTON3){

                            Filler seedFiller = new SeedFiller(mouseEvent.getX(), mouseEvent.getY(), 0x00ff00, Color.black.getRGB(), raster);
                            seedFiller.fill();

                        }

                        panel.repaint();
                    }

                });

            }
        } );

/*        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);

                raster.clear();

                Line line = new Line(
                        width / 2, height / 2,
                        mouseEvent.getX(),
                        mouseEvent.getY()
                );
                lineRasterizer.rasterize(line);

                panel.repaint();
            }
        });*/

    }

    public void start(){

        raster.clear();
        panel.repaint();

    }
}
