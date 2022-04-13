package com.demo.common;

import com.demo.base.OccupancyGrid;
import com.demo.base.Point;
import com.demo.advanced.Node;
import com.demo.advanced.QuadTree;
import com.demo.advanced.Region;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ImageUtils {
    static final int gridLineWidth = 8;
    public static final int GRID_STEP = 32;

    public static void saveAsImage(OccupancyGrid map, int gridSize, String filename) throws IOException {
        int shadeStep = 255 / (map.getMaxOccupancies() + 1);
        BufferedImage imageBuff = new BufferedImage(gridSize * GRID_STEP, gridSize * GRID_STEP, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().fillRect(0, 0, imageBuff.getWidth(), imageBuff.getHeight());
        for (Point point : map.getGridMap().keySet()) {
            int ventCount = map.getGridMap().get(point);
            drawGridCell(imageBuff, point, ventCount, shadeStep);
        }
        drawGrid(imageBuff, gridSize);
        saveImageAsFile(imageBuff, filename);
    }

    public static void saveAsImage(QuadTree quadTree, int gridSize, String filename) throws IOException {
        List<Node> nodes = quadTree.search(quadTree.getArea());
        BufferedImage imageBuff = new BufferedImage(gridSize * GRID_STEP+gridLineWidth, gridSize * GRID_STEP+gridLineWidth, BufferedImage.TYPE_INT_RGB);
        int maxVentCount = nodes.stream().map(Node::getPointCount).max(Comparator.naturalOrder()).orElse(0);
        int shadeStep = 255 / (maxVentCount + 1);
        imageBuff.getGraphics().fillRect(0, 0, imageBuff.getWidth(), imageBuff.getHeight());
        for (Node node : nodes) {
            drawGridCell(imageBuff, node.getPoint(), node.getPointCount(), shadeStep);
        }
        drawGrid(imageBuff, gridSize);
        drawTreeGrid(imageBuff, quadTree);
        saveImageAsFile(imageBuff, filename);
    }

    private static void saveImageAsFile(BufferedImage image, String filename) throws IOException {
        File output = new File(filename);
        ImageIO.write(image, "jpg", output);
    }

    private static void drawGridCell(BufferedImage image, Point point, int ventCount, int shadeStep) {
        Graphics graphics = image.getGraphics();
        int grayShade = 255 - ventCount * shadeStep;
        Color color = new Color(grayShade, grayShade, grayShade);
        graphics.setColor(color);
        graphics.fillRect(point.getX() * GRID_STEP, point.getY() * GRID_STEP, GRID_STEP, GRID_STEP);
    }

    private static void drawGrid(BufferedImage image, int gridSize) {
        java.awt.Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0,0, image.getWidth()-1, image.getHeight()-1);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.drawRect(i * GRID_STEP, j * GRID_STEP, (i + 1) * GRID_STEP, (j + 1) * GRID_STEP);
            }
        }
    }

    private static void drawTreeGrid(BufferedImage image, QuadTree quadTree) {
        java.awt.Graphics g = image.getGraphics();
        g.setColor(new Color(255,0,0,80));
        ((Graphics2D) g).setStroke(new BasicStroke(gridLineWidth));
        List<Region> regions = quadTree.searchRegions();
        for (Region region: regions) {
            g.drawRect(region.getX1()* GRID_STEP+gridLineWidth/2, region.getY1()*GRID_STEP+gridLineWidth/2, region.getWidth()*GRID_STEP, region.getHeight()*GRID_STEP);
        }
    }
}
