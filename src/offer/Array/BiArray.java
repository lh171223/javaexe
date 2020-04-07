package offer.Array;
import java.util.*;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/22 12:49
 *
 * 【一】和为S的两个数字
 * 【二】连续子数组的最大和  ---simple---
 * 【三】把数组排成最小的数  --complex---
 * 【四】
 * 【五】
 * 【六】滑动窗口的最大值
 * 【七】和为S的连续正数序列  --simple complex-- tcp滑动窗口的应用
 *
 */
public class BiArray {

    /*
    【一】和为S的两个数字
    输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
    如果有多对数字的和等于S，输出两个数的乘积最小的。
    输出描述：对应每个测试案例，输出两个数，小的先输出。
    思路：注意“递增排序”
    */
    //思路一：最笨方法，直接穷举
    public static ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (array.length == 0)
            return arrayList;//注意不能返回空
        int multiply = 0;
        for (int i=0;i<array.length;i++){
            for (int j=i+1;j<array.length;j++){
                if (array[i] + array[j] == sum && arrayList.size() == 0){//初次
                    arrayList.add(array[i]);
                    arrayList.add(array[j]);
                    multiply = array[i]*array[j];
                }
                if (array[i]+array[j] == sum &&  array[i]*array[j]<multiply && arrayList.size() != 0){//多次
                    arrayList.clear();
                    arrayList.add(array[i]);
                    arrayList.add(array[j]);
                    multiply = array[i]*array[j];
                }
            }
        }
        return arrayList;
    }

    /*思路二
    ①数列满足递增，设两个头尾两个指针i和j，
    若ai + aj == sum，就是答案（相差越远乘积越小）
    若ai + aj > sum，aj肯定不是答案之一（前面已得出 i 前面的数已是不可能），j -= 1
    若ai + aj < sum，ai肯定不是答案之一（前面已得出 j 后面的数已是不可能），i += 1
    ②数学问题：可以证明得出当x+y=sum,x*y的最小值时，相差最大（第一组）的两个数即是乘积最小的
    (二元函数问题，设相差间隔为d，即是y-x=d，假设y>=x，集合x+y=C，可以得出x*y=(C*C-d*d)/4，间隔d越大，乘积越小)
    O(n)
    */
    public static ArrayList<Integer> FindNumbersWithSum1(int [] array,int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (array.length == 0)
            return arrayList;//注意不能返回空
        int i=0,j=array.length-1;
        while (i<j){
            if (array[i]+array[j] == sum){
                arrayList.add(array[i]);
                arrayList.add(array[j]);
                break;
            }
            if (array[i]+array[j] > sum)
                j--;
            if (array[i]+array[j] < sum)
                i++;
        }
        return arrayList;
    }

    /*
    【二】连续子数组的最大和  ---simple---
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
    【三】把数组排成最小的数  --complex---
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


    /*
    【六】滑动窗口的最大值
    给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
    例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
    他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
    {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     */
    /*
    思路：
    滑动窗口个数 = 数组长度 - （滑动窗口大小-1）
    依次从头开始遍历数组，直至剩下最后 滑动窗口大小-1 的元素个数无需遍历；0~num.length-size
    例如：{2,3,4,2,6,2,5,1}
    数组长度为8，滑动窗口大小为3，当遍历到数组元素2时，后续元素个数不能满足滑动窗口大小，因为仅遍历至此，其下表即为8-3=5
     */
    public static ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (num.length==0 || size==0)
            return arrayList;
        for (int i=0;i<=num.length-size;i++){
            int temp = num[i];
            for (int j=i+1;j<=i+size-1;j++){
                 temp = temp >= num[j] ? temp:num[j];
            }
            arrayList.add(temp);
        }
        return arrayList;
    }

    /*
    【七】和为S的连续正数序列
    小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
    但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
    没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
    现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
    输出描述：输出所有和为S的连续正数序列。
    序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     */
    /*
    思路一：tcp滑动窗口思想应用
    双指针技术,就是相当于有一个窗口，窗口的左右两边就是两个指针，我们根据窗口内值之和来确定窗口的位置和宽度。
     */
    public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1,phigh = 2;
        while (phigh > plow){
            //由于是连续的、差值为1的一个序列，则其求和公式为（a0+an）*n/2
            int cur = (phigh+plow)*(phigh-plow+1)/2;
            if (cur == sum){//相等则将窗口范围内的所有数值添加至结果集
                ArrayList<Integer> list = new ArrayList<>();
                for (int i =plow;i<=phigh;i++)
                    list.add(i);
                result.add(list);
                plow++; //！！！phigh++同可
            }else
                if (cur < sum)//若当前窗口内的所有数值之和小于sum,则右窗口phigh右移一下
                    phigh++;
                else   //若当前窗口内的所有数值之和大于sum,则左窗口plow右移一下
                    plow++;
        }
        return result;
    }

    public static void main(String[] args){
        int[] array = {2,3,4,2,6,2,5,1};
//        System.out.println(FindNumbersWithSum1(array,10));//【一】
//        System.out.println(FindGreatestSumOfSubArray(array)); //【二】
//        System.out.println(PrintMinNumber(array)); //【三】
          System.out.println(maxInWindows(array,3));//【六】
//        System.out.println(FindContinuousSequence(100));//【七】


    }


}