
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException{
        HashMap<String, HashSet<String>> substitutionSets = new HashMap<>();
        String input = "";

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        for (String line : lines) {
            if (line.contains("=>")) {
                String[] splitLine = line.split(" => ");
                substitutionSets.putIfAbsent(splitLine[0], new HashSet<>());
                substitutionSets.get(splitLine[0]).add(splitLine[1]);
            }

            else if (!line.isEmpty()){
                input = line;
            }
        }

        int elements = 0;
        for (String letter : input.split("")) {
            if (letter.equals(letter.toUpperCase())) {
                elements++;
            }
        }
        int rnCount = 0;
        int lastIndex = 0;
        while(lastIndex != -1){
            lastIndex = input.indexOf("Rn",lastIndex);
            if(lastIndex != -1){
                rnCount++;
                lastIndex += "Rn".length();
            }
        }

        int arCount = 0;
        lastIndex = 0;
        while(lastIndex != -1){
            lastIndex = input.indexOf("Ar",lastIndex);
            if(lastIndex != -1){
                arCount++;
                lastIndex += "Ar".length();
            }
        }

        int yCount = 0;
        lastIndex = 0;
        while(lastIndex != -1){
            lastIndex = input.indexOf("Y",lastIndex);
            if(lastIndex != -1){
                yCount++;
                lastIndex += "Y".length();
            }
        }



        System.out.println("Total Steps: " + (elements - rnCount - arCount - (2 * yCount) - 1));
        System.out.println("Done!");
    }


}