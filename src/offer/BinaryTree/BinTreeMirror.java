package offer.BinaryTree;

import java.util.Stack;

/*
《二叉树镜像》：操作给定的二叉树，将其变换为源二叉树的镜像
参考：https://blog.csdn.net/xiaoxingxing1744/article/details/82782461
 */
public class BinTreeMirror {
    /*
    非递归：栈空间 --- ①每次弹出栈顶元素，并判断该元素的左右孩子结点，做swap操作 ②结束条件为栈空间为空
    利用栈存放每次需要判断的结点（栈顶元素），将交换后的结点存入栈中，再在弹出时来交换其下子女结点
    （交换后再次存入栈，而先前的已被判断过子女的结点已被弹出，这样每次只要判断在栈中的结点的孩子结点并进行交换即可）
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

}
