package SortAlgorithm.quicksort;

/*
快速排序的基本实现
快速排序算法是一种基于交换的高效的排序算法，它采用了分治法的思想：

从数列中取出一个数作为基准数（枢轴，pivot）。
将数组进行划分(partition)，将比基准数大的元素都移至枢轴右边，将小于等于基准数的元素都移至枢轴左边。
再对左右的子区间重复第二步的划分操作，直至每个子区间只有一个元素。
快排最重要的一步就是划分了。划分的过程用通俗的语言讲就是“挖坑”和“填坑”。
 */

import java.util.LinkedList;

public class QuickSort {
    int partition(int[] array, int left, int right)
    {
        int i = left, j = right;
        int tmp = array[left];
        while (i < j)
        {
            while (i < j && array[j] > tmp)
                j--;
            if (i < j)
            {
                array[i] = array[j];
            }

            while (i < j && array[i] < tmp)
                i++;
            if (i < j)
            {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = tmp;
        return i;
    }
    public void quicksort(int[] array, int left, int right)
    {
        if (left > right)
            return;
        int j = partition(array, left, right);
        quicksort(array, left, j - 1);
        quicksort(array, j+1, right);
    }

    public static void main(String[] args)
    {

    }
}
