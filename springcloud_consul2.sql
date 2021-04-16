/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : springcloud_consul2

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 16/04/2021 14:53:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_0
-- ----------------------------
INSERT INTO `user_0` VALUES (5, 'forezp5', '1233edwd');
INSERT INTO `user_0` VALUES (15, 'forezp15', '1233edwd');
INSERT INTO `user_0` VALUES (25, 'forezp25', '1233edwd');
INSERT INTO `user_0` VALUES (35, 'forezp35', '1233edwd');
INSERT INTO `user_0` VALUES (45, 'forezp45', '1233edwd');
INSERT INTO `user_0` VALUES (55, 'forezp55', '1233edwd');
INSERT INTO `user_0` VALUES (65, 'forezp65', '1233edwd');
INSERT INTO `user_0` VALUES (75, 'forezp75', '1233edwd');
INSERT INTO `user_0` VALUES (85, 'forezp85', '1233edwd');
INSERT INTO `user_0` VALUES (95, 'forezp95', '1233edwd');

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_1
-- ----------------------------
INSERT INTO `user_1` VALUES (1, 'forezp1', '1233edwd');
INSERT INTO `user_1` VALUES (11, 'forezp11', '1233edwd');
INSERT INTO `user_1` VALUES (21, 'forezp21', '1233edwd');
INSERT INTO `user_1` VALUES (31, 'forezp31', '1233edwd');
INSERT INTO `user_1` VALUES (41, 'forezp41', '1233edwd');
INSERT INTO `user_1` VALUES (51, 'forezp51', '1233edwd');
INSERT INTO `user_1` VALUES (61, 'forezp61', '1233edwd');
INSERT INTO `user_1` VALUES (71, 'forezp71', '1233edwd');
INSERT INTO `user_1` VALUES (81, 'forezp81', '1233edwd');
INSERT INTO `user_1` VALUES (91, 'forezp91', '1233edwd');
INSERT INTO `user_1` VALUES (211, 'zsw分布式事务测试', '123456');

-- ----------------------------
-- Table structure for user_2
-- ----------------------------
DROP TABLE IF EXISTS `user_2`;
CREATE TABLE `user_2`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_2
-- ----------------------------
INSERT INTO `user_2` VALUES (7, 'forezp7', '1233edwd');
INSERT INTO `user_2` VALUES (17, 'forezp17', '1233edwd');
INSERT INTO `user_2` VALUES (27, 'forezp27', '1233edwd');
INSERT INTO `user_2` VALUES (37, 'forezp37', '1233edwd');
INSERT INTO `user_2` VALUES (47, 'forezp47', '1233edwd');
INSERT INTO `user_2` VALUES (57, 'forezp57', '1233edwd');
INSERT INTO `user_2` VALUES (67, 'forezp67', '1233edwd');
INSERT INTO `user_2` VALUES (77, 'forezp77', '1233edwd');
INSERT INTO `user_2` VALUES (87, 'forezp87', '1233edwd');
INSERT INTO `user_2` VALUES (97, 'forezp97', '1233edwd');

-- ----------------------------
-- Table structure for user_3
-- ----------------------------
DROP TABLE IF EXISTS `user_3`;
CREATE TABLE `user_3`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_3
-- ----------------------------
INSERT INTO `user_3` VALUES (3, 'forezp3', '1233edwd');
INSERT INTO `user_3` VALUES (13, 'forezp13', '1233edwd');
INSERT INTO `user_3` VALUES (23, 'forezp23', '1233edwd');
INSERT INTO `user_3` VALUES (33, 'forezp33', '1233edwd');
INSERT INTO `user_3` VALUES (43, 'forezp43', '1233edwd');
INSERT INTO `user_3` VALUES (53, 'forezp53', '1233edwd');
INSERT INTO `user_3` VALUES (63, 'forezp63', '1233edwd');
INSERT INTO `user_3` VALUES (73, 'forezp73', '1233edwd');
INSERT INTO `user_3` VALUES (83, 'forezp83', '1233edwd');
INSERT INTO `user_3` VALUES (93, 'forezp93', '1233edwd');
INSERT INTO `user_3` VALUES (103, '123', '123');

-- ----------------------------
-- Table structure for user_4
-- ----------------------------
DROP TABLE IF EXISTS `user_4`;
CREATE TABLE `user_4`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx-username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_4
-- ----------------------------
INSERT INTO `user_4` VALUES (9, 'forezp9', '1233edwd');
INSERT INTO `user_4` VALUES (19, 'forezp19', '1233edwd');
INSERT INTO `user_4` VALUES (29, 'forezp29', '1233edwd');
INSERT INTO `user_4` VALUES (39, 'forezp39', '1233edwd');
INSERT INTO `user_4` VALUES (49, 'forezp49', '1233edwd');
INSERT INTO `user_4` VALUES (59, 'forezp59', '1233edwd');
INSERT INTO `user_4` VALUES (69, 'forezp69', '1233edwd');
INSERT INTO `user_4` VALUES (79, 'forezp79', '1233edwd');
INSERT INTO `user_4` VALUES (89, 'forezp89', '1233edwd');
INSERT INTO `user_4` VALUES (99, 'forezp99', '1233edwd');

SET FOREIGN_KEY_CHECKS = 1;
