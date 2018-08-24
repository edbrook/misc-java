import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class PickNumbers {

    static int pickingNumbers(int[] numbers) {
        int[] frequency = new int[100];
        for (int n : numbers) {
            frequency[n]++;
        }
        int maxCount = 0;
        for (int i = 0; i < frequency.length - 1; i++) {
            maxCount = Math.max(maxCount, frequency[i] + frequency[i+1]);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        ) {
            int length = Integer.parseInt(in.readLine());
            int[] numbers = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            if (numbers.length != length) {
                throw new RuntimeException("Got unexpected number of integers.");
            }

            System.out.println(pickingNumbers(numbers));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}