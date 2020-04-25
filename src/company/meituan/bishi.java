package company.meituan;

import java.util.Scanner;

/**
 * @author lh
 * @version 1.0
 * @date 2020/4/23 18:40
 */
public class bishi {
    /*
    幸运星：x1<x<x2 且 y1=y=y2 ；y1<y<y2 且x1=x=x2
    至少存在一个；当有一个不满足时直接跳出
    利用hash数组，先判断x横坐标，只判断重复hash[i]>=3时，y被夹在中间的坐标，直接计算个数
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//星星总数
        int result = 0;
        int[][] array = new int[n][n];
        while (scanner.hasNext()){
            int x = scanner.nextInt();//横坐标
            int y = scanner.nextInt();//纵坐标
            array[x][y] = 1;//直接将存在的二维坐标标记为1，表示存在这个点
        }
        boolean flag = false;
        for (int i=-n/2;i<=n/2;i++){
            int count =0;
            for (int j=-n/2;j<=n/2;j++){
//                if (!flag){
//                    continue;
//                }
                if (array[i][j]==1){
                    count++;
                }
                if (count>=3){
                    result+=count-2;
                }
            }
//            flag = true;
        }
        System.out.println(result);
    }
}
