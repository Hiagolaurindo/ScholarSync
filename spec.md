Pode colar isso direto no Antigravity como prompt/spec do projeto:

````md
# SPEC DO PROJETO — AcadEvents

Crie um projeto fullstack simples para o trabalho final da disciplina de Teste de Software.

O sistema se chama **AcadEvents** e serve para gerenciamento de eventos acadêmicos. Deve ter backend em Java Spring Boot, frontend principal em React e um painel administrativo simples em Angular. O objetivo é ter uma base funcional rápida, com login, CRUD, API, banco de dados e testes básicos.

## Objetivo do sistema

O AcadEvents permite que usuários participantes façam login, visualizem eventos acadêmicos disponíveis e se inscrevam em eventos. Administradores conseguem cadastrar, listar, editar e excluir eventos.

O sistema deve ser simples, funcional e fácil de testar.

## Tecnologias obrigatórias

Backend:
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security simples ou autenticação mockada via endpoint
- Banco H2 em memória
- Maven
- JUnit 5
- Mockito
- MockMvc

Frontend participante:
- React
- Vite
- Axios
- React Router
- Vitest ou Testing Library

Painel admin:
- Angular
- Angular Router
- HttpClient
- Teste básico de componente/service

E2E:
- Playwright

Documentação:
- Markdown dentro da pasta `/docs`

## Estrutura do repositório

Crie a seguinte estrutura:

