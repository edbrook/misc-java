import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BeautifulDays {

    static int beautifulDays(int i, int j, int k) {
        return (int) IntStream.range(i, j+1)
                .filter(n -> Math.abs(n - reverseInt(n)) % k == 0)
                .count();
    }

    private static int reverseInt(int n) {
        int nReversed = 0;
        while (n > 0) {
            nReversed = nReversed * 10 + (n % 10);
            n /= 10;
        }
        return nReversed;
    }

    public static void main(String[] args) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        ) {
            int[] values = readIntegers(in);
            if (values.length != 3) {
                throw new RuntimeException("Got unexpected number of heights!");
            }

            int i = values[0];
            int j = values[1];
            int k = values[2];

            System.out.println(beautifulDays(i, j, k));
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