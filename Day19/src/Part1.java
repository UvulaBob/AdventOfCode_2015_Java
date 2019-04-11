import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Part1 {

    public static void main(String[] args) throws IOException{
        HashSet<String> molecules = new HashSet<>();
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

}
