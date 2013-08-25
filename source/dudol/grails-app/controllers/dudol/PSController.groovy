package dudol

import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import grails.converters.JSON

class PSController {

	LogService logService

    def enviar() {

		ConnectionFactory factory = new ConnectionFactory()
        factory.setHost("localhost")
        Connection connection = factory.newConnection()
        Channel channel = connection.createChannel()

        String exchange = params.grupo

        channel.exchangeDeclare(exchange, "fanout")

        String message = params.msg

        channel.basicPublish(exchange, "", null, message.getBytes())

        channel.close()
        connection.close()

        logService.registrarLog(exchange, request.getRemoteAddr())
   }
}
