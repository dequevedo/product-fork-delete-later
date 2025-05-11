
# Tech Challenge - Product API

Microserviço responsável pela gestão de produtos da lanchonete digital.
Este repositório faz parte da solução maior descrita no repositório principal de documentação.

---

## Repositórios do Projeto

##### fiap-soat-tech-challenge-fase-3-infra-terraform:
https://github.com/RenatoMartinsXrd/fiap-soat-tech-challenge-fase-3-infra-terraform

##### fiap-soat-tech-challenge-fase-3-db-terraform:
https://github.com/RenatoMartinsXrd/fiap-soat-tech-challenge-fase-3-db-terraform

##### fiap-soat-tech-challenge-fase-3-lambda:
https://github.com/RenatoMartinsXrd/fiap-soat-tech-challenge-fase-3-identify-customer-lambda

##### fiap-soat-tech-challenge-fase-3-app:
https://github.com/dequevedo/fiap-soat-tech-challenge-fase-3-app

## 📦 Tecnologias Utilizadas

- Java 21 + Spring Boot
- PostgreSQL + Flyway
- Docker + Docker Compose
- Swagger (OpenAPI)
- Clean Architecture

---

## ▶️ Excutando a API Localmente

**Pré-requisitos:**
- Docker + Docker Compose
- (Opcional) Java 21 (caso queira rodar pela IDE)

**Passos:**

```bash
docker compose up --build
```

- API disponível em: http://localhost:8080

- PGAdmin: http://localhost:5050

---

### Acessando a documentação OpenAPI/Swagger

1. Abra o URL no seu navegador:

```sh
http://localhost:30080/swagger-ui/index.html
```

---

## 🔌 Endpoints Disponíveis

| Método | Rota           | Descrição                   |
|--------|----------------|-----------------------------|
| GET    | /products      | Lista todos os produtos     |
| GET    | /products/{id} | Retorna um produto específico |
| POST   | /products      | Cadastra um novo produto    |
| PUT    | /products/{id} | Atualiza um produto         |
| DELETE | /products/{id} | Remove um produto           |

> A documentação completa pode ser acessada via Swagger.

---

## 🧠 Arquitetura

Este microserviço adota o padrão **Clean Architecture**, com foco em separação de responsabilidades e independência tecnológica.

**Principais camadas:**

- **core**: Entidades, regras de negócio, casos de uso e contratos (ports)
- **adapters**: Controladores e mapeadores que traduzem o mundo externo para o domínio
- **frameworks**: Implementações específicas (REST, JPA, integrações externas)
- **shared**: Configurações globais e utilitários comuns

---

## 🧪 Testes

> Implementação em andamento.

- Os testes serão escritos com JUnit e Mockito.
- A cobertura mínima exigida será de **80%**, conforme o desafio.
- Ao menos um dos fluxos principais será testado com BDD (usando Cucumber ou JBehave, por exemplo).

---
