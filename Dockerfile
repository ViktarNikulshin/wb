FROM maven:3.8.3-openjdk-17
LABEL authors="ViktarNikulshin"
VOLUME /app
COPY target/

ENTRYPOINT ["top", "-b"]