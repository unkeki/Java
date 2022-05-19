package work;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;

public class WorkReceive1 {

    public final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //能者多劳，告诉rabbitmq一次不要向工作人鱼发送多于一条消息
        channel.basicQos(1);
        //定义队列消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                String message = new String(body);

                System.out.println("[消费者1]received:"+message+"!");

                //模拟任务耗时：1000ms
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //手动ACK
                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        };
        //监听队列,手动ACK需要将第二个参数设置为false
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
