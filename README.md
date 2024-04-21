# User-Registration-SQS
## Descrição do Projeto
O projeto User-Registration-SQS consiste em dois microservices: ms-user para registro e gerenciamento de usuários, e ms-email para envio de e-mails aos usuários cadastrados. 
Ambos os microservices utilizam o sistema de filas da AWS, SQS, para comunicação assíncrona. 
A arquitetura do projeto coloca em prática diversos conceitos e tecnologias, proporcionando uma experiência abrangente de desenvolvimento.

## Tecnologias Usadas
- Java
- Spring Boot
- Swagger API
- LocalStack
- AWS SQS
- PostgreSQL
- JPA
- Flyway
- Java Mail
- JUnit
- Mockito
- MockMVC
- Banco de dados H2 (para testes)

## Testes
O projeto possui uma suite de testes abrangente, incluindo testes unitários e de integração.
JUnit, Mockito são utilizados para os testes unitários.
MockMVC e Banco de dados H2 é utilizado para os testes de integração.

## Funcionalidades dos Microservices
### ms-user:
- Registro de usuário
- Busca de todos os usuários
- Login de usuário

### ms-email:
- Envio de e-mail

## Documentação da API
A documentação detalhada da API pode ser acessada em: http://localhost:8080/api/v1/swagger-ui/index.html#

## Usuário ADMIN da aplicação
- email: admin@gmail.com
- password: admin1234

## Pré-requisitos
- LocalStack instalado e configurado em sua máquina.
- Docker instalado.
- AWS CLI
- Maven instalado.

1. Clone o projeto
```bash
git clone git@github.com:AndersonVianaDev/user-registration-sqs.git
``` 

2. Iniciar o LocalStack
```bash
localstack start -d
```
3. Criar uma fila SQS no LocalStack
#### Opção 1:
- Usando o site do LocalStack: https://www.localstack.cloud/
- Navegue para o serviço SQS e crie uma nova fila com o nome "test-email".
#### Opção 2: 
```bash
aws --endpoint http://localhost:4566 create-queue --queue-name test-email
```

4. Subir os bancos de dados
Navegue até a raiz do projeto e execute o seguinte comando:
```bash
docker compose up -d
```

5. Executes os 2 microsserviços

# Contribuindo
Sinta-se à vontade para contribuir com novas funcionalidades, correções de bugs ou melhorias no projeto. Basta abrir uma issue ou enviar um pull request.

# Autor do projeto
Anderson Palmerim Viana
# Contato 
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anderson-palmerim-6a5a17262/)

