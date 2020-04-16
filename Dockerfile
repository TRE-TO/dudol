FROM openjdk:11
COPY /build/libs/dudol.war /opt
WORKDIR /opt
RUN mkdir /var/lib/h2
#RUN java -Dgrails.env=prod -jar /opt/dudol.war
CMD ["java", "-Dgrails.env=prod", "-jar", "/opt/dudol.war"]
#CMD ["java", "Main"]
