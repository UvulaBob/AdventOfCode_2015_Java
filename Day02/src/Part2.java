import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        int ribbonLength = 0;

        for (String line: lines) {
            String[] splitLine = line.split("x");
            int length = Integer.parseInt(splitLine[0]);
            int width = Integer.parseInt(splitLine[1]);
            int height = Integer.parseInt(splitLine[2]);

            ArrayList<Integer> sides = new ArrayList<>(Arrays.asList(length, width, height));
            Collections.sort(sides);

            ribbonLength += (sides.get(0) * 2) + (sides.get(1) * 2) + (length * width * height);
        }
        
        System.out.println("Total Ribbon Length: " + ribbonLength);
        System.out.println("Done!");

    }
}
