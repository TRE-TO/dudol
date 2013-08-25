package dudol

import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import grails.converters.JSON

class PSController {

	LogService logService
    DestinoService destinoService

    def enviar() {

		ConnectionFactory factory = new ConnectionFactory()
        factory.setHost("localhost")
        Connection connection = factory.newConnection()
        Channel channel = connection.createChannel()

        println destinoService.gerarParametros(params.exchange, params.destino)

        channel.exchangeDeclare(params.exchange, "fanout")

        channel.basicPublish(params.exchange, "", null, params.msg.getBytes())

        channel.close()
        connection.close()

        logService.registrarLog(params.exchange, request.getRemoteAddr())
   }
}
