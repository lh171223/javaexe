package offer.BinaryTree;

/**
 * 平衡二叉树
 * 特性：
 * 1.左右子树深度之差（平衡因子）的绝对值不超过1
 * 2.左右子树也都是一颗平衡二叉树
 * ------
 * 3.空树或具有上述两点性质的树
 * 4.同时也必须是二叉查找树、二叉搜索树
 * @author lh
 * @version 1.0
 * @date 2020/3/27 16:04
 *
 * 【一】平衡二叉树（AVL）
 *
 */
public class BalanceBT {

    /*
    【一】平衡二叉树
    输入一棵二叉树，判断该二叉树是否是平衡二叉树
     */
    /*
    ①递归方法
    采用自底向上遍历，每个结点仅遍历依次，当遍历到一个结点时，其左右子树已经遍历
    若子树是平衡二叉树，则返回子树的深度；若子树不是平衡二叉树，则直接停止遍历（此时至多对每个结点遍历依次）。

    若采用自上至下方法遍历，则会在判断上层结点时，多次重复遍历下层结点，增加了不必要的开销
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return getDepth(root) != -1;
    }
    private int getDepth(TreeNode root){
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        if (left == -1)
            return -1;//子树非平衡二叉树，直接退出
        int right = getDepth(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left-right)>1 ? -1:1+Math.max(left,right);
    }

    //非递归方法


}
