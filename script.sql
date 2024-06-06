-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Trip` (
                                             `TripID` INT NOT NULL,
                                             `Destination` VARCHAR(45) NULL,
    `Start` DATE NULL,
    `End` DATE NULL,
    `Price` DOUBLE NULL,
    `Available` BIT NULL,
    `Organizer` VARCHAR(45) NULL,
    `Passengers` INT NULL,
    PRIMARY KEY (`TripID`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Review` (
                                               `ReviewID` INT NOT NULL,
                                               `Trip_TripID` INT NOT NULL,
                                               `Review` VARCHAR(45) NULL,
    PRIMARY KEY (`ReviewID`, `Trip_TripID`),
    INDEX `fk_Rating_Trip_idx` (`Trip_TripID` ASC) VISIBLE,
    CONSTRAINT `fk_Rating_Trip`
    FOREIGN KEY (`Trip_TripID`)
    REFERENCES `mydb`.`Trip` (`TripID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
