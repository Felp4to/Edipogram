#Docker file per il servizio enigmi-seguiti

FROM openjdk:17-jdk
ADD build/libs/enigmi-seguiti.jar enigmi-seguiti.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "enigmi-seguiti.jar"]
HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1