/**
 * Created by zhaoyigang on 2017/7/14.
 */
public class ArrayUtils {

    public static void swap(int[] a, int index1, int index2){
        int tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }
}
