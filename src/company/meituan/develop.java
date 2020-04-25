package company.meituan;

import java.util.Scanner;

/**
 * @author lh
 * @version 1.0
 * @date 2020/4/23 9:37
 */
public class develop {

    /*
    【一】大数加法
    以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和
    输入描述：输入两行，表示两个数字a和b,用双引号括起。
    输出描述：输出a+b的值，用双引号括起。
    输入例：
    "-26"
    "100"
    输出例：
    "74"
     */
    /*
    思路：字符串实现大数相加，则需要分割存放至数组，从末尾开始相加，考虑进位问题
     */
    public static void bigDataAdd(String s1,String s2){
        String result;
        char[] chars = new char[s1.length()+s2.length()+1];
        char[] chars1 = new char[s1.length()];
        char[] chars2 = new char[s2.length()];

//        return result;
    }

    /*
    【二】回文子串
    给定一个字符串，你的任务是计算这个字符串中有多少个回文子串（回文串是一个正读和反读都一样的字符串）。
    具有不同开始位置或结束位置的回文串，即使是由相同的字符组成，也会被计为是不同的子串。
    输入描述：输入仅包含一个字符串，长度不会超过 1000。
    输出描述：输出仅包含一个非负整数， 代表输入字符串有多少个回文子串。
    输入例：
    aaa
    输出例：
    6
     */
    public static int plalindrome(String s){
        if (s==null || s.equals(""))
            return 0;

        return 0;
    }


    /*
    【三】合并金币
     有 N 堆金币排成一排，第 i 堆中有 C[i] 块金币。
     每次合并都会将相邻的两堆金币合并为一堆，成本为这两堆金币块数之和。
     经过N-1次合并，最终将所有金币合并为一堆。请找出将金币合并为一堆的最低成本。
     其中，1 <= N <= 30，1 <= C[i] <= 100
     输入描述：第一行输入一个数字 N 表示有 N 堆金币；第二行输入 N 个数字表示每堆金币的数量 C[i]
     4
     3 2 4 1
     输出描述：输出一个数字 S 表示最小的合并成一堆的成本
     20
     */

    /*
    【四】最小唯一前缀
    给定一组个字符串，为每个字符串找出能够唯一识别该字符串的最小前缀。
    输入描述：给定一组个字符串，为每个字符串找出能够唯一识别该字符串的最小前缀。
    5
    meituanapp
    meituanwaimai
    dianpingliren
    dianpingjiehun
    mt
    输出描述：输出n行，每行一个字符串，依次是每个字符串的最小可唯一识别前缀
    meituana
    meituanw
    dianpingl
    dianpingj
    mt
     */

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            bigDataAdd(s1,s2);
        }
    }

}
