-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: khachsan_database
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `tbladmin`
--

DROP TABLE IF EXISTS `tbladmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbladmin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_username` char(60) NOT NULL COMMENT 'tÃªn tÃ i khoáº£n',
  `admin_password` char(60) NOT NULL COMMENT 'Máº­t kháº©u',
  `admin_note` varchar(1000) DEFAULT NULL COMMENT 'mÃ´ táº£, ghi chÃº',
  `admin_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_username_UNIQUE` (`admin_username`),
  UNIQUE KEY `admin_id_UNIQUE` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbladmin`
--

LOCK TABLES `tbladmin` WRITE;
/*!40000 ALTER TABLE `tbladmin` DISABLE KEYS */;
INSERT INTO `tbladmin` VALUES (1,'admin2','c84258e9c39059a89ab77d846ddab909','another note.','2024-11-30 00:28:50','2024-11-30 00:39:23'),(3,'admin1','e10adc3949ba59abbe56e057f20f883e','just a note.','2024-11-30 00:31:16','2024-11-30 00:31:16'),(4,'admin','21232f297a57a5a743894a0e4a801fc3','just a note.','2024-11-30 07:13:26','2024-11-30 07:13:26');
/*!40000 ALTER TABLE `tbladmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbooking`
--

DROP TABLE IF EXISTS `tblbooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbooking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL COMMENT 'ngÆ°á»i Ä‘áº·t phÃ²ng',
  `room_id` int NOT NULL COMMENT 'phÃ²ng Ä‘Æ°á»£c Ä‘áº·t',
  `booking_state` int DEFAULT NULL COMMENT 'tráº¡ng thÃ¡i Ä‘á»“ng Ã½ hay bá»‹ tá»« chá»‘i',
  `booking_comment` varchar(255) DEFAULT NULL COMMENT 'bÃ¬nh luáº­n',
  `booking_rate` int DEFAULT NULL COMMENT 'Ä‘Ã¡nh giÃ¡ tá»« 1 Ä‘áº¿n 5 sao',
  `booking_start_date` date NOT NULL COMMENT 'thá»i gian báº¯t Ä‘áº§u, check in',
  `booking_end_date` date NOT NULL COMMENT 'thá»i gian káº¿t thÃºc, check out',
  `booking_people_count` int DEFAULT NULL COMMENT 'sá»‘ lÆ°á»£ng ngÆ°á»i',
  `booking_note` int DEFAULT NULL COMMENT 'ghi chÃº, yÃªu cáº§u thÃªm',
  `booking_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `booking_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `booking_id_UNIQUE` (`booking_id`),
  KEY `customer_id` (`customer_id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `tblbooking_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `tblcustomer` (`customer_id`),
  CONSTRAINT `tblbooking_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `tblroom` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbooking`
--

LOCK TABLES `tblbooking` WRITE;
/*!40000 ALTER TABLE `tblbooking` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblbooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblcustomer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_username` char(60) NOT NULL COMMENT 'tÃªn Ä‘Äƒng nháº­p',
  `customer_password` char(60) NOT NULL,
  `customer_fullname` varchar(255) DEFAULT NULL COMMENT 'tÃªn Ä‘áº§y Ä‘á»§',
  `customer_phone` char(20) DEFAULT NULL COMMENT 'sdt',
  `customer_email` varchar(60) DEFAULT NULL COMMENT 'email',
  `customer_address` varchar(255) DEFAULT NULL COMMENT 'Ä‘á»‹a chá»‰',
  `customer_birthday` date DEFAULT NULL COMMENT 'ngÃ y sinh',
  `customer_gender` char(20) DEFAULT NULL COMMENT 'giá»›i tÃ­nh',
  `customer_note` varchar(1000) DEFAULT NULL COMMENT 'mÃ´ táº£, ghi chÃº',
  `customer_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `customer_username_UNIQUE` (`customer_username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcustomer`
--

LOCK TABLES `tblcustomer` WRITE;
/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` VALUES (1,'gangplank','fe01d67a002dfa0f3ac084298142eccd',NULL,NULL,NULL,NULL,'2003-12-31',NULL,'just a note.','2024-11-30 01:15:02','2024-11-30 01:20:12'),(3,'customer2','a11c1e7006223a2a80bd295e96566d6e',NULL,NULL,NULL,NULL,'2003-01-07',NULL,'just a note.','2024-11-30 01:17:27','2024-11-30 01:17:27'),(4,'customer5','a11c1e7006223a2a80bd295e96566d6e',NULL,NULL,NULL,NULL,'2003-10-31',NULL,'just a note.','2024-11-30 01:18:44','2024-11-30 01:18:44');
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblemployee`
--

DROP TABLE IF EXISTS `tblemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblemployee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `employee_username` char(60) NOT NULL COMMENT 'tÃªn Ä‘Äƒng nháº­p',
  `employee_password` char(60) NOT NULL,
  `employee_fullname` varchar(255) DEFAULT NULL COMMENT 'tÃªn Ä‘áº§y Ä‘á»§',
  `employee_phone` char(20) DEFAULT NULL COMMENT 'sdt',
  `employee_email` varchar(60) DEFAULT NULL COMMENT 'email',
  `employee_address` varchar(255) DEFAULT NULL COMMENT 'Ä‘á»‹a chá»‰',
  `employee_birthday` date DEFAULT NULL COMMENT 'ngÃ y sinh',
  `employee_gender` char(20) DEFAULT NULL COMMENT 'giá»›i tÃ­nh',
  `employee_note` varchar(1000) DEFAULT NULL COMMENT 'mÃ´ táº£, ghi chÃº',
  `employee_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `employee_id_UNIQUE` (`employee_id`),
  UNIQUE KEY `employee_username_UNIQUE` (`employee_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblemployee`
--

LOCK TABLES `tblemployee` WRITE;
/*!40000 ALTER TABLE `tblemployee` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblemployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblroom`
--

DROP TABLE IF EXISTS `tblroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblroom` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) NOT NULL,
  `admin_id` int NOT NULL COMMENT '1 admin sá»Ÿ há»¯u nhiá»u phÃ²ng',
  `room_image` blob NOT NULL COMMENT 'áº£nh minh hoáº¡ phÃ²ng',
  `room_size` double NOT NULL COMMENT 'diá»‡n tÃ­ch phÃ²ng, theo m2',
  `room_bed_count` int NOT NULL COMMENT 'sá»‘ giÆ°á»ng trong 1 phÃ²ng',
  `room_star_count` int NOT NULL COMMENT 'sá»‘ sao cá»§a phÃ²ng, tá»« 1 sao Ä‘áº¿n 5 sao',
  `room_price_per_hour_vnd` double NOT NULL COMMENT 'GiÃ¡ tiá»n thuÃª phÃ²ng trong 1 giá»',
  `room_is_available` tinyint(1) NOT NULL COMMENT 'phÃ²ng cÃ³ sáºµn sÃ ng Ä‘á»ƒ dÃ¹ng hay khÃ´ng.',
  `room_note` varchar(1000) NOT NULL COMMENT 'mÃ´ táº£, ghi chÃº',
  `room_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `room_updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`),
  KEY `tblroom_ibfk_1` (`admin_id`),
  CONSTRAINT `tblroom_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `tbladmin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblroom`
--

LOCK TABLES `tblroom` WRITE;
/*!40000 ALTER TABLE `tblroom` DISABLE KEYS */;
INSERT INTO `tblroom` VALUES (1,'room1',1,_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\í\0„Photoshop 3.0\08BIM\0\0\0\0\0h(\0bFBMD0a000a6f0100007b04000099090000930a0000d90b00000b1100006a1800001b190000481a0000a91b000067270000\0ÿ\Û\0C\0\n\n\n		\n\Z%\Z# , #&\')*)-0-(0%()(ÿ\Û\0C\n\n\n\n(\Z\Z((((((((((((((((((((((((((((((((((((((((((((((((((ÿ\Â\0\0\È\0\È\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ú\0\0\0\0+\';Q@\Ë\ç:9-V\äzóÁ\É%Ü–-}\Öy\Ê\ÝV£3hDÝ[£d‰_Y\î\Ö4JsU™r\â\Ú\ï /©\Ét2Zž]•\ây£­bŒ\í¸XT#‚©\Í\Ý\ëWü\\\ÝVzM \Æ²zòÀ\çC\×kx%Y`•\Z¢r’\Ó\\\Ø8Ôº*Š\ße±csX¬ü^\ã2žn˜O>š8m\ÌDA²\Êj\Õ\"’7À\Ô\ÔÓ¼â’¼‘Ê†%:12X\ÄN\ã®KñÎ½Ox<UÁ²\ÒYF«“‚$E²³0S[®\äN\ãFñÜ’	[2G\'¦\Ç+\è\Òu$¬\é»$r>I#™¥ˆ5,Óƒ‚ŠY¨x~†]œrSºXý}/\Î=´¿Þ©z\êasÆ±\Ïc\Ú;¼ym$gD•²º€G³\Ýv±\ÛY´ùýúptp·NS7F#6¼lœ\Ýã¸Œä“Ž2^!lŽ‰„\ïF\Úõ¾zp†°\ÂYû\ë+Dµd\'Ž\áU7U¼ù4\ç¨;2T4–¯g´9-{\\Ü¶¹$\Ã;Y?f6\ÂE\ÏX«ó‡±/ÐˆM!(Ÿ¼	Ô’\â»»N¤”«\É\"ÃºŒ.”1\Å~¶kÊ¶FT:ºŒ\ÇX„ŸGŸ^\ë&²\ÓµŸU^\ëF¹#t:2\ÆLeö”)|pœ01L|o`Ÿ„ô¤h,s˜ö\\–•óWRPx\êsz¤³E	·Ô‰+‹¡•\Í\Ê5F\Ë7Sf\åKÀ­¾N.m-+€+W‚\Í6|€ž¨\'—q\ëˆY\ïQ%QjÉ‹„€ù<r«úKF¦W˜Ñ”qae\ì¥ý\ë =yóñw;\Ït\"º\Ù)#oL.\Äóƒ\ÓWk\ÓuQ²—z¼¬§Ï‚§+º\Õ	\Ó9\âØ¢\ÒC\06&£oË¯>…\rw>ð/\ïzY\ÉÓ¯`H\ïD¤-`(u#\ÃW0€!9žJ\êxÔ¤·a.~£/J”–\ÌOb[ñ\Ä=&[%U¬\êJx’‘\×ýIXRIÿ\Ä\0-\0\0\0\0\0\01!23\"A#4 $C%Bÿ\Ú\0\0ÿ\0‚L›\Zª?p\Ìbr²\Ì{oŒlaZ“t…|h&*ý–.†	W\Ã_6¸¯¦¾k÷þi\ä|3¿¨o% ‹œNFñj\Ã\é_Äœ\Ù\îY¬\×\Í~\éL_fgõ\Ê>\ë/¤ÍºM|\ë¤\Ùö…\êõU(\ê•\Ü[Bb|IÍž\á5šÁ\Í|Ê¢|\"ey\Å_3\"€[\'}ƒUlvWµ{5d\ä3¾ý¦›YZœ‚G\ÔÐ¿\Zóg»\Ôrœ\Ê\â|B_ð./ñR›+\äm›&Ã¯\êõS‰ˆù-\Ò\éE¿¦\ã™\ÔqŽ=ß§<ãyw¨\å=+¿ŒFò¼MD[A¸\Ê\Ú~¢ \Û\Óñ;x8µf\Õd»¨€ù	^v/@Ò¼þ—\ç\Ôr°E¿lojø>4\×_P\'Q­ž§\ï×‹„´Xp©6\n\ë¬t\ê;`û?>¢,^G5ú?fž£\ÒÀhf¹Œ\Ü\Ë+\'r Dn#s\ê9yü\×\Èô`\Úÿ\0(›®‚Ë„\ï];·N\í³-f´¥u2Ë®\Ì|›÷+£/t95ja\è9‹\Ï\æ¿w¡}DwJ–Þ£3®w«¤\ØÏ‡÷™§Ž1ù\0‘]ï²¼mø\åXs?šýðzh&Å¡V´È¹\îm6¾Cfv\Òô\ÖvN\ï\Ü,¿\n{¦›\ÌÁ»m\Óòy\ÊþOñ¬Yö\n´\É_7Ž\àQ)Mg[J\Ö\à\ìPRw@u%À!,¥»•~[•\æWói\çI¤\Û6À&m‡#\"\Âv«oœŽ\ÉYÓ²\ÆEm`©ý¼½dæ¶[·\'{8¦µ­:}š7\å¢ó\Åß‘\ZôIf[½Ü\ËNŸv@\Ò{y~\Ñ\Í¸·;1­”/j¤÷õ\á½\Ý2\ÞòöNÊˆ½[•\æ^\Ì\ï\Üa\r† mes)µP\n\í­e\ë\Ô/\ÅQÈ¬¬sºš1v+®¢d?r\ÎOG+SœZ\ç™¾ü¯3\ÌO&Ygn•\Ç\Èk\Øjgù/†\ãú)¯7\"»Cý\éŽû\ØM\Ò\æñ´™³H¾%Z¶r™uJ\å¢ó/m¢µØ‚(\0_]—\ÌP³tt\"ò+Œ6“\ì°vr²Fq£li\à“\\Û´ñ~²V~\ÙQ\Õ\Ý*¥\r½¢`¥\ÄÛ¬ŸQeš\ä3\é<ÿ\0!\ÒÚ©\Ú*²\ä¬¬\Í\Ä@\Ë\Ì\év\Â\È]–)?\Ê9¢\Ë.^@\"\ËoŸ‹¬qw\Òe,û’rO²\í\Ï+­\ÃYUÁ´%Ž‹Y\ÉD¦nm\Ý/8Ss\0õ¯4{¬Ýôa£z\àSô\Øÿ\0\ÔE¶õŸ_–Žrr\ZS\Ô2i‹\Õò!®Ì¼Sƒer\ÌP\Ó\éÊ›^\Í;m4\Ç\Ã\Ü\ØfÊ©\r›¿%\rvR\Ó1v\å*n›\ÂþÚ’ÅŸL|µ×¥ºò \Ü\ÚW\0\Ûmµj\ÔÙ•P²÷j\Ú\ël\"ý±³4Ÿ\î\äN•U¸x¸ko\Ôa\Øfu­OH÷CBÎª4\Íf’Ÿ\â¹lQçœ´³	\ÛYg\ÉO\Ë\ÂÝ¹¹\ÍXÍº2b´§\r%?H°.³i`\Ç\n>˜<K1«„\ê:£»\×/E\îk¤\ÐE\Æ6\ÛÙ°MlY_›¬ðÙŸ\ÐE\Þ\Ûj‚\Ò\Äd€•‰›zJúœUZr‹­U‹eú\åùÿ\0\n¹ò*JÙ©º›T·\Å{\ëqûfðey6‰•”_z2iûRwÑ¦\Ú\Zk4#B¤©¯:õ—õK\ÉË¦¿£õ\Åón\ß\âÁ\ÓK™U\î!ªgƒ\rbhÕ¿}NŒmÿ\0 Ê·N\å;(Ñ©Ñ¿ÿ\Ä\0$\0\0\0\0\0\0\0\0\0\0!1 2A0Q\"ÿ\Ú\0?+\ÎQ{\Z\ä\Íl\Éñ|eô×ŽX 8\ZJ5ô\ÍdQ4¡GQD‘Œw\Êñxy,\Þ_\Ô\ÚE9ú©š\Ùg½¼Z›\Þ\ÒVá‹¶Uc§V\êÿ\08©¿W\ê²Ur=È­+s8\àÍ±h-Lt’\â\î,\Ã\\Ÿ:\å—è®\Å\ÙEº=”\ã¦%L=˜\â¿Môi\Ä\ÕxpWñi¯dE\ÈÊ­1ú³\ÛrsR2ˆ\Ï©úy\ì:t…jsn\'Dt\Ö	\â\'^2B61øe¡Všökcµ\â|‚K‘›\çy\Çÿ\Ä\0$\0\0\0\0\0\0\0\0\0\0!1A\" BQ2ÿ\Ú\0?‰\âÓŒ–\ç®¡Ad\Â<\åõ3»®ð#Åž”\ÉLQ\ÔX\ÉZ~×Ÿá\ÝpGž#P\ÞQ“#;º\à\\§wº»;¿B²Ì²1“\'\r&OTZ\ÝŒ\n”\Üt.?GSÀ\Ö8*­„FVkRÁ-¶:\ãJR(Gw’CG§\ë=	bÁ\äG´t#Ôˆ\ÓÆ¡ýl.7¶m)iGµ\Ëft!;yÒ°Š-ˆCY1j¼YŠóz¤AMoJ\Ã\Ù5ú”\êÆ¦Æƒ6\ZÜ¤¾‡FvH‚h]™#™=%\ZR§É¸\ážIÁ!\ËH«Nõ^™\æ*\Ò\ÔCT¹X#\áÔ„\Ó\'Ñ«ú|\ÈôÀõ\ÄVò\Ùû7,‹\È\âb\í\'\ÈÖ•”G-dÿ\Ä\05\0\0\0\0\0\0\0\0!\"1AQ aq#20BRbr‘3‚’¡±CÁÿ\Ú\0\0?m…ð\å{š£š;*\Æ=•b¿ò½ET›Bw\âuŽC­Ž\\\×5UN\ÜC‰\Ö\Þ\Ç\Ø\ÑBV\Ô\ÉH1Æ’žH\Ò\Ù\ê¨E…#aG­‘:*\Ð&A5\È\"E(\Z¢\Éx®\ËNj¹òX;\ÍrW„L\\\Õ\ïˆQ\Èñ›\nu\è›<\æ€\ÙH,¦ª²š€)vea @<^B\ãn\ÉS,\ÔcõxÍ…:\Â\Z¡EE[*œæŠ°\ÞL\ÌÖaŸp¤>s’q-“À¢0ˆ\Äq\Ï\Ü\n}‡Ž™|RL\ÄrÐ fJ%ÀL©1 \'»°÷o÷29YH²\Ê×šb¹\Îô‹º¡y]n^\í\Ö\áqÖ½KEYEñn´6³A\ì•\éf\è—B7*˜Œ†ªnhSŠ\0\ÚKQ\ÙM¦c\Þ\âóÌ‚>rÔ¢^\âK“¤\æQI\ï§$\0M\è¡\ÉÄ„ó5Lhžh˜•\íe’n\\\Ã\Æ^\ã@¯:Œ\ÛdW¦ZIS-P­Ž‡¢Zš\íH\rj\'áœ‚|G}¢Áü~õ¾j•\"„õÿ\0P#EqÔ¿dÜ¤\Íÿ\0\Ä\ß!ÿ\0©­Ùªº)™ôGg!šk\Îg\Þdš;\çaºi˜Rw\ê=tµ\'r‡\Ê\Å>a^\"òò\ÛwA\Íns\'u\á»\'T{Œ\æ°\ÑzŠs‹ŒÀSSo~vM¹f®\î¦Î ¯y8ñ\0ou5 1,oü)±\îe4\×p^Y•Rx.‰’t\nNk‡P¦\ç“\Ð x4f\é\'{<j=ªa„´º…N”²ed@hL•÷™\éUúF_S“ ––W¤+Å¢}J€ÿ\0‰\Ì|.Y¬P%ûW‡	Á¬Øµ1Ù¹¦Ž\ncU‰L[’ªk¶E¯{ùDnN\à\Âk²[3’-f[•\':œµX˜w7^”×©ß…$:§\Ã\Ó0§º”\Ô\Ö**¬Y\ì²^\Ï\í_v%ÑžEW;:R\ÑûKvš¡i\î½*¶\Ó4\\~dAnõ\ê›\ÑwLx\Ùs@\ÈOUOÂ£Ö°j\Ì%_ø]þ\Ù-\Ç¼wTŒÿ\0Êˆc<¸6–D‡	…\ÏÌ„O\ÛU«T\ÍQ\Óp\ÒJDn,¹,Va^f\'|ªò“\ÎP¢7±¥WL–F\ÜŸn\ëd8ð\É’§´D¹ÿ\0\Èy\ê±G¬0°3\Ùÿ\0Š\Å\ì\Ð\\©7œ\î¸!x’¨¼\Øl-\ì½$¯D‚–gd\ÈQ3\ç œ49\Ü\Ð;\ÖÈ£\êYª(?p±\Ñ6õoJ¨M@/Yü \á(wC\Ìwz¬A“—Êª\ç;º\Ä\â½\Ëb†qžk3’s¢D¿†”^\×ú|IÁÿ\0iûLmd¨\Ùt)ü\äVk1ùP\â; \é£2£m\'\Æ]ˆ\ß\ì)\n515],‘”“?j(¼f8Ú¬8ƒ£\Ö8.<\ï/.\ã;IR¶g.\é\Ð\Ø÷µ¦¦K\Í/xmq95­ž3MU\0#ªc‡\ËÀZú\ÉE–ºjª:µzÚ™‰ o5Vñ„\É\çx\"Ÿ\ÙJr\æ½nþ+\ËsÐ¬m#ª›I®÷Ü¼\ÈÄ¯\Ö-\äP\ZØ šI\rF#E\æ2\áÝ©¼ûS¤HªidWƒ-b!v5\ât{úŸy­0\ÒÆ¸Kr½\ÛŠª‘o\r¢U9„\ZN­r:\á)“NvQ\æ[/2O6\áXb}\áM’xúÔˆ‘Si#¢©PD01ñaF1¸?iQ{\'n¦Œ¹YUEº¼E\"…U¬=¸h¤\ã|lñ5Žg6\åFi\äú#\âD`\éUÿ\Ä\0\'\0\0\0\0\0\0\0!1AQaq‘¡±Á \Ñð\áñÿ\Ú\0\0?!¯—Ñ‹*\n6¥Ÿd­mó…ý\æ\Û{\ÊN_™\å\Ä:%\ÝÀþ_$7ð!a‚?¿ó>·ªx<$\Åu\á,\0EÍ³\ÒS6\ÄÀ%\ÅL¬L“\è\å\×0‹\æ\æŠ¡ƒw\æf\Þ\ßP\Ï\Ã;\Ý\ày†¡4²\ç\Ä[	U\Z6¼°+ˆ‹\Ë\Ê:a+K\Ë*d\Û ÊŸf}¨¡zXEþûÁ\Ë\á›{}J(Fd~œ3i\Õ=ÁC±©C/h•9\\gÁ \êŒöÑ¤§%¦¶\â[\Ñ{-\Þ\Z®ŒûsWŸB\åÅ‹Hs\á†~\ç§#‡j;=¡RPX»fea}%.ÁJn˜i\Î.‰e­»%´Ü²œo¢V–”—l\ÞooI£Ò¹~¦ðs\âfõ\î‘6,l…–Å™nÓž3÷1¦rÐ…¡\ÓO\æ^\Õ+de\Æ\î”3\ß\Ü]tÜ¥’^ô+M?3GòM\ãw\Ä\Í\Zô;›‚7\ÄVn Gsš\0»U!*4\Ñ*‹\Ç2\Ú^‹r\È\"óI÷)Rq‚\n¾¼Í\â\í9C)§„&ÿ\0¬°±\n;„2d†µ²?Ä³\ÄÀd¯!ý0:!`ó\ÏhT\é[ƒ8†kø,8NS\êú+î“¤\Ø3ªa<Ð€­ j´5Y\æ-U#ú±ù”kžOÁ\ë)6L\Ï19>h«\î±èž…C„vŸB|Jz@„þò‰xt³\Ç\íV^!K;>\Û\"\ë¾	R`BURS\à9<\Î$J7¥¬\ÐR\í7\Öú\í¢&}3´@þ	wýJý³]F¡5mX[=\×t\ÂX•–‚I\ÒYù©ÿ\0v†ø#q\ï\Éðô<°µl7\Ä;õ‰\r=-Q\Û~„!\é¬\í~¥–µ\Ø|\"¸¥gûƒ¯lŽ—–\\º\Ñ]ŠŽ¿\Ò[6R\ï1\å\å¨býÙˆ.Ú›\È÷E0`ó+>–˜\íƒ\é6B	 \êò\ë\ìs\É(\×:<Ÿ\æQ\×ý\Å	+\íÀ)ƒ7\Ïyy¹Ê£—h”U\Ñ?3B\ãTµp\Ö\çcjþ‰Î“/j:&\Ë8ea\æo5\Çl¨*”6“ugBa*~\ã²}\à)?4\rR	zqÿ\0P-ª0ÀrOb\ÔZŠF„aOvA¯`×™\Üh†{E23Ä­V£gG\Ò\×±¹\è0\à~Šô\å\Í\ÇQ\ëö\ám@pa\Õ\Å\Õ\ÑE¿§\Üx½\Î\Ñ	Úš†8\ëˆ\Ö6`¸7Gùi¨\ÚSˆJ\Ì7rÛ”<\Æ\0]˜üf0sžž\ÏG\Ð\Ó°_,J\í®²¯ƒþ‰n¦õe r£\Í\í8†.ªD¦Gcø€G•¹6\0\Ö:7\Ä\ì19ð\Ç\çÃ²fµQ\Z´ˆ·\ìGb\Ú\'>Dð	+\ï\Ùÿ\0&mœ\Í2²Ä®õŒ\")žS9\\}Á\Ö\Û\Å÷w³´÷\Ø\Ü\Ù\r-\ë-¥\\[7©\ç3\àþ¤\êö\\Å®W\íÚ¹IKœªºñ)¬·D\Ð^²|%\à³À\ÃZc 40ý0\Î\ä0†\Ø(ÁŠ(g\ãd5n¢€xˆ\n\é(Z–w®\ëe³ø‹P›\áŒ\Ýþj\\®zÇ…³×„À\ë‘{58¼¡<YˆFÆ¾¦=\Ý\íÿ\0Q\\P¿À†§”HcXñ>‘ü»´Æ£´3£\Ë\Ä.©*®	X—´\Ù2‰g\Ã\Ü\"Ž]ÀuÁX˜§¡Ä ·k÷@‚\×\Þk”5Sh\èˆfš\ìJs=‡£1¥\Ä4Ü¹8c¨š\àjd=ô²9\'£)Z2ÁOºî—»µ.\ÕñûŒÔ¿¸RÝ­¯ó¿òŽ U[œ…ŠAGjP4qœº’˜\Æ\éXòA˜\ÑAóBv†Ì«\Ä_n³j¿¯´&Á«:/xg\Ò~el\èAøŒL\êzv•·~ga\ß÷`_‰k£¯²\å‹?tAž¿™w.\Ç\â röxþ®“ð\æu\æÌ½±2F\Ý12’t\Å/#»&SÚ©~\Í\Â„4)\è!ó,£eCû­EyˆQk\Ä\0“òŠã€‹\ÌNf\Å\È[¸?GüƒË¤R\ZþQ9Iy–@/2\ê´ûL&Ho\ä5(Vt}\Ø>oÜ¦M_1VP-9»‰Bã£¦ ù¡Ž aˆhV+¨\ÆÁz­õÿ\04cl=F\çjS!Y‰¦û¼\Ì\n€U\ë¤~Ñ£$©\Å\ÊU\Æ\Ü*67t}Ÿ\à×šJ{j$ù¡£•q¦\îþ£w\åSò@ÀF\ÚX½Ã¾—:¯’^\ÑV\Ï3\ÏIõ?\"kaJ®	c‚ó¯´\rü1jO²£\Ö^ª§F:Ÿ\Øÿ\0d[bxjxJ\Ø{KdIûÑˆÀx²ô³\Ò\å\ÌÇ»õ–-q5•3\0ú´\"\Î.\ÔK\Çh¦PAÏˆ±ÁbQ+˜v\ëÿ\0rƒ\ÅX\è\æ\Ã\Ðõ\Ä÷sp\Ê\ïÌŸýŠ_\Ô\ä÷L?$\Ãt\rr\è%Kózª•\'DY÷\ä~\àð( ª¾\Ûô¸°*þ™ðWù1°\à­@«jµ8E{s¹s4\Ë:ûJ[ž\'\n£#‚U¯hŒPÈ‘\Ïo_\Äú™\ëó/©ñy‚«Hõ!(f\Â•0\ßÎ•ÿ\0¨W\Öõ—\êÿ\Ú\0\0\0\0\0\0œ÷\ßSs)ˆœ\á9ÿ\0[FŽ3úqp&_öI=dú\ëõ°r—3\Íòšþ‹\Ì\á\á%½\á\ÊxŒ6¸¸“Iß½¿kÏ·¢+”Wq\ì#•½Ë¸†§,7û\Î{n>ŸNª\ßÊ‰2\Æ\Äbagmð\Ì\n|Mzƒ\å\Øf\ìW‡sZ;~-´@\àœƒ\ï|‹ð\Üÿ\Ä\0!\0\0\0\0\0\0\0\0!1A aQ±qðÿ\Ú\0?aB¾\æqÍ¶»=þŽR\Ë$\î}™\Ë\"z»\áœ@\Ý_c¾ ŸgÅ\0Ï°¦\Êõ\Æqö}üaM\r$3nûðû>þ\0^…´K\ìƒ\Ó=~\Åögœ›\n½\Ä“«t%½Ï³\ïtË¿òF‡\Þø\r·ù-\"y#&jû7\×Yz-®ÅŠ+\íÙ±ºNøpzº\ì}\ßx\Ë\Ñ(\Ñ*y¨€þ\ìü—a[²3È»!‘\ç}š^9¦%k¾ÿ\0¨\r»=Z;[\ZB\äòl\02Ág„n\ä\Â\ÈT…ú\åzr™¬(ÿ\0\æ\Õ\Ý\â,ƒ\ß\Ë„¬?-;*ž9.ß¨þk\×t‚/\Û\0óõ\â\Zƒba„yÈ¼6ŒIÿ\Ä\0&\0\0\0\0\0\0\0\0\0!1AaQq±\Ñ\áð ‘¡ñÿ\Ú\0?„\é¶\æ€\"\íR¢‰E~aÀb\â\â.D-E»ƒ\ÜŽ!(\Ò>ž\'\Ç\èf[µñ®;q...9e;E‹\r„\Ö°Up¸¸„\ë6õ<“\É470U¶6Ô¤ajõ)™¤#T7`Eil\Ú^zÅ­\ÂW\Ü-”#B—4\ÅO\"10\ß?’¢5\Ù\×}n\É\ÈL•6Á\Z:KµØ…9e\ÌÌ¹y(º^\Ì(en\Æ1E—¼\ÅJÁVDÁ!jÊ¦i4\æ\Ñ\ê!aûÿ\0²öGóöŠ\Ï´;*},–&jMBn#u\Ô[fŠ#a-˜À;jŠ\à>\êËŠa‚ñ²8\ë†`I\ç!0	U‚&Ox\Ý&\ZGóó©sDþþ>’ð\áŠf£\èðb¡\ì|\Æ\Z)\â>,$ˆwÎ•rñŸ™’?Iÿ\Ä\0&\0\0\0\0\0\0\0!1AQaq‘¡±Á\Ñð\áñÿ\Ú\0\0?-\ÙD9!¢ŠÌ±%¬Ü¯@iÀ~D5÷Yj)L\ë8¸0\ç1Y_\ä\ÌU¼°Mˆx£\ê+Qˆÿ\0w\äôÉ±\Ûø´ÜµžÈž\Ö_]ü˜¯g\ëöT\ã-pµ\'²W‰g0…/\ÆI\ÞIHÀ °\"ö—/QC|XÈšv b\éY¦\"°ÿ\0\n¼¯\ÉW‰>¼\ï\Ígrs|\Ì\Ì2ü?“ö†\ßi\ã9: ýÍˆn¬\Ì<³D0KK5ºª—-» \0V]µ\Ä<‘´®gª\Ü\r‹h\é¶a\ä\Éu\ÖMy5­FaL\ëÀŸ^)ü0Om>f\ãþˆ}\Ú.‘M„Ûƒ^\Ë“i§¹U j\è[ˆ“A¥4B?X©D\à²qRÓ…È§€þþ##»›|[h¨U^\Ì>\Z\ë¶>!\0\n¾!´6\ßÈŒÇ½ø\Ñ\ç\âÁ†{ˆý\Ûý:L\ß	‘ô\á.\×3\è«t§ûV\ä“k¡\É<|Aœñ\ëõô\rˆ\Ùyf¢Ø‚\ê\Å\Î‚NüÂ«NL+\æq¢C+zúEýË˜À\èk_‰‡–}²<¼L\Ú,xL<\Û?w\ä§\á‚\Ç\Ã7†T¸\0·F+¤M\0±‡-b\åÁ\Øf9X«B#f¸c¤»\Ëj)ð[\â\í\Ås¢)W©‡\Û\\#^\Ü\Ã ¤ˆ^d s1VÀê°§\æ?.>\ì\Íÿ\0u5O¨~°\ØwJü\Ä?˜\Ën¢Ù ew\n¨¦J™±\è!Qp.Gˆm‡D\Ì\Ö\Z¯2\ì)\îf%jõ=E€òªÃ¾©J¹Àò\ÆEw»\Ý\ÔCg(\Þ>‚P0+\Í6ÿ\0	8þŸûþ?8$ÁsµB;s¹ˆ\Óp\Ö0œ@POx\á|tŽ	!„vLÅ±9³ˆ\îUe‡€ º\ï	[@€‚ô\Þ+-Ê“0Ë™ž\àX\Îù”•s*>˜Ha+ð\Ü÷R«\ãƒ|\r%@@-²o?’b¯”B\Â\é\ê}a•#%q«KNúI„¹ÒšŽŒ^ß•\Ì\Ê\è[\ÇD­Œ¬Z„c\Ê\ëÑ›\éY•€cxoVwYŽ“p_ùB)º[<D˜ \Â\\pOg\×>ø%8Ä¾\Ûs¼B¤\è¥NYF-]\r02K{ù\âgm…ÀwFŒ\ãƒ°l<ƒ\Ã_\î‡\"’Kz\Ì-e¦PN³\ä}\Â&€·¬¸÷ðGYx\Êö&6s\Î\Ä;¹‹µŽGk•Ü‚t¡ø!öO».>²˜\Æ\ì!¹êœƒPbý^­ø;º‚ª\Å÷ulöý‰]JWóqž	\0qŠ¿c\0|U\ÄABÎƒ¬¾R¾\Ås-\Æ{\Ê)Ež\å‡Ê¾n\Í\r\×:ú=ÀjPAŠ:\Ùõ5ò\Çû*\Ù98ÿ\0Iš|¼\ïÏ·1}e‘ó\Ñ0®c½$°ñ€÷o\Ä7e\"ndÁJ\ç†þJc4\Z³‚ñ\í¹Lr\ç]/öZ\Å¡ˆ4˜tPô¾¿’´\ê6Û“š\â1¶´²ø£««\î\Ë\ìy þ\â¾dU\îº~†2šÖž{ýø„,56+ý–\ÃÒ¸)\Ô!\n^!¿,ûóSªü»2LQÍƒ\Ì\ru¹]÷Z\íf­Õ»ª‡Í¡…±\êòüL\ëCQ\É7\ì„jUO‘J\Ë\ášp\ÈQI\Ö½\à[˜ð[ß´D6»\ÓÓ‚¸/™E\0(Œ/Y¯›P\ÈL†Y\äÔ¼_@ù\Ä\Ö\ç­TU› o˜¿\äP…wP\ìŸgx\Ò#õŸ¢}ø°hvöŠ\ëG/2•€ê°¤+,¯\ÜQn.\ã}€N›Y¢)\ë\Ú*_!k¸t\î÷)+\Û3i\Ï\ÌYM#\Âÿ\05()\Ò;ôùŠ»(\ç‘\ìòL\ÅÀ¥‡•\åÝ½\ê%-c•el*nVó¨Z¢?$¢dp\á‹ðwŽÿ\0\î\å‚8„©f·þÀ\0]#g¹ùA·ö(\Ö	‚UÑ•i]–¥¥ þlFolz†‹­\ÊôJ\Ðq™‡±h¼ô‹]º´¬{­1\Ò\ãE\ßI\Ê\äv0šb\ì\ì\Ætb\î‹]K«ª–!A~S‡´©k§gp€R\Ð^ …\Z\Û,´$÷°|³a$+ö6þK:òÁgÀ§\Ëµ”˜8AD?\íÁ—ƒø\ßf-5\ÃŒTm\ítI;\Ö+D T+F\0•uMo3\î\éY‰\ÃYA*\Í?R\ìÒŠ\Ú\Ûo\Ü¬\æd\';…%]G‰nTµ¢Nµ®ñhT\á½L±/	“\Ë\Õ\Ôx´‘ÜµVt²\È2\ÆW¹š\ë\Æ{’\Ä\êCQ\Ï\r\Ó×±•\Ú\0nº}˜¬l¦^‘G\Ão»¸”\Ê@¼ŸHX“ v\r±\ßdMÝ±yk.D>X(THü“]0”F°\ãw\0@ô1q \"X\Ä\ì‘R¥\Øÿ\0Çˆ\r\×c”Sé¯‰‰b\Íq§Z÷*2©\Õ\èƒcŠÅ€TVÔŽ(%S\ÂR³œ2†Ge\ä\×H\Ð/»`i=‘z\é\Ä\ëo>¿\'\ÜA~Y\í§%ú›3\Ëúv´3{y£+\0¨DY«Ž[a·~&C†©/òt´@)˜ŽTtÇƒ?ýù›?küqmz\ìrP­‹»\"\Ý)[÷\ZP%¨\ïv[¡nkÌ´±]v³gß¬6\Ô\ë³\å¸\Â-™qGô/\ÈÍŽ\ãpWüÿ\0\ábôa·\Ê°|“/7\Â;tº¯ø\Ã~\Ûú¸+\à]-µZÑCQbÔ¾:@\äW>z“ˆŠñ˜(Tp\Óð\Æð\å)³ ¬pZP£\0EƒtQ6e\Öo\Ç\ÄI@T.üu`B \Ãr\Ø7C\Ì2Æ»N»\0H—°!À\Éc]‹\ÏeŠ½l½¡óLhDø®c .´\ì\áý—\Îû4ùû3‹EunW\áO\ëø\0\rF€\Úð@0u\\\ÑgƒÄ¥Ò„Ô¥œ•—V¶¾§`/.ˆ¬GŠ´Ø·ý–û™œ¶ù‚7.\Û.9X+ŒVZwX\ÜÂ¢vµ\ìK\ZH=s7fk\æ\rpec\äDhøVºód¹UþÃ‰\á?¨¾ \ánº;´w˜hƒ¤1L˜¼QD\ì»QõhBk\Ë\Üzòª)\Óþ2ð ¸e\'…ÿ\0P#\Í~\àO²–\\)Ýƒ\è÷\0%¢tº}Ô²P\Ò|”c¸\Íò³\ê\Æ\ÌlªI\Õ\Çº”v\n‰¬\Öô\á\êl‹;»ý\Å\"\Ë+@¾ˆ­\êþü:–˜L\Ê\ÄQ²AP5J­õ!1Vto-¿p\Ó\â$\ÑK·Z™\"\Û\0ü•©·m=0úUþ\â³P0* ÊªµAIfùÃ¬·~‚UE­]\é—>\êÇŒ¬ø«\"~Qù01)I…µ~R\Ë`·?\Ê#¡û‘ýL‰.\Ú\Èc¯Ô¨.Ö•}q\ë{¬ü\î9`omÿ\0ŒVu£®Ï›%G\0(\n«Ÿ&fFT…p;•ýÊ›²*Tmc6f;)\êŠD9ð\Æõ\è0z_²§ú\èm\îu\Æ\Ã\×y˜=¼o\é\Z+\\¿\ê%gAý?¸\'\Ä> øi7Ü¡Åû\Ð{Š}^\àLb‚)EÃ…\Ïf\r)\\VÔ«\ãR¹\ËW\Í\Z\ÇYR\á•R)¿†,Y\Þ%:·x¥|x‚¥O^ð\Æý1—\Â\ÇÝ£¨ÑŸ¢qQ²\Ö`\Öä””}J\Å(ºypeZµ€W%p-ú”¤K\ÑÌª:À-b¦!\Þ\ï\0þÔ¦³—\\{`}\\M\Þ\ïõ\r€òÔ¨0x}ýÀA\ÓýOõ)	(«<Ý¸´\ä\è4\Ê\ãÚºA\ØR®ð4335®×–\Íû\Üq\Ô~¸\á\n\ÌsŸ¥_Ô¡`e\Ö{|Jµ%÷›3­jlŒ,ðú\ÔUH¬p¶ú28\Ù	ü§À´›\í-P¾k K\n móno\æ\èòƒ\Â\Ñøø-Îƒ“¦`W\róq2\Óeh‹Y\Éñ\È\Ís+ù\Ô\ß\ì%\Zq\Û>’×©\Ïo]ÿ\0>\Ñe\Õú™úº¨—¦å¯¨øc„½)‚Ä©eþ ¶\äMv2š7[‹\ï\à,\ÙF‹\çò`f½¥a,\Ñz5s\ãm\å0{õÿ\0¸‰…l_uKcT.©¯$¿J½™ž\â¬…\Ù\ëPB~B`\ìÁ9\ØR12Áv6ó„)\ÍuaÔºb\Þ\Z\ìûE(\Ñ\Ã\é)öBEœ\í\É\î`¼Uý\ç\Ü_´Q\ï\Ê÷+gÓ‡\Üÿ\Ù',20.5,3,5,250000,1,'just a note.','2024-11-30 02:00:49','2024-11-30 02:00:49');
/*!40000 ALTER TABLE `tblroom` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-30 17:01:44
