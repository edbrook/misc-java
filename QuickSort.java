import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) { return; }
        printArray(arr, left, right);
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left++;
        int pivot = arr[pivotIndex];
        while (left <= right) {
            while (left < right && arr[left] < pivot) { left++; }
            while (right >= left && arr[right] >= pivot) { right--; }
            if (left < right) { swap(arr, left, right); } else { break; }
        }
        swap(arr, pivotIndex, right);
        return left;
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void printArray(int[] arr) {
        printArray(arr, -1, -1);
    }

    private static void printArray(int[] arr, int l, int r) {
        for (int i = 0; i < arr.length; i++) {
            if (i == l) System.out.print("[");
            System.out.print(arr[i]);
            if (i == r) System.out.print("]");
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray();
        
        quickSort(arr);
        printArray(arr);
    }
}
