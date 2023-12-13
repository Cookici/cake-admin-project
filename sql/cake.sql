/*
 Navicat MySQL Data Transfer

 Source Server         : Lou's MySql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 127.0.0.1:3306
 Source Schema         : cake

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 13/12/2023 20:20:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cake_item
-- ----------------------------
DROP TABLE IF EXISTS `cake_item`;
CREATE TABLE `cake_item`  (
  `cake_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cake_item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配料名称',
  `cake_item_photo` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配料图片',
  `cake_item_price` bigint NOT NULL COMMENT '配料价格',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`cake_item_id`) USING BTREE,
  UNIQUE INDEX `cake_item_name`(`cake_item_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_item
-- ----------------------------
INSERT INTO `cake_item` VALUES (1, '果酱', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg', 2000, '2023-12-06 18:31:40', '2023-12-09 16:21:36');
INSERT INTO `cake_item` VALUES (2, '巧克力', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg', 3000, '2023-12-06 18:31:52', '2023-12-09 16:21:38');
INSERT INTO `cake_item` VALUES (3, '黄油', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg', 2500, '2023-12-06 18:32:26', '2023-12-09 16:21:40');
INSERT INTO `cake_item` VALUES (12, '鸡蛋酱', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-10/c6444574-2b59-4e93-a420-0eff01ccce7d.png', 1200, '2023-12-10 17:47:32', '2023-12-10 17:47:32');
INSERT INTO `cake_item` VALUES (13, 'test008', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/9c59bb86-ce95-4653-91db-2ba6666e84dd.png', 1123, '2023-12-11 11:34:36', '2023-12-11 11:34:45');
INSERT INTO `cake_item` VALUES (14, 'test1231', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/2723a538-e35f-44bc-bce0-d1b66cec87ac.png', 2313, '2023-12-11 14:17:26', '2023-12-11 14:17:42');
INSERT INTO `cake_item` VALUES (15, 'tets12312', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-13/8d3fcd3c-4607-4019-b1e1-b0ebf83bda4f.png', 23111, '2023-12-13 18:36:09', '2023-12-13 19:00:11');

-- ----------------------------
-- Table structure for cake_label
-- ----------------------------
DROP TABLE IF EXISTS `cake_label`;
CREATE TABLE `cake_label`  (
  `cake_label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cake_label_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
  `cake_label_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签样式',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`cake_label_id`) USING BTREE,
  UNIQUE INDEX `cake_label_name`(`cake_label_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_label
-- ----------------------------
INSERT INTO `cake_label` VALUES (1, '普通', 'circle', '2023-12-04 17:12:18', '2023-12-11 09:44:58');
INSERT INTO `cake_label` VALUES (2, '新品', 'star', '2023-12-06 18:30:25', '2023-12-11 09:41:17');
INSERT INTO `cake_label` VALUES (3, '热销', 'fire', '2023-12-06 18:30:32', '2023-12-11 09:41:21');

-- ----------------------------
-- Table structure for cake_permit
-- ----------------------------
DROP TABLE IF EXISTS `cake_permit`;
CREATE TABLE `cake_permit`  (
  `cake_permit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `cake_permit_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cake_permit_description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限描述',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`cake_permit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_permit
-- ----------------------------
INSERT INTO `cake_permit` VALUES (1, 'test:user', '测试用户', '2023-12-12 11:20:27');
INSERT INTO `cake_permit` VALUES (2, 'test:admin', '测试管理', '2023-12-12 11:20:27');
INSERT INTO `cake_permit` VALUES (7, 'item:*', '配料所有权限', '2023-12-13 14:56:11');
INSERT INTO `cake_permit` VALUES (8, 'label:*', '标签所有权限', '2023-12-13 14:56:23');
INSERT INTO `cake_permit` VALUES (10, 'product:*', '产品所有权限', '2023-12-13 14:56:58');
INSERT INTO `cake_permit` VALUES (11, 'role:*', '角色所有权限', '2023-12-13 14:57:08');
INSERT INTO `cake_permit` VALUES (12, 'role-permit:*', '角色权限所有权限', '2023-12-13 14:57:27');
INSERT INTO `cake_permit` VALUES (13, 'product-label:*', '产品标签所有权限', '2023-12-13 14:57:47');
INSERT INTO `cake_permit` VALUES (14, 'user:*', '用户所有权限', '2023-12-13 14:57:58');
INSERT INTO `cake_permit` VALUES (15, 'oss:*', 'oss所有权限', '2023-12-13 14:58:12');
INSERT INTO `cake_permit` VALUES (16, 'permit:*', '顶级权限', '2023-12-13 18:30:45');
INSERT INTO `cake_permit` VALUES (17, 'permit:list', '查看权限', '2023-12-13 18:31:09');

-- ----------------------------
-- Table structure for cake_product
-- ----------------------------
DROP TABLE IF EXISTS `cake_product`;
CREATE TABLE `cake_product`  (
  `cake_product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cake_product_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '蛋糕名称',
  `cake_product_price` bigint NOT NULL COMMENT '蛋糕价格',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `cake_product_photo` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`cake_product_id`) USING BTREE,
  UNIQUE INDEX `cake_product_name`(`cake_product_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 273 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_product
-- ----------------------------
INSERT INTO `cake_product` VALUES (1, '面包片', 1500, '2023-12-06 18:32:58', '2023-12-08 10:00:00', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_product` VALUES (2, '豆沙面包', 3500, '2023-12-06 18:33:37', '2023-12-08 10:00:01', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_product` VALUES (3, '鸡蛋蛋糕', 2000, '2023-12-06 18:33:55', '2023-12-08 10:00:02', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_product` VALUES (4, '奶皮蛋糕', 2500, '2023-12-06 18:34:05', '2023-12-08 10:00:03', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_product` VALUES (5, '奶酪蛋糕', 4000, '2023-12-06 18:34:40', '2023-12-08 10:00:04', 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_product` VALUES (267, 'test1111', 123, '2023-12-10 22:05:59', '2023-12-10 22:06:12', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-10/ad6885c9-c63e-49a5-94a3-44f3d3b23299.png');
INSERT INTO `cake_product` VALUES (268, 'test007', 123, '2023-12-11 11:34:02', '2023-12-11 11:34:14', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/aa891c59-c56f-4dc1-9f9c-6b6a847c40fa.png');
INSERT INTO `cake_product` VALUES (269, 'test09', 123, '2023-12-11 18:12:00', '2023-12-11 18:16:41', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/c1142acb-6cd2-4c0f-a481-9315750f15a7.png');
INSERT INTO `cake_product` VALUES (271, 'test011', 23, '2023-12-11 18:20:11', '2023-12-11 18:20:35', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/488353d5-1ab4-4b00-9b95-887f4f7feb00.png');
INSERT INTO `cake_product` VALUES (272, 'test021', 123, '2023-12-11 18:30:56', '2023-12-11 18:31:23', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-11/adc2eeb4-6374-4a66-95eb-e43ff9550fec.png');
INSERT INTO `cake_product` VALUES (274, 'tets0213', 1321311, '2023-12-13 18:35:39', '2023-12-13 18:59:56', 'https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-13/9e7ee3c8-c188-4853-9a28-0b6004f8b662.png');

-- ----------------------------
-- Table structure for cake_product_label
-- ----------------------------
DROP TABLE IF EXISTS `cake_product_label`;
CREATE TABLE `cake_product_label`  (
  `product_label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cake_product_id` bigint NOT NULL COMMENT '蛋糕ID',
  `cake_label_id` bigint NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`product_label_id`) USING BTREE,
  UNIQUE INDEX `cake_product_id`(`cake_product_id`, `cake_label_id`) USING BTREE,
  INDEX `cake_label_id`(`cake_label_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_product_label
-- ----------------------------
INSERT INTO `cake_product_label` VALUES (26, 4, 1);
INSERT INTO `cake_product_label` VALUES (25, 4, 2);
INSERT INTO `cake_product_label` VALUES (24, 4, 3);
INSERT INTO `cake_product_label` VALUES (23, 5, 2);
INSERT INTO `cake_product_label` VALUES (22, 5, 3);
INSERT INTO `cake_product_label` VALUES (21, 267, 1);
INSERT INTO `cake_product_label` VALUES (20, 267, 2);
INSERT INTO `cake_product_label` VALUES (19, 267, 3);
INSERT INTO `cake_product_label` VALUES (17, 268, 2);
INSERT INTO `cake_product_label` VALUES (14, 268, 3);
INSERT INTO `cake_product_label` VALUES (32, 269, 14);
INSERT INTO `cake_product_label` VALUES (29, 271, 1);
INSERT INTO `cake_product_label` VALUES (28, 271, 2);
INSERT INTO `cake_product_label` VALUES (27, 271, 3);
INSERT INTO `cake_product_label` VALUES (41, 272, 3);
INSERT INTO `cake_product_label` VALUES (42, 272, 14);
INSERT INTO `cake_product_label` VALUES (43, 274, 3);

-- ----------------------------
-- Table structure for cake_role
-- ----------------------------
DROP TABLE IF EXISTS `cake_role`;
CREATE TABLE `cake_role`  (
  `cake_role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `cake_role_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `cake_role_description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `cake_role_level` int NOT NULL COMMENT '角色等级',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`cake_role_id`) USING BTREE,
  UNIQUE INDEX `cake_role_name`(`cake_role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_role
-- ----------------------------
INSERT INTO `cake_role` VALUES (1, 'ROLE_管理员', '管理员', 2, '2023-12-12 11:21:11');
INSERT INTO `cake_role` VALUES (2, 'ROLE_用户', '用户', 1, '2023-12-12 11:21:11');
INSERT INTO `cake_role` VALUES (3, 'ROLE_超级管理员', '超级管理员', 3, '2023-12-12 11:21:11');
INSERT INTO `cake_role` VALUES (13, 'ROLE_游客', '游客', 1, '2023-12-13 17:42:31');
INSERT INTO `cake_role` VALUES (14, 'ROLE_test', 'test', 100, '2023-12-13 17:55:19');
INSERT INTO `cake_role` VALUES (16, 'test', 'test01', 111, '2023-12-13 17:57:01');
INSERT INTO `cake_role` VALUES (18, 'test12', '123', 123, '2023-12-13 17:57:51');

-- ----------------------------
-- Table structure for cake_role_permit
-- ----------------------------
DROP TABLE IF EXISTS `cake_role_permit`;
CREATE TABLE `cake_role_permit`  (
  `role_permit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cake_permit_id` bigint NOT NULL COMMENT '权限ID',
  `cake_role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_permit_id`) USING BTREE,
  UNIQUE INDEX `cake_permit_id`(`cake_permit_id`, `cake_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_role_permit
-- ----------------------------
INSERT INTO `cake_role_permit` VALUES (1, 1, 1);
INSERT INTO `cake_role_permit` VALUES (2, 1, 2);
INSERT INTO `cake_role_permit` VALUES (7, 1, 3);
INSERT INTO `cake_role_permit` VALUES (3, 2, 1);
INSERT INTO `cake_role_permit` VALUES (8, 2, 3);
INSERT INTO `cake_role_permit` VALUES (11, 7, 1);
INSERT INTO `cake_role_permit` VALUES (12, 8, 1);
INSERT INTO `cake_role_permit` VALUES (14, 10, 1);
INSERT INTO `cake_role_permit` VALUES (15, 11, 1);
INSERT INTO `cake_role_permit` VALUES (16, 12, 1);
INSERT INTO `cake_role_permit` VALUES (17, 13, 1);
INSERT INTO `cake_role_permit` VALUES (18, 14, 1);
INSERT INTO `cake_role_permit` VALUES (19, 15, 1);
INSERT INTO `cake_role_permit` VALUES (24, 16, 18);
INSERT INTO `cake_role_permit` VALUES (20, 17, 1);

-- ----------------------------
-- Table structure for cake_user
-- ----------------------------
DROP TABLE IF EXISTS `cake_user`;
CREATE TABLE `cake_user`  (
  `cake_user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `cake_user_username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `cake_user_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `cake_user_nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `cake_role_id` int NOT NULL COMMENT '用户角色ID',
  `cake_user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户电话',
  `cake_user_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ip',
  `cake_user_level` int NOT NULL COMMENT '用户等级',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `cake_user_status` int NOT NULL DEFAULT 0 COMMENT '用户状态',
  `cake_user_avatar` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户头像',
  PRIMARY KEY (`cake_user_id`) USING BTREE,
  INDEX `cake_role_id`(`cake_role_id`) USING BTREE,
  CONSTRAINT `cake_user_ibfk_1` FOREIGN KEY (`cake_role_id`) REFERENCES `cake_role` (`cake_role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cake_user
-- ----------------------------
INSERT INTO `cake_user` VALUES (1, 'admin', '$2a$10$BIOFQNyzfAuZTDUZwlxDWekMGldNByYQXhPRVT5tm/C.WW/WNw9au', 'admin', 1, '15823202507', '127.0.0.1', 100, '2023-12-06 16:16:59', 0, 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_user` VALUES (2, 'user', '$2a$10$t0u/AKgh04M3j11.lOYp5etCZwWKLDHLB/kVwrgy9c5sU8B4te6Qe', 'user', 1, '15823202507', '127.0.0.1', 100, '2023-12-06 16:17:38', 0, 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');
INSERT INTO `cake_user` VALUES (3, 'super', '$2a$10$1SnMGL9U.xi75f5SPtA9XucPYd1KtT389rQdmvNIsuaRsRYQTjC0a', 'super', 3, '15823202507', '127.0.0.1', 100, '2023-12-12 09:14:56', 0, 'https://lrh-blog-project.oss-cn-beijing.aliyuncs.com/default.jpg');

SET FOREIGN_KEY_CHECKS = 1;
