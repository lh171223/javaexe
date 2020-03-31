package offer.Array;

import java.util.ArrayList;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/24 16:59
 * <p>
 * 【一】丑数  --complex---
 * 【二】进制转换：求1+2+3+...+n
 * 【三】进制转化：不用加减乘除做加法
 */
public class math {

    /*
    【一】丑数   ---complex---
    把只包含质因子2、3和5的数称作丑数（Ugly Number）。
    例如6、8都是丑数，但14不是，因为它包含质因子7。
    习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    思路： 我们只用比较3个数：用于乘2的最小的数、用于乘3的最小的数，用于乘5的最小的数
     */
    public static int GetUglyNumber(int index) {
        if (index<= 0)
            return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i2=0,i3=0,i5=0;
        while (list.size()<index){
            int m2 = list.get(i2)*2;
            int m3 = list.get(i3)*3;
            int m5 = list.get(i5)*5;
            int min = Math.min(m2,Math.min(m3,m5));//找到三者中最小数
            list.add(min);//取每次相乘后的最小数放入新列表中，取完i对应的最小值后，i++进行下一次新的比较（每种组合穷举比较）
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size()-1);
    }

    /*
    【二】进制转换：求1+2+3+...+n
    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */
    /*思路：短路求值原理
    逻辑与中若前面为假，则后面不计算
     */
    public static int Sum(int n) {
        int ans = n;
        boolean flag = ans>0 && (ans += Sum(n-1))>0;
        return ans;
    }

    /*
    【三】进制转化：不用加减乘除做加法
    写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    /*
    思路：二进制相加
    参考：https://www.nowcoder.com/profile/645151/codeBookDetail?submissionId=1512236
    1.不考虑进位，每步相加可作为异或操作
    2.考虑进位，每位做与操作，再向左移一位
    3.重复上述两步，各位相加
     */
    public static int Add(int num1,int num2) {
        while (num2 != 0){
            int temp = num1^num2;
            num2 = (num1 & num2)<<1;
            num1 = temp;
        }
        return num1;
    }

    public static void main(String[] args){
//        System.out.println(GetUglyNumber(10)); //【一】
//        System.out.println(Sum(100));//【二】
        System.out.println(Add(5,7));//【三】
    }

}
