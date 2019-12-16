package offer.WareHouse;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/*
https://www.nowcoder.com/profile/9067475/codeBookDetail?submissionId=18693161
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
假设压入栈的所有数字均不相等
 */
public class JudgePopOrder {
    public void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(",");//压入栈序列
        String[] s2 = sc.nextLine().split(",");//弹出栈序列
        int[] push = new int[s1.length];
        int[] pop = new int[s2.length];
        for (int i=0;i<s1.length;i++)
            push[i] = Integer.valueOf(s1[i]);
        for (int i=0;i<s2.length;i++)
            pop[i] = Integer.valueOf(s2[i]);

        System.out.println(IsPopOrder(push,pop));
    }
    /*
    当栈顶元素与弹出序列首元素相同时弹出，再依次判断栈顶元素与下一个弹出序列元素（每次弹出一元素后，标识弹出序列下标后推一）
    未相同之前先压入栈
    停止条件：当
     */

    private boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || popA == null)
            return false;
        Stack<Integer> stack = new Stack<>();
        int popindex = 0;
        for (int i=0;i<pushA.length;i++){
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[popindex]){//不相等时继续压栈；相等时出栈，并出栈顺序向后移动一位
                stack.pop();
                popindex++;//出栈顺序popA
            }//当不相等时且i以全部入栈完后，跳出循环的此时还有未成功匹配的元素在栈中
        }
        return stack.empty();//未成功匹配则不为空返回false
    }
}
