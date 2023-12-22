package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathToFile = Paths.get(args[0]).toAbsolutePath().normalize();
        List<Integer> nums = Files.readAllLines(pathToFile).stream()
                .map(v -> Integer.parseInt(v))
                .collect(Collectors.toList());

        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            int stepsForAll = 0;
            for (int num : nums) {
                int steps = Math.abs(i - num);
                stepsForAll += steps;
            }
            if (stepsForAll < min) {
                min = stepsForAll;
            }
        }
        System.out.println(min);
    }

}
