/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shanjiajihe

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-22 03:21:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bs_sys_handle
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_handle`;
CREATE TABLE `bs_sys_handle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `handle_code` varchar(150) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `url` varchar(400) DEFAULT NULL,
  `method` varchar(15) DEFAULT NULL,
  `type` char(7) DEFAULT NULL,
  `is_available` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_handle
-- ----------------------------
INSERT INTO `bs_sys_handle` VALUES ('4', '2', 'SYS_USER_LIST2', '列表查询', '/sys/user/datables/list', null, 'PUT', '1', '2017-11-21 18:32:06', '2', '2', '2017-11-23 18:54:25');
INSERT INTO `bs_sys_handle` VALUES ('5', '2', 'SYS_USER_LIST', '11', '111', null, null, '2', '2017-11-21 18:34:12', '2', '2', '2017-11-21 18:34:51');
INSERT INTO `bs_sys_handle` VALUES ('6', '6', 'test', '测试', '', null, 'GET', '1', '2017-11-23 16:37:08', '2', null, null);

-- ----------------------------
-- Table structure for bs_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_menu`;
CREATE TABLE `bs_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(100) DEFAULT NULL,
  `menu_name` varchar(200) DEFAULT NULL,
  `url` varchar(400) DEFAULT NULL,
  `menu_icon` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_menu
-- ----------------------------
INSERT INTO `bs_sys_menu` VALUES ('1', 'SYS', '系统管理', '', 'fa fa-home', '1', '1', '0', '2017-09-20 00:28:55', '1', '1', '2017-11-02 19:46:02');
INSERT INTO `bs_sys_menu` VALUES ('2', 'SYS_USER', '用户管理', '../../system/user/sys_user_list.html', 'fa fa-user', null, '1', '1', '2017-09-20 00:34:35', '1', '2', '2017-12-01 16:47:19');
INSERT INTO `bs_sys_menu` VALUES ('6', 'SYS_MANAGE', '菜单管理', '../../system/menu/sys_menu.html', 'fa fa-industry', null, '1', '1', '2017-10-27 19:07:49', '1', '2', '2017-12-01 16:47:27');
INSERT INTO `bs_sys_menu` VALUES ('11', 'SYS_ROLE', '角色管理', '../../system/role/sys_role.html', 'fa fa-users', null, '1', '1', '2017-11-02 19:48:54', null, '2', '2017-12-01 16:47:36');
INSERT INTO `bs_sys_menu` VALUES ('18', 'SYS_MENU', '操作菜单', '../../system/handle/sys_handle.html', 'fa fa-wrench', '4', '1', '1', '2017-11-21 00:00:00', '2', '2', '2017-12-01 16:47:50');
INSERT INTO `bs_sys_menu` VALUES ('23', 'GW', '官网管理', null, 'fa fa-object-group', '1', '1', '0', '2017-11-29 00:00:00', '2', '2', '2018-07-05 00:20:54');
INSERT INTO `bs_sys_menu` VALUES ('24', 'GW_COLUMN', '栏目管理', '../../officialWebsite/column/gw_column.html', 'fa fa-map-signs', '1', '1', '23', '2017-11-29 14:45:13', '2', '2', '2017-12-01 16:46:41');
INSERT INTO `bs_sys_menu` VALUES ('25', 'GW_CLASSIFY', '分类管理', '../../officialWebsite/classify/gw_classify.html', 'fa fa-bars', '2', '1', '23', '2017-12-01 16:31:18', '2', '2', '2017-12-01 16:47:07');
INSERT INTO `bs_sys_menu` VALUES ('26', 'GW_CONTENT', '内容管理', '../../officialWebsite/content/gw_content.html', 'fa fa-sticky-note', '3', '1', '23', '2017-12-03 18:57:35', '2', '2', '2017-12-03 19:06:48');
INSERT INTO `bs_sys_menu` VALUES ('27', 'GW_MESSAGE', '留言板信息', '../../officialWebsite/message/gw_message.html', 'fa fa-pencil-square-o', '4', '1', '23', '2017-12-15 16:05:24', '2', '2', '2017-12-15 16:12:36');

