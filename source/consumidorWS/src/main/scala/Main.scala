import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import com.rabbitmq.client.QueueingConsumer
// import dispatch._, Defaults._

object Hi {
	
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
					println(" [*] Recebeu: " + message)

					import spray.json._
					import DefaultJsonProtocol._

					val jsonAst = message.asJson
					val msg = jsonAst.asJsObject.getFields("dados", "conf") match {
						case Seq(JsObject(dados), JsObject(conf)) => List(dados, conf("url"))
						case _ => Nil
					}

					import dispatch._, Defaults._
					val svc = url(msg.tail.head.toString().replaceAll("\"", ""))
					val country = Http(svc OK as.String)
					for (c <- country)
						println(c)

				}
			}.start()
		}

	}
}