/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50617
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50617
 File Encoding         : 65001

 Date: 04/09/2019 15:28:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for batch
-- ----------------------------
DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字符串',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '测试日期值',
  `sal` decimal(10, 2) NULL DEFAULT NULL COMMENT '测试double 值',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试枚举值',
  `success` bit(1) NULL DEFAULT NULL COMMENT '测试bool 值',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `idcard` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `job` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试批量添加' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `empno` decimal(4, 0) NULL DEFAULT NULL,
  `job` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sal` decimal(10, 0) NULL DEFAULT NULL,
  `comm` decimal(10, 0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `dname` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `loc` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES ('20', 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES ('30', 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES ('40', 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `empno` decimal(4, 0) NOT NULL COMMENT '员工编号',
  `ename` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `job` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `mgr` decimal(4, 0) NULL DEFAULT NULL COMMENT '上级编号',
  `hiredate` datetime(0) NULL DEFAULT NULL COMMENT '雇佣日期',
  `sal` decimal(7, 2) NULL DEFAULT NULL COMMENT '薪水',
  `comm` decimal(7, 2) NULL DEFAULT NULL COMMENT '奖金',
  `deptno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17 00:00:00', 800.00, NULL, '20');
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20 00:00:00', 1600.00, 300.00, '30');
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22 00:00:00', 1250.00, 500.00, '30');
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02 00:00:00', 2975.00, NULL, '20');
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28 00:00:00', 1250.00, 1400.00, '30');
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01 00:00:00', 2850.00, NULL, '30');
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09 00:00:00', 2450.00, NULL, '10');
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09 00:00:00', 3000.00, NULL, '20');
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17 00:00:00', 5000.00, NULL, '10');
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08 00:00:00', 1500.00, 0.00, '30');
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1983-01-12 00:00:00', 1100.00, NULL, '20');
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03 00:00:00', 950.00, NULL, '30');
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03 00:00:00', 3000.00, NULL, '20');
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23 00:00:00', 1300.00, NULL, '10');

-- ----------------------------
-- Table structure for salgrade
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade`  (
  `grade` decimal(10, 0) NULL DEFAULT NULL,
  `losal` decimal(10, 0) NULL DEFAULT NULL,
  `hisal` decimal(10, 0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of salgrade
-- ----------------------------
INSERT INTO `salgrade` VALUES (1, 700, 1200);
INSERT INTO `salgrade` VALUES (2, 1201, 1400);
INSERT INTO `salgrade` VALUES (3, 1401, 2000);
INSERT INTO `salgrade` VALUES (4, 2001, 3000);
INSERT INTO `salgrade` VALUES (5, 3001, 9999);

SET FOREIGN_KEY_CHECKS = 1;
