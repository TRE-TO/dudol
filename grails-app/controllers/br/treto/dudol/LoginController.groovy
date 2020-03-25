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


        if(request.method == 'POST'){
            String login = params.login

            String senha  = params.senha
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://autorizador.tre-to.jus.br/v2/autenticar/");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("login",login));
            params.add(new BasicNameValuePair("senha", senha.bytes.encodeBase64().toString()));
            params.add(new BasicNameValuePair("sistema", "DUDOL"));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response = client.execute(httpPost);
            String retorno  = EntityUtils.toString(response.getEntity());

            if(response.statusLine.statusCode.equals(200)) {

                session["logado"] = true
                redirect (uri:"")

            }
            else{
                flash.message = "Login e/ou senha inválidos"
            }
        }

        render (view: "index")

    }

    def logout(){
        session.invalidate()
        redirect(uri:"/login")
    }

    def teste(){
        render status:200, text: params.id
    }


}
