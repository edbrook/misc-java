import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class NegativeSubArray {

    private static int countNegativeSubArrays(int[] array) {
        int count = 0;
        int subSum;
        for (int left = 0; left < array.length; left++) {
            for (int right = left; right < array.length; right++) {
                subSum = sumSubArray(array, left, right);
                count += subSum < 0 ? 1 : 0;
                if (subSum < 0) {
                    System.err.printf("[%d:%d] => %d\n", left, right, subSum);
                }
            }
        }
        return count;
    }

    private static int sumSubArray(int[] array, int left, int right) {
        return IntStream.rangeClosed(left, right)
                    .map(i -> array[i])
                    .sum();
    }

    public static void main(String[] args) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        ) {
            int n = Integer.parseInt(in.readLine());
            int[] array = readIntegers(in);
            if (array.length != n) {
                throw new RuntimeException("Unexpected number of integers!");
            }
            System.out.println(countNegativeSubArrays(array));
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
