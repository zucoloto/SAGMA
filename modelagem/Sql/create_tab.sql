-- Estrutura da tabela 'configurar'
CREATE TABLE IF NOT EXISTS atv_configurar (
  id BIGINT NOT NULL AUTO_INCREMENT,
  om VARCHAR(100) NOT NULL,
  sigla VARCHAR(20) NOT NULL,
  logo MEDIUMBLOB NOT NULL,
  diretorio_pop VARCHAR(255),
  PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Estrutura da tabela 'fracao'
CREATE TABLE IF NOT EXISTS atv_fracao (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome_fracao VARCHAR(100) NOT NULL,
  fracao_pai_id BIGINT(20),
  ordem_qc VARCHAR(10) NOT NULL,
  PRIMARY KEY(id),
  INDEX fk_fracao_fracao_pai(fracao_pai_id),
  FOREIGN KEY(fracao_pai_id) REFERENCES atv_fracao(id)
) ENGINE=InnoDB AUTO_INCREMENT=32;

-- Estrutura da tabela 'assunto_atividade'
CREATE TABLE IF NOT EXISTS atv_assunto_atividade (
  id BIGINT NOT NULL AUTO_INCREMENT,
  fracao_id BIGINT NOT NULL,  
  nome_atividade VARCHAR(100) NOT NULL,
  caderno_encargo TINYINT(1) NOT NULL,  
  periodico VARCHAR(30) NULL,
  PRIMARY KEY(id),
  INDEX fk_assunto_fracao(fracao_id),
  FOREIGN KEY(fracao_id) REFERENCES atv_fracao(id)
) ENGINE=InnoDB;

-- Estrutura da tabela 'pop'
CREATE TABLE IF NOT EXISTS atv_pop (
  id BIGINT NOT NULL AUTO_INCREMENT,
  pop VARCHAR(100) NULL,
  assunto_atividade_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  INDEX fk_atividade_assunto(assunto_atividade_id),
  FOREIGN KEY(assunto_atividade_id) REFERENCES atv_assunto_atividade(id)
) ENGINE=InnoDB;

-- Estrutura da tabela 'usuario_fracao'
CREATE TABLE IF NOT EXISTS atv_usuario_fracao (
  usuario_id BIGINT(20) NOT NULL,
  fracao_id BIGINT(20) NOT NULL,
  PRIMARY KEY (usuario_id, fracao_id),
  INDEX fk_usuario_fracao(usuario_id),
  INDEX fk_fracao_usuario(fracao_id),
  FOREIGN KEY(usuario_id) REFERENCES seg_usuario(id),
  FOREIGN KEY(fracao_id) REFERENCES atv_fracao(id)
) ENGINE=InnoDB;


-- Estrutura da tabela 'atividade'
CREATE TABLE IF NOT EXISTS atv_atividade (
  id BIGINT NOT NULL AUTO_INCREMENT,
  assunto_atividade_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  status_prioridade VARCHAR(20) NOT NULL,
  status_atividade VARCHAR(20) NOT NULL,
  prazo DATE NULL,
  data_inicio DATE NULL,
  data_termino DATE NULL,
  observacao TEXT,
  PRIMARY KEY(id),
  INDEX fk_atividade_assunto(assunto_atividade_id),  
  INDEX fk_atividade_usuario(usuario_id),
  FOREIGN KEY(assunto_atividade_id) REFERENCES atv_assunto_atividade(id), 
  FOREIGN KEY(usuario_id) REFERENCES seg_usuario(id)
) ENGINE=InnoDB;

-- Estrutura da tabela 'grupo'
CREATE TABLE IF NOT EXISTS atv_grupo (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome_grupo VARCHAR(50) NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB;

-- Estrutura da tabela 'usuario_grupo'
CREATE TABLE IF NOT EXISTS atv_usuario_grupo (
  usuario_id BIGINT(20) NOT NULL,
  grupo_id BIGINT(20) NOT NULL,
  PRIMARY KEY (usuario_id, grupo_id),
  INDEX fk_usuario_grupo(usuario_id),
  INDEX fk_grupo_usuario(grupo_id),
  FOREIGN KEY(usuario_id) REFERENCES seg_usuario(id),
  FOREIGN KEY(grupo_id) REFERENCES atv_grupo(id)
) ENGINE=InnoDB;
