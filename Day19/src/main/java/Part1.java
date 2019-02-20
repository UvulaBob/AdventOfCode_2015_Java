import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Part1 {
    private static HashSet<String> molecules = new HashSet<>();
    private static HashMap<String, HashSet<String>> substitutionSets = new HashMap<>();
    private static String input = "";

    public static void main(String[] args) {
        initialize();

        for (Map.Entry<String, HashSet<String>> entry : substitutionSets.entrySet()) {
            String atom = entry.getKey();
            HashSet<String> substitutionSet = entry.getValue();
            System.out.println(String.format("%s => %s", atom, substitutionSet));
            for (String substitution : substitutionSet) {
                HashSet<Integer> indexes = new HashSet<>();
                int lastIndex = 0;
                while(lastIndex != -1){
                    lastIndex = input.indexOf(atom,lastIndex);
                    if(lastIndex != -1){
                        indexes.add(lastIndex);
                        lastIndex += atom.length();
                    }
                }

                for (int index : indexes) {
                    String leftSide = input.substring(0, index);
                    String rightSide = input.substring(index);
                    rightSide = rightSide.replaceFirst(atom, substitution);
                    String newMolecule = leftSide + rightSide;
                    molecules.add(newMolecule);
                }
            }
        }

        System.out.println("Number of Unique Molecules: " + molecules.size());
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
