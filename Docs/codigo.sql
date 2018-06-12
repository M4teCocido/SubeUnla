-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SUBEdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SUBEdb` ;

-- -----------------------------------------------------
-- Schema SUBEdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SUBEdb` DEFAULT CHARACTER SET utf8 ;
USE `SUBEdb` ;

-- -----------------------------------------------------
-- Table `SUBEdb`.`DescuentoSUBE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`DescuentoSUBE` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`DescuentoSUBE` (
  `idDescuento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDescuento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`Persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`Persona` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`Persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `genero` INT NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `email` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`idPersona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`TarjetaSUBE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`TarjetaSUBE` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`TarjetaSUBE` (
  `idTarjetaSube` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `idPropietario` INT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  `activa` BIT(1) NOT NULL DEFAULT true,
  PRIMARY KEY (`idTarjetaSube`),
  INDEX `idPropietario_idx` (`idPropietario` ASC),
  CONSTRAINT `idPropietario`
    FOREIGN KEY (`idPropietario`)
    REFERENCES `SUBEdb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`DescuentoRedSUBE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`DescuentoRedSUBE` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`DescuentoRedSUBE` (
  `idDescuento` INT NOT NULL,
  `idTarjetaSUBE` INT NOT NULL,
  PRIMARY KEY (`idDescuento`),
  INDEX `fk_DescuentoRedSUBE_TarjetaSUBE1_idx` (`idTarjetaSUBE` ASC),
  CONSTRAINT `idDescuento`
    FOREIGN KEY (`idDescuento`)
    REFERENCES `SUBEdb`.`DescuentoSUBE` (`idDescuento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DescuentoRedSUBE_TarjetaSUBE1`
    FOREIGN KEY (`idTarjetaSUBE`)
    REFERENCES `SUBEdb`.`TarjetaSUBE` (`idTarjetaSube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LapsoDescuentoRedSUBE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LapsoDescuentoRedSUBE` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LapsoDescuentoRedSUBE` (
  `idLapso` INT NOT NULL AUTO_INCREMENT,
  `fechaHoraVencimiento` DATETIME NOT NULL,
  `idDescuentoRedSube` INT NOT NULL,
  PRIMARY KEY (`idLapso`),
  INDEX `fk_LapsoDescuentoRedSUBE_DescuentoRedSUBE1_idx` (`idDescuentoRedSube` ASC),
  CONSTRAINT `fk_LapsoDescuentoRedSUBE_DescuentoRedSUBE1`
    FOREIGN KEY (`idDescuentoRedSube`)
    REFERENCES `SUBEdb`.`DescuentoRedSUBE` (`idDescuento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`Lectora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`Lectora` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`Lectora` (
  `idLectora` INT NOT NULL AUTO_INCREMENT,
  `nroSerie` INT NOT NULL,
  PRIMARY KEY (`idLectora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`Fichada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`Fichada` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`Fichada` (
  `idFichada` INT NOT NULL AUTO_INCREMENT,
  `fechaHora` DATETIME NOT NULL,
  `idLapsoDescuentoRedSUBE` INT NULL,
  `idLectora` INT NOT NULL,
  PRIMARY KEY (`idFichada`),
  INDEX `fk_Fichada_LapsoDescuentoRedSUBE1_idx` (`idLapsoDescuentoRedSUBE` ASC),
  INDEX `fk_Fichada_Lectora1_idx` (`idLectora` ASC),
  CONSTRAINT `fk_Fichada_LapsoDescuentoRedSUBE1`
    FOREIGN KEY (`idLapsoDescuentoRedSUBE`)
    REFERENCES `SUBEdb`.`LapsoDescuentoRedSUBE` (`idLapso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fichada_Lectora1`
    FOREIGN KEY (`idLectora`)
    REFERENCES `SUBEdb`.`Lectora` (`idLectora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LineaTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LineaTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LineaTren` (
  `idLinea` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLinea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`EstacionTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`EstacionTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`EstacionTren` (
  `idEstacion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idEstacion`),
  INDEX `fk_EstacionTren_LineaTren1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_EstacionTren_LineaTren1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaTren` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`FichadaTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`FichadaTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`FichadaTren` (
  `idFichada` INT NOT NULL,
  `idEstacion` INT NOT NULL,
  `tipoFichada` INT NOT NULL,
  PRIMARY KEY (`idFichada`),
  INDEX `idEstacion_idx` (`idEstacion` ASC),
  CONSTRAINT `idFichada`
    FOREIGN KEY (`idFichada`)
    REFERENCES `SUBEdb`.`Fichada` (`idFichada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idEstacion`
    FOREIGN KEY (`idEstacion`)
    REFERENCES `SUBEdb`.`EstacionTren` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LineaColectivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LineaColectivo` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LineaColectivo` (
  `idLinea` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLinea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`TramoColectivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`TramoColectivo` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`TramoColectivo` (
  `idTramo` INT NOT NULL AUTO_INCREMENT,
  `precio` DECIMAL(10,2) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idTramo`),
  INDEX `fk_TramoColectivo_LineaColectivo1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_TramoColectivo_LineaColectivo1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaColectivo` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`InternoColectivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`InternoColectivo` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`InternoColectivo` (
  `idinterno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idinterno`),
  INDEX `fk_internoColectivo_LineaColectivo1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_internoColectivo_LineaColectivo1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaColectivo` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`FichadaColectivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`FichadaColectivo` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`FichadaColectivo` (
  `idFichada` INT NOT NULL AUTO_INCREMENT,
  `idTramo` INT NOT NULL,
  `idInterno` INT NOT NULL,
  INDEX `fk_FichadaColectivo_TramoColectivo1_idx` (`idTramo` ASC),
  PRIMARY KEY (`idFichada`),
  INDEX `fk_FichadaColectivo_internoColectivo1_idx` (`idInterno` ASC),
  CONSTRAINT `fk_FichadaColectivo_TramoColectivo1`
    FOREIGN KEY (`idTramo`)
    REFERENCES `SUBEdb`.`TramoColectivo` (`idTramo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FichadaColectivo_Fichada1`
    FOREIGN KEY (`idFichada`)
    REFERENCES `SUBEdb`.`Fichada` (`idFichada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FichadaColectivo_internoColectivo1`
    FOREIGN KEY (`idInterno`)
    REFERENCES `SUBEdb`.`InternoColectivo` (`idinterno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LineaSubte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LineaSubte` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LineaSubte` (
  `idLinea` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `precioViaje` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idLinea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`EstacionSubte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`EstacionSubte` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`EstacionSubte` (
  `idEstacion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idEstacion`),
  INDEX `fk_EstacionSubte_LineaSubte1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_EstacionSubte_LineaSubte1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaSubte` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`FichadaSubte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`FichadaSubte` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`FichadaSubte` (
  `idFichada` INT NOT NULL,
  `idEstacion` INT NOT NULL,
  INDEX `fk_FichadaSubte_EstacionSubte1_idx` (`idEstacion` ASC),
  PRIMARY KEY (`idFichada`),
  CONSTRAINT `fk_FichadaSubte_EstacionSubte1`
    FOREIGN KEY (`idEstacion`)
    REFERENCES `SUBEdb`.`EstacionSubte` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FichadaSubte_Fichada1`
    FOREIGN KEY (`idFichada`)
    REFERENCES `SUBEdb`.`Fichada` (`idFichada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`FichadaRecarga`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`FichadaRecarga` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`FichadaRecarga` (
  `idFichada` INT NOT NULL,
  `monto` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idFichada`),
  CONSTRAINT `fk_FichadaRecarga_Fichada1`
    FOREIGN KEY (`idFichada`)
    REFERENCES `SUBEdb`.`Fichada` (`idFichada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`SeccionTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`SeccionTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`SeccionTren` (
  `idSeccion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `importe` DECIMAL(10,2) NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idSeccion`),
  INDEX `fk_SeccionTren_LineaTren1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_SeccionTren_LineaTren1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaTren` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`ViajeTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`ViajeTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`ViajeTren` (
  `idViaje` INT NOT NULL AUTO_INCREMENT,
  `idSeccion` INT NOT NULL,
  `idEstacionOrigen` INT NOT NULL,
  `idEstacionDestino` INT NOT NULL,
  `idLinea` INT NOT NULL,
  PRIMARY KEY (`idViaje`),
  INDEX `fk_ViajeTren_SeccionTren1_idx` (`idSeccion` ASC),
  INDEX `fk_ViajeTren_EstacionTren1_idx` (`idEstacionOrigen` ASC),
  INDEX `fk_ViajeTren_EstacionTren2_idx` (`idEstacionDestino` ASC),
  INDEX `fk_ViajeTren_LineaTren1_idx` (`idLinea` ASC),
  CONSTRAINT `fk_ViajeTren_SeccionTren1`
    FOREIGN KEY (`idSeccion`)
    REFERENCES `SUBEdb`.`SeccionTren` (`idSeccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ViajeTren_EstacionTren1`
    FOREIGN KEY (`idEstacionOrigen`)
    REFERENCES `SUBEdb`.`EstacionTren` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ViajeTren_EstacionTren2`
    FOREIGN KEY (`idEstacionDestino`)
    REFERENCES `SUBEdb`.`EstacionTren` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ViajeTren_LineaTren1`
    FOREIGN KEY (`idLinea`)
    REFERENCES `SUBEdb`.`LineaTren` (`idLinea`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`TransaccionSUBE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`TransaccionSUBE` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`TransaccionSUBE` (
  `idTransaccion` INT NOT NULL,
  `importe` DECIMAL(10,2) NOT NULL,
  `idTarjetaSube` INT NOT NULL,
  INDEX `fk_TransaccionSUBE_TarjetaSUBE1_idx` (`idTarjetaSube` ASC),
  PRIMARY KEY (`idTransaccion`),
  CONSTRAINT `fk_TransaccionSUBE_TarjetaSUBE1`
    FOREIGN KEY (`idTarjetaSube`)
    REFERENCES `SUBEdb`.`TarjetaSUBE` (`idTarjetaSube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TransaccionSUBE_Fichada1`
    FOREIGN KEY (`idTransaccion`)
    REFERENCES `SUBEdb`.`Fichada` (`idFichada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`DescuentoBoletoEstudiantil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`DescuentoBoletoEstudiantil` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`DescuentoBoletoEstudiantil` (
  `idDescuento` INT NOT NULL,
  `descuento` DECIMAL(10,2) NOT NULL,
  `viajesRestantes` INT NOT NULL,
  `tipoBoleto` INT NOT NULL,
  `idPersona` INT NOT NULL,
  PRIMARY KEY (`idDescuento`),
  INDEX `fk_DescuentoBoletoEstudiantil_Persona1_idx` (`idPersona` ASC),
  CONSTRAINT `fk_DescuentoBoletoEstudiantil_DescuentoSUBE1`
    FOREIGN KEY (`idDescuento`)
    REFERENCES `SUBEdb`.`DescuentoSUBE` (`idDescuento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DescuentoBoletoEstudiantil_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `SUBEdb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`DescuentoTarifaSocial`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`DescuentoTarifaSocial` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`DescuentoTarifaSocial` (
  `idDescuento` INT NOT NULL,
  `descuento` DECIMAL(10,2) NOT NULL,
  `idPersona` INT NOT NULL,
  PRIMARY KEY (`idDescuento`),
  INDEX `fk_DescuentoTarifaSocial_Persona1_idx` (`idPersona` ASC),
  CONSTRAINT `fk_DescuentoTarifaSocial_DescuentoSUBE1`
    FOREIGN KEY (`idDescuento`)
    REFERENCES `SUBEdb`.`DescuentoSUBE` (`idDescuento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DescuentoTarifaSocial_Persona1`
    FOREIGN KEY (`idPersona`)
    REFERENCES `SUBEdb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`DocumentoPersona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`DocumentoPersona` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`DocumentoPersona` (
  `idDocumento` INT NOT NULL AUTO_INCREMENT,
  `numero` INT(4) NOT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`idDocumento`),
  CONSTRAINT `fk_DocumentoPersona_Persona1`
    FOREIGN KEY (`idDocumento`)
    REFERENCES `SUBEdb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `idPersona` INT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `idPersona_idx` (`idPersona` ASC),
  CONSTRAINT `idPersona`
    FOREIGN KEY (`idPersona`)
    REFERENCES `SUBEdb`.`Persona` (`idPersona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`Permiso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`Permiso` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`Permiso` (
  `idPermiso` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPermiso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`PermisoPersona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`PermisoPersona` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`PermisoPersona` (
  `Permiso_idPermiso` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`Permiso_idPermiso`, `Usuario_idUsuario`),
  INDEX `fk_Permiso_has_Usuario_Usuario1_idx` (`Usuario_idUsuario` ASC),
  INDEX `fk_Permiso_has_Usuario_Permiso1_idx` (`Permiso_idPermiso` ASC),
  CONSTRAINT `fk_Permiso_has_Usuario_Permiso1`
    FOREIGN KEY (`Permiso_idPermiso`)
    REFERENCES `SUBEdb`.`Permiso` (`idPermiso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Permiso_has_Usuario_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `SUBEdb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LectoraSubte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LectoraSubte` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LectoraSubte` (
  `idLectora` INT NOT NULL,
  `idEstacion` INT NOT NULL,
  PRIMARY KEY (`idLectora`, `idEstacion`),
  INDEX `fk_LectoraSubte_Lectora1_idx` (`idLectora` ASC),
  CONSTRAINT `fk_LectoraSubte_EstacionSubte1`
    FOREIGN KEY (`idEstacion`)
    REFERENCES `SUBEdb`.`EstacionSubte` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LectoraSubte_Lectora1`
    FOREIGN KEY (`idLectora`)
    REFERENCES `SUBEdb`.`Lectora` (`idLectora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LectoraColectivo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LectoraColectivo` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LectoraColectivo` (
  `idLectora` INT NOT NULL,
  `idInterno` INT NOT NULL,
  PRIMARY KEY (`idLectora`),
  INDEX `fk_LectoraColectvo_InternoColectivo1_idx` (`idInterno` ASC),
  CONSTRAINT `fk_LectoraColectvo_Lectora1`
    FOREIGN KEY (`idLectora`)
    REFERENCES `SUBEdb`.`Lectora` (`idLectora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LectoraColectvo_InternoColectivo1`
    FOREIGN KEY (`idInterno`)
    REFERENCES `SUBEdb`.`InternoColectivo` (`idinterno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LectoraTren`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LectoraTren` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LectoraTren` (
  `idLectora` INT NOT NULL,
  `idEstacion` INT NOT NULL,
  `esEntrada` BIT(1) NOT NULL,
  PRIMARY KEY (`idLectora`, `idEstacion`),
  INDEX `fk_LectoraTren_EstacionTren1_idx` (`idEstacion` ASC),
  CONSTRAINT `fk_LectoraTren_Lectora1`
    FOREIGN KEY (`idLectora`)
    REFERENCES `SUBEdb`.`Lectora` (`idLectora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LectoraTren_EstacionTren1`
    FOREIGN KEY (`idEstacion`)
    REFERENCES `SUBEdb`.`EstacionTren` (`idEstacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SUBEdb`.`LectoraExterna`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SUBEdb`.`LectoraExterna` ;

CREATE TABLE IF NOT EXISTS `SUBEdb`.`LectoraExterna` (
  `idLectora` INT NOT NULL,
  `ubicacion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLectora`),
  INDEX `fk_LectoraExterna_Lectora1_idx` (`idLectora` ASC),
  CONSTRAINT `fk_LectoraExterna_Lectora1`
    FOREIGN KEY (`idLectora`)
    REFERENCES `SUBEdb`.`Lectora` (`idLectora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
