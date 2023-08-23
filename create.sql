create table tb_departamento (id bigserial not null, titulo varchar(255), primary key (id));
create table tb_pessoa (id bigserial not null, id_departamento bigint, nome varchar(255), primary key (id));
create table tb_tarefa (duracao integer not null, finalizado boolean not null, prazo date, id bigserial not null, id_departamento bigint, pessoa bigint, descricao varchar(255), titulo varchar(255), primary key (id));
alter table if exists tb_pessoa add constraint FKqyjtcw89eaun86a0jinjhomrm foreign key (id_departamento) references tb_departamento;
alter table if exists tb_tarefa add constraint FK6hkr31g6erpo72omxcwtg2kxb foreign key (id_departamento) references tb_departamento;
alter table if exists tb_tarefa add constraint FKt7rqnfy8nwbv9ncx747vtdp6j foreign key (pessoa) references tb_pessoa;
