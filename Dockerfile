FROM openjdk:11
ARG JAR_FILE=build/libs/MediscreenApp-0.0.1-SNAPSHOT.jar
VOLUME /tmp
COPY ${JAR_FILE} MediscreenApp-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app
RUN sh -c 'touch MediscreenApp-0.0.1-SNAPSHOT.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /MediscreenApp-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8084