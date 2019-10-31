package baidu;


import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//行
        int M = sc.nextInt();//列
        String[] str = new String[M];//长度为M、仅由N、S、W、E组成 N*M二维数组相当于
        for(int i=0;i<N;i++){
            str[i] = sc.next();//每行术语一个字符串
        }
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println(fun(N,M,x,y,str));
    }
    public static int fun(int N,int M,int x,int y,String[] str){
        int number = 0;//棋子移动数
        /*
        1.遍历每个棋子str[i][j]，初始棋子为str[x][y]
        2.判断移动的每一种情况并进行相关操作
        3.每一步都将移动的步数相加
         */
        for (int i=0;i<N;i++)
            for (int j=0;j<M;j++){
                if (x>1 && x<N && y>1 && y<M){
                    while (str[x].charAt(y)== 'N'){
                        str[x].indexOf(' ',y);
                        x=x-1;
                        number++;
                    }
                    while (str[x].charAt(y)=='S'){
                        str[x].indexOf(' ',y);
                        x=x+1;
                        number++;
                    }
                    while (str[x].charAt(y)=='W'){
                        str[x].indexOf(' ',y);
                        y=y-1;
                        number++;
                    }
                    while (str[x].charAt(y)=='E'){
                        str[x].indexOf(' ',y);
                        y=y+1;
                        number++;
                    }

                }
            }

        return number+1;
    }


}
