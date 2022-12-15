FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/productService-1.0.jar productservice.jar
ENTRYPOINT ["java","-jar","/productservice.jar"]