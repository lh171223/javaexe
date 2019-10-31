package offer.BinaryTree;

public class TraverseBinTree {

    //将二叉树先序遍历，用于测试结果 “根左右”
    public static void preTraverseBinTree(TreeNode node){
        if (node==null) {
            return;
        }
        System.out.print(node.val+" ");
        if (node.left!=null) {
            preTraverseBinTree(node.left);
        }
        if(node.right!=null){
            preTraverseBinTree(node.right);
        }
    }

    //将二叉树中序遍历，用于测试结果 “左根右”
    public static void inTraverseBinTree(TreeNode node){
        if (node==null) {
            return;
        }
        if (node.left!=null) {
            inTraverseBinTree(node.left);
        }
//        System.out.print(node.val+",");
        System.out.print(node.sum+" ");//求和树
        if(node.right!=null){
            inTraverseBinTree(node.right);
        }
    }

    //将二叉树后序遍历，用于测试结果 “左右根”
    public static void postTraverseBinTree(TreeNode node){
        if (node==null) {
            return;
        }
        if (node.left!=null) {
            postTraverseBinTree(node.left);
        }
        if(node.right!=null){
            postTraverseBinTree(node.right);
        }
        System.out.print(node.val+" ");
    }


}
