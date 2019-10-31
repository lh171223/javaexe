package offer.BinaryTree;

import java.util.Arrays;
import java.util.Scanner;
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
     int sum;//左右子树之和
     TreeNode(int x) { val = x; }

}