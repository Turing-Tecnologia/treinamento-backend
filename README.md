# Backend da aplicação da clínica de odontologia (codinome Albatroz).

## Como sou executado localmente?
- instale o [Java](https://adoptopenjdk.net/)
- instale o [PostgreSQL+pgAdmin](https://www.postgresql.org/download/), com senha.
- crie a base de dados `albatroz` no PostgreSQL (Servers->PostgreSQL->Create->Database)
- clone o projeto
- modifique a senha do sgbd no arquivo src/main/resources/application.properties
- execute `mvn package -DskipTests` no terminal na pasta do projeto
- execute `java -jar target/backend-albatroz-0.0.1-SNAPSHOT.jar` no terminal na pasta do projeto

Os endpoints da API estarão disponíveis em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

*Obs.: O Albatroz foi testado com a OpenJDK 11(LTS)+JVM HotSpot. Em testes com a OpenJ9 ocorreu dump de memória. Não foi testado na GraalVM.*
