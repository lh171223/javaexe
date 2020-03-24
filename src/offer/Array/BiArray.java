package offer.Array;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.sql.Array;
import java.util.*;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/22 12:49
 *
 * 【一】二维数组的查找
 * 【二】旋转数组的最小数字  ---complex---
 * 【三】数组中出现次数超过一半的数字   ---simple---
 * 【四】最小的K个数  --simple--
 * 【五】连续子数组的最大和  ---simple---
 * 【六】把数组排成最小的数  --complex---
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
    【二】旋转数组的最小数字 ---complex---
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
    【三】数组中出现次数超过一半的数字  ---simple---
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
    【四】最小的K个数  --simple--
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
    【五】连续子数组的最大和  ---simple---
    HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
    今天测试组开完会后,他又发话了:
    在古老的一维模式识别中,常常需要计算连续子向量的最大和,
    当向量全为正数的时候,问题很好解决。
    但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
    例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
    给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0)
            return 0;
        int maxValue = array[0];
        for (int i=0;i<array.length;i++){//计算数组中每个数的子序列
            int sum =0;//子序列之和
            for (int j=i;j<array.length;j++){
                sum += array[j];
                maxValue = maxValue >= sum ? maxValue:sum;
            }

        }
        return maxValue;
    }

    /*
    【六】把数组排成最小的数  --complex---
    输入一个正整数数组，
    把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    思路：拼接--在String类型中常见字符串拼接 此处需要将int类型转换为String类型进行比较
     */
    public static String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        String[] str = new String[numbers.length];
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<numbers.length;i++){
            str[i] = String.valueOf(numbers[i]);
        }
        //重写sort重点比较方法！！！
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1+s2;
                String c2 = s2+s1;
                return c1.compareTo(c2);
            }
        });
        for (int i=0;i<numbers.length;i++)
            sb.append(str[i]);
        return sb.toString();
    }

    public static void main(String[] args){
        int[] array = {3,32,321};
//        System.out.println(MoreThanHalfNum(array));
//        System.out.println(GetLeastNumbers1(array,4));
//        System.out.println(FindGreatestSumOfSubArray(array));
        System.out.println(PrintMinNumber(array));

    }


}