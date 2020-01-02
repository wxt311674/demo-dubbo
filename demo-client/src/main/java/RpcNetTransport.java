import com.rpc.demo.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 14:14
 */
public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() throws IOException {
        System.out.println("开始构建客户端链家");
        return  new Socket(host,port);
    }

    public  Object send(RPCRequest request) throws Exception {
        Socket socket =null;
        Object result = null;
        ObjectOutputStream oos=null;
        ObjectInputStream  ios=null;
        try {
            socket=newSocket();
            oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            ios=new ObjectInputStream(socket.getInputStream());
            result = ios.readObject();
        }catch (Exception e){
            throw  e;
        }finally {
            if(socket !=null){
                socket.close();
            }
           if(oos!=null){
               oos.close();
           }
           if(ios!=null){
               ios.close();
           }
        }
        return result;
    }
}
