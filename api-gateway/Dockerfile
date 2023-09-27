#Docker file per il servizio apigateway

FROM openjdk:17-jdk
ADD build/libs/api-gateway.jar api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "api-gateway.jar"]
HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1
