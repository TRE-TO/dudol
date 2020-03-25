package br.treto.dudol

import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

import java.util.concurrent.*
import groovy.json.JsonSlurper
class ScheduleRefManager {
	
	private static Map<String,ScheduledFuture> refs = new HashMap<String,ScheduledFuture>()

	private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5)

	public static start(Schedule schedule, Long startIn = 2) {
		ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				println "executando"
				try{
					def jsonSlurper = new JsonSlurper()
					def obj = jsonSlurper.parseText(schedule.executable)

					if(obj.method == 'POST'){

						CloseableHttpClient client = HttpClients.createDefault();
						HttpPost httpPost = new HttpPost(obj.url);


						obj.headers.each{
							def chave = it.keySet()[0]
							def valor =it.get(it.keySet()[0])
							httpPost.setHeader(chave.toString(),valor.toString())
						}

						List<NameValuePair> parametros = new ArrayList<NameValuePair>();

						def hasBody = false
						obj.params.each{
							def chave = it.keySet()[0]
							def valor =it.get(it.keySet()[0])

							if(chave.equals("body")){
								httpPost.setEntity(valor)
								hasBody = true
							}
							parametros.add(new BasicNameValuePair(chave.toString(),valor.toString()))
						}

						if(!hasBody)
							httpPost.setEntity(new UrlEncodedFormEntity(parametros));

						CloseableHttpResponse response = client.execute(httpPost);
						String retorno  = EntityUtils.toString(response.getEntity());
//					println "retorno"+retorno
//					if(response.statusLine.statusCode.equals(200)) {
//						println "[DUDOL] ok."+obj.url
//					}
						client.close()
					}
					else if(obj.method == "GET"){


						URIBuilder builder = new URIBuilder(obj.url);
						obj.params.each{
							def chave = it.keySet()[0]
							def valor =it.get(it.keySet()[0])
							builder.setParameter(chave,valor)
						}

						obj.headers.each{
							def chave = it.keySet()[0]
							def valor =it.get(it.keySet()[0])
							httpGet.setHeader(chave.toString(),valor.toString())
						}

						CloseableHttpClient client = HttpClients.createDefault();
						HttpGet httpGet = new HttpGet(builder.build());
						CloseableHttpResponse response = client.execute(httpGet);
						String retorno  = EntityUtils.toString(response.getEntity());
//					println "retorno"+retorno
//					if(response.statusLine.statusCode.equals(200)) {
//						println "[DUDOL] ok."+obj.url
//					}
						client.close()

					}
				}catch(Exception ex){
					println "[DUDOL] Erro ao rodar: "+schedule.executable+". erro: "+ex.getMessage()
				}




				//Runtime.getRuntime().exec(schedule.executable.split())


			}
		},
		startIn,
		new Long(schedule.rateInSeconds),
		TimeUnit.SECONDS)

	
		println "[DUDOL] Agendando tarefa '${schedule.key}' para início em ${startIn} segundos..."


		refs.put(schedule.key, scheduledFuture)
	}

	public static cancel(String key) {
		refs.get(key).cancel(true)
		refs.remove(key)
		println "[DUDOL] Tarefa '${key}' interrompida."
	}

	public static boolean isRunning(String key) {
		return refs.containsKey(key)
	}
}