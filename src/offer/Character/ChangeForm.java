package offer.Character;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/30 15:37
 *
 * 【一】把字符串转换成整数  --未写完
 * 【二】构建乘积数组
 * 【三】正则表达式匹配
 *
 */
public class ChangeForm {

    /*
    【一】把字符串转换成整数
    将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
    数值为0或者字符串不是一个合法的数值则返回0
    输入描述：输入一个字符串,包括数字字母符号,可以为空
    +2147483647
    1a33
    输出描述：如果是合法的数值表达则返回该数字，否则返回0
    2147483647
    0
     */
    public static int StrToInt(String str) {
        if (str.equals("") || str.length() ==0)
            return 0;
        char[] chars = str.toCharArray();
        int flag = 0;
        if (chars[0] == '-')
            flag = 1;
        int sum = 0;
        for (int i= flag;i<chars.length;i++){//第一位考虑正负号
            if (chars[i] == '+')
                continue;
            if (chars[i]<48 || chars[i]>57)//非数值情况 单位数值：0~9
                return 0;
            sum = sum*10 + chars[i]-48;
        }
        //考虑溢出 --- 未写完
        
        return flag==0 ? sum : sum*(-1);
    }

    /*
    【二】构建乘积数组
    给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
    其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
    不能使用除法。
    （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     */
    //思路-参考：https://www.nowcoder.com/profile/645151/codeBookDetail?submissionId=1516453
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        if (n !=0){
            B[0] = 1;
            //计算下三角连乘（从上往下）
            for (int i=1;i<n;i++)
                B[i] = B[i-1]*A[i-1];
            int temp =1;
            //计算上三角连乘()
            for (int j=n-2;j>=0;j--){
                temp *= A[j+1];
                B[j] *= temp;
            }
        }
        return B;
    }

    /*
    【三】正则表达式匹配
    请实现一个函数用来匹配包括'.'和'*'的正则表达式。
    模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
    在本题中，匹配是指字符串的所有字符匹配整个模式。
    例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    public boolean match(char[] str, char[] pattern)
    {

    }


    public static void main(String[] agrs){
        System.out.println(StrToInt("-2147483649"));//【一】

    }

}
