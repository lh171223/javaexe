package offer.MatrixArray;
/*
有序的二维数组（矩阵）中查数：https://www.nowcoder.com/practice/dd5b5b2df5f84bae9b26c99a0a8f8660
 */
import java.util.Scanner;

/*
矩阵中进行折半查找：多个（行数）一位数组的折半查找
*/

public class matrixBinarySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
                matrix[i][j] = sc.nextInt();
        int element = sc.nextInt();
        System.out.println(twoSearch(matrix,element));
    }
    //二维数组查找
    private static boolean twoSearch(int[][] arr,int ele){
        for(int i=0;i<arr.length;i++){
            if(oneSearch(arr[i],ele))
                return true;
        }
        return false;
    }
    //一维数组查找
    private static boolean oneSearch(int[] arr,int ele){
        int low = 0;
        int high = arr.length-1;
        int mid;
        while (low<=high){
            mid = (low+high)/2;
            if (arr[mid] > ele)
                high = mid-1;
            else if (arr[mid] < ele)
                low = mid+1;
            else
                return true;
        }
        return false;
    }

}



