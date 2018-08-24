import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SquareSumTest {

    public static void main(String[] args) throws IOException {
        final int size = 3;

        int[][] square = new int[size][];

        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        for (int i = 0; i < size; i++) {
            square[i] = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        displaySums(square);
    }

    private static void displaySums(int[][] square) {
        printArray(SquareSum.sumRows(square));
        printArray(SquareSum.sumCols(square));
        printArray(SquareSum.sumDiagonals(square));
    }

    private static void printArray(int[] arr) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }
}