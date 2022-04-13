package com.demo.advanced;

import com.demo.base.LineSegment;
import com.demo.base.Point;
import lombok.Getter;

import java.util.*;

public class QuadTree {
    private static final int MAX_POINTS = 3;
    @Getter
    private final Region area;
    private final List<Node> nodes = new ArrayList<>();
    private final List<QuadTree> quadTrees = new ArrayList<>();
    private StringBuilder searchTraversePath;

    public QuadTree(Region area) {
        this.area = area;
    }

    public void insertSegments(List<LineSegment> segments) {
        for (LineSegment segment: segments) {
            this.insertSegment(segment);
        }
    }

    private void insertSegment(LineSegment segment) {
        for (Point point : segment.getPoints()) {
            this.insert(point);
        }
    }

    public boolean insert(Point point) {
        if (this.area.containsPoint(point)) {
            Node existingNode = nodes.stream().
                filter(n -> n.getPoint().equals(point)).
                findFirst().orElse(null);
            if (existingNode != null) {
                existingNode.incrementPointCount();
            } else {
                if (this.nodes.size() < MAX_POINTS) {
                    this.nodes.add(new Node(point));
                    return true;
                } else {
                    if (this.quadTrees.size() == 0) {
                        createQuadrants();
                    }
                    return addPointToOneQuadrant(point);
                }
            }
        }
        return false;
    }

    private boolean addPointToOneQuadrant(Point point) {
        boolean isPointAdded;
        for (int i = 0; i < 4; i++) {
            isPointAdded = this.quadTrees.get(i).insert(point);
            if (isPointAdded)
                return true;
        }
        return false;
    }

    private void createQuadrants() {
        Region region;
        for (int i = 0; i < 4; i++) {
            region = this.area.getQuadrant(i);
            quadTrees.add(new QuadTree(region));
        }
    }

    public List<Node> search(Region area) {
        return this.search(area, 1);
    }

    public List<Node> search(Region searchRegion, int threshold) {
        List<Node> matches = new ArrayList<>();
        return search(searchRegion, matches, threshold, "");
    }

    private List<Node> search(Region searchRegion, List<Node> matches, int threshold, String depthIndicator) {
        searchTraversePath = new StringBuilder();
        if (matches == null) {
            matches = new ArrayList<>();
            searchTraversePath.append(depthIndicator)
                .append("Search Boundary =")
                .append(searchRegion)
                .append("\n");
        }
        if (!this.area.doesOverlap(searchRegion)) {
            return matches;
        } else {
            for (Node node : nodes) {
                if (searchRegion.containsPoint(node.getPoint())) {
                    searchTraversePath.append(depthIndicator).append("Found match ").append(node)
                        .append("\n");
                    if (node.getPointCount() >= threshold) {
                        matches.add(node);
                    }
                }
            }
            if (this.quadTrees.size() > 0) {
                for (int i = 0; i < 4; i++) {
                    searchTraversePath.append(depthIndicator)
                        .append("Q")
                        .append(i)
                        .append("-->")
                        .append(quadTrees.get(i).area)
                        .append("\n");
                    quadTrees.get(i).search(searchRegion, matches, threshold, depthIndicator + "\t");
                    this.searchTraversePath.append(quadTrees.get(i).printSearchTraversePath());
                }
            }
        }
        return matches;
    }

    @Override
    public String toString() {
        return printTree("");
    }

    private String printTree(String depthIndicator) {
        StringBuilder str = new StringBuilder();
        if (Objects.equals(depthIndicator, "")) {
            str.append("Root-->").append(area.toString()).append("\n");
        }

        for (Node node : nodes) {
            str.append(depthIndicator).append(node.toString()).append("\n");
        }
        for (int i = 0; i < quadTrees.size(); i++) {
            str.append(depthIndicator).append("Q").append(i).append("-->").append(quadTrees.get(i).area.toString()).append("\n");
            str.append(quadTrees.get(i)
                .printTree(depthIndicator + "\t"));
        }
        return str.toString();
    }

    public String printSearchTraversePath() {
        return searchTraversePath.toString();
    }

    public List<Region> searchRegions() {
        List<Region> regionsInRange = new LinkedList<>();
        regionsInRange.add(this.area);
        this.searchRegions(this.area, regionsInRange);
        return regionsInRange;
    }

    private void searchRegions(Region searchRegion, List<Region> regionsInRange) {
        if (this.area.doesOverlap(searchRegion)) {
            if (this.quadTrees.size() > 0) {
                for (int i = 0; i < 4; i++) {
                    regionsInRange.add(quadTrees.get(i).area);
                    quadTrees.get(i).searchRegions(searchRegion, regionsInRange);
                }
            }
        }
    }
}