package tengxun;

import java.util.Scanner;

public class niuniu {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] seline = new int[n];
        for (int i=0;i<seline.length;i++)
            seline[i] = sc.nextInt();//第二行 ai  正整数

        for (int i=1;i<=k;i++){
            int min = seline[0];
//            for (int mini = 0;mini<seline.length;mini++){
//                while (seline[mini] != 0)
//                    min = seline[mini];
//            }

            //找出数组中最小非零整数
            for(int j=1;j<seline.length;j++){
                if (seline[j] <= 0)
                    continue;
                if (min > seline[j]) //都是正整数 无需判断0
                    min = seline[j];
            }
            //打印
            System.out.println(min);
            //将序列中所有非零元素减x

            for (int ii=0;ii<seline.length;ii++){
                System.out.print(seline[ii]);
                seline[ii] = seline[ii] - min;
            }
        }

    }

}
