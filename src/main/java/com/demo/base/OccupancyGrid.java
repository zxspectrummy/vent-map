package com.demo.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class OccupancyGrid {
    private final Map<Point, Integer> gridMap;

    public OccupancyGrid(List<LineSegment> segments) {
        this.gridMap = new HashMap<>();
        for (LineSegment segment : segments) {
            this.insertSegment(segment);
        }
    }

    public void insertSegment(LineSegment segment) {
        for (Point point : segment.getPoints()) {
            gridMap.merge(point, 1, Integer::sum);
        }
    }

    public long getOccupiedCellCount(int threshold) {
        return gridMap.values().stream().filter(n -> n >= threshold).count();
    }

    public int getMaxOccupancies() {
        return gridMap.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getValue();
    }
}
