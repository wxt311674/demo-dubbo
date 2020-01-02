import com.rpc.demo.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 10:24
 */
public class ProcessHander implements Runnable{

    private Socket socket;
    private Object objectService;

    public  ProcessHander (Socket socket,Object objectService){
        this.objectService=objectService;
        this.socket=socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois=new ObjectInputStream(socket.getInputStream());
            RPCRequest RPCRequest =(RPCRequest) ois.readObject();
            Object result = invork(RPCRequest);
            oos=new ObjectOutputStream(socket.getOutputStream());
            //序列化  写入通信管道中
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private Object  invork(RPCRequest RPCRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //请求的参数
        Object[] pararms=RPCRequest.getPararms();
        Class<?>[] type=new Class[pararms.length];
        for (int i=0;i<pararms.length;i++){
            type[i]= pararms[i].getClass();
        }
        //反射加载

        Class<?> aClass = Class.forName(RPCRequest.getClassName());
        Method method = aClass.getMethod(RPCRequest.getMethodName(), type);
        Object invoke = method.invoke(objectService, pararms);
        return invoke;
    }
}
