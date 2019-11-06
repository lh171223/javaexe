package offer;

import java.util.Scanner;
//https://www.nowcoder.com/practice/47232470945644458213ddd07580e121
public class Main{
    public static void main(String[] args){
        //中位数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//初始数组大小
        int x = sc.nextInt();//新数组的median数
        int[] arr = new int[n];
        for (int i=0;i<n;i++)
            arr[i] = sc.nextInt();
        int subscript =0;
        for (int i=0;i<n;i++){
            if (arr[i] == x){
                subscript = i;
                break;
            }
        }
        int number=0;
        number = subscript*2-n;
        System.out.print(number);
    }
}