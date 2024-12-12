# Sistema de Casamento

## Sobre o Projeto

O **Sistema de Casamento** é uma aplicação desenvolvida para facilitar a organização de casamentos, incluindo a gestão de convidados, fornecedores, cronograma e mais. Este sistema é projetado para ser intuitivo e flexível, permitindo personalizações de acordo com as necessidades do casal.

## Funcionalidades

- **Gestão de Convidados**: Cadastro, organização de mesas e confirmação de presença (RSVP).
- **Controle de Fornecedores**: Registro de contatos e serviços contratados.
- **Cronograma do Evento**: Planejamento e acompanhamento de todas as etapas do casamento.
- **Orçamento**: Controle de custos e monitoramento de gastos.

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Frontend**: Html, CSS e JavaScript
- **Banco de Dados**: MySql
- **Autenticação**: JWT (JSON Web Tokens)

## Requisitos

- **Java** 17
- **MySql** instalado e configurado
- **Maven** para gerenciamento de dependências no backend

## Instalação e Configuração

---

## Guia de Requisições para o Cliente

Aqui está o passo a passo para realizar as ações no sistema:

### 1. Registro e Login
- **Ação:** Registrar-se ou fazer login no sistema.
- **Endpoint:** `POST /auth/login` ou ` POST /auth/register`
- **Descrição Do Login:** O cliente informa e-mail e senha para acessar a aplicação.
- - **Descrição Do Registro:** O cliente informa e-mail, primeiro nome, o ultimo nome.

### 2. Cadastro de Casamento
- **Ação:** Cadastrar ou atualizar informações do casamento.
- **Endpoint:** `POST /casamentos`
- **Descrição:** Inserir informações como data e local do casamento.

### 3. Gerenciamento de Convidados
- **Ação:** Adicionar convidados ao evento.
- **Endpoint:** `POST /convidados`
- **Descrição:** Forneça o nome e o status de presença (RSVP) dos convidados.

### 4. Envio de Convites
- **Ação:** Enviar convites para os convidados.
- **Endpoint:** `POST /convites`
- **Descrição:** Notifica os convidados com detalhes do evento.

### 5. Gerenciamento de Fornecedores
- **Ação:** Registrar fornecedores e serviços contratados.
- **Endpoint:** `POST /fornecedores`
- **Descrição:** Adicionar informações dos fornecedores, como nome e tipo de serviço.

### 6. Controle de Orçamento
- **Ação:** Adicionar ou atualizar dados do orçamento.
- **Endpoint:** `POST /orcamentos`
- **Descrição:** Inserir custos e controlar as despesas.


---

Com este guia, o cliente poderá navegar facilmente pelo sistema e utilizar todas as funcionalidades disponíveis. Caso tenha dúvidas, entre em contato com o suporte!

