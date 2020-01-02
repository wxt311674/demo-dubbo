import com.rpc.demo.HHService;

import java.io.IOException;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 10:57
 */
public class APP {


    public static void main(String[] args) throws IOException {
        HHService hhService=new HHServiceImpl();
        RPCProxy RPCProxy=new RPCProxy();
        RPCProxy.publish(hhService,8888);
    }
}
