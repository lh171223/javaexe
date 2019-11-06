package offer.MatrixArray;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        Scanner sc = new Scanner(System.in);
        int M,N;
        M = sc.nextInt();
        N = sc.nextInt();
        while (M != -1 && N != -1) {

            int[][] matrix = new int[M][N];
            for (int i = 0; i < M; i++)
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }

        matrixClockwisePrint mcp = new matrixClockwisePrint();
        ArrayList<Integer> arr = mcp.printMatrix(matrix);

        StringBuilder str = new StringBuilder();
        for (int element:arr){
            str.append(element);
            str.append(',');
        }
        System.out.println(str.substring(0,str.length()-1));
        M = sc.nextInt();
        N = sc.nextInt();
    }
}


}
