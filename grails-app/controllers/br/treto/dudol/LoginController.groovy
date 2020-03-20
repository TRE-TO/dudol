package br.treto.dudol

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

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://autorizador.tre-to.jus.br/v2/autenticar/");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login", "alexandre.oliveira"));
        params.add(new BasicNameValuePair("senha", "MTIzNjU0Nzg="));
        params.add(new BasicNameValuePair("sistema", "COYOTE"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = client.execute(httpPost);
        String retorno  = EntityUtils.toString(response.getEntity());
        render(text:'teste'+retorno);

    }
}
