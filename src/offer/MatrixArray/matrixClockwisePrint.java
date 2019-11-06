package offer.MatrixArray;
/*
顺时针打印矩阵
从外向里、顺时针
 */
import java.util.ArrayList;
public class matrixClockwisePrint {
    //参考：https://blog.csdn.net/weixin_37672169/article/details/80207479
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> arr = new ArrayList<>();
        int up=0;
        int left=0;
        int down = matrix.length-1;
        int right = matrix[0].length-1;
        while(left<=right && up<=down){
            for (int i=left;i<=right;i++)
                arr.add(matrix[up][i]);
            up++;
            for (int i=up;i<=down;i++)
                arr.add(matrix[i][right]);
            right--;
            if (up-1 != down){
                for (int i=right;i>=left;i--)
                    arr.add(matrix[down][i]);
            }
            down--;
            if (left != right+1){
                for (int i=down;i>=up;i--){
                    arr.add(matrix[i][left]);
                }
            }
            left++;
        }
        return arr;
    }

    /*
    参考：https://www.nowcoder.com/questionTerminal/9b4c81a02cd34f76be2659fa0d54342a
    模拟魔方逆时针旋转的方法，一直做取出第一行的操作
    输出并删除第一行后，再进行一次逆时针旋转
    继续重复操作即可
     */
//    public void matrixClockwisePrint2 {
//
//    }
//    private ArrayList<Integer> turnCube(int[][] matrix){
//        int r = matrix.length;//行
//        int c = matrix[0].length;//列
//        ArrayList<Integer> new_matrix = new ArrayList<>();
//        for (int i=0;i<c;i++){
//            ArrayList<Integer> new_matrix2 = new ArrayList<>();
//            for (int j=0;j<r;j++){
//                new_matrix2.add(matrix[j][i]);
//            }
//            new_matrix.add(new_matrix2);
//        }
//        new_matrix.
//    }



}
