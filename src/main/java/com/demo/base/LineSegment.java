package com.demo.base;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class LineSegment {
    private Point start;
    private Point end;

    public List<Point> getPoints() {
        return points;
    }

    private List<Point> points;

    public LineSegment(Point a, Point b) {
        Point[] points = {a, b};
        Arrays.sort(points);
        this.start = points[0];
        this.end = points[1];
        this.points = populateIntermediatePoints();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment segment = (LineSegment) o;
        return Objects.equals(start, segment.start) && Objects.equals(end, segment.end) && Objects.equals(points, segment.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, points);
    }

    public LineSegment(int startX, int startY, int endX, int endY) {
        this(new Point(startX, startY), new Point(endX, endY));
    }

    private List<Point> populateIntermediatePoints() {
        if ((start.getY() != end.getY()) &&
            (start.getX() != end.getX()))
            return Collections.emptyList();
        return IntStream.rangeClosed(start.getX(), end.getX())
            .boxed()
            .flatMap(x ->
                IntStream.rangeClosed(start.getY(), end.getY())
                    .mapToObj(y -> new Point(x, y))
            )
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "VentSegment{" +
            "start=" + start +
            ", end=" + end +
            '}';
    }
}
            /*
            // Bresenham's line algorithm to support non-horizontal and -vertical lines

            List<Point> list = new ArrayList<>();

            int dx = end.getX() - start.getX();
            int dy = end.getY() - start.getY();
            int yi = 1;
            if (dy < 0) {
                yi = -1;
                dy = -dy;
            }
            int D = 2 * dy - dx;
            int y = start.getY();
            for (int x = start.getX(); x <= end.getX(); x++) {
                list.add(new Point(x, y));
                if (D > 0) {
                    y = y + yi;
                    D = D + 2 * (dy - dx);
                } else {
                    D = D + 2 * dy;
                }
            }
            return list;*/