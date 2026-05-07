# Historias de Usuario
HU-001 Login de usuario
Descricao: como usuario quero autenticar para acessar eventos.
Criterios: credenciais validas retornam usuario; invalidas retornam 401.
BDD: Dado usuario cadastrado Quando informa email/senha validos Entao entra no sistema.

HU-002 Cadastro de usuario
Descricao: como visitante quero criar conta.
Criterios: cria participante.
BDD: Dado formulario valido Quando enviar Entao conta criada.

HU-003 Consulta de eventos
Descricao: como participante quero ver eventos.
Criterios: lista disponivel em /api/events.
BDD: Dado usuario logado Quando acessar eventos Entao ve lista.

HU-004 Cadastro de evento / HU-005 Atualizacao / HU-006 Exclusao / HU-007 Inscricao
Descricao: admin gerencia eventos e participante se inscreve.
Criterios: CRUD e regras de lotacao/duplicidade respeitadas.
BDD: Dado evento existente Quando usuario se inscreve Entao registro criado se houver vaga.
