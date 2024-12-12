# Sistema de Casamento

## Sobre o Projeto

O **Sistema de Casamento** é uma aplicação desenvolvida para facilitar a organização de casamentos, incluindo a gestão de convidados, fornecedores, cronograma e mais. Este sistema é projetado para ser intuitivo e flexível, permitindo personalizações de acordo com as necessidades do casal.

## Funcionalidades

- **Gestão de Convidados**: Cadastro, organização de mesas e confirmação de presença (RSVP).
- **Controle de Fornecedores**: Registro de contatos e serviços contratados.
- **Cronograma do Evento**: Planejamento e acompanhamento de todas as etapas do casamento.
- **Orçamento**: Controle de custos e monitoramento de gastos.
- **Galeria de Fotos**: Álbum colaborativo para registro dos momentos especiais.
- **Personalização do Site**: Criação de um site exclusivo para o casamento com informações e contagem regressiva.

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Frontend**: Angular ou React (escolha de acordo com a necessidade do usuário)
- **Banco de Dados**: Oracle ou PostgreSQL
- **Autenticação**: JWT (JSON Web Tokens)
- **Hospedagem**: AWS ou Heroku

## Requisitos

- **Java** 11 ou superior
- **Node.js** 14 ou superior (para o frontend)
- **Oracle** ou **PostgreSQL** instalado e configurado
- **Maven** ou **Gradle** para gerenciamento de dependências no backend

## Instalação e Configuração

---

## Guia de Requisições para o Cliente

Aqui está o passo a passo para realizar as ações no sistema:

### 1. Registro e Login
- **Ação:** Registrar-se ou fazer login no sistema.
- **Endpoint:** `POST /api/usuario/login`
- **Descrição:** O cliente informa e-mail e senha para acessar a aplicação.

### 2. Cadastro de Casamento
- **Ação:** Cadastrar ou atualizar informações do casamento.
- **Endpoint:** `POST /api/casamento`
- **Descrição:** Inserir informações como data, local e tema do casamento.

### 3. Gerenciamento de Convidados
- **Ação:** Adicionar convidados ao evento.
- **Endpoint:** `POST /api/convidados`
- **Descrição:** Forneça o nome e o status de presença (RSVP) dos convidados.

### 4. Envio de Convites
- **Ação:** Enviar convites para os convidados.
- **Endpoint:** `POST /api/convites/enviar`
- **Descrição:** Notifica os convidados com detalhes do evento.

### 5. Gerenciamento de Fornecedores
- **Ação:** Registrar fornecedores e serviços contratados.
- **Endpoint:** `POST /api/fornecedores`
- **Descrição:** Adicionar informações dos fornecedores, como nome e tipo de serviço.

### 6. Controle de Orçamento
- **Ação:** Adicionar ou atualizar dados do orçamento.
- **Endpoint:** `POST /api/orcamento`
- **Descrição:** Inserir custos e controlar as despesas.

### 7. Monitoramento do Evento
- **Ação:** Acompanhar o status do planejamento.
- **Endpoint:** `GET /api/evento/status`
- **Descrição:** Verificar o progresso geral, como confirmações de presença e orçamento.

---

Com este guia, o cliente poderá navegar facilmente pelo sistema e utilizar todas as funcionalidades disponíveis. Caso tenha dúvidas, entre em contato com o suporte!

