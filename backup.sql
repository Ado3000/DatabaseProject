-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: movies
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (440925,'Micheal Douglas'),(460706,'Sylvester Stallone'),(470730,'Arnold Schwarzenegger'),(610403,'Eddie Murpy'),(620703,'Tom Cruise'),(660814,'Hale Berry'),(670718,'Vin Diesel'),(700429,'Uma Thurman'),(750604,'Angelina Jolie'),(900815,'Jennifer Lawrence');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director` (
  `id` int(11) NOT NULL,
  `Name` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (280726,'Stanley Kubrick'),(440411,'John Milius'),(460915,'Oliver Stone'),(461218,'Steven Spielberg'),(540301,'Ron Howard');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directs`
--

DROP TABLE IF EXISTS `directs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directs` (
  `director_id` int(11) NOT NULL,
  `movie_name` varchar(45) NOT NULL,
  PRIMARY KEY (`director_id`,`movie_name`),
  KEY `movie_name_idx` (`movie_name`),
  CONSTRAINT `directs_ibfk_1` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `directs_ibfk_2` FOREIGN KEY (`movie_name`) REFERENCES `movie` (`Title`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directs`
--

LOCK TABLES `directs` WRITE;
/*!40000 ALTER TABLE `directs` DISABLE KEYS */;
INSERT INTO `directs` VALUES (440411,'Conan the barbarian'),(461218,'E.T'),(280726,'Eyes wide shut'),(461218,'Jurassic Park');
/*!40000 ALTER TABLE `directs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Comedy'),(3,'Horror'),(4,'Drama'),(5,'Fantasy');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `istypeof`
--

DROP TABLE IF EXISTS `istypeof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `istypeof` (
  `genre_id` int(11) NOT NULL,
  `movie_namel` varchar(45) NOT NULL,
  PRIMARY KEY (`genre_id`,`movie_namel`),
  KEY `movie_name_idx` (`movie_namel`),
  CONSTRAINT `istypeoof_ibfk_1` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `istypeoof_ibfk_2` FOREIGN KEY (`movie_namel`) REFERENCES `movie` (`Title`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `istypeof`
--

LOCK TABLES `istypeof` WRITE;
/*!40000 ALTER TABLE `istypeof` DISABLE KEYS */;
INSERT INTO `istypeof` VALUES (5,'Conan the barbarian'),(1,'Demolition Man'),(2,'The Nutty Proffesor'),(1,'The Terminator');
/*!40000 ALTER TABLE `istypeof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `Title` varchar(45) NOT NULL,
  `Year` int(11) NOT NULL,
  `Runningtime` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Title`,`Year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('Coctail',1988,'104','Have not seen it'),('Conan the barbarian',1982,'209','One of Arnolds best movies'),('Demolition Man',1993,'115','Good Action Movie'),('E.T',1982,'115','Seen it but dont remember'),('Eyes wide shut',1999,'159','Strange movie'),('Jurassic Park',1993,'127','Movie with cool effects'),('Red Heat',1988,'104','Russian Arnold'),('The Nutty Proffesor',1996,'95','Very funny movie'),('The Terminator',1984,'107','Great movie');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `startsin`
--

DROP TABLE IF EXISTS `startsin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `startsin` (
  `actor_id` int(11) NOT NULL,
  `movie_name` varchar(45) NOT NULL,
  PRIMARY KEY (`actor_id`,`movie_name`),
  KEY `movie_name_idx` (`movie_name`),
  CONSTRAINT `actor_id` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `movie_name` FOREIGN KEY (`movie_name`) REFERENCES `movie` (`Title`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `startsin`
--

LOCK TABLES `startsin` WRITE;
/*!40000 ALTER TABLE `startsin` DISABLE KEYS */;
INSERT INTO `startsin` VALUES (620703,'Coctail'),(470730,'Conan the barbarian'),(460706,'Demolition Man'),(470730,'Red Heat'),(470730,'The Terminator');
/*!40000 ALTER TABLE `startsin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-21 19:08:25
