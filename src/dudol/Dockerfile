FROM openjdk:11-jre-slim
COPY build/libs/dudol.war /opt
WORKDIR /opt
RUN mkdir /var/lib/h2
CMD ["java", "-Dgrails.env=prod", "-jar", "/opt/dudol.war"]
