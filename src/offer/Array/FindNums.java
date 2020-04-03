package offer.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/28 12:34
 *
 * 【一】数组中只出现一次的数字   --simple 需要注意返回值的设置
 * 【二】数组中出现次数超过一半的数字   ---simple---
 * 【三】最小的K个数  --simple--
 * 【四】旋转数组的最小数字  ---complex---
 * 【五】二维数组的查找
 * 【六】数组中重复的数字  --simple 需要注意返回值的设置
 *
 */
public class FindNums {

    /*
    【一】数组中只出现一次的数字  --simple 需要注意返回值的设置
    一个整型数组里除了两个数字之外，其他的数字都出现了两次。
    请写程序找出这两个只出现一次的数字。

    num1,num2分别为长度为1的数组。传出参数
    将num1[0],num2[0]设置为返回结果
     */

    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null)
            return;
        for (int i=0;i<array.length;i++){
            int count = 0;
            for (int j=0;j<array.length;j++){
                if (array[i] == array[j]){
                    count++;
                }
                if (count == 2)
                    break;
            }
            if (count == 1 && num1[0] == 0) {
                num1[0] = array[i];
                continue;
            }
            if (count == 1 && num2[0] == 0)
                num2[0] = array[i];
        }
        System.out.println(num1[0]+","+num2[0]);
    }

    /*
    【二】数组中出现次数超过一半的数字  ---simple---
    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
    由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
    如果不存在则输出0。
     */

    public static int MoreThanHalfNum(int [] array) {
        if (array == null){
            return 0;
        }
        for (int i=0;i<array.length;i++){
            int count=0;//计算每个数字出现的次数
            for (int j =0 ;j<array.length;j++){
                if (array[i] == array[j]){
                    count++;
                    if (count>(array.length)/2)
                        return array[i];//因为一个数超过一半后，必不存在第二个数超过一半，当出现该数时直接返回即可
                    if (count+array.length-j < (array.length)/2)
                        return 0;
                }
            }
        }
        return 0;
    }

    /*
    思路二：数组排序后，若有符合条件的数存在，则必定是数组中间的那个数
     */
    public static int MoreThanHalfNum1(int [] array) {
        if (array == null){
            return 0;
        }
        Arrays.sort(array);//排序
        int mid = (array.length)/2;
        int count=0;
        for (int i=0;i<array.length;i++){
            if (array[i] == array[mid])
                count++;
        }
        if (count>(array.length)/2)
            return array[mid];
        else
            return 0;
    }

    /*
    【三】最小的K个数  --simple--
    输入n个整数，找出其中最小的K个数。
    例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     */
    public static ArrayList<Integer> GetLeastNumbers(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (input.length ==0 || k==0 || k>input.length)//注意需要考虑数组长度与K大小问题
            return arrayList;
        Arrays.sort(input);
        for (int i=0;i<k;i++)
            arrayList.add(input[i]);
        return arrayList;
    }

    /*
    【四】旋转数组的最小数字 ---complex---
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

    /*
  【五】二维数组的查找
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
    【六】数组中重复的数字
    在一个长度为n的数组里的所有数字都在0到n-1的范围内。
    数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
    请找出数组中任意一个重复的数字。
    例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     */
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        for (int i=0;i<length;i++){
            int count =0;
            for (int j=i;j<length;j++){
                if (numbers[i] == numbers[j]){
                    count++;
                }
                if (count>1){
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        int[] array = {6,3,1,0,2,5,8};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        int[] duplication = new int[1];
//        System.out.println(MoreThanHalfNum(array));
//        System.out.println(GetLeastNumbers1(array,4));
//        FindNumsAppearOnce(array,num1,num2);
        System.out.println(duplicate(array,7,duplication));
    }


}

