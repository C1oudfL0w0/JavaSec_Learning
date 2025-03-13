public class Person3 {
    public int age;
    public String name;
    public Sex sex;

    @Override
    public String toString() {
        return String.format("Person.age=%d, Person.name=%s, %s", age, name, sex == null ? "null" : sex);
    }
}