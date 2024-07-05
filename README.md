# API REST de consultas médicas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Spring-security](https://img.shields.io/badge/Spring_Security-6DB50F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/JSON%20Web%20Tokens-46317c.svg?style=for-the-badge&logo=JSON-Web-Tokens&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![MYSQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200.svg?style=for-the-badge&logo=Flyway&logoColor=white)


## Sobre o projeto
MedConsultAPI é uma API RESTful desenvolvida para gerenciamento de consultas médicas, visando facilitar a obtenção de
dados de médicos, pacientes e consultas.

## Funcionalidades

- **Gerenciamento de Médicos:** Cadastro, atualização e remoção de informações de médicos.
- **Gerenciamento de Pacientes:** Cadastro, atualização e remoção de informações de pacientes.
- **Gerenciamento de Consultas:** Agendamento, atualização e cancelamento de consultas médicas.
- **Segurança:** Configuração de autenticação e autorização utilizando Spring Security e o padrão JWT.
## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/J0aoPaulo/medConsultAPI.git
   ```
2. Navegue até o diretório do projeto:

   ```bash
   cd medConsultAPI
   ```
3. Configure o banco de dados no arquivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/medconsult
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
4. Execute o projeto utilizando a IDE de sua escolha.

## Autenticação

- **Login:**
  - `POST /login` - Autentica o usuário e retorna um token de autenticação.
  - Body da requisição:
      ```json
      {
        "email": "usuario@example.com",
        "senha": "sua_senha"
      }
      ```
Para acessar os endpoints (exceto `/login`), é necessário incluir o token JWT no cabeçalho das requisições.

- **Cabeçalho da requisição:**
  ```
  Authorization: Bearer <seu_token_jwt>
  ```
  
## Endpoints
- **Médicos:**
  - `GET /medicos` - Listar todos os médicos.
  - `POST /medicos` - Adicionar um novo médico.
  - `PUT /medicos/{id}` - Atualizar informações de um médico existente.
  - `DELETE /medicos/{id}` - Remover um médico.

- **Pacientes:**
  - `GET /pacientes` - Listar todos os pacientes.
  - `POST /pacientes` - Adicionar um novo paciente.
  - `PUT /pacientes/{id}` - Atualizar informações de um paciente existente.
  - `DELETE /pacientes/{id}` - Remover um paciente.

<!-- - **Consultas:**
  - `GET /consultas` - Listar todas as consultas.
  - `POST /consultas` - Agendar uma nova consulta.
  - `PUT /consultas/{id}` - Atualizar informações de uma consulta existente.
    - `DELETE /consultas/{id}` - Cancelar uma consulta. -->       

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
