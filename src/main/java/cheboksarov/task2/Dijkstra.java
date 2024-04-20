package cheboksarov.task2;

import java.util.*;

public class Dijkstra {
    private final Node sourceNode;
    PriorityQueue<Node> priorityQueue;

    public Dijkstra(Node sourceNode) {
        this.sourceNode = sourceNode;
        this.priorityQueue = new PriorityQueue<>(
                Comparator.comparing(Node::getDistance)
        );
    }

    public Set<Node> run(){
        Set<Node> visitedNodes = new HashSet<>();
        sourceNode.setDistance(0);
        priorityQueue.add(sourceNode);
        while(!priorityQueue.isEmpty()){
            Node currentNode = priorityQueue.poll();
            visitedNodes.add(currentNode);
            for(Map.Entry<Node, Integer> adNode: currentNode.getAdjacentNodes().entrySet()){
                if (visitedNodes.contains(adNode.getKey())){
                    continue;
                }
                if (adNode.getKey().getDistance() > (currentNode.getDistance() + adNode.getValue())){
                    adNode.getKey().setParent(currentNode);
                    adNode.getKey().setDistance(currentNode.getDistance() + adNode.getValue());
                }
                if (!priorityQueue.contains(adNode.getKey())){
                    priorityQueue.add(adNode.getKey());
                }
            }
        }
        return visitedNodes;
    }

    public static Map<Node, Map<Node, Integer>> makeGraphFromSourceNode(Node sourceNode){
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        Set<Node> visitedNodes = new HashSet<>();
        Map<Node, Map<Node, Integer>> graph = new HashMap<>();
        nodes.add(sourceNode);
        while (!nodes.isEmpty()){
            Node currentNode = nodes.poll();
            graph.put(currentNode, currentNode.getAdjacentNodes());
            for(Map.Entry<Node, Integer> adNode: currentNode.getAdjacentNodes().entrySet()){
                if (visitedNodes.contains(adNode.getKey())){
                    continue;
                }
                nodes.add(adNode.getKey());
                visitedNodes.add(adNode.getKey());
            }
        }
        return graph;
    }

    public Map<String, Integer> printShortestPath(){
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        Set<Node> visitedNodes = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        nodes.add(this.sourceNode);
        while(!nodes.isEmpty()){
            Node curNode = nodes.pop();
            visitedNodes.add(curNode);
            System.out.println(curNode.getName() + ": " + curNode.getDistance());
            distances.put(curNode.getName(), curNode.getDistance());
            for(Map.Entry<Node, Integer> adNode:curNode.getAdjacentNodes().entrySet()){
                if (!visitedNodes.contains(adNode.getKey()) &&(!nodes.contains(adNode.getKey()))) {
                    nodes.addLast(adNode.getKey());
                }
            }
        }
        return distances;
    }


    public static Node createGraphFromSource(Map<String, Map<String, Integer>> adjacencyList, String initialNode){
        Map<String, Node> nodesCreated = new HashMap<>();
        if(adjacencyList.isEmpty()){
            throw new IllegalArgumentException("Adjacency list is empty");
        }
        if (!adjacencyList.containsKey(initialNode)){
            throw new IllegalArgumentException("Initial node is not in the graph");
        }
        Node sourceNode = new Node(initialNode);
        for(Map.Entry<String, Map<String, Integer>> entry: adjacencyList.entrySet()){
            Node node = nodesCreated.get(entry.getKey());
            if (node == null){
                node = new Node(entry.getKey());
                nodesCreated.put(entry.getKey(), node);
            }
            if (Objects.equals(node.getName(), initialNode)){
                sourceNode = node;
            }
            Map<String, Integer> adjacentNodes = entry.getValue();
            Map<Node,Integer> adjacentNodesParsed = new HashMap<>();
            for(Map.Entry<String, Integer> adjacentNode: adjacentNodes.entrySet()){
                Node adjacentNodeParsed = nodesCreated.get(adjacentNode.getKey());
                if (adjacentNodeParsed == null){
                    adjacentNodeParsed = new Node(adjacentNode.getKey());
                    nodesCreated.put(adjacentNode.getKey(), adjacentNodeParsed);
                }
                adjacentNodesParsed.put(adjacentNodeParsed, adjacentNode.getValue());
            }
            node.setAdjacentNodes(adjacentNodesParsed);
        }
        return sourceNode;
    }
}