```txt
acad-events/
├── backend-java/
├── frontend-react/
├── admin-angular/
├── e2e/
├── docs/
└── README.md
````

## Backend Java — Spring Boot

Criar API REST em `/backend-java`.

### Entidades

#### User

Campos:

```txt
id: Long
name: String
email: String
password: String
role: String
```

Roles possíveis:

```txt
ADMIN
PARTICIPANT
```

#### Event

Campos:

```txt
id: Long
title: String
description: String
date: LocalDate
location: String
capacity: Integer
```

#### Registration

Campos:

```txt
id: Long
user: User
event: Event
registrationDate: LocalDate
```

### Dados iniciais

Ao iniciar a aplicação, carregar dados iniciais no H2:

Usuário admin:

```txt
email: admin@acad.com
senha: 123456
role: ADMIN
```

Usuário participante:

```txt
email: user@acad.com
senha: 123456
role: PARTICIPANT
```

Eventos iniciais:

```txt
Palestra de Teste de Software
Workshop de Java Spring Boot
Minicurso de React
```

### Endpoints obrigatórios

#### Auth

```http
POST /api/auth/login
```

Body:

```json
{
  "email": "admin@acad.com",
  "password": "123456"
}
```

Resposta esperada:

```json
{
  "id": 1,
  "name": "Administrador",
  "email": "admin@acad.com",
  "role": "ADMIN"
}
```

Se login inválido, retornar HTTP 401.

#### Users

```http
POST /api/users
```

Cria usuário participante.

Body:

```json
{
  "name": "Natan",
  "email": "natan@email.com",
  "password": "123456"
}
```

#### Events

```http
GET /api/events
```

Lista todos os eventos.

```http
GET /api/events/{id}
```

Consulta um evento por ID.

```http
POST /api/events
```

Cria evento.

Body:

```json
{
  "title": "Evento Acadêmico",
  "description": "Descrição do evento",
  "date": "2026-06-10",
  "location": "Auditório",
  "capacity": 50
}
```

```http
PUT /api/events/{id}
```

Atualiza evento.

```http
DELETE /api/events/{id}
```

Remove evento.

#### Registrations

```http
POST /api/events/{eventId}/registrations
```

Body:

```json
{
  "userId": 2
}
```

Regra: o mesmo usuário não pode se inscrever duas vezes no mesmo evento.

Regra: não permitir inscrição se a capacidade do evento estiver esgotada.

```http
GET /api/users/{userId}/registrations
```

Lista inscrições do usuário.

## Regras de negócio

Implementar validações simples:

```txt
Login deve validar email e senha.
Evento deve ter título obrigatório.
Evento deve ter capacidade maior que zero.
Usuário não pode se inscrever duas vezes no mesmo evento.
Usuário não pode se inscrever se o evento estiver lotado.
Ao deletar evento, remover inscrições relacionadas ou impedir erro.
```

## Frontend React — participante

Criar app em `/frontend-react`.

Rotas:

```txt
/login
/register
/events
/events/:id
/my-registrations
```

### Telas

#### Login

Campos:

```txt
email
password
```

Botão:

```txt
Entrar
```

Ao logar com sucesso, salvar usuário no localStorage e redirecionar para `/events`.

Exibir erro se login falhar.

#### Cadastro

Campos:

```txt
name
email
password
```

Botão:

```txt
Cadastrar
```

Após cadastro, redirecionar para login.

#### Lista de eventos

Mostrar cards com:

```txt
title
date
location
capacity
botão Ver detalhes
```

#### Detalhes do evento

Mostrar:

```txt
title
description
date
location
capacity
botão Inscrever-se
```

Ao clicar em inscrever-se, chamar endpoint de inscrição usando o usuário salvo no localStorage.

Exibir mensagem de sucesso ou erro.

#### Minhas inscrições

Listar eventos em que o usuário está inscrito.

## Angular Admin

Criar app em `/admin-angular`.

Rotas:

```txt
/admin/login
/admin/events
/admin/events/new
/admin/events/:id/edit
```

### Telas

#### Login admin

Pode usar o mesmo endpoint `/api/auth/login`.

Permitir acesso apenas se `role` for `ADMIN`.

#### Lista de eventos admin

Mostrar tabela com:

```txt
ID
Título
Data
Local
Capacidade
Editar
Excluir
```

#### Criar evento

Formulário com:

```txt
title
description
date
location
capacity
```

Botão:

```txt
Salvar
```

#### Editar evento

Carregar dados do evento e permitir atualização.

#### Excluir evento

Botão de excluir com confirmação simples.

## Testes obrigatórios

Criar testes simples, mas funcionando.

### Backend — testes unitários

Em `/backend-java/src/test`.

Criar testes para `EventService`.

Casos:

```txt
Deve criar evento com dados válidos.
Não deve criar evento com capacidade menor ou igual a zero.
Não deve permitir inscrição duplicada.
Não deve permitir inscrição em evento lotado.
```

Usar JUnit 5 e Mockito.

### Backend — testes de API

Usar MockMvc.

Testar:

```txt
POST /api/auth/login com credenciais válidas deve retornar 200.
POST /api/auth/login com credenciais inválidas deve retornar 401.
GET /api/events deve retornar 200.
POST /api/events deve criar evento e retornar 201 ou 200.
DELETE /api/events/{id} deve remover evento.
```

### React — teste simples

Criar pelo menos 1 teste:

```txt
LoginPage deve renderizar campos de email, senha e botão Entrar.
```

### Angular — teste simples

Criar pelo menos 1 teste:

```txt
Componente de lista de eventos deve ser criado com sucesso.
```

### E2E — Playwright

Criar projeto em `/e2e`.

Testes:

```txt
Usuário participante consegue acessar tela de login.
Usuário consegue fazer login.
Usuário consegue visualizar lista de eventos.
Admin consegue acessar painel de eventos.
```

Os testes E2E podem ser simples e usar os dados iniciais.

## Documentação

Criar pasta `/docs` com os arquivos:

```txt
documento-visao.md
historias-usuario.md
plano-testes.md
casos-teste.md
evidencias.md
relatorio-final.md
```

Preencher todos com conteúdo inicial.

### documento-visao.md

Incluir:

```txt
Nome do sistema
Objetivo
Público-alvo
Funcionalidades principais
Tecnologias utilizadas
```

### historias-usuario.md

Criar histórias:

```txt
HU-001 Login de usuário
HU-002 Cadastro de usuário
HU-003 Consulta de eventos
HU-004 Cadastro de evento
HU-005 Atualização de evento
HU-006 Exclusão de evento
HU-007 Inscrição em evento
```

Cada história deve ter:

```txt
Descrição
Critérios de aceitação
Cenário BDD em Gherkin
```

### plano-testes.md

Incluir:

```txt
Objetivo dos testes
Escopo
Fora do escopo
Ferramentas utilizadas
Estratégia de testes
Critérios de aceitação
Papéis e responsabilidades
```

Papéis:

```txt
Natan: desenvolvimento principal, backend, frontend, testes principais e integração
Yves: README e documento de visão
Hiago: histórias de usuário e cenários BDD
Thales: casos de teste
Nicolas: evidências dos testes e relatório final
```

### casos-teste.md

Criar tabela com pelo menos estes casos:

```txt
CT-001 Login válido
CT-002 Login inválido
CT-003 Cadastro de usuário
CT-004 Listagem de eventos
CT-005 Criar evento
CT-006 Editar evento
CT-007 Deletar evento
CT-008 Inscrição em evento
CT-009 Inscrição duplicada
CT-010 Evento lotado
```

Cada caso deve ter:

```txt
ID
Descrição
Passos
Entrada
Saída esperada
Critério de sucesso
Tipo de teste: unitário, API ou E2E
```

### evidencias.md

Criar modelo com seções para:

```txt
Prints dos testes unitários
Prints dos testes de API
Prints dos testes E2E
Logs de execução
Bugs encontrados
Correções realizadas
```

### relatorio-final.md

Criar estrutura com:

```txt
1. Introdução
2. Descrição do sistema
3. Planejamento de testes
4. Casos de teste
5. Resultados e evidências
6. Conclusão
7. Lições aprendidas
8. Link do GitHub
```

## README.md

Criar README principal com:

```txt
Nome do projeto
Descrição
Integrantes
Tecnologias
Como executar backend
Como executar React
Como executar Angular
Como executar testes
Link da documentação
```

Comandos esperados:

Backend:

```bash
cd backend-java
mvn spring-boot:run
```

Testes backend:

```bash
cd backend-java
mvn test
```

React:

```bash
cd frontend-react
npm install
npm run dev
```

Angular:

```bash
cd admin-angular
npm install
npm start
```

E2E:

```bash
cd e2e
npm install
npx playwright test
```

## Commits sugeridos para o grupo

Deixar no README uma seção com tarefas simples para cada integrante.

```txt
Natan:
feat: cria backend com CRUD de eventos
feat: cria frontend React para participantes
feat: cria painel Angular administrativo
test: adiciona testes principais

