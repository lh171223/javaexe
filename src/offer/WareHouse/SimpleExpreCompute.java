package offer.WareHouse;

import java.util.Scanner;
import java.util.Stack;

/*
栈的应用
简单表达式计算：https://www.nowcoder.com/practice/6221faa383fc49f1b10dffcb62c866bf
给定一个合法的表达式字符串，其中只包含非负整数、加法、减法以及乘法符号（不会有括号），
例如7+3*4*5+2+4-3-1，请写程序计算该表达式的结果并输出；

思路：
0.创建数字栈和符号栈
1.创建index用来循环扫描expression
2.如果E[index]是数字，直接入数栈
3.若E[index]是符号，
    1)如果当前的符号栈为空，入符号栈
    2)如果当前符号栈不为空，进行比较
        A）如果当前操作符优先级《=OperStack栈顶的操作符
           就从numStack中pop两个数，从符号栈中pop一个operator，进行运算，将运算结果进numStack，再把当前operator进operStack。
        B）如果 > operStack[top],直接入operStack.
4.如果栈中最后只有一个数字，那就是结果

 */
public class SimpleExpreCompute {
    private static Stack<Character> stackOPR = new Stack<>();//操作符栈
    private static Stack<Integer> stackOPN = new Stack<>();//操作数栈

    public void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("END")){
            char[] c = s.toCharArray();
            for (int i=0;i<s.length();i++){
                if (!isOPN(s.charAt(i))){//不是运算符号
                    stackOPN.push(c[i]-'0');//2.数字则直接压入操作数栈
                }else {//3.若是运算符，则需要判断优先级
                    if (stackOPR.empty())//1）操作符栈为空则直接压入
                        stackOPR.push(c[i]);
                    else {//2）操作符栈不为空则比较优先级
                        if (isPrior(c[i]) <= isPrior(stackOPR.peek())){//A)操作符优先级小于栈顶符号级别
                            int t = Option(stackOPN.pop(),stackOPN.pop(),stackOPR.pop());
                            stackOPN.push(t);
                        }else {//B)大于
                            stackOPR.push(c[i]);
                        }
                    }
                }
            }
        }

    }

    private boolean isOPN(char c){//判断是否是操作数
        return  c=='+' || c=='-' || c=='*' || c=='/' || c=='(' || c==')';
    }

    /*
    计算加减乘除
     */
    private int Option(int a,int b,Character c){
        switch (c){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
            {
                if (b == 0)
                    return 0;
                return a/b;
            }
        }
        return 0;
    }

    /*
    返回符号优先级
     */
    private int levelPrior(char c){
        switch(c){
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /*
    算符优先级：返回和栈顶元素优先级的比较结果
    > 表示优先级大于栈顶元素
    = 表示优先级相等
    < 表示优先级小于栈顶元素
     */
    private char isPrior(Character c){
        char c2 = stackOPR.peek();//栈顶元素

        if (c=='+' || c=='-'){
            if (c2=='+' || c2=='-' || c2=='*' || c2=='/')//如果同为+-时，则c2的优先级大于c ，同理 * /就更不用说了
                return '<';
            if (c2=='(')
                return '>';
            if (c2==')')
                return '<';
        }

        if (c=='*' || c=='/'){
            if (c2=='+' || c2=='-')
                return '>';
            if (c2=='*' || c2=='/')
                return '<';
            if (c2=='(')
                return '>';
            if (c2==')')
                return '<';
        }

        if (c=='(')
            return '>';

        if (c==')'){
            if (c2!='(')
                return '<';
            else
                return '=';
        }

        return 0;
    }



}
