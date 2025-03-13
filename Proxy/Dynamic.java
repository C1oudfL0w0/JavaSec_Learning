import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

interface Network {
    // 定义网络接口，即目标类要完成的功能
    public void browse();
}

// 真实上网
class Real implements Network {
    @Override
    public void browse() {
        System.out.println("上网冲浪");
    }
}

// 代理类的调用处理器
class MyInvocationHandler implements InvocationHandler {
    private Object obj; // 真实主题

    public MyInvocationHandler(Object obj) {    // 绑定真实操作主题
        this.obj = obj;
    }

    // 此函数在代理对象调用任何一个方法时都会被调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 定义预处理的工作，可以根据 method 的不同进行不同的预处理工作
        System.out.println("正在选择端口...");
        System.out.println("正在选择匹配模式...");
        System.out.println("正在选择节点...");

        Object invoke = method.invoke(obj, args);

        System.out.println("提供后续业务...");
        return invoke;
    }
}

public class Dynamic {
    public static void main(String[] args) {
        // 1.创建原始对象
        Network net = new Real();
        // 2.创建调用处理器对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(net);
        // 3.动态生成代理对象
        Network netproxy = (Network) Proxy.newProxyInstance(
                Dynamic.class.getClassLoader(),
                new Class[] { Network.class },
                myInvocationHandler);
        // 4.客户端通过代理对象调用方法，本次调用将自动被代理处理器的invoke方法接收
        netproxy.browse();
    }
}