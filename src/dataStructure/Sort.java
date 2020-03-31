package dataStructure;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/22 9:22
 */
public class Sort {

    /*
    归并排序
    平均时间复杂度：O(nlogn)
    时间复杂度：O(nlogn)
    空间复杂度：O(N)
    所需额外空间：递归 O(n+logn) ；非递归O(n)
    稳定性：稳定
     */

    /*
    ①递归方法
    如果一个数组有n个数据，则可以把这个数组看作n个有序的子序列，
    每个子序列的长度为1，然后两两归并，就能得到[n/2]个长度为2（或者1，落单的)的字序列，
    再不断地两两归并，直到得到一个长度为n的有序数组。
    缺点：递归的归并排序需要深度为logN的栈空间，会造成时间空间的性能损耗
     */

    public void MergeSort(int[] arr){
        sortMRecursion(arr,0,arr.length-1);
    }

    private void sortMRecursion(int[] arr,int L,int R){
//        if (L == R) //另一种表示方法。子序列只有一个元素时，结束递归
////            return;
        if (L < R){ //子序列只有一个元素时，结束递归
            int mid = (L+R)/2;
            sortMRecursion(arr,L,mid);
            sortMRecursion(arr,mid+1,R);
            merge(arr,L,mid,R);
        }
    }

    /*
  ②非递归方法
  从归并段的长度为1开始，一次使归并段的长度变为原来的2倍。
  在每趟归并的过程中，要注意处理归并段的长度为奇数和 最后一个归并段的长度和前面的不等的情况，需要做一下处理
   */
    public void MergeSort2(int[] arr){
        int k =1; //K个元素
        while (k < arr.length){
            sortM(arr,k);
            k *= 2;
        }
    }

    //将数组中相邻的有k个元素的子序列进行归并
    private void sortM(int[] arr,int k){
        int n = arr.length;
        int i = 0;
        int j;
        //从前往后，将两个长度为k的子序列合并为一个
        while (i < n-2*k+1){ //即至少除两倍K空间外还剩余i个元素位置（剩余数组长度），当长度不足2K时弹出
            merge(arr,i,i+k-1,i+2*k-1);//合并从i位置开始的 连续两个k子序列
            i += 2*k;//i移动至合并后的序列第一个位置（最后：是剩余部分的首位置 i~n-1）
        }
        //将长度不足两两合并merge的部分和前面的合并merge起来
        if (i < n-k) //n-k>0至少，说明剩余长度至少有k个元素？为什么剩余部分长度必定大于K？
            merge(arr,i,i+k-1,n-1);//以K为分界点，合并剩余部分
    }

    //左右两部分 部分排序
    private void merge(int[] arr,int L,int mid,int R){
        int[] temp = new int[R-L+1];//辅助数组
        int i = 0 ;//辅助数组下标--类似新辅助链表的指针
        int p1 = L;
        int p2 = mid + 1;
        //比较左右两部分元素，将较小一方的元素填入temp中
        while (p1<=mid && p2<=R){
            temp[i++] = arr[p1]<=arr[p2] ? arr[p1++]:arr[p2++];
        }
        //较短的数组遍历完成后，将较长数组的剩余部分全部填入新数组中
        while (p1 <= mid) //若是合并链表，此处直接用if将指针指向剩余链表即可，具体可见offer/LinkedList/ListList【四】
            temp[i++] = arr[p1++];
        while (p2 <= R)
            temp[i++] = arr[p2++];
        //把最终的排序结果复制给原数组（此函数是完成新L-R部分的数组排序，需要将此部分复制到原始需要排序的整个大数组中）
        for (i = 0; i< temp.length;i++)
            arr[L+i] = temp[i];
    }

    /*
    ②非递归方法
    用迭代代替递归实现
     */


//    @Test
//    public void test() {
//        int[] a = {49, 38, 65, 97, 76, 13, 27, 50};
//        MergeSort(a);
//        System.out.println("排好序的数组：");
//        for (int e : a)
//            System.out.print(e + " ");
//    }


}
