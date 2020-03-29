package offer.BinaryTree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉搜索树
 * 特性：主要强调结点值
 * 1、若它的左子树不为空，则左子树上所有节点的值都小于根节点的值
 * 2、若它的右子树不为空，则右子树上所有节点的值都大于根节点的值
 * 3、它的左右子树也分别为二叉搜索树
 *
 * @author lh
 * @version 1.0
 * @date 2020/3/21 16:38
 *
 * 《二叉搜索树与双向链表》
 * 输入一棵二叉搜索树
 * 将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
/*

解题思路：
二叉搜索树中序遍历后是一个递增的序列（中序遍历非递归方法借助栈）
中序遍历的每一个结点都双向指向

双向链表怎么编程实现双向指向？？？ ---  创建一个前结点：找到第一个（最小的）结点，将其作为前结点
修改当前遍历结点与前一遍历结点的指针指向
 */
public class BSearchT {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p =pRootOfTree;
        TreeNode pre = null;//用于保存中序遍历的上一结点
        boolean isFirst = true;

        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left; //将所有的左节点入栈（入：从大到小）
            }
            p = stack.pop(); //左子树中最左的即最小的那个结点（出：从小到大）
            if (isFirst){ //找出第一个前结点
                pRootOfTree = p; // 将中序遍历序列中的第一个节点记为pRootOfTree（最小的一个结点）
                pre = pRootOfTree; //使得pre作为当前结点p的前结点，同时也是第一个前结点
                isFirst =false; //此后将直接进入p指针的双向指向过程，无需寻找前结点
            }else {// 双向链表指向实现
                pre.right = p; //建立两节点间的双向关系后，pre移动至P,即当前结点p又设定为一个前结点，从而去判断下一个结点
                p.left = pre;
                pre = p; //当前结点成为下一个结点的前结点
            }
            p = p.right; //依次考虑弹出的左节点的右节点（最小的左节点即叶子结点无右节点，具体见中序遍历说明）
        }
        return pRootOfTree;
    }
}
