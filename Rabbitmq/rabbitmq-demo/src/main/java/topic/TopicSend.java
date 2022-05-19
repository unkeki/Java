package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class TopicSend {

    public final static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception{

        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列，并指定队列类型为topic
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        //消息内容
        String message1 = "新增商品：id1001";
        String message2 = "更新商品：id1002";
        String message3 = "删除商品：id1003";
        //发布消息，并设置routing key
        channel.basicPublish(EXCHANGE_NAME,"item.insert",null,message1.getBytes());
        System.out.println("[生产者]Send："+message1);
        channel.basicPublish(EXCHANGE_NAME,"item.update",null,message2.getBytes());
        System.out.println("[生产者]Send："+message2);
        channel.basicPublish(EXCHANGE_NAME,"item.delete",null,message3.getBytes());
        System.out.println("[生产者]Send："+message3);
        //关系通道
        channel.close();
        //关闭连接
        connection.close();
    }

}
