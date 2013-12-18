package br.treto.dudol

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.IOException

import java.util.Map.Entry

import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.ResponseHandler
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair


@Transactional(readOnly = true)
class HTTPServiceController {

	static allowedMethods = [processRequest: ['GET', 'POST']]

    def processRequest() {
        if (request.method == 'GET') get()
        else {
            if (post())
                render(status: 200, text: 'Dados enviados.')
            else
                render(status: 400, text: 'Ocorreu um erro.')
        }
    }

    private callAction(request, params, action) {
        if (!params.key) {
            render(status: 400, text: 'O parâmetro "key" deve ser informado.')
            return
        }

        HTTPService conf = HTTPService.findWhere(key: params.key)
        if (!conf) {
            render(status: 400, text: 'A chave informada (' + params.key + ') não existe.')
            return
        }

        Boolean sucesso = false
        String url = createUrl(request, params, conf)
        CloseableHttpClient httpclient = HttpClients.createDefault()
        try {
            action(httpclient, conf, url)
            sucesso = true
        }
        catch (ClientProtocolException e) {
            int statusorig = Integer.parseInt(e.message)
            response.status = (statusorig < 300) ? 400 : statusorig
            registrarErro(url, response.status)
        }
        catch (Exception e) {
            response.status = 500
            registrarErro(url, response.status)
        }
        finally {
            httpclient.close()
        }
        sucesso
    }

	private get() {
        def action = { httpclient, conf, url ->
            HttpGet httpget = new HttpGet(url)
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode()

                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity()
                        return entity != null ? EntityUtils.toString(entity) : null
                    }
                    else {
                        throw new ClientProtocolException(String.valueOf(status))
                    }
                }
            }
            render httpclient.execute(httpget, responseHandler)
        }
        
        callAction(request, params, action)
	}

	private post() {
        params.remove('action')
        params.remove('controller')

		def action = { httpclient, conf, url ->
            HttpPost httpPost = new HttpPost(url)

            List <NameValuePair> nvps = new ArrayList <NameValuePair>()
            for (Entry<String, Object> entry : params) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()))
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps))
            CloseableHttpResponse response = httpclient.execute(httpPost)

            try {
                System.out.println(response.getStatusLine())
                HttpEntity entity = response.getEntity()
                // se fosse precisar tratar o conteudo retornado, seria aqui
                EntityUtils.consume(entity)
            } finally {
                response.close()
            }
        }

        callAction(request, params, action)

        //TODO: Controle de 'retentativas' para quando não conseguir da primeira vez.
	}

    private registrarErro(String url, Integer status) {
        render(status: status, text: 'Requisição inválida - Status: ' + status + '<br/>URL usada: ' + url)
        log.warn 'Requisição inválida - Status: ' + status + '<br/>URL usada: ' + url
    }

    private createUrl(request, params, conf) {
        // Ex: part1/part2/part3
        def suffix = (params.opt) ? new StringBuffer('/' + params.opt) : new StringBuffer()
        if (request.queryString) suffix << '?' + request.queryString
        conf.baseUrl + suffix.toString()
    }

}