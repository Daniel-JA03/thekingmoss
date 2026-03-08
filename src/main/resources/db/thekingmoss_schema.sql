-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: thekingmoss
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id_carrito` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `producto_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id_carrito`),
  KEY `FKa66gl3j7wnlwyc1i7rj5cm163` (`producto_id`),
  KEY `FKbunaoq2qnb3gd29rcqv2e2dal` (`usuario_id`),
  CONSTRAINT `FKa66gl3j7wnlwyc1i7rj5cm163` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`),
  CONSTRAINT `FKbunaoq2qnb3gd29rcqv2e2dal` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `categoria_id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Musgo Deshidratado'),(2,'Musgo Vivo');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto` (
  `contactoid` bigint NOT NULL AUTO_INCREMENT,
  `asunto` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `estado` enum('LEIDO','NUEVO') NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `mensaje` text NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`contactoid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` VALUES (1,'No me llega mi pedido','pepe123@gmail.com','LEIDO','2025-08-04 16:39:22.690529','Ya paso 2 semanas y no me llega todavia mi pedido como puedo hacer para que me den mi reembolso','Pepe Luna '),(2,'Tiempo de entrega de producto ','juanPe@gmail.com','NUEVO','2025-08-08 15:28:09.166968','Cuanto dias se la demora del envio de un producto  ','Juan Perez'),(3,'Pedido Perdido','pedro@gmail.com','LEIDO','2025-09-12 23:11:36.284014','prueba fdsfgddsag','Pedro Diaz');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedidos`
--

DROP TABLE IF EXISTS `detalle_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedidos` (
  `cantidad` int NOT NULL,
  `pedido_id` bigint NOT NULL,
  `producto_id` bigint NOT NULL,
  PRIMARY KEY (`pedido_id`,`producto_id`),
  KEY `FKd977lyb5oo3v1j0reijul4byi` (`producto_id`),
  CONSTRAINT `FK8a8fuu2ps83uxuf5wmoo8n72b` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`),
  CONSTRAINT `FKd977lyb5oo3v1j0reijul4byi` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedidos`
--

LOCK TABLES `detalle_pedidos` WRITE;
/*!40000 ALTER TABLE `detalle_pedidos` DISABLE KEYS */;
INSERT INTO `detalle_pedidos` VALUES (2,1,1),(3,2,1),(1,5,1),(10,6,1),(15,7,1),(2,8,1),(2,10,1),(8,14,19),(1,15,1),(4,15,19),(1,15,25),(2,15,27),(1,16,19),(6,17,20),(5,17,22),(6,18,19),(10,18,20),(5,19,1),(6,19,23),(6,20,23),(2,21,19),(2,22,20),(1,24,1),(1,24,19),(5,25,1),(1,25,19),(1,25,20),(2,26,1),(1,26,19),(2,26,20),(3,27,19),(1,28,1),(3,28,19),(2,28,20),(1,28,22),(2,29,19),(2,29,20),(2,29,24),(2,32,23),(10,32,24),(2,32,25),(3,32,27),(1,32,28),(4,35,20),(7,35,22),(2,35,23),(2,35,26),(1,36,19),(1,36,23),(2,37,19),(2,38,1),(4,38,19),(1,39,19),(1,39,20),(3,42,19),(2,42,20),(1,42,22),(1,43,19),(2,43,20),(1,44,1),(1,44,19),(1,45,22),(2,45,23),(1,45,24),(1,46,1),(3,46,19),(2,46,20),(1,46,22),(2,47,1),(1,47,19),(1,48,19),(1,49,1),(1,49,19),(1,50,23),(1,50,24),(1,51,20),(1,52,1),(1,53,19),(1,54,23),(1,54,25),(1,54,27),(1,55,19),(2,56,1),(1,56,19),(1,56,20),(1,58,23),(1,58,25),(1,58,27),(29,59,28),(20,60,1),(1,61,1),(1,61,20),(1,61,23),(2,62,1),(1,62,19),(1,62,25);
/*!40000 ALTER TABLE `detalle_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `direccion_id` bigint NOT NULL AUTO_INCREMENT,
  `distrito` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `pais` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `direccion_referencia` varchar(255) NOT NULL,
  `tipo_direccion` enum('DEPARTAMENTO','DOMICILIO','TRABAJO') NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`direccion_id`),
  KEY `FK54oy4k8b4ltgwmoq6kuocwhc7` (`usuario_id`),
  CONSTRAINT `FK54oy4k8b4ltgwmoq6kuocwhc7` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,'Los Olivos','ACTIVO','Peru','Lima','Por Angelica Gamarra','DOMICILIO',1),(2,'Independencia','ACTIVO','Peru','Lima','Cerca al centro comercial MegaPlaza','DEPARTAMENTO',2),(3,'Tomas Valle actualizado','ACTIVO','Peru','Lima','Cerca al centro comercial Plaza Norte','TRABAJO',3),(5,'Tomas Valle actualizado','DESACTIVO','Peru','Lima','Cerca al centro comercial Plaza Norte','TRABAJO',2),(8,'Independencia','ACTIVO','Peru','Lima','Es una referencia','DEPARTAMENTO',5),(9,'Independencia','ACTIVO','Peru','Lima','Es una referencia','TRABAJO',5);
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento_identidades`
--

DROP TABLE IF EXISTS `documento_identidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento_identidades` (
  `documento_identidad_id` bigint NOT NULL AUTO_INCREMENT,
  `numero_documento_identidad` varchar(255) NOT NULL,
  `tipo_documento_identidad` enum('CARNET_EXTRANGERIA','DNI','RUC') NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`documento_identidad_id`),
  UNIQUE KEY `UKcu3583wohn8pe5srvd4pvevgk` (`usuario_id`),
  CONSTRAINT `FKokudmb42rofhngcs6eovfy9ws` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_identidades`