Yves:
docs: atualiza README e documento de visão

Hiago:
docs: adiciona histórias de usuário e cenários BDD

Thales:
docs: adiciona casos de teste do sistema

Nicolas:
docs: adiciona evidências de execução dos testes
```

## Prioridade de implementação

Implementar primeiro:

```txt
1. Backend Java funcionando com H2.
2. Endpoints principais.
3. React com login e listagem de eventos.
4. Angular admin com lista e cadastro de eventos.
5. Testes backend.
6. Documentação inicial.
7. E2E simples.
```

Evitar complexidade desnecessária.

Não precisa implementar JWT real se atrasar. Pode usar login simples retornando o usuário e salvar no localStorage.

Não precisa criar layout avançado. Interface simples e funcional.

Não precisa usar Docker.

Não precisa usar banco externo. Usar H2 em memória para facilitar execução.

O projeto deve rodar rapidamente em ambiente local e ser fácil de apresentar.

## Resultado esperado

Ao final, o repositório deve conter:

```txt
API Java funcionando
Frontend React funcionando
Painel Angular funcionando
Banco H2 com dados iniciais
Testes unitários
Testes de API
Testes E2E simples
Documentação completa em Markdown
README com instruções
Tarefas simples separadas para Yves, Hiago, Thales e Nicolas
```

````

Também dá para acrescentar no final do prompt:

```md
Gere o código completo agora, priorizando simplicidade e execução rápida. Quando houver dúvida, escolha a solução mais simples. Não implemente autenticação complexa. Prefira código direto, organizado e fácil de testar.
````
