package offer.Character;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/30 15:37
 *
 * 【一】把字符串转换成整数  --未写完
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

    public static void main(String[] agrs){
        System.out.println(StrToInt("-2147483649"));//【一】

    }

}
