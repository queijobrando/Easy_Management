# 🛒 EasyManagement

**EasyManagement** é uma aplicação web de **controle de estoque** voltada para **mercados e comércios de pequeno porte**. A aplicação permite o cadastro, movimentação e gestão de produtos, categorias, usuários, permissões e lotes, com autenticação personalizada, controle de acesso por grupo/permissão e painel administrativo.

---

## 🚀 Tecnologias Utilizadas

### ⚙️ Backend
- **Java 21**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
- **PostgreSQL** – banco de dados relacional
- **Flyway** – controle de versionamento do banco de dados
- **MapStruct** – mapeamento de DTOs
- **Lombok** – geração de boilerplate (getters, setters, construtores)
- **Jakarta Persistence (JPA)** – persistência de dados
- **BCrypt** – codificação de senhas
- **Thymeleaf** – mecanismo de templates
- **Maven** – gerenciamento de dependências

### 🎨 Frontend
- **HTML5 + Thymeleaf**
- **Bootstrap 5.3**
- **Bootstrap Icons**
- **JavaScript (AJAX)** – para interações dinâmicas em algumas telas

---

## 📁 Estrutura do Projeto

```text
src/
├── main/
│   ├── java/
│   │   └── com.estoque.gerenciador.management.easy.easymanagement/
│   │       ├── controller/           # Controllers REST e Web
│   │       ├── dto/                  # DTOs de entrada e saída
│   │       ├── mapper/               # Conversores com MapStruct
│   │       ├── model/                # Entidades JPA (Produto, Categoria, Lote, etc.)
│   │       ├── repository/           # Interfaces JPA Repositories
│   │       ├── security/             # Configurações de autenticação/autorização
│   │       ├── service/              # Lógicas de negócio
│   │       └── EasyManagementApplication.java
│   ├── resources/
│   │   ├── db/migration/             # Migrations Flyway (.sql)
│   │   ├── templates/                # Páginas HTML com Thymeleaf
│   │   ├── static/css/               # Estilos customizados
│   │   └── application.yml           # Configurações da aplicação
```

---

## 🔐 Autenticação e Autorização

- Usuários possuem **Grupos (Roles)** que contêm **Permissões**.
- Os acessos a páginas e endpoints são controlados por **anotações `@PreAuthorize`** e também via Thymeleaf com `sec:authorize`.
- Senhas são armazenadas com **criptografia BCrypt**.

---

## 🧰 Funcionalidades

- ✅ Cadastro e gerenciamento de produtos
- ✅ Controle de categorias
- ✅ Movimentações de estoque (entrada e saída)
- ✅ Controle de lotes por produto
- ✅ Visualização de estoque com total agregado por produto
- ✅ Cadastro de usuários e controle de permissões
- ✅ Tela de login com Spring Security
- ✅ Layout moderno e responsivo com Bootstrap

---

## ▶️ Como rodar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/easymanagement.git
   ```

2. Configure o PostgreSQL:
   - Crie o banco `easymanagement`
   - Atualize `application.yml` se necessário

3. Rode o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse no navegador:
   ```
   http://localhost:8080
   ```

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

Desenvolvido por **Tiago Azevedo de Souza**  
📧 tiagoadesouza21@gmail.com  
💼 [LinkedIn](https://www.linkedin.com/in/tiago-azevedo-souza)