import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Part2 {
    private static HashMap<String, Integer> houses = new HashMap<>();
    private static String[] input;

    public static void main(String[] args) {
        initialize();
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

        System.out.println("Done!");
        System.out.println("Houses With at Least One Present: " + (houses.size()));
    }

    private static String key(int x, int y) {
        return x + "," + y;
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day3\\src\\main\\resources\\input.txt";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            input = bufferedReader.readLine().split("");

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
    }
}
