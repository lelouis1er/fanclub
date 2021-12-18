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


LOCK TABLES `TypeOperation` WRITE;
/*!40000 ALTER TABLE `TypeOperation` DISABLE KEYS */;
INSERT INTO `TypeOperation` VALUES (1,'Entrée'),(2,'Sortie');
/*!40000 ALTER TABLE `TypeOperation` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `ModelOperation`
--

LOCK TABLES `ModelOperation` WRITE;
/*!40000 ALTER TABLE `ModelOperation` DISABLE KEYS */;
INSERT INTO `ModelOperation` VALUES (1,1,'Inscription'),(2,1,'Contribution'),(3,1,'Don'),(4,2,'Achat de maillots'),(5,2,'Location de stade'),(6,2,'Restauration');
/*!40000 ALTER TABLE `ModelOperation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PrivilegesUtilisateur`
--

--
-- Dumping data for table `RestrictionPrivilege`
--

LOCK TABLES `RestrictionPrivilege` WRITE;
/*!40000 ALTER TABLE `RestrictionPrivilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `RestrictionPrivilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RolePrivilege`
--

--
-- Dumping data for table `RoleUtilisateur`
--

LOCK TABLES `RoleUtilisateur` WRITE;
/*!40000 ALTER TABLE `RoleUtilisateur` DISABLE KEYS */;
INSERT INTO `RoleUtilisateur` VALUES (1,'Super admin','su');
/*!40000 ALTER TABLE `RoleUtilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TypeMembre`
--

LOCK TABLES `TypeMembre` WRITE;
/*!40000 ALTER TABLE `TypeMembre` DISABLE KEYS */;
INSERT INTO `TypeMembre` VALUES (1,'Membre'),(2,'Client'),(3,'Fournisseur'),(4,'Donateur');
/*!40000 ALTER TABLE `TypeMembre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TypeOperation`
--

--
-- Dumping data for table `Utilisateur`
--

LOCK TABLES `Utilisateur` WRITE;
/*!40000 ALTER TABLE `Utilisateur` DISABLE KEYS */;
INSERT INTO `Utilisateur` VALUES (1,1,NULL,'superadmin','$2a$12$hPX.X5gfFPKGySicqt.hk.wyIrnFX1EZBgyY/3v0zRrsAM5wFFiM.','Lafortune Kadjo','super utilisateur du système',1,0,'su.png',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-18 13:43:52
