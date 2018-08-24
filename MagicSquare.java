import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagicSquare {
    private static int[][] squares = new int[][] {
        new int[] {8, 1, 6, 3, 5, 7, 4, 9, 2},
        new int[] {6, 1, 8, 7, 5, 3, 2, 9, 4},
        new int[] {2, 7, 6, 9, 5, 1, 4, 3, 8},
        new int[] {4, 3, 8, 9, 5, 1, 2, 7, 6},
        new int[] {2, 9, 4, 7, 5, 3, 6, 1, 8},
        new int[] {4, 9, 2, 3, 5, 7, 8, 1, 6},
        new int[] {6, 7, 2, 1, 5, 9, 8, 3, 4},
        new int[] {8, 3, 4, 1, 5, 9, 6, 7, 2}};
    

    static int formingMagicSquare(int[][] square) {
        int minDistance = Integer.MAX_VALUE;
        int myVal, yourVal;
        for (int sq = 0; sq < squares.length; sq++) {
            int distance = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    myVal = squares[sq][i * 3 + j];
                    yourVal = square[i][j];
                    distance += Math.abs(myVal - yourVal);
                }
            }
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

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

        System.out.println(formingMagicSquare(square));
    }
}