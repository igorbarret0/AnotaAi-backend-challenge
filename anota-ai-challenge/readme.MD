<h1 align="center">
  RESTAURANTE MARKETPLACE
</h1>

Este projeto é uma API para um sistema de gestão de catálogos de produtos em um marketplace

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [AWS](https://aws.amazon.com/pt/)


## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/AnotaAi-backend-challenge.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

- API PRODUCT
```
POST /api/product - Criar um novo produto
GET /api/product - Recuperar todos os produtos
PUT /api/product/{id} - Atualizar um produto
DELETE /api/product/{id} - Deletar um produto
```

- BODY
```
{
  "title": "Some title",
  "description": "Some description",
  "ownerId": "1335",
  "categoryId": "659d558b0304df732ddd4587",
  "price": 10000
}
```

- API CATEGORY
```
POST /api/category - Create a new category
GET /api/category - Retrieve all categories
PUT /api/category/{id} - Updates a category
DELETE /api/category/{id} - Delete a category
```

- BODY
```
{
  "title": "Some title",
  "description": "Some description",
  "ownerId": "1335"
}
```


