-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: examdb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anwers`
--

DROP TABLE IF EXISTS `anwers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `anwers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `is_correct` bit(1) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo4e6yvx9isivn9uokvr8be0l6` (`question_id`),
  CONSTRAINT `FKo4e6yvx9isivn9uokvr8be0l6` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anwers`
--

LOCK TABLES `anwers` WRITE;
/*!40000 ALTER TABLE `anwers` DISABLE KEYS */;
INSERT INTO `anwers` VALUES (1,'10',_binary '\0',1),(2,'12',_binary '',1),(3,'15',_binary '\0',1),(4,'8',_binary '\0',1),(6,'5',_binary '\0',2),(7,'6',_binary '',2),(8,'7',_binary '\0',2),(9,'10',_binary '\0',3),(10,'12',_binary '',3),(11,'16',_binary '\0',3),(12,'9',_binary '\0',3),(13,'7',_binary '\0',4),(14,'8',_binary '\0',4),(15,'9',_binary '\0',4),(24,'600',_binary '\0',7),(25,'1000',_binary '\0',7),(26,'60',_binary '\0',7),(27,'3600',_binary '',7),(28,'Không có phép nào được thực hiện trước',_binary '\0',8),(29,'Phép nhân',_binary '',8),(30,'Tùy thuộc vào quy tắc ưu tiên',_binary '\0',8),(31,'Phép cộng',_binary '\0',8),(32,'1001',_binary '',9),(33,'1100',_binary '\0',9),(34,'1111',_binary '\0',9),(35,'1010',_binary '\0',9),(36,'125',_binary '',10),(37,'15',_binary '\0',10),(38,'625',_binary '\0',10),(39,'75',_binary '\0',10),(40,'3',_binary '\0',11),(41,'4',_binary '',11),(42,'5',_binary '\0',11),(43,'6',_binary '\0',11),(44,'60',_binary '\0',12),(45,'50',_binary '',12),(46,'40',_binary '\0',12),(47,'30',_binary '\0',12),(48,'12',_binary '',13),(49,'6',_binary '\0',13),(50,'18',_binary '\0',13),(51,'8',_binary '\0',13),(52,'F = ma',_binary '\0',14),(53,'a = F/m',_binary '\0',14),(54,'v = d/t',_binary '',14),(55,'E = mc^2',_binary '\0',14),(56,'Newton (N)',_binary '\0',15),(57,'Mét (m)',_binary '\0',15),(58,'Giây (s)',_binary '',15),(59,'Kilogram (kg)',_binary '\0',15),(60,'Khối lượng chia cho thể tích',_binary '\0',16),(61,'Tốc độ thay đổi thời gian',_binary '\0',16),(62,'Lực chia cho diện tích',_binary '',16),(63,'Năng lượng chia cho cấp số nhân của ánh sáng',_binary '\0',16),(64,'50,000 m/s',_binary '\0',17),(65,'299,792,458 m/s',_binary '',17),(66,'100 m/s',_binary '\0',17),(67,'1,000,000 m/s',_binary '\0',17),(68,'F = ma',_binary '\0',18),(69,'a = F/m',_binary '\0',18),(70,'K = (1/2)mv^2',_binary '',18),(71,'E = mc^2',_binary '\0',18),(72,'Làm một công bằng không',_binary '\0',19),(73,'Không làm công nào cả',_binary '',19),(74,'System.out.println(\"Hello World\");',_binary '',20),(75,'Console.WriteLine(\"Hello World\");',_binary '\0',20),(76,'logger.info(\"Hello World\");',_binary '',20),(77,'echo(\"Hello World\");',_binary '\0',20),(78,'True',_binary '\0',21),(79,'False',_binary '',21),(80,'String can be created using new operator.',_binary '\0',22),(81,'String is a primary data type.',_binary '',22),(82,'None of the above.',_binary '\0',22),(83,'String is immutable.',_binary '\0',22),(84,'None of the above.',_binary '\0',23),(85,'Abstraction is a technique to define different methods of the same type.',_binary '\0',23),(86,'It refers to the ability to make a class abstract in OOP.',_binary '\0',23),(87,'Abstraction is the ability of an object to take on many forms.',_binary '',23),(88,'class declared final is a final class.',_binary '\0',24),(89,'Final classes are created so the methods implemented by that class cannot be overridden.',_binary '',24),(90,'It can\'t be inherited.',_binary '',24),(91,'All of the above.',_binary '\0',24),(92,'None of the above.',_binary '\0',25),(93,'A transient variable is a variable that may not be serialized during Serialization.',_binary '',25),(94,'A transient variable is a variable which is serialized during Serialization.',_binary '\0',25),(95,'A transient variable is a variable which is to be marked as serializable.',_binary '\0',25),(96,'StringBuffer',_binary '',26),(97,'Both of the above',_binary '\0',26),(98,'StringBuilder',_binary '\0',26),(99,'None of the above',_binary '\0',26),(120,'Platform independence',_binary '',31),(121,'Automatic memory management (Garbage Collection)',_binary '',31),(122,'Compiled directly to machine code',_binary '\0',31),(123,'Strictly typed language',_binary '\0',31),(124,'Multiple inheritance support',_binary '\0',31),(143,'None of the above.',_binary '\0',37),(144,'public static void main(String[] args)',_binary '',37),(145,'public int main(String[] args)',_binary '\0',37),(146,'public static int main(String[] args)',_binary '\0',37),(147,'/ , %',_binary '',38),(148,'! , -',_binary '\0',38),(149,'* , +',_binary '\0',38),(150,'>>, <<',_binary '\0',38),(151,'Applet is a tool.',_binary '\0',39),(152,'An applet is a Java program that runs in a Web browser.',_binary '',39),(153,'Applet is a run time environment.',_binary '\0',39),(154,'Applet is a standalone java program.',_binary '\0',39),(155,'True',_binary '',40),(156,'False',_binary '\0',40),(157,'0',_binary '',41),(158,'not defined',_binary '\0',41),(159,'0.0',_binary '\0',41),(160,'null',_binary '\0',41),(173,'8',_binary '\0',2),(183,'6',_binary '',47),(184,'7',_binary '\0',47),(185,'8',_binary '',47),(186,'9',_binary '\0',47);
/*!40000 ALTER TABLE `anwers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime(6) DEFAULT NULL,
  `mark` float NOT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi63cpl1xkgy32iq68ru4ypjn4` (`user_id`),
  CONSTRAINT `FKi63cpl1xkgy32iq68ru4ypjn4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (1,'2023-09-15 09:19:41.145000',3,'2023-09-15 09:19:30.212000',11),(2,'2023-09-15 09:29:14.038000',0,'2023-09-15 09:27:13.038000',11),(3,'2023-09-15 09:28:18.624000',4,'2023-09-15 09:28:07.033000',11),(4,'2023-09-15 13:09:22.491000',2,'2023-09-15 13:09:10.516000',11),(5,'2023-09-15 14:04:57.205000',3,'2023-09-15 14:04:28.801000',11);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams_answers`
--

DROP TABLE IF EXISTS `exams_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exams_answers` (
  `exam_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  KEY `FK23205ymlvfuwxtosvxwvjb99l` (`answer_id`),
  KEY `FK2lcsv6ecff8gurvfwfxo1b2xs` (`exam_id`),
  CONSTRAINT `FK23205ymlvfuwxtosvxwvjb99l` FOREIGN KEY (`answer_id`) REFERENCES `anwers` (`id`),
  CONSTRAINT `FK2lcsv6ecff8gurvfwfxo1b2xs` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams_answers`
--

LOCK TABLES `exams_answers` WRITE;
/*!40000 ALTER TABLE `exams_answers` DISABLE KEYS */;
INSERT INTO `exams_answers` VALUES (1,2),(1,9),(1,40),(1,13),(1,29),(1,34),(1,8),(1,49),(1,45),(1,26),(3,41),(3,26),(3,29),(3,45),(3,33),(3,4),(3,14),(3,11),(3,48),(3,6),(4,15),(4,32),(4,40),(4,38),(4,2),(4,8),(4,25),(4,49),(4,30),(4,44),(5,156),(5,76),(5,74),(5,94),(5,148),(5,90),(5,91),(5,88),(5,89),(5,79),(5,157),(5,153),(5,82),(5,143);
/*!40000 ALTER TABLE `exams_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams_questions`
--

DROP TABLE IF EXISTS `exams_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exams_questions` (
  `exam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  KEY `FKdoj690gn6gdqir2x7pl95bvjm` (`question_id`),
  KEY `FKrn7vrieqs1e8nb1v462b1mjet` (`exam_id`),
  CONSTRAINT `FKdoj690gn6gdqir2x7pl95bvjm` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  CONSTRAINT `FKrn7vrieqs1e8nb1v462b1mjet` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams_questions`
--

LOCK TABLES `exams_questions` WRITE;
/*!40000 ALTER TABLE `exams_questions` DISABLE KEYS */;
INSERT INTO `exams_questions` VALUES (1,1),(1,3),(1,11),(1,4),(1,8),(1,9),(1,2),(1,13),(1,12),(1,7),(2,2),(2,9),(2,4),(2,1),(2,10),(2,12),(2,8),(2,11),(2,13),(2,3),(3,11),(3,7),(3,8),(3,12),(3,9),(3,1),(3,4),(3,3),(3,13),(3,2),(4,4),(4,9),(4,11),(4,10),(4,1),(4,2),(4,7),(4,13),(4,8),(4,12),(5,40),(5,20),(5,25),(5,38),(5,24),(5,21),(5,41),(5,39),(5,22),(5,37);
/*!40000 ALTER TABLE `exams_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `true_answer` int(11) NOT NULL DEFAULT '1',
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FKdb5p6ukb0v76he4pq87cbymhg` (`topic_id`),
  CONSTRAINT `FKdb5p6ukb0v76he4pq87cbymhg` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'5 + 7 bằng bao nhiêu?',1,1,1,_binary ''),(2,'9 - 3 bằng bao nhiêu?',1,1,1,_binary ''),(3,'3 * 4 bằng bao nhiêu?',2,1,1,_binary ''),(4,'18 / 2 bằng bao nhiêu?',2,1,1,_binary ''),(7,'Bao nhiêu giây trong 1 giờ?',3,1,1,_binary ''),(8,'Trong phép tính 6 x 7 + 8, phép nào được thực hiện trước?',3,1,1,_binary ''),(9,'Trong hệ thập phân, biểu diễn nhị phân của số 9 là gì?',1,1,1,_binary ''),(10,'5^3 bằng bao nhiêu?',2,1,1,_binary ''),(11,'2 + 2 = ?',1,1,1,_binary ''),(12,'10 * 5 = ?',2,1,1,_binary ''),(13,'Ước chung lớn nhất của 24 và 36 là bao nhiêu?',3,1,1,_binary ''),(14,'Công thức vận tốc là gì?',1,2,1,_binary ''),(15,'Đơn vị nào được sử dụng để đo thời gian?',2,2,1,_binary ''),(16,'Áp suất là gì?',3,2,1,_binary ''),(17,'Ánh sáng di chuyển với tốc độ nào?',1,2,1,_binary ''),(18,'Công thức của động năng là gì?',2,2,1,_binary ''),(19,'Nếu bạn đẩy một đồ vật mà không làm thay đổi vận tốc của nó, thì công bạn đã làm là gì?',3,2,1,_binary ''),(20,'What is a correct syntax to output \"Hello World\" in Java?',3,5,2,_binary ''),(21,'Java is short for \"JavaScript\".',3,5,1,_binary ''),(22,'Which of the following is false about String?',2,5,1,_binary ''),(23,'What is Abstraction?',2,5,1,_binary ''),(24,'What is true about a final class?',2,5,2,_binary ''),(25,'What is a transient variable?',2,5,1,_binary ''),(26,'Which of the following is thread-safe?',2,5,1,_binary ''),(31,'Which of the following are features of the Java programming language?',2,5,2,_binary ''),(37,'What is the correct syntax for the main method of a Java class?',1,5,1,_binary ''),(38,'Which arithmetic operations can result in the throwing of an ArithmeticException?',1,5,1,_binary ''),(39,'What is an applet?',1,5,1,_binary ''),(40,'Can we have multiple classes in the same Java file?',1,5,1,_binary ''),(41,'What is the default value of an int variable?',1,5,1,_binary ''),(47,'1 + 5 = ? và 2 + 6 = ?',2,1,2,_binary '');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(150) NOT NULL,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'can add question, manage user and setting','admin'),(2,'can do exam and see result','user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'Number of question',10),(2,'Time per exam',111);
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `topics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_msdep2s7k6y32gv8c0ifck0oy` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,'Math',_binary ''),(2,'Physics',_binary ''),(3,'Chemistry',_binary ''),(4,'English',_binary ''),(5,'Technology',_binary ''),(6,'Science',_binary ''),(8,'History',_binary ''),(13,'IT2',_binary '');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  `authentication_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,'admin','$2a$10$2SW6X3Li6lMjpPwwkqPLdeXMCu2hIlL6VuCW7NRBgkPyFbtkahSdm',2,_binary '','a1.jpg','DATABASE'),(11,'user','$2a$10$2SW6X3Li6lMjpPwwkqPLdeXMCu2hIlL6VuCW7NRBgkPyFbtkahSdm',1,_binary '','a1.jpg','DATABASE'),(12,'bac@gmail.com','$2a$10$jvATC7UW4u6VcEV6p.5miOchyzIWS0PYvir07/bLyxvuG6RakC4DS',1,_binary '','a2.jpg',NULL),(14,'bac123@gmail.com','$2a$10$XtYGwCEl9RoiaGg4L5RvIO2YfzXR0LV.Wpt8qtrkeYIUcExBAg0ZW',2,_binary '\0',NULL,NULL),(16,'user123','$2a$10$gyEa2qw3x7tOtd/oHDdkZeiNzVXOTw3b24tYu74byzvKs3q300umK',2,_binary '',NULL,NULL),(17,'test','$2a$10$N1eq3apQ7h96NHajtJjOMONpjfc4J.rL8By4caeMe9MNad/5.pNq.',2,_binary '\0',NULL,NULL),(23,'BAC Nguyen Van (VTI.DS)','',2,_binary '',NULL,'GOOGLE'),(24,'user12345','$2a$10$cbEIXY//nUanSB84YWE5pe7j/h.4VPokoOeCbM56yfCCHIWuIFxlq',2,_binary '',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-15 14:46:01
