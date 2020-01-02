import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 10:09
 */
public class RPCProxy {

    /**
     *
     * @param HHServiceImpl  需要发布出去的服务
     * @param port     暴露的端口号
     *
     */
    private final ExecutorService executorService= Executors.newCachedThreadPool();
    public  void  publish(Object service,int port) throws IOException {
        ServerSocket serverSocket =null;
        try {
            serverSocket= new ServerSocket(port);
            while (true){
                //获得一个远程连接    默认阻塞
               final Socket socket = serverSocket.accept();
               //客服端连接进来
                executorService.execute(new ProcessHander(socket,service));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket !=null) {
                serverSocket.close();
            }
        }
    }
}
