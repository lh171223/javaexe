package offer.search;

public class test {
    public static void main(String[] args) {
        binarySearch bs = new binarySearch();

        int arr1[]={11,15,18,19,21,25,28,29,35};
//        System.out.println(bs.oneSearch(arr1,29));  //打印key在数组中的下标

        int arr2[][]={{2,3,5},{3,4,7},{3,5,8}};
        bs.twoSearch(arr2,3);


    }
}
