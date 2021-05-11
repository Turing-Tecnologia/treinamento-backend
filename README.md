# Backend da aplicação da clínica de odontologia (codinome Albatroz).

## Como sou executado localmente?
- instale o [Java](https://adoptopenjdk.net/)
- instale o [PostgreSQL+pgAdmin](https://www.postgresql.org/download/), com senha.
- crie a base de dados `albatroz` no PostgreSQL (Servers->PostgreSQL->Create->Database)
- clone o projeto
- modifique a senha do sgbd no arquivo src/main/resources/application.properties
- execute `./mvnw clean package -DskipTests spring-boot:run` no terminal na pasta do projeto
- os endpoints da API estarão disponíveis em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

*Obs. 1: Há restrição dos dias de salvar consultas para terças e quintas. Ao testar, remova a restrição na classe "ConsultaServiceImplementation.java"*

*Obs. 2: O Albatroz foi testado com a OpenJDK 11(LTS)+JVM HotSpot. Em testes com a OpenJ9 ocorreu dump de memória. Não foi testado na GraalVM.*
