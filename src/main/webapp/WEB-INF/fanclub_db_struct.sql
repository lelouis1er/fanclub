CREATE DATABASE  IF NOT EXISTS `fanclub_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fanclub_db`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: fanclub_db
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.21.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `FanClub`
--

DROP TABLE IF EXISTS `FanClub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FanClub` (
  `idFanclub` int NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `logo` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `dateCreation` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `siteweb` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFanclub`),
  UNIQUE KEY `FANCLUB_PK` (`idFanclub`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Membre`
--

DROP TABLE IF EXISTS `Membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Membre` (
  `idmembre` int NOT NULL,
  `idTypeMembre` int NOT NULL,
  `idFanclub` int DEFAULT NULL,
  `matricule` varchar(254) DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `prenom` varchar(254) DEFAULT NULL,
  `tel` varchar(254) DEFAULT NULL,
  `ddn` timestamp NULL DEFAULT NULL,
  `ldn` varchar(254) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `cni` varchar(254) DEFAULT NULL,
  `pssd` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idmembre`),
  UNIQUE KEY `MEMBRE_PK` (`idmembre`),
  KEY `ASSOCIATION10_FK` (`idTypeMembre`),
  KEY `ASSOCIATION11_FK` (`idFanclub`),
  CONSTRAINT `FK_MEMBRE_ASSOCIATI_FANCLUB` FOREIGN KEY (`idFanclub`) REFERENCES `FanClub` (`idFanclub`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_MEMBRE_ASSOCIATI_TYPEMEMB` FOREIGN KEY (`idTypeMembre`) REFERENCES `TypeMembre` (`idTypeMembre`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Menu` (
  `idmenu` int NOT NULL,
  `Men_idmenu` int DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `ressource` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idmenu`),
  UNIQUE KEY `MENU_PK` (`idmenu`),
  KEY `ASSOCIATION56_FK` (`Men_idmenu`),
  CONSTRAINT `FK_MENU_ASSOCIATI_MENU` FOREIGN KEY (`Men_idmenu`) REFERENCES `Menu` (`idmenu`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ModelOperation`
--

DROP TABLE IF EXISTS `ModelOperation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ModelOperation` (
  `idModel` int NOT NULL,
  `idType` int NOT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idModel`),
  UNIQUE KEY `MODELOPERATION_PK` (`idModel`),
  KEY `ASSOCIATION15_FK` (`idType`),
  CONSTRAINT `FK_MODELOPE_ASSOCIATI_TYPEOPER` FOREIGN KEY (`idType`) REFERENCES `TypeOperation` (`idType`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Mouchard`
--

DROP TABLE IF EXISTS `Mouchard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Mouchard` (
  `id_mouchard` int NOT NULL,
  `idUtilisateur` int NOT NULL,
  `description` varchar(254) DEFAULT NULL,
  `date_operation` timestamp NULL DEFAULT NULL,
  `heure_operation` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_mouchard`),
  UNIQUE KEY `MOUCHARD_PK` (`id_mouchard`),
  KEY `ASSOCIATION_49_FK` (`idUtilisateur`),
  CONSTRAINT `FK_MOUCHARD_ASSOCIATI_UTILISAT` FOREIGN KEY (`idUtilisateur`) REFERENCES `Utilisateur` (`idUtilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Operation`
--

DROP TABLE IF EXISTS `Operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Operation` (
  `idOperation` bigint NOT NULL,
  `idModel` int NOT NULL,
  `idSession` int NOT NULL,
  `idmembre` int NOT NULL,
  `dateOperation` timestamp NULL DEFAULT NULL,
  `montant` float DEFAULT NULL,
  `descriptionOp` VARCHAR(200) NULL,
  PRIMARY KEY (`idOperation`),
  UNIQUE KEY `OPERATION_PK` (`idOperation`),
  KEY `ASSOCIATION13_FK` (`idmembre`),
  KEY `ASSOCIATION14_FK` (`idModel`),
  KEY `ASSOCIATION16_FK` (`idSession`),
  CONSTRAINT `FK_OPERATIO_ASSOCIATI_MEMBRE` FOREIGN KEY (`idmembre`) REFERENCES `Membre` (`idmembre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_OPERATIO_ASSOCIATI_MODELOPE` FOREIGN KEY (`idModel`) REFERENCES `ModelOperation` (`idModel`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_OPERATIO_ASSOCIATI_SESSION` FOREIGN KEY (`idSession`) REFERENCES `SessionOp` (`idSession`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PrivilegesUtilisateur`
--

DROP TABLE IF EXISTS `PrivilegesUtilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PrivilegesUtilisateur` (
  `idprivilege` int NOT NULL,
  `idmenu` int NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `niveau` int DEFAULT NULL,
  PRIMARY KEY (`idprivilege`),
  UNIQUE KEY `PRIVILEGESUTILISATEUR_PK` (`idprivilege`),
  KEY `ASSOCIATION3_FK` (`idmenu`),
  CONSTRAINT `FK_PRIVILEG_ASSOCIATI_MENU` FOREIGN KEY (`idmenu`) REFERENCES `Menu` (`idmenu`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RestrictionPrivilege`
--

DROP TABLE IF EXISTS `RestrictionPrivilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RestrictionPrivilege` (
  `idrestriction` int NOT NULL,
  `idUtilisateur` int NOT NULL,
  `idprivilege` int NOT NULL,
  `restrictUp` tinyint DEFAULT NULL,
  `canCreate` tinyint DEFAULT NULL,
  `canUpdate` tinyint DEFAULT NULL,
  `canDelete` tinyint DEFAULT NULL,
  PRIMARY KEY (`idrestriction`),
  UNIQUE KEY `RESTRICTIONPRIVILEGE_PK` (`idrestriction`),
  KEY `ASSOCIATION4_FK` (`idUtilisateur`),
  KEY `ASSOCIATION5_FK` (`idprivilege`),
  CONSTRAINT `FK_RESTRICT_ASSOCIATI_PRIVILEG` FOREIGN KEY (`idprivilege`) REFERENCES `PrivilegesUtilisateur` (`idprivilege`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RESTRICT_ASSOCIATI_UTILISAT` FOREIGN KEY (`idUtilisateur`) REFERENCES `Utilisateur` (`idUtilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RolePrivilege`
--

DROP TABLE IF EXISTS `RolePrivilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RolePrivilege` (
  `idroleprivilege` int NOT NULL,
  `idprivilege` int NOT NULL,
  `idrole` int NOT NULL,
  `canCreate` tinyint DEFAULT NULL,
  `canUpdate` tinyint DEFAULT NULL,
  `canDelete` tinyint DEFAULT NULL,
  PRIMARY KEY (`idroleprivilege`),
  UNIQUE KEY `ROLEPRIVILEGE_PK` (`idroleprivilege`),
  KEY `ASSOCIATION6_FK` (`idrole`),
  KEY `ASSOCIATION7_FK` (`idprivilege`),
  CONSTRAINT `FK_ROLEPRIV_ASSOCIATI_PRIVILEG` FOREIGN KEY (`idprivilege`) REFERENCES `PrivilegesUtilisateur` (`idprivilege`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROLEPRIV_ASSOCIATI_ROLEUTIL` FOREIGN KEY (`idrole`) REFERENCES `RoleUtilisateur` (`idrole`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoleUtilisateur`
--

DROP TABLE IF EXISTS `RoleUtilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RoleUtilisateur` (
  `idrole` int NOT NULL,
  `nomrole` varchar(254) DEFAULT NULL,
  `coderole` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idrole`),
  UNIQUE KEY `ROLEUTILISATEUR_PK` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SessionOp`
--

DROP TABLE IF EXISTS `SessionOp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SessionOp` (
  `idSession` int NOT NULL,
  `idFanclub` int DEFAULT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` datetime DEFAULT NULL,
  PRIMARY KEY (`idSession`),
  UNIQUE KEY `SESSION_PK` (`idSession`),
  KEY `ASSOCIATION12_FK` (`idFanclub`),
  CONSTRAINT `FK_SESSION_ASSOCIATI_FANCLUB` FOREIGN KEY (`idFanclub`) REFERENCES `FanClub` (`idFanclub`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TypeMembre`
--

DROP TABLE IF EXISTS `TypeMembre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TypeMembre` (
  `idTypeMembre` int NOT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idTypeMembre`),
  UNIQUE KEY `TYPEMEMBRE_PK` (`idTypeMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TypeOperation`
--

DROP TABLE IF EXISTS `TypeOperation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TypeOperation` (
  `idType` int NOT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idType`),
  UNIQUE KEY `TYPEOPERATION_PK` (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Utilisateur`
--

DROP TABLE IF EXISTS `Utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utilisateur` (
  `idUtilisateur` int NOT NULL,
  `idrole` int NOT NULL,
  `idmembre` int DEFAULT NULL,
  `login` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `actif` smallint DEFAULT NULL,
  `deleted` smallint DEFAULT NULL,
  `photo` varchar(254) DEFAULT NULL,
  `dateCreation` timestamp NULL DEFAULT NULL,
  `dateDelete` timestamp NULL DEFAULT NULL,
  `dateDesactivation` timestamp NULL DEFAULT NULL,
  `dateActivation` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE KEY `UTILISATEUR_PK` (`idUtilisateur`),
  KEY `ASSOCIATION1_FK` (`idrole`),
  KEY `ASSOCIATION17_FK` (`idmembre`),
  CONSTRAINT `FK_UTILISAT_ASSOCIATI_MEMBRE` FOREIGN KEY (`idmembre`) REFERENCES `Membre` (`idmembre`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UTILISAT_ASSOCIATI_ROLEUTIL` FOREIGN KEY (`idrole`) REFERENCES `RoleUtilisateur` (`idrole`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-18 13:40:20
