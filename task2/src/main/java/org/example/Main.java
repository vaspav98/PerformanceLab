package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathToCircle = getPath(args[0]);
        Path pathToPoints = getPath(args[1]);

        Point circleCenter;
        int r;
        List<Point> points = new ArrayList<>();

        try (BufferedReader fileReader1 = Files.newBufferedReader(pathToCircle);
                BufferedReader fileReader2 = Files.newBufferedReader(pathToPoints)) {
            String circleCenterInString = fileReader1.readLine();
            circleCenter = Point.toPoint(circleCenterInString);
            r = Integer.parseInt(fileReader1.readLine());

            String pointInString = "";
            while (pointInString != null) {
                pointInString  = fileReader2.readLine();
                if (pointInString != null) {
                    Point point = Point.toPoint(pointInString);
                    points.add(point);
                }
            }
        }

        for (Point point : points) {
            int x = point.getX();
            int y = point.getY();
            int a = circleCenter.getX();
            int b = circleCenter.getY();
            int calculation = (x - a) * (x - a) + (y - b) * (y - b);
            if (calculation < r * r) {
                System.out.println(points.indexOf(point) + " - точка внутри");
            } else if (calculation > r * r) {
                System.out.println(points.indexOf(point) + " - точка снаружи");
            } else {
                System.out.println(points.indexOf(point) + " - точка лежит на окружности");
            }
        }
    }

    public static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

}
