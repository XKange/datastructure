package SortAlgorithm.heapsort;

public class HeapSort {
    public static void adjustMinHeap(int[] a, int pos, int len){
        int temp;
        int child;
        for (temp = a[pos]; 2 * pos + 1 <= len; pos=child){
            child = 2* pos + 1;
            if (child < len && a[child] > a[child + 1])
                child++;
            if (a[child] < temp)
                a[pos] = a[child];
            else
                break;
        }
        a[pos] = temp;
    }
}
