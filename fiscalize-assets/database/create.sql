SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `fiscalize` ;
CREATE SCHEMA IF NOT EXISTS `fiscalize` DEFAULT CHARACTER SET utf8 ;
USE `fiscalize` ;

-- -----------------------------------------------------
-- Table `fiscalize`.`cota`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`cota` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`cota` (
  `cotaId` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cotaId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fiscalize`.`despesa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`despesa` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`despesa` (
  `despesaId` INT(11) NOT NULL AUTO_INCREMENT,
  `codLegislatura` VARCHAR(500) NULL DEFAULT NULL,
  `datEmissao` VARCHAR(500) NULL DEFAULT NULL,
  `ideCadastro` VARCHAR(500) NULL DEFAULT NULL,
  `indTipoDocumento` VARCHAR(500) NULL DEFAULT NULL,
  `nuCarteiraParlamentar` VARCHAR(500) NULL DEFAULT NULL,
  `nuLegislatura` VARCHAR(500) NULL DEFAULT NULL,
  `numAno` VARCHAR(500) NULL DEFAULT NULL,
  `numEspecificacaoSubCota` VARCHAR(500) NULL DEFAULT NULL,
  `numLote` VARCHAR(500) NULL DEFAULT NULL,
  `numMes` VARCHAR(500) NULL DEFAULT NULL,
  `numParcela` VARCHAR(500) NULL DEFAULT NULL,
  `numRessarcimento` VARCHAR(500) NULL DEFAULT NULL,
  `numSubCota` VARCHAR(500) NULL DEFAULT NULL,
  `sgPartido` VARCHAR(500) NULL DEFAULT NULL,
  `sgUF` VARCHAR(500) NULL DEFAULT NULL,
  `txNomeParlamentar` VARCHAR(500) NULL DEFAULT NULL,
  `txtCNPJCPF` VARCHAR(500) NULL DEFAULT NULL,
  `txtDescricao` VARCHAR(500) NULL DEFAULT NULL,
  `txtDescricaoEspecificacao` VARCHAR(500) NULL DEFAULT NULL,
  `txtFornecedor` VARCHAR(500) NULL DEFAULT NULL,
  `txtNumero` VARCHAR(500) NULL DEFAULT NULL,
  `txtPassageiro` VARCHAR(500) NULL DEFAULT NULL,
  `txtTrecho` VARCHAR(500) NULL DEFAULT NULL,
  `vlrDocumento` VARCHAR(500) NULL DEFAULT NULL,
  `vlrGlosa` VARCHAR(500) NULL DEFAULT NULL,
  `vlrLiquido` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`despesaId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fiscalize`.`partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`partido` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`partido` (
  `partidoId` INT(11) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`partidoId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fiscalize`.`parlamentar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`parlamentar` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`parlamentar` (
  `parlamentarId` INT(11) NOT NULL AUTO_INCREMENT,
  `partidoId` INT(11) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `nomeCivil` VARCHAR(200) NULL DEFAULT NULL,
  `email` VARCHAR(500) NULL DEFAULT NULL,
  `profissao` VARCHAR(200) NULL DEFAULT NULL,
  `ideCadastro` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`parlamentarId`),
  CONSTRAINT `fk_Parlamentar_Partido1`
    FOREIGN KEY (`partidoId`)
    REFERENCES `fiscalize`.`partido` (`partidoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_Parlamentar_Partido1_idx` ON `fiscalize`.`parlamentar` (`partidoId` ASC);


-- -----------------------------------------------------
-- Table `fiscalize`.`uf`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`uf` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`uf` (
  `ufId` INT(11) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(2) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ufId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `sigla_UNIQUE` ON `fiscalize`.`uf` (`sigla` ASC);


-- -----------------------------------------------------
-- Table `fiscalize`.`notafiscal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`notafiscal` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`notafiscal` (
  `notaFiscalId` INT(11) NOT NULL AUTO_INCREMENT,
  `parlamentarId` INT(11) NOT NULL,
  `cotaId` INT(11) NOT NULL,
  `ufId` INT(11) NOT NULL,
  `dataEmissao` DATETIME NULL DEFAULT NULL,
  `descricao` VARCHAR(500) NULL DEFAULT NULL,
  `descricaoSubCota` VARCHAR(500) NULL DEFAULT NULL,
  `fornecedor` VARCHAR(500) NULL DEFAULT NULL,
  `cpfCnpj` VARCHAR(45) NULL DEFAULT NULL,
  `ano` INT(11) NOT NULL,
  `mes` INT(11) NOT NULL,
  `numeroDocumento` VARCHAR(45) NULL DEFAULT NULL,
  `parcela` INT(11) NULL DEFAULT NULL,
  `tipoDocumentoFiscal` INT(11) NOT NULL COMMENT 'Nota Fiscal - 0\nRecibo - 1\nDespesa Exterior - 2\n',
  `nomePassageiro` VARCHAR(500) NULL DEFAULT NULL,
  `trechoViagem` VARCHAR(500) NULL DEFAULT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `valorGlosa` DECIMAL(10,2) NULL DEFAULT NULL,
  `valorLiquido` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`notaFiscalId`),
  CONSTRAINT `fk_NotaFiscal_Cota1`
    FOREIGN KEY (`cotaId`)
    REFERENCES `fiscalize`.`cota` (`cotaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NotaFiscal_Parlamentar1`
    FOREIGN KEY (`parlamentarId`)
    REFERENCES `fiscalize`.`parlamentar` (`parlamentarId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notafiscal_Uf1`
    FOREIGN KEY (`ufId`)
    REFERENCES `fiscalize`.`uf` (`ufId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_NotaFiscal_Parlamentar1_idx` ON `fiscalize`.`notafiscal` (`parlamentarId` ASC);

CREATE INDEX `fk_NotaFiscal_Cota1_idx` ON `fiscalize`.`notafiscal` (`cotaId` ASC);

CREATE INDEX `fk_notafiscal_Uf1_idx` ON `fiscalize`.`notafiscal` (`ufId` ASC);


-- -----------------------------------------------------
-- Table `fiscalize`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`usuario` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`usuario` (
  `usuarioId` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `facebookId` VARCHAR(45) NULL,
  `tokenId` VARCHAR(100) NULL,
  `nome` VARCHAR(100) NULL,
  `email` VARCHAR(200) NULL,
  `experiente` TINYINT(1) NULL DEFAULT false,
  PRIMARY KEY (`usuarioId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fiscalize`.`suspeita`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`suspeita` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`suspeita` (
  `suspeitaId` INT NOT NULL AUTO_INCREMENT,
  `notaFiscalId` INT(11) NOT NULL,
  `usuarioId` INT NOT NULL,
  `suspeita` TINYINT(1) NOT NULL DEFAULT false,
  `suspeitaValor` TINYINT(1) NOT NULL DEFAULT false COMMENT 'Valor sob suspeita?',
  `suspeitaObjeto` TINYINT(1) NOT NULL DEFAULT false COMMENT 'Objeto da compra/contratação sob suspeita?',
  `suspeitaFornecedor` TINYINT(1) NOT NULL DEFAULT false COMMENT 'Fornecedor sob suspeita?',
  `comentarios` VARCHAR(2000) NULL,
  `dataIncluida` DATETIME NOT NULL,
  PRIMARY KEY (`suspeitaId`),
  CONSTRAINT `fk_Suspeita_notafiscal1`
    FOREIGN KEY (`notaFiscalId`)
    REFERENCES `fiscalize`.`notafiscal` (`notaFiscalId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Suspeita_Usuario1`
    FOREIGN KEY (`usuarioId`)
    REFERENCES `fiscalize`.`usuario` (`usuarioId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Suspeita_notafiscal1_idx` ON `fiscalize`.`suspeita` (`notaFiscalId` ASC);

CREATE INDEX `fk_Suspeita_Usuario1_idx` ON `fiscalize`.`suspeita` (`usuarioId` ASC);


-- -----------------------------------------------------
-- Table `fiscalize`.`analise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fiscalize`.`analise` ;

CREATE TABLE IF NOT EXISTS `fiscalize`.`analise` (
  `analiseId` INT NOT NULL,
  `notaFiscalId` INT(11) NOT NULL,
  `responsavelUsuarioId` INT NOT NULL,
  `concluida` TINYINT(1) NOT NULL DEFAULT false,
  `comentarios` VARCHAR(2000) NULL,
  PRIMARY KEY (`analiseId`),
  CONSTRAINT `fk_analise_notafiscal1`
    FOREIGN KEY (`notaFiscalId`)
    REFERENCES `fiscalize`.`notafiscal` (`notaFiscalId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_analise_usuario1`
    FOREIGN KEY (`responsavelUsuarioId`)
    REFERENCES `fiscalize`.`usuario` (`usuarioId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_analise_notafiscal1_idx` ON `fiscalize`.`analise` (`notaFiscalId` ASC);

CREATE INDEX `fk_analise_usuario1_idx` ON `fiscalize`.`analise` (`responsavelUsuarioId` ASC);

SET SQL_MODE = '';
GRANT USAGE ON *.* TO fiscalize;
 DROP USER fiscalize;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'fiscalize' IDENTIFIED BY 'fiscalize&890';

GRANT ALL ON `fiscalize`.* TO 'fiscalize';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
