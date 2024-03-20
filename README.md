### GerenciamentoJPA
___________________________________________

Este é um projeto acadêmico desenvolvido para estudos de persistência de dados utilizando JPA (Java Persistence API). O foco principal deste projeto é explorar os conceitos de mapeamento objeto-relacional (ORM) e operações básicas de banco de dados utilizando o Hibernate como provedor de persistência.

### Descrição do Projeto
_________________________________________

O projeto consiste em um sistema básico para gerenciamento de veículos, onde é possível realizar operações como adicionar, atualizar, buscar e remover veículos do banco de dados. Além disso, os veículos estão associados a uma categoria, permitindo a categorização e organização dos mesmos.
Tecnologias Utilizadas

    Java
    JPA (Java Persistence API)
    Hibernate
    Banco de Dados Relacional (utilizando MySQL neste exemplo)

### Estrutura do Projeto
_____________________________________________

O projeto está organizado da seguinte maneira:

    br.com.herick.model: Pacote contendo as classes de modelo do projeto.
        Veiculo.java: Classe que representa a entidade Veículo.
        Categoria.java: Classe que representa a entidade Categoria.
    br.com.herick.model.dao: Pacote contendo as classes responsáveis pela interação com o banco de dados.
        VeiculoDAO.java: Classe com operações CRUD para a entidade Veículo.
        CategoriaDAO.java: Classe com operações CRUD para a entidade Categoria.
    br.com.herick.tests: Pacote contendo classes de teste para as operações do sistema.
        VeiculoTest.java: Classe com métodos de teste para operações relacionadas a Veículos.
        CategoriaTest.java: Classe com métodos de teste para operações relacionadas a Categorias.

### Execução do Projeto
___________________________________________________

Para executar o projeto, siga os passos abaixo:

    Certifique-se de ter o JDK (Java Development Kit) e um servidor MySQL instalados em seu sistema.
    Importe o projeto para sua IDE de desenvolvimento (Eclipse, IntelliJ, etc.).
    Certifique-se de que as configurações de conexão com o banco de dados estão corretamente configuradas no arquivo persistence.xml.
    Execute a classe VeiculoTest.java para testar as operações relacionadas a Veículos.
    Execute a classe CategoriaTest.java para testar as operações relacionadas a Categorias.

### Atenção
____________________________________________________

Este projeto foi desenvolvido com propósitos acadêmicos e pode conter bugs, erros ou lacunas de implementação. Não é recomendado para uso em ambientes de produção sem uma revisão adequada e implementação de boas práticas de desenvolvimento.
