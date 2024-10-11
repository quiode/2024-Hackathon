FROM openjdk:21 AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -Dmaven.test.skip

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 8080

CMD ["java","-jar","/app/backend.jar"]