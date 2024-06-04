# Definindo a imagem base como Ubuntu
FROM ubuntu:latest AS build

# Atualizando o ambiente
RUN apt-get update -y && apt-get upgrade -y

# Instalando o OpenJDK
RUN apt-get install -y openjdk-17-jdk

# Instalando o Maven
RUN apt-get install -y maven

# Copiando o código fonte para o container
COPY . .

ENV URL=dpg-cotal27sc6pc73cgi7q0-a.oregon-postgres.render.com
ENV PORT=5432
ENV POSTGRES_DB=todolist_fsfu
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=Dms9U6C6G9V9MS6dYeKwDtRa9Hskk9HQ

# Solicitando a geração do arquivo JAR com o Maven
RUN mvn clean install -e

# Escolhendo a imagem que executará a aplicação
FROM openjdk:17-jdk-slim

# Expondo a porta 8080 para acesso externo
EXPOSE 8080

# Copy the .env file to the container (adjust the path as necessary)
#COPY --from=build .env .env

# Load environment variables from the .env file at runtime
#CMD export $(cat .env | xargs) && java -jar app.jar

# Copiando o arquivo JAR para execução
COPY --from=build target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

## Executando o arquivo JAR com as variáveis de ambiente carregadas
#CMD ["dotenv", "-e", ".env", "java", "-jar", "app.jar"]