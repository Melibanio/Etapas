# Dados Pessoais — API REST (Etapas 1 a 3)

Projeto Spring Boot desenvolvido a partir do tutorial "Dados Pessoais", cobrindo:

- **Etapa 1**: classe `Pessoa`, `PessoaService` (armazenamento em `ConcurrentHashMap`,
  dados pré-populados via `@PostConstruct`) e `PessoaController` com os endpoints
  `GET /pessoas` e `GET /pessoas/{username}`.
- **Etapa 2**: inclusão de pessoas via `POST`, primeiro sem validação
  (`POST /pessoas/sem-validacao`), depois com Bean Validation (`POST /pessoas`
  usando `@Valid`) e validação customizada `@SenhasIguais` (garante que `senha`
  e `senhaRepeticao` sejam iguais).
- **Etapa 3**: `PUT /pessoas/{username}` e `DELETE /pessoas/{username}`,
  `NaoEncontradoException`, `PessoaAlteracaoDto` (DTO próprio para alteração,
  sem `username`/`senha`) e um `@ExceptionHandler` que retorna `ProblemDetail`
  (RFC 7807) com HTTP 404 quando a pessoa não é encontrada.

Inclui também duas páginas HTML de teste em `src/main/resources/static/`:
`form.html` (listagem + cadastro) e `edicao.html` (alteração de uma pessoa).

## Como executar

Pré-requisitos: Java 17+ e Maven (ou use o wrapper, se disponível).

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Formulário de teste: `http://localhost:8080/form.html`
- Listagem: `GET http://localhost:8080/pessoas`

## Endpoints

| Método   | Caminho                       | Descrição                                             |
|----------|--------------------------------|--------------------------------------------------------|
| GET      | `/pessoas`                     | Lista todas as pessoas                                  |
| GET      | `/pessoas/{username}`          | Retorna uma pessoa (404 se não existir)                  |
| POST     | `/pessoas/sem-validacao`       | Cria uma pessoa sem validação (aceita dados inválidos)   |
| POST     | `/pessoas`                     | Cria uma pessoa com validação (`@Valid`)                  |
| PUT      | `/pessoas/{username}`          | Atualiza nome/email/telefone/data/conhecimentos           |
| DELETE   | `/pessoas/{username}`          | Remove uma pessoa (204 No Content)                        |

## Exemplos com `curl`

```bash
# Listar pessoas
curl http://localhost:8080/pessoas

# Buscar uma pessoa
curl http://localhost:8080/pessoas/fulano

# Criar sem validação (aceita dados inválidos)
curl -v -X POST http://localhost:8080/pessoas/sem-validacao -H "Content-Type: application/json" -d "{\"username\":\"teste\",\"nome\":\"\",\"email\":\"email-invalido\",\"telefone\":\"(11) 90909-1234\",\"dataNascimento\":\"2099-01-01\",\"senha\":\"abc\",\"senhaRepeticao\":\"xyz\"}"

# Criar com validação (dados válidos)
curl -v -X POST http://localhost:8080/pessoas -H "Content-Type: application/json" -d "{\"username\":\"maria\",\"nome\":\"Maria da Silva\",\"email\":\"maria@email.com\",\"telefone\":\"(11) 91111-2222\",\"dataNascimento\":\"1990-03-15\",\"senha\":\"minhasenha\",\"senhaRepeticao\":\"minhasenha\"}"

# Atualizar
curl -v -X PUT http://localhost:8080/pessoas/maria -H "Content-Type: application/json" -d "{\"nome\":\"Maria Silva\",\"email\":\"maria@email.com\",\"telefone\":\"(11) 91111-2222\",\"dataNascimento\":\"1990-03-15\",\"conhecimentos\":[\"Java\",\"Spring Boot\"]}"

# Remover
curl -v -X DELETE http://localhost:8080/pessoas/maria

# Buscar pessoa inexistente (deve retornar 404 com ProblemDetail)
curl -v http://localhost:8080/pessoas/inexistente
```

## Estrutura de pacotes

```
br.senac.tads.dsw.dadospessoais
├── DadospessoaisApplication.java
├── Pessoa.java
├── PessoaAlteracaoDto.java
├── PessoaService.java
├── PessoaController.java
├── NaoEncontradoException.java
└── validacao
    ├── SenhasIguais.java
    └── SenhasIguaisValidator.java
```

## Etapa 04 — Spring Data JPA e H2

Nesta etapa foram adicionadas as entidades `PessoaEntity` e `ConhecimentoEntity`, os repositórios JPA e o `DataInitializer`. O `PessoaService` continua utilizando o `Map`, como o tutorial pede; a integração do service com o banco fica para a Etapa 05.

### Como executar

1. Abra a pasta `dadospessoais` no VS Code ou IntelliJ.
2. Execute `./mvnw spring-boot:run` se o projeto tiver Maven Wrapper; caso contrário, execute `mvn spring-boot:run` em um terminal com Maven instalado.
3. Confira o terminal e procure as instruções SQL geradas pelo Hibernate.
4. Acesse `http://localhost:8080/h2-console`.
5. Use JDBC URL `jdbc:h2:mem:dadospessoais`, usuário `sa` e senha vazia.
6. Clique em Connect e execute as consultas abaixo.

```sql
SHOW TABLES;
SELECT * FROM TB_PESSOAS;
SELECT * FROM TB_CONHECIMENTOS;
SELECT * FROM TB_PESSOAS_CONHECIMENTOS;
```

**Observação sobre a dependência do console H2:** este projeto mantém Spring Boot 3.3.4, por isso usa a configuração `spring.h2.console.enabled=true` já suportada nessa versão. A dependência `spring-boot-h2console` citada no PDF é indicada para Spring Boot 4 e não foi adicionada a este projeto para evitar misturar versões incompatíveis.

**Limite do teste nesta entrega:** o ambiente em que o ZIP foi preparado não possui Maven instalado, então não foi possível executar a aplicação aqui. Execute os passos acima no seu computador para confirmar a inicialização e observar as três tabelas antes de responder às questões práticas.
