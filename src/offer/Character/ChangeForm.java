package offer.Character;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/30 15:37
 *
 * 【一】把字符串转换成整数  --未写完
 * 【二】构建乘积数组
 * 【三】正则表达式匹配  --- complex ---
 * 【四】表示数值的字符串
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
    /*越界的简单解决方案：让符号位参与运算，并合理利用 INT_MAX/10
    参考：https://blog.nowcoder.net/n/eb66593eb79a4428a72e385adcfce6dd?f=comment
     */
    public static int StrToInt(String str) {
        if (str.equals("") || str.length() ==0)
            return 0;
        int flag = 1;//数值化正负标记位
        int overValue =0;//判断是否越界
        if (str.charAt(0) == '-')
            flag = -1;
        int sum = 0;
        for (int i= (str.charAt(0)=='-'||str.charAt(0)=='+')?1:0;i<str.length();i++){//第一位考虑正负号
            if (str.charAt(i)<48 || str.charAt(i)>57)//非数值情况 单位数值：0~9
                return 0;
            overValue = flag*sum - 0x7FFFFFFF/10+(((flag+1)/2+str.charAt(i)-48>8)?1:0);
            if (overValue>0)
                return 0;
            sum = sum*10 + flag*(str.charAt(i)-48);//①让符号位参与运算
        }
        //考虑溢出 -2147483648~2147483647，即是int [-2^31,2^31-1]
        //0x7FFFFFFF：除首位0外，其余都是1，最大的int整型数（0表示是整数）；0x80000000：除首位为1，其余均为0（负数）
        //最大正数的绝对值小于最小负数绝对值，所以当值为 INT_MIN 时，将导致结果出错
        return sum;
    }

    public static int StrToInt1(String str) {
        int length = str.length();
        int isNegtive = 1, overValue = 0;
        int digit = 0, value = 0;

        if (length == 0) return 0;
        else {
            int idx = 0;
            if (str.charAt(0)== '-') { isNegtive = -1; idx = 1;}
            else if (str.charAt(0)== '+') {idx = 1;}

            for (; idx<length; idx++) {
                digit = str.charAt(idx)-'0';
                // overValue表示本轮循环是否会越界
                overValue = isNegtive*value - 0x7FFFFFFF/10
                        + (((isNegtive+1)/2 + digit > 8) ? 1:0);

                if (digit<0 || digit>9) return 0;
                else if (overValue > 0) return 0;

                value = value*10 + isNegtive*digit;
            }
            return value;
        }
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
    【三】正则表达式匹配  --- complex ---
    请实现一个函数用来匹配包括'.'和'*'的正则表达式。
    模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
    在本题中，匹配是指字符串的所有字符匹配整个模式。
    例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    /*
    匹配下一个字符，则存在匹配成功或失败。
    考虑pattern下一个字符可能是'*'，分两种情况讨论：即下一个字符是'*'还是不是
    1.不是时，直接匹配当前字符。成功则继续，否则返回false（成功情况：①当前字符为'.' ②两个字符相同）
    2.是时，‘*’可以代表0个或多个
    ①匹配0个时，当前字符不变，pattern当前字符后移两位，跳过这个'*'字符（无需用到此符号）
    ②匹配1个或多个时，str当前字符移向下一个，pattern当前字符不变。直到回到了上边的情况①；
    当匹配多余一个字符时，相当于从str的下一个字符继续开始匹配
     */
    public boolean match(char[] str, char[] pattern)
    {
        if (str == null || pattern==null)
            return false;
        int strIndex = 0;
        int pattIndex =0;
        return matchCore(str,strIndex,pattern,pattIndex);
    }

    private boolean matchCore(char[] str,int strIndex,char[] pattern,int pattIndex){
        //有效性检验
        if (strIndex == str.length && pattIndex == pattern.length)
            return true;
        //pattern先到尾，匹配失败
        if (strIndex != str.length && pattIndex == pattern.length)
            return false;
        //情况2：模式的第2个字符是'*'。若第1个字符匹配成功，则分3种匹配模式
        if (pattIndex+1<pattern.length && pattern[pattIndex+1]=='*') {
            if ((strIndex != str.length && pattern[pattIndex] == str[strIndex]) ||
                    (pattern[pattIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, pattIndex + 2)//匹配0个字符，模式后移2
                        || matchCore(str, strIndex + 1, pattern, pattIndex + 2)//匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, pattIndex);//*匹配多个：匹配1个，再匹配str中的下一个
            }
            else //若字符串第1个字符跟模式第1个字符匹配不成功，则模式后移2个字符，继续匹配
                return matchCore(str, strIndex, pattern, pattIndex + 2);
        }
        //情况1：模式的第2个字符不是'*'，且第1个字符匹配成功，则均后移1位，否则返回false
        if ((strIndex!=str.length && pattern[pattIndex]==str[strIndex])
        || (pattern[pattIndex]=='.' && strIndex!=str.length))
            return matchCore(str,strIndex+1,pattern,pattIndex+1);
        return false;
    }


    /*
    【四】表示数值的字符串
    请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
    但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    /*
    思路：什么情况下会不是数值？
    1. 只包含数字、Ee、.、+-情况
    Ee后必须含有数字
    .只能存在一位，且左侧只能是数值，右侧存在位于最后一位的情况
    +-必须在第一位
    2. 除1外还包含其他字符  false
    ASCII: 数值 48~57 ；大写字符 65~90 ；小写字符 97~122

    设置一个标注，标记符号、小数点和e是否出现过，更为简便
     */
    public static boolean isNumeric(char[] str) {
        //标记符号、小数点、e是否出现过
        boolean sign=false,decimal=false,hasE=false;
        for (int i=0;i<str.length;i++){
            if (str[i] == 'e' || str[i] == 'E'){
                if (i == str.length -1)//不能是最后一位数，必须后面接数字
                    return false;
                if (hasE)//不能同时存在两个e
                    return false;
                hasE = true;//已经发现e时标注为true，方便判断是否出现第二次e
            }else
                if (str[i] == '+' || str[i] == '-'){//首字位置，或E之后
                    if (!sign && i>0 && str[i-1]!='e' && str[i-1]!='E')//第一次出现，必须时第一位或e之后
                        return false;
                    if (sign && str[i-1] != 'e' && str[i-1] != 'E') //第二次出现+-，必须在E之后
                        return false;
                    sign = true;
                }else
                    if (str[i] == '.'){//e后不能接小数点；小数点不能出现两次
                       if (hasE || decimal)
                           return false;
                       decimal = true;
                    }
                    else
                        if (str[i]<'0' || str[i]>'9')//不合法字符
                            return false;
        }
        return true;
    }
    /*
    正则表达式匹配
     */
    public static boolean isNumeric1(char[] str) {
        String s=String.valueOf(str);
        //return s.matches("[+-]?[0-9]*(.[0-9]*)?([eE][+-]?[0-9]+)?");
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }

    public static void main(String[] agrs){
        System.out.println(StrToInt("-2147483649"));//【一】
//        String str = "-1E-16";
//        char[] chars = str.toCharArray();
//        System.out.println(isNumeric(chars));
    }

}
