package cheboksarov.task2;

import lombok.Getter;

public class Graph {
    @Getter
    private final int[][] adjacencyList;
    @Getter
    private final int vertexCount;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacencyList = new int[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                adjacencyList[i][j] = 0;
            }
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList[source][destination] = weight;
    }

    public void addVertex(int source, int[] adjacencyList) {
        for(int i = 0; i < adjacencyList.length; i++) {
            this.adjacencyList[source][i] = adjacencyList[i];
        }
    }
}
