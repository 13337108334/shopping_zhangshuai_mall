/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : DB_OnlineMall

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 12/11/2023 22:35:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `link_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `link_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `link_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `user_id` bigint DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO `address` VALUES (7, '杭州', '余杭区202幢', '1333333', 2);
COMMIT;

-- ----------------------------
-- Table structure for avatar
-- ----------------------------
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '文件类型，后缀 。 jpg …',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '文件路径',
  `md5` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='头像表';

-- ----------------------------
-- Records of avatar
-- ----------------------------
BEGIN;
INSERT INTO `avatar` VALUES (9, 'png', 47, '/avatar/51c0343a-9f77-4063-abe1-b7543f15fb3c.png', 'b8b1242cb02994c3a6e4cd39fd11e8e2');
INSERT INTO `avatar` VALUES (10, 'jpg', 191, '/avatar/6822d648-1c3c-43d7-afc0-25af6250f06a.jpg', 'f32a75724fd46cc30790b9ad5db5c148');
COMMIT;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `good_id` bigint DEFAULT NULL COMMENT '对应的商品id',
  `show_order` int DEFAULT NULL COMMENT '播放顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='轮播图表';

-- ----------------------------
-- Records of carousel
-- ----------------------------
BEGIN;
INSERT INTO `carousel` VALUES (8, 9, 3);
INSERT INTO `carousel` VALUES (9, 10, 2);
INSERT INTO `carousel` VALUES (10, 11, 1);
INSERT INTO `carousel` VALUES (11, 12, 4);
COMMIT;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `count` int DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  `good_id` bigint DEFAULT NULL COMMENT '商品id',
  `standard` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '商品规格',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='购物车表';

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES (8, 1, '2023-11-12 17:48:29', 9, '21基础款', 1);
INSERT INTO `cart` VALUES (9, 1, '2023-11-12 17:48:36', 11, '10斤装', 1);
INSERT INTO `cart` VALUES (10, 1, '2023-11-12 18:05:42', 11, '10斤装', 2);
INSERT INTO `cart` VALUES (12, 1, '2023-11-12 18:06:37', 12, '精品猫', 2);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (27, '电脑');
INSERT INTO `category` VALUES (28, '手机');
INSERT INTO `category` VALUES (29, '耳机');
INSERT INTO `category` VALUES (30, '钢笔');
INSERT INTO `category` VALUES (31, '铅笔');
INSERT INTO `category` VALUES (32, '文具盒');
INSERT INTO `category` VALUES (33, '男装');
INSERT INTO `category` VALUES (34, '女装');
INSERT INTO `category` VALUES (35, '运动鞋');
INSERT INTO `category` VALUES (36, '板鞋');
INSERT INTO `category` VALUES (37, '红酒');
INSERT INTO `category` VALUES (38, '啤酒');
INSERT INTO `category` VALUES (39, '宠物猫');
INSERT INTO `category` VALUES (40, '宠物狗');
INSERT INTO `category` VALUES (41, '水果');
COMMIT;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `description` varchar(1600) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `discount` double(10,2) NOT NULL DEFAULT '1.00' COMMENT '折扣',
  `sales` bigint NOT NULL DEFAULT '0' COMMENT '销量',
  `sale_money` double(10,2) DEFAULT '0.00' COMMENT '销售额',
  `category_id` bigint DEFAULT NULL COMMENT '分类id',
  `imgs` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐。0不推荐，1推荐',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0未删除，1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- ----------------------------
-- Records of good
-- ----------------------------
BEGIN;
INSERT INTO `good` VALUES (9, '笔记本book 21-pro', '笔记本book21-pro，M1系列芯片，性能提升强劲', 1.00, 1, 7999.00, 27, '/file/5a9c597e-e089-4a72-9e10-0afe6e64678b.png', '2023-11-12 17:11:12.590675', 1, 0);
INSERT INTO `good` VALUES (10, '智能手机phone最新款', '智能手机phone最新款，2023最新款', 1.00, 1, 6999.00, 28, '/file/c7fe03b2-29e8-44ba-9987-747f143dcdc0.png', '2023-11-12 17:13:29.475016', 1, 0);
INSERT INTO `good` VALUES (11, '山竹水果', '水果山竹，水果之王', 1.00, 6, 59.94, 41, '/file/4998b959-1730-4712-a117-10b168270df2.png', '2023-11-12 17:16:15.502456', 1, 0);
INSERT INTO `good` VALUES (12, '宠物猫-布偶', '布偶宠物猫，性格温和，容易养', 1.00, 0, 0.00, 39, '/file/1c33cf16-3539-45e5-a6ba-60982e2f4d1f.png', '2023-11-12 17:23:23.687024', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for good_standard
-- ----------------------------
DROP TABLE IF EXISTS `good_standard`;
CREATE TABLE `good_standard` (
  `good_id` bigint DEFAULT NULL COMMENT '商品id',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '规格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `store` bigint DEFAULT NULL COMMENT '库存'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='商品规格表';

-- ----------------------------
-- Records of good_standard
-- ----------------------------
BEGIN;
INSERT INTO `good_standard` VALUES (10, '智能手机-基础班', 3999.00, 98);
INSERT INTO `good_standard` VALUES (10, '智能手机-专业版', 6999.00, 33);
INSERT INTO `good_standard` VALUES (11, '1斤装', 9.99, 994);
INSERT INTO `good_standard` VALUES (11, '10斤装', 999.00, 23);
INSERT INTO `good_standard` VALUES (12, '纯种', 10000.00, 10);
INSERT INTO `good_standard` VALUES (12, '精品猫', 99999.00, 1);
INSERT INTO `good_standard` VALUES (9, '21基础款', 5000.00, 10);
INSERT INTO `good_standard` VALUES (9, '21升级款', 7999.00, 99);
COMMIT;

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '图标的识别码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='图标表';

-- ----------------------------
-- Records of icon
-- ----------------------------
BEGIN;
INSERT INTO `icon` VALUES (26, '&#xe60a;');
INSERT INTO `icon` VALUES (27, '&#xe709;');
INSERT INTO `icon` VALUES (28, '&#xe8c7;');
INSERT INTO `icon` VALUES (29, '&#xe617;');
INSERT INTO `icon` VALUES (30, '&#xe605;');
INSERT INTO `icon` VALUES (31, '&#xe606;');
INSERT INTO `icon` VALUES (32, '&#xe73d;');
COMMIT;

-- ----------------------------
-- Table structure for icon_category
-- ----------------------------
DROP TABLE IF EXISTS `icon_category`;
CREATE TABLE `icon_category` (
  `category_id` bigint NOT NULL COMMENT '分类id',
  `icon_id` bigint NOT NULL COMMENT '图标id',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='商品分类 - 图标关联表';

-- ----------------------------
-- Records of icon_category
-- ----------------------------
BEGIN;
INSERT INTO `icon_category` VALUES (27, 26);
INSERT INTO `icon_category` VALUES (28, 26);
INSERT INTO `icon_category` VALUES (29, 26);
INSERT INTO `icon_category` VALUES (30, 27);
INSERT INTO `icon_category` VALUES (31, 27);
INSERT INTO `icon_category` VALUES (32, 27);
INSERT INTO `icon_category` VALUES (33, 28);
INSERT INTO `icon_category` VALUES (34, 28);
INSERT INTO `icon_category` VALUES (35, 29);
INSERT INTO `icon_category` VALUES (36, 29);
INSERT INTO `icon_category` VALUES (37, 30);
INSERT INTO `icon_category` VALUES (38, 30);
INSERT INTO `icon_category` VALUES (39, 31);
INSERT INTO `icon_category` VALUES (40, 31);
INSERT INTO `icon_category` VALUES (41, 32);
COMMIT;

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `good_id` bigint DEFAULT NULL COMMENT '商品id',
  `count` int DEFAULT NULL COMMENT '数量',
  `standard` varchar(1600) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '规格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_goods
-- ----------------------------
BEGIN;
INSERT INTO `order_goods` VALUES (15, 15, 10, 1, '智能手机-专业版');
INSERT INTO `order_goods` VALUES (16, 16, 11, 6, '1斤装');
INSERT INTO `order_goods` VALUES (17, 17, 9, 1, '21升级款');
COMMIT;

-- ----------------------------
-- Table structure for standard
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard` (
  `goodId` bigint NOT NULL COMMENT '商品id',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '商品规格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '该规格的价格',
  `store` bigint DEFAULT NULL COMMENT '该规格的库存',
  PRIMARY KEY (`goodId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='规格表';

-- ----------------------------
-- Records of standard
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '大小',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '文件路径',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `md5` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='系统文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
INSERT INTO `sys_file` VALUES (7, '07.jpg', 'jpg', 1814, '/file/7dfd10628edc4b4e97de19c1cb86585e.jpg', 1, 0, '04271616ebc6914643c3af592dd58bef');
INSERT INTO `sys_file` VALUES (8, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 1, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (9, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 1, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (10, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 1, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (11, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 1, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (12, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (13, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (14, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (15, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (16, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (17, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 1, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (18, '02.jpg', 'jpg', 33, '/file/84ad8a9829424254811ce2220edc2d3b.jpg', 1, 0, 'fcf09e93c497c75cf2b3656f80f997cc');
INSERT INTO `sys_file` VALUES (19, '01.jpg', 'jpg', 26, '/file/cef757d124ec4b169cffd65de5e3c47c.jpg', 1, 0, 'd5b6bb3b068c1980d77c59079248a4a4');
INSERT INTO `sys_file` VALUES (20, '03.jpg', 'jpg', 22, '/file/7791be8ea1ee4aa0a149ae8e75c857d6.jpg', 1, 0, '75e8b3e8790e514fb799857f636a1623');
INSERT INTO `sys_file` VALUES (21, '04.jpg', 'jpg', 25, '/file/867aaf026b684b1e8b1a10c87e31df7e.jpg', 1, 0, '1d7397d5a4ce0995f711a1484d593f44');
INSERT INTO `sys_file` VALUES (22, '05.jpg', 'jpg', 19, '/file/f9f26a01e13d4ba68d7f7bd12df282e0.jpg', 1, 0, 'b2d243af2652abf08a491074c8f099ea');
INSERT INTO `sys_file` VALUES (23, '06.jpg', 'jpg', 11, '/file/286aa7816325455b8cdcd522aca833fe.jpg', 1, 0, 'c160a645c3dacb58ffb123a4239dcb50');
INSERT INTO `sys_file` VALUES (24, '230892C84EFF474374441B8BB328CEA7.jpg', 'jpg', 1531, '/file/2fbac822-ed72-4d96-9dcb-21ea50d17853.jpg', 1, 0, '6a393d5b02422a99acedc99778e24ab3');
INSERT INTO `sys_file` VALUES (25, '653A169BCEC9ED732FF6255149ED2764.jpg', 'jpg', 845, '/file/bd5fbe6e-4099-43fb-b8f3-bf690f5adc2c.jpg', 1, 0, '5fab7b4889adfe022e48d9c087ff42a7');
INSERT INTO `sys_file` VALUES (26, '1.png', 'png', 47, '/file/bc3082bc-0cfc-40e9-9e83-8fe3e3ca982e.png', 0, 0, 'b8b1242cb02994c3a6e4cd39fd11e8e2');
INSERT INTO `sys_file` VALUES (27, 'c8adb15efa3ba09e4bdd8105f026ae5e.png', 'png', 1285, '/file/17bf2b1e-ca96-4d9a-9bfc-d7f8736ae82c.png', 0, 0, 'c8adb15efa3ba09e4bdd8105f026ae5e');
INSERT INTO `sys_file` VALUES (28, '9054f9a8e408dbe36cb55c9895012a8b.png', 'png', 1574, '/file/c7fe03b2-29e8-44ba-9987-747f143dcdc0.png', 0, 0, '9054f9a8e408dbe36cb55c9895012a8b');
INSERT INTO `sys_file` VALUES (29, '1.png', 'png', 47, '/file/bc3082bc-0cfc-40e9-9e83-8fe3e3ca982e.png', 0, 0, 'b8b1242cb02994c3a6e4cd39fd11e8e2');
INSERT INTO `sys_file` VALUES (30, '1.jpg', 'jpg', 191, '/file/86e42e4d-8bd9-4257-8c5c-f3ba04f582ce.jpg', 0, 0, 'f32a75724fd46cc30790b9ad5db5c148');
INSERT INTO `sys_file` VALUES (31, '14d398ffd3667a2fd4136f4543f73836.png', 'png', 389, '/file/4998b959-1730-4712-a117-10b168270df2.png', 0, 0, '14d398ffd3667a2fd4136f4543f73836');
INSERT INTO `sys_file` VALUES (32, '993d824a74151bea014eae0411be4336.png', 'png', 1424, '/file/31f4b7e8-53ee-4c20-bdb2-48618fa991ee.png', 0, 0, '993d824a74151bea014eae0411be4336');
INSERT INTO `sys_file` VALUES (33, '215dbf89511c75a622bfe1053b01b71e.png', 'png', 1321, '/file/1c33cf16-3539-45e5-a6ba-60982e2f4d1f.png', 0, 0, '215dbf89511c75a622bfe1053b01b71e');
INSERT INTO `sys_file` VALUES (34, '3eb3982b407910ce98913f6412afd02a.png', 'png', 424, '/file/5a9c597e-e089-4a72-9e10-0afe6e64678b.png', 0, 0, '3eb3982b407910ce98913f6412afd02a');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `address` varchar(1600) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `avatar_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '头像链接',
  `role` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '110238372@qq.com', '13333333333', '上海市闵行路古荡小区11单元110', '/avatar/51c0343a-9f77-4063-abe1-b7543f15fb3c.png', 'admin');
INSERT INTO `sys_user` VALUES (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', '新用户', '726381263@qq.com', '15888888888', '浙江省杭州市西湖区翠苑1单元202', '/avatar/6822d648-1c3c-43d7-afc0-25af6250f06a.jpg', 'user');
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `link_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `link_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (15, '20231112180622711294', 6999.00, 2, '杭州', '1333333', '余杭区202幢', '已收货', '2023-11-12 18:06:22');
INSERT INTO `t_order` VALUES (16, '20231112181636951844', 59.94, 2, '杭州', '1333333', '余杭区202幢', '已支付', '2023-11-12 18:16:36');
INSERT INTO `t_order` VALUES (17, '20231112214958576445', 7999.00, 2, '杭州', '1333333', '余杭区202幢', '已支付', '2023-11-12 21:49:58');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
