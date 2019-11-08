package offer;
/*
题目：https://www.nowcoder.com/practice/47232470945644458213ddd07580e121
中位数：排序后数组的中间值。若是奇数个：最中间的那个数；若是偶数个：最中间两个数的平均数
因为仅仅是计算个数，无需将数组排序，仅需计算比中位数大、小及相等的个数，
再针对情况计算分别左右添加的个数
计算已存在的x值个数：当大小差距个数小于等于存在的x个数时，说明无须添加多余的元素情况下肯定可以得到中位数x
左边添加：小的个数<大的个数 最少情况下可以少添加一个元素，因为取整除
右边添加：小的个数>大的个数
 */
import java.util.Scanner;
public class Median {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//初始数组大小
        int x = sc.nextInt();//新数组的median数
        int[] arr = new int[n];
        for (int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        System.out.println(medianMomenta(arr,x));
    }

    //中位数:使之成为中位数需要添加的个数
    private static int medianMomenta(int[] arr,int x){
        int res=0;//需要添加的个数
        //计算比x大的、小的、相等的数的个数
        boolean isExist = false;//中位数是否存在
        int max=0;
        int min=0;
        int same=0;//具体分存在不存在情况。存在时，计算多余一个x值的个数；不存在时，自主添加一个中位数
        for (int i=0;i<arr.length;i++){
            if (arr[i] >x ){
                max++;
            }else if (arr[i]<x){
                min++;
            }else if (arr[i] == x){//计算多余中位数值的个数
                if (isExist)
                    same++;
                else
                    isExist=true;//第一次时不加入same计算
            }
        }
        //若是没有中位数，则自主添加一个（不计算same中，不影响下述计算）
        if (!isExist) res++;

        if (max > min){
            int dif = max - min;
            dif = dif>same?dif-same-1:0;
            res+=dif;
        }else if (max<min){
            int dif = min - max;
            dif = dif>same?dif-same:0;
            res+=dif;
        }
        return res;
    }

}