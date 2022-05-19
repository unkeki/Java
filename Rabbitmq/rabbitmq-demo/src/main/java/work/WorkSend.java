package work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class WorkSend {

    public final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{

        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //循环发布消息
        for (int i = 1;i<=50;i++){
            String message = "task.." + i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

            System.out.println("[x]Send'"+message+"'");
        }
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();

    }
}
