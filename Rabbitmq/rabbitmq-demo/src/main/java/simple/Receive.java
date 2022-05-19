package simple;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;

public class Receive {

    public final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){

            //获取消息并且处理，类似于监听，如果队列中有消息，自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //body就是消息体
                String message = new String(body);

                System.out.println("[x]Receive'"+message+"'");

                //手动进行ACK
                channel.basicAck(envelope.getDeliveryTag(),false);

            }

        };
        //监听队列,第二个参数：是否自动进行消息确认（ACK）
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
