FROM gradle:latest AS build
WORKDIR /app
COPY build.gradle .
COPY src ./src
RUN gradle clean assemble


FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar ./sporttag-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "sporttag-backend.jar"]