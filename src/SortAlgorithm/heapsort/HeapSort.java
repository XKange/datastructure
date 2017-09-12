package SortAlgorithm.heapsort;

public class HeapSort {
    // 打印函数
    public static void print(int[] a){
        for (int i:a) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }
    // 调整范围length且以i为根的节点符合大根堆
    // length参数的意义是确定调整的范围大小，因为在堆排序每轮循环只排从1到i-1个数
    public static void max_heapify(int[] a, int i, int length) {
        int left = i * 2;
        int right = left + 1;
        int largest = i;
        if (left <= length && a[left] > a[i])
            largest = left;
        if (right <= length && a[right] > a[largest])
            largest = right;
        if (largest != i) {
            // 交换
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            // 需要调整以该节点为根的子树
            max_heapify(a, largest, length);
        }
    }
    // 建堆
    // 数量为n的堆，其树叶节点是从n/2的下限+1开始到n的，所以建堆需要从n/2的下限开始下降到1
    public static void build_max_heap(int[] a) {
        for (int i = (a.length-1) / 2; i >= 1; i--) {
            max_heapify(a, i, a.length-1);
        }
    }
    /* 堆排序算法
    1.建堆
    2.交换下标为1和i的值，调整下标为1的子树 范围为i-1（因为a[i]已经是1..i中最大的了）
    */
    public static void heapsort(int[] a) {
        build_max_heap(a);
        print(a);
        for (int i = a.length-1; i >= 2; i--) {
            int temp = a[i];
            a[i] = a[1];
            a[1] = temp;
            print(a);
            max_heapify(a, 1, i -1);
            print(a);
        }
    }
    public static void main(String[] args) {
        // 因为堆排序与下标有关，必须从1开始
        int[] a = {0, 16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        heapsort(a);
        print(a);
    }
}
