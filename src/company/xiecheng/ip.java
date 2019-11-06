package company.xiecheng;

import java.util.*;

public class ip {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static boolean CheckBlackList(String userIP, String blackIP) {
        if (userIP.equals(blackIP))
            return true;
        String[] userC = userIP.split("\\.");
        String[] blackC = blackIP.split("\\.");
        //userC[3]
        //若是一个范围 如0/24
        if (blackC[3].contains("/")){
            String[] blackCc = blackC[3].split("\\/");
            //blackCc[0] ~ blackCc[1]
            if(Integer.parseInt(userC[3])>=Integer.parseInt(blackCc[0]) && Integer.parseInt(userC[3])<=Integer.parseInt(blackCc[1]))
                return true;
        }

        return false;
//
//        for (int i=0;i<blackCc.length;i++) {
//            System.out.print(blackCc[i]);
//        }

    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean res;

        String _userIP;
        try {
            _userIP = in.nextLine();
        } catch (Exception e) {
            _userIP = null;
        }

        String _blackIP;
        try {
            _blackIP = in.nextLine();
        } catch (Exception e) {
            _blackIP = null;
        }

        res = CheckBlackList(_userIP, _blackIP);
        System.out.println(String.valueOf(res ? 1 : 0));
    }
}
