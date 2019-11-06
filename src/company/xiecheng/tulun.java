package company.xiecheng;

import java.util.*;

public class tulun {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int BestMatch(int[] LimitArray, int[][] DegMatrix) {//a[i] p[i,j]
        int res =0;
        for (int i=0;i<LimitArray.length;i++){//求出每个区域的最大喜好程度
            for (int j=0;j<LimitArray[i];j++){//员工数
                res += DegMatrix[i][j];

            }
        }
        return res;

    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _LimitArray_size = 0;
        int _DegMatrix_rows = 0;
        int _DegMatrix_cols = 0;
        _DegMatrix_rows = Integer.parseInt(in.nextLine().trim());//M 员工
        _DegMatrix_cols = Integer.parseInt(in.nextLine().trim());//N 区域
        _LimitArray_size = _DegMatrix_cols;//区域数

        int[] _LimitArray = new int[_LimitArray_size];
        int _LimitArray_item;
        for(int _LimitArray_i = 0; _LimitArray_i < _LimitArray_size; _LimitArray_i++) {
            _LimitArray_item = Integer.parseInt(in.nextLine().trim());
            _LimitArray[_LimitArray_i] = _LimitArray_item;//第三行 每个区域可以容纳的员工数
        }

        int[][] _DegMatrix = new int[_DegMatrix_rows][_DegMatrix_cols];
        for(int _DegMatrix_i=0; _DegMatrix_i<_DegMatrix_rows; _DegMatrix_i++) {
            for(int _DegMatrix_j=0; _DegMatrix_j<_DegMatrix_cols; _DegMatrix_j++) {
                _DegMatrix[_DegMatrix_i][_DegMatrix_j] = in.nextInt();

            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        res = BestMatch(_LimitArray, _DegMatrix);//a[i] p[i,j]
        System.out.println(res);//String.valueOf(res ? 1 : 0)
    }
}
