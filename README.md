# Projeto com Spring Boot e Ionic com Angular

Este projeto é um teste utilizando **Spring Boot (Java 21)** no backend e **Ionic 8 com Angular 19** no frontend.

## Tecnologias Utilizadas

- **Backend:** Java 21 com Spring Boot
- **Frontend:** Ionic 8 com Angular 19
- **Banco de Dados:** PostgreSQL
- **Ferramentas de Build:** Maven
- **Containerização:** Docker Compose

## Endpoints da API

A API expõe os seguintes endpoints:

### Autenticação

### Usuário (Login)

- **POST `/usuario`** - Realiza a autenticação do usuário.
  - **Credenciais Padrão:**
    - **Login:** `admin`
    - **Senha:** `admin123`
  - **Resposta:**
    ```json
      {
        "id": 9007199254740991,
        "loguin": "admin",
        "senha": "admin123"
      }
    ```

- **POST `/token`** - Gera um token JWT para autenticação.
  - **Resposta:**
    ```json
    {
      "sub": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub3h0ZWMiLCJpYXQiOjE3Mzg1NDM5NjQsImV4cCI6MTczODU0NzU2NH0.cV9FJx2CJanSbNmrylaYC1MK8CoCpHzErzE6GbmZ6Io"
    }
    ```
  - O token JWT deve ser enviado no cabeçalho `Authorization` como `Bearer <token>` em requisições protegidas.

### Tarefas

- **GET `/tarefas`** - Retorna a listagem de tarefas.
- **POST `/tarefas`** - Cria uma nova tarefa.
  - **Payload:**
    ```json
    {
        "titulo": "string",
        "descricao": "string",
        "completada": true,
        "data": "2025-03-03T15:06:12.896Z",
        "usuario_id": 9007199254740991,
        "categoria_id": 9007199254740991
    }
    ```
  - **Campos obrigatórios:** `titulo`, `data`, `usuario_id`, `categoria_id`
- **PUT `/tarefas/{id}`** - Atualiza uma tarefa existente.
  - **Payload:**
    ```json
    {
        "id": 9007199254740991,
        "titulo": "string",
        "descricao": "string",
        "completada": true,
        "data": "2025-03-03T15:06:12.896Z",
        "usuario_id": 9007199254740991,
        "categoria_id": 9007199254740991
    }
    ```
- **DELETE `/tarefas/{id}`** - Exclui uma tarefa pelo ID.
- **GET `/tarefas/{id}`** - Retorna uma tarefa específico pelo ID.

## Documentação da API

A documentação da API pode ser acessada via Swagger na seguinte URL:

[Swagger UI] `http://localhost:8080/swagger-ui.html`

## Como Executar o Projeto

### Banco de Dados e o RabbitMQ estam no Docker Compose (inicie primeiro)

1. Certifique-se de ter o Docker e o Docker Compose instalados.
2. Na raiz do projeto, execute o seguinte comando para subir o banco de dados PostgreSQL:
   ```sh
   docker-compose up -d
   ```
3. O banco de dados estará disponível para conexão.

### Backend

1. Certifique-se de ter o Java 21 instalado.
2. Clone este repositório.
3. Navegue até a pasta do backend.
4. Execute o seguinte comando para iniciar o servidor:
   ```sh
   mvn spring-boot:run
   ```

### Frontend

1. Certifique-se de ter o Node.js instalado.
2. Navegue até a pasta do frontend (todo-list-ionic).
3. Instale as dependências:
   ```sh
   npm install
   ```
4. Execute o seguinte comando para iniciar o servidor de desenvolvimento:
   ```sh
   ionic serve
   ```
5. Acesse o frontend pelo navegador em `http://localhost:8100`.
6. Na tela de login, utilize as credenciais padrão:
   - **Login:** `admin`
   - **Senha:** `admin123`
