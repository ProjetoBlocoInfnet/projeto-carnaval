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

CREATE TABLE `atividade_integrante_escola` (
  `id_atividade` int(11) NOT NULL AUTO_INCREMENT,
  `integrante_id_integrante` int(10) unsigned NOT NULL,
  `acao_id_acao` int(10) unsigned NOT NULL,
  `escola_samba_id_escola_samba` int(10) unsigned NOT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date DEFAULT NULL,
  PRIMARY KEY (`id_atividade`,`integrante_id_integrante`,`acao_id_acao`,`escola_samba_id_escola_samba`),
  KEY `integrante_has_atividade_FKIndex1` (`integrante_id_integrante`),
  KEY `integrante_has_atividade_FKIndex2` (`acao_id_acao`),
  KEY `integrante_has_atividade_FKIndex3` (`escola_samba_id_escola_samba`),
  CONSTRAINT `atividade_integrante_escola_ibfk_1` FOREIGN KEY (`integrante_id_integrante`) REFERENCES `integrante` (`id_integrante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `atividade_integrante_escola_ibfk_2` FOREIGN KEY (`acao_id_acao`) REFERENCES `acao` (`id_acao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `atividade_integrante_escola_ibfk_3` FOREIGN KEY (`escola_samba_id_escola_samba`) REFERENCES `escola_samba` (`id_escola_samba`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE carnaval_posicao_jurado (
  id_carnaval_posicao_jurado INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  descricao_posicao VARCHAR(50) NULL,
  PRIMARY KEY(id_carnaval_posicao_jurado)
);

CREATE TABLE carnaval (
  id_carnaval INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  grupos_id_grupo INTEGER UNSIGNED NOT NULL,
  data_primeiro_desfile DATE NULL,
  data_ultimo_desfile INTEGER UNSIGNED NULL,
  PRIMARY KEY(id_carnaval),
  INDEX carnaval_FKIndex1(grupos_id_grupo),
  FOREIGN KEY(grupos_id_grupo)
    REFERENCES grupos(id_grupo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE `carnaval_quesitos` (
  `id_carnaval_quesitos` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `carnaval_id_carnaval` int(10) unsigned NOT NULL,
  `ordem_quesito` int(10) unsigned NOT NULL,
  `quesito_id_quesito` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_carnaval_quesitos`,`carnaval_id_carnaval`,`ordem_quesito`,`quesito_id_quesito`),
  KEY `carnaval_quesitos_FKIndex1` (`carnaval_id_carnaval`),
  KEY `carnaval_quesitos_id_quesito_idx` (`quesito_id_quesito`),
  CONSTRAINT `carnaval_quesitos_ibfk_1` FOREIGN KEY (`carnaval_id_carnaval`) REFERENCES `carnaval` (`id_carnaval`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `carnaval_quesitos_id_quesito` FOREIGN KEY (`quesito_id_quesito`) REFERENCES `quesito` (`id_quesito`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE carnaval_quesito_jurado (
  id_carnaval_quesito_jurado INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  carnaval_posicao_jurado_id_carnaval_posicao_jurado INTEGER UNSIGNED NOT NULL,
  carnaval_quesitos_ordem_quesito INTEGER UNSIGNED NOT NULL,
  carnaval_quesitos_carnaval_id_carnaval INTEGER UNSIGNED NOT NULL,
  jurado_id_jurado INTEGER UNSIGNED NOT NULL,
  carnaval_quesitos_id_carnaval_quesitos INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id_carnaval_quesito_jurado),
  INDEX carnaval_quesito_jurado_FKIndex1(carnaval_quesitos_id_carnaval_quesitos, carnaval_quesitos_carnaval_id_carnaval, carnaval_quesitos_ordem_quesito),
  INDEX carnaval_quesito_jurado_FKIndex2(jurado_id_jurado),
  INDEX carnaval_quesito_jurado_FKIndex3(carnaval_posicao_jurado_id_carnaval_posicao_jurado),
  FOREIGN KEY(carnaval_quesitos_id_carnaval_quesitos, carnaval_quesitos_carnaval_id_carnaval, carnaval_quesitos_ordem_quesito)
    REFERENCES carnaval_quesitos(id_carnaval_quesitos, carnaval_id_carnaval, ordem_quesito)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(jurado_id_jurado)
    REFERENCES jurado(id_jurado)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(carnaval_posicao_jurado_id_carnaval_posicao_jurado)
    REFERENCES carnaval_posicao_jurado(id_carnaval_posicao_jurado)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE carnaval_desfile_escola (
  id_carnaval_desfile_escola INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  escola_samba_id_escola_samba INTEGER UNSIGNED NOT NULL,
  carnaval_id_carnaval INTEGER UNSIGNED NOT NULL,
  data_hora_inicio DATETIME NULL,
  PRIMARY KEY(id_carnaval_desfile_escola),
  INDEX carnaval_desfile_escola_FKIndex1(carnaval_id_carnaval),
  INDEX carnaval_desfile_escola_FKIndex2(escola_samba_id_escola_samba),
  FOREIGN KEY(carnaval_id_carnaval)
    REFERENCES carnaval(id_carnaval)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(escola_samba_id_escola_samba)
    REFERENCES escola_samba(id_escola_samba)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE `projeto_carnaval`.`carnaval_notas` (
  `id_carnaval_notas` INT NOT NULL AUTO_INCREMENT,
  `id_carnaval_quesito_jurado` INT UNSIGNED NOT NULL,
  `id_carnaval_desfile_escola` INT UNSIGNED NOT NULL,
  `nota` DECIMAL(2,1) NOT NULL,
  PRIMARY KEY (`id_carnaval_notas`, `id_carnaval_quesito_jurado`, `id_carnaval_desfile_escola`, `nota`),
  INDEX `notas_quesito_idx` (`id_carnaval_quesito_jurado` ASC),
  INDEX `notas_escola_idx` (`id_carnaval_desfile_escola` ASC),
  CONSTRAINT `notas_quesito`
    FOREIGN KEY (`id_carnaval_quesito_jurado`)
    REFERENCES `projeto_carnaval`.`carnaval_quesito_jurado` (`id_carnaval_quesito_jurado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `notas_escola`
    FOREIGN KEY (`id_carnaval_desfile_escola`)
    REFERENCES `projeto_carnaval`.`carnaval_desfile_escola` (`id_carnaval_desfile_escola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into perfil (nome_perfil, descricao) values ('Administrador','Administrador do sistema');
insert into perfil (nome_perfil, descricao) values ('Escola de Samba','Escolas de samba usuárias do sistema');
insert into perfil (nome_perfil, descricao) values ('Integrante','Integrantes participantes dos desfiles');
insert into perfil (nome_perfil, descricao) values ('Torcedor','Torcedores das escolas de samba');
commit;

--Usuario master
insert into usuario (usuario, senha) values ('admin', '123456');
commit;

insert into re_usuario_perfil values (1,1);
commit;

-- Se estiver correto, a query abaixo tem que retornar o admin como administrador do sistema

--select * from usuario, re_usuario_perfil, perfil
--where usuario.id_usuario = re_usuario_perfil.usuario_id_usuario
--and perfil.id_perfil = re_usuario_perfil.perfil_id_perfil

--Valores fixos dos grupos de escola de samba
insert into grupos (nome_grupo) values ("Grupo Especial");
insert into grupos (nome_grupo) values ("Serie A"); 
insert into grupos (nome_grupo) values ("Grupo B"); 
insert into grupos (nome_grupo) values ("Grupo C");
insert into grupos (nome_grupo) values ("Grupo D");
insert into grupos (nome_grupo) values ("Grupo Avaliação");
commit;

--Valores iniciais para as acoes. Conforme tiverem mais vamos complementando aqui.
insert into acao (nome,descricao) values ('Carpinteiro','');
insert into acao (nome,descricao) values ('Ala','');
insert into acao (nome,descricao) values ('Mestre Sala','');
insert into acao (nome,descricao) values ('Porta Bandeira','');
insert into acao (nome,descricao) values ('Confeccionador de instrumentos','');
insert into acao (nome,descricao) values ('Figurinista','');
insert into acao (nome,descricao) values ('Baterista','');
insert into acao (nome,descricao) values ('Vocalista','');
insert into acao (nome,descricao) values ('Bailarino','');
insert into acao (nome,descricao) values ('Sambista','');
commit;
