package offer.BinaryTree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/27 11:27
 *
 * 【一】二叉树的深度
 * 【二】二叉树中和为某一值的路径
 */
public class DFSBFS {

    /*
    【一】二叉树的深度  --- simple - complex ---
    输入一棵二叉树，求该树的深度。
    从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     */
    //① 递归方法
    public static int TreeDepth(TreeNode node){ //计算二叉树的深度
        if (node == null)
            return 0;
        int l = TreeDepth(node.left);
        int r = TreeDepth(node.right);
        if (l>r)
            return l+1;
        else
            return r+1;
    }
    //②非递归方法：层次遍历
    public static int TreeDepth1(TreeNode node){
        if (node == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth=0;//当前结点所在的层数
        int count=0;//计算已经遍历过的当前层结点数
        int nextCount=1;//计算每一层的结点总数
        while (queue.size()!=0){ //队列不为空时 -- 当不再有当前根节点可以弹出（即最后一个根节点弹出后不再有左右子节点时）
            TreeNode top = queue.poll();
            count++; //弹出（遍历过）当前结点即记录
            if (top.left !=  null)
                queue.offer(top.left);
            if (top.right != null)
                queue.offer(top.right);
            if (count == nextCount){ //表示本层结点已经遍历完毕
                nextCount = queue.size();//下一层结点总数
                count = 0;
                depth++;
            }
        }
        return depth;
    }


    /*
    【二】二叉树中和为某一值的路径
    输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
    路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    (注意: 在返回值的list中，数组长度大的数组靠前)
    深度优先搜索 DFS
    */

    ArrayList<ArrayList<Integer>> pathlist = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> path = new ArrayList<>();
    //递归方法
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null || target == 0)
            return pathlist;
        getPaths(root,target,0);
        return pathlist;
    }

    private void getPaths(TreeNode root,int target,int sum) {
        path.add(root.val);//1.添加当前结点到当前路径中
        sum += root.val;
        while(root.left!=null){//2.
            getPaths(root.left,target,sum);
        }
        while(root.right!=null){//3.
            getPaths(root.right,target,sum);
        }
        if(root.left == null && root.right == null && sum == target){//3.满足条件时，该路径添加到总体路径列表中
            pathlist.add(new ArrayList<>(path));
        }
        path.remove(path.size()-1);
        //4.排序：数组长度大的数组靠前
//        return pathlist;
    }


    public static void main(String[] args){
        TreeNode node = new TreeNode(7);

    }


}
