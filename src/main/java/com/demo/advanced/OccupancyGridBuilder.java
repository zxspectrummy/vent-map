package com.demo.advanced;


import com.demo.common.InputDataParser;
import com.demo.base.LineSegment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.demo.common.ImageUtils.saveAsImage;

public class OccupancyGridBuilder {
    static final int gridSize = 10;

    public static void main(String[] args) throws Exception {
        List<LineSegment> segments;
        segments = InputDataParser.parse(Files.readAllLines(Path.of("src/main/resources/data.txt")));
        QuadTree quadTree = new QuadTree(new Region(0, 0, gridSize, gridSize));
        quadTree.insertSegments(segments);
        saveAsImage(quadTree, gridSize, "grid_quadtree.jpg");
        //System.out.println(quadTree.search(quadTree.getArea(),2).size());

        quadTree.search(new Region(5, 4, 6, 5));
        System.out.println(quadTree.printSearchTraversePath());
    }
}