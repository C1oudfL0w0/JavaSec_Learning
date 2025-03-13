import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class User2 {
    private String name;
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private int age;

    public User2() {
        System.out.println("构造方法");
    }

    public User2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println("getname");
        return name;
    }

    public void setName(String name) {
        System.out.println("setname");
        this.name = name;
    }

    public int getAge() {
        System.out.println("getage");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setage");
        this.age = age;
    }
}