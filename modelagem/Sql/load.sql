insert into atv_grupo (id, nome_grupo, descricao) values (1, 'ADMINISTRADOR', 'Administrador do Sistema - Acesso ao controle de Usuários');
insert into atv_grupo (id, nome_grupo, descricao) values (2, 'USUARIO', 'Usuário do Sistema - Acesso as Atividades');

insert into atv_usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into atv_usuario_grupo (usuario_id, grupo_id) values (1, 2);
