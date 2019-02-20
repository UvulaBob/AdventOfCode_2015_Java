
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Part2 {
    private static HashMap<String, HashSet<String>> substitutionSets = new HashMap<>();
    private static String input = "";

    public static void main(String[] args) {
        initialize();
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


    private static void initialize() {
// The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day19\\src\\main\\resources\\input.txt";

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
                if (line.contains("=>")) {
                    String[] splitLine = line.split(" => ");
                    substitutionSets.putIfAbsent(splitLine[0], new HashSet<>());
                    substitutionSets.get(splitLine[0]).add(splitLine[1]);
                }

                else if (!line.isEmpty()){
                    input = line;
                }
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