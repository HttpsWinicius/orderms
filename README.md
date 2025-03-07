
# Order Microservice

Order Microservice é um microsserviço responsável por consumir mensagens de pedidos enviadas para filas do RabbitMQ, processá-las e armazenar os dados no MongoDB. Além disso, calcula o valor total do pedido recebido antes de persistir no banco.


## Pré-requisitos
Antes de rodar a aplicação, certifique-se de ter instalado:\

- Java 17+

- Maven 3+

- Docker (para rodar o MongoDB e RabbitMQ localmente)


## Como Rodar a Aplicação

Clone o projeto

```bash
  git clone https://github.com/seu-repositorio/order-microservice.git
```

Entre no diretório do projeto

```bash
  cd order-microservice
```

Inicie os containers do MongoDB e RabbitMQ com Docker

```bash
  docker-compose up -d
```

Compile e rode a aplicação

```bash
  mvn spring-boot:run
```

A aplicação estará disponível em http://localhost:8081.

## Uso/Exemplos

Exemplo de Entrada (RabbitMQ):
```
{
   "codigoPedido": 1001,
   "codigoCliente":1,
   "itens": [
       {
           "produto": "lápis",
           "quantidade": 100,
           "preco": 1.10
       },
       {
           "produto": "caderno",
           "quantidade": 10,
           "preco": 1.00
       }
   ]
}
```
Exemplo de Registro no MongoDB:
```
{
   "_id": 1002,
   "customerId": 1,
   "total": 15034.28,
   "items": [
       {
           "product": "notebook",
           "quantity": 2,
           "price": 5000.00
       }
   ],
   "_class": "com.base.orderms.entity.OrderEntity"
}
```
## Licença

[MIT](https://choosealicense.com/licenses/mit/)

