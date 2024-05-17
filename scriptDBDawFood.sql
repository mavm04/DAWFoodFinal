CREATE DATABASE IF NOT EXISTS DAWFoodSamuelNavarro;

USE DAWFoodSamuelNavarro;

DROP TABLE IF EXISTS `tipoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoproducto` (
  `IdTipoProducto` int NOT NULL AUTO_INCREMENT,
  `nomCategoria` enum('COMIDA','BEBIDA','POSTRE') NOT NULL,
  `nomTipoProducto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdTipoProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `IdProducto` int NOT NULL AUTO_INCREMENT,
  `precio` decimal(5,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `IVA` decimal(4,2) DEFAULT NULL,
  `descripcion` varchar(300) NOT NULL,
  `IdTipoProducto` int DEFAULT NULL,
  PRIMARY KEY (`IdProducto`),
  KEY `fk_productos_tipoproducto` (`IdTipoProducto`),
  CONSTRAINT `fk_productos_tipoproducto` FOREIGN KEY (`IdTipoProducto`) REFERENCES `tipoproducto` (`IdTipoProducto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `tpv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tpv` (
  `IdTpv` int NOT NULL AUTO_INCREMENT,
  `fechaSistema` date DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `contraseña` varchar(6) NOT NULL,
  `horaSistema` time DEFAULT NULL,
  PRIMARY KEY (`IdTpv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `IdTicket` int NOT NULL AUTO_INCREMENT,
  `codTransaccion` varchar(100) NOT NULL,
  `horaOperacion` date DEFAULT NULL,
  `importeTotal` decimal(5,2) DEFAULT NULL,
  `fechaOperacion` date DEFAULT NULL,
  `IdTpv` int DEFAULT NULL,
  PRIMARY KEY (`IdTicket`),
  KEY `fk_ticket_tpv` (`IdTpv`),
  CONSTRAINT `fk_ticket_tpv` FOREIGN KEY (`IdTpv`) REFERENCES `tpv` (`IdTpv`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `detalleventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleventa` (
  `cantidadProducto` int DEFAULT NULL,
  `IdProducto` int NOT NULL,
  `IdTicket` int NOT NULL,
  PRIMARY KEY (`IdProducto`,`IdTicket`),
  KEY `fk_detalleventa_ticket` (`IdTicket`),
  CONSTRAINT `fk_detalleVenta_productos` FOREIGN KEY (`IdProducto`) REFERENCES `productos` (`IdProducto`) ON UPDATE CASCADE,
  CONSTRAINT `fk_detalleventa_ticket` FOREIGN KEY (`IdTicket`) REFERENCES `ticket` (`IdTicket`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;