package dudol

import java.io.IOException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import grails.converters.JSON

class PSController {

    def enviar() {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(params.grupo, "fanout");

        String message = params.t

        channel.basicPublish(params.grupo, "", null, message.getBytes());
        System.out.println(" [x] Enviado: '" + message + "'");

        channel.close();
        connection.close();
   }
}
