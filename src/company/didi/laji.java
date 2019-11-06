package company.didi;
/*
垃圾 ： n堆
垃圾车 ： 2辆   同数量堆垃圾/辆
要求运载：最多垃圾/次
约束： a,b不能放在同一辆垃圾车上
 */
import java.util.*;
public class laji {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] mab = new int[m][2];
        for (int i=0;i<m;i++){//输入m个约束条件
            for (int j=0;j<2;j++){
                mab[i][j] = input.nextInt();
            }
        }

        System.out.print(fun(n,m,mab));
    }

    private static int fun(int n,int m,int[][] mab){


        return -1;
    }


}
