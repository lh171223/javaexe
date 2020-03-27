package offer.Array;

import java.util.ArrayList;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/24 16:59
 * <p>
 * 【一】丑数  --complex---
 */
public class others {

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



    public static void main(String[] args){
        System.out.println(GetUglyNumber(10));
    }

}
