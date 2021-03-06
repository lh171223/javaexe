package offer.WareHouse;
/*
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
题目：https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking

 */
import java.util.Iterator;
import java.util.Stack;
public class WareDefinition {

    Stack<Integer> stack = new Stack<>();
    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        //将栈顶元素设为初始最小值；后续依次弹出并与初始最小值比较，一轮后将最小值放入到min中
        int min = stack.peek();
        //stack.iterator()  用于遍历堆栈中的对象
        int temp = 0;
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            temp = iterator.next();
            if (min > temp)
                min = temp;
        }
        return min;
    }

}
