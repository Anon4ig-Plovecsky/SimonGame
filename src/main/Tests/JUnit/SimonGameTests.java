package JUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import com.example.simongame.SimonGame;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class SimonGameTests {
    Queue<Integer> trueSubsequence;
    @BeforeEach
    public void setUp() {
        trueSubsequence = new LinkedList<>();
        trueSubsequence.add(4);
        trueSubsequence.add(3);
        trueSubsequence.add(0);
        trueSubsequence.add(2);
        trueSubsequence.add(3);
        trueSubsequence.add(1);
    }
    @Test
    public void checkCorrectnessSubsequenceTest1() {
        boolean expected = true;
        boolean actual = true;
        Queue<Integer> userSubsequence = new LinkedList<>();
        userSubsequence.add(4);
        userSubsequence.add(3);
        userSubsequence.add(0);
        userSubsequence.add(2);
        userSubsequence.add(3);
        userSubsequence.add(1);
        while(!userSubsequence.isEmpty() && actual) {
            actual = SimonGame.areEqual(userSubsequence.poll(),
                    Objects.requireNonNull(trueSubsequence.poll()));
        }
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void checkCorrectnessSubsequenceTest2() {
        boolean expected = false;
        boolean actual = true;
        Queue<Integer> userSubsequence = new LinkedList<>();
        userSubsequence.add(4);
        userSubsequence.add(3);
        userSubsequence.add(4);
        userSubsequence.add(2);
        userSubsequence.add(3);
        userSubsequence.add(1);
        while(!userSubsequence.isEmpty() && actual) {
            actual = SimonGame.areEqual(userSubsequence.poll(),
                    Objects.requireNonNull(trueSubsequence.poll()));
        }
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void checkCorrectnessSubsequenceTest3() {
        boolean expected = false;
        boolean actual = true;
        Queue<Integer> userSubsequence = new LinkedList<>();
        userSubsequence.add(4);
        userSubsequence.add(3);
        userSubsequence.add(0);
        userSubsequence.add(2);
        userSubsequence.add(3);
        userSubsequence.add(4);
        while(!userSubsequence.isEmpty() && actual) {
            actual = SimonGame.areEqual(userSubsequence.poll(),
                    Objects.requireNonNull(trueSubsequence.poll()));
        }
        Assertions.assertEquals(expected, actual);
    }
}