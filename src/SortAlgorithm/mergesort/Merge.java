package SortAlgorithm.mergesort;

/**
 * Created by kangkang on 2017/10/23
 */
//原地归并排序需要借助n的辅助空间，这个是实现整数数组的归并排序
//可以发现是在合并的时候才会有比较
public class Merge {
    private static int[] aux;    //归并排序所需的辅助数组

    public static void sort(int[] a) { //一次性分配空间
        aux = new int[a.length];
        sort(a, 0, a.length -1);
    }

    public static void sort(int[] a, int low, int high) {
        // 将数组a[low..high]排序
        if (high <= low) return;
        int mid = low + (high - low)/2;
        sort(a, low, mid);
        sort(a, mid+1, high);
        merge(a, low, mid, high);
    }

    public static void merge(int[] a, int low, int mid, int high) {
        //将a[low..mid]和a[mid+1, high]归并
        int i = low, j = mid+1;

        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > high)             a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    public static boolean less(int a, int b) {
        return a < b?true:false;
    }

    public static void print(int[] a) {
        for (int e:a) {
            System.out.print(e);
        }
    }
    public static void main(String[] args) {
        int[] a = {1,3,2,4,7,9,6,5};
        sort(a);
        print(a);
    }
}
