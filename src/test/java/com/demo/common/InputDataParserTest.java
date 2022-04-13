package com.demo.common;

import com.demo.base.LineSegment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class InputDataParserTest {
    @Test
    public void test() {
        List<String> data = Arrays.asList(
            "0, 92->  5,9",
            "10,23 ->24,1"
        );
        List<LineSegment> segments = InputDataParser.parse(data);
        List<LineSegment> expectedResult = Arrays.asList(
            new LineSegment(0, 92, 5, 9),
            new LineSegment(10,23,24,1)
        );
        Assertions.assertEquals(segments, expectedResult);
    }
}
