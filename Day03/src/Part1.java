import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part1 {
    private static HashMap<String, Integer> santaHouses = new HashMap<>();
    private static String[] input;

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        // The name of the file to open.

        input = lines.get(0).split("");
        int santaX = 0;
        int santaY = 0;

        santaHouses.put(key(santaX, santaY), 1);

        for (String direction : input) {
            switch (direction) {
                case "^":
                    santaY--;
                    break;
                case "<":
                    santaX--;
                    break;
                case ">":
                    santaX++;
                    break;
                case "v":
                    santaY++;
                    break;
            }

            santaHouses.putIfAbsent(key(santaX, santaY), 0);
            santaHouses.put(key(santaX, santaY), santaHouses.get(key(santaX, santaY)) + 1);
        }

        System.out.println("Done!");
        System.out.println("Houses With at Least One Present: " + santaHouses.size());

    }

    private static String key(int x, int y) {
        return x + "," + y;
    }
}
