name := "consumidorWS"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies += "com.rabbitmq" % "amqp-client" % "3.1.4"

libraryDependencies += "io.spray" %%  "spray-json" % "1.2.5"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"