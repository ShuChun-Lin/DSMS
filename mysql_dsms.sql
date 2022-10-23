CREATE DATABASE IF NOT EXISTS `dsms`;
USE `dsms`;

CREATE TABLE IF NOT EXISTS `dsms_department`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `fab/dept` VARCHAR(20) NOT NULL COMMENT '廠別/部門',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_department` VALUES
('1', 'fab1/dept1', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'fab1/dept2', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'fab2/deptA', '1', '2022-09-05 00:00:00', NULL, NULL),
('4', 'fab2/deptB', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_role`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `roleCode` VARCHAR(20) NOT NULL COMMENT '權限角色編碼',
    `roleName` VARCHAR(20) NOT NULL COMMENT '權限角色名稱',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_role` VALUES
('1', 'ADMIN', '系统管理員', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'DEPTADMIN', '部門倉管', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'EMPLOYEE', '普通員工', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_user`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `userCode` VARCHAR(30) NOT NULL COMMENT '使用者帳號',
    `userName` VARCHAR(30) NOT NULL COMMENT '使用者名稱',
    `userPassword` VARCHAR(30) NOT NULL COMMENT '密碼',
    `userId` VARCHAR(20) NOT NULL COMMENT '員工工號',
    `userDepartment` INT NOT NULL COMMENT '使用者部門(取自dsms_department-id)',
    `userRole` INT NOT NULL COMMENT '使用者權限(取自dsms_role-id)',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_user` VALUES
('1', 'admin', '系統管理員', '123456', '220101', '1', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'leon0301', '謝阿昌', '000000', '220102', '1', '2', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'littlelin', '小林', '000000', '220901', '1', '3', '1', '2022-09-05 00:00:00', NULL, NULL),
('4', 'bigcow', '阿牛', '000000', '220314', '2', '2', '1', '2022-09-05 00:00:00', NULL, NULL),
('5', 'bigcenter', '王大中', '000000', '220715', '2', '3', '1', '2022-09-05 00:00:00', NULL, NULL),
('6', 'bruceli', '李布魯斯', '000000', '220103', '3', '2', '1', '2022-09-05 00:00:00', NULL, NULL),
('7', 'jackey', '黃進祥', '000000', '220104', '3', '2', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_status`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `status` VARCHAR(20) NOT NULL COMMENT '零件狀態',
	`createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_status` VALUES
