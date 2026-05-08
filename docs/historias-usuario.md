# Historias de Usuario


## HU-001 Login de usuario

### Descricao
Como usuario, quero autenticar no sistema para acessar os eventos e funcionalidades da plataforma.

### Criterios de aceitacao
- O usuario deve informar email e senha.
- Credenciais validas devem permitir acesso ao sistema.
- Credenciais invalidas devem retornar erro 401.
- O sistema deve impedir acesso sem autenticacao.

### Cenario BDD
Dado que o usuario possui cadastro no sistema  
Quando informar email e senha validos  
Entao deve acessar o sistema com sucesso  

Dado que o usuario informa credenciais invalidas  
Quando tentar realizar login  
Entao o sistema deve retornar erro 401  


## HU-002 Cadastro de usuario

### Descricao
Como visitante, quero criar uma conta para participar dos eventos da plataforma.

### Criterios de aceitacao
- O usuario deve informar nome, email e senha.
- O email nao pode estar cadastrado.
- O sistema deve validar campos obrigatorios.
- A conta deve ser criada com sucesso.

### Cenario BDD
Dado que o visitante esta na tela de cadastro  
Quando preencher os dados corretamente  
Entao a conta deve ser criada com sucesso  

Dado que o email ja esta cadastrado  
Quando tentar criar uma nova conta  
Entao o sistema deve informar que o email ja existe  


## HU-003 Consulta de eventos

### Descricao
Como participante, quero visualizar os eventos disponiveis para realizar inscricoes.

### Criterios de aceitacao
- O sistema deve listar eventos disponiveis.
- A rota `/api/events` deve retornar a lista de eventos.
- Apenas usuarios autenticados podem consultar eventos.
- Os eventos devem apresentar titulo, data e descricao.

### Cenario BDD
Dado que o usuario esta autenticado  
Quando acessar a listagem de eventos  
Entao o sistema deve exibir os eventos disponiveis  

Dado que nao existem eventos cadastrados  
Quando acessar a listagem  
Entao o sistema deve informar que nao ha eventos disponiveis  


## HU-004 Cadastro de evento

### Descricao
Como administrador, quero cadastrar eventos para disponibiliza-los aos participantes.

### Criterios de aceitacao
- Apenas administradores podem cadastrar eventos.
- O evento deve possuir titulo, data e descricao.
- O sistema deve validar campos obrigatorios.
- O evento deve ser salvo corretamente.

### Cenario BDD
Dado que o administrador esta autenticado  
Quando preencher os dados do evento corretamente  
Entao o sistema deve cadastrar o evento com sucesso  

Dado que campos obrigatorios nao foram preenchidos  
Quando tentar cadastrar o evento  
Entao o sistema deve informar erro de validacao  


## HU-005 Atualizacao de evento

### Descricao
Como administrador, quero atualizar informacoes de eventos ja cadastrados.

### Criterios de aceitacao
- Apenas administradores podem editar eventos.
- O sistema deve permitir alterar dados do evento.
- Alteracoes devem ser salvas corretamente.
- O evento atualizado deve aparecer na listagem.

### Cenario BDD
Dado que existe um evento cadastrado  
Quando o administrador atualizar suas informacoes  
Entao o sistema deve salvar as alteracoes com sucesso  


## HU-006 Exclusao de evento

### Descricao
Como administrador, quero excluir eventos que nao serao mais realizados.

### Criterios de aceitacao
- Apenas administradores podem excluir eventos.
- O sistema deve solicitar confirmacao da exclusao.
- O evento removido nao deve aparecer na listagem.
- O sistema deve impedir exclusao de eventos inexistentes.

### Cenario BDD
Dado que existe um evento cadastrado  
Quando o administrador confirmar a exclusao  
Entao o evento deve ser removido do sistema  


## HU-007 Inscricao em evento

### Descricao
Como participante, quero me inscrever em eventos disponiveis.

### Criterios de aceitacao
- O usuario deve estar autenticado.
- O evento deve possuir vagas disponiveis.
- O sistema nao deve permitir inscricoes duplicadas.
- A inscricao deve ser registrada com sucesso.

### Cenario BDD
Dado que o evento possui vagas disponiveis  
Quando o participante solicitar inscricao  
Entao o sistema deve registrar a inscricao com sucesso  

Dado que nao existem vagas disponiveis  
Quando tentar realizar a inscricao  
Entao o sistema deve informar indisponibilidade de vagas