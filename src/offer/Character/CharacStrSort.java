package offer.Character;

import java.util.*;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/22 22:39
 *
 * 字符串的排列
 *
 */
public class CharacStrSort {

    /*
    【一】字符串的排列
    输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    输入描述：输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     解题： 快速排序
     参考：字典序全排练算法研究 https://www.cnblogs.com/pmars/archive/2013/12/04/3458289.html
     ① 字典序排列算法（非递归）
     1.从尾部向前扫描，找到第一个P(i-1) < P(i)的位置
     2.从i位置往后扫描，找到最后一个大于P(i-1)的数，此处位置记为K
     3.交换i-1与K两位置的数值
     4.倒序i位置后的所有数值

     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str == null || str.length()==0)
            return list;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);//对输入的字符数组进行排序（从小到大） 为什么？
        list.add(String.valueOf(chars));//将原始的字符串（排序后的）加入列表中
        int len = chars.length;
        while (true){
            int lIndex = len-1;//1.从尾部开始扫描
            int rIndex;
            while (lIndex>=1 && chars[lIndex-1]>=chars[lIndex])
                lIndex--;//lINndex-1的值小于lIndex值 第一个（从尾部）
            if (lIndex == 0)//递减序列
                break;
            rIndex = lIndex;//2.i处往后扫描 找到最后一个大于lIndex-1处的值
            while (rIndex<len && chars[rIndex]>chars[lIndex-1])
                rIndex++;
            swap(chars,lIndex-1,rIndex-1);//3.交换
            reverse(chars,lIndex);//4.倒序i位置（最小值lIndex-1之后的）后的所有数值
            list.add(String.valueOf(chars));
        }
        return list;
    }

    //倒序k（包括k）位置后的所有数值（两两元素交换实现 + 次数限制）
    private void reverse(char[] chars,int k){
        if (chars == null || chars.length <= k)
            return;
        int len = chars.length;
        for (int i=0;i<(len-k)/2;i++){//len-k为需要交换的数的个数；除以2表示需要两两交换的次数
            int m = k+i;//从第k位置的数值与尾部第一个数值len-1交换开始
            int n = len-1-i;
            if (m<=n)
                swap(chars,m,n);
        }
    }

    /*
    ②递归方法
    【例】递归全排列 1 2 3 4 5
    1，for循环将每个位置的数据交换到第一位
            swap(1,1~5)
    2，按相同的方式全排列剩余的位
     */
    public ArrayList<String> Permutation1(String str){
        ArrayList<String> list = new ArrayList<>();
        if (str != null && str.length()>0){
            PermutationHelper(str.toCharArray(),0,list);
            Collections.sort(list);
        }
        return list;
    }

    private void PermutationHelper(char[] chars,int i,ArrayList<String> list){
        if (i==chars.length-1)
            list.add(String.valueOf(chars));
        else {
            Set<Character> characters = new HashSet<>();//
            for (int j=i;j<chars.length;++j){//全排列起始位置：j ；终止位置：chars.length
                if (j==i || !characters.contains(chars[j])){
                    characters.add(chars[j]);
                    swap(chars,i,j);
                    PermutationHelper(chars,i+1,list);//i+1：将每个位置的数据交换到第一位
                    swap(chars,j,i);
                }
            }
        }
    }

    //交换两个数
    private void swap(char[] c,int i,int j){
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }




}
