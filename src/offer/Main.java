package offer;
/*
中位数：排序后数组的中间值。若是奇数个：最中间的那个数；若是偶数个：最中间两个数的平均数
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        //中位数
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//初始数组大小
        int x = sc.nextInt();//新数组的median数
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i=0;i<n;i++)
            arr.add(sc.nextInt());
        Collections.sort(arr);
        for (int ele:arr
             ) {
            System.out.print(ele);
            System.out.print(" ");
        }
        int sub;
        if (arr.contains(x)){
        for (int i=0;i<n;i++){
            if (x==arr.get(i)){
                sub=i;
                if (n-1-sub>sub){
                    System.out.println(n-1-sub-1);
                }else
                    System.out.println(n-1-sub);
                break;
            }
        }
        }else
            if (count(arr,x) == n)
                System.out.print(n+1);
            else
                System.out.print(arr.size() - count(arr,x) - count(arr,x));

    }

    private static int count(ArrayList<Integer> a,int x){
        //计算比x小的数的个数
        for (int i=0;i<a.size();i++){
            if (a.get(i) >x ){
                return i;
            }
        }
        return a.size();
    }

}