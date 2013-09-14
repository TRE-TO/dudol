package dudol

import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

import grails.converters.JSON

class PSController {

    LogService logService
    DestinoService destinoService

    def enviar() {

	ConnectionFactory factory = new ConnectionFactory()
        factory.setHost("localhost")
        Connection connection = factory.newConnection()
        Channel channel = connection.createChannel()

        //Removendo itens que não precisam ser enfileirados.
        params.remove('action')
        params.remove('controller')

        def msg = [ dados: params, conf: getConfMap(params.exchange, params.destino) ]
        def msgJson = msg.encodeAsJSON()

        channel.exchangeDeclare(params.exchange, "fanout")
        channel.basicPublish(params.exchange, "", null, msgJson.getBytes())
        println " [*] Enviado: ${msgJson}"

        channel.close()
        connection.close()

        logService.registrarLog(params.exchange, request.getRemoteAddr())

        render ''
    }

    private getConfMap(String exchange, String destino) {
        def paramConf = destinoService.gerarParametros(exchange, destino)
        def conf = [:]
        for (i in paramConf[0].split('\n')) {
            def s = i.split('=')
            if (s.length == 2) conf[s[0]] = s[1]
        }
        conf        
    }
}
