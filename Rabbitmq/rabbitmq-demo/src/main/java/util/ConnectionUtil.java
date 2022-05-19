package util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception{
        //简历
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("1.12.250.154");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws Exception{

        Connection con = ConnectionUtil.getConnection();
        System.out.println(con);
        con.close();

    }
}
