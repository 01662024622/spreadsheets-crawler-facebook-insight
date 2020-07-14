FROM openjdk:8u141-jre

COPY ./target/facebook-0.0.1-SNAPSHOT.jar /app/dwh/
COPY ./src/main/resources/credential.json /app/dwh/
WORKDIR /app/dwh
ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD java -jar -Dspring.profiles.active=release /app/dwh/facebook-0.0.1-SNAPSHOT.jar
