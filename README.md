Sistema de Casamento

Sobre o Projeto

O Sistema de Casamento é uma aplicação desenvolvida para facilitar a organização de casamentos, incluindo a gestão de convidados, fornecedores, cronograma e mais. Este sistema é projetado para ser intuitivo e flexível, permitindo personalizações de acordo com as necessidades do casal.

Funcionalidades

Gestão de Convidados: Cadastro, organização de mesas e confirmação de presença (RSVP).

Controle de Fornecedores: Registro de contatos e serviços contratados.

Cronograma do Evento: Planejamento e acompanhamento de todas as etapas do casamento.

Orçamento: Controle de custos e monitoramento de gastos.

Galeria de Fotos: Álbum colaborativo para registro dos momentos especiais.

Personalização do Site: Criação de um site exclusivo para o casamento com informações e contagem regressiva.

Tecnologias Utilizadas

Backend: Spring Boot

Frontend: Angular ou React (escolha de acordo com a necessidade do usuário)

Banco de Dados: Oracle ou PostgreSQL

Autenticação: JWT (JSON Web Tokens)

Hospedagem: AWS ou Heroku

Requisitos

Java 11 ou superior

Node.js 14 ou superior (para o frontend)

Oracle ou PostgreSQL instalado e configurado

Maven ou Gradle para gerenciamento de dependências no backend

Instalação e Configuração

Backend

Clone o repositório:

git clone https://github.com/seu-usuario/sistema-casamento.git
cd sistema-casamento/backend

Configure as variáveis de ambiente no arquivo .env:

DATABASE_URL=<URL do banco de dados>
JWT_SECRET=<Chave secreta para autenticação>

Compile e execute o projeto:

./mvnw spring-boot:run

Frontend

Navegue até a pasta do frontend:

cd sistema-casamento/frontend

Instale as dependências:

npm install

Inicie o servidor de desenvolvimento:

npm start

Banco de Dados

Execute os scripts de criação do banco de dados disponíveis em scripts/database/.

Verifique as configurações no arquivo de propriedades do backend.

Contribuição

Contribuições são bem-vindas! Siga os passos abaixo:

Faça um fork do repositório.

Crie uma branch para sua funcionalidade:

git checkout -b minha-funcionalidade

Faça o commit das suas alterações:

git commit -m "Adiciona minha funcionalidade"

Envie para o repositório remoto:

git push origin minha-funcionalidade

Abra um Pull Request.

Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.
