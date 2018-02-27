/*
 Navicat Premium Data Transfer

 Source Server         : 120.78.191.94_root
 Source Server Type    : MySQL
 Source Server Version : 50633
 Source Host           : 120.78.191.94:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50633
 File Encoding         : 65001

 Date: 27/02/2018 12:50:35
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
INSERT INTO `admins` VALUES (6, 'r99', '123', 3, 1519361180, 1519361180, '');
INSERT INTO `admins` VALUES (7, 'godaner', 'godaner', 1, 1519364370, 1519364370, 'godaner');
INSERT INTO `admins` VALUES (8, 'ergouz', 'ergouz', 2, 1519364384, 1519364384, 'ergouz');

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 11252 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 3, 1, 1518421497, '/courses/img/c5950c05-8bff-47c1-8e31-43d0d00fb7f8', 'asd', '666', 28, 23, 111);
INSERT INTO `courses` VALUES (2, 1, 1, 1519361202, '/courses/img/02c4a229-639f-4083-a678-a3fbe6f8c079', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (3, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (4, 1, 1, 1518422998, '/courses/img/1639dbaf-d871-45b7-a977-2820ca077524', 'unity3d入门到放弃', '学习不好aa ', 888, 222, 0);
INSERT INTO `courses` VALUES (11, 1, 1, 1518421592, '/courses/img/2ecbd709-514a-4aa4-84e9-b68b53b8ca26', 'java学习', 'java学习java学习ja真的学习', 4564, 1, 0);
INSERT INTO `courses` VALUES (12, 1, 1, 1, '/courses/img/30f76dc8-61d0-494d-9590-a5cd02cbbae7', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (13, 1, 1, 1, '/courses/img/965c632f-244c-4373-8281-afefac0c0295', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (14, 1, 1, 1, '/courses/img/b354b834-004e-466d-8804-7ce32f6f508f', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (15, 1, 1, 1, '/courses/img/df4c8bd2-de22-4863-b62c-025ed4e9c9c3', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (16, 1, 1, 1, '/courses/img/16447561-652b-4fcd-9d90-62e8ddff2417', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (17, 1, 1, 1, '/courses/img/a99c4114-bb8e-4036-aaab-0e3666694b28', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (18, 1, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 892, 0);
INSERT INTO `courses` VALUES (121, 1, 1, 1518422983, '/courses/img/e96345b1-a6dc-4d0f-9698-3a460662de4b', '.net学习', '.net学习.net学习.net学习', 4564, 1, 0);
INSERT INTO `courses` VALUES (122, 1, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (123, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (124, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (125, 1, 1, 1, '/courses/img/808da9b2-abbc-49fe-8999-ff0370d15ff5', 'cc学习', '学习不好', 1, 888, 0);
INSERT INTO `courses` VALUES (126, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (128, 1, 1, 1, '/courses/img/1471513730333', 'cc学习', '学习不好', 1, 889, 0);
INSERT INTO `courses` VALUES (139, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (149, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (217, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1123, 1, 1, 1, '/courses/img/e8311250-f813-4d59-bfe1-f3cb03b355ac', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1124, 1, 1, 1, '/courses/img/8680a1d6-51fc-4880-8726-2b0db2430c09', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1126, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1217, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1239, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (1249, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (11239, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 223, 0);
INSERT INTO `courses` VALUES (11249, 1, 1, 1, '/courses/img/1471513730333', 'unity3d入门到放弃', '学习不好', 888, 222, 0);
INSERT INTO `courses` VALUES (11250, 2, 1519363953, 1519363953, '/courses/img/66cc2ad7-01e3-4f09-babd-b2153c672cb7', '123', '阿萨德', 0, 0, 0);
INSERT INTO `courses` VALUES (11251, 1, 1519364186, 1519364207, '/courses/img/eef1ed2d-7ab8-4a89-b2b3-bc1c0597a8f0', 'java', 'java厉害了', 0, 0, 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
INSERT INTO `tag_groups` VALUES (12, 1, 1519610251, 1519610251, '类型');
INSERT INTO `tag_groups` VALUES (13, 1, 1519610258, 1519610258, '热度');

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
INSERT INTO `tags` VALUES (14, 1, 1519610269, 1519610269, '入门');
INSERT INTO `tags` VALUES (15, 1, 1519610291, 1519610291, '很冷');
INSERT INTO `tags` VALUES (16, 1, 1519610297, 1519610297, '初级');
INSERT INTO `tags` VALUES (17, 1, 1519610516, 1519610516, '很热');

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
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '前端用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 1517461275, 1518404749, 1, 'root', '123', 3, 1517461275, '6666666666666666666666', '/users/img/19b6f9e4-9438-46e5-9e3c-ad42bf733864');
INSERT INTO `users` VALUES (42, 1517463452, 1518404762, 1, 'root1', '123', 2, 1517463452, '阿斯达多奥术大师多阿萨德撒', '/users/img/40d4e110-2565-4a94-a9d0-900d515a9226');
INSERT INTO `users` VALUES (43, 1517463845, 1518404207, 1, 'root122', '123', 2, 1517463845, '我最刘', '/users/img/5d554a77-99a5-40de-a55f-082a916e9e0c');
INSERT INTO `users` VALUES (44, 1517465402, 1517465402, 1, 'rootaaaaa', '123', 3, 1517465402, '我最刘', '/users/img/fb178928-797d-43fe-bac6-d4ce5dd6d7b5');
INSERT INTO `users` VALUES (45, 1517465415, 1518311634, 1, 'rootaaaaaaaa', '123', 1, 1517465415, '我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚我的代码时尚时尚最时尚', '/users/img/a52412f6-ea67-42da-bb98-fc8f3059d2a3');
INSERT INTO `users` VALUES (46, 1517483434, 1517483434, 1, 'leg', '123', 3, 1517483434, '我最刘', '/users/img/d45b43b1-ae90-4d5a-ba00-12cfa1941a43');
INSERT INTO `users` VALUES (47, 1518094407, 1518094407, 1, 'r1', '123', 3, 1518094407, '', '/users/img/20bff3d4-41a4-477d-a48f-fbc064cdcdb0');
INSERT INTO `users` VALUES (48, 1518094421, 1518094421, 1, 'r2', '123', 3, 1518094421, '', '/users/img/fb5a8dc3-ecbb-4bf7-af84-9224eedecbcd');
INSERT INTO `users` VALUES (49, 1518094433, 1518094433, 1, 'r3', '123', 3, 1518094433, '', '/users/img/d8c231ef-a800-4f12-afed-e5e5274e8fe5');
INSERT INTO `users` VALUES (50, 1518094446, 1519361167, 1, 'r4', '1234', 1, 1518094446, '', '/users/img/212113ea-5a90-4cf0-932f-01852922b11f');
INSERT INTO `users` VALUES (51, 1518094590, 1519361160, 1, 'r6', '123', 3, 1518094590, '', '/users/img/65307c85-bad3-4308-99bd-b1bc61ccf020');
INSERT INTO `users` VALUES (52, 1518094614, 1518427312, 1, 'r99', '123', 2, 1518094614, '666', '/users/img/a8978e74-7356-491a-8774-b2f3bb7867a2');
INSERT INTO `users` VALUES (53, 1518426501, 1519353966, 3, 'r999999', '789', 3, 1518426501, '', '/users/img/6d00ccdf-6f28-4b10-a53c-314f4f4e4d98');
INSERT INTO `users` VALUES (54, 1518427070, 1519360743, 2, 'zhangke', '123', 2, 1518427070, '123', '/users/img/c9209589-f43a-414a-9466-1dc556900fad');
INSERT INTO `users` VALUES (55, 1519347581, 1519347581, 3, 'godaner', '159357', 3, 1519347581, '', '/users/img/d85e00c6-2af4-42a4-bb99-81ad9d32d3ac');
INSERT INTO `users` VALUES (56, 1519347686, 1519347686, 3, 'godaner02', '123', 3, 1519347686, '', '/users/img/332a70b1-6aed-464d-88b6-30d5a41f7ca8');
INSERT INTO `users` VALUES (61, 1519348451, 1519348451, 3, 'godaner02', '123', 3, 1519348451, '', '/users/img/cd426a72-1162-401e-8b7b-8b4bb5e945d2');
INSERT INTO `users` VALUES (62, 1519348776, 1519348776, 3, 'r99123', '123', 3, 1519348776, '', '/users/img/72b2ea76-5208-4110-b107-b2fe07e7004c');
INSERT INTO `users` VALUES (63, 1519348831, 1519348831, 3, 'r99asd', '123', 1, 1519348831, '', '/users/img/2eb2a371-bab8-4c85-af81-bc5f9d7e92e3');
INSERT INTO `users` VALUES (64, 1519348839, 1519348839, 3, 'r99asdasdads', '123', 1, 1519348839, 'asdada', '/users/img/e38b5e49-c4e1-452c-831d-eddb0c3f6823');
INSERT INTO `users` VALUES (65, 1519360479, 1519360492, 3, 'r99123', '123', 1, 1519360479, '1232131', '/users/img/aef29ae4-0a56-43d0-86d8-d3830e065574');
INSERT INTO `users` VALUES (66, 1519702691, 1519702691, 1, 'xiaoyu', '123', 3, 1519702691, '', '/users/img/1545006b-573b-4965-a9f7-adac9d6df47e');

SET FOREIGN_KEY_CHECKS = 1;
