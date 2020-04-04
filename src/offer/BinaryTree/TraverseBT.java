package offer.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
遍历二叉树
 */
public class TraverseBT {

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
        System.out.print(node.val+",");
//        System.out.print(node.sum+" ");//求和树
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

    //将二叉树层次遍历
    public static void levelTraverseBinTree(TreeNode node){
        if (node==null) {
            return;
        }
        int depth = depthTree(node);
        System.out.print(depth+" ");
        for (int i=0;i<=depth;i++)
            levelTraverseBinTree(node,i);
    }
    private static void levelTraverseBinTree(TreeNode node,int level){
        if(node == null || level < 1)
            return;
        if (level == 1){
            System.out.print(node.val + " ");
            return;
        }
        //左子树
        levelTraverseBinTree(node.left,level-1);
        //右子树
        levelTraverseBinTree(node.right,level-1);
    }

    private static int depthTree(TreeNode node){ //计算二叉树的深度
        if (node == null)
            return 0;
        int l = depthTree(node.left);
        int r = depthTree(node.right);
        if (l>r)
            return l+1;
        else
            return r+1;
    }
    /*
    非递归方法
    前、中、后利用栈；层次遍历利用队列
    https://www.jianshu.com/p/9f148caf2535
     */

    public static void preOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()){
            while (node !=null){
                System.out.print(node.val+" "); //简单方式在入栈的同时访问节点
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()){
                node = stack.pop();
                node = node.right;
            }
        }
    }
/*
中序遍历
从根节点一次将左节点入栈，当没有左结点的时候，弹出栈顶元素，并将其右结点入栈

先遍历左子树的左节点，再依次弹出该左节点并考虑其右节点；左子树全部左右结点判断完成后，压入右子树中的所有左节点，再同左子树一样去判断右子树中的所有右节点
 */
    public static void inOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()){
            while (node !=null){
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()){
                node = stack.pop();
                System.out.print(node.val+" ");
                node = node.right; //当node是叶子结点时，node==null（所有该结点没有右节点时会为空，然后继续等待弹出下一个结点再又判断其右节点）
            }
        }
    }

    /*
前序+后序：后序遍历的输出顺序是左、右、根，当我们采用先序遍历的方法，
但是先遍历右子树，实现的效果是根、右、左，刚好和后序遍历的结果相反，
所以我们通过add(0, node)的方式将顺序反序，达到我们想要的效果。
 */
    public static void postOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()){
            while (node !=null){
                stack.push(node);
                System.out.print(node.val+" ");//根
                node = node.right;//右
            }
            if (!stack.empty()){
                node = stack.pop();
                node = node.left;//左
            }
        }
    }
    /*
    https://blog.csdn.net/Ybt_c_index/article/details/79625030
    层次遍历：借助队列的特性
    根节点先入队，接着其左右子结点入队（若有）
    子节点入队后，根节点出队
    则子节点成为现存的根节点，其子节点入队，再根节点出队，循环往复，实现了按层遍历每个节点（每层的根节点）
     */
    //带返回值的参考代码:https://blog.csdn.net/seagal890/article/details/79772657(注意为null时的返回值)
    public static void levelOrder(TreeNode node){
        if (node==null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();//java中队列（Queue）的用法：LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
        queue.offer(node);
        while (!queue.isEmpty()){ //当不再有当前根节点（即最后一个弹出的根节点没有左右子节点）时退出循环
            TreeNode temp = queue.poll();//不推荐用add()和remove() -- 依次弹出当前根节点
            System.out.print(temp.val+" ");
            if(temp.left != null)
                queue.offer(temp.left);
            if (temp.right != null)
                queue.offer(temp.right);
        }
    }


}
