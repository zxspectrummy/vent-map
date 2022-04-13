package com.demo.advanced;


import com.demo.base.Point;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Node {
    private final Point point;
    private int pointCount;

    public Node(Point point) {
        this.point = point;
        this.pointCount = 1;
    }

    @Override
    public String toString() {
        return "Node{" +
            "point=" + point +
            ", pointCount=" + pointCount +
            '}';
    }

    public void incrementPointCount() {
        pointCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(point, node.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
