package offer.BinaryTree;

import java.util.ArrayList;
/*
输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径
深度优先搜索 DFS
 */
public class PathSum {

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


}
