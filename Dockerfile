#
# Build Stage
#
FROM maven AS build
COPY src /app/src
COPY pom.xml /app/
RUN mvn -f /app/pom.xml clean package -DskipTests

#
# Package Stage
#
FROM java:8
#ADD ./target/mtdan-1.0-SNAPSHOT.jar app.jar
COPY --from=build /app/target/mtdan-1.0-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
HEALTHCHECK --interval=1m30s --timeout=20s --start-period=40s --retries=3 CMD [ "curl", "-f", "http://localhost:8080/todos" ]