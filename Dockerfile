FROM maven:3.8.3-openjdk-17
LABEL authors="ViktarNikulshin"
VOLUME /app
COPY target/wb-0.0.1-SNAPSHOT.war app/app.war
COPY tokens/StoredCredential tokens/StoredCredential

ENTRYPOINT ["java", "-jar", "app/app.war"]