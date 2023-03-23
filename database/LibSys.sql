/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : libsys

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 21/03/2023 15:54:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `ISBN` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bookname` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `author` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `typeID` int(0) NULL DEFAULT NULL,
  `publisher` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `stock` int(0) NULL DEFAULT NULL,
  `borrow` int(0) NULL DEFAULT 0,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`ISBN`) USING BTREE,
  INDEX `typeID`(`typeID`) USING BTREE,
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`typeID`) REFERENCES `types` (`typeID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('9787111495482', '被讨厌的勇气', '岸見一郎, 古賀史健', 1, '机械工业出版社', 2, 1, 33);
INSERT INTO `books` VALUES ('9787111577973', '定位：争夺用户心智的战争', '艾·里斯', 0, '机械工业出版社', 5, 0, 39.5);
INSERT INTO `books` VALUES ('9787115410047', '社会心理学', '戴维·迈尔斯', 1, '人民邮电出版社', 3, 0, 106.5);
INSERT INTO `books` VALUES ('9787115546081', 'Python编程 从入门到实践 第2版', '埃里克·马瑟斯', 5, '人民邮电出版社', 10, 1, 41);
INSERT INTO `books` VALUES ('9787302622635', '软件平台架构设计与技术管理之道', '由维昭', 5, '清华大学出版社', 7, 0, 62.4);
INSERT INTO `books` VALUES ('9787503449482', '红楼梦', '曹雪芹', 7, '中国文史出版社', 8, 0, 21.3);
INSERT INTO `books` VALUES ('9787506396295', '红楼梦', '曹雪芹', 7, '作家出版社', 2, 1, 29.5);
INSERT INTO `books` VALUES ('9787516833391', '杨绛传', '吴牧宸', 8, '台海出版社', 6, 1, 24.6);
INSERT INTO `books` VALUES ('9787530221532', '活着', '余华', 7, '北京十月文艺出版社', 8, 0, 31);
INSERT INTO `books` VALUES ('9787533681678', '红楼梦', '曹雪芹', 7, '安徽教育出版社', 15, 0, 11.3);
INSERT INTO `books` VALUES ('9787539672625', '孙子兵法', '孙武', 0, '安徽文艺出版社', 3, 1, 25.5);
INSERT INTO `books` VALUES ('9787540484880', '苏东坡传', '林语堂', 8, '湖南文艺出版社', 2, 0, 26);
INSERT INTO `books` VALUES ('9787541154294', '童年', '高尔基', 7, '四川文艺出版社', 2, 0, 15.5);
INSERT INTO `books` VALUES ('9787547724873', '骆驼祥子', '老舍', 7, '北京日报出版社', 4, 1, 16.7);
INSERT INTO `books` VALUES ('9787559638182', '丈量世界：500条经典铁路路线中的世界史', '萨拉·巴克斯特', 8, '北京联合出版有限公司', 2, 0, 90);
INSERT INTO `books` VALUES ('9787560097152', '新编大学德语', '朱建华等', 7, '外语教学与研究出版社', 2, 2, 39.3);
INSERT INTO `books` VALUES ('9787568707138', '鲁迅杂文集', '鲁迅', 7, '湘潭大学出版社', 8, 0, 44.3);
INSERT INTO `books` VALUES ('9787807048992', '新编世界地图册（2023年版）', '成都地图出版社', 8, '成都地图出版社', 3, 0, 14.2);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `ISBN` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `readerID` int(0) NOT NULL,
  `borrowDate` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `staffID` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ISBN`, `readerID`) USING BTREE,
  INDEX `readerID`(`readerID`) USING BTREE,
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `books` (`ISBN`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`readerID`) REFERENCES `readers` (`readerID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('9787111495482', 20221103, '2023-03-19', '10011001');
INSERT INTO `borrow` VALUES ('9787115546081', 20221114, '2023-03-20', '10011002');
INSERT INTO `borrow` VALUES ('9787506396295', 20221109, '2023-03-20', '10011001');
INSERT INTO `borrow` VALUES ('9787516833391', 20221101, '2023-03-12', '10011001');
INSERT INTO `borrow` VALUES ('9787539672625', 20221101, '2023-03-16', '10011001');
INSERT INTO `borrow` VALUES ('9787547724873', 20221101, '2023-03-16', '10011001');
INSERT INTO `borrow` VALUES ('9787560097152', 20221101, '2023-03-16', '10011001');
INSERT INTO `borrow` VALUES ('9787560097152', 20221103, '2023-03-10', '10011001');

-- ----------------------------
-- Table structure for readers
-- ----------------------------
DROP TABLE IF EXISTS `readers`;
CREATE TABLE `readers`  (
  `readerID` int(0) NOT NULL AUTO_INCREMENT,
  `readerName` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`readerID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20221115 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of readers
-- ----------------------------
INSERT INTO `readers` VALUES (20221101, '刘一');
INSERT INTO `readers` VALUES (20221102, '王二');
INSERT INTO `readers` VALUES (20221103, '张三');
INSERT INTO `readers` VALUES (20221104, '李四');
INSERT INTO `readers` VALUES (20221105, '赵五');
INSERT INTO `readers` VALUES (20221106, '何六');
INSERT INTO `readers` VALUES (20221107, '陈七');
INSERT INTO `readers` VALUES (20221108, '梁八');
INSERT INTO `readers` VALUES (20221109, '李明');
INSERT INTO `readers` VALUES (20221110, '王丽');
INSERT INTO `readers` VALUES (20221111, '刘红');
INSERT INTO `readers` VALUES (20221112, '何亮');
INSERT INTO `readers` VALUES (20221113, '陈小');
INSERT INTO `readers` VALUES (20221114, '何乐');
INSERT INTO `readers` VALUES (20221117, '李明');
INSERT INTO `readers` VALUES (20221118, 'null');
INSERT INTO `readers` VALUES (20221119, 'null');

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `typeID` int(0) NOT NULL,
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  INDEX `typeID`(`typeID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES (0, '科学与知识');
INSERT INTO `types` VALUES (1, '哲学、心理学');
INSERT INTO `types` VALUES (2, '宗教、神学');
INSERT INTO `types` VALUES (3, '社会科学');
INSERT INTO `types` VALUES (4, '数学、自然科学');
INSERT INTO `types` VALUES (5, '应用科学、医学、科技');
INSERT INTO `types` VALUES (6, '艺术、娱乐、休闲、体育');
INSERT INTO `types` VALUES (7, '语言、语言学、文学');
INSERT INTO `types` VALUES (8, '地理、传记、历史');
INSERT INTO `types` VALUES (9, '其它');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `entryyear` int(0) NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10011001', '李丽丽', '女', 2021, '0000', 1);
INSERT INTO `user` VALUES ('10011002', '王红', '女', 2021, '10011002', 1);
INSERT INTO `user` VALUES ('10011003', '李梅', '女', 2021, '10011003', 1);
INSERT INTO `user` VALUES ('10011004', '张明', '男', 2021, '10011004', 1);
INSERT INTO `user` VALUES ('123456', '系统管理员', NULL, NULL, '123456', 0);

-- ----------------------------
-- Triggers structure for table types
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_type_upd`;
delimiter ;;
CREATE TRIGGER `tri_type_upd` BEFORE UPDATE ON `types` FOR EACH ROW SET new.typeID = old.typeID, new.type = old.type
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
