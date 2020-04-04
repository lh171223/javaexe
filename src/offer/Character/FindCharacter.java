package offer.Character;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/25 17:14
 *
 * 【一】第一个只出现一次的字符  --simple---
 * 【二】字符流中第一个不重复的字符  complex
 */
public class FindCharacter {

    /*
   【一】第一个只出现一次的字符
   在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
   并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
   思路：①全字符串仅出现一次的字符 ②所有仅出现一次的字符中的第一个
   判断该字符是否重复出现，当未重复出现时直接输出（从头至尾，第一次输出则直接返回）
   穷举遍历
    */
    public static int FirstNotRepeatingChar(String str) {
        if (str == null)
            return -1;
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++){
            int count = 0;//计算每个字符的出现次数
            for (int j=0;j<chars.length;j++){
                if (chars[i] == chars[j])
                    count++;
                if (count>1)
                    break;
            }
//            System.out.println("count="+count);
            if (count == 1){
//                System.out.println("chars[i]="+chars[i]);
                return i;
            }
        }
        return -1;
    }

    /*
    【二】字符流中第一个不重复的字符  ---complex
    请实现一个函数用来找出字符流中第一个只出现一次的字符。
    例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    输出描述：如果当前字符流没有存在出现一次的字符，返回#字符
     */
    //思路：借助队列保存字符顺序，仅存入未重复出现元素，先进先出，首先输出第一个则是所求，输出前由于字符流特性，需要再次判断重复性
    private int[] charCnt = new int[128];//记录每个字符出现的次数
    private Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if (charCnt[ch]++ == 0) //保存未重复出现的字符 (优化的主要点！！！)
            queue.add(ch);//使用队列来维护字符出现的顺序，最后可以实现将先出现的不重复字符先输出
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        Character CHAR = null;
        char c = 0;
        //字符流的长度是随机可以增长的，因此在输出前需要再次判断是否重复
        while ((CHAR = queue.peek()) != null){
            c = CHAR.charValue();
            if (charCnt[c] == 1) //判断是否脱单（重复），未则输出
                return c;
            else
                queue.remove();//是则移除队列（重复元素）
        }
        return '#'; // 队空，返回#
    }

    //思路二：借助hash表实现
    private char[] hash = new char[256]; //存储插入字符的个数
    public void Insert1(char ch)
    {
        queue.add(ch);
        hash[ch]++;
    }
    //return the first appearence once char in current stringstream
    /*
    遍历插入的字符（按照插入的顺序，可方便得到第一个）
    hash表中个数为1时，输出该元素，否则输出#
     */
    public char FirstAppearingOnce1()
    {
        Character temp = null;
        char t=0;
        while ((temp = queue.peek())!=null){
            t = temp.charValue();
            if (hash[t] == 1)
                return t;
            else
                queue.remove();
        }
        return '#'; // 队空，返回#
    }


    public static void main(String[] args){
        System.out.println(FirstNotRepeatingChar("goole"));
    }


}
