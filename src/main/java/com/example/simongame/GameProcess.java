package com.example.simongame;

import java.util.Random;
import java.util.Queue;

public class GameProcess {
    private Queue<Integer> trueSubsequence;
    private final Random random = new Random();
    public Queue<Integer> addSubsequence(Queue<Integer> source) {
        source.add(random.nextInt(4));
        return source;
    }
}
