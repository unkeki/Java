package topic;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;

public class TopicReceive1 {

    public final static String EXCHANGE_NAME = "topic_exchange";

    public final static String QUEUE_NAME = "topic_exchange_queue_1";

    public static void main(String[] args) throws Exception{

        //获得连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定交换机，并指定routing key
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.delete");
        //定义队列消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            //获取消息，并且处理，当队列有消息的时候自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                String message = new String(body);

                System.out.println("[Topic消费者1]received:"+message+"! ");
            }

        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }

}
