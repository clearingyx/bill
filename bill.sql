/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : bill

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-10-12 18:07:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) DEFAULT NULL,
  `account` double(9,2) DEFAULT '0.00' COMMENT '出账',
  `use_status` int(1) DEFAULT '0' COMMENT ' -1 删除订单,0-生成，1-订单结束',
  `type` int(1) DEFAULT '0' COMMENT '类型，0-自己记账，1-分账',
  `cate` int(3) DEFAULT NULL COMMENT '消费类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img` varchar(255) DEFAULT '' COMMENT '图片',
  `count` int(11) DEFAULT '1' COMMENT '付款人数',
  `my_account` double DEFAULT '0' COMMENT '这个订单提起人的花费费用，可能为0',
  PRIMARY KEY (`id`),
  KEY `bill_cate_fk` (`cate`),
  CONSTRAINT `bill_cate_fk` FOREIGN KEY (`cate`) REFERENCES `cate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='账单';

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('8', 'oQCmNwFpGGx35LHa8p--177jAqLE', '265.20', '1', '1', '4', '超市购物', '2016-10-12 09:43:54', '2016-10-12 16:28:34', '/upload/62aabfa51e1d4bf4becd3a17a361466b.jpg', '3', '88.4');
INSERT INTO `bill` VALUES ('9', 'oQCmNwMF2sCiMREkCMi-w5PA-8AQ', '274.00', '0', '1', '1', '晚饭', '2016-10-12 11:24:07', '2016-10-12 16:28:40', '', '3', '91.3');
INSERT INTO `bill` VALUES ('10', 'oQCmNwFpGGx35LHa8p--177jAqLE', '125.00', '0', '1', '1', '火锅', '2016-09-12 14:48:30', '2016-10-12 16:28:42', '', '3', '41.7');
INSERT INTO `bill` VALUES ('11', 'oQCmNwFpGGx35LHa8p--177jAqLE', '100.00', '0', '1', '3', '水费', '2016-10-12 16:29:22', '2016-10-12 16:29:21', '', '2', '0');

-- ----------------------------
-- Table structure for cate
-- ----------------------------
DROP TABLE IF EXISTS `cate`;
CREATE TABLE `cate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cate
-- ----------------------------
INSERT INTO `cate` VALUES ('1', '吃');
INSERT INTO `cate` VALUES ('2', '穿');
INSERT INTO `cate` VALUES ('3', '住');
INSERT INTO `cate` VALUES ('4', '用');
INSERT INTO `cate` VALUES ('5', '行');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_person` varchar(28) DEFAULT '' COMMENT '负责人',
  `info` text COMMENT '合租信息',
  `plus_person` varchar(500) DEFAULT '' COMMENT '增加合租人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('6', 'oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', null, 'oQCmNwFpGGx35LHa8p--177jAqLE,oQCmNwMF2sCiMREkCMi-w5PA-8AQ,');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `open_id` varchar(28) NOT NULL,
  `phone` varchar(11) DEFAULT '',
  `nickname` varchar(255) DEFAULT '' COMMENT '微信名字',
  `headimgurl` varchar(255) DEFAULT '' COMMENT '微信头像',
  `city` varchar(50) DEFAULT '',
  `province` varchar(50) DEFAULT '',
  `country` varchar(50) DEFAULT '',
  `status` int(1) DEFAULT '1' COMMENT '1-关注，2-取消关注',
  `create_date` datetime DEFAULT NULL,
  `account` double(9,2) DEFAULT '0.00' COMMENT '收支总金额',
  `name` varchar(50) DEFAULT '' COMMENT '真实姓名',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('oQCmNwBsGOuHo75mmLaWeZxIV6I4', null, null, null, null, null, null, '2', null, null, null, null);
INSERT INTO `person` VALUES ('oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', '', 'admin', null, null, null, null, '1', null, null, null, '2016-10-01 00:00:00');
INSERT INTO `person` VALUES ('oQCmNwFpGGx35LHa8p--177jAqLE', '18734857140', '张鹏', null, null, null, null, null, null, null, null, '2016-10-12 17:59:24');
INSERT INTO `person` VALUES ('oQCmNwMF2sCiMREkCMi-w5PA-8AQ', '', 'xx', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `account` double(9,2) DEFAULT '0.00' COMMENT '还款金额',
  `type` int(11) DEFAULT '0' COMMENT '0-一次性，1-分次还',
  `refund_status` int(1) DEFAULT '0' COMMENT '-1删除的的订单,0-生成，1-还款完成，2-确认还款完成，3-分次',
  `remark` varchar(255) DEFAULT '',
  `photo` varchar(255) DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `refund_bill` (`bill_id`),
  CONSTRAINT `refund_bill` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='还款';

-- ----------------------------
-- Records of refund
-- ----------------------------
INSERT INTO `refund` VALUES ('13', 'oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', '8', '88.40', '0', '2', ' 确定', '/upload/62aabfa51e1d4bf4becd3a17a361466b.jpg', '2016-10-12 09:43:54', '2016-10-12 13:56:54');
INSERT INTO `refund` VALUES ('14', 'oQCmNwMF2sCiMREkCMi-w5PA-8AQ', '8', '88.40', '0', '-1', null, null, '2016-10-12 09:43:54', '2016-10-12 13:44:55');
INSERT INTO `refund` VALUES ('15', 'oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', '9', '91.30', '0', '0', '', '', '2016-10-12 11:24:07', '2016-10-12 11:24:06');
INSERT INTO `refund` VALUES ('16', 'oQCmNwFpGGx35LHa8p--177jAqLE', '9', '91.30', '0', '0', '', '', '2016-10-12 11:24:07', '2016-10-12 11:24:06');
INSERT INTO `refund` VALUES ('17', 'oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', '10', '41.70', '0', '1', '', '', '2016-09-12 14:48:30', '2016-09-12 14:48:30');
INSERT INTO `refund` VALUES ('18', 'oQCmNwMF2sCiMREkCMi-w5PA-8AQ', '10', '41.70', '0', '0', '', '', '2016-09-12 14:48:30', '2016-09-12 14:48:30');
INSERT INTO `refund` VALUES ('19', 'oQCmNwDOtsuJ_fm_VWoq8fvXyS5g', '11', '50.00', '0', '0', '', '', '2016-10-12 16:29:22', '2016-10-12 16:29:21');
INSERT INTO `refund` VALUES ('20', 'oQCmNwMF2sCiMREkCMi-w5PA-8AQ', '11', '50.00', '0', '0', '', '', '2016-10-12 16:29:22', '2016-10-12 16:29:21');

-- ----------------------------
-- Table structure for stages
-- ----------------------------
DROP TABLE IF EXISTS `stages`;
CREATE TABLE `stages` (
  `id` int(11) NOT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `refund_id` int(11) DEFAULT NULL,
  `account` double(9,2) DEFAULT '0.00' COMMENT '还款金额',
  `refund_status` int(1) DEFAULT NULL COMMENT '0-生成，1-提交还款，2-确认还款',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `stages_refund_fk` (`refund_id`),
  CONSTRAINT `stages_refund_fk` FOREIGN KEY (`refund_id`) REFERENCES `refund` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stages
-- ----------------------------
