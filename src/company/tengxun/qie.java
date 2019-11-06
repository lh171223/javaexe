package company.tengxun;

import java.util.Scanner;

public class qie {
    public static void main(String[] args){
        String result = "NO";
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();//组数
        for (int i=0;i<t;i++){
            int n = sc.nextInt();//第一行 字符串长度
            String s = sc.next();//第二行 字符串s
            if (s.length()<n || n < 11)
                result = "NO";
            if (n>10 && s.substring(0,n-10).contains("8"))
                result = "YES";
            System.out.println(result);
        }
    }
}
