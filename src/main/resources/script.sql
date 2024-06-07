-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema m295
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `m295` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

USE `m295` ;

-- -----------------------------------------------------
-- Table `m295`.`trips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m295`.`trips` (
                                              `tripId` INT NOT NULL AUTO_INCREMENT,
                                              `destination` VARCHAR(64) NULL DEFAULT NULL,
    PRIMARY KEY (`tripId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `m295`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m295`.`reviews` (
                                                `reviewId` INT NOT NULL AUTO_INCREMENT,
                                                `createdAt` DATETIME(6) NULL DEFAULT NULL,
    `rating` DOUBLE NOT NULL,
    `recommend` BIT(1) NOT NULL,
    `review` VARCHAR(64) NULL DEFAULT NULL,
    `wordCount` INT NOT NULL,
    `tripId` INT NULL DEFAULT NULL,
    PRIMARY KEY (`reviewId`),
    INDEX `FK4vv9m2ygumhackv4rot73uai` (`tripId` ASC) VISIBLE,
    CONSTRAINT `FK4vv9m2ygumhackv4rot73uai`
    FOREIGN KEY (`tripId`)
    REFERENCES `m295`.`trips` (`tripId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
