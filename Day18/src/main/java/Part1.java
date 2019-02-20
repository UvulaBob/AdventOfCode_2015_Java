import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    private static final int gridSize = 100;
    private static final int steps = 100;
    private static boolean[][] grid = new boolean[gridSize][gridSize];

    public static void main(String[] args) {
        initialize();

        printGrid();

        for (int stepNumber = 0; stepNumber < steps; stepNumber++) {
            boolean[][] newGrid = new boolean[gridSize][gridSize];

            for (int x = 0; x < gridSize; x++){
                System.arraycopy(grid[x], 0, newGrid[x], 0, grid.length);
                for (int y = 0; y < gridSize; y++) {
                    if (grid[x][y]) {
                        if (adjacentOnBulbs(x, y) != 2 && adjacentOnBulbs(x, y) != 3) {
                            newGrid[x][y] = false;
                        }
                    } else {
                        if (adjacentOnBulbs(x, y) == 3) {
                            newGrid[x][y] = true;
                        }
                    }
                }
            }
            grid = newGrid;
            printGrid();
        }

        int onBulbCount = 0;
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
               if (grid[x][y]) {
                   onBulbCount++;
               }
            }
        }
        System.out.println("On Bulbs: " + onBulbCount);
        System.out.println("Done!");

    }

    private static void printGrid() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (grid[x][y]) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\r\n");
        }

        System.out.println(sb.toString());
    }

    private static int adjacentOnBulbs(int x, int y) {
        int onBulbs = 0;
        for (int adjacentX = -1; adjacentX <= 1; adjacentX++) {
            for (int adjacentY = -1; adjacentY <= 1; adjacentY++) {
                if (adjacentX == 0 && adjacentY == 0) {
                    continue;
                }
                if (x + adjacentX < 0 || x + adjacentX > gridSize - 1) {
                    continue;
                }
                if (y + adjacentY < 0 || y + adjacentY > gridSize - 1) {
                    continue;
                }
                if (grid[x + adjacentX][y + adjacentY]){
                    onBulbs++;
                }
            }
        }
        return onBulbs;
    }


    private static void initialize() {
// The name of the file to open.
        String fileName = "C:\\workspace\\AoC2015\\Day18\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int x = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("");
                for (int y = 0; y < splitLine.length; y++) {
                    grid[x][y] = (splitLine[y].equals("#"));
                }
                x++;
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
