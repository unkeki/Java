package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class Send {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception{
        //获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中穿件通道，通过通过才能完成数据的相关操作
        Channel channel = connection.createChannel();
        /**
         * 队列名称
         * 持久队列（该队列在服务器重启之后还存在）
         * 排他队列
         * 自动删除队列（服务器将在不再使用该队列的时候删除他）
         * 参数队列的其他属性（构造参数）
         */
        //声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String message = "Hello World!";
        //向指定的队列中发布消息
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("[x]Send'"+message+"'");
        //关闭通道
        channel.close();
        //关闭连接
        connection.close();

    }

}
