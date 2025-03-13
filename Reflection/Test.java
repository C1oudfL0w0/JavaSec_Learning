class Person03 {
    String name; // 声明姓名属性
    int age; // 声明年龄属性

    public void tell() { // 取得信息的方法
        System.out.println("姓名：" + name + ",年龄：" + age);
    }
}

public class Test {
    public static void main(String[] args) {
        Person03 per = new Person03();
        per.name = "张三";
        per.age = 30;
        per.tell();
        Class clazz = Person03.class;
        System.out.println(clazz.getMethods());
    }
}
