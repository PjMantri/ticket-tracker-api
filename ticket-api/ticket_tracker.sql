-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ticket_tracker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ticket_tracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ticket_tracker` DEFAULT CHARACTER SET utf8 ;
USE `ticket_tracker` ;

-- -----------------------------------------------------
-- Table `ticket_tracker`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket_tracker`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  `email` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  `type` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`user_id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ticket_tracker`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket_tracker`.`ticket` (
  `ticket_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `status` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  `area` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(45) CHARACTER SET 'latin1' NULL DEFAULT NULL COMMENT '',
  `customer` INT(11) NULL DEFAULT NULL COMMENT '',
  `created_by` INT(11) NULL DEFAULT NULL COMMENT '',
  `assigned_to` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`ticket_id`)  COMMENT '',
  INDEX `customer_idx` (`customer` ASC)  COMMENT '',
  INDEX `created_by_idx` (`created_by` ASC)  COMMENT '',
  INDEX `assigned_to_idx` (`assigned_to` ASC)  COMMENT '',
  CONSTRAINT `assigned_to`
    FOREIGN KEY (`assigned_to`)
    REFERENCES `ticket_tracker`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `ticket_tracker`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer`
    FOREIGN KEY (`customer`)
    REFERENCES `ticket_tracker`.`users` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