-- ----------------------------
-- Table structure for bs_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_organization`;
CREATE TABLE `bs_sys_organization` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `organization_code` varchar(50) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `is_available` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for bs_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_role`;
CREATE TABLE `bs_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(100) DEFAULT NULL,
  `role_name` varchar(150) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_role
-- ----------------------------
INSERT INTO `bs_sys_role` VALUES ('1', 'sysAdmin', '超级管理员', null, '2017-11-03 00:14:31', null, null, '2017-11-04 12:35:07');
INSERT INTO `bs_sys_role` VALUES ('2', 'test', '测试2', null, '2017-11-04 02:19:07', null, null, '2017-11-05 13:50:30');
INSERT INTO `bs_sys_role` VALUES ('3', '测试', '111', null, '2017-11-04 02:54:17', null, '2', '2017-11-16 00:52:52');

-- ----------------------------
-- Table structure for bs_sys_role_handle
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_role_handle`;
CREATE TABLE `bs_sys_role_handle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `handle_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_role_handle
-- ----------------------------
INSERT INTO `bs_sys_role_handle` VALUES ('4', '2', '4', '2017-11-22 01:19:43', '2', null, null);
INSERT INTO `bs_sys_role_handle` VALUES ('32', '1', '4', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_handle` VALUES ('33', '1', '5', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_handle` VALUES ('34', '1', '6', '2018-01-08 01:28:24', '2', null, null);

-- ----------------------------
-- Table structure for bs_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_role_menu`;
CREATE TABLE `bs_sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 COMMENT=' ';

-- ----------------------------
-- Records of bs_sys_role_menu
-- ----------------------------
INSERT INTO `bs_sys_role_menu` VALUES ('84', '2', '1', '2017-11-22 01:19:43', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('85', '2', '0', '2017-11-22 01:19:43', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('86', '2', '11', '2017-11-22 01:19:43', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('87', '2', '2', '2017-11-22 01:19:43', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('202', '1', '2', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('203', '1', '1', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('204', '1', '0', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('205', '1', '6', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('206', '1', '11', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('207', '1', '18', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('208', '1', '23', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('209', '1', '24', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('210', '1', '25', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('211', '1', '26', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('212', '1', '27', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('213', '1', '40', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('214', '1', '34', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('215', '1', '35', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('216', '1', '37', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('217', '1', '38', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('218', '1', '39', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('219', '1', '41', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('220', '1', '29', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('221', '1', '44', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('222', '1', '45', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('223', '1', '28', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('224', '1', '42', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('225', '1', '43', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('226', '1', '30', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('227', '1', '47', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('228', '1', '46', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('229', '1', '31', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('230', '1', '48', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('231', '1', '49', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('232', '1', '32', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('233', '1', '50', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('234', '1', '51', '2018-01-08 01:28:24', '2', null, null);
INSERT INTO `bs_sys_role_menu` VALUES ('235', '1', '36', '2018-01-08 01:28:24', '2', null, null);

-- ----------------------------
-- Table structure for bs_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_user`;
CREATE TABLE `bs_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(50) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `hard_img` varchar(500) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `moblie` varchar(11) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `is_available` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of bs_sys_user
-- ----------------------------
INSERT INTO `bs_sys_user` VALUES ('2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', '系统管理员', '13751878576', '645614025@qq.com', '', '1', '2017-11-15 23:18:38', null, '2', '2017-12-10 15:32:03');

-- ----------------------------
-- Table structure for bs_sys_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_user_organization`;
CREATE TABLE `bs_sys_user_organization` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_user_organization
-- ----------------------------

-- ----------------------------
-- Table structure for bs_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bs_sys_user_role`;
CREATE TABLE `bs_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bs_sys_user_role
-- ----------------------------
INSERT INTO `bs_sys_user_role` VALUES ('22', '1', '2', '2017-11-21 01:14:44', '2', null, null);
INSERT INTO `bs_sys_user_role` VALUES ('23', '2', '3', '2017-11-21 01:15:19', '2', null, null);

-- ----------------------------
-- Table structure for gw_classify_table
-- ----------------------------
DROP TABLE IF EXISTS `gw_classify_table`;
CREATE TABLE `gw_classify_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `covers_img` varchar(1024) DEFAULT NULL,
  `logo` varchar(1024) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '一级分类id为0',
  `column_id` int(11) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `present` varchar(1024) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='分类表\r\n';

-- ----------------------------
-- Records of gw_classify_table
-- ----------------------------
INSERT INTO `gw_classify_table` VALUES ('56', null, '', '南航', '1', '0', '3', '', '南航的经典案例', 'NANHANF', '2018-10-21 19:10:51', '2', null, null);
INSERT INTO `gw_classify_table` VALUES ('57', null, '', 'VR经典案例', '2', '0', '3', '', '', 'VR ', '2018-10-21 19:11:22', '2', null, null);
INSERT INTO `gw_classify_table` VALUES ('58', null, '', '地址信息分类', '1', '0', '8', '', '', 'LXWO', '2018-10-21 19:11:56', '2', null, null);
INSERT INTO `gw_classify_table` VALUES ('59', null, '', '二维码分类', '2', '0', '8', '', '', 'QCODE', '2018-10-21 19:12:15', '2', null, null);
INSERT INTO `gw_classify_table` VALUES ('60', null, '', '轮播图分类', '1', '0', '1', '', '', 'BANNER', '2018-10-21 19:12:42', '2', null, null);

-- ----------------------------
-- Table structure for gw_column_table
-- ----------------------------
DROP TABLE IF EXISTS `gw_column_table`;
CREATE TABLE `gw_column_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `is_show_in_top` tinyint(4) DEFAULT NULL,
  `logo` varchar(1024) DEFAULT NULL,
  `present` varchar(1024) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `concent` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gw_column_table
-- ----------------------------
INSERT INTO `gw_column_table` VALUES ('1', 'JIESAO', 'index.html', '1', '1', 'http://127.0.0.1:8082/sjjh/static/tc/img/b7a7b20a-1e45-4446-a9c2-3f9fa5cfb369.jpg', '', '首页', '该模块是 首页', '2017-11-30 18:35:02', '2', '2', '2018-10-21 19:04:21', null);
INSERT INTO `gw_column_table` VALUES ('3', 'DINGWEI', 'product.html', '2', '1', 'http://127.0.0.1:8082/sjjh/static/tc/img/5466a867-f898-40ee-a12c-66acc94dbad1.jpeg', '', '经典案例 ', '经典案例    模块', '2017-12-01 16:55:07', '2', '2', '2018-10-21 19:04:32', null);
INSERT INTO `gw_column_table` VALUES ('8', 'WOMEN', '', '7', '1', 'http://127.0.0.1:8082/sjjh/static/tc/img/f1e4a6c2-3fc6-44fd-a38f-799e0c5eeaed.jpg', '', '联系我们', '联系我们 模块', '2017-12-07 15:34:28', '2', '2', '2018-07-05 01:11:22', null);

-- ----------------------------
-- Table structure for gw_content_table
-- ----------------------------
DROP TABLE IF EXISTS `gw_content_table`;
CREATE TABLE `gw_content_table` (
  `name` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sort` int(11) DEFAULT NULL,
  `logo` varchar(1024) DEFAULT NULL,
  `covers_img` varchar(1024) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `classify_id` int(11) DEFAULT NULL,
  `column_id` int(11) DEFAULT NULL,
  `is_show` tinyint(4) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `itude_x` decimal(24,14) DEFAULT NULL,
  `itude_y` decimal(24,14) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `less_present` text,
  `is_favourable` tinyint(4) DEFAULT NULL,
  `favourable_start` datetime DEFAULT NULL,
  `favourable_end` datetime DEFAULT NULL,
  `favourable_context` varchar(1024) DEFAULT NULL,
  `full_present` text,
  `is_top` tinyint(4) DEFAULT NULL,
  `top_start` datetime DEFAULT NULL,
  `top_end` datetime DEFAULT NULL,
  `special_start` datetime DEFAULT NULL,
  `special_end` datetime DEFAULT NULL,
  `is_special` tinyint(4) DEFAULT NULL,
  `workTime` varchar(300) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gw_content_table
-- ----------------------------
INSERT INTO `gw_content_table` VALUES ('这是第一个轮播图内容', '66', '1', '', 'http://biggirlos.natapp1.cc/static/classify/image/html/2d219e91-0db1-44a4-a86d-630e02b3d108.jpg', '这是第一个轮播图内容', 'TEST1', '60', null, '1', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, null, '', '2018-10-21 19:13:46', '2', '2', '2018-10-21 19:19:42', null, '');

-- ----------------------------
-- Table structure for gw_message_board
-- ----------------------------
DROP TABLE IF EXISTS `gw_message_board`;
CREATE TABLE `gw_message_board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moble` varchar(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `context` longtext,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `emali` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gw_message_board
-- ----------------------------
INSERT INTO `gw_message_board` VALUES ('1', '13751878576', '王雁欣', '厕所', '2017-12-15 16:50:49', '1', null, null, null, null);
INSERT INTO `gw_message_board` VALUES ('11', '13726262626', '测试', '', '2018-08-07 23:57:39', null, null, null, null, null);
INSERT INTO `gw_message_board` VALUES ('12', '13726262626', '测试', '', '2018-08-07 23:58:22', null, null, null, null, null);
INSERT INTO `gw_message_board` VALUES ('16', '13725252525', '测试1', '测试', '2018-08-08 11:07:37', null, null, null, null, null);