--

LOCK TABLES `documento_identidades` WRITE;
/*!40000 ALTER TABLE `documento_identidades` DISABLE KEYS */;
INSERT INTO `documento_identidades` VALUES (1,'73828112','DNI',1),(4,'70262103212','RUC',2),(5,'702621033','CARNET_EXTRANGERIA',3);
/*!40000 ALTER TABLE `documento_identidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `pedido_id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_pedido` datetime(6) NOT NULL,
  `informacion_pedido` varchar(255) NOT NULL,
  `instruccion_entrega` varchar(255) NOT NULL,
  `tipo_entrega` varchar(255) NOT NULL,
  `estado_pedido` enum('CANCELADO','ENVIANDO','GENERADO','PAGADO','RECIBIDO') NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `stripe_payment_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  KEY `FK5g0es69v35nmkmpi8uewbphs2` (`usuario_id`),
  CONSTRAINT `FK5g0es69v35nmkmpi8uewbphs2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'2025-05-05 19:00:00.000000','nueva orden 1','es una prueba 1','delivery','PAGADO',3,NULL),(2,'2025-05-05 19:00:00.000000','nueva orden 2','es una prueba 2','delivery','ENVIANDO',4,NULL),(5,'2025-06-20 14:38:00.000000','Nuevo Pedido de Prueba','hfhdgsdg','ddg','PAGADO',3,'pi_3R1o09FjHofjkUwZ1B0h09bX_SUCCESS'),(6,'2025-06-23 15:05:00.000000','Nuevo Pedido de Prueba 3','Es una prueba 2','Delivery','PAGADO',3,NULL),(7,'2025-06-13 13:37:00.000000','Nuevo Pedido de Prueba 3','Es una prueba','Delivery','RECIBIDO',3,NULL),(8,'2025-06-25 17:39:00.000000','Nuevo Pedido de Prueba','hfhdgsdg','Delivery','CANCELADO',3,NULL),(10,'2025-06-12 01:30:00.000000','Nuevo Pedido de Prueba','Es una prueba','Delivery','PAGADO',3,NULL),(13,'2025-07-16 13:40:00.000000','Nuevo Pedido de Prueba','Es una prueba','Delivery','GENERADO',3,NULL),(14,'2025-08-06 13:44:45.629000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(15,'2025-08-06 13:47:57.837000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(16,'2025-08-06 14:28:50.062000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(17,'2025-08-06 14:31:39.302000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(18,'2025-08-06 14:37:14.046000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(19,'2025-08-06 14:45:31.455000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(20,'2025-08-06 14:46:34.814000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(21,'2025-08-06 15:18:30.223000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(22,'2025-08-06 16:14:09.665000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(24,'2025-09-12 23:09:17.912000','Pedido de testuser','Dejar en puerta','DOMICILIO','CANCELADO',3,NULL),(25,'2025-09-16 23:10:03.266000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(26,'2025-11-13 21:58:55.155000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(27,'2025-11-13 22:33:09.221000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(28,'2025-11-15 12:09:37.923000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(29,'2025-11-15 12:29:29.456000','Pedido de testuser','Dejar en puerta','DOMICILIO','RECIBIDO',3,NULL),(32,'2025-11-15 16:26:50.719000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(35,'2025-11-15 16:27:54.439000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(36,'2025-11-15 16:47:10.815000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(37,'2025-11-15 17:04:59.670000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(38,'2025-11-15 20:29:20.123000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(39,'2025-11-15 20:30:27.251000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(42,'2025-11-15 20:31:44.675000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(43,'2025-11-15 23:28:43.131000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(44,'2025-11-16 18:34:26.465000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(45,'2025-11-22 17:18:57.343000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(46,'2025-11-22 18:03:47.303000','Pedido de testuser2','Dejar en puerta','DOMICILIO','GENERADO',4,NULL),(47,'2025-11-23 00:02:46.460000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(48,'2025-11-24 15:37:57.810000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(49,'2025-11-24 18:21:54.399000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(50,'2025-11-24 18:56:02.662000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(51,'2025-11-24 20:19:20.635000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(52,'2025-11-24 20:27:57.843000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SXB2wFjHofjkUwZ0Nmae5BG'),(53,'2025-11-24 20:36:42.937000','Pedido de testuser','Dejar en puerta','DOMICILIO','GENERADO',3,NULL),(54,'2025-11-25 11:22:34.919000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SXP0jFjHofjkUwZ0eNqNhwS'),(55,'2025-11-25 11:26:35.999000','Pedido de testuser2','Dejar en puerta','DOMICILIO','PAGADO',4,'pi_3SXP4bFjHofjkUwZ1M0f38oB'),(56,'2025-11-27 00:18:26.485000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SXxb7FjHofjkUwZ03QzZ7AN'),(58,'2025-12-14 17:33:33.354000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SeNr7FjHofjkUwZ1rLI0Org'),(59,'2025-12-14 17:40:27.858000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SeNxnFjHofjkUwZ14JUNh9d'),(60,'2025-12-17 21:39:33.194000','Pedido de testuser','Dejar en puerta','DOMICILIO','PAGADO',3,'pi_3SfX7rFjHofjkUwZ0hufc28V'),(61,'2025-12-27 23:08:30.311000','Pedido de testuser3','Dejar en puerta','DOMICILIO','GENERADO',5,NULL),(62,'2026-01-28 16:37:02.000000','Compra desde app móvil','Entregar en portería','DELIVERY','PAGADO',3,NULL);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_imagenes`
--

DROP TABLE IF EXISTS `producto_imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_imagenes` (
  `producto_imagen_id` bigint NOT NULL AUTO_INCREMENT,
  `imagen_url` varchar(255) NOT NULL,
  `producto_id` bigint NOT NULL,
  PRIMARY KEY (`producto_imagen_id`),
  KEY `FKo89u1q4jan8gbgod33hv48u27` (`producto_id`),
  CONSTRAINT `FKo89u1q4jan8gbgod33hv48u27` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_imagenes`
--

LOCK TABLES `producto_imagenes` WRITE;
/*!40000 ALTER TABLE `producto_imagenes` DISABLE KEYS */;
INSERT INTO `producto_imagenes` VALUES (32,'Musgo-Sphagnum.webp',1),(33,'musgo-plano.webp',19),(34,'musgo-terrario.webp',20),(35,'musgo-rizado.webp',22),(36,'musgo-plano-deshidratado.webp',23),(37,'musgo-manualidad.webp',24),(38,'musgo-cushion.webp',25),(39,'musgo-bosque.webp',26),(40,'Musgo-Sphagnum-deshidratado.webp',27),(41,'musgo-natural.webp',28);
/*!40000 ALTER TABLE `producto_imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `producto_id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  `descuento` decimal(5,2) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `peso` decimal(10,2) DEFAULT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  `tamanio` varchar(255) DEFAULT NULL,
  `categoria_id` bigint NOT NULL,
  PRIMARY KEY (`producto_id`),
  KEY `FK2fwq10nwymfv7fumctxt9vpgb` (`categoria_id`),
  CONSTRAINT `FK2fwq10nwymfv7fumctxt9vpgb` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Musgo vivo ideal para retención de humedad en bonsáis, plantas carnívoras o como base para orquídeas. Conserva muy bien la humedad.',10.00,'Musgo Sphagnum Vivo',0.30,18.00,7,'25x25 cm',2),(19,'Musgo natural recolectado cuidadosamente, ideal para cubrir superficies en terrarios y jardines verticales vivos.',0.00,'Musgo Plano Vivo (Hypnum)',0.50,22.00,19,'30x30 cm',2),(20,'Musgo fresco y flexible, perfecto para ambientes húmedos. Muy usado en hábitats de reptiles o ranas.',5.00,'Musgo para Terrarios',0.10,14.00,19,'17x18',2),(22,'Musgo preservado con textura esponjosa, ideal para decoración de cuadros, letras verdes y paredes interiores.',15.00,'Musgo Rizado Deshidratado',0.25,16.00,24,'25x25 cm',1),(23,'Musgo seco ideal para cubrir áreas decorativas planas, perfecto para paisajismo en interiores o proyectos artísticos.',0.00,'Musgo Plano Deshidratado',0.40,20.00,14,'30x30 cm',1),(24,'Musgo deshidratado especialmente seleccionado para artesanías, nacimientos, dioramas y decoración DIY.',5.00,'Musgo para Manualidades',0.10,10.00,36,'Pequeño',1),(25,'Musgo natural en forma de cojín, de textura esponjosa y elegante. Ideal para decoración en kokedamas, bonsáis y jardines japoneses.',5.00,'Musgo Cushion Vivo (Leucobryum)',0.40,24.00,19,'25x25 cm',2),(26,'Musgo recolectado en hábitats naturales, con textura variada e irregular. Muy usado en maquetas, terrarios y decoraciones naturales.',0.00,'Musgo de Bosque Vivo',0.30,19.00,20,'20x30 cm',2),(27,'Musgo seco y conservado ideal para arreglos florales, orquídeas o retención de humedad. Excelente base para estructuras vegetales.',10.00,'Musgo Sphagnum Seco',0.30,17.00,28,'25x25 cm',1),(28,'Musgo tratado sin colorantes, mantiene su color y textura natural. Ideal para cuadros verdes, letras vegetales o decoración ecofriendly.',0.00,'Musgo Verde Natural Preservado',0.20,21.00,12,'20x25 cm',1),(31,'Producto creado desde prueba unitaria',0.00,'Producto Test',1.00,25.50,10,'M',1),(32,'Producto creado desde prueba unitaria',0.00,'Producto Test',1.00,25.50,10,'M',1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_roles` (
  `usuario_id` bigint NOT NULL,
  `nombre_rol` varchar(255) NOT NULL,
  PRIMARY KEY (`usuario_id`,`nombre_rol`),
  KEY `FKge11fj4rgo1k6nh86beexem45` (`nombre_rol`),
  CONSTRAINT `FKge11fj4rgo1k6nh86beexem45` FOREIGN KEY (`nombre_rol`) REFERENCES `roles` (`name`),
  CONSTRAINT `FKuu9tea04xb29m2km5lwe46ua` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_ADMIN'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER');
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario_id` bigint NOT NULL AUTO_INCREMENT,
  `apellido_usuario` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Jaimes Amancio','danieljaimes@example.com','Daniel Aaron','$2y$10$bYixTpXaX3j6tp8FxReMu.NX7Zp10bkYIDR7E2AtbeQ387NOWZ6LO','978651179','daniel123'),(2,'Cubas Illanes','diegocubas@example.com','Diego Sebastian','$2y$10$bYixTpXaX3j6tp8FxReMu.NX7Zp10bkYIDR7E2AtbeQ387NOWZ6LO','945033245','diegol123'),(3,'User','test@gmail.com','Test','$2a$10$Vq1azsevVvwV.o5IhlvdguMl/XN8r0JlSQCdfBL2euL9z3tU9Akoe','123456789','testuser'),(4,'User2','test2@gmail.com','Test2','$2a$10$TmwqzOxgM3VIjLG4Lu3KWOBW43RXrrQTaFoxbQLfon6BI4iMm99cm','123456789','testuser2'),(5,'User3','test3@gmail.com','Test3','$2a$10$RMxh5KTCiyix2N1zrx7zu.ZszvmLoycaEdH5J7TkpRAthKUE8pys6','945654201','testuser3'),(6,'Register','pruebaRegister@gmail.com','Prueba','$2a$10$Dfd2DH4Fxooo89BiIBgGK.DZb4W1ixpeWTcHaCaVdOkID7YKqpO/S','945098762','prueba1'),(7,'Register2','register2@gmail.com','Prueba2','$2a$10$GuurkBoy29ixRwXPf2E/t.j5cq4.zpWQ0UB4gUPjR5ISKoQai62w2','946103584','prueba2'),(8,'Test','pruebaTest@gmail.com','Prueba3','$2a$10$L0SVs4UZsaonwXZMc9JYOOpEwgE10gpE254AcX8Od.5awf9CJ3.qG','999999999','prueba3');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-25 21:37:44
