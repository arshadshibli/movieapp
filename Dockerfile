FROM openjdk:10-jdk
#RUN apt-get -y update && apt-get -y install default-jre && apt-get -y clean
ADD ./target/Movie-Cruiser-App-0.0.1-SNAPSHOT.jar /usr/src/Movie-Cruiser-App-0.0.1-SNAPSHOT.jar
#VOLUME /opt/fitnesse/
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "Movie-Cruiser-App-0.0.1-SNAPSHOT.jar"]