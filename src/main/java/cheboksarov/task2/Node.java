package cheboksarov.task2;


import java.util.*;
public class Node {
    private String name;
    private Node parent;
    private Integer distance = Integer.MAX_VALUE;
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }


    public Integer getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
    public Node(String name) {
        this.name = name;
    }
    public Node(String name, Integer distance) {
        this.name = name;
        this.distance = distance;
    }

    public String toString() {
        return this.name + ": " + this.distance;
    }
}