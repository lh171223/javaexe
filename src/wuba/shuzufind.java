package wuba;

import java.util.Scanner;

public class shuzufind {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
        int[] a = {5,4,5,6};
//        int[] a = new int[s.length()];
//        for(int i =0;i<s.length();i++)
//            a[i] = (int)s.charAt(i);
//        for(int i =0;i<s.length();i++)
//            System.out.print(a[i]);

        int temp = a[0];
        for(int i=0;i<a.length;i++){
//            if (a[i] == ' ')
//                continue;
            if (temp >= a[i])
                temp = a[i];
        }
        System.out.println(temp);


    }}
