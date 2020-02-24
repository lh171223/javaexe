
public class Main {

    public static void main(String[] args) {
        String s = new String("a");
        s.intern();
        String s1 = "mogen";
        System.out.println(s == s1);

        String s2 = new String("mogen") + new String("mogen");
        s2.intern();
        String s3 = "mogen";
        System.out.println(s2 == s3);
    }
}