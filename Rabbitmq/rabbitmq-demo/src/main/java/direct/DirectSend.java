package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class DirectSend {

    public final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception{

        //获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列，指定类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //消息内容
        String message1 = "商品新增了，id为1001";
        String message2 = "商品更新了，id为1001";
        String message3 = "商品删除了，id为1001";
        //发送消息，并且指明routing ket为insert
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message1.getBytes());
        System.out.println("[生产者]Send:"+message1);
        channel.basicPublish(EXCHANGE_NAME,"update",null,message2.getBytes());
        System.out.println("[生产者]Send:"+message2);
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message3.getBytes());
        System.out.println("[生产者]Send:"+message3);
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
    }
}
