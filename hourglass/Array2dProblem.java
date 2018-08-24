import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Array2dProblem {
    private static final int[][] HG_SEG_OFFSETS = new int[][] {
            {0,0}, {0,1}, {0,2},
                   {1,1},
            {2,0}, {2,1}, {2,2}};

    public static int largestHourglass(int[][] array) {
        int max = Integer.MIN_VALUE;
        for (int row = 0; row <= array.length - 3; row++) {
            for (int col = 0; col <= array[0].length - 3; col++) {
                int sum = 0;
                for (int seg = 0; seg < HG_SEG_OFFSETS.length; seg++) {
                    int dRow = row + HG_SEG_OFFSETS[seg][0];
                    int dCol = col + HG_SEG_OFFSETS[seg][1];
                    sum += array[dRow][dCol];
                    System.err.printf("[%d,%d](%d) ", dRow, dCol, array[dRow][dCol]);
                }
                System.err.printf("=> %d\n", sum);
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        ) {
            int[][] array = new int[6][];
            for (int i = 0; i < 6; i++) {
                array[i] = readIntegers(in);
            }
            System.out.println(largestHourglass(array));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static int[] readIntegers(BufferedReader in) throws IOException {
        return Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
    }
}