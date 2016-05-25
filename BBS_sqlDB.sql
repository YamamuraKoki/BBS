SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `login_id` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(10) NOT NULL ,
  `branch` INT NOT NULL ,
  `position` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`branches`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`branches` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `branch_number` INT NOT NULL ,
  `branch_name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`positions`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`positions` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `position_number` INT NOT NULL ,
  `position_name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`articles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`articles` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `article_title` VARCHAR(50) NOT NULL ,
  `article_text` VARCHAR(1000) NOT NULL ,
  `article_category` VARCHAR(10) NOT NULL ,
  `insert_date` DATE NOT NULL ,
  `update_date` DATE NOT NULL ,
  `article_user` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comments`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `comment_text` VARCHAR(500) NOT NULL ,
  `insert_date` DATE NOT NULL ,
  `update_date` DATE NOT NULL ,
  `comment_user` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
