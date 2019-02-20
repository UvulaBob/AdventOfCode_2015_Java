import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;


public class Part2 {
    private static String input = "";
    private static int total = 0;
    public static void main(String[] args) {
        initialize();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(input);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        processNode(rootNode);

        System.out.println("Done!");
        System.out.println(total);
    }

    private static void processNode(JsonNode node) {
        if (node instanceof IntNode) {
            System.out.println(node.intValue());
            total += node.intValue();
        }

        if (node instanceof ArrayNode) {
            for (JsonNode childNode : node) {
                processNode(childNode);
            }
        }

        if (node instanceof ObjectNode) {
            for (JsonNode childNode : node) {
                if (childNode instanceof TextNode) {
                    if (childNode.textValue().equals("red")) {
                        return;
                    }
                }
            }

            for (JsonNode childNode : node) {
                processNode(childNode);
            }
        }
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day12\\src\\main\\resources\\input.txt";

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
                input += line;
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
