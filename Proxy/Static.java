interface Network {
    // 定义网络接口
    public void browse();
}

// 真实上网操作
class Real implements Network {
    @Override
    public void browse() {
        System.out.println("上网冲浪");
    }
}

// 代理上网
class Proxy implements Network {
    private Network network;

    public Proxy(Network network) {
        this.network = network;
        System.out.println("猫猫使用了神秘的抛瓦！");
    }

    public void check() {
        System.out.println("🈲真人面对面收割，美女角色在线掉分，发狂玩蛇新天地🈲");	// 与具体上网相关的操作
    }
	
    @Override
    public void browse() {
        this.check();	// 调用具体业务
        this.network.browse();	// 
    }
}

public class Static {
    public static void main(String[] args) {
        Network net = null;
        net = new Proxy(new Real());	// 实例化代理，同时传入真实操作
        net.browse();	// 客户只关心核心业务——上网
    }
}