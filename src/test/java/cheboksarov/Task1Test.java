package cheboksarov;

import cheboksarov.task2.Dijkstra;
import cheboksarov.task2.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    @Test
    void testArcsinValuesInRange() {
        Integer n = 1000;
        Map<Double, Double> testCases = new HashMap<>();
        testCases.put(-1.0, -1.5708);
        testCases.put(-0.5, -0.5236);
        testCases.put(0.0, 0.0);
        testCases.put(0.5, 0.5236);
        testCases.put(1.0, 1.5708);

        for (Map.Entry<Double, Double> entry : testCases.entrySet()) {
            double angle = entry.getKey();
            double expectedValue = entry.getValue();
            double tolerance = 0.1;

            assertEquals(expectedValue, Task1.myArcsin(angle, n).doubleValue(), tolerance);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.0, 2.0})
    public void outOfBoundsTest(Double x){
        Integer n = 5000;
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Task1.myArcsin(x, n));
        assertEquals("x must be between -1 and 1",exception.getMessage());
    }

    @Nested
    public class FactorialTest {

        @Test
        void testFactorialZero() {
            int n = 0;
            BigDecimal expectedResult = BigDecimal.valueOf(1);
            assertEquals(expectedResult, Task1.factorialUsingIteration(n));
        }

        @Test
        void testFactorialPositive() {
            int n = 5;
            BigDecimal expectedResult = BigDecimal.valueOf(120);
            assertEquals(expectedResult, Task1.factorialUsingIteration(n));
        }

        @Test
        void testFactorialLargeNumber() {
            int n = 50;
            BigDecimal expectedResult = new BigDecimal("30414093201713378043612608166064768844377641568960512000000000000");
            assertEquals(expectedResult, Task1.factorialUsingIteration(n));
        }

        @Test
        void testFactorialNegative() {
            int n = -5;
            assertThrows(IllegalArgumentException.class, () -> Task1.factorialUsingIteration(n));
        }
    }


}