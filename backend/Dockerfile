FROM openjdk:21 AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -Dmaven.test.skip

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/target/*.jar /app/backend.jar

CMD ["java","-jar","/app/backend.jar"]