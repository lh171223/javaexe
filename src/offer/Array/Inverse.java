package offer.Array;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/26 14:12
 *
 * 【一】数组中的逆序对
 */
public class Inverse {

    /*
    【一】数组中的逆序对
    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    输入一个数组,求出这个数组中的逆序对的总数P。
    并将P对1000000007取模的结果输出。 即输出P%1000000007

    题目保证输入的数组中没有的相同的数字
    数据范围：
	对于%50的数据,size<=10^4
	对于%75的数据,size<=10^5
	对于%100的数据,size<=2*10^5

	例：输入 1,2,3,4,5,6,7,0  输出 7
	思路：①穷举遍历方法复杂度太高Q(N平方)
     */
    public static int InversePairs(int [] array) {
        if (array == null)
            return 0;
        int p =0 ;
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){ //与其后的数字相比较
                if (array[i] > array[j])
                    p++;
            }
        }
        int result = p%1000000007;
        return result;//返回时求余会溢出???
    }

    /*
    思路②：归并排序：将两个有序的序列合并为一个有序的序列
    归并排序的应用
    合并数组，合并时，出现前面的数组值array[i]大于后面组的数组值array[j]时，
    则前面数组array[i]~array[mid]都是大于array[j]的，即count += mid+1 -i
    当测试用例输出结果较大时，对每次返回的count进行求余mod(1000000007)
     */
    public static int InversePairs1(int [] array) {
        if (array == null || array.length == 0)
            return 0;
        int[] temp = new int[array.length];
        for (int i=0;i<array.length;i++)
            temp[i] = array[i];
        int count = mergeSort(array,temp,0,array.length-1);//数值过大求余
        return count;
    }

    //二路归并
    private static int mergeSort(int[] array,int[] temp,int low,int high){
        if (low == high)
            return 0;
        int mid = (low+high)>>1;//右移除以2;左移乘以2
        int leftCount = mergeSort(array,temp,low,mid)%1000000007;
        int rightCount = mergeSort(array,temp,mid+1,high)%1000000007;
        int count = 0;
        int i = mid;
        int j = high;
        int iTemp = high;
        while (i>=low && j>mid){
            if (array[i] > array[j]){
                count += j-mid;
                temp[iTemp--] = array[i--];
                if (count >= 1000000007)
                    count %= 1000000007;
            }else
                temp[iTemp--] = array[j--];
        }
        while (i>=low)
            temp[iTemp--] = array[i--];
        while (j>=mid)
            temp[iTemp--] = array[j--];
        for (int k=low;k<=high;k++)
            array[k] = temp[k];
        return (leftCount+rightCount+count)%1000000007;
    }



    public static void main(String[] args){
        int[] array = {1,2,3,4,5,6,7,0};
        System.out.println(InversePairs(array));
    }


}
