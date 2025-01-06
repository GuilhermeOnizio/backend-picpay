# :star2: PicPay Clone - Sistema de Gestão de Pagamentos

Bem-vindo ao projeto PicPay Clone, uma aplicação back-end que simula funcionalidades essenciais de um sistema de pagamentos. Este projeto foi feito apenas para fins de estudo e treino para melhorar minhas habilidades. Este sistema é desenvolvido com Java e utiliza o framework Spring Boot para fornecer endpoints REST que gerenciam usuários, transações, histórico de pagamentos e saldos.

## Funcionalidades

- Cadastro de Usuários: Criação de novos usuários com validação de dados.

- Gestão de Saldos:
  - Adição de saldo à conta do usuário.
  - Consulta do saldo disponível.

- Transferências:
  - Realização de transferências entre usuários com validações como saldo insuficiente e IDs inválidos.

- Histórico de Transações:
  - Recuperação de todas as transações associadas a um usuário específico.

- Tratamento de Erros:
  - Respostas padronizadas para erros de validação, requisições inválidas e falhas internas.

## Tecnologias Utilizadas

- Java 23
- Spring Boot 3
- Maven
- MySQL
- JPA/Hibernate
- Lombok
- Insomnia (para testes de API)


## Passos para rodar o projeto

1. Clone este repositório:

   ```bash
    git clone https://github.com/seuusuario/picpay-clone.git
   ```

2. Entre na pasta do projeto:

   ```bash
    cd picpay-clone
   ```

3. Compile e construa o projeto com Maven:

   ```bash
    mvn clean install
   ```

4. Execute o projeto:

   ```bash
    mvn spring-boot:run
   ```

5. Acesse o Swagger UI para testar os endpoints (se configurado) em:

   ```bash
    [mvn spring-boot:run](http://localhost:8080/swagger-ui.html)
   ```

## Endpoints Principais

### /usuarios
  - POST /usuarios: Cadastro de novo usuário.

### /saldo
  - POST /saldo/adicionar/{usuarioId}: Adicionar saldo.
  - GET /saldo/consultar/{usuarioId}: Consultar saldo.

### /transferencia
- POST /transferencia/realizar: Realizar uma transferência.

### /historico
- GET /historico/{idUsuario}: Consultar histórico de transações.
