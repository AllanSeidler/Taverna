-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: taverna
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `amizade`
--

DROP TABLE IF EXISTS `amizade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amizade` (
  `id_usuario1` int NOT NULL COMMENT 'usuario1 é quem convidou.',
  `id_usuario2` int NOT NULL COMMENT 'usuario2 é quem recebeu o convite.',
  `aceita` bit(1) DEFAULT b'0' COMMENT 'aceita: 0 se ainda não aceitou e 1 se já aceitou.\nem caso de rejeitar a amizade a mesma deve ser removida.',
  PRIMARY KEY (`id_usuario1`,`id_usuario2`),
  KEY `fk_usuario2_idx` (`id_usuario2`),
  CONSTRAINT `fk_usuario1` FOREIGN KEY (`id_usuario1`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario2` FOREIGN KEY (`id_usuario2`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amizade`
--

LOCK TABLES `amizade` WRITE;
/*!40000 ALTER TABLE `amizade` DISABLE KEYS */;
INSERT INTO `amizade` VALUES (1,2,_binary '');
/*!40000 ALTER TABLE `amizade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id_organizador` int NOT NULL COMMENT 'responsável pelo evento',
  `id_jogo` int NOT NULL COMMENT 'jogo (ou outra coisa) foco do evento. Deve se enquadrar nos interesses dos usuarios',
  `nome` varchar(45) NOT NULL COMMENT 'nome do evento',
  `data` datetime NOT NULL COMMENT 'quando o evento acontecerá',
  `inscricao` float DEFAULT '0' COMMENT 'valor para se inscrever, null deve ser tratado como 0.00',
  `endereco` json NOT NULL COMMENT 'json por hora, talvez vire uma tabela no futuro',
  `presencial` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_organizador`,`id_jogo`,`data`),
  KEY `fk_jogo_idx` (`id_jogo`),
  CONSTRAINT `fk_jogo` FOREIGN KEY (`id_jogo`) REFERENCES `interesse` (`id`),
  CONSTRAINT `fk_organizador` FOREIGN KEY (`id_organizador`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interesse`
--

DROP TABLE IF EXISTS `interesse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interesse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `interesse` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interesse`
--

LOCK TABLES `interesse` WRITE;
/*!40000 ALTER TABLE `interesse` DISABLE KEYS */;
INSERT INTO `interesse` VALUES (1,'Magic: The Gathering'),(2,'Yu-Gi-Oh!'),(3,'Pokémon TCG');
/*!40000 ALTER TABLE `interesse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interesses_usuario`
--

DROP TABLE IF EXISTS `interesses_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interesses_usuario` (
  `id_usuario` int NOT NULL,
  `id_interesse` int NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_interesse`),
  KEY `fk_interesse_idx` (`id_interesse`),
  CONSTRAINT `fk_interesse` FOREIGN KEY (`id_interesse`) REFERENCES `interesse` (`id`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interesses_usuario`
--

LOCK TABLES `interesses_usuario` WRITE;
/*!40000 ALTER TABLE `interesses_usuario` DISABLE KEYS */;
INSERT INTO `interesses_usuario` VALUES (2,1),(3,1),(1,2),(2,2),(3,3);
/*!40000 ALTER TABLE `interesses_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem` (
  `id_remetente` int NOT NULL COMMENT 'quem enviou a mensagem',
  `id_destinatario` int NOT NULL COMMENT 'quem recebeu a mensagem',
  `conteudo` tinytext NOT NULL COMMENT 'conteudo da mensagem',
  `data` datetime NOT NULL COMMENT 'data de envio da mensagem',
  PRIMARY KEY (`id_remetente`,`id_destinatario`,`data`),
  KEY `fk_destinatario_idx` (`id_destinatario`),
  CONSTRAINT `fk_destinatario` FOREIGN KEY (`id_destinatario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_remetente` FOREIGN KEY (`id_remetente`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (1,2,'ola amigo','2024-09-29 14:32:53'),(1,2,'Mensagem que estou escrevendo','2024-09-29 15:30:30'),(1,2,'sdf´mpkqernoghqe','2024-09-29 15:30:51'),(1,2,'sdf´mpkqernoghqe','2024-09-29 15:42:04'),(1,2,'sdf´mpkqernoghqe','2024-09-29 15:42:05'),(1,2,'sdf´mpkqernoghqe','2024-09-29 15:42:06'),(1,2,'Bati a cara no teclado sem querer :P','2024-09-30 12:53:28'),(1,2,'adopsjdoufhdsifh','2024-09-30 15:18:01'),(1,2,'sdçmkdlfkvn','2024-09-30 15:18:03'),(1,2,'Oi amigo :D como vai','2024-09-30 15:18:39'),(1,2,'ola amigo','2024-10-03 20:55:41'),(1,2,'vc é um amigo','2024-10-03 21:01:06'),(1,2,'asdasdasd','2024-10-04 19:37:51'),(1,2,'Só fazendo um teste pq tinha dado erro aq','2024-10-04 19:38:03'),(1,2,'Testando de novo','2024-10-04 19:57:36'),(1,2,'Uma mensagem só pra tirar o print','2024-10-10 22:38:00'),(2,1,'Oi, como vai','2024-09-29 15:52:03'),(2,1,'tudo bem ai?','2024-09-29 15:52:15'),(2,1,'tu mandou um monte de \"sdf´mpkqernoghqe\"','2024-09-29 15:52:37'),(2,1,'Blz mano','2024-10-04 19:38:30'),(2,1,'eai, funcionou?','2024-10-04 19:58:13');
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'nenhuma chave natural que nao seja texto',
  `nome` varchar(15) NOT NULL,
  `login` varchar(40) NOT NULL COMMENT 'não necessariamente um email',
  `senha` varchar(20) NOT NULL COMMENT 'talvez tenha que mudar o tamanho em caso de criptografia no futuro',
  `endereco` json NOT NULL COMMENT 'como json por hora, talvez vire uma tabela a parte futuramente',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Allan Eduardo','AllanSeidler','senha','{\"cidade\": \"Londrina\", \"estado\": \"Paraná\"}'),(2,'Luqueta','amigo 2','senha','{\"cidade\": \"Little London\", \"estado\": \"Parara\"}'),(3,'Eduardo','Eduardo01','123456','{\"cidade\": \"Londrina\", \"estado\": \"Paraná\"}');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-11  6:38:06
