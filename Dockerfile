FROM java:8
ADD ./target/mtdan-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
HEALTHCHECK --interval=1m30s --timeout=20s --start-period=40s --retries=3 CMD [ "curl", "-f", "http://localhost:8080/todos" ]