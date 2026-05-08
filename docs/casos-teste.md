# Casos de Teste - ScholarSync

| ID     |      Descrição      |                Passos                  |         Entrada        | Saída Esperada             | Critério de Sucesso     | Tipo     |

| CT-001 | Login válido        | 1. Inserir email/senha corretos.       | Email e senha válidos  | Redirecionamento para Home | Status 200 OK           | E2E      |
| CT-002 | Login inválido      | 1. Inserir senha errada.               | Credenciais incorretas | Alerta de erro             | Acesso negado           | E2E      |
| CT-003 | Cadastro usuário    | 1. Preencher formulário de registro.   | Dados pessoais novos   | Usuário criado             | Registro no BD          | API      |
| CT-004 | Listagem eventos    | 1. Acessar página de eventos           | N/A                    | Lista de eventos ativos    | Retorno da lista        | API      |
| CT-005 | Criar evento        | 1. Preencher detalhes do evento.       | Nome, data, local      | Evento salvo com sucesso   | Status 201 Created      | API      |
| CT-006 | Editar evento       | 1. Alterar data de um evento.          | Nova data              | Dados atualizados          | Persistência no BD      | API      |
| CT-007 | Deletar evento      | 1. Selecionar evento.                  | ID do evento           | Remoção da lista           | Status 204 No Content   | API      |
| CT-008 | Inscrição           | 1. Escolher evento.                    | ID Usuário + ID Evento | Comprovante gerado         | Vínculo na tabela       | E2E      |
| CT-009 | Inscrição duplicada | 1. Tentar inscrever-se no mesmo evento | Dados já inscritos     | Mensagem de erro           | Bloqueio de duplicidade | Unitário |
| CT-010 | Evento lotado       | 1. Tentar inscrição em evento sem vaga | ID Evento sem vagas    | Aviso de "Lotado"          | Impedir inscrição       | Unitário |