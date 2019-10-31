package meituan;/*
美团集体照
输入一行正整数，以逗号隔开
输出移动数
例：
1，1，4，2，1，3    =》3
 */
import java.util.*;

public class paizhao {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        char[] c = s.toCharArray();
//        int length = c.length;
//        int[] a = new int[length];
        int[] a = {1,1,4,2,1,3};
//        for(int i=0;i<a.length;i++)
//            a[i]=c[i];

//		System.out.print(a);
        System.out.print(fun(a));
    }

    //折半插入排序
    private static int fun(int[] a) {
        int low = 0;
        int high = a.length-1;
        int n = 0;//计数
        for(int i=0;i<a.length;i++) {  //1,1,4,2,1,3
            int temp = a[i];//站错的人
            while(low <= high) {//查找站错的位置
                int mid = (low+high)/2;
                if(a[i] <= a[mid])
                    high = mid - 1;
                else
                    low = mid +1;
            }
            for(int j=i-1;j>=low;j--) {
                a[j+1] = a[j];
                n++;
            }
            System.out.println(n);
            a[low] = temp;

        }
        for (int i=0;i<a.length;i++)
            System.out.print(a[i]);
        System.out.print('\n');
        return n;
    }
}
