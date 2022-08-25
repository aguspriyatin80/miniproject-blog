FROM adoptopenjdk/openjdk11:jdk11u-alpine-nightly
EXPOSE 9090
COPY target/blog-0.0.1-SNAPSHOT.jar blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","blog-0.0.1-SNAPSHOT.jar"]
