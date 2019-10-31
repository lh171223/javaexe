package offer.BinaryTree;
/*快手2019秋招 中等难度
 将满二叉树转换为求和树 https://www.nowcoder.com/practice/b31734e46ba644de85a9cf95bbd57a5f
 */
//参考：https://www.jianshu.com/p/b3b7f2a85da5
public class SumTree {
    /*
    二叉树 ==》求和树
    利用后序遍历计算sum   “左右根”
        后序遍历求和、输出：根据后序遍历构建求和树
     */
    public static TreeNode BinaryToSumTree(TreeNode treeNode){
        if(treeNode == null)
            return null;
        BinaryToSumTree(treeNode.left);
        BinaryToSumTree(treeNode.right);
        if(treeNode.left != null && treeNode.right != null){
            treeNode.sum = treeNode.left.sum + treeNode.left.val + treeNode.right.sum + treeNode.right.val;
        }else if (treeNode.left != null){
            treeNode.sum = treeNode.left.sum + treeNode.left.val;
        }else {
            //nothing
        }

        return treeNode;
    }

/*
输入：
10 -2 8 -4 6 7 5
8 -2 -4 10 7 6 5
输出：
0 4 0 20 0 12 0
 */

}
