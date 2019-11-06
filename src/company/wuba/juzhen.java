package company.wuba;

import java.util.Scanner;

public class juzhen {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();//m*n矩阵
        int n = sc.nextInt();
        int sum = 0;
        sum = 2*(m-1)+n-2;
        System.out.println(sum);

    }
}
