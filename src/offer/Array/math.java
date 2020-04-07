package offer.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/24 16:59
 * <p>
 * 【一】丑数  --complex---
 * 【二】进制转换：求1+2+3+...+n
 * 【三】进制转化：不用加减乘除做加法
 * 【四】进制转化：数据流中的中位数  --complex  优先队列PriorityQueue
 * 【五】贪心：剪绳子
 */
public class math {

    /*
    【一】丑数   ---complex---
    把只包含质因子2、3和5的数称作丑数（Ugly Number）。
    例如6、8都是丑数，但14不是，因为它包含质因子7。
    习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    思路： 我们只用比较3个数：用于乘2的最小的数、用于乘3的最小的数，用于乘5的最小的数
     */
    public static int GetUglyNumber(int index) {
        if (index<= 0)
            return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i2=0,i3=0,i5=0;
        while (list.size()<index){
            int m2 = list.get(i2)*2;
            int m3 = list.get(i3)*3;
            int m5 = list.get(i5)*5;
            int min = Math.min(m2,Math.min(m3,m5));//找到三者中最小数
            list.add(min);//取每次相乘后的最小数放入新列表中，取完i对应的最小值后，i++进行下一次新的比较（每种组合穷举比较）
            if (min == m2) i2++;
            if (min == m3) i3++;
            if (min == m5) i5++;
        }
        return list.get(list.size()-1);
    }

    /*
    【二】进制转换：求1+2+3+...+n
    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */
    /*思路：短路求值原理
    逻辑与中若前面为假，则后面不计算
     */
    public static int Sum(int n) {
        int ans = n;
        boolean flag = ans>0 && (ans += Sum(n-1))>0;
        return ans;
    }

    /*
    【三】进制转化：不用加减乘除做加法
    写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */
    /*
    思路：二进制相加
    参考：https://www.nowcoder.com/profile/645151/codeBookDetail?submissionId=1512236
    1.不考虑进位，每步相加可作为异或操作
    2.考虑进位，每位做与操作，再向左移一位
    3.重复上述两步，各位相加
     */
    public static int Add(int num1,int num2) {
        while (num2 != 0){
            int temp = num1^num2;
            num2 = (num1 & num2)<<1;
            num1 = temp;
        }
        return num1;
    }

    /*
    【四】进制转化：数据流中的中位数  --complex
    如何得到一个数据流中的中位数？
    如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
    如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
     */
    /*
    思路：
    利用优先队列，构建两个顶堆，大顶堆存放小数据，小顶堆存放大数据
    奇数存入大顶堆，所以到数值个数为奇数个时，大顶堆直接弹出的即是中位数
     */
    private int count = 0;//计算
    private PriorityQueue<Integer> low = new PriorityQueue<>();
    // 默认维护小顶堆??? 此处比较器Comparator未看懂
    private PriorityQueue<Integer> high = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void Insert(Integer num) {
        count++;
        if ((count & 1) == 1){
            /*
            奇数时,存放至大顶堆
            但由于小顶堆存放的是后半段大的数，而此时不知道num与小顶堆的关系
            所以，若当前值num比小顶堆上堆顶的数更大时,
            存入小顶堆，并把小顶堆中最小的放入大顶堆，因为大顶堆存放前半段
             */
            if (!low.isEmpty() && num>low.peek()){
                low.offer(num);
                num = low.poll();
            }
            high.offer(num);
        }else{
                /*
                偶数时，此时需要存放的是小的数
                小数存入大顶堆，大数弹出并存入小顶堆
                 */
            if (!high.isEmpty() && num<high.peek()){
                high.offer(num);
                num = high.poll();
            }
            low.offer(num);
        }
    }

    public Double GetMedian() {
        double result;
        if ((count&1)==1)
            result = high.peek();
        else
            result = (high.peek()+low.peek())/2.0;
        return result;
    }

    /*
    【五】剪绳子
     给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
     每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
     例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     输入描述：输入一个数n，意义见题面。（2 <= n <= 60）
     例子：输入8，输出18
     */
    /*
    思路：贪心算法 --- 局部最优解
    采用自顶向下、以迭代的方法做出相继选择，每做一次贪心选择就将所求问题
    简化为一个规模更小的子问题。
    递归、动态规划
     */
    /*
    为哈？
     */
    public static int cutRope(int target) { //输入长度
        if (target == 0)
            return 0;
        if (target<=3 && target>0)
            return target-1;
        int result =1;//乘积
        while (target>4){
            target -= 3;//平均每段减去1，直至无法再均摊时？
            result *= 3;
        }
        return result*target;
    }

    /*
    思路：数学规律
    4 ： 2*2
    5 ： 2*3
    6 ： 3*3
    7 ： 2*2*3 或 4*3
    8 ： 2*3*3
    9 ： 3*3*3
    10 ： 2*2*3*3 或 4*3*3
    11 ： 2*3*3*3
    12 ： 3*3*3*3
    13 ： 2*2*3*3*3 或 4*3*3*3

    分析：
    由上可知，k[0]~k[m]只可能是2或3（4=2*2）；
    5：2*3，6：3*3，则比6更大的数字会继续细分；
    由6：2*2*2<3*3可知，2的数量肯定小于3的数量；
    使用n除以3，根据余数判断2的数量即可
    特殊情况：2：1*1 ；3：2*1 （m>1）

    乘方运算的复杂度：O(logN)
     */
    public static int cutRope1(int target) { //输入长度
        if (target == 2)
            return 1;
        if (target == 3)
            return 2;
        int x = target%3;//余数，用以判断2的个数
        int y = target/3;//3的个数
        if (x == 0)//没有2
            return (int)Math.pow(3,y);
        else
            if (x == 1)//有两个2,如4,7
                return 2*2*(int)Math.pow(3,y-1);
            else  //一个2，如8
                return 2*(int)Math.pow(3,y);
    }






    public static void main(String[] args){
//        System.out.println(GetUglyNumber(10)); //【一】
//        System.out.println(Sum(100));//【二】
//        System.out.println(Add(5,7));//【三】
        System.out.println(cutRope(8));
    }

}
