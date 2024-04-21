package cheboksarov;

import cheboksarov.task2.Node;
import cheboksarov.task2.Dijkstra;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {

    @Nested
    public class DijkstraAlgorithmTest{
        @ParameterizedTest
        @ArgumentsSource(GraphAndAnswerProvider.class)
        public void basicDijkstraTest(Map<String, Map<String, Integer>> adjacencyList, Map<String, Integer> expectedResult) {
            Node startNode = Dijkstra.createGraphFromSource(adjacencyList, "0");
            Dijkstra dijkstra = new Dijkstra(startNode);
            dijkstra.run();
            Map<String, Integer> distances = dijkstra.printShortestPath();
            assertEquals(expectedResult, distances);
        }
        static class GraphAndAnswerProvider implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context){
                return Stream.of(
                        Arguments.of(Map.of("0", Map.of("1", 1, "2", 3),
                                "1", Map.of("0", 1, "3", 7),
                                "2", Map.of("0", 3, "3", 2)),
                                Map.of(
                                        "0", 0,
                                        "1", 1,
                                        "2", 3,
                                        "3", 5
                                )
                        ),
                        Arguments.of(Map.of("0", Map.of("1", 1, "2", 2),
                                        "1", Map.of("0", 1),
                                        "2", Map.of("0", 3, "3", 2)),
                                Map.of(
                                        "0", 0,
                                        "1", 1,
                                        "2", 2,
                                        "3", 4
                                )
                        ),
                        Arguments.of(Map.of("0", Map.of("1", 1, "3", 1, "2", 1),
                                "1", Map.of("0", 1, "2", 1),
                                "2", Map.of("1", 1, "3", 1),
                                "3", Map.of("0", 1, "2", 1)),
                                Map.of(
                                        "0", 0,
                                        "1", 1,
                                        "2", 1,
                                        "3", 1
                                )
                ));

            }

        }

        @Test
        public void oneNodeGraphTest(){
            Dijkstra dijkstra = new Dijkstra(new Node("0"));
            dijkstra.run();
            Map<String, Integer> distances = dijkstra.printShortestPath();
            assertEquals(Map.of("0", 0), distances);
        }
    }
    @Nested
    public class GraphCreationTest{

        @Test
        public void emptyAdjacencyListTest(){
            Map<String, Map<String, Integer>> adjacencyList = new HashMap<>();
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> Dijkstra.createGraphFromSource(adjacencyList, null));
            assertEquals("Adjacency list is empty",exception.getMessage());
        }

        @Test
        public void nullInitialNodeTest(){
            Map<String, Map<String, Integer>> adjacencyList = new HashMap<>();
            adjacencyList.put("0", Map.of("1", 1));
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> Dijkstra.createGraphFromSource(adjacencyList, null));
            assertEquals("Initial node is not in the graph",exception.getMessage());
        }

        @Test
        public void noInitialNodeTest(){
            Map<String, Map<String, Integer>> adjacencyList = new HashMap<>();
            adjacencyList.put("0", Map.of("1", 1));
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> Dijkstra.createGraphFromSource(adjacencyList, "A"));
            assertEquals("Initial node is not in the graph",exception.getMessage());
        }

        @ParameterizedTest
        @ArgumentsSource(AdjacencyListAndAnswerProvider.class)
        public void graphCreationTest(Map<String, Map<String, Integer>> adjacencyList, String sourceNode, Map<Node, Map<Node, Integer>> expectedResult){
            Node startNode = Dijkstra.createGraphFromSource(adjacencyList, sourceNode);
            Map<Node, Map<Node, Integer>> graph = Dijkstra.makeGraphFromSourceNode(startNode);
            MapDifference<Node, Map<Node, Integer>> difference = Maps.difference(expectedResult, graph);
            assertTrue(difference.entriesDiffering().isEmpty());
        }
        static class AdjacencyListAndAnswerProvider implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context){
                Node node3 = new Node("3");
                Node node2 = new Node("2");
                Node node1 = new Node("1");
                Node node0 = new Node("0");
                return Stream.of(
                        Arguments.of(Map.of("0", Map.of("1", 1, "2", 3),
                                        "1", Map.of("0", 1, "3", 7),
                                        "2", Map.of("0", 3, "3", 2)),
                                "0",
                                Map.of(
                                        node0, Map.of(node1, 1, node2, 3),
                                        node1, Map.of(node0, 1, node3, 7),
                                        node2, Map.of(node0, 3, node3, 2))
                        )
                );

            }

        }
    }


}
