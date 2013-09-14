import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import com.rabbitmq.client.QueueingConsumer

import spray.json._
import DefaultJsonProtocol._

import dispatch._, Defaults._

object Main {
	
	def main(args: Array[String]) = {

		val exchange = "ex1"

		val factory = new ConnectionFactory()
		factory.setHost("localhost")
		val connection = factory.newConnection()
		val channel = connection.createChannel()

		channel.exchangeDeclare(exchange, "fanout")
		val queueName = channel.queueDeclare().getQueue()
		channel.queueBind(queueName, exchange, "")

		println(" [*] Aguardando mensagens. Para sair, digite CTRL+C")

		val consumer = new QueueingConsumer(channel)
		channel.basicConsume(queueName, true, consumer)

		while (true) {
			val delivery = consumer.nextDelivery()
			val message = new String(delivery.getBody())

			new Thread() { 
				override def run() = {
					val jsonObj = message.asJson.asJsObject
					val conf = jsonObj.getFields("conf")(0).convertTo[Map[String, String]]
					val dados = jsonObj.getFields("dados")(0).convertTo[Map[String, String]]

					val post = url(conf("url"))
					def postComBody = post << dados
					Http(postComBody OK as.String)		
				}
			}.start()
		}

	}
}
