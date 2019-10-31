package offer.BinaryTree;
/**
 * 重建二叉树 剑指offer第五题
 * 前序 + 中序 ==》 重建
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}

public class reConstructBinaryTree {

    public TreeNode ConstructBinaryTree(int [] pre,int [] in) {//,int[] post
        /*
        1.序列中找到根节点root
        2.根据root到中序序列中找到左子树和右子树
        3.再分别从以上得到的左子树与右子树中得到新的根节点，每个子树递归
        */
        //创建二叉树对象
        TreeNode root;
        root = reConstruct(pre,0,pre.length -1,in,0,in.length -1);//start\end表示前序和中序的起始位置
//        root = reConstructInPost(in,0,in.length-1,post,0,post.length-1);//中序+后序==》前序
        return root;
    }

    //前+中 ==》重建
    private TreeNode reConstruct(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        //判空
        if(preStart>preEnd | inStart>inEnd){
            return null;
        }
        //第1步 从前序序列中得到根节点 “根左右”
        TreeNode root = new TreeNode(pre[preStart]);
        //第2步 利用root到中序中找到左右子树（值相等，遍历）
        for(int i=inStart;i<=inEnd;i++){
            if(in[i] == pre[preStart]){
                //第3步 分别给予左右子树同等操作，递归遍历执行
                root.left = reConstruct(pre,preStart+1,preStart+i-inStart,in,inStart,i-1);//i-inStart 是左子树长度 用起始preStart相加得到其尾部下标
                root.right = reConstruct(pre,preStart+i-inStart+1,preEnd,in,i+1,inEnd);
            }
        }
        return root;
    }

    //中+后 ==》重建
    private TreeNode reConstructInPost(int[] in,int inStart,int inEnd,int[] post,int postStart,int postEnd){
        if(inStart>inEnd | postStart>postEnd)
            return null;
        TreeNode root = new TreeNode(post[postEnd]);
        for(int i=inStart;i<=inEnd;i++){
            if (in[i] == post[postEnd]){
                root.left = reConstructInPost(in,inStart,i-1,post,postStart,postStart+i-(inStart+1));//postStart+i-(inStart+1) ??? 为什么这里需要+1，与上序函数有何不同？
                root.right = reConstructInPost(in,i+1,inEnd,post,postStart+i-inStart,postEnd-1);
            }
        }
        return root;
    }

}
/*
输入：前序、中序
1 2 4 7 3 5 6 8
4 7 2 1 5 3 8 6
输出：后序
7 4 2 5 8 6 3 1
 */