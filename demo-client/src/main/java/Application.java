import com.rpc.demo.HHService;

/**
 * TODO  注释
 *
 * @author yhuiyun
 * @date 2019/10/28 9:58
 */
public class Application {

    public static void main(String[] args) {
        RpcProxyClient prcClient = new RpcProxyClient();
        HHService HHService=prcClient.clientProxy(HHService.class,"localhost",8888);
        System.out.println(HHService.getString("原慧赟"));
    }
}
