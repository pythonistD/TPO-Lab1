package cheboksarov.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    @Nested
    public class WhaleTest{
        private Planet planet = new Planet(9.8f, "Earth", 13000);
        @Test
        @DisplayName("Negative time of falling")
        void checkWhaleConstructor(){
            Whale whale = new Whale("Whale", WhaleType.CACHALOT, 2, planet.getDistanceToStratosphere(),
                    1);
            Throwable exception = assertThrows(IllegalArgumentException.class,() -> whale.whatWhaleThoughtWhenFalling(-1));
            assertEquals("Time of falling must be positive",exception.getMessage());
        }

        @Test
        @DisplayName("Current thought: out of bounds of list with thoughts")
        public void checkCurrentThoughtOutOfBounds(){
            Whale whale = new Whale("Whale", WhaleType.CACHALOT, 2, planet.getDistanceToStratosphere(),
                    1);
            Integer t = whale.getFullListOfWhaleThoughts().size() * whale.getThinkRate() + 100;
            assertEquals("Aaaaaaaaaa...", whale.getCurrentWhaleThought(t));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 4})
        @DisplayName("Check whale constructor for incorrect durability value")
        public void checkWhaleConstructorForIncorrectDurabilityValue(Integer durability){
            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> new Whale("Whale", WhaleType.CACHALOT, 2, planet.getDistanceToStratosphere(),
                    durability));
            assertEquals("Durability must be between 1 and 3",exception.getMessage());
        }


        @ParameterizedTest
        @MethodSource("fallingTimes")
        @DisplayName("Test whatWhaleThoughtWhenFalling function with random values")
        public void whatWhaleThoughtWhenFalling(Integer timeOfFalling){
            Whale whale = new Whale("Whale", WhaleType.CACHALOT, 2, planet.getDistanceToStratosphere(),
                    1);
            assertNotNull(whale.whatWhaleThoughtWhenFalling(timeOfFalling));
        }

        public static List<Integer> fallingTimes(){
           Random random = new Random();
           List<Integer> times = new ArrayList<>();
           for(int i = 0; i < 100; i++){
               times.add(random.nextInt(1000));
           }
           return times;
        }
    }
}
