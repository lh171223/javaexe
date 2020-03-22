package offer.Array;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/22 12:49
 *
 * 【一】二维数组的查找
 * 【二】旋转数组的最小数字
 *
 */
public class BiArray {

    /*
    【一】二维数组的查找
    在一个二维数组中（每个一维数组的长度相同），
    每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int [][] array) {
        if (array == null)
            return false;
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                if (array[i][j] > target) //仅此步因为有序而考虑了优化
                    break;
                if (array[i][j] == target)
                    return true;
            }
        }
        return false;
    }
    /*
    矩阵有序，从左下角来看，向上数字递减，向右数字递增
    因此从左下角开始查找，当要查找数字比左下角数字大时，右移
    要查找数字比左下角数字小时，上移
     */
    public boolean Find1(int target, int [][] array) {
        if (array == null)
            return false;
        int i,j;
        for (i=array.length-1,j=0;i>=0 && j<array[0].length;){
            if (target == array[i][j])
                return true;
            if (target < array[i][j]){
                i--;
                continue;
            }
            if (target > array[i][j]){
                j++;
            }
        }
        return false;
    }

    public boolean Find2(int target, int [][] array) {
        if (array == null)
            return false;
        boolean flag = false;
        int row = array.length;
        int column = array[0].length;
        int i = 0;
        int j = column-1;
        while (i<row && j>=0){
            int val = array[i][j];
            if (target>val)
                i++;
            else
                if (target < val)
                    j--;
                else {
                    flag = true;
                    break;
                }
        }
        return flag;
    }

    /*
    【二】旋转数组的最小数字
    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
    NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

    解题：二分查找
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0)
            return 0;
        int l = 0;
        int r = array.length - 1;
        while (l < r){
            int m = (l+r)/2;
            if (array[l] > array[m]){
                r = m;
                continue;
            }
            l = m+1;
            if (array[l] < array[r])
                break;
        }
        return array[l];
    }



}
