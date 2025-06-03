# API REST em Java - Disaster Pulse

Backend desenvolvido em Spring Boot Java 21, arquitetura Maven.

# Sumário

- [Sobre a Disaster Pulse](#sobre-a-disaster-pulse)
- [Integrantes](#integrantes)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Dependências Utilizadas](#dependências-utilizadas)
- [Endpoints](#endpoints)
  - [Autenticação](#autenticação)
  - [Login](#apilogin)
  - [Cadastro de Entidade](#apicadastroentidade)
  - [Eventos](#apieventos)
  - [Alertas](#apialertas)
  - [Tipos de Evento](#apitipo-evento)
  - [Cadastro de Civil](#apicadastrocivil)
  - [Pedido de SOS](#apisos)
- [Enums Utilizados](#enums-utilizados)
- [Documentação da API - Swagger](#documentação-da-api---swagger)
- [Instruções de Execução e Deploy](#instruções-de-execução-e-deploy)
- [Modelo Relacional Oracle](#modelo-relacional---oracle)
- [Modelo Relacional MySQL](#modelo-relacional----mysql)
- [Demonstração Implantação dos contêineres](#demonstração-implantação-dos-contêineres---backend-e-banco-de-dados)

## Sobre o Disaster Pulse

O Disaster Pulse é uma plataforma de monitoramento e alerta para eventos catastróficos.
A proposta é conectar cidadãos e entidades governamentais ou sociais em uma rede ágil de resposta a desastres ambientais,
biológicos e climáticos. 

### Integrantes:
- André Geraldi Marcolongo - RM555285 - 2TDSPV
- Felipe Gabriel Lopes Pinheiro Clarindo - RM554547 - 2TDSPX
- Humberto de Souza Silva - RM558482 - 2TDSPX

## Estrutura do Projeto
``` java
src
├── main
│   ├── java
│   │   └── disaster
│   │       └── pulse
│   │           └── api
│   │               ├── config
│   │               │   ├── AuthFilter.java
│   │               │   ├── CorsConfig.java
│   │               │   ├── DataInitializer.java
│   │               │   ├── SecurityConfig.java
│   │               │   └── SwaggerConfig.java
│   │               ├── controller
│   │               │   ├── AlertaController.java
│   │               │   ├── AuthController.java
│   │               │   ├── CivilController.java
│   │               │   ├── EntidadeController.java
│   │               │   ├── EventoController.java
│   │               │   ├── PedidoSosController.java
│   │               │   └── TipoEventoController.java
│   │               ├── DisasterPulseBackendApplication.java
│   │               ├── dto
│   │               │   ├── DadosUsuario.java
│   │               │   ├── JwtDto.java
│   │               │   ├── LoginDto.java
│   │               │   ├── request
│   │               │   │   ├── AlertaRequestDTO.java
│   │               │   │   ├── CivilRequestDTO.java
│   │               │   │   ├── EntidadeRequestDTO.java
│   │               │   │   ├── EventoRequestDTO.java
│   │               │   │   └── PedidoSosRequestDTO.java
│   │               │   └── response
│   │               │       ├── AlertaResponseDTO.java
│   │               │       ├── CivilResponseDTO.java
│   │               │       ├── CivilResumoResponseDTO.java
│   │               │       ├── EntidadeResponseDTO.java
│   │               │       ├── EntidadeResumoResponseDTO.java
│   │               │       ├── EventoResponseDTO.java
│   │               │       ├── EventoResumoResponseDTO.java
│   │               │       ├── PedidoSosResponseDTO.java
│   │               │       ├── PedidoSosResumoResponseDTO.java
│   │               │       └── TipoEventoResponseDTO.java
│   │               ├── mapper
│   │               │   ├── AlertaMapper.java
│   │               │   ├── CivilMapper.java
│   │               │   ├── EntidadeMapper.java
│   │               │   ├── EventoMapper.java
│   │               │   ├── PedidoSosMapper.java
│   │               │   └── TipoEventoMapper.java
│   │               ├── model
│   │               │   ├── Alerta.java
│   │               │   ├── Civil.java
│   │               │   ├── Entidade.java
│   │               │   ├── enums
│   │               │   │   ├── Risco.java
│   │               │   │   └── Status.java
│   │               │   ├── Evento.java
│   │               │   ├── PedidoSOS.java
│   │               │   ├── TipoEvento.java
│   │               │   └── Usuario.java
│   │               ├── repository
│   │               │   ├── AlertaRepository.java
│   │               │   ├── CivilRepository.java
│   │               │   ├── EntidadeRepository.java
│   │               │   ├── EventoRepository.java
│   │               │   ├── PedidoSosRepository.java
│   │               │   ├── TipoEventoRepository.java
│   │               │   └── UsuarioRepository.java
│   │               └── service
│   │                   ├── AlertaService.java
│   │                   ├── AuthService.java
│   │                   ├── CivilService.java
│   │                   ├── EntidadeService.java
│   │                   ├── EventoService.java
│   │                   ├── JwtService.java
│   │                   └── PedidoSosService.java
│   └── resources
│       ├── application-mysql.properties  //  Perfil para persistência de dados em Banco MySQL
│       ├── application-oracle.properties // Perfil para persistência de dados em Banco Oracle
│       └── application.properties // Contém propriedades e configurações universais do projeto, tão como a escolha do perfil para persitência
└── test
    └── java
        └── disaster
            └── pulse
                └── api
                    └── DisasterPulseBackendApplicationTests.java

```

---

## Dependências utilizadas

```xml
<dependencies>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${org.mapstruct.version}</version>
    </dependency>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>4.4.0</version>
    </dependency>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-hateoas</artifactId>
    </dependency>
    <dependency>
            <groupId>org.springframework.hateoas</groupId>
            <artifactId>spring-hateoas</artifactId>
            <version>3.0.0-M3</version>
    </dependency>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
    </dependency>
    <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
    </dependency>
    <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <scope>runtime</scope>
    </dependency>
    <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
    </dependency>
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
    </dependency>
    <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
    </dependency>
    <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.5</version>
    </dependency>
</dependencies>
```

## Endpoints 

### Autenticação

Os endpoints:
- `/api/sos` - Métodos (`POST`, `PUT` e `DELETE`)
- `/api/eventos` - Métodos (`POST`, `PUT` e `DELETE`)

Exigem autenticação, respectivamente para usuário do tipo **CIVIL** e usuário do tipo **ENTIDADE**.

O identificador de Login respectivamente para ambos são: CPF (CIVIL) e CNPJ (ENTIDADE).

Use o endpoint `/api/login` para obter um token válido.

### `/api/login`

Endpoint de login. Retorna token JWT de validação. Necessário login antes de realizar operações POST (PedidoSOS para Civil e Evento para Entidade)

### Método suportado
- `POST /api/login` - Realiza login e gera token JWT de autenticação

#### Request (`POST`)
Corpo do request:
```json
{
  "login": "12345678901 ou 12345678000199 (cpf para civil, cnpj para entidade)",
  "senha": "senha"
}
```

#### Response (`POST`)
Corpo do response:
``` json
{
  "jwt": "string",
  "type": "bearer"
}
```

---

### `/api/cadastro/entidade`

#### Métodos Suportados
- `POST /api/cadastro/entidade` – Cadastra uma nova entidade
- `PUT /api/cadastro/entidade/{id}` – Atualiza os dados de uma entidade existente
- `GET /api/cadastro/entidade/{id}` – Retorna os dados de uma entidade específica

#### Request (`POST` e `PUT`)
```json
{
  "nomeFantasia": "Defesa Civil de São Paulo",
  "email": "defesacivilsp@sp.gov.br",
  "telefone": "11 99123-4567",
  "cnpj": "12345678000195",
  "senha": "senha123" // é serializada e cadastrada na tabela 'DP_USUARIO'
}
```
#### Response (`POST`, `PUT` e `GET/{id}`)
```json
{
  "id": 1,
  "nomeFantasia": "Defesa Civil de São Paulo",
  "email": "defesacivilsp@sp.gov.br",
  "telefone": "11 99123-4567",
  "cnpj": "12345678000195",
  "eventos": [
    {
      "idEvento": 1,
      "titulo": "Temporal e risco de enchentes no Butantã",
      "risco": "ALTO",
      "dataInicio": "2025-06-02T17:14:04.676Z"
    }
  ]
}
```

---

### `/api/eventos`
#### Métodos Suportados
- `POST /api/eventos` - Cadastra um novo evento. **Requer autenticação** de um usuário com perfil de **Entidade** devidamente cadastrado.
- `PUT /api/eventos/{id}` - Atualiza os dados de um evento existente. **Requer autenticação** de um usuário com perfil de **Entidade**.
- `GET /api/eventos/{id}` - Retorna os dados detalhados de um evento específico, identificado pelo seu `id`.
- `GET /api/eventos` - Retorna todos os eventos cadastrados. Suporta **paginação**, **filtros por entidade** e **ordenação**.
- `DELETE /api/eventos/{id}` - Remove um evento existente. **Requer autenticação** de um usuário com perfil de **Entidade**.

#### Request (`POST` e `PUT`)
```json
{
  "idTipoEvento": 1,
  "titulo": "Temporal e risco de enchentes no Butantã",
  "descricao": "Previsão de fortes chuvas acompanhadas de risco elevado de enchentes na região do Butantã. Moradores devem permanecer atentos a possíveis alagamentos, quedas de árvores e interrupções no trânsito.",
  "dataInicio": "2025-06-02T17:11:41.528Z",
  "latitude": -23.55052,
  "longitude": -46.633308,
  "risco": "ALTO"
}
```
#### Response (`POST`, `PUT`, `GET/{id}`)
```json
{
  "id": 1,
  "nomeFantasia": "Defesa Civil de São Paulo",
  "email": "defesacivilsp@sp.gov.br",
  "telefone": "11 99123-4567",
  "cnpj": "12345678000195",
  "eventos": [
    {
      "idEvento": 1,
      "titulo": "Tempestade com risco de enchente",
      "risco": "ALTO",
      "dataInicio": "2025-06-02T17:11:41.528Z"
    }
  ]
}
```
#### Response (`GET`) 

Retorna uma lista paginada de eventos cadastrados.

##### Parâmetros de Consulta (Query Params)

| Nome         | Tipo       | Local     | Descrição                                                                 |
|--------------|------------|-----------|---------------------------------------------------------------------------|
| `entidadeId` | `integer`  | query     | (opcional) ID da entidade para filtrar os eventos                         |
| `page`       | `integer`  | query     | Índice da página (base 0). **Padrão:** `0`                                |
| `size`       | `integer`  | query     | Número de elementos por página. **Padrão:** `20`                          |
| `sort`       | `string[]` | query     | Critério(s) de ordenação: `propriedade,(asc|desc)`. Ex: `dataInicio,desc`|


/api/eventos?entidadeId=1&page=0&size=10&sort=dataInicio,desc

##### Exemplo:

``` json
{
  "totalPages": 1,
  "totalElements": 1,
  "size": 10,
  "content": [
    {
      "idEvento": 1,
      "titulo": "Temporal e risco de enchentes no Butantã",
      "descricao": "Previsão de fortes chuvas acompanhadas de risco elevado de enchentes na região do Butantã. Moradores devem permanecer atentos a possíveis alagamentos, quedas de árvores e interrupções no trânsito.",
      "dataInicio": "2025-06-02T17:11:41.528Z",
      "latitude": -23.55052,
      "longitude": -46.633308,
      "risco": "ALTO",
      "tipoEvento": {
        "idTipoEvento": 1,
        "tipo": "Climático"
      },
      "entidade": {
        "id": 1,
        "nomeFantasia": "Defesa Civil de São Paulo"
      }
    }
  ],
  "number": 0,
  "sort": [
    {
      "direction": "DESC",
      "nullHandling": "NATIVE",
      "ascending": false,
      "property": "dataInicio",
      "ignoreCase": false
    }
  ],
  "numberOfElements": 1,
  "pageable": {
    "offset": 0,
    "sort": [
      {
        "direction": "DESC",
        "nullHandling": "NATIVE",
        "ascending": false,
        "property": "dataInicio",
        "ignoreCase": false
      }
    ],
    "unpaged": false,
    "paged": true,
    "pageNumber": 0,
    "pageSize": 10
  },
  "first": true,
  "last": true,
  "empty": false
}
```

---

### `/api/alertas`
#### Métodos Suportados

- `POST /api/alertas` - Cadastra um alerta relacionado a um evento.
- `PUT /api/alertas/{id}` - Atualiza os dados de um alerta existenete. 
- `GET /api/alertas/{id}` - Retorna os dados detalhados de um alerta específico, identificado pelo seu `id`.
- `GET /api/alertas` - Retorna todos os alertas cadastrados em um evento.
- `DELETE /api/alertas/{id}` - Remove um alerta existente.

#### Request (`POST` e `PUT`)
```json
{
  "idEvento": 1,
  "mensagem": "Risco de alagamento na região do Butantã. Evacuar pontos de risco.",
  "dataHora": "2025-06-02T17:37:14.176Z"
}
```
#### Response (`POST`, `PUT`, `GET/{id}`)
```json
[
  {
    "idAlerta": 1,
    "evento": {
      "idEvento": 1,
      "titulo": "Fortes chuvas previstas para a região do Butantã, com risco de alagamentos.",
      "risco": "ALTO",
      "dataInicio": "2025-06-02T17:18:21.222Z"
    },
    "mensagem": "Risco de alagamento na região do Butantã. Evacuar pontos de risco.",
    "dataHora": "2025-06-02T17:37:14.176Z"
  }
]
```

---

### `/api/tipo-evento`
#### Método Suportado

- `GET /api/tipo-evento` - Retorna todos os tipos de eventos cadastrados. O arquivo `../api/config/DataInitializer.java` já cadastra os tipos de evento na inicialização do projeto

#### Response (`GET`)
```json
// Exemplo - retorna os 5 tipos cadastrados na inicialização do projeto
[
  {
    "idTipoEvento": 1,
    "tipo": "Climático"
  },
  {
    "idTipoEvento": 2,
    "tipo": "Geográfico"
  },
  {
    "idTipoEvento": 3,
    "tipo": "Biológico"
  },
  {
    "idTipoEvento": 4,
    "tipo": "Ambiental"
  },
  {
    "idTipoEvento": 5,
    "tipo": "Epidêmico"
  }
]
```

### `/api/cadastro/civil`
#### Métodos Suportados

- `POST /api/cadastro/civil` – Cadastra um novo usuário do tipo **Civil**
- `PUT /api/cadastro/civil/{id}` – Atualiza os dados de usuário do tipo **Civil**
- `GET /api/cadastro/civil/{id}` – Retorna os dados de um usuário do tipo **Civil**

#### Request (`POST` e `PUT`)
```json
{
  "nomeCompleto": "José da Silva",
  "email": "jose.silva@example.com",
  "cpf": "12345678901",
  "telefone": "11 91234-1234",
  "senha": "senha123"
}
```
#### Response (`POST`, `PUT`, `GET/{id}`)
```json
{
  "id": 2,
  "nomeCompleto": "José da Silva",
  "email": "jose.silva@example.com",
  "cpf": "12345678901",
  "telefone": "11 91234-1234",
  "sos": [
    {
      "idSos": 1,
      "comentario": "Ilhado na região do Butantã"
    }
  ]
}
```
---

### `/api/sos`
#### Métodos Suportados
- `POST /api/sos` - Cadastra um novo pedido de SOS vinculado a um evento existente. **Requer autenticação** de um usuário com perfil de **Civil** devidamente cadastrado.
- `PUT /api/sos/{id}` - Atualiza os dados de um pedido de SOS existente. **Requer autenticação** de um usuário com perfil de **Civil**.
- `GET /api/sos/{id}` - Retorna os dados detalhados de um pedido de SOS específico, identificado pelo seu `id`.
- `GET /api/sos` - Retorna todos os pedidos de SOS cadastrados.
- `DELETE /api/sos/{id}` - Remove um pedido de SOS existente. **Requer autenticação** de um usuário com perfil de **Civil**.

#### Request (`POST` e `PUT`)
```json
{
  "idEvento": 1, // Evento relacionado ao SOS
  "comentario": "Ilhado na região do Butantã.",
  "status": "ABERTO"
}
```
#### Response (`POST`, `PUT`, `GET/{id}` e `GET`)
```json
{
  "idSos": 1,
  "civil": {
    "id": 2,
    "nomeCompleto": "José da Silva"
  },
  "dataHora": "2025-06-02T17:37:14.176Z",
  "comentario": "Ilhado na região do Butantã.",
  "status": "ABERTO"
}
```
---
## Enums utilizados

A API utiliza enums para padronizar estados e classificações importantes.

- **Risco** (usado em EVENTO)  
  Valores possíveis: `BAIXO`, `MEDIO`, `ALTO`, `CRITICO`

- **Status** (usado em PEDIDO_SOS)  
  Valores possíveis: `ABERTO`, `RESOLVIDO`, `PENDENTE`, `CONCLUIDO`, `CANCELADO`

---

## Documentação da API - Swagger 

**/swagger-ui/index.html**

O projeto está implantado no Render.

A documentação da API pode ser acessada através do link abaixo:

**Documentação:** 
<a href="https://disaster-pulse-api-rest.onrender.com/swagger-ui/index.html" target="_blank">Swagger UI - Disaster Pulse API</a>


**O projeto implantado no Render faz persistência no Oracle interno da faculdade.**

1. Os endpoints estão organizados em categorias distintas para facilitar o uso: Civil e Entidade
![choose-definition-1png](https://github.com/user-attachments/assets/b381f127-edc9-4e64-84d3-651508245183)

2. Autenticação do usuário (Civil ou Entidade) com o JWT gerado:
![authentication-jwt](https://github.com/user-attachments/assets/a953c744-22f7-4135-a525-b0153994ecc3)

Basta colar o token gerado após o envio das credenciais pelo endpoint `/api/login`:

![auth-jwt](https://github.com/user-attachments/assets/0a171a44-653f-4d03-806a-f969ddc43afd)

---

## Instruções de execução e deploy:

``` bash
git clone https://github.com/andremarko/disaster-pulse-api-rest
cd disaster-pulse-api-rest
```

Caso queira via **script** `docker-script.sh`

``` bash
user disaster-pulse-api-rest$ ./docker-script.sh
# [...]
# Siga as instruções do script.
```

**Persistência Externa**

``` bash
docker build -t disaster-pulse-backend-image .

docker run -d --name disaster-pulse-backend -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=oracle \ 
    -e DB_USER=seu_usuario \
    -e DB_PASS=sua_senha\
    disaster-pulse-backend-image\
```

**Persistência interna (MySQL)**

```bash
docker network create disaster-pulse-network

docker volume create disaster-pulse-db-data

docker run --name disaster-pulse-mysql --network disaster-pulse-network \
    -e MYSQL_ROOT_PASSWORD=suasenharoot \
    -e MYSQL_DATABASE=disaster_pulse \
    -e MYSQL_USER=disasterpersist \
    -e MYSQL_PASSWORD=senhadoschema \
    -v disaster-pulse-db-data:/var/lib/mysql \
    -p 3306:3306 \
    -d mysql:8.0

docker build -t disaster-pulse-backend-image .

docker run -d --name disaster-pulse-backend --network disaster-pulse-network -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=mysql \
    -e DB_USER=disasterpersist \
    -e DB_PASS=senhadoschema \
    disaster-pulse-backend-image
```

## Modelo Relacional - Oracle

![dp_relational_1](https://github.com/user-attachments/assets/4bd0f2b4-a7fc-48ed-b6af-1fb0730fc6a1)

## Modelo Relacional - MySQL

![db_model_mysql](https://github.com/user-attachments/assets/f4f6056a-5e6a-4549-863b-bd3658d3382f)

## Demonstração Implantação dos contêineres - backend e banco dados

[Implantação Modular com Docker - Backend Java Spring Boot e MySQL em Contêineres](https://www.youtube.com/watch?v=xY0wcKdfGZI)




