package offer.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
DFS、BFS递归与非递归的实现
 */
public class DFSAndBFS {

    //初始化顶点个数。当然也可以自己写一个函数由用户输入自定义图，这里重点不是图的构造
    private int numVertexes = 5;
    //初始化顶点
    private String[] vex;
    //邻接矩阵，表明了顶点的关系
    private int[][] acr;

    private DFSAndBFS()
    {
        vex= new String[]{"A", "B", "C", "D", "E"};
        acr= new int[][]{
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };
    }

    private void printGraph(DFSAndBFS g)
    {
        System.out.println("图中各顶点的关系如下图所示（邻接矩阵，1代表两顶点有边，0代表无）");
        System.out.print("       ");
        for(int i=0;i<g.numVertexes;i++)
        {
            System.out.print(g.vex[i]+"     ");
        }

        for(int i=0;i<g.numVertexes;i++)
        {
            System.out.println();
            System.out.print(g.vex[i]+"      ");
            for(int j=0;j<g.numVertexes;j++)
            {
                System.out.print(g.acr[i][j]+"     ");
            }

        }
    }

    //深度优先遍历，递归算法
    private void DFSTraverse(DFSAndBFS g)
    {
        //建立访问数组
        boolean []visited=new boolean [g.numVertexes];
        //初始化 访问顶点
        for(int i=0;i<visited.length;i++)
            visited[i]=false;
        //对未访问顶点调用DFS
        for(int i=0;i<visited.length;i++)
            if(!visited[i])
                DFS(g,i,visited);
    }

    //从顶点i开始进行的深度优先搜索DFS
    private void DFS(DFSAndBFS g, int i, boolean[] visited) {
        visited[i]=true;
        System.out.print(g.vex[i]+"  ");
        for(int j=0;j<g.numVertexes;j++)
        {
            if(g.acr[i][j]>0 && !visited[j])
                DFS(g,j,visited);
        }

    }

    //深度优先遍历，非递归算法
    private void DFS(DFSAndBFS g){
        boolean[] visited = new boolean[numVertexes];
        Stack<Integer> stack =new Stack<Integer>();
        for(int i=0;i<g.numVertexes;i++){
            if(!visited[i]){
                visited[i]=true;
                System.out.print(g.vex[i]+"  ");
                stack.push(i);
            }
            while(!stack.isEmpty()){
                int k = stack.pop();
                for(int j=0;j<g.numVertexes;j++){
                    if(g.acr[k][j]==1&& !visited[j]){
                        visited[j]=true;
                        System.out.print(g.vex[j]+"  ");
                        stack.push(j);
                        break;
                    }
                }

            }
        }
    }


    //广度优先搜索  借助队列
    private void BFS(DFSAndBFS g)
    {
        int i=0;
        Queue<Integer> q=new LinkedList<Integer>();
        //建立访问数组
        boolean visited[]=new boolean[numVertexes];
        //初始化 访问顶点
        for(i=0;i<g.numVertexes;i++)
            visited[i]=false;

        //对每个顶点做循环
        for( i=0;i<g.numVertexes;i++)
        {
            if(!visited[i])
            {
                visited[i]=true;
                System.out.print(g.vex[i]+"  ");
                q.offer(i);
                while(!q.isEmpty())
                {
                    i=q.poll();
                    for(int j=0;j<g.numVertexes;j++)
                    {
                        if(g.acr[i][j]>0 && !visited[j])
                        {
                            visited[j]=true;
                            System.out.print(g.vex[j]+"  ");
                            q.offer(j);
                        }
                    }


                }

            }
        }
    }

    public static void main(String []args)
    {
        DFSAndBFS g=new DFSAndBFS();
        g.printGraph(g);

        System.out.println();
        System.out.println("深度优先搜索(递归)结果：");
        g.DFSTraverse(g);

        System.out.println();
        System.out.println("深度优先搜索(非递归)结果：");
        g.DFS(g);

        System.out.println();
        System.out.println("广度优先搜索结果：");
        g.BFS(g);
    }

}

