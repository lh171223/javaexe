package offer.Character;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/28 16:41
 *
 * 【一】左旋转字符串  --simple---
 * 【二】翻转单词顺序列
 * 【三】扑克牌顺子   --medium---
 */
public class RotateReverse {

    /*
    【一】左旋转字符串  --simple---
    汇编语言中有一种移位指令叫做循环左移（ROL），
    现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
    对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
    例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
    是不是很简单？OK，搞定它！
     */
    public static String LeftRotateString(String str,int n) {
        if (str == null || n == 0)
            return str;
        if (str.length()<n)
            return "";
        String result;
        result = str.substring(n,str.length()).concat(str.substring(0,n));
        return result;
    }

    /*
    【二】翻转单词顺序列
    牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
    同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
    例如，“student. a am I”。
    后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
    Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */
    //思路：按照空格划分
    public static String ReverseSentence(String str) {
        if (str.trim().equals("")) // 注意必须加trim()
            return str;
        StringBuilder result = new StringBuilder();
        String[] strings = str.split(" ");//以空格划分
       for (int i=strings.length-1;i>=0;i--){
           if (i==0)
               result.append(strings[i]);
           else {
               result.append(strings[i]);
               result.append(" ");
           }
       }
        return result.toString();
    }

    /*
    【三】扑克牌顺子
    LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
    他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
    “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
    LL不高兴了,他想了想,决定大\小王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
    上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
    现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，如果牌能组成顺子就输出true，否则就输出false。
    为了方便起见,你可以认为大小王是0。
     */
    /*
    思路一：1~13 大王小王任意数字四张(视为0)；从54张中随机抽取五张，查看是否连续
    0可以替补任何空缺以生成连续数
    计算0的个数与空缺数，当0的个数不够以替补空缺时，不是顺子
     */
    public static boolean isContinuous(int [] numbers) {
        if (numbers.length == 0)
            return false;
        Arrays.sort(numbers);
        int count=0;//计算0的个数 大王小王
        int sum = 0;//计算所有相邻两数之间的间隔数
        for (int i=0;i<numbers.length-1;i++){
            System.out.print(i);
            if (numbers[i] == 0){
                count++;//计算数组中0的总数
                continue;
            }
            if (numbers[i] == numbers[i+1])
                return false;
            sum += numbers[i+1]-numbers[i]-1;
        }
        //当0的个数无法补充所有间隔空缺值时，则无法连续
        return sum<=count;
    }

    /*
    思路二：必须满足两个条件
    1.除0外没有重复的数
    2.max-min<5
     */
    public static boolean isContinuous1(int [] numbers) {
        if (numbers.length !=5 )
            return false;
        int[] d = new int[14];//用以判断是否具有除了0以外的重复数
        d[0] = -5;
        int max = -1;//记录两者之差，间隔
        int min = 14;
        for (int i=0;i<numbers.length;i++){
            d[numbers[i]]++;
            if (numbers[i] == 0)//只有0是可以重复的
                continue;
            if (d[numbers[i]]>1)//说明除了0还有其他重复数字
                return false;
            if (numbers[i] > max)//最大与最小值，再计算其间隔
                max = numbers[i];
            if (numbers[i] <min)
                min = numbers[i];
        }
        return  max-min < 5;
    }


    public static void main(String[] args){
//        System.out.println(LeftRotateString("abcXYZdef",3));//【一】
//        System.out.println(ReverseSentence("I am a student."));//【二】
        int[] array = {0,3,2,6,4};
        System.out.print(isContinuous1(array));//【三】
    }

}
