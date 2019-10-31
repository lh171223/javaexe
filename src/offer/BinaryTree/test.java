package offer.BinaryTree;
import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(" ");
        String[] s2 = sc.nextLine().split(" ");
        int[] pre = new int[s1.length];
        int[] in = new int[s2.length];
        for(int i=0;i<pre.length;i++){
            pre[i] = Integer.valueOf(s1[i]);
        }
        for(int i=0;i<in.length;i++){
            in[i] = Integer.valueOf(s2[i]);
        }
        System.out.println("pre:" + Arrays.toString(pre));
        System.out.println("in:" + Arrays.toString(in));

        //重建二叉树 reConstructBinaryTree.class
        reConstructBinaryTree root = new reConstructBinaryTree();
        TreeNode treeNode = root.ConstructBinaryTree(pre,in);
        //遍历输出 TraverseBinTree.class
        TraverseBinTree.postTraverseBinTree(treeNode);

        //二叉树==>求和树
        SumTree.BinaryToSumTree(treeNode);
        //遍历输出
        TraverseBinTree.inTraverseBinTree(treeNode);

    }


}
