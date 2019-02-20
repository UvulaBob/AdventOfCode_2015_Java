import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Part2 {
    private static int ribbonLength = 0;

    public static void main(String[] args) {
        initialize();
        System.out.println("Done!");
        System.out.println("Total Ribbon Length: " + ribbonLength);

    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day2\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("x");
                int length = Integer.parseInt(splitLine[0]);
                int width = Integer.parseInt(splitLine[1]);
                int height = Integer.parseInt(splitLine[2]);

                ArrayList<Integer> sides = new ArrayList<>(Arrays.asList(length, width, height));
                Collections.sort(sides);

                ribbonLength += (sides.get(0) * 2) + (sides.get(1) * 2) + (length * width * height);

            }

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
