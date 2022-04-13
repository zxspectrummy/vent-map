package com.demo.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineSegmentTest {
    @Test
    public void testHorizontalLineCreation() {
        Point a = new Point(5, 2);
        Point b = new Point(2, 2);
        LineSegment segment = new LineSegment(a, b);
        List<Point> expectedPoints = IntStream.rangeClosed(segment.getStart().getX(), segment.getEnd().getX())
            .mapToObj(x -> new Point(x, a.getY())).collect(Collectors.toList());
        Assertions.assertEquals(segment.getPoints(), expectedPoints);
    }

    @Test
    public void testVerticalLineCreation() {
        Point a = new Point(4, 8);
        Point b = new Point(4, 3);
        LineSegment segment = new LineSegment(a, b);
        List<Point> expectedPoints = IntStream.rangeClosed(segment.getStart().getY(), segment.getEnd().getY())
            .mapToObj(y -> new Point(a.getX(), y)).collect(Collectors.toList());
        Assertions.assertEquals(segment.getPoints(), expectedPoints);
    }

    @Test
    public void testSlopingLineCreation() {
        Point a = new Point(0, 1);
        Point b = new Point(6, 4);
        LineSegment segment = new LineSegment(a, b);
        List<Point> expectedPoints = Arrays.stream(new int[][]{{0, 1}, {1, 1}, {2, 2}, {3, 2}, {4, 3}, {5, 3}, {6, 4}})
            .map(p -> new Point(p[0], p[1])).collect(Collectors.toList());
        Assertions.assertEquals(segment.getPoints(), expectedPoints);
    }
}