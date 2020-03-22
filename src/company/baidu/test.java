package company.baidu;
import java.util.*;

public class test {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();//积数
        int b = sc.nextInt();//积数
        int n = sc.nextInt();//n个a
        //1.计算乘积 2.遍历相加
        int sum = 0;
        int ji = (int)Math.pow(a,n) * b;
        String str = String.valueOf(ji);//变换成String类型计算长度并遍历
        for (int i=0;i<str.length();i++){
            sum+=str.charAt(i) - '0';//字符形式减去ascll码值才是数字
        }
        System.out.print(sum);
    }


}
