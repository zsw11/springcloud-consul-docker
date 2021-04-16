/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : springcloud_consul

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 16/04/2021 14:53:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'zsw', 10);
INSERT INTO `account` VALUES (2, 'bbb', 1000);
INSERT INTO `account` VALUES (3, 'ccc', 1000);
INSERT INTO `account` VALUES (4, '祖尔特额', 111111.223);
INSERT INTO `account` VALUES (5, '祖尔特额', 111111.223);
INSERT INTO `account` VALUES (6, '直达2', 1111211.223);
INSERT INTO `account` VALUES (7, '直达2', 1111211.223);
INSERT INTO `account` VALUES (8, '直达2', 1111211.223);
INSERT INTO `account` VALUES (9, '直达2', 1111211.223);
INSERT INTO `account` VALUES (10, '直达2', 1111211.223);
INSERT INTO `account` VALUES (11, '直达2', 1111211.223);
INSERT INTO `account` VALUES (12, '\"zs2\"', 2);

-- ----------------------------
-- Table structure for mail_send_log
-- ----------------------------
DROP TABLE IF EXISTS `mail_send_log`;
CREATE TABLE `mail_send_log`  (
  `msg_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `emp_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `route_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exchange` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `try_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息队列mall日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for parent
-- ----------------------------
DROP TABLE IF EXISTS `parent`;
CREATE TABLE `parent`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `p_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户父母信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parent
-- ----------------------------
INSERT INTO `parent` VALUES (1, 26, 'zse');
INSERT INTO `parent` VALUES (2, 28, '2323eeee');
INSERT INTO `parent` VALUES (3, 31, '2dfa');

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
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_0
-- ----------------------------
INSERT INTO `user_0` VALUES (10, 'forezp10', '1233edwd');
INSERT INTO `user_0` VALUES (20, 'forezp20', '1233edwd');
INSERT INTO `user_0` VALUES (30, 'forezp30', '1233edwd');
INSERT INTO `user_0` VALUES (40, 'forezp40', '1233edwd');
INSERT INTO `user_0` VALUES (50, 'forezp50', '1233edwd');
INSERT INTO `user_0` VALUES (60, 'forezp60', '1233edwd');
INSERT INTO `user_0` VALUES (70, 'forezp70', '1233edwd');
INSERT INTO `user_0` VALUES (80, 'forezp80', '1233edwd');
INSERT INTO `user_0` VALUES (90, 'forezp90', '1233edwd');
INSERT INTO `user_0` VALUES (100, 'forezp100', '1233edwd');
INSERT INTO `user_0` VALUES (101, '123', '123');
INSERT INTO `user_0` VALUES (102, '123', '123');
INSERT INTO `user_0` VALUES (103, '123', '123');
INSERT INTO `user_0` VALUES (104, '123', '123');
INSERT INTO `user_0` VALUES (105, '123', '123');
INSERT INTO `user_0` VALUES (106, '123', '123');
INSERT INTO `user_0` VALUES (107, '123', '123');

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
) ENGINE = InnoDB AUTO_INCREMENT = 277 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_1
-- ----------------------------
INSERT INTO `user_1` VALUES (6, 'forezp6', '1233edwd');
INSERT INTO `user_1` VALUES (16, 'forezp16', '1233edwd');
INSERT INTO `user_1` VALUES (26, 'forezp26', '1233edwd');
INSERT INTO `user_1` VALUES (36, 'forezp36', '1233edwd');
INSERT INTO `user_1` VALUES (46, 'forezp46', '1233edwd');
INSERT INTO `user_1` VALUES (56, 'forezp56', '1233edwd');
INSERT INTO `user_1` VALUES (66, 'forezp66', '1233edwd');
INSERT INTO `user_1` VALUES (76, 'forezp76', '1233edwd');
INSERT INTO `user_1` VALUES (86, 'forezp86', '1233edwd');
INSERT INTO `user_1` VALUES (96, 'forezp96', '1233edwd');

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
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_2
-- ----------------------------
INSERT INTO `user_2` VALUES (2, 'forezp2', '1233edwd');
INSERT INTO `user_2` VALUES (12, 'forezp12', '1233edwd');
INSERT INTO `user_2` VALUES (22, 'forezp22', '1233edwd');
INSERT INTO `user_2` VALUES (32, 'forezp32', '1233edwd');
INSERT INTO `user_2` VALUES (42, 'forezp42', '1233edwd');
INSERT INTO `user_2` VALUES (52, 'forezp52', '1233edwd');
INSERT INTO `user_2` VALUES (62, 'forezp62', '1233edwd');
INSERT INTO `user_2` VALUES (72, 'forezp72', '1233edwd');
INSERT INTO `user_2` VALUES (82, 'forezp82', '1233edwd');
INSERT INTO `user_2` VALUES (92, 'forezp92', '1233edwd');

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
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_3
-- ----------------------------
INSERT INTO `user_3` VALUES (8, 'forezp8', '1233edwd');
INSERT INTO `user_3` VALUES (18, 'forezp18', '1233edwd');
INSERT INTO `user_3` VALUES (28, 'forezp28', '1233edwd');
INSERT INTO `user_3` VALUES (38, 'forezp38', '1233edwd');
INSERT INTO `user_3` VALUES (48, 'forezp48', '1233edwd');
INSERT INTO `user_3` VALUES (58, 'forezp58', '1233edwd');
INSERT INTO `user_3` VALUES (68, 'forezp68', '1233edwd');
INSERT INTO `user_3` VALUES (78, 'forezp78', '1233edwd');
INSERT INTO `user_3` VALUES (88, 'forezp88', '1233edwd');
INSERT INTO `user_3` VALUES (98, 'forezp98', '1233edwd');

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
) ENGINE = InnoDB AUTO_INCREMENT = 275 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_4
-- ----------------------------
INSERT INTO `user_4` VALUES (4, 'forezp4', '1233edwd');
INSERT INTO `user_4` VALUES (14, 'forezp14', '1233edwd');
INSERT INTO `user_4` VALUES (24, 'forezp24', '1233edwd');
INSERT INTO `user_4` VALUES (34, 'forezp34', '1233edwd');
INSERT INTO `user_4` VALUES (44, 'forezp44', '1233edwd');
INSERT INTO `user_4` VALUES (54, 'forezp54', '1233edwd');
INSERT INTO `user_4` VALUES (64, 'forezp64', '1233edwd');
INSERT INTO `user_4` VALUES (74, 'forezp74', '1233edwd');
INSERT INTO `user_4` VALUES (84, 'forezp84', '1233edwd');
INSERT INTO `user_4` VALUES (94, 'forezp94', '1233edwd');
INSERT INTO `user_4` VALUES (104, '123', '123');
INSERT INTO `user_4` VALUES (124, '123', '123');
INSERT INTO `user_4` VALUES (274, 'zsw分布式事务测试', '123456');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lock_endtime` datetime(0) NULL DEFAULT NULL COMMENT '锁定结束时间',
  `fail_number` int(11) NULL DEFAULT NULL COMMENT '登录失败次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'zsw', '123456', '111', NULL, NULL, 1);

-- ----------------------------
-- Table structure for user_old
-- ----------------------------
DROP TABLE IF EXISTS `user_old`;
CREATE TABLE `user_old`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `age` int(11) NULL DEFAULT NULL COMMENT '用户年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `blog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户博客地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_old
-- ----------------------------
INSERT INTO `user_old` VALUES (31, 'dsfdas', 12, '广州天河区', '1111', '546232194@qq.com', '222');
INSERT INTO `user_old` VALUES (40, '111', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (44, '111133', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (49, '郑天宇姐12', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (54, '郑天宇姐121221', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (56, '郑212211', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (58, '郑21212111', 111, '111', '111', '546232182@qq.com', '111');
INSERT INTO `user_old` VALUES (60, 'z周露', 34, '天河区', '1234', '3434@qq.com', '2www.ccc');
INSERT INTO `user_old` VALUES (61, '走访大', 23, '天河', '2222', '3242@qq.com', 'eeee');
INSERT INTO `user_old` VALUES (62, '更新地址', 10, '地址更新', '111111111', '4444@qq.com', 'www.bb.com');
INSERT INTO `user_old` VALUES (64, 'eeee', 10, 'string', '444444', '33333@qq.com', 'string');
INSERT INTO `user_old` VALUES (65, 'zsw', 40, '日娜河区', '111111', '666666@qq.com', 'www.biobk.com');
INSERT INTO `user_old` VALUES (66, 'string', 10, '22', '333', '33@qq.com', '222');
INSERT INTO `user_old` VALUES (67, 'fasd456', 14, 'dfa法', '520', '66@qq.com', 'dasfd');
INSERT INTO `user_old` VALUES (69, 'fasd45116', 14, 'dfa法', '520', '66@qq.com', 'dasfd');
INSERT INTO `user_old` VALUES (70, 'ZW', 10, 'www', '1111', '33@qq.COM', 'www');
INSERT INTO `user_old` VALUES (72, 'ZW11', 10, 'www', '1111', '33@qq.COM', 'www');
INSERT INTO `user_old` VALUES (74, 'dffdf分布式', 10, 'www', '11111', '3333@qq.com', '2222');
INSERT INTO `user_old` VALUES (79, 'dffdf分布式11', 10, 'www', '11111', '3333@qq.com', '2222');

SET FOREIGN_KEY_CHECKS = 1;
