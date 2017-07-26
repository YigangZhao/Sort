import com.sun.scenario.effect.Merge;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by zhaoyigang on 2017/7/2.
 */
public class Sort {

    public void selectSort(int[] a){
        int m = a.length;
        for (int i = 0; i < m - 1; i++){
            int k = i;
          for (int j = i + 1; j < m; j++){
              if (a[k] > a[j])
                  k = j;
            }
            int tmp = a[i];
            a[i] = a[k];
            a[k] = tmp;
        }
    }

    public void doubleSelectSort(int[] a){
        int m = a.length;
        for (int i = 0; i < m/2; i++){
            int min = i; int max = i;
            //随着最大值不断的放到最后，j的值也会变小
            for (int j = i + 1; j < m - i; j++){
                if (a[min] > a[j]){
                    min = j;
                    continue;
                }
                if (a[max] < a[j]){
                    max = j;
                }
            }
            int tmp;
            tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
            tmp = a[m - 1 - i];
            a[m - 1 - i] = a[max];
            a[max] = tmp;
        }
    }

    public void insertSort(int[] a){
        int m = a.length;
        for (int i = 1; i < m; i++){
            if (a[i] < a[i-1]){
                int j = i;
                int x = a[i];
                while (j > 0 && x < a[j - 1]){
                    a[j] = a[j - 1];
                    j--;
                }
                a[j] = x;
            }
        }
    }

    private void shellInsertSort(int[] a, int dk){
        int m = a.length;
        for(int i = dk; i < m; i = i + dk){
            if (a[i] < a[i - dk]){
                int j = i;
                int x = a[i];
                while (j > 0 && x < a[j - dk]){
                    a[j] = a[j - dk];
                    j = j - dk;
                }
                a[j] = x;
            }
        }
    }

    public void shellSort(int[] a){
        int dk = a.length/2;
        while (dk >= 1){
            shellInsertSort(a, dk);
            dk = dk/2;
        }
    }

    public void heapSort(int[] a){
        if (a == null || a.length <= 1){
            return;
        }

        buildMaxHeap(a);

        for (int i = a.length - 1; i > 0; i--){
            ArrayUtils.swap(a, 0, i);

            adjustHeap(a, i, 0);
        }

        //ArrayUtils.print(a);

    }

    public void buildMaxHeap(int[] a){
        int index = a.length / 2;
        for (int i = index; i >= 0; i--){
            adjustHeap(a, a.length, i);
        }
    }

    public void adjustHeap(int[] a, int heapSize, int index){
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int lastest = index;
        if (left < heapSize && a[index] < a[left]){
            lastest = left;
        }
        if (right < heapSize && a[lastest] < a[right]){
            lastest = right;
        }

        if (lastest != index){
            ArrayUtils.swap(a, index, lastest);
            adjustHeap(a, heapSize, lastest);
        }

    }


    public void simpleBubbleSort(int [] a){
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length - i - 1; j++){
                if (a[j] > a[j + 1])
                    ArrayUtils.swap(a, j, j + 1);
            }
        }
    }

    //增加一个标致记录冒泡排序停止排序的位置
    public void bubbleSort(int[] a){
        int i = a.length - 1;
        while (i > 0){
            int pos = 0;
            for (int j = 0; j < i; j++)
                if (a[j] > a[j + 1]){
                    pos = j;
                    ArrayUtils.swap(a, j, j + 1);
                }
            i = pos;
        }
    }

    public void quickSort(int[] a){
        if (a == null || a.length <= 1){
            return;
        }

        quickSort(a, 0, a.length - 1);
    }

    public void quickSort(int[] a, int low, int high){
        if (low < high){
            int pos = partition(a, low, high);
            quickSort(a, low, pos - 1);
            quickSort(a, pos + 1 , high);
        }
    }

    public int partition(int[] a, int low, int high){
        int privotkey = a[low];
        while (low < high){
            while (low < high && a[high] >= privotkey)
                --high;
            ArrayUtils.swap(a, low, high);
            while (low < high && a[low] <= privotkey)
                ++low;
            ArrayUtils.swap(a, low, high);
        }
        return low;
    }

    public void mergeSort(int[] a){
        if (a == null || a.length <= 1){
            return;
        }

        mergeSort(a, 0, a.length - 1);
    }

    public void mergeSort(int[] a, int low, int high){
        int mid = (low + high)/2;
        if (low < high){
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    public void merge(int[] a, int low, int mid, int high){
        int[] tem = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high){
            if (a[i] < a[j]){
                tem[k++] = a[i++];
            }else {
                tem[k++] = a[j++];
            }
        }

        while (i <= mid){
            tem[k++] = a[i++];
        }

        while (j <= high){
            tem[k++] = a[j++];
        }

        for (int h = 0; h < tem.length; h++) {
            a[h + low] = tem[h];
        }


    }

    public static void main(String[] args) {
        //排好序后记得减去已完成排序的数字
        Sort sort = new Sort();
        int a[] = {6, 5, 25, 45, 68, 52, 53, 84, 21, 32, 29, 55};
        //sort.selectSort(a);
        //sort.doubleSelectSort(a);
        //sort.insertSort(a);
        //sort.shellSort(a);
        //sort.heapSort(a);
        //sort.simpleBubbleSort(a);
        //sort.bubbleSort(a);
        //sort.quickSort(a);
        //sort.mergeSort(a);
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
    }
}
