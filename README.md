#  gooLogin

## Introdução
O sistema gooLogin tem como finalidade auxiliar no processo de login, fornecendo um componente seguro de autenticação e gestão de usuários.

## Instalação
- Ter o SQL Server Management Studio versão 19+;
- Rodar os scripts de criação de tabela contidos na pasta scripts ou realizar a criação via Hibernate, alterando a propriedade `hibernate.hbm2ddl.auto` para `create`;
- Clonar o projeto, verificar se a versão do java é compatível com a máquina, caso negativo modificar no arquivo pom.xml;
- Caso necessário, adicionar a dependência AbsoluteLayout-RELEASE65.jar.

## Como utilizar
- Pode ser utilizado em conjunto com outro sistema, visto que o componente de login visa aunteticar o usuário e direcionar para outra tela, de acordo com suas permissões.

## Funcionalidades
- Cadastros:
    - Cadastrar usuários e grupos de usuários.
- Integração com o banco de dados:
    - Salva as informações no banco de dados SQL Server.

## Referências:

#### Framework Hibernate:
1. [https://hibernate.org](https://hibernate.org)
#### JDBC para SQLServer:
2. [https://learn.microsoft.com/pt-br/sql/connect/jdbc/deploying-the-jdbc-driver?view=sql-server-ver16](https://learn.microsoft.com/pt-br/sql/connect/jdbc/deploying-the-jdbc-driver?view=sql-server-ver16)