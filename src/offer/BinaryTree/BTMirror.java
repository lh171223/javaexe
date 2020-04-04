package offer.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树镜像
 * 【一】二叉树镜像
 * 【二】对称的二叉树
 *
 */

public class BTMirror {

    /*
    【一】二叉树镜像
    操作给定的二叉树，将其变换为源二叉树的镜像
    参考：https://blog.csdn.net/xiaoxingxing1744/article/details/82782461
    非递归：栈空间 ---
     ①每次弹出栈顶元素，并判断该元素的左右孩子结点，做swap操作
     ②结束条件为栈空间为空
    利用栈存放每次需要判断的结点（栈顶元素），将交换后的结点存入栈中，再在弹出时来交换其下子女结点
    （交换后再次存入栈，而先前的已被判断过子女结点的已被弹出，这样每次只要判断栈中的结点的孩子结点并进行交换即可）
    栈为空则停止
     */
    public void nonRecurMirror(TreeNode root){
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();//使用额外空间栈
        stack.push(root);//压入根节点
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();//弹出栈顶元素
            if (node.left != null || node.right != null){//交换左右孩子结点（该孩子的子女结点也会伴随着整体移动，下面的连接状况不变）
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            //压入根节点的左右孩子结点（此时已被交换）
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
    }

    /*
    递归
     */
    public void recurMirror(TreeNode root){
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //左右结点继续进行交换
        if (root.left != null)
            recurMirror(root.left);
        if (root.right != null)
            recurMirror(root.right);
    }

    /*
    【二】对称的二叉树
    请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    */
    /*
    思路
    二叉树镜像是左右孩子结点做swap操作
    必须要成对输入输出，考虑对称性，非上述单纯左右结点相等就可以，要考虑顺序问题
     */
    public boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(pRoot.left);
        stack.push(pRoot.right);
        while (!stack.isEmpty()){
            TreeNode tempLeft = stack.pop();//成对取出
            TreeNode tempRight = stack.pop();
            if (tempLeft == null && tempRight == null)
                continue;
            if (tempLeft == null || tempRight == null)
                return false;
            if (tempLeft.val != tempRight.val)
                return false;
            stack.push(tempLeft.left);//必须按照此顺序 左结点的左子树对应右节点的右子树
            stack.push(tempRight.right);
            stack.push(tempLeft.right);
            stack.push(tempRight.left);
        }
        return true;
    }
    public boolean isSymmetrical1(TreeNode pRoot)
    {
        if (pRoot == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot.left);
        queue.offer(pRoot.right);
        while (!queue.isEmpty()){
            TreeNode tempLeft = queue.poll();
            TreeNode tempRight = queue.poll();
            if (tempLeft==null && tempRight==null)
                continue;
            if (tempLeft==null || tempRight==null)
                return false;
            if (tempLeft.val != tempRight.val)
                return false;
            queue.offer(tempLeft.left);
            queue.offer(tempRight.right);
            queue.offer(tempLeft.right);
            queue.offer(tempLeft.left);
        }
        return true;
    }

}
