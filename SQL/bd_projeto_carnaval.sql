CREATE TABLE grupos (
  id_grupo INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome_grupo VARCHAR(20) NOT NULL,
  PRIMARY KEY(id_grupo)
);

CREATE TABLE perfil (
  id_perfil INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome_perfil VARCHAR(20) NULL,
  descricao VARCHAR(255) NULL,
  PRIMARY KEY(id_perfil)
);

CREATE TABLE quesito (
  id_quesito INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome_quesito VARCHAR(45) NOT NULL,
  descricao VARCHAR(255) NULL,
  PRIMARY KEY(id_quesito)
);

CREATE TABLE usuario (
  id_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  ativo BOOL NULL DEFAULT true,
  PRIMARY KEY(id_usuario)
);

CREATE TABLE acao (
  id_acao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  descricao VARCHAR(255) NULL,
  PRIMARY KEY(id_acao)
);

CREATE TABLE pessoa (
  id_pessoa INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  usuario_id_usuario INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(255) NULL,
  endereco VARCHAR(255) NULL,
  cpf VARCHAR(11) NULL,
  cep VARCHAR(8) NULL,
  telefone VARCHAR(20) NULL,
  email VARCHAR(255) NULL,
  sexo CHAR(1) NULL,
  PRIMARY KEY(id_pessoa),
  INDEX pessoa_FKIndex1(usuario_id_usuario),
  FOREIGN KEY(usuario_id_usuario)
    REFERENCES usuario(id_usuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE integrante (
  id_integrante INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  pessoa_id_pessoa INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id_integrante),
  INDEX integrante_FKIndex1(pessoa_id_pessoa),
  FOREIGN KEY(pessoa_id_pessoa)
    REFERENCES pessoa(id_pessoa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE re_usuario_perfil (
  usuario_id_usuario INTEGER UNSIGNED NOT NULL,
  perfil_id_perfil INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(usuario_id_usuario, perfil_id_perfil),
  INDEX usuario_has_perfil_FKIndex1(usuario_id_usuario),
  INDEX usuario_has_perfil_FKIndex2(perfil_id_perfil),
  FOREIGN KEY(usuario_id_usuario)
    REFERENCES usuario(id_usuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(perfil_id_perfil)
    REFERENCES perfil(id_perfil)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE escola_samba (
  id_escola_samba INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  usuario_id_usuario INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(255) NULL,
  endereco_quadra VARCHAR(255) NULL,
  endereco_barracao VARCHAR(255) NULL,
  data_fundacao DATE NULL,
  lema VARCHAR(255) NULL,
  filiacao VARCHAR(255) NULL,
  grupos_id_grupo INTEGER UNSIGNED NOT NULL,
  email VARCHAR(255) NULL,
  telefone VARCHAR(20) NULL,
  cnpj VARCHAR(20) NOT NULL,
  PRIMARY KEY(id_escola_samba),
  INDEX escola_samba_FKIndex1(grupos_id_grupo),
  INDEX escola_samba_FKIndex2(usuario_id_usuario),
  FOREIGN KEY(grupos_id_grupo)
    REFERENCES grupos(id_grupo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(usuario_id_usuario)
    REFERENCES usuario(id_usuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE jurado (
  id_jurado INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  quesito_id_quesito INTEGER UNSIGNED NOT NULL,
  pessoa_id_pessoa INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id_jurado, quesito_id_quesito, pessoa_id_pessoa),
  INDEX jurado_FKIndex1(quesito_id_quesito),
  INDEX jurado_FKIndex2(pessoa_id_pessoa),
  FOREIGN KEY(quesito_id_quesito)
    REFERENCES quesito(id_quesito)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(pessoa_id_pessoa)
    REFERENCES pessoa(id_pessoa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE atividade_integrante_escola (
  integrante_id_integrante INTEGER UNSIGNED NOT NULL,
  acao_id_acao INTEGER UNSIGNED NOT NULL,
  escola_samba_id_escola_samba INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(integrante_id_integrante, acao_id_acao, escola_samba_id_escola_samba),
  INDEX integrante_has_atividade_FKIndex1(integrante_id_integrante),
  INDEX integrante_has_atividade_FKIndex2(acao_id_acao),
  INDEX integrante_has_atividade_FKIndex3(escola_samba_id_escola_samba),
  FOREIGN KEY(integrante_id_integrante)
    REFERENCES integrante(id_integrante)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(atividade_id_atividade)
    REFERENCES atividade(id_atividade)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(escola_samba_id_escola_samba)
    REFERENCES escola_samba(id_escola_samba)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE ensaio (
  id_ensaio INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  escola_samba_id_escola_samba INTEGER UNSIGNED NOT NULL,
  data_ensaio DATE NULL,
  PRIMARY KEY(id_ensaio, escola_samba_id_escola_samba),
  INDEX ensaio_FKIndex1(escola_samba_id_escola_samba),
  FOREIGN KEY(escola_samba_id_escola_samba)
    REFERENCES escola_samba(id_escola_samba)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE torcedor (
  id_torcedor INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  pessoa_id_pessoa INTEGER UNSIGNED NOT NULL,
  escola_samba_id_escola_samba INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id_torcedor, pessoa_id_pessoa, escola_samba_id_escola_samba),
  INDEX torcedor_FKIndex1(escola_samba_id_escola_samba),
  INDEX torcedor_FKIndex2(pessoa_id_pessoa),
  FOREIGN KEY(escola_samba_id_escola_samba)
    REFERENCES escola_samba(id_escola_samba)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(pessoa_id_pessoa)
    REFERENCES pessoa(id_pessoa)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


insert into perfil (nome_perfil, descricao) values ('Administrador','Administrador do sistema');
insert into perfil (nome_perfil, descricao) values ('Escola de Samba','Escolas de samba usuárias do sistema');
insert into perfil (nome_perfil, descricao) values ('Integrante','Integrantes participantes dos desfiles');
insert into perfil (nome_perfil, descricao) values ('Torcedor','Torcedores das escolas de samba');
commit;

insert into usuario (usuario, senha) values ('admin', '123456');
commit;


-- Se estiver correto, a query abaixo tem que retornar o admin como administrador do sistema

--select * from usuario, re_usuario_perfil, perfil
--where usuario.id_usuario = re_usuario_perfil.usuario_id_usuario
--and perfil.id_perfil = re_usuario_perfil.perfil_id_perfil


insert into grupos (nome_grupo) values ("Grupo Especial");
insert into grupos (nome_grupo) values ("Serie A"); 
insert into grupos (nome_grupo) values ("Grupo B"); 
insert into grupos (nome_grupo) values ("Grupo C");
insert into grupos (nome_grupo) values ("Grupo D");
insert into grupos (nome_grupo) values ("Grupo Avaliação");
commit;
