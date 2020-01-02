
import java.lang.reflect.Proxy;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 13:54
 */
public class RpcProxyClient {
    public <T>  T clientProxy(final  Class<T> interfaceClass,final  String host,final  int port){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass}, new PemoteInvocationHandler(host,port));
    }
}
