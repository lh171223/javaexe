package offer.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lh
 * @version 1.0
 * @date 2020/4/4 9:39
 *
 * 【一】二叉树的下一个结点
 * 【二】按之字形顺序打印二叉树
 * 【三】把二叉树打印成多行
 *
 */
public class otherBT {

    /*
    【一】二叉树的下一个结点
    给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */
    /*
    思路
    中序遍历：左根右
    将该结点作为根节点考虑，下一个结点应是其右子节点或根节点(两种情况)
    1.为空返回空
    2.结点作为根节点，其右孩子存在，下个结点即为右孩子最左边的结点
    3.结点不是根节点，若该节点是其父节点的左孩子，则父节点是下一个结点；若是右孩子，向上遍历其父节点的父节点...直至找到其父节点的左孩子位置
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null)
            return null;
        if (pNode.right != null){ //第2种情况
            pNode = pNode.right;
            while (pNode.left != null)
                pNode = pNode.left;
            return pNode;
        }
        while (pNode.parent != null){
            if (pNode.parent.left == pNode)//其父节点的左孩子
                return pNode.parent;
            pNode = pNode.parent;//向上遍历父节点，当当前父结点的下一个父节点为空时，跳出循环，保留有当前父节点
        }
        return null;
    }

    /*
    【二】按之字形顺序打印二叉树
    请实现一个函数按照之字形打印二叉树，
    即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */
    /*
    思路
    类似于BFS，BFS每次只取一个；此处需先得到队列长度size（该层结点个数），再利用
    for循环去poll出这个结点
     */
    private ArrayList<ArrayList<Integer>> PrintZhi(TreeNode pRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null)
            return arrayLists;
        boolean flag = true;
        queue.add(pRoot);//第一层
        while (!queue.isEmpty()){
            int size = queue.size();//当前层的个数
            ArrayList<Integer> list = new ArrayList<>();//存储当前层的结点
            for (int i=0;i<size;i++){
                TreeNode tempNode = queue.poll();//依次弹出当前层的所有结点
                if (tempNode == null)
                    continue;
                //每层的插入方式不同而已
                if (flag) //奇数行  -- 从左到右
                    list.add(tempNode.val);//list.add(T): 按照索引顺序从小到大依次添加
                else  //偶数行  -- 从右到左
                    list.add(0,tempNode.val);//将元素插入index位置，index索引后的元素依次后移,这就完成了每一行元素的倒序，或者使用Collection.reverse()方法倒序也可以

                queue.offer(tempNode.left);
                queue.offer(tempNode.right);
            }
           if (list.size()!=0)
               arrayLists.add(list);
           flag = !flag;
        }
        return arrayLists;
    }

    /*
    【三】把二叉树打印成多行
    从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     */
    private ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null)
            return arrayLists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()){
            ArrayList<Integer> arrayList = new ArrayList<>();//用来存储每一层的结点
            int size = queue.size();//若遇到与“每层”有关的二叉树问题，考虑设个size然后分层循环遍历输出即可
            for (int i=0;i<size;i++){//遍历打印出该层结点
                TreeNode tempNode = queue.poll();//依次弹出当前层的结点
                if (tempNode == null)
                    continue;
                arrayList.add(tempNode.val);
                queue.offer(tempNode.left);
                queue.offer(tempNode.right);
            }
            if (arrayList.size() != 0)
                arrayLists.add(arrayList);
        }
        return arrayLists;
    }


}
