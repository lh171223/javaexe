package offer.BinaryTree;

/*
Definition for binary tree
放在其他类中，可以写成内部静态类
static class TreeNode{
        int data;
        int sum;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int data){
           this.data = data;
        }
    }
 */
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode(int val) { this.val = val; }

     int sum;//左右子树之和

}