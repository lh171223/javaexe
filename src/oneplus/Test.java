package oneplus;

public class Test {
    int n=0;
    int m=0;

    /*
    哪一行出错
    static
     */
//    public static void main(String[] args){
//        Test t1,t2;
//        static int j,k;
//        j=3;
//        k=5;
//        t1=new Test();
//        System.out.print(j*k);
//    }
//
    public static void main(String args[]){
        B mB = new B();
    }

}
class A{
    A(){
        System.out.print("A");
    }
}
class B extends A{
    static {
        System.out.print("plus");
    }
    B(){
        System.out.print("B");
    }
}
