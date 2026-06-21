# 🐾 Sistema PetShop

Sistema web de gerenciamento de PetShop desenvolvido como projeto da disciplina de **Análise e Projeto de Software** da UFPB.

\---

## 📋 Sobre o Projeto

O Sistema PetShop permite o cadastro e gerenciamento de animais atendidos pelo estabelecimento, registrando informações como nome, espécie, raça, idade e dados do responsável.

\---

## 🚀 Tecnologias Utilizadas

|Tecnologia|Uso|
|-|-|
|**Java 21**|Linguagem principal|
|**Javalin**|Framework web (backend)|
|**Thymeleaf**|Templates HTML (frontend)|
|**Bootstrap 5**|Estilização da interface|
|**PostgreSQL**|Banco de dados|
|**Docker**|Containerização|
|**GitHub Actions**|Pipeline de CI/CD|

\---

## ⚙️ Funcionalidades

* ✅ Cadastro de pets
* ✅ Listagem de pets cadastrados
* ✅ Edição de dados do pet
* ✅ Remoção de pets
* ✅ Validações de formulário
* ✅ Logs da aplicação
* ✅ Deploy automatizado

\---

## 🗂️ Estrutura do Projeto

```
src/
└── main/
    ├── java/com/petshop/
    │   ├── App.java                  ← ponto de entrada
    │   ├── config/
    │   │   ├── DatabaseConfig.java   ← conexão PostgreSQL
    │   │   ├── ThymeleafConfig.java  ← templates HTML
    │   │   └── RouteConfig.java      ← rotas da aplicação
    │   ├── controller/
    │   │   ├── HomeController.java
    │   │   └── PetController.java
    │   ├── model/
    │   │   └── Pet.java
    │   ├── repository/
    │   │   └── PetRepository.java
    │   └── service/
    │       └── PetService.java
    └── resources/
        ├── templates/
        │   ├── index.html
        │   └── pet/
        │       ├── lista.html
        │       └── formulario.html
        └── logback.xml
```

\---

## 🐳 Como Rodar Localmente

### Com Docker

```bash
docker compose up --build
```

Acesse: http://localhost:8080

### Sem Docker

Configure as variáveis de ambiente:

```bash
export DB\\\_URL=jdbc:postgresql://localhost:5432/petshopdb
export DB\\\_USER=postgres
export DB\\\_PASSWORD=postgres
export PORT=8080
```

Execute:

```bash
mvn package -DskipTests
java -jar target/app.jar
```

\---

## 🔧 Variáveis de Ambiente

|Variável|Descrição|
|-|-|
|`DB\\\_URL`|URL de conexão com o banco|
|`DB\\\_USER`|Usuário do banco|
|`DB\\\_PASSWORD`|Senha do banco|
|`PORT`|Porta do servidor (padrão: 8080)|

\---

## 🔄 Pipeline CI/CD

O deploy é feito automaticamente via **GitHub Actions** a cada push na branch `main`:

1. Build do fat JAR com Maven
2. Geração da imagem Docker
3. Publicação no GitHub Container Registry (GHCR)
4. Deploy no servidor via SSH

Acompanhe em: [Actions](../../actions)

\---

## 🌐 Aplicação em Produção

A aplicação está disponível em:

```
https://eq11.aps.rodrigor.com
```

\---

## 👥 Equipe

**Equipe eq11** — Disciplina de APS · UFPB ·

\---

## 📝 Organização do Desenvolvimento

* Branches por funcionalidade
* Pull Requests com revisão antes do merge
* Deploy automático na branch `main`



