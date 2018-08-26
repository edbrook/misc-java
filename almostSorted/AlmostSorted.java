import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // static void as(int[] a) {
    //     List<String> s = new ArrayList<>();
    //     int last = Integer.MIN_VALUE;
    //     for (int i=0; i<a.length; i++) {
    //         s.add(a[i] > last ? "Up" : "Down");
    //         last = a[i];
    //     }
    //     System.out.println(s);
    // }

    public static void main(String[] args) {
        almostSorted(Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray());
        // int[][] qs = new int[][] {
        //     {1, 2, 3, 4},                   // yes
        //     {3, 2},                         // SWP [1:2]
        //     {3, 2, 1, 4},                   // SWP [1:3]
        //     {1, 5, 4, 3, 2, 6},             // REV [2:5]
        //     {1, 5, 3, 4, 2, 6},             // SWP [2:5]
        //     {1, 2, 8, 4, 5, 6, 7, 3, 9},    // SWAP [3:8]
        //     {3, 1, 2},                      // no
        //     {3, 1, 2, 4},                   // no
        //     {43, 650, 50, 98, 99, 101},     // no
        //     {43, 65, 1, 98, 99, 101},       // no
        //     {10, 50, 1, 2, 3, 20, 60},      // no
        //     {10, 50, 100, 200, 300, 20, 60},// no
        //     {10, 50, 3, 2, 1, 20, 60},      // no
        //     {10, 50, 300, 200, 100, 20, 60},// no
        //     {1, 2, 8, 6, 5, 4, 7, 3, 9},    // no (SWAP [3:8] & REV [4:6])
            
        //     // yes\nswap 12 95
        //     {
        //         4104,8529,49984,54956,63034,82534,84473,86411,92941,95929,
        //         108831,894947,125082,137123,137276,142534,149840,154703,174744,180537,
        //         207563,221088,223069,231982,249517,252211,255192,260283,261543,262406,
        //         270616,274600,274709,283838,289532,295589,310856,314991,322201,339198,
        //         343271,383392,385869,389367,403468,441925,444543,454300,455366,469896,
        //         478627,479055,484516,499114,512738,543943,552836,560153,578730,579688,
        //         591631,594436,606033,613146,621500,627475,631582,643754,658309,666435,
        //         667186,671190,674741,685292,702340,705383,722375,722776,726812,748441,
        //         790023,795574,797416,813164,813248,827778,839998,843708,851728,857147,
        //         860454,861956,864994,868755,116375,911042,912634,914500,920825,979477
        //     }
        // };

        // for (int[] q : qs) {
        //     almostSorted(q);
        //     System.out.println();
        // }
    }
}