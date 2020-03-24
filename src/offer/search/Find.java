package offer.search;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/24 15:01
 *
 * 【一】整数中1出现的次数（从1至n的整数中出现1的次数） simple
 */
public class Find {

    /*
    【一】整数中1出现的次数（从1至n的整数中出现1的次数） --simple--
    求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
    为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
    ACMer希望你们帮帮他,并把问题更加普遍化,
    可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）
     思路：遍历1~n中的每个数，将每个数都存入数组中，遍历该数组中每位处是否是1即可
     */
    public static int NumberOf1Between1AndN(int n) {
        if (n == 0)
            return n;
        int count = 0;
        while (n>0){
            String str = String.valueOf(n);//转换成字符串形式
            char[] chars = str.toCharArray();//转成字符数组
            for (int i=0;i<chars.length;i++){
                if (chars[i] == '1')
                    count++;
            }
            n--;
        }
        return count;
    }


    public static void main(String[] args){
        System.out.println(NumberOf1Between1AndN(13));
    }


}
