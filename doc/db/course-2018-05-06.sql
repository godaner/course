/*
 Navicat Premium Data Transfer

 Source Server         : 39.108.231.231
 Source Server Type    : MySQL
 Source Server Version : 50633
 Source Host           : 39.108.231.231:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50633
 File Encoding         : 65001

 Date: 06/05/2018 19:44:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '唯一用户名',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码，md5加密',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, 'r99', '456', 3, 1519352848, 1519353697, '敖德萨多撒');
INSERT INTO `admins` VALUES (2, 'r99', '123', 3, 1519353887, 1519353913, '奥术大师多');
INSERT INTO `admins` VALUES (3, 'r999', '465', 3, 1519353923, 1519354954, '456');
INSERT INTO `admins` VALUES (4, '12313', '123', 3, 1519355035, 1519355035, '');
INSERT INTO `admins` VALUES (5, 'zhangke', '1234', 1, 1519355056, 1519693145, '这是张大可的管理员这是张大可的管理员这是张大可的管理员这是张大可的管理员');
INSERT INTO `admins` VALUES (6, 'root', '123', 1, 1519361180, 1519361180, '');
INSERT INTO `admins` VALUES (7, 'godaner', 'godaner', 1, 1519364370, 1519364370, 'godaner');
INSERT INTO `admins` VALUES (8, 'ergouz', 'ergouz', 1, 1519364384, 1524560448, 'ergouz');

-- ----------------------------
-- Table structure for course_sources
-- ----------------------------
DROP TABLE IF EXISTS `course_sources`;
CREATE TABLE `course_sources`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `course_id` int(11) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `watch_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `download_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course_sources
-- ----------------------------
INSERT INTO `course_sources` VALUES (1, 1, 1, 1, 1, '第一节', '/courses/src/2', 1, '这是第一节', '/courses/download/1');
INSERT INTO `course_sources` VALUES (2, 1, 1, 1, 1, '第二节', '/courses/src/1', 1, '这是第二节', '/courses/download/1');
INSERT INTO `course_sources` VALUES (3, 3, 1519699868, 1519699868, 11251, '12313', '/courses/src/90eb1d77-602d-42ab-9432-e3b3e16cda38', 0, '123131', '/courses/download/90eb1d77-602d-42ab-9432-e3b3e16cda38');
INSERT INTO `course_sources` VALUES (4, 3, 1519700117, 1519700117, 11251, '123131', '/courses/src/c52a8f53-67d2-44a1-917c-25b6bf4a27da', 0, '12313112313', '/courses/download/c52a8f53-67d2-44a1-917c-25b6bf4a27da');
INSERT INTO `course_sources` VALUES (5, 3, 1519700336, 1519700336, 11251, '123131', '/courses/src/262d9bf5-77ea-488b-8640-e5d278942010', 0, '12313', '/courses/download/262d9bf5-77ea-488b-8640-e5d278942010');
INSERT INTO `course_sources` VALUES (6, 3, 1519706773, 1519706773, 11251, '1231212313', '/courses/src/d7ca9d62-5040-488d-a642-4816405e01b2', 0, '12313', '/courses/download/d7ca9d62-5040-488d-a642-4816405e01b2');
INSERT INTO `course_sources` VALUES (7, 3, 1519706785, 1519706785, 11251, '13131as', '/courses/src/c6647fc3-b8dc-4d14-aae8-09662359f74c', 0, 'aaa', '/courses/download/c6647fc3-b8dc-4d14-aae8-09662359f74c');
INSERT INTO `course_sources` VALUES (8, 3, 1519706873, 1519706873, 11251, '12', '/courses/src/391abfc4-a10d-4c49-a270-0bc5cb264a27', 0, '', '/courses/download/391abfc4-a10d-4c49-a270-0bc5cb264a27');
INSERT INTO `course_sources` VALUES (9, 3, 1524560770, 1524560770, 11251, '', '/courses/src/87dcc067-292f-413d-9f45-22fec8c53b1e', 0, '', '/courses/download/87dcc067-292f-413d-9f45-22fec8c53b1e');
INSERT INTO `course_sources` VALUES (10, 3, 1524560785, 1524560785, 11251, 'kljl', '/courses/src/05f33448-3cb0-4c35-8703-0583c29927b2', 0, 'ddd', '/courses/download/05f33448-3cb0-4c35-8703-0583c29927b2');
INSERT INTO `course_sources` VALUES (11, 3, 1524566504, 1524566504, 11251, '123', '/courses/src/833c1935-ca08-4383-8ee4-c9e9fa47dc80', 0, '123', '/courses/download/833c1935-ca08-4383-8ee4-c9e9fa47dc80');
INSERT INTO `course_sources` VALUES (12, 1, 1524566868, 1524566868, 12, '1', '/courses/src/df70d1d1-38aa-469b-8264-aa50daced4ca', 0, '12', '/courses/download/df70d1d1-38aa-469b-8264-aa50daced4ca');
INSERT INTO `course_sources` VALUES (13, 1, 1524715910, 1524715910, 11252, '01_课程安排', '/courses/src/b9d755c3-7383-461c-b4df-70f6183cbd8a', 0, '', '/courses/download/b9d755c3-7383-461c-b4df-70f6183cbd8a');
INSERT INTO `course_sources` VALUES (14, 1, 1524716150, 1524716150, 11252, '02_程序进阶之路', '/courses/src/44458b46-dd20-4132-995b-0e8408392c1a', 0, '', '/courses/download/44458b46-dd20-4132-995b-0e8408392c1a');
INSERT INTO `course_sources` VALUES (15, 1, 1524716329, 1524716329, 11252, '03_听课要求', '/courses/src/aa781c8e-5364-4e96-8b0c-2c4ed9a4af37', 0, '', '/courses/download/aa781c8e-5364-4e96-8b0c-2c4ed9a4af37');
INSERT INTO `course_sources` VALUES (16, 1, 1524716353, 1524716353, 11252, '04_学习重点', '/courses/src/cb43b79a-4a24-4a73-9a9c-837c9255d40e', 0, '', '/courses/download/cb43b79a-4a24-4a73-9a9c-837c9255d40e');
INSERT INTO `course_sources` VALUES (17, 1, 1524716393, 1524716393, 11252, '05_cs和bs区别', '/courses/src/09baa17a-6de3-470b-8ea3-6c3b2efccb10', 0, '', '/courses/download/09baa17a-6de3-470b-8ea3-6c3b2efccb10');
INSERT INTO `course_sources` VALUES (18, 1, 1524718192, 1524718192, 11257, '01_初识Ajax', '/courses/src/2113cad0-2bc8-461b-8ccb-7e626dd322b8', 0, '', '/courses/download/2113cad0-2bc8-461b-8ccb-7e626dd322b8');
INSERT INTO `course_sources` VALUES (19, 1, 1524718249, 1524718249, 11257, '02_服务器与客户端概念', '/courses/src/36d73db3-fe1e-447f-9277-4222e0c85e93', 0, '', '/courses/download/36d73db3-fe1e-447f-9277-4222e0c85e93');
INSERT INTO `course_sources` VALUES (20, 1, 1524718284, 1524718284, 11257, '03_网路相关的概念', '/courses/src/45270a9e-0083-4e6b-a6f5-8d01d4797893', 0, '', '/courses/download/45270a9e-0083-4e6b-a6f5-8d01d4797893');
INSERT INTO `course_sources` VALUES (21, 1, 1524718605, 1524718605, 11259, '00.Scrapy架构流程介绍', '/courses/src/fc660d13-804e-4b9e-9554-c85aa8c0bc8a', 0, '', '/courses/download/fc660d13-804e-4b9e-9554-c85aa8c0bc8a');
INSERT INTO `course_sources` VALUES (22, 1, 1524718629, 1524718629, 11259, '01.Scrapy框架配置安装和命令介绍', '/courses/src/c7942208-2ba7-4bf4-9872-93174f2b4c41', 0, '', '/courses/download/c7942208-2ba7-4bf4-9872-93174f2b4c41');
INSERT INTO `course_sources` VALUES (23, 1, 1524718648, 1524718648, 11259, '02.Scrapy项目的基本使用', '/courses/src/60f63586-40c8-41dd-a8b8-301ed4be5e96', 0, '', '/courses/download/60f63586-40c8-41dd-a8b8-301ed4be5e96');
INSERT INTO `course_sources` VALUES (24, 1, 1524718690, 1524718690, 11259, '03.案例：ITcast小爬虫 与管道文件', '/courses/src/7d2a5678-5388-480d-970d-8c312ce96023', 0, '', '/courses/download/7d2a5678-5388-480d-970d-8c312ce96023');
INSERT INTO `course_sources` VALUES (25, 1, 1524718708, 1524718708, 11259, '04.案例：ITcast小爬虫', '/courses/src/e1540a1a-c127-4008-b0f0-6ec71e0d60ee', 0, '', '/courses/download/e1540a1a-c127-4008-b0f0-6ec71e0d60ee');
INSERT INTO `course_sources` VALUES (26, 1, 1524718726, 1524718726, 11259, '05.scrapy shell以及scrapy selector使用', '/courses/src/6842c200-e495-4143-909c-c5a1958cc7d2', 0, '', '/courses/download/6842c200-e495-4143-909c-c5a1958cc7d2');
INSERT INTO `course_sources` VALUES (27, 3, 1524718745, 1524718745, 11259, '06.案例：Tencent招聘信息采集', '/courses/src/a4fb8b4d-afbc-4e08-bdcd-c049436fdae6', 0, '', '/courses/download/a4fb8b4d-afbc-4e08-bdcd-c049436fdae6');
INSERT INTO `course_sources` VALUES (28, 3, 1524718767, 1524718767, 11259, '07.案例：Tencent招聘信息采集2', '/courses/src/f95f5c54-d48d-419b-b28d-f01c306e8a6b', 0, '', '/courses/download/f95f5c54-d48d-419b-b28d-f01c306e8a6b');
INSERT INTO `course_sources` VALUES (29, 1, 1525488777, 1525488777, 11264, '123', '/courses/src/a76df81f-be3e-46ad-8b19-cc31e75ea869', 0, '123', '/courses/download/a76df81f-be3e-46ad-8b19-cc31e75ea869');

-- ----------------------------
-- Table structure for course_tag_realations
-- ----------------------------
DROP TABLE IF EXISTS `course_tag_realations`;
CREATE TABLE `course_tag_realations`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `tag_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course_tag_realations
-- ----------------------------
INSERT INTO `course_tag_realations` VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `course_tag_realations` VALUES (2, 1, 1, 1, 2, 1);
INSERT INTO `course_tag_realations` VALUES (3, 1, 1, 1, 3, 1);
INSERT INTO `course_tag_realations` VALUES (4, 3, 1, 1, 1, 2);
INSERT INTO `course_tag_realations` VALUES (5, 3, 1, 1, 2, 2);
INSERT INTO `course_tag_realations` VALUES (6, 3, 1, 1, 3, 2);
INSERT INTO `course_tag_realations` VALUES (7, 3, 1, 1, 4, 2);
INSERT INTO `course_tag_realations` VALUES (8, 3, 1519373993, 1519373993, 4, 2);
INSERT INTO `course_tag_realations` VALUES (9, 3, 1519373993, 1519373993, 5, 2);
INSERT INTO `course_tag_realations` VALUES (10, 3, 1519373993, 1519373993, 6, 2);
INSERT INTO `course_tag_realations` VALUES (11, 3, 1519374004, 1519374004, 1, 11251);
INSERT INTO `course_tag_realations` VALUES (12, 3, 1519374004, 1519374004, 2, 11251);
INSERT INTO `course_tag_realations` VALUES (13, 3, 1519374004, 1519374004, 3, 11251);
INSERT INTO `course_tag_realations` VALUES (14, 3, 1519374004, 1519374004, 4, 11251);
INSERT INTO `course_tag_realations` VALUES (15, 3, 1519374004, 1519374004, 5, 11251);
INSERT INTO `course_tag_realations` VALUES (16, 3, 1519374004, 1519374004, 6, 11251);
INSERT INTO `course_tag_realations` VALUES (17, 3, 1519374224, 1519374224, 1, 11251);
INSERT INTO `course_tag_realations` VALUES (18, 3, 1519374224, 1519374224, 2, 11251);
INSERT INTO `course_tag_realations` VALUES (19, 3, 1519374224, 1519374224, 3, 11251);
INSERT INTO `course_tag_realations` VALUES (20, 3, 1519374224, 1519374224, 4, 11251);
INSERT INTO `course_tag_realations` VALUES (21, 3, 1519374224, 1519374224, 5, 11251);
INSERT INTO `course_tag_realations` VALUES (22, 3, 1519374321, 1519374321, 6, 4);
INSERT INTO `course_tag_realations` VALUES (23, 3, 1519374408, 1519374408, 6, 3);
INSERT INTO `course_tag_realations` VALUES (24, 1, 1519374414, 1519374414, 3, 3);
INSERT INTO `course_tag_realations` VALUES (25, 1, 1519374414, 1519374414, 4, 3);
INSERT INTO `course_tag_realations` VALUES (26, 1, 1519374414, 1519374414, 6, 3);
INSERT INTO `course_tag_realations` VALUES (27, 3, 1519374459, 1519374459, 1, 11251);
INSERT INTO `course_tag_realations` VALUES (28, 3, 1519374459, 1519374459, 2, 11251);
INSERT INTO `course_tag_realations` VALUES (29, 3, 1519374459, 1519374459, 3, 11251);
INSERT INTO `course_tag_realations` VALUES (30, 3, 1519374459, 1519374459, 4, 11251);
INSERT INTO `course_tag_realations` VALUES (31, 3, 1519374459, 1519374459, 5, 11251);
INSERT INTO `course_tag_realations` VALUES (32, 3, 1519374459, 1519374459, 6, 11251);
INSERT INTO `course_tag_realations` VALUES (33, 3, 1519375574, 1519375574, 1, 11251);
INSERT INTO `course_tag_realations` VALUES (34, 3, 1519375574, 1519375574, 2, 11251);
INSERT INTO `course_tag_realations` VALUES (35, 3, 1519375574, 1519375574, 3, 11251);
INSERT INTO `course_tag_realations` VALUES (36, 3, 1519375574, 1519375574, 4, 11251);
INSERT INTO `course_tag_realations` VALUES (37, 3, 1519375574, 1519375574, 5, 11251);
INSERT INTO `course_tag_realations` VALUES (38, 3, 1519606519, 1519606519, 7, 11251);
INSERT INTO `course_tag_realations` VALUES (39, 3, 1519606519, 1519606519, 8, 11251);
INSERT INTO `course_tag_realations` VALUES (40, 3, 1519610220, 1519610220, 12, 11251);
INSERT INTO `course_tag_realations` VALUES (41, 3, 1519610220, 1519610220, 13, 11251);
INSERT INTO `course_tag_realations` VALUES (42, 3, 1519610318, 1519610318, 15, 11251);
INSERT INTO `course_tag_realations` VALUES (43, 3, 1519610318, 1519610318, 16, 11251);
INSERT INTO `course_tag_realations` VALUES (44, 3, 1519610318, 1519610318, 14, 11251);
INSERT INTO `course_tag_realations` VALUES (45, 3, 1519610318, 1519610318, 12, 11251);
INSERT INTO `course_tag_realations` VALUES (46, 3, 1519610318, 1519610318, 13, 11251);
INSERT INTO `course_tag_realations` VALUES (47, 1, 1519610324, 1519610324, 16, 2);
INSERT INTO `course_tag_realations` VALUES (48, 1, 1519610324, 1519610324, 14, 2);
INSERT INTO `course_tag_realations` VALUES (49, 1, 1519610328, 1519610328, 15, 4);
INSERT INTO `course_tag_realations` VALUES (50, 1, 1519695334, 1519695334, 17, 11251);
INSERT INTO `course_tag_realations` VALUES (51, 1, 1519695334, 1519695334, 15, 11251);
INSERT INTO `course_tag_realations` VALUES (52, 1, 1519695334, 1519695334, 16, 11251);
INSERT INTO `course_tag_realations` VALUES (53, 1, 1519695334, 1519695334, 14, 11251);
INSERT INTO `course_tag_realations` VALUES (54, 1, 1519695334, 1519695334, 12, 11251);
INSERT INTO `course_tag_realations` VALUES (55, 1, 1519695334, 1519695334, 13, 11251);
INSERT INTO `course_tag_realations` VALUES (56, 1, 1524633652, 1524633652, 25, 11252);
INSERT INTO `course_tag_realations` VALUES (57, 1, 1524633652, 1524633652, 22, 11252);
INSERT INTO `course_tag_realations` VALUES (58, 1, 1524634741, 1524634741, 26, 11253);
INSERT INTO `course_tag_realations` VALUES (59, 1, 1524634741, 1524634741, 22, 11253);
INSERT INTO `course_tag_realations` VALUES (60, 1, 1524634929, 1524634929, 26, 11254);
INSERT INTO `course_tag_realations` VALUES (61, 1, 1524634929, 1524634929, 23, 11254);
INSERT INTO `course_tag_realations` VALUES (62, 1, 1524634929, 1524634929, 12, 11254);
INSERT INTO `course_tag_realations` VALUES (63, 1, 1524635139, 1524635139, 28, 11255);
INSERT INTO `course_tag_realations` VALUES (64, 1, 1524635139, 1524635139, 23, 11255);
INSERT INTO `course_tag_realations` VALUES (65, 1, 1524635139, 1524635139, 12, 11255);
INSERT INTO `course_tag_realations` VALUES (66, 1, 1524635247, 1524635247, 28, 11256);
INSERT INTO `course_tag_realations` VALUES (67, 1, 1524635247, 1524635247, 23, 11256);
INSERT INTO `course_tag_realations` VALUES (68, 1, 1524635337, 1524635337, 28, 11257);
INSERT INTO `course_tag_realations` VALUES (69, 1, 1524635337, 1524635337, 22, 11257);
INSERT INTO `course_tag_realations` VALUES (70, 1, 1524635459, 1524635459, 27, 11258);
INSERT INTO `course_tag_realations` VALUES (71, 1, 1524635459, 1524635459, 21, 11258);
INSERT INTO `course_tag_realations` VALUES (72, 1, 1524635570, 1524635570, 27, 11259);
INSERT INTO `course_tag_realations` VALUES (73, 1, 1524635570, 1524635570, 22, 11259);
INSERT INTO `course_tag_realations` VALUES (74, 1, 1524635710, 1524635710, 29, 11260);
INSERT INTO `course_tag_realations` VALUES (75, 1, 1524635710, 1524635710, 21, 11260);
INSERT INTO `course_tag_realations` VALUES (76, 1, 1524636098, 1524636098, 29, 11261);
INSERT INTO `course_tag_realations` VALUES (77, 1, 1524636098, 1524636098, 23, 11261);
INSERT INTO `course_tag_realations` VALUES (78, 1, 1524636166, 1524636166, 25, 11262);
INSERT INTO `course_tag_realations` VALUES (79, 1, 1524636166, 1524636166, 21, 11262);
INSERT INTO `course_tag_realations` VALUES (80, 3, 1524636247, 1524636247, 26, 11263);
INSERT INTO `course_tag_realations` VALUES (81, 3, 1524636247, 1524636247, 23, 11263);
INSERT INTO `course_tag_realations` VALUES (82, 1, 1524636402, 1524636402, 26, 11263);
INSERT INTO `course_tag_realations` VALUES (83, 1, 1524636402, 1524636402, 23, 11263);
INSERT INTO `course_tag_realations` VALUES (84, 3, 1524636452, 1524636452, 26, 11264);
INSERT INTO `course_tag_realations` VALUES (85, 3, 1524636452, 1524636452, 22, 11264);
INSERT INTO `course_tag_realations` VALUES (86, 1, 1525339662, 1525339662, 26, 11264);
INSERT INTO `course_tag_realations` VALUES (87, 1, 1525339662, 1525339662, 27, 11264);
INSERT INTO `course_tag_realations` VALUES (88, 1, 1525339662, 1525339662, 22, 11264);
INSERT INTO `course_tag_realations` VALUES (89, 1, 1525339662, 1525339662, 13, 11264);
INSERT INTO `course_tag_realations` VALUES (90, 1, 1525595695, 1525595695, 28, 11266);
INSERT INTO `course_tag_realations` VALUES (91, 1, 1525595695, 1525595695, 22, 11266);
INSERT INTO `course_tag_realations` VALUES (92, 1, 1525595704, 1525595704, 27, 11265);
INSERT INTO `course_tag_realations` VALUES (93, 1, 1525595704, 1525595704, 22, 11265);
INSERT INTO `course_tag_realations` VALUES (94, 1, 1525595797, 1525595797, 28, 11267);
INSERT INTO `course_tag_realations` VALUES (95, 1, 1525595797, 1525595797, 23, 11267);
INSERT INTO `course_tag_realations` VALUES (96, 1, 1525595880, 1525595880, 26, 11268);
INSERT INTO `course_tag_realations` VALUES (97, 1, 1525595880, 1525595880, 22, 11268);
INSERT INTO `course_tag_realations` VALUES (98, 1, 1525595880, 1525595880, 12, 11268);

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像url',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `download_number` int(11) DEFAULT NULL,
  `collect_number` int(11) DEFAULT NULL,
  `watch_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11269 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 3, 1, 1518421497, '/courses/img/c5950c05-8bff-47c1-8e31-43d0d00fb7f8', 'asd', '666', 28, 23, 111);
INSERT INTO `courses` VALUES (2, 3, 1, 1519361202, '/courses/img/02c4a229-639f-4083-a678-a3fbe6f8c079', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (3, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (4, 3, 1, 1518422998, '/courses/img/1639dbaf-d871-45b7-a977-2820ca077524', 'unity3d入门到放弃', '学习不好aa ', 888, 222, 0);
INSERT INTO `courses` VALUES (11, 3, 1, 1518421592, '/courses/img/2ecbd709-514a-4aa4-84e9-b68b53b8ca26', 'java学习', 'java学习java学习ja真的学习', 4564, 1, 0);
INSERT INTO `courses` VALUES (12, 3, 1, 1, '/courses/img/30f76dc8-61d0-494d-9590-a5cd02cbbae7', 'cc学习', '学习不好', 2, 888, 5);
INSERT INTO `courses` VALUES (13, 3, 1, 1, '/courses/img/965c632f-244c-4373-8281-afefac0c0295', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (14, 3, 1, 1, '/courses/img/b354b834-004e-466d-8804-7ce32f6f508f', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (15, 3, 1, 1, '/courses/img/df4c8bd2-de22-4863-b62c-025ed4e9c9c3', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (16, 3, 1, 1, '/courses/img/16447561-652b-4fcd-9d90-62e8ddff2417', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (17, 3, 1, 1, '/courses/img/a99c4114-bb8e-4036-aaab-0e3666694b28', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (18, 3, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 892, 0);
INSERT INTO `courses` VALUES (121, 3, 1, 1518422983, '/courses/img/e96345b1-a6dc-4d0f-9698-3a460662de4b', '.net学习', '.net学习.net学习.net学习', 4564, 1, 0);
INSERT INTO `courses` VALUES (122, 3, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (123, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (124, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (125, 3, 1, 1, '/courses/img/808da9b2-abbc-49fe-8999-ff0370d15ff5', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (126, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (128, 3, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 889, 0);
INSERT INTO `courses` VALUES (139, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (149, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (217, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1123, 3, 1, 1, '/courses/img/e8311250-f813-4d59-bfe1-f3cb03b355ac', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1124, 3, 1, 1, '/courses/img/8680a1d6-51fc-4880-8726-2b0db2430c09', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1126, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1217, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1239, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1249, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (11239, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 223, 0);
INSERT INTO `courses` VALUES (11249, 3, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (11250, 3, 1519363953, 1519363953, '/courses/img/66cc2ad7-01e3-4f09-babd-b2153c672cb7', '123', '阿萨德', 0, 0, 0);
INSERT INTO `courses` VALUES (11251, 3, 1519364186, 1519364207, '/courses/img/6201df3a-e692-47d7-89cd-71c4dd163c9e', 'java', 'java厉害了', 0, 0, 3);
INSERT INTO `courses` VALUES (11252, 1, 1524633554, 1525595252, '/courses/img/23491c1a-60ac-49b7-ae61-c8a1f1a222c1', '2017版 C++基础教程完整版', '第一阶段C语言提高; 第二阶段数据结构; 第三阶段C++开发编程;', 1, 0, 15);
INSERT INTO `courses` VALUES (11253, 1, 1524634658, 1525595268, '/courses/img/f922476f-ada0-4726-a31f-18ec597f1c57', 'JS全面教程', '', 0, 0, 0);
INSERT INTO `courses` VALUES (11254, 1, 1524634883, 1525595280, '/courses/img/9bc65b0c-6cf0-455e-978d-3efb2974424c', 'JavaWeb图书管理系统视频', 'JavaWeb图书管理系统主要目的是实现图书馆的信息化管理。图书馆的主要业务就是新书的借阅和归还，因此系统最核心的功能便是实现图书的借阅和归还。', 0, 0, 0);
INSERT INTO `courses` VALUES (11256, 1, 1524635217, 1525595295, '/courses/img/9062c877-ca3c-4792-a1b7-1b37a29d5bfb', '百度外卖项目教程', 'Web前端进阶案例练习，运用ajax和JavaScript完成手机端页面。', 0, 0, 0);
INSERT INTO `courses` VALUES (11257, 1, 1524635313, 1525595307, '/courses/img/e29e28a7-23e6-4d8d-89b9-6dbf586c062d', ' Ajax从入门到精通', '1、异步      2、XMLHttpRequest      3、XML      4、Jason      5、兼容性      6、封装Ajax工具函数      7、jQuery中的ajax      8、案例练习', 0, 0, 5);
INSERT INTO `courses` VALUES (11258, 1, 1524635420, 1525595326, '/courses/img/d4dbe96c-7c00-4b6d-88e7-c70ebcfd6463', 'Python入门教程完整版', '本套教程15天  学前环境搭建  1-3   天内容为Linux基础命令  4-13  天内容为Python基础教程  14-15 天内容为 飞机大战项目演练', 0, 0, 0);
INSERT INTO `courses` VALUES (11261, 1, 1524636011, 1525595341, '/courses/img/a66cb891-53ab-4741-8412-f4c61fbc2c63', 'PHP办公留言系统', '', 0, 0, 0);
INSERT INTO `courses` VALUES (11262, 3, 1524636132, 1524636152, '/courses/img/58ae67d8-0095-4cb4-9411-4f97dba9f7ee', 'C语言小白入门教程', '', 0, 0, 0);
INSERT INTO `courses` VALUES (11264, 1, 1524636406, 1525595357, '/courses/img/1d01ed9e-2762-47b6-b54d-1ba3879d2bc5', 'Linux网络编程', '', 0, 0, 3);
INSERT INTO `courses` VALUES (11265, 1, 1525595548, 1525595562, '/courses/img/c6620b7f-7731-4916-8e21-e1afbedf607e', 'Scrapy爬虫框架', '此课程为Python学科爬虫项目实战Scrapy框架课程。', 0, 0, 0);
INSERT INTO `courses` VALUES (11266, 1, 1525595642, 1525595662, '/courses/img/3ced42bc-79fe-40f3-b7f6-98c33b7c15be', '美食美刻网站制作实战', '本视频系统的讲解了如何制作一个网站，该视频的起点是针对有一定的html+css+JavaScript基础的同学。', 0, 0, 0);
INSERT INTO `courses` VALUES (11267, 1, 1525595763, 1525595775, '/courses/img/69c88fa3-779c-4b54-8097-e5f4c7d43474', 'Vue2.0从入门到实战', '', 0, 0, 0);
INSERT INTO `courses` VALUES (11268, 1, 1525595858, 1525595869, '/courses/img/1208562d-992f-4508-9391-5a6ad48aa970', 'Javaweb毕设图书管理系统', 'JavaWeb图书管理系统主要目的是实现图书馆的信息化管理。', 0, 0, 0);

-- ----------------------------
-- Table structure for tag_group_realations
-- ----------------------------
DROP TABLE IF EXISTS `tag_group_realations`;
CREATE TABLE `tag_group_realations`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `tag_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag_group_realations
-- ----------------------------
INSERT INTO `tag_group_realations` VALUES (1, 3, 1, 1, 1, 1);
INSERT INTO `tag_group_realations` VALUES (2, 3, 2, 2, 2, 1);
INSERT INTO `tag_group_realations` VALUES (3, 3, 1, 1, 1, 2);
INSERT INTO `tag_group_realations` VALUES (4, 3, 1, 1, 2, 2);
INSERT INTO `tag_group_realations` VALUES (5, 3, 1, 1, 5, 3);
INSERT INTO `tag_group_realations` VALUES (6, 3, 1, 1, 6, 3);
INSERT INTO `tag_group_realations` VALUES (7, 3, 1519606428, 1519606428, 7, 9);
INSERT INTO `tag_group_realations` VALUES (8, 3, 1519606437, 1519606437, 8, 9);
INSERT INTO `tag_group_realations` VALUES (9, 3, 1519609077, 1519609077, 9, 9);
INSERT INTO `tag_group_realations` VALUES (10, 3, 1519609081, 1519609081, 10, 9);
INSERT INTO `tag_group_realations` VALUES (11, 3, 1519609701, 1519609701, 11, 10);
INSERT INTO `tag_group_realations` VALUES (12, 1, 1519609735, 1519609735, 12, 11);
INSERT INTO `tag_group_realations` VALUES (13, 1, 1519609744, 1519609744, 13, 11);
INSERT INTO `tag_group_realations` VALUES (14, 1, 1519610269, 1519610269, 14, 12);
INSERT INTO `tag_group_realations` VALUES (15, 1, 1519610291, 1519610291, 15, 13);
INSERT INTO `tag_group_realations` VALUES (16, 1, 1519610297, 1519610297, 16, 12);
INSERT INTO `tag_group_realations` VALUES (17, 1, 1519610516, 1519610516, 17, 13);
INSERT INTO `tag_group_realations` VALUES (18, 1, 1524630220, 1524630220, 18, 13);
INSERT INTO `tag_group_realations` VALUES (19, 1, 1524630256, 1524630256, 19, 13);
INSERT INTO `tag_group_realations` VALUES (20, 1, 1524630414, 1524630414, 20, 12);
INSERT INTO `tag_group_realations` VALUES (21, 1, 1524630440, 1524630440, 21, 12);
INSERT INTO `tag_group_realations` VALUES (22, 1, 1524630456, 1524630456, 22, 12);
INSERT INTO `tag_group_realations` VALUES (23, 1, 1524630478, 1524630478, 23, 12);
INSERT INTO `tag_group_realations` VALUES (24, 1, 1524630618, 1524630618, 24, 13);
INSERT INTO `tag_group_realations` VALUES (25, 1, 1524630764, 1524630764, 25, 13);
INSERT INTO `tag_group_realations` VALUES (26, 1, 1524630806, 1524630806, 26, 13);
INSERT INTO `tag_group_realations` VALUES (27, 1, 1524630831, 1524630831, 27, 13);
INSERT INTO `tag_group_realations` VALUES (28, 1, 1524630864, 1524630864, 28, 13);
INSERT INTO `tag_group_realations` VALUES (29, 1, 1524630902, 1524630902, 29, 13);
INSERT INTO `tag_group_realations` VALUES (30, 1, 1524630935, 1524630935, 30, 13);
INSERT INTO `tag_group_realations` VALUES (31, 1, 1524630986, 1524630986, 31, 13);
INSERT INTO `tag_group_realations` VALUES (32, 1, 1525339607, 1525339607, 32, 13);
INSERT INTO `tag_group_realations` VALUES (33, 1, 1525339611, 1525339611, 33, 12);
INSERT INTO `tag_group_realations` VALUES (34, 1, 1525595980, 1525595980, 34, 13);

-- ----------------------------
-- Table structure for tag_groups
-- ----------------------------
DROP TABLE IF EXISTS `tag_groups`;
CREATE TABLE `tag_groups`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag_groups
-- ----------------------------
INSERT INTO `tag_groups` VALUES (1, 3, 1, 1519450102, '类型2');
INSERT INTO `tag_groups` VALUES (2, 3, 1, 1, '难度');
INSERT INTO `tag_groups` VALUES (3, 3, 1, 1, '毕设');
INSERT INTO `tag_groups` VALUES (4, 3, 1519434987, 1519450127, '1231233213');
INSERT INTO `tag_groups` VALUES (5, 3, 1519439705, 1519450137, '驱蚊器二');
INSERT INTO `tag_groups` VALUES (6, 3, 1519450196, 1519450196, '123');
INSERT INTO `tag_groups` VALUES (7, 3, 1519458897, 1519458897, '123123');
INSERT INTO `tag_groups` VALUES (8, 3, 1519458917, 1519458917, '123123阿斯达多');
INSERT INTO `tag_groups` VALUES (9, 3, 1519458921, 1519609056, '123123阿斯达多阿斯达多');
INSERT INTO `tag_groups` VALUES (10, 3, 1519609066, 1519609066, '11');
INSERT INTO `tag_groups` VALUES (11, 1, 1519609726, 1519609726, '毕业设计');
INSERT INTO `tag_groups` VALUES (12, 1, 1519610251, 1524630570, '难度等级');
INSERT INTO `tag_groups` VALUES (13, 1, 1519610258, 1524630602, '学科分类');
INSERT INTO `tag_groups` VALUES (14, 3, 1525339712, 1525339712, '12313');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, 3, 1, 1, 'java');
INSERT INTO `tags` VALUES (2, 3, 1, 1, 'c');
INSERT INTO `tags` VALUES (3, 3, 1, 1, '初级');
INSERT INTO `tags` VALUES (4, 3, 1, 1, '中级');
INSERT INTO `tags` VALUES (5, 3, 1, 1, 'java毕设');
INSERT INTO `tags` VALUES (6, 3, 1, 1, 'c毕设');
INSERT INTO `tags` VALUES (7, 3, 1519606428, 1519606428, '12313');
INSERT INTO `tags` VALUES (8, 3, 1519606437, 1519606437, '789798');
INSERT INTO `tags` VALUES (9, 3, 1519609076, 1519609076, '1');
INSERT INTO `tags` VALUES (10, 3, 1519609081, 1519609081, '2');
INSERT INTO `tags` VALUES (11, 3, 1519609700, 1519609700, '1231');
INSERT INTO `tags` VALUES (12, 1, 1519609735, 1519609735, 'java毕业设计');
INSERT INTO `tags` VALUES (13, 1, 1519609744, 1519609744, 'c语言毕业设计');
INSERT INTO `tags` VALUES (14, 3, 1519610269, 1519610269, '入门');
INSERT INTO `tags` VALUES (15, 3, 1519610291, 1519610291, '很冷');
INSERT INTO `tags` VALUES (16, 3, 1519610297, 1519610297, '初级');
INSERT INTO `tags` VALUES (17, 3, 1519610516, 1519610516, '很热');
INSERT INTO `tags` VALUES (18, 3, 1524630219, 1524630219, '最新');
INSERT INTO `tags` VALUES (19, 3, 1524630254, 1524630254, '最热');
INSERT INTO `tags` VALUES (20, 3, 1524630414, 1524630414, '全部');
INSERT INTO `tags` VALUES (21, 1, 1524630439, 1524630439, '初级');
INSERT INTO `tags` VALUES (22, 1, 1524630455, 1524630455, '中级');
INSERT INTO `tags` VALUES (23, 1, 1524630478, 1524630478, '高级');
INSERT INTO `tags` VALUES (24, 3, 1524630618, 1524630618, 'java');
INSERT INTO `tags` VALUES (25, 1, 1524630764, 1524630764, 'C/C++');
INSERT INTO `tags` VALUES (26, 1, 1524630805, 1524630805, 'Java');
INSERT INTO `tags` VALUES (27, 1, 1524630830, 1524630830, 'Python');
INSERT INTO `tags` VALUES (28, 1, 1524630863, 1524630863, 'Web前端');
INSERT INTO `tags` VALUES (29, 1, 1524630900, 1524630900, 'PHP');
INSERT INTO `tags` VALUES (30, 1, 1524630935, 1524630935, 'UI/UE');
INSERT INTO `tags` VALUES (31, 1, 1524630986, 1524630986, 'Android');
INSERT INTO `tags` VALUES (32, 3, 1525339607, 1525339607, '123');
INSERT INTO `tags` VALUES (33, 3, 1525339611, 1525339611, '12313131');
INSERT INTO `tags` VALUES (34, 1, 1525595980, 1525595980, '云计算大数据');

-- ----------------------------
-- Table structure for user_collect_course
-- ----------------------------
DROP TABLE IF EXISTS `user_collect_course`;
CREATE TABLE `user_collect_course`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_collect_course
-- ----------------------------
INSERT INTO `user_collect_course` VALUES (3, 1, 1517881999, 1517881999, 217, 45);
INSERT INTO `user_collect_course` VALUES (4, 1, 1517882175, 1517882175, 1, 45);
INSERT INTO `user_collect_course` VALUES (5, 1, 1517882193, 1517882193, 11, 45);
INSERT INTO `user_collect_course` VALUES (6, 1, 1517901287, 1517901287, 13, 45);
INSERT INTO `user_collect_course` VALUES (7, 1, 1517902745, 1517902745, 1123, 45);
INSERT INTO `user_collect_course` VALUES (8, 1, 1517974487, 1517974487, 2, 45);
INSERT INTO `user_collect_course` VALUES (9, 1, 1517983062, 1517983062, 1124, 45);
INSERT INTO `user_collect_course` VALUES (10, 1, 1518094667, 1518094667, 4, 52);
INSERT INTO `user_collect_course` VALUES (11, 1, 1518169700, 1518169700, 217, 52);
INSERT INTO `user_collect_course` VALUES (12, 1, 1524558116, 1524558116, 11239, 66);
INSERT INTO `user_collect_course` VALUES (13, 1, 1524566791, 1524566791, 122, 66);
INSERT INTO `user_collect_course` VALUES (14, 1, 1524566911, 1524566911, 12, 66);
INSERT INTO `user_collect_course` VALUES (15, 1, 1524572809, 1524572809, 149, 66);
INSERT INTO `user_collect_course` VALUES (16, 1, 1524716787, 1524716787, 11252, 66);
INSERT INTO `user_collect_course` VALUES (17, 1, 1525436769, 1525436769, 11260, 1);
INSERT INTO `user_collect_course` VALUES (18, 1, 1525438871, 1525438871, 11261, 1);
INSERT INTO `user_collect_course` VALUES (19, 1, 1525438882, 1525438882, 11255, 1);
INSERT INTO `user_collect_course` VALUES (20, 1, 1525439316, 1525439316, 11263, 1);
INSERT INTO `user_collect_course` VALUES (21, 1, 1525439325, 1525439325, 11259, 1);

-- ----------------------------
-- Table structure for user_download_course
-- ----------------------------
DROP TABLE IF EXISTS `user_download_course`;
CREATE TABLE `user_download_course`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_download_course
-- ----------------------------
INSERT INTO `user_download_course` VALUES (3, 1, 1517884989, 1517884989, 1, 45);
INSERT INTO `user_download_course` VALUES (4, 1, 1517884996, 1517884996, 1, 45);
INSERT INTO `user_download_course` VALUES (5, 1, 1517885014, 1517885014, 1, 45);
INSERT INTO `user_download_course` VALUES (6, 1, 1517885078, 1517885078, 1, 45);
INSERT INTO `user_download_course` VALUES (7, 1, 1517885218, 1517885218, 1, 45);
INSERT INTO `user_download_course` VALUES (8, 1, 1517885291, 1517885291, 1, 45);
INSERT INTO `user_download_course` VALUES (9, 1, 1517885796, 1517885796, 1, 45);
INSERT INTO `user_download_course` VALUES (10, 1, 1517887909, 1517887909, 1, 45);
INSERT INTO `user_download_course` VALUES (11, 1, 1517904015, 1517904015, 1, 45);
INSERT INTO `user_download_course` VALUES (12, 1, 1517904024, 1517904024, 1, 45);
INSERT INTO `user_download_course` VALUES (13, 1, 1517904031, 1517904031, 1, 45);
INSERT INTO `user_download_course` VALUES (14, 1, 1517966898, 1517966898, 1, 45);
INSERT INTO `user_download_course` VALUES (15, 1, 1518169712, 1518169712, 1, 52);
INSERT INTO `user_download_course` VALUES (16, 1, 1524566899, 1524566899, 12, 66);
INSERT INTO `user_download_course` VALUES (17, 1, 1524716471, 1524716471, 11252, 1);

-- ----------------------------
-- Table structure for user_watch_course
-- ----------------------------
DROP TABLE IF EXISTS `user_watch_course`;
CREATE TABLE `user_watch_course`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_watch_course_copy1
-- ----------------------------
DROP TABLE IF EXISTS `user_watch_course_copy1`;
CREATE TABLE `user_watch_course_copy1`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `create_time` int(10) UNSIGNED NOT NULL COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL COMMENT '更新时间',
  `status` tinyint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态,1为正常，2为冻结，3为删除',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '唯一用户名',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码，md5加密',
  `sex` tinyint(2) UNSIGNED NOT NULL DEFAULT 3 COMMENT '性别，1为男，2为女，3未设置',
  `birthday` int(10) UNSIGNED DEFAULT NULL COMMENT '用户生日',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户描述',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '前端用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 1517461275, 1525439355, 1, 'root', '123', 3, 1517461275, '6666666666666666666666', '/users/img/a8858f31-d995-41f7-bff1-39699c50bd85');
INSERT INTO `users` VALUES (42, 1517463452, 1518404762, 3, 'root1', '123', 2, 1517463452, '阿斯达多奥术大师多阿萨德撒', '/users/img/40d4e110-2565-4a94-a9d0-900d515a9226');
INSERT INTO `users` VALUES (43, 1517463845, 1518404207, 3, 'root122', '123', 2, 1517463845, '我最刘', '/users/img/5d554a77-99a5-40de-a55f-082a916e9e0c');
INSERT INTO `users` VALUES (44, 1517465402, 1517465402, 3, 'rootaaaaa', '123', 3, 1517465402, '我最刘', '/users/img/fb178928-797d-43fe-bac6-d4ce5dd6d7b5');
INSERT INTO `users` VALUES (45, 1517465415, 1518311634, 3, 'rootaaaaaaaa', '123', 1, 1517465415, '我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚', '/users/img/a52412f6-ea67-42da-bb98-fc8f3059d2a3');
INSERT INTO `users` VALUES (46, 1517483434, 1517483434, 3, 'leg', '123', 3, 1517483434, '我最刘', '/users/img/d45b43b1-ae90-4d5a-ba00-12cfa1941a43');
INSERT INTO `users` VALUES (47, 1518094407, 1525598259, 1, 'r1', '123', 3, 1518094407, '', '/users/img/4f0140ad-4155-45e1-b8cb-f17772962052');
INSERT INTO `users` VALUES (48, 1518094421, 1525598247, 1, 'r2', '123', 3, 1518094421, '', '/users/img/12c7944b-69c0-4f71-87b8-da9e2107c9e5');
INSERT INTO `users` VALUES (49, 1518094433, 1525598232, 1, 'r3', '123', 3, 1518094433, '', '/users/img/6cd1098d-e247-45bf-b30e-cbc4533b7649');
INSERT INTO `users` VALUES (50, 1518094446, 1525598218, 1, 'r4', '1234', 1, 1518094446, '', '/users/img/b019845a-7d74-4308-90ec-7d5964dbce79');
INSERT INTO `users` VALUES (51, 1518094590, 1525598207, 1, 'r6', '123', 3, 1518094590, '', '/users/img/4966f726-baac-4cc3-8d71-41eca6ac328c');
INSERT INTO `users` VALUES (52, 1518094614, 1524559339, 3, 'r99', '123', 1, 1518094614, '666', '/users/img/a8978e74-7356-491a-8774-b2f3bb7867a2');
INSERT INTO `users` VALUES (53, 1518426501, 1519353966, 3, 'r999999', '789', 3, 1518426501, '', '/users/img/6d00ccdf-6f28-4b10-a53c-314f4f4e4d98');
INSERT INTO `users` VALUES (54, 1518427070, 1525496765, 2, 'zhangke', '123', 2, 1518427070, '123', '/users/img/16cee1ec-945a-4845-9939-21eaf36d1b35');
INSERT INTO `users` VALUES (55, 1519347581, 1519347581, 3, 'godaner', '159357', 3, 1519347581, '', '/users/img/d85e00c6-2af4-42a4-bb99-81ad9d32d3ac');
INSERT INTO `users` VALUES (56, 1519347686, 1519347686, 3, 'godaner02', '123', 3, 1519347686, '', '/users/img/332a70b1-6aed-464d-88b6-30d5a41f7ca8');
INSERT INTO `users` VALUES (61, 1519348451, 1519348451, 3, 'godaner02', '123', 3, 1519348451, '', '/users/img/cd426a72-1162-401e-8b7b-8b4bb5e945d2');
INSERT INTO `users` VALUES (62, 1519348776, 1519348776, 3, 'r99123', '123', 3, 1519348776, '', '/users/img/72b2ea76-5208-4110-b107-b2fe07e7004c');
INSERT INTO `users` VALUES (63, 1519348831, 1519348831, 3, 'r99asd', '123', 1, 1519348831, '', '/users/img/2eb2a371-bab8-4c85-af81-bc5f9d7e92e3');
INSERT INTO `users` VALUES (64, 1519348839, 1519348839, 3, 'r99asdasdads', '123', 1, 1519348839, 'asdada', '/users/img/e38b5e49-c4e1-452c-831d-eddb0c3f6823');
INSERT INTO `users` VALUES (65, 1519360479, 1519360492, 3, 'r99123', '123', 1, 1519360479, '1232131', '/users/img/aef29ae4-0a56-43d0-86d8-d3830e065574');
INSERT INTO `users` VALUES (66, 1519702691, 1524629862, 1, 'xiaoyu', '123', 2, 1519702691, '', '/users/img/af1cd340-df2c-4b71-85e9-fe38c65544c9');
INSERT INTO `users` VALUES (67, 1524560047, 1524560047, 3, 'gougou', '123', 1, 1524560047, '', '/users/img/43b81b12-c362-4d11-9bff-77dd3603568a');
INSERT INTO `users` VALUES (68, 1525501683, 1525598195, 1, 'root1', '123', 1, 1525501683, '123', '/users/img/72f02a20-6cba-4d80-a929-207d5c1ea872');

SET FOREIGN_KEY_CHECKS = 1;
