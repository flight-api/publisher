FROM maven:3.6.1-jdk-8

RUN ls -l

WORKDIR /code

ADD pom.xml /code/pom.xml

ADD src /code/src

RUN mvn clean install
EXPOSE 8080

CMD java -jar target/publisher-0.0.1-SNAPSHOT.jar