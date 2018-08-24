import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class PickNumbers {

    static int pickingNumbers(int[] numbersIn) {
        int[] numbers = Arrays.copyOf(numbersIn, numbersIn.length);
        Arrays.sort(numbers);
        
        int maxCount = 0;
        int countNumbersWithinRange = 0;
        int lowestNumberInRange = numbers[0];
        int lastNumber = lowestNumberInRange;
        int countConsecutiveSameNumber = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];

            if (lastNumber != currentNumber) {
                if (Math.abs(currentNumber - lowestNumberInRange) > 1) {
                    if (Math.abs(currentNumber - lastNumber) < 2) {
                        lowestNumberInRange = lastNumber;
                        countNumbersWithinRange = countConsecutiveSameNumber;
                    } else {
                        lowestNumberInRange = currentNumber;
                        countNumbersWithinRange = 0;
                    }
                }
                countConsecutiveSameNumber = 0;
            }

            countNumbersWithinRange++;
            countConsecutiveSameNumber++;
            lastNumber = currentNumber;
            maxCount = Math.max(maxCount, countNumbersWithinRange);

            System.err.printf("%3d [cur:%d, max:%d]\n",
                    currentNumber, countNumbersWithinRange, maxCount);
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