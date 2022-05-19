package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class FanoutSend {

    public final static String EXCHANGE_QUEUE = "fanout_exchange";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明exchange，指定类型fanout
        channel.exchangeDeclare(EXCHANGE_QUEUE,"fanout");
        //消息内容
        String message = "Hello Everyone";
        System.out.println("[生产者]Send:"+message);
        //发布消息到exchange
        channel.basicPublish(EXCHANGE_QUEUE,"",null,message.getBytes());
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
    }
}
