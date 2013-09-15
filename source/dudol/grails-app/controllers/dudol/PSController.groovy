package dudol

import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

import org.apache.http.client.fluent.Request

import grails.converters.JSON

class PSController {

    LogService logService
    DestinoService destinoService

    def enviar() {

        if (request.method == 'GET') {
            repassar()
        }
        else if (request.method == 'POST') {
            enfileirar()
        }

        logService.registrarLog(params.exchange, request.getRemoteAddr())

        render ''
    }

    private repassar() {
        String url = getConfMap(params.exchange, params.destino)['url'] + '?' + request.queryString

        render Request.Get(url).execute().returnContent().asString()
    }

    private enfileirar() {
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

        channel.close()
        connection.close()
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
