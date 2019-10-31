import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
public class map
{
    public static void main(String[] args)
    {
        HashMap users=new HashMap();
        users.put("11","张浩太");    //将学生信息键值对存储到Map中
        users.put("22","刘思诚");
        users.put("33","王强文");
        users.put("44","李国量");
        users.put("55","王路路");
        System.out.println("******** 学生列表 ********");
        Iterator it=users.keySet().iterator();//使用迭代器遍历
        while(it.hasNext())
        {
            //遍历 Map
            Object key=it.next();
            Object val=users.get(key);
            System.out.println("学号："+key+"，姓名:"+val);
        }
        Scanner input=new Scanner(System.in);
        System.out.println("请输入要删除的学号：");
        int num=input.nextInt();
        if(users.containsKey(String.valueOf(num))) //返回字符串表示形式，并判断是否包含指定键的映射，返回true
        {    //判断是否包含指定键
            users.remove(String.valueOf(num));    //如果包含就删除
        }
        else
        {
            System.out.println("该学生不存在！");
        }
        System.out.println("******** 学生列表 ********");
        it=users.keySet().iterator();
        while(it.hasNext())
        {
            Object key=it.next();
            Object val=users.get(key);
            System.out.println("学号："+key+"，姓名："+val);
        }
    }
}