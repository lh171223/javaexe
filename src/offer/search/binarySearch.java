package offer.search;
//折半查找
public class binarySearch {
    //一维数组折半查找
     public int oneSearch(int[] arr,int ele){
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
                 return mid;
         }
         return -1;
     }
     //二维数组折半查找
    public void twoSearch(int[][] matrix,int ele){
         for (int i=0;i<matrix.length;i++){//遍历每行
             System.out.println(oneSearch(matrix[i],ele));
         }
    }

}
