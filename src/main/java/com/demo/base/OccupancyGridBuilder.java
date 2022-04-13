package com.demo.base;

import com.demo.common.InputDataParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.demo.common.ImageUtils.saveAsImage;

public class OccupancyGridBuilder {
    static final int gridSize = 10;

    public static void main(String[] args) throws Exception {
        List<LineSegment> segments;
        segments = InputDataParser.parse(Files.readAllLines(Path.of("src/main/resources/data.txt")));
        OccupancyGrid map = new OccupancyGrid(segments);
        System.out.println(map.getOccupiedCellCount(2));
        saveAsImage(map, gridSize, "grid_hashmap.jpg");
    }
}