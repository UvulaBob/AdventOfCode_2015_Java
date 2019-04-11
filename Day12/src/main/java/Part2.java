import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;


public class Part2 {
    private static int total = 0;

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));
        String input = lines.get(0);
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
}
