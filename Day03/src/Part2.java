import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part2 {
    private static HashMap<String, Integer> houses = new HashMap<>();
    private static String[] input;

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        input = lines.get(0).split("");

        int santaX = 0;
        int santaY = 0;

        int roboSantaX = 0;
        int roboSantaY = 0;

        houses.put(key(santaX, santaY), 1);
        houses.put(key(roboSantaX, roboSantaY), 1);

        boolean santasTurn = true;

        for (String direction : input) {
            switch (direction) {
                case "^":
                    if (santasTurn) {
                        santaY--;
                    } else {
                        roboSantaY--;
                    }
                    break;
                case "<":
                    if (santasTurn) {
                        santaX--;
                    } else {
                        roboSantaX--;
                    }
                    break;
                case ">":
                    if (santasTurn) {
                        santaX++;
                    } else {
                        roboSantaX++;
                    }
                    break;
                case "v":
                    if (santasTurn) {
                        santaY++;
                    } else {
                        roboSantaY++;
                    }
                    break;
            }

            if (santasTurn) {
                houses.putIfAbsent(key(santaX, santaY), 0);
                houses.put(key(santaX, santaY), houses.get(key(santaX, santaY)) + 1);
            } else {
                houses.putIfAbsent(key(roboSantaX, roboSantaY), 0);
                houses.put(key(roboSantaX, roboSantaY), houses.get(key(roboSantaX, roboSantaY)) + 1);
            }

            santasTurn = !santasTurn;
        }

        System.out.println("Houses With at Least One Present: " + (houses.size()));
        System.out.println("Done!");
    }

    private static String key(int x, int y) {
        return x + "," + y;
    }


}
