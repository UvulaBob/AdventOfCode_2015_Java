import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        int squareFeet = 0;

        for (String line : lines) {
            String[] splitLine = line.split("x");
            int length = Integer.parseInt(splitLine[0]);
            int width = Integer.parseInt(splitLine[1]);
            int height = Integer.parseInt(splitLine[2]);

            ArrayList<Integer> sides = new ArrayList<>(Arrays.asList(length * width, height * width, length * height));
            Collections.sort(sides);

            squareFeet += (2 * length * width) + (2 * height * width) + (2 * length * height) + sides.get(0);
        }

        System.out.println("Total Square Footage: " + squareFeet);
        System.out.println("Done!");

    }
}
