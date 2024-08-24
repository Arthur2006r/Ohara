-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ohara
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ohara
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ohara` DEFAULT CHARACTER SET utf8 ;
USE `ohara` ;

-- -----------------------------------------------------
-- Table `ohara`.`Avaliador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Avaliador` (
  `idAvaliador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NULL,
  PRIMARY KEY (`idAvaliador`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ohara`.`Manga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Manga` (
  `idManga` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `autor` VARCHAR(45) NOT NULL,
  `sinopse` VARCHAR(500) NOT NULL,
  `capa` VARCHAR(45) NOT NULL,
  `banner` VARCHAR(45) NOT NULL,
  `anoDePublicacao` INT NOT NULL,
  `qtdDeCapitulos` INT NOT NULL,
  `qtdDeVolumes` INT NOT NULL,
  `status` ENUM('Em lançamento', 'Finalizado') NOT NULL,
  PRIMARY KEY (`idManga`),
  UNIQUE INDEX `idManga_UNIQUE` (`idManga` ASC),
  UNIQUE INDEX `titulo_UNIQUE` (`titulo` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Lista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Lista` (
  `idLista` INT NOT NULL AUTO_INCREMENT,
  `idAvaliador` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLista`),
  UNIQUE INDEX `idLista_UNIQUE` (`idLista` ASC),
  INDEX `idAvaliador_idx` (`idAvaliador` ASC),
  CONSTRAINT `fk_Lista_Avaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`ListaManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`ListaManga` (
  `idListaManga` INT NOT NULL AUTO_INCREMENT,
  `idLista` INT NOT NULL,
  `idManga` INT NOT NULL,
  PRIMARY KEY (`idListaManga`, `idLista`, `idManga`),
  UNIQUE INDEX `idListaManga_UNIQUE` (`idListaManga` ASC),
  INDEX `idManga_idx` (`idManga` ASC),
  INDEX `idLista_idx` (`idLista` ASC),
  CONSTRAINT `fk_ListaManga_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ListaManga_Lista`
    FOREIGN KEY (`idLista`)
    REFERENCES `ohara`.`Lista` (`idLista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorAvaliaManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorAvaliaManga` (
  `idAvaliador` INT NOT NULL,
  `idManga` INT NOT NULL,
  `nota` INT NOT NULL,
  PRIMARY KEY (`idAvaliador`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_AvaliadorAvaliaManga_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AvaliadorAvaliaManga_Avaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorComentaManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorComentaManga` (
  `idAvaliadorComentaManga` INT NOT NULL AUTO_INCREMENT,
  `idAvaliador` INT NOT NULL,
  `idManga` INT NOT NULL,
  `descricao` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idAvaliadorComentaManga`),
  UNIQUE INDEX `idAvaliadorComentaManga_UNIQUE` (`idAvaliadorComentaManga` ASC),
  INDEX `idAvaliador_idx` (`idAvaliador` ASC),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_AvaliadorComentaManga_Avaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AvaliadorComentaManga_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorMarcaCurtidaManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorMarcaCurtidaManga` (
  `idAvaliador` INT NOT NULL,
  `idManga` INT NOT NULL,
  PRIMARY KEY (`idAvaliador`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `idAvaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idManga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorMarcaLidoManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorMarcaLidoManga` (
  `idAvaliador` INT NOT NULL,
  `idManga` INT NOT NULL,
  PRIMARY KEY (`idAvaliador`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `idAvaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idManga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorMarcaLerDepoisManga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorMarcaLerDepoisManga` (
  `idAvaliador` INT NOT NULL,
  `idManga` INT NOT NULL,
  PRIMARY KEY (`idAvaliador`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `idManga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAvaliador`
    FOREIGN KEY (`idAvaliador`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ohara`.`AvaliadorSegueAvaliador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`AvaliadorSegueAvaliador` (
  `idAvaliadorSegue` INT NOT NULL,
  `idAvaliadorSeguido` INT NOT NULL,
  PRIMARY KEY (`idAvaliadorSegue`, `idAvaliadorSeguido`),
  INDEX `idAvaliadorSeguido_idx` (`idAvaliadorSeguido` ASC),
  CONSTRAINT `idAvaliadorSegue`
    FOREIGN KEY (`idAvaliadorSegue`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAvaliadorSeguido`
    FOREIGN KEY (`idAvaliadorSeguido`)
    REFERENCES `ohara`.`Avaliador` (`idAvaliador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ohara` ;

-- -----------------------------------------------------
-- Placeholder table for view `ohara`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `ohara`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ohara`.`view1`;
USE `ohara`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
