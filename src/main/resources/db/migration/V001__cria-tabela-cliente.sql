CREATE TABLE clientes (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(160) NOT NULL,
  email varchar(160) NOT NULL,
  telefone varchar(20) NOT NULL,
  PRIMARY KEY (id)
)