import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectRuntime {
    public static void main(String[] args) throws Exception {
        Class p = Class.forName("java.lang.Runtime");
        Constructor constructor = p.getDeclaredConstructor(); // 调用私有构造器
        constructor.setAccessible(true); // 修改作用域
        Method m = p.getMethod("exec", String.class); // 获取exec方法
        Object o = constructor.newInstance(); // 实例化对象
        m.invoke(o, "calc"); // 调用exec方法，执行calc命令
    }
}