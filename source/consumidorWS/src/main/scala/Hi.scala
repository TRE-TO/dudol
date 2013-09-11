import java.io.IOException
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import com.rabbitmq.client.QueueingConsumer

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

		println(" [*] Waiting for messages. To exit press CTRL+C")

		val consumer = new QueueingConsumer(channel)
		channel.basicConsume(queueName, true, consumer)

		while (true) {
			val delivery = consumer.nextDelivery()
			val message = new String(delivery.getBody())

			println(" [x] Received '" + message + "'")
		}

	}
}