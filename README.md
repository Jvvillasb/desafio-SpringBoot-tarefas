# Desafio-Gerenciamento de Tarefas

### Tecnologias Utilizadas
-Java

-Spring Boot

-Postgres

### Iniciando o Projeto

Ao importar o projeto no computador local, o único passo que devemos ficar atentos é no arquivo application.properties,
onde devemos colocar as configurações do banco Postgres configurado em nossa máquina. E também devemos criar uma database
com o nome desafio, que eu deixei por padrão, para quando startarmos o projeto pela primeira vez, seja realizado a criação
das tabelas e também o Seeding da tabela Departamento.

### Rotas

-Adicionar uma pessoa (post/pessoas)
°Body ({
    "idDepartamento": ,
    "nome": ""
})

-Atualizar uma pessoa (put/pessoas/{id})
°Params (id)
°Body ({
    "idDepartamento": ,
    "nome": ""
})

-Remover uma pessoa (delete/pessoas/{id})
°Params (id)

-Adicionar uma tarefa (post/tarefas)
°Body ({
    "descricao": "",
    "duracao": ,
    "idDepartamento": ,
    "prazo": "(yyyy-MM-dd)",
    "titulo": ""
})

-Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})
°Params (id => idPessoa)
°Body ({
    "idTarefa": 2
})

-Finalizar a tarefa (put/tarefas/finalizar/{id})
°Params (id)

-Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)

-Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)
°Params (nome , dataMin, dataMax)

-Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)

-Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
