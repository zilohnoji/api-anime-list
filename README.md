# API de Gerenciamento de Animes - DEBVER

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/zilohnoji/api-anime-list/blob/main/LICENSE)

Essa API foi desenvolvida como parte do ecossistema da plataforma DEBVER, que tem como foco fornecer uma solução completa para entusiastas de animes gerenciarem suas preferências e interações com conteúdo de anime. Abaixo, você encontrará uma visão geral das principais características desta API.

## Índice
1. [Visão Geral](#visão-geral)
2. [Configuração](#configuração)
3. [Endpoints](#endpoints)

   3.1 [Autenticação](#autenticação)

   3.2 [Usuário](#usuário)
   
   3.3 [Anime](#anime)
   
4. [Exemplos de Uso](#exemplos-de-uso)
5. [Tecnologias Utilizadas](#tecnologias-utilizadas)
6. [Considerações Finais](#considerações-finais)
7. [Autor](#autor)

## Visão Geral
A API de Gerenciamento de Animes foi criada para atender às necessidades da plataforma DEBVER. Ela permite que os usuários gerenciem suas listas de animes assistidos, em andamento e desejados. A API oferece recursos de autenticação e autorização, permitindo que os usuários se registrem, façam login e acessem apenas os recursos autorizados.

Este documento oferece uma visão geral das principais funcionalidades da API, bem como instruções para configurar e utilizar o projeto.
## Através desta API, os usuários podem:

* Registrar uma nova conta de usuário, fornecendo informações básicas e uma senha segura.
* Autenticar-se através do login, obtendo um token JWT que será usado para acessar os endpoints protegidos.
* Visualizar informações do próprio perfil, incluindo detalhes pessoais e preferências.
* Explorar animes por nome, obtendo informações sobre eles.
* Adicionar novos animes à lista, incluindo título, descrição, imagem e status.
* Visualizar uma lista paginada de todos os animes registrados.
* Adicionar animes ao seu carrinho, especificando o status do anime (assistido, em andamento etc.).
* A API é construída usando tecnologias modernas, como o framework Spring Boot, que proporciona uma arquitetura robusta e segura para o desenvolvimento de APIs. Além disso, utiliza conceitos de autenticação JWT para garantir a segurança das operações realizadas pelos usuários.

## Configuração
### Para configurar e executar a API em seu ambiente local, siga os passos abaixo:

```bash
# Clone o repositório
git clone https://github.com/zilohnoji/api-anime-list
```
![image](https://github.com/zilohnoji/api-anime-list/assets/58833700/1134aa70-2085-4f25-9b84-9c4bffa58f8b)
```bash
# Entre na pasta do projeto
cd api-anime-list
```
![image](https://github.com/zilohnoji/api-anime-list/assets/58833700/da084d75-6431-46fd-8ffb-469f511e2b72)
```bash
# Faça a compactação para .jar
mvn clean package
```
![image](https://github.com/zilohnoji/api-anime-list/assets/58833700/f07a8bd8-8b9d-4f62-bf92-1d9862f195bc)

```bash
# Entre na pasta target
cd target
```
![image](https://github.com/zilohnoji/api-anime-list/assets/58833700/7220beac-9a06-4b84-a7c1-4b21aca1bd92)

```bash
# Execute o arquivo compactado
java -jar anime-list-api-0.0.1-SNAPSHOT.jar
```


## Endpoints

### Autenticação
* **POST** /anime-api/v1/auth/register: Criação de uma nova conta de usuário.
* **POST** /anime-api/v1/auth/login: Autenticação do usuário e geração de token JWT.

### Usuário
* **GET** /anime-api/v1/users?name={name}: Obtenção das informações de um usuário pelo **nome**.
* **GET** /anime-api/v1/users/me: Visualização do próprio perfil.
* **GET** /anime-api/v1/users/my-cart: Visualização do próprio carrinho.

### Anime
* **POST** /anime-api/v1/anime: Criação de um novo anime.
* **GET** /anime-api/v1/anime?name={name}: Obtenção das informações de um anime pelo **nome**.
* **GET** /anime-api/v1/anime/all: Obtenção **paginada** dos animes registrados.
* **GET** /anime-api/v1/anime/{id}: Obtenção das informações de um anime pelo **id**.

### Order
* **POST** /anime-api/v1/orders: Adiciona um anime no carrinho do usuário.

## Exemplos de uso
1. Registrar um novo usuário
```txt
POST /anime-api/v1/auth/register
Content-Type: application/json
```
```json
{
    "name": "Vitor",
    "email": "vitor@gmail.com",
    "password": "1234567890",
    "profile": {
        "img_url": "http://icon.com.br/lucasImg",
        "bio": "Sou o Vitor"
    },
    "role": "role_admin"
}
```
Obs: Caso a chave "role" não seja incluída no registro, ele será registrado como um role_client.

2. Autenticar usuário.
```txt
POST /anime-api/v1/auth/login
Content-Type: application/json
```
```json
{
    "email": "vitor@gmail.com",
    "password": "1234567890"
}
```
3. Obter usuário pelo nome.
```txt
GET /anime-api/v1/users?name=vitor
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```
4. Visualizar o próprio perfil.
```txt
GET /anime-api/v1/users/me
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```
5. Visualizar o próprio carrinho.
```txt
GET /anime-api/v1/users/my-cart
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```
6. Criação de um novo anime.
```txt
POST /anime-api/v1/anime
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```
```json
{
    "title": "100 Things to Do Before You Become a Zombie",
    "description": "Akira via sua vida sendo completamente sugada por uma empresa tóxica. No enquanto, quando de repente sua cidade é assolada por um apocalipse zumbi, ele acha um novo sentido na vida.",
    "img_url": "https://www.crunchyroll.com/imgsrv/display/thumbnail/1200x675/catalog/crunchyroll/2ae27299771603c5b9bf3407ae2b30f9.jpe",
    "author_name": "Haro Aso e Kotaro Takata",
    "status": "AIRING",
    "episodes": 12
}
```
7. Buscar anime pelo nome.
```txt
GET /anime-api/v1/anime?name=100
Content-Type: application/json
```
8. Buscar todos os animes **paginados**.
```txt
GET /anime-api/v1/anime/all
Content-Type: application/json
```
9. Buscar anime pelo id.
```txt
GET /anime-api/v1/anime/{id}
Content-Type: application/json
```
10. Adicionar anime no carrinho do usuário.
```txt
POST /anime-api/v1/orders
Authorization: Bearer seu_token_jwt
Content-Type: application/json
```
```json
{
    "anime_id": -4,
    "status": "COMPLETED",
    "episode": -2
}
```
## Tecnologias Utilizadas
Nossa API utiliza uma combinação de tecnologias para oferecer um ambiente de desenvolvimento eficiente e seguro.

- **Spring Boot:** Framework que agiliza o desenvolvimento de aplicações Spring, proporcionando configurações padrão e um ambiente de execução embarcado.
- **Spring Web:** Módulo do Spring que facilita a criação de APIs RESTful.
- **Spring Security:** Oferece recursos de segurança robustos para proteger a API contra ameaças.
- **H2:** Banco de dados em memória para desenvolvimento e testes.
- **Postgresql:** Banco de dados relacional para armazenamento de dados em produção.
- **Spring Validation:** Facilita a validação de entrada de dados.
- **OpenAPI - Swagger:** Documentação interativa para a API.
- **JPA / Hibernate:** Mapeamento objeto-relacional para acesso ao banco de dados.
- **Maven:** Ferramenta de gerenciamento de dependências e construção de projetos.

## Considerações Finais
A API de Gerenciamento de Animes é uma ferramenta essencial para a funcionalidade central da aplicação Debver. 
Ela permite aos usuários organizar suas preferências e interações com os animes, melhorando sua experiência de usuário. 
Esta documentação oferece uma visão geral das funcionalidades e endpoints principais, 
mas fique à vontade para explorar o código-fonte para mais detalhes e personalizações.
Caso surjam dúvidas ou problemas, sinta-se à vontade para entrar em contato com a equipe de desenvolvimento.
Divirta-se utilizando a API de Gerenciamento de Animes!

## Autor
Conheça mais sobre o autor do projeto:
[https://www.linkedin.com/in/pedro-donato-a9aa42246/](https://www.linkedin.com/in/pedro-donato-a9aa42246/)
