package tengxun;

import java.util.Scanner;

public class fenzu {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();//测试用例数
        int n = sc.nextInt();//员工数量
        int[] x = new int[n];//战力值/人
        for (int i =0;i<x.length;i++)
            x[i] = sc.nextInt();

        int nA = n/2;
        int nB = n-n/2;
        int sumA = 0;
        int sumB = 0;
        int low = 0;
        int high = x.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(x[mid]<x[low])
                low = mid+1;
            if(x[mid]>x[high])
                high = mid-1;

        }


    }

}
