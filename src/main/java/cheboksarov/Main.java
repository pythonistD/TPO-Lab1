package cheboksarov;


import cheboksarov.task2.Dijkstra;
import cheboksarov.task2.Node;
import cheboksarov.task3.God;
import cheboksarov.task3.Planet;
import cheboksarov.task3.Whale;
import cheboksarov.task3.WhaleType;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*Node node4 = new Node("D");
        Node node3 = new Node("C");
        Node node2 = new Node("B");
        Node node1 = new Node("A");
        HashMap<Node, Integer> map1 = new HashMap<>();
        map1.put(node2, 12);
        map1.put(node3, 20);
        node1.setAdjacentNodes(map1);

        HashMap<Node, Integer> map2 = new HashMap<>();
        map2.put(node1, 12);
        map2.put(node4, 3);
        node2.setAdjacentNodes(map2);

        HashMap<Node, Integer> map3 = new HashMap<>();
        map3.put(node1, 20);
        map3.put(node4, 2);
        node3.setAdjacentNodes(map3);

        HashMap<Node, Integer> map4 = new HashMap<>();
        map4.put(node2, 3);
        map4.put(node3, 2);
        node4.setAdjacentNodes(map4);

        Dijkstra dijkstra = new Dijkstra(node1);
        dijkstra.run();
        dijkstra.printShortestPath();
         */
        Planet planet = new Planet(9.8f, "Earth", 13000);
        God god = new God(planet);
        Whale whale = new Whale("Whale", WhaleType.CACHALOT, 40000, 2, planet.getDistanceToStratosphere());
        god.spawnWhale(whale, planet.getDistanceToStratosphere());
    }
}