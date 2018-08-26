import java.util.Arrays;

class AlmostSorted {

    static void almostSorted(int[] array) {
        int left = 0;
        int right = 0;
        
        // Find the first out of order number from the left
        int last = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < last) { break; }
            last = array[i];
            left = i;
        }

        // Is it sorted
        if (left == array.length - 1) {
            System.out.println("yes");
            return;
        }
        
        // Find the first out of order number from the right
        last = Integer.MAX_VALUE;
        for (int i = array.length - 1; i > left; i--) {
            if (array[i] > last) { break; }
            last = array[i];
            right = i;
        }

        // Check if _values_ at left/right, when put in the correct position,
        // don't fall outside of left and right _positions_.
        if ( (left > 0 && array[right] < array[left-1])
                || (right < array.length-1 && array[left] > array[right+1])) {
            System.out.println("no");
        } else {
            // Check swap
            boolean swap = true;
            for (int i=left+1; i<right-1; i++) {
                if (array[i] > array[i+1]) {
                    swap = false;
                    break;
                }
            }
            if (swap) {
                if (right < array.length-1 && array[left] > array[right+1]) {
                    System.out.println("no");
                } else {
                    System.out.printf("yes\nswap %d %d\n", left + 1, right + 1);
                }
            } else {
                // Check reverse
                boolean reverse = true;
                for (int i=left+1; i<right-1; i++) {
                    if (array[i] < array[i+1]) {
                        reverse = false;
                        break;
                    }
                }
                if (reverse) {
                    System.out.printf("yes\nreverse %d %d\n", left + 1, right + 1);
                } else {
                    System.out.println("no");
                }
            }
        }
    }

    public static void main(String[] args) {
        almostSorted(Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray());
    }
}