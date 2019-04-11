import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {
    private static final int gridSize = 100;
    private static final int steps = 100;
    private static boolean[][] grid = new boolean[gridSize][gridSize];

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        int x = 0;
        for (String line : lines) {
            String[] splitLine = line.split("");
            for (int y = 0; y < splitLine.length; y++) {
                grid[x][y] = (splitLine[y].equals("#"));
            }
            x++;
        }

        grid[0][0] = true;
        grid[gridSize - 1][0] = true;
        grid[0][gridSize - 1] = true;
        grid[gridSize - 1][gridSize - 1] = true;

        
        printGrid();

        for (int stepNumber = 0; stepNumber < steps; stepNumber++) {
            boolean[][] newGrid = new boolean[gridSize][gridSize];

            for (x = 0; x < gridSize; x++){
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

            newGrid[0][0] = true;
            newGrid[gridSize - 1][0] = true;
            newGrid[0][gridSize - 1] = true;
            newGrid[gridSize - 1][gridSize - 1] = true;

            grid = newGrid;
            printGrid();
        }

        int onBulbCount = 0;
        for (x = 0; x < gridSize; x++) {
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
}
