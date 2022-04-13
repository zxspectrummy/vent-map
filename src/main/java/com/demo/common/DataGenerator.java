package com.demo.common;

import com.demo.base.LineSegment;
import com.demo.base.Point;

import java.util.*;

public class DataGenerator {

    public static List<LineSegment> generate(int ridgeCount, int minRidgeSegmentCount, int maxRidgeSegmentCount, int size) {
        List<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < ridgeCount; i++) {
            int pointCount = new Random().nextInt(maxRidgeSegmentCount - minRidgeSegmentCount) + minRidgeSegmentCount;
            List<Point> points = new ArrayList<>();
            int start = new Random().nextInt(size - 1);
            for (int j = 0; j < pointCount; j++) {
                int randomDelta = new Random().nextInt(size / 20);
                points.add(new Point(new Random().nextInt(size - 1), start + randomDelta));
            }
            Collections.sort(points);
            for (int j = 0; j < points.size() - 1; j++) {
                //connect two dots with two segments
                segments.add(new LineSegment(points.get(j).getX(), points.get(j).getY(), points.get(j + 1).getX(), points.get(j).getY()));
                segments.add(new LineSegment(points.get(j+1).getX(), points.get(j).getY(), points.get(j+1).getX(), points.get(j + 1).getY()));
            }
        }
        return segments;
    }
}
