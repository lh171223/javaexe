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
 * 【三】矩阵中的路径  ---回溯算法
 * 【四】机器人的运动范围   ---回溯算法
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

    /*
   【三】矩阵中的路径  --- 回溯算法---
   请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
   路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
   如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
    */
    /*
    思路：DFS递归的回溯剪枝思想

     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if (str == null || rows<=0 ||cols<=0)
            return false;
        int[] flag = new int[rows*cols];//标记路径
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (helpHashPath(matrix,rows,cols,str,0,flag,i,j))
                    return true;
            }
        }
        return false;
    }
    /*
    任选一格子作为路径的起点，若路径上的第i个字符不是ch，则该格子不可能位于路径上的第i个位置；
    若路径上第i个字符恰好是ch，则继续往相邻格子上寻找路径上的第i+1个字符。
    除在边界上的格子之外，其余格子都有4个相邻格子。
    重复上述过程，直至路径上的所有字符都在矩阵中找到相应的位置
    根据回溯法的递归特性，路径可以被开成一个栈。
    当在矩阵中定位了路径中前n个字符位置之后，在与第n个字符对应相邻格子中都没有找到第n+1个字符时，
    回到路径上的第n-1个字符处，重新定位第n个字符。

    由于不能重复进入矩阵的格子，所以需要设置标识数组，用来标识路径是否已经进入每个格子。
    当矩阵中坐标为（row,col）格子和路径字符串中相应字符一样时，从4个相邻格子中去定位路径字符串中的下一个字符；
    若4个相邻格子都未成功匹配字符串的下个字符，表明当前路径字符串中字符在矩阵中定位不正确，需要返回至前一个，然后重新定位。
    重复上述构成，直至路径字符串上所有字符都在矩阵中找到合适的位置。

    row,col：当前坐标的行列值
    falg：曾经在路径中出现过的结点坐标
    cur：当前需要匹配的字符位置，是下一个待访问的结点，无需关系其有效性，因为最后一个元素是0
     */
    private boolean helpHashPath(char[] matrix,int rows,int cols,char[] str,int cur,int[] flag,int r,int c){
        int index = cols*r + c;
        if (r>=0 && r<rows && c>=0 && c<cols && flag[index]==0){
            if (matrix[index] == str[cur]){
                cur = cur+1;
                if (cur >= str.length)
                    return true;
                flag[index] = 1;
                if (helpHashPath(matrix,rows,cols,str,cur,flag,r-1,c)||
                        helpHashPath(matrix,rows,cols,str,cur,flag,r+1,c)||
                        helpHashPath(matrix,rows,cols,str,cur,flag,r,c-1)||
                        helpHashPath(matrix,rows,cols,str,cur,flag,r,c+1)
                )
                    return true;
                flag[index] = 0;
                return false;
            }
            return false;
        }
        return false;
    }

    /*
    【四】机器人的运动范围
    地上有一个m行和n列的方格。
    一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
    但是不能进入行坐标和列坐标的数位之和大于k的格子。
    例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
    但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
    请问该机器人能够达到多少个格子？
     */
    /*
    思路：
    仅考虑坐标的数位之和，与符号无关（为负数时，需要不计算第一位）
    如何模拟机器人的运动路径？
    如何去叠加每位数字之和？

    广度遍历思想，比矩阵路径上题更为简单，因为已被规定从（0，0）坐标开始走起
     */
    //统计能够走到的次数
    public int count = 0;

    public int movingCount(int threshold, int rows, int cols)
    {
        int[][] flag = new int[rows][cols];
        helpMoveCount(0,0,threshold,rows,cols,flag);
        return count;
    }

    //判断坐标是否符合要求
    private boolean isValid(int row,int col,int threshold){
        int sum =0;
        while (row>0){ //计算行的数位之和
            sum += row%10;
            row = row/10;
        }
        while (col>0){ //计算列的数位之后
            sum += col%10;
            col = col/10;
        }
        return  sum<=threshold;
    }

    private void helpMoveCount(int i,int j,int threshold,int rows,int cols,int[][] flag){
        if (i<0 || i>=rows || j<0 || j>=cols)//若坐标不符合则不计数
            return;
        if (flag[i][j] == 1) //曾经被访问过的不计数
            return;
        if (!isValid(i,j,threshold)){ //不满足坐标有效性，则不计数并标记为已被访问
            flag[i][j] =1;
            return;
        }
        flag[i][j] = 1;
        count++ ;
        helpMoveCount(i-1,j,threshold,rows,cols,flag);
        helpMoveCount(i+1,j,threshold,rows,cols,flag);
        helpMoveCount(i,j-1,threshold,rows,cols,flag);
        helpMoveCount(i,j+1,threshold,rows,cols,flag);
    }



    public static void main(String[] args){
        TreeNode node = new TreeNode(7);

    }


}
