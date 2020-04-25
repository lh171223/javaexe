package company.meituan;

import java.util.Scanner;

/**
 * @author lh
 * @version 1.0
 * @date 2020/4/23 19:54
 */
public class huobi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String result ="";
            int data = sc.nextInt();
            /*
            1.正数还是负数
            负数则考虑去负号加双括号，并在左括号后添加$
            2.是否含有小数点
            是的话，小数点前每三位加‘，’，小数点后保留两位，没有则填0
             */
            if (data>0 && !Character.isDigit(data)){//不是小数且正数时，直接每三位加逗号（从后往前）
                
            }
        }
    }
}
