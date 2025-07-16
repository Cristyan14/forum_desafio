API Fórum (Desafio Alura)
Descrição do Projeto

API REST para gerenciamento de tópicos em um fórum, feita com Spring Boot e JPA.
Permite criar, listar, detalhar, atualizar e excluir tópicos, além de filtrar por curso e ano.

O projeto utiliza banco de dados MySQL para persistência e paginação nos endpoints.
Tecnologias Utilizadas

    Java 17+

    Spring Boot 3.x

    Spring Data JPA

    MySQL

    Jakarta Validation

    Maven

Configuração do Banco de Dados MySQL

Crie o banco e a tabela topico com os comandos abaixo:

CREATE DATABASE desafio_forum CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE desafio_forum;

CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL,
    data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

    Importante: A coluna data_criacao foi criada para armazenar a data de criação do tópico.

Configuração do Projeto

No arquivo application.properties ou application.yml, configure a conexão com seu banco MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/desafio_forum?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Endpoints Disponíveis
Método	URI	Descrição	Request Body	Response
POST	/topicos	Criar um novo tópico	JSON com título, mensagem, autor, curso	Objeto Topico criado
GET	/topicos	Listar tópicos (paginado)	Parâmetros opcionais: curso, ano, paginação	Página de tópicos
GET	/topicos/{id}	Detalhar um tópico pelo ID	—	Objeto Topico ou 404
PUT	/topicos/{id}	Atualizar um tópico pelo ID	JSON com título, mensagem, autor, curso	Objeto Topico atualizado ou 404
DELETE	/topicos/{id}	Excluir um tópico pelo ID	—	200 OK ou 404
Como Testar a API
Usando o Insomnia ou Postman

    Criar tópico

        Método: POST

        URL: http://localhost:8080/topicos

        Body (JSON):

        {
          "titulo": "Título do tópico",
          "mensagem": "Mensagem do tópico",
          "autor": "Nome do autor",
          "curso": "Nome do curso"
        }

    Listar tópicos (com paginação e filtros opcionais)

        Método: GET

        URL: http://localhost:8080/topicos?curso=Spring Boot&ano=2025&page=0&size=10

    Detalhar tópico

        Método: GET

        URL: http://localhost:8080/topicos/{id}

    Atualizar tópico

        Método: PUT

        URL: http://localhost:8080/topicos/{id}

        Body: JSON igual ao POST

    Excluir tópico

        Método: DELETE

        URL: http://localhost:8080/topicos/{id}

Sobre Autenticação e Testes no Insomnia
Por que usar autenticação?

Sua API pode conter dados sensíveis e precisa proteger quem acessa e altera esses dados.
Autenticação garante que somente usuários autorizados consigam usar os endpoints.
Como funciona a autenticação?

    Geralmente usamos tokens (exemplo: JWT) para identificar o usuário após login.

    A cada requisição, o token deve ser enviado no cabeçalho HTTP para validar o acesso.

Como configurar o Insomnia para autenticação?

    Se você usar Basic Auth (usuário e senha simples):
    Vá na aba Auth da requisição, escolha Basic Auth e preencha usuário e senha.

    Se você usar Bearer Token (JWT):
    Adicione um header na requisição:

    Authorization: Bearer <seu_token_jwt>

    Para testar APIs protegidas, primeiro faça a requisição de login para obter o token.

Considerações Finais

    Certifique-se que o banco de dados está rodando e configurado corretamente.

    Para mudanças no modelo, utilize o spring.jpa.hibernate.ddl-auto=update para atualizar as tabelas automaticamente.

    Sempre valide os dados de entrada usando a validação (@Valid).

    Mensagens de erro e códigos HTTP foram configurados para indicar claramente falhas e sucesso.
