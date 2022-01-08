
/**
 * @author 陈玉轩
 * @date 2022-01-06
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        // 初始化数据
        int[] sources  = {5,8,6,8,7,3,6,5,6,4};

        // 归并排序
        toMergeSort(sources);

        // 输出数据
        for(int item : sources) {
            System.out.print(item + "-");
        }
    }

    private static void toMergeSort(int[] sources) {
        // 获取当前数据长度
        int length = sources.length;

        // 如果当前数据小于1则不需要排序
        if(length <= 1) {
            // 放弃后续操作
            return;
        }

        // 否则执行归并排序
        mergeSort();
    }

    private static void mergeSort(){
        merge();
    }

    private void merge(int[] sources, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        // 记录新的值
        int[] lSources;

        for ( int i = n1 ; i < n1 ; i++ ) {
            // 
        }
    }
}
