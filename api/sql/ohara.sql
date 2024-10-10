DROP DATABASE IF EXISTS ohara;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ohara
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ohara` DEFAULT CHARACTER SET utf8;
USE `ohara`;

-- -----------------------------------------------------
-- Table `ohara`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Usuario` (
  `idUsuario` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Manga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Manga` (
  `idManga` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(500) NOT NULL,
  `autor` VARCHAR(255) NOT NULL,
  `sinopse` TEXT NOT NULL,
  `capa` VARCHAR(255) NOT NULL,
  `anoDePublicacao` YEAR NOT NULL,
  `qtdDeCapitulos` INT NOT NULL,
  `qtdDeVolumes` INT NOT NULL,
  `popularidade` INT NOT NULL,
  `status` ENUM('Em lançamento', 'Finalizado', 'Cancelado', 'Em hiato') NOT NULL,
  PRIMARY KEY (`idManga`),
  UNIQUE INDEX `titulo_UNIQUE` (`titulo` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Lista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Lista` (
  `idLista` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idLista`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Avaliacao` (
  `idUsuario` BIGINT NOT NULL,
  `idManga` BIGINT NOT NULL,
  `nota` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_Avaliacao_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Avaliacao_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Review` (
  `idReview` BIGINT NOT NULL AUTO_INCREMENT,
  `idUsuario` BIGINT NOT NULL,
  `idManga` BIGINT NOT NULL,
  `descricao` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idReview`),
  INDEX `idUsuario_idx` (`idUsuario` ASC),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_Review_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Review_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Curtida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Curtida` (
  `idUsuario` BIGINT NOT NULL,
  `idManga` BIGINT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_Curtida_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curtida_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`Lido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`Lido` (
  `idUsuario` BIGINT NOT NULL,
  `idManga` BIGINT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_Lido_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lido_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`LerDepois`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`LerDepois` (
  `idUsuario` BIGINT NOT NULL,
  `idManga` BIGINT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idManga`),
  INDEX `idManga_idx` (`idManga` ASC),
  CONSTRAINT `fk_LerDepois_Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LerDepois_Manga`
    FOREIGN KEY (`idManga`)
    REFERENCES `ohara`.`Manga` (`idManga`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ohara`.`UsuarioSegueUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ohara`.`UsuarioSegueUsuario` (
  `idUsuarioSegue` BIGINT NOT NULL,
  `idUsuarioSeguido` BIGINT NOT NULL,
  PRIMARY KEY (`idUsuarioSegue`, `idUsuarioSeguido`),
  INDEX `idUsuarioSeguido_idx` (`idUsuarioSeguido` ASC),
  CONSTRAINT `fk_UsuarioSegueUsuario_Seguido`
    FOREIGN KEY (`idUsuarioSeguido`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UsuarioSegueUsuario_Seguindo`
    FOREIGN KEY (`idUsuarioSegue`)
    REFERENCES `ohara`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
