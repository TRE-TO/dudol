package br.treto.dudol

import groovy.json.JsonSlurper
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

class LoginController {

    def index() {

        if (grailsApplication.config.autenticarAdmin == false) {
            session["logado"] = true
            redirect (uri:"/home")
        }

        if (request.method == 'POST') {
            String login = params.login
            String senha  = params.senha

            CloseableHttpClient client = HttpClients.createDefault()
            HttpPost httpPost = new HttpPost(grailsApplication.config.autorizador.url.autenticar)

            List<NameValuePair> params = new ArrayList<NameValuePair>()
            params.add(new BasicNameValuePair("login",login))
            params.add(new BasicNameValuePair("senha", senha.bytes.encodeBase64().toString()))
            params.add(new BasicNameValuePair("sistema", grailsApplication.config.autorizador.sistema))
            httpPost.setEntity(new UrlEncodedFormEntity(params))

            CloseableHttpResponse response = client.execute(httpPost)
            String retorno = EntityUtils.toString(response.getEntity())

            if (response.statusLine.statusCode.equals(200)) {

                JsonSlurper json = new JsonSlurper()
                def obj =  json.parseText(retorno)
                def isAdmin = false
                obj.perfis.each{
                    if (it.equals('ADMIN'))
                        isAdmin = true
                }
                if (isAdmin) {
                    session["logado"] = true
                    redirect (uri:"/home")
                }
                flash.message = "Usuário sem permissão"
            }
            else {
                flash.message = "Login e/ou senha inválidos"
            }
        }

        render (view: "index")
    }

    def logout() {
        session.invalidate()
        redirect(uri:"/login")
    }

    def teste() {
        render status:200, text: params.id
    }
}
