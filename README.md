# Cooperative Votes

## Sobre o projeto ##

API REST para votação de pautas no cooperativismo.

## Pré-requisitos ##

* Java 11
* Gradle
* JUnit
* Docker ou MongoDB

## Executar a aplicação

O Docker deve estar rodando na maquina para subir o MongoDB, exemplos de comandos:

- docker pull tutum/mongodb (Baixa a imagem do mongodb)
- docker ps -a (Lista os containers)
- docker start {CONTAINER ID} (O CONTAINER ID está disponível no comando acima)

Então, execute os comandos abaixo na pasta do projeto para executar a aplicação:

- gradle build
- gradle bootRun

## Executar somente os testes

- gradle test

## Acessar a API

http://localhost:8080/v1/