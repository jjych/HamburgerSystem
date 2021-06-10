-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.7-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- gimal2020 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `gimal2020` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gimal2020`;

-- 테이블 gimal2020.bank 구조 내보내기
CREATE TABLE IF NOT EXISTS `bank` (
  `Money` int(11) NOT NULL,
  PRIMARY KEY (`Money`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 gimal2020.bank:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT IGNORE INTO `bank` (`Money`) VALUES
	(51200);
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;

-- 테이블 gimal2020.food 구조 내보내기
CREATE TABLE IF NOT EXISTS `food` (
  `SellFood` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL,
  `SellCount` int(11) NOT NULL,
  PRIMARY KEY (`SellFood`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 gimal2020.food:~12 rows (대략적) 내보내기
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT IGNORE INTO `food` (`SellFood`, `Price`, `SellCount`) VALUES
	('감자튀김', 1000, 3),
	('더블버거', 4400, 0),
	('베이컨버거', 5300, 1),
	('불고기버거', 2000, 1),
	('빅맥', 4900, 1),
	('사이다', 1300, 1),
	('아이스크림', 500, 1),
	('에그치즈버거', 3500, 0),
	('오징어버거', 4300, 0),
	('치즈버거', 2200, 0),
	('콜라', 1300, 1),
	('크리스피버거', 3300, 0);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;

-- 테이블 gimal2020.ingredient 구조 내보내기
CREATE TABLE IF NOT EXISTS `ingredient` (
  `IngredientName` varchar(50) NOT NULL,
  `IngredientPrice` int(100) NOT NULL,
  `Count` int(100) NOT NULL,
  PRIMARY KEY (`IngredientName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 gimal2020.ingredient:~17 rows (대략적) 내보내기
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT IGNORE INTO `ingredient` (`IngredientName`, `IngredientPrice`, `Count`) VALUES
	('감자', 600, 0),
	('달걀', 300, 2),
	('베이컨', 800, 2),
	('불고기패티', 800, 4),
	('사이다', 500, 1),
	('식용유', 1200, 0),
	('양상추', 300, 4),
	('양파', 500, 5),
	('얼음', 300, 2),
	('오징어패티', 900, 3),
	('우유', 600, 1),
	('참깨빵', 500, 5),
	('치즈', 500, 1),
	('치킨패티', 1000, 3),
	('콜라', 500, 3),
	('특별한소스', 200, 3),
	('피클', 400, 0);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;

-- 테이블 gimal2020.jumun 구조 내보내기
CREATE TABLE IF NOT EXISTS `jumun` (
  `JumunName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 gimal2020.jumun:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `jumun` DISABLE KEYS */;
/*!40000 ALTER TABLE `jumun` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
