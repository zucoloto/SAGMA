-- Estrutura da tabela 'Usuário'
CREATE TABLE IF NOT EXISTS seg_usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  identidade VARCHAR(15) UNIQUE KEY NOT NULL,
  nome_usuario VARCHAR(100) NOT NULL,
  email VARCHAR(255) UNIQUE KEY NOT NULL,
  status_usuario VARCHAR(20) NOT NULL,
  senha VARCHAR(32) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB;
