FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY recipe-vault-backend /app/recipe-vault-backend

WORKDIR /app/recipe-vault-backend

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/recipe-vault-backend-0.0.1-SNAPSHOT.jar"]
