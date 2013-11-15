package br.treto.dudol

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

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

@Transactional(readOnly = true)
class HTTPServiceController {

	static allowedMethods = [get: 'GET', post: 'POST']

	def get() {
		if (!params.key) {
			render(status: 400, text: 'O parâmetro "key" deve ser informado.')
			return
		}

        HTTPService conf = HTTPService.findWhere(key: params.key)
        if (!conf) {
            render(status: 400, text: 'A chave informada (' + params.key + ') não existe.')
            return
        }

        // Ex: part1/part2/part3
        def suffix = (params.opt) ? new StringBuffer('/' + params.opt) : new StringBuffer()
        if (request.queryString) suffix << '?' + request.queryString
        String url = conf.baseUrl + suffix.toString()

        CloseableHttpClient httpclient = HttpClients.createDefault()
        try {
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
	}

	def post() {
		//TODO
	}

    private registrarErro(String url, Integer status) {
        render(status: status, text: 'Requisição inválida - Status: ' + status + '<br/>URL usada: ' + url)
        log.warn 'Requisição inválida - Status: ' + status + '<br/>URL usada: ' + url
    }

}