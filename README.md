
# ms-email

Este serviço faz parte de um sistema baseado em microsserviços. O `ms-email` é responsável por consumir mensagens da fila RabbitMQ e enviar e-mails de boas-vindas para usuários recém-cadastrados.

## Funcionalidades

- Consumo de mensagens RabbitMQ publicadas pelo `ms-user`.
- Envio de e-mails utilizando o Spring Mail.
- Integração com Gmail via SMTP.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Web, JPA e Validation
- RabbitMQ (via CloudAMQP)
- MySQL
- Spring Mail (SMTP via Gmail)
- Lombok

## Pré-requisitos

- Java 17 instalado
- Maven instalado
- MySQL em execução
- Conta no CloudAMQP (RabbitMQ na nuvem)
- Conta Gmail habilitada para envio SMTP

> Atenção: é necessário habilitar a autenticação SMTP e uso de senhas de app no Gmail.

## Como rodar

Por padrão, o `ms-email` utiliza a porta `8080`. Certifique-se de que o `ms-user` está rodando em outra porta (ex: `8081`).

1. Clone o repositório:

```bash
git clone https://github.com/bbering/ms-email.git
cd ms-email
```

2. Crie um arquivo `env.properties` na raiz do projeto com as seguintes variáveis configuradas:

```properties
DB_NAME=nome_do_banco
DB_USERNAME=usuario
DB_PASSWORD=senha

RABBIT_URL=amqps://usuario:senha@host/vhost

HOST_USER=seu_email@gmail.com
HOST_PASS=sua_senha_de_aplicativo
```

> O arquivo `application.properties` já está configurado para importar automaticamente esse arquivo.

3. Execute o projeto com Maven:

```bash
mvn spring-boot:run
```

## Observações

- A aplicação depende do serviço `ms-user`, que envia os eventos via RabbitMQ para que o `ms-email` os consuma.
- Ambos os serviços devem estar conectados à mesma instância do RabbitMQ no CloudAMQP.

## Testes

Para executar os testes automatizados:

```bash
mvn test
```

## Licença

Este projeto é livre para uso e modificação.
