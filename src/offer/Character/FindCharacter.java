package offer.Character;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/25 17:14
 *
 * 【一】第一个只出现一次的字符  --simple---
 *
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
    【二】
     */


    public static void main(String[] args){
        System.out.println(FirstNotRepeatingChar("goole"));
    }


}
