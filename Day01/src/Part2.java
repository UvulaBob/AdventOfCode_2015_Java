import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        String[] input = lines.get(0).split("");
        int floorNumber = 0;

        for (int i = 0 ; i < input.length; i++ ) {
            String symbol = input[i];
            if (symbol.equals("(")) {
                floorNumber++;
            } else {
                floorNumber--;
                if (floorNumber < 0) {
                    System.out.println("Basement entered at position " + (i + 1));
                    System.out.println("Done!");
                    return;
                }
            }
        }

        System.out.println("We never hit the basement!");
        System.out.println("Done!");
    }
}
