import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) throws IOException {
        int[][] grid = new int[1000][1000];

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        Pattern pattern = Pattern.compile("(toggle|(?:turn (?:on|off))) (\\d+,\\d+) through (\\d+,\\d+)");

        for (String line : lines) {
            Matcher m = pattern.matcher(line);

            while (m.find()) {
                String operation = (m.group(1));

                String startPosition = (m.group(2));
                int startX = Integer.parseInt(startPosition.split(",")[0]);
                int startY = Integer.parseInt(startPosition.split(",")[1]);

                String endPosition = (m.group(3));
                int endX = Integer.parseInt(endPosition.split(",")[0]);
                int endY = Integer.parseInt(endPosition.split(",")[1]);

                switch (operation) {
                    case "turn on":
                        for (int y = startY; y <= endY; y++) {
                            for (int x = startX; x <= endX; x++) {
                                grid[y][x]++;
                            }
                        }
                        break;
                    case "turn off":
                        for (int y = startY; y <= endY; y++) {
                            for (int x = startX; x <= endX; x++) {
                                grid[y][x]--;
                                if (grid[y][x] < 0 ) {
                                    grid[y][x] = 0;
                                }
                            }
                        }
                        break;
                    case "toggle":
                        for (int y = startY; y <= endY; y++) {
                            for (int x = startX; x <= endX; x++) {
                                grid[y][x] += 2;
                            }
                        }
                        break;
                }
            }
        }

        int brightness = 0;
        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                brightness += grid[y][x];
            }
        }

        System.out.println(brightness);
        System.out.println("Done!");
    }
}
