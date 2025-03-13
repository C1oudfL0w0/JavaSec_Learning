package test;

public class ReflectPhone {
    public static void main(String[] args) throws Exception {
        Class p = Class.forName("test.Phone");
        System.out.println(p);
    }
}