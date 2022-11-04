FROM 142401413602.dkr.ecr.sa-east-1.amazonaws.com/ecr-image-default-java

COPY target/*.jar /opt/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-javaagent:newrelic/newrelic.jar","-jar","-Duser.country=BR","-Duser.language=pt","app.jar"]