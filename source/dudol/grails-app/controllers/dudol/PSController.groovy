package dudol

import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

import java.io.IOException

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.ResponseHandler
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

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

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url)

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode()

                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity()
                        return entity != null ? EntityUtils.toString(entity) : null
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status)
                    }
                }

            }
            String responseBody = httpclient.execute(httpget, responseHandler)
            render responseBody
        }
        catch (Exception e) {
            render 'ERRO'
        }
        finally {
            httpclient.close()
        }
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
