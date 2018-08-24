import java.util.Arrays;

public class SquareSum {

    public static int[] sumRows(int[][] square) {
        return Arrays.stream(square)
                .mapToInt(r -> Arrays.stream(r).sum())
                .toArray();
    }

    public static int[] sumCols(int[][] square) {
        int[] colSums = new int[square.length];
        Arrays.stream(square)
            .forEach(row -> {
                for (int i = 0; i < row.length; i++) {
                    colSums[i] += row[i];
                }
            });
        return colSums;
    }

    public static int[] sumDiagonals(int[][] square) {
        int[] diagSums = new int[2];
        for (int i = 0, j = square.length - 1; i < square.length; i++, j--) {
            diagSums[0] += square[i][i];
            diagSums[1] += square[i][j];
        }
        return diagSums;
    }

    public static int sumRow(int[][] square, int row) {
        return Arrays.stream(square[row]).sum();
    }

    public static int sumCol(int[][] square, int col) {
        return Arrays.stream(square).mapToInt(row -> row[col]).sum();
    }

    public static int sumDiagonal(int[][] square, Diagonal direction) {
        int answer = 0;

        boolean tlBr = (direction == Diagonal.TOP_LEFT_TO_BOTTOM_RIGHT);
        int n = tlBr ? 0 : square.length - 1;
        int dN = tlBr ? 1 : -1;
        
        for (int i = 0, j = n; i < square.length; i++, j += dN) {
            answer += square[i][j];
        }
        
        return answer;
    }

    public static enum Diagonal {
        TOP_LEFT_TO_BOTTOM_RIGHT,
        TOP_RIGHT_TO_BOTTOM_LEFT;
    }
}