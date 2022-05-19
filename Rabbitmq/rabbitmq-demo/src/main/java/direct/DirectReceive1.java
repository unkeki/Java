package direct;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;

public class DirectReceive1 {

    public final static String EXCHANGE_NAME = "direct_exchange";

    public final static String QUEUE_NAME = "direct_exchange_queue_1";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机，同时指定需要订阅的routing key
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            //获取消息，并且处理，当队列有消息的时候自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                String message = new String(body);

                System.out.println("[Direct消费者1]received:"+message+"! ");
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

}