('1', 'new', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'scrap', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_parts`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `partsCode` VARCHAR(30) NOT NULL COMMENT '零件號',
    `partsName` VARCHAR(30) NOT NULL COMMENT '零件名',
    `partsDepartment` INT NOT NULL COMMENT '零件歸屬部門(取自dsms_department-id)',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_parts` VALUES
('1', 'S000GEAR001', '齒輪01','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'S000GEAR002', '齒輪02','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'S000GEAR003', '齒輪03','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('4', 'S00MOTOR001', '馬達01','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('5', 'S00MOTOR002', '馬達02','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('6', 'S000GEAR002', '齒輪A','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('7', 'S000GEAR003', '齒輪B','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('8', 'S00MOTOR001', '馬達A','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('9', 'S00MOTOR003', '馬達B','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('10', 'S000GEAR001', '齒輪甲','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('11', 'S000GEAR002', '齒輪乙','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('12', 'S000GEAR004', '齒輪丙','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('13', 'S000GEAR005', '齒輪丁','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('14', 'S000GEAR007', '齒輪戊','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('15', 'S000GEAR005', '齒輪01','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('16', 'S000GEAR006', '齒輪02','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('17', 'S00MOTOR003', '馬達01','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('18', 'S00MOTOR001', '馬達02','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('19', 'S000PUMP005', '幫浦01','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('20', 'S000PUMP001', '幫浦02','4', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_area`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `areaName` VARCHAR(15) NOT NULL COMMENT '儲區名稱',
    `areaDesc` VARCHAR(100) NOT NULL COMMENT '描述',
    `areaDepartment` INT NOT NULL COMMENT '儲區歸屬部門(取自dsms_department-id)',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_area` VALUES
('1', 'GEAR', '齒輪區','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'MOTOR', '馬達區','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'STUFF', '零件','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('4', 'PARTS', '放置新品','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('5', 'GEAR', '齒輪新品','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('6', 'MOTOR', '馬達新品','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('7', 'PUMP', '幫浦新品','4', '1', '2022-09-05 00:00:00', NULL, NULL),
('8', 'SCRAP', '報廢區','1', '1', '2022-09-05 00:00:00', NULL, NULL),
('9', 'SCRAP', '報廢區','2', '1', '2022-09-05 00:00:00', NULL, NULL),
('10', 'SCRAP', '報廢區','3', '1', '2022-09-05 00:00:00', NULL, NULL),
('11', 'SCRAP', '報廢區','4', '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_position`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `positionName` VARCHAR(15) NOT NULL COMMENT '儲位名稱',
    `positionArea` INT NOT NULL COMMENT '儲位所屬儲區(取自dsms_area-id)',
    `positionParts` INT DEFAULT NULL COMMENT '儲區要放置的零件(取自dsms_parts-id)',
    `positionStatus` INT DEFAULT NULL COMMENT '儲區選擇放置的零件狀態(取自dsms_status-id)',
    `createdBy` INT DEFAULT NULL COMMENT '創建者 ( dsms_user - id )',
    `creationDate` DATETIME DEFAULT NULL COMMENT '創建時間',
    `modifyBy` INT DEFAULT NULL COMMENT '更新者 ( dsms_user - id )',
    `modifyDate` DATETIME DEFAULT NULL COMMENT '更新時間',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_position` VALUES
('1', 'A001', '1', '1', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('2', 'A002', '1', '2', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('3', 'A003', '1', '3', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('4', 'A001', '2', '4', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('5', 'A002', '2', '5', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('6', 'GR01', '3', '6', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('7', 'GR02', '3', '7', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('8', 'MO01', '3', '8', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('9', 'MO02', '3', '9', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('10', '0001', '4', '10', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('11', '0002', '4', '11', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('12', '0003', '4', '12', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('13', '0004', '4', '13', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('14', '0005', '4', '14', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('15', 'G001', '5', '15', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('16', 'G002', '5', '16', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('17', 'M001', '6', '17', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('18', 'M001', '6', '18', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('19', 'P001', '7', '19', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('20', 'P002', '7', '20', '1', '1', '2022-09-05 00:00:00', NULL, NULL),
('21', 'P002', '1', NULL, NULL, '1', '2022-09-05 00:00:00', NULL, NULL),
('22', 'P002', '1', NULL, NULL, '1', '2022-09-05 00:00:00', NULL, NULL),
('23', 'P002', '7', NULL, NULL, '1', '2022-09-05 00:00:00', NULL, NULL);

CREATE TABLE IF NOT EXISTS `dsms_partsInst`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `quantity` INT NOT NULL COMMENT '零件數量',
    `snCode` VARCHAR(50) DEFAULT NULL COMMENT '零件序號碼',
    `partsId` INT NOT NULL COMMENT '零件id(取自dsms_parts-id)',
    `statusId` INT NOT NULL COMMENT '零件狀態(取自dsms_status-id)',
    `positionId` INT NOT NULL COMMENT '零件儲位(取自dsms_position-id)',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `dsms_partsInst` VALUES
('1', '3', null, '1', '1', '1'),
('2', '1', null, '2', '1', '2'),
('3', '2', null, '6', '1', '6');

CREATE TABLE IF NOT EXISTS `dsms_history`(
	`id` INT NOT NULL AUTO_INCREMENT COMMENT '主鍵id',
    `eventDate` DATETIME NOT NULL COMMENT '發生時間',
    `eventContent` VARCHAR(100) NOT NULL COMMENT '內容',
    `userId` INT NOT NULL COMMENT '執行的使用者(取自dsms_user-id)',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;