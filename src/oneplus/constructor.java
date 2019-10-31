package oneplus;

public class constructor {

    public constructor(){
        System.out.println("constructor");
    }
    public void constructor(){
        System.out.print("not constructor");
    }

    public static void main(String[] args){
        constructor test = new constructor();
        test.constructor();
    }
}
