package cheboksarov;

import cheboksarov.task2.Dijkstra;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @ParameterizedTest
    @MethodSource("xValues")
    @DisplayName("myArcsinTest")
    public void myArcsinTest(Double x){
        Integer n = 5000;
        double result = Math.asin(x);
        assertEquals(result, Task1.myArcsin(x, n).doubleValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.0, 2.0})
    public void outOfBoundsTest(Double x){
        Integer n = 5000;
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Task1.myArcsin(x, n));
        assertEquals("x must be between -1 and 1",exception.getMessage());
    }

    public static List<Double> xValues(){
        return Arrays.asList(-1.0, -0.9, -0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1,
                0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0);
    }

}