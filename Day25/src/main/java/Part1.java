import java.util.ArrayList;

public class Part1 {

    public static void main(String[] args) {

        long initialValue = 20151125L;
        int upperRow = 2;

        int targetRow = 2981;
        int targetColumn = 3075;

        long nextNumber = initialValue;

        while(true) {
            int currentRow = upperRow;
            int currentColumn = 1;
            while (currentRow > 0) {
                nextNumber = nextNumber(nextNumber);
                if (currentRow == targetRow && currentColumn == targetColumn) {
                    System.out.println(String.format("(%s, %s): %s", currentRow, currentColumn, nextNumber));
                    return;
                }
                currentRow -= 1;
                currentColumn += 1;
            }
            upperRow++;
        }

    }

    private static long nextNumber(long value) {
        return (value * 252533L) % 33554393L;
    }


}
