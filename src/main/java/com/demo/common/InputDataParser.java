package com.demo.common;

import com.demo.base.LineSegment;
import com.demo.base.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataParser {
    public static List<LineSegment> parse(List<String> data) {
        List<LineSegment> segments = new ArrayList<>();
        String regex = "(?<x1>\\d+),(?<y1>\\d+)->(?<x2>\\d+),(?<y2>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        for (String s : data) {
            Matcher matcher = pattern.matcher(s.replace(" ", ""));
            if (! matcher.matches())
                throw new IllegalArgumentException("Invalid input: \"" + s + "\"");
            Point a = new Point(Integer.parseInt(matcher.group("x1")),Integer.parseInt(matcher.group("y1")));
            Point b = new Point(Integer.parseInt(matcher.group("x2")),Integer.parseInt(matcher.group("y2")));
            segments.add(new LineSegment(a,b));
        }
        return segments;
    }
}
