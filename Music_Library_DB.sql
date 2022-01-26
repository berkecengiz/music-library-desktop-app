CREATE DATABASE  IF NOT EXISTS `music_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `music_db`;
--
-- Host: localhost    Database: music_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `album_id` int NOT NULL AUTO_INCREMENT,
  `album_name` varchar(45) NOT NULL,
  `image_id` int DEFAULT NULL,
  `release_id` int NOT NULL,
  `label_id` int NOT NULL,
  `genre_id` int NOT NULL,
  `artist_id` int NOT NULL,
  PRIMARY KEY (`album_id`),
  KEY `album_FK_idx` (`image_id`),
  KEY `album_FK_1_idx` (`release_id`),
  KEY `album_FK_2_idx` (`label_id`),
  KEY `album_FK_3_idx` (`genre_id`),
  KEY `album_FK_4_idx` (`artist_id`),
  CONSTRAINT `album_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`),
  CONSTRAINT `album_FK_1` FOREIGN KEY (`release_id`) REFERENCES `release` (`release_id`),
  CONSTRAINT `album_FK_2` FOREIGN KEY (`label_id`) REFERENCES `label` (`lablel_id`),
  CONSTRAINT `album_FK_3` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `album_FK_4` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (10,'Random Tracks',NULL,5,8,8,1),(11,'RTJ4',8,6,9,3,3),(12,'Future Nostalgia',NULL,6,10,4,4);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `artist_id` int NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(45) NOT NULL,
  `track_count` int DEFAULT NULL,
  `album_count` int DEFAULT NULL,
  `age` int NOT NULL,
  `image_id` int DEFAULT NULL,
  `gender` varchar(6) NOT NULL,
  PRIMARY KEY (`artist_id`),
  KEY `artist_FK_idx` (`image_id`),
  KEY `artist_FK_1_idx` (`gender`),
  CONSTRAINT `artist_FK` FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`),
  CONSTRAINT `artist_FK_1` FOREIGN KEY (`gender`) REFERENCES `gender` (`gender_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'Tyler Gregory',1,1,30,NULL,'Male'),(3,'Run the Jewels',3,1,27,7,'Male'),(4,'Dua Lipa',3,1,26,9,'Female');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `gender_name` varchar(6) NOT NULL,
  PRIMARY KEY (`gender_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES ('Female'),('Male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(45) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Rock'),(2,'Jazz'),(3,'Hip hop music'),(4,'Pop music'),(5,'Folk music'),(6,'Classical music'),(7,'Country music'),(8,'Soul music'),(9,'Disco'),(10,'Trance'),(11,'Opera'),(12,'Sad'),(13,'Romantic'),(14,'Hip hop');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_name` varchar(45) NOT NULL,
  `image_type` varchar(5) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'pic','JPEG'),(2,'pic','JPEG'),(3,'pic','JPEG'),(4,'img','PNG'),(5,'img','PNG'),(6,'alb1','JPEG'),(7,'rtj','JPEG'),(8,'RTJ4','JPEG'),(9,'dl','JPEG');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `includes`
--

DROP TABLE IF EXISTS `includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `includes` (
  `playlist_id` int NOT NULL,
  `track_id` int NOT NULL,
  `album_id` int NOT NULL,
  KEY `includes_FK_idx` (`playlist_id`),
  KEY `includes_FK_1_idx` (`track_id`),
  KEY `includes_FK_2_idx` (`album_id`),
  CONSTRAINT `includes_FK` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE,
  CONSTRAINT `includes_FK_1` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`) ON DELETE CASCADE,
  CONSTRAINT `includes_FK_2` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `includes`
--

LOCK TABLES `includes` WRITE;
/*!40000 ALTER TABLE `includes` DISABLE KEYS */;
INSERT INTO `includes` VALUES (2,2,10),(2,7,12),(2,9,12);
/*!40000 ALTER TABLE `includes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `label` (
  `lablel_id` int NOT NULL AUTO_INCREMENT,
  `label_name` varchar(25) NOT NULL,
  PRIMARY KEY (`lablel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'Columbia'),(2,'Columbia'),(3,'Columbia'),(4,'Columbia'),(5,'Columbia'),(6,'Columbia'),(7,'Columbia'),(8,'Texas'),(9,'BMG'),(10,'Warner');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `language_name` varchar(10) NOT NULL,
  PRIMARY KEY (`language_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('English');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `playlist_id` int NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(45) NOT NULL,
  `total_track` int NOT NULL,
  `total_length` float NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`playlist_id`),
  KEY `playlist_FK_idx` (`user`),
  CONSTRAINT `playlist_FK` FOREIGN KEY (`user`) REFERENCES `user` (`email`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (2,'Random List',4,12.28,'Dummy Description','john12@gmail.com');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release`
--

DROP TABLE IF EXISTS `release`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `release` (
  `release_id` int NOT NULL AUTO_INCREMENT,
  `release_country` varchar(45) NOT NULL,
  `release_year` int NOT NULL,
  PRIMARY KEY (`release_id`),
  KEY `release_FK_idx` (`release_country`),
  CONSTRAINT `release_FK` FOREIGN KEY (`release_country`) REFERENCES `release_country` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release`
--

LOCK TABLES `release` WRITE;
/*!40000 ALTER TABLE `release` DISABLE KEYS */;
INSERT INTO `release` VALUES (1,'America',2021),(3,'America',2019),(4,'America',2018),(5,'America',2017),(6,'America',2020);
/*!40000 ALTER TABLE `release` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release_country`
--

DROP TABLE IF EXISTS `release_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `release_country` (
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_country`
--

LOCK TABLES `release_country` WRITE;
/*!40000 ALTER TABLE `release_country` DISABLE KEYS */;
INSERT INTO `release_country` VALUES ('America'),('Australia'),('Canada'),('Germany'),('Russia');
/*!40000 ALTER TABLE `release_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `reviewer_id` varchar(45) NOT NULL,
  `album_id` int NOT NULL,
  `track_id` int NOT NULL,
  `rating` int NOT NULL,
  `comment` varchar(45) DEFAULT NULL,
  KEY `review_FK_idx` (`reviewer_id`),
  KEY `review_FK_1_idx` (`album_id`),
  KEY `review_FK_2_idx` (`track_id`),
  CONSTRAINT `review_FK` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  CONSTRAINT `review_FK_1` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE,
  CONSTRAINT `review_FK_2` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES ('john12@gmail.com',11,5,4,'Lovely track');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sungby`
--

DROP TABLE IF EXISTS `sungby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sungby` (
  `track_id` int NOT NULL,
  `album_id` int NOT NULL,
  KEY `sung_FK_idx` (`album_id`),
  KEY `sung_FK_1_idx` (`track_id`),
  CONSTRAINT `sung_FK` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE,
  CONSTRAINT `sung_FK_1` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sungby`
--

LOCK TABLES `sungby` WRITE;
/*!40000 ALTER TABLE `sungby` DISABLE KEYS */;
INSERT INTO `sungby` VALUES (2,10),(4,11),(5,11),(6,11),(7,12),(8,12),(9,12);
/*!40000 ALTER TABLE `sungby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `track_id` int NOT NULL AUTO_INCREMENT,
  `track_name` varchar(45) NOT NULL,
  `length` float NOT NULL,
  `genre_id` int NOT NULL,
  `language` varchar(10) NOT NULL,
  PRIMARY KEY (`track_id`),
  KEY `track_FK_idx` (`genre_id`),
  KEY `track_FK_1_idx` (`language`),
  CONSTRAINT `track_FK` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `track_FK_1` FOREIGN KEY (`language`) REFERENCES `language` (`language_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (2,'Money',2.9,1,'English'),(4,'Yankee and the Brave',2.27,3,'English'),(5,'Ooh La La',3.04,14,'English'),(6,'Out of Sight',3.21,3,'English'),(7,'Future Nostalgia',3.05,4,'English'),(8,'Don\'t Start Now',3.03,4,'English'),(9,'Cool',3.3,9,'English');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `profile_image` int DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `user_FK_idx` (`gender`),
  KEY `user_FK_1_idx` (`profile_image`),
  CONSTRAINT `user_FK` FOREIGN KEY (`gender`) REFERENCES `gender` (`gender_name`),
  CONSTRAINT `user_FK_1` FOREIGN KEY (`profile_image`) REFERENCES `image` (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('berke123@gmail.com','Berke','berke12','Male',NULL),('john12@gmail.com','John','john12','Male',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-30 20:06:00
