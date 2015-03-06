SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `cotas` DEFAULT CHARACTER SET utf8 ;
USE `cotas` ;

-- -----------------------------------------------------
-- Table `cotas`.`Cota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cotas`.`Cota` (
  `cotaId` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cotaId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotas`.`Partido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cotas`.`Partido` (
  `partidoId` INT(11) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`partidoId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cotas`.`Parlamentar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cotas`.`Parlamentar` (
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
    REFERENCES `cotas`.`Partido` (`partidoId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Parlamentar_Partido1_idx` ON `cotas`.`Parlamentar` (`partidoId` ASC);


-- -----------------------------------------------------
-- Table `cotas`.`Uf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cotas`.`Uf` (
  `ufId` INT(11) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(2) NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`ufId`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `sigla_UNIQUE` ON `cotas`.`Uf` (`sigla` ASC);


-- -----------------------------------------------------
-- Table `cotas`.`NotaFiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cotas`.`NotaFiscal` (
  `notaFiscalId` INT(11) NOT NULL AUTO_INCREMENT,
  `parlamentarId` INT(11) NOT NULL,
  `cotaId` INT(11) NOT NULL,
  `ufId` INT(11) NOT NULL,
  `dataEmissao` DATETIME NULL,
  `descricao` VARCHAR(500) NULL,
  `descricaoSubCota` VARCHAR(500) NULL,
  `fornecedor` VARCHAR(500) NULL,
  `cpfCnpj` VARCHAR(45) NULL,
  `ano` INT(11) NOT NULL,
  `mes` INT(11) NOT NULL,
  `numeroDocumento` VARCHAR(45) NULL,
  `parcela` INT(11) NULL,
  `tipoDocumentoFiscal` INT(11) NOT NULL COMMENT 'Nota Fiscal - 0\nRecibo - 1\nDespesa Exterior - 2\n',
  `nomePassageiro` VARCHAR(500) NULL,
  `trechoViagem` VARCHAR(500) NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `valorGlosa` DECIMAL(10,2) NULL,
  `valorLiquido` DECIMAL(10,2) NULL,
  PRIMARY KEY (`notaFiscalId`),
  CONSTRAINT `fk_NotaFiscal_Parlamentar1`
    FOREIGN KEY (`parlamentarId`)
    REFERENCES `cotas`.`Parlamentar` (`parlamentarId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NotaFiscal_Cota1`
    FOREIGN KEY (`cotaId`)
    REFERENCES `cotas`.`Cota` (`cotaId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notafiscal_Uf1`
    FOREIGN KEY (`ufId`)
    REFERENCES `cotas`.`Uf` (`ufId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_NotaFiscal_Parlamentar1_idx` ON `cotas`.`NotaFiscal` (`parlamentarId` ASC);

CREATE INDEX `fk_NotaFiscal_Cota1_idx` ON `cotas`.`NotaFiscal` (`cotaId` ASC);

CREATE INDEX `fk_notafiscal_Uf1_idx` ON `cotas`.`NotaFiscal` (`ufId` ASC);

GRANT ALL ON `cotas`.* TO 'cotas';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
