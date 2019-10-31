package oneplus;

public class javatest {
    static boolean foo(char c){
        System.out.print(c);
        return true;
    }

    public static void main(String arg[]){
        int i=0;
        for(foo('1');foo('2') && (i<2);foo('3')){
            i++;
            foo('4');
        }
    }
}
