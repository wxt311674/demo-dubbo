import com.rpc.demo.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 14:02
 */
public class PemoteInvocationHandler implements InvocationHandler {
    private String host;
    private  int   port;

    public PemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("host="+host+"port="+port);
        RPCRequest request = new RPCRequest();
        request.setMethodName(method.getName());
        request.setClassName(method.getDeclaringClass().getName());
        request.setPararms(args);
        RpcNetTransport rpcNetTransport =new RpcNetTransport(host,port);
        return  rpcNetTransport.send(request);

    }
}
