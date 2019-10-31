package didi;
import java.util.*;
public class luanma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//字符串长度
        int k = input.nextInt();//题目参数
        String s = input.nextLine();//长度为n 由小写字母组成的 字符串
        char[] c = s.toCharArray();
        char[] newc = new char[n];
        ArrayList newl = new ArrayList();
        for (int i = 0; i < n; i++) {
            for(int j = i+k;j<n;j++){
                if(c[i] == c[j])
                    newl.add(0);
                else
                    newl.add(1);
            }
        }
        System.out.print(newl);

    }
}
