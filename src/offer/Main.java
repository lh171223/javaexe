package offer;
/*

 */


import java.util.Scanner;

import java.util.Stack;



public class Main {



    public static void main(String[] args) {

        // TODO Auto-generated method stub



        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String str = sc.nextLine();

            solution(str);

        }

        sc.close();

    }



    private static void solution(String str) {

        // TODO Auto-generated method stub

        Stack<Integer> numStack = new Stack<>();

        Stack<Character> signalStack = new Stack<>();

        int index = 0;// 记录已经执行的符号数

        int len = str.length();

        // System.out.println("多项式字符串长度：" + len);

        while (index < len) {

            // System.out.println("第" + index + "步++++++++++");

            char c = str.charAt(index); // 取出这一步的符号

            if (c == '(') {

                // System.out.println(c + "------------进栈");

                signalStack.push(c);// 若是左括号就进栈

            }

            // 否则要先判断优先级

            else if (c == '+' || c == '-' || c == '*' || c == '/') {

                int currOperLevel = getOperlevel(c);// 当前符号的优先级

                while (true) {

                    int stackOperLevel = 0;// 栈顶元素的优先级

                    if (signalStack.isEmpty() == false) {

                        Object obj = signalStack.peek();

                        stackOperLevel = getOperlevel((char) obj);

                    }

                    // 若当前元素优先级大于栈顶元素的优先级则入栈

                    if (currOperLevel > stackOperLevel) {

                        // System.out.println(c + "------------进栈");

                        signalStack.push(c);

                        break;// 直到让比自己优先级高的符号都出栈运算了再把自己进栈

                    } else {// 不能入栈就进行计算

                        try {

                            char optemp = '0';

                            int num1 = 0;

                            int num2 = 0;

                            if (signalStack.isEmpty() == false) {

                                optemp = (char) signalStack.pop();// 取出优先级大的那个符号

                            }

                            if (numStack.isEmpty() == false) {

                                num1 = (int) numStack.pop();

                                num2 = (int) numStack.pop();// 取出数据栈中的两个数

                            }

                            /*

                             * System.out .println(optemp + " " + num1 + " " +

                             * num2); System.out.println(caculateResult(optemp,

                             * num2, num1) + "---------------进栈");

                             */

                            numStack.push(caculateResult(optemp, num2, num1));// 将算出来的结果数据再次进入数据栈

                        } catch (Exception e) {

                            // TODO: handle exception

                            System.out.println("多项式不正确1" + str + " " + c);

                            e.printStackTrace();

                        }

                    }

                }

            } else if (c == ')') {// 右括号就返回栈顶元素，右括号是不进栈的

                while (true) {

                    char theop = (char) signalStack.pop();

                    if (theop == '(') {

                        break;

                    } else {

                        try {

                            int num1 = (int) numStack.pop();

                            int num2 = (int) numStack.pop();

                            /*

                             * System.out.println(" " + num1 + " " + num2);

                             * System.out .println(caculateResult(theop, num2,

                             * num1) + "------------进栈");

                             */

                            numStack.push(caculateResult(theop, num2, num1));// 运算括号内的内容

                        } catch (Exception e) {

                            // TODO: handle exception

                            System.out.println("多项式不正确2" + str);

                            e.printStackTrace();

                        }

                    }

                }

            } else if (c >= '0' && c <= '9') {

                int tempIndex = index + 1;

                while (tempIndex < len) {

                    char temp = str.charAt(tempIndex);// 取字符串中处于当前字符的下一位

                    if (temp >= '0' && temp <= '9') {

                        tempIndex++;// 若为数字则继续向后取

                    } else {

                        break;// 证明数字去完

                    }

                }

                String numstr = str.substring(index, tempIndex);// 截取这个字符串则为两个符号之间的数字

                /*

                 * System.out.println("-------------" + numstr +

                 * "----------------");

                 */

                try {

                    int numnum = Integer.parseInt(numstr);// 将数字转换成整型便于运算

                    // System.out.println(numnum + "-----------进栈");

                    numStack.push(numnum);

                    index = tempIndex - 1;

                } catch (Exception e) {

                    // TODO: handle exception

                    System.out.println("多项式不正确3:" + str);

                    e.printStackTrace();

                }

            }

            index++;

        }

        // 检查符号栈是否为空

        while (true) {

            Object obj = null;

            if (signalStack.isEmpty() == false) {

                obj = signalStack.pop();

            }

            if (obj == null) {

                break;// 为空证明运算已结束

            } else {// 不为空就出栈运算

                char opterTemp = (char) obj;

                int num1 = (int) numStack.pop();

                int num2 = (int) numStack.pop();

                /*

                 * System.out.println(caculateResult(opterTemp, num2, num1) +

                 * "--------------进栈");

                 */

                numStack.push(caculateResult(opterTemp, num2, num1));

            }

        }

        int result = 0;

        try {

            result = (int) numStack.pop();

        } catch (Exception e) {

            // TODO: handle exception

            System.out.println("多项式不正确4" + str);

            e.printStackTrace();

        }

        System.out.println(result);

    }



    /*

     * 计算加减乘除余

     */

    private static Integer caculateResult(char optemp, int num1, int num2) {

        // TODO Auto-generated method stub

        switch (optemp) {

            case '+':

                return num1 + num2;

            case '-':

                return num1 - num2;

            case '*':

                return num1 * num2;

            case '/':

                return num1 / num2;

        }

        return 0;

    }



    /*

     * 返回符号优先级

     */

    private static int getOperlevel(char c) {

        // TODO Auto-generated method stub

        switch (c) {

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



}
