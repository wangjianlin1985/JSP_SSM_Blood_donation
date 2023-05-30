/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : blood_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-09-19 13:32:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `t_blooddonate`
-- ----------------------------
DROP TABLE IF EXISTS `t_blooddonate`;
CREATE TABLE `t_blooddonate` (
  `donateId` int(11) NOT NULL auto_increment COMMENT '捐献id',
  `userObj` varchar(30) NOT NULL COMMENT '献血人',
  `bloodType` varchar(20) NOT NULL COMMENT '血型',
  `donateNumber` int(11) NOT NULL COMMENT '献血量',
  `loveFlag` varchar(20) NOT NULL COMMENT '爱心血库',
  `donateTime` varchar(20) default NULL COMMENT '献血时间',
  `place` varchar(50) NOT NULL COMMENT '采血地点',
  `doctor` varchar(20) NOT NULL COMMENT '采血医生',
  `donateMemo` varchar(500) default NULL COMMENT '献血备注',
  PRIMARY KEY  (`donateId`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_blooddonate_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blooddonate
-- ----------------------------
INSERT INTO `t_blooddonate` VALUES ('1', 'user1', 'O型', '500', '是', '2018-08-07 23:52:53', '第一人民医院', '王建新', '爱心献血人士');
INSERT INTO `t_blooddonate` VALUES ('2', 'user2', 'A型', '500', '否', '2018-08-19 13:59:59', 'XXX医院', 'XX医生', '这个人献血了很多次了');

-- ----------------------------
-- Table structure for `t_bloodtest`
-- ----------------------------
DROP TABLE IF EXISTS `t_bloodtest`;
CREATE TABLE `t_bloodtest` (
  `testId` int(11) NOT NULL auto_increment COMMENT '化验id',
  `userObj` varchar(30) NOT NULL COMMENT '准献血人',
  `protein` float NOT NULL COMMENT '蛋白',
  `bloodType` varchar(20) NOT NULL COMMENT '血型',
  `alt` float NOT NULL COMMENT 'ALT',
  `hbsag` varchar(20) NOT NULL COMMENT 'HBsAg',
  `antiHCV` varchar(20) NOT NULL COMMENT '抗-HCV',
  `antiHIV` varchar(20) NOT NULL COMMENT '抗-HIV',
  `meidu` varchar(20) NOT NULL COMMENT '梅毒',
  `doctor` varchar(20) NOT NULL COMMENT '检测医生',
  `testTime` varchar(20) default NULL COMMENT '检测时间',
  `testResult` varchar(500) NOT NULL COMMENT '化验结果',
  PRIMARY KEY  (`testId`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_bloodtest_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bloodtest
-- ----------------------------
INSERT INTO `t_bloodtest` VALUES ('1', 'user1', '80.7', 'O型', '21.8', '阴性', '阴性', '阴性', '阴性', '王建新', '2018-08-15 23:56:52', '合格');
INSERT INTO `t_bloodtest` VALUES ('2', 'user2', '83.5', 'B型', '33.5', '阴性', '阴性', '阴性', '阳性', '王晓霞', '2018-08-19 13:52:23', '不合格');

-- ----------------------------
-- Table structure for `t_leaveword`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaveword`;
CREATE TABLE `t_leaveword` (
  `leaveWordId` int(11) NOT NULL auto_increment COMMENT '留言id',
  `leaveTitle` varchar(80) NOT NULL COMMENT '留言标题',
  `leaveContent` varchar(2000) NOT NULL COMMENT '留言内容',
  `userObj` varchar(30) NOT NULL COMMENT '留言人',
  `leaveTime` varchar(20) default NULL COMMENT '留言时间',
  `replyContent` varchar(1000) default NULL COMMENT '管理回复',
  `replyTime` varchar(20) default NULL COMMENT '回复时间',
  PRIMARY KEY  (`leaveWordId`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_leaveword_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaveword
-- ----------------------------
INSERT INTO `t_leaveword` VALUES ('1', '我想献血', '经常有想献血的想法，不知道流程如何', 'user1', '2018-08-15 23:53:10', '只要你通过血液检测，身体健康就行哈', '2018-08-16 00:06:06');
INSERT INTO `t_leaveword` VALUES ('2', '有哪些病不能献血呢', '我有甲减也可以献血吗？', 'user1', '2018-08-19 12:11:53', '--', '--');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeId` int(11) NOT NULL auto_increment COMMENT '公告id',
  `title` varchar(80) NOT NULL COMMENT '标题',
  `content` varchar(5000) NOT NULL COMMENT '公告内容',
  `publishDate` varchar(20) default NULL COMMENT '发布时间',
  PRIMARY KEY  (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '对热心献血者李明坤表示感谢！', '<p>李明坤先生从2000年就开始献血，到现在都有接近20个年头了，特此重大感谢！</p>', '2018-08-15 23:53:21');

-- ----------------------------
-- Table structure for `t_orderstate`
-- ----------------------------
DROP TABLE IF EXISTS `t_orderstate`;
CREATE TABLE `t_orderstate` (
  `stateId` int(11) NOT NULL auto_increment COMMENT '状态id',
  `stateName` varchar(20) NOT NULL COMMENT '状态名称',
  PRIMARY KEY  (`stateId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orderstate
-- ----------------------------
INSERT INTO `t_orderstate` VALUES ('1', '预约待处理');
INSERT INTO `t_orderstate` VALUES ('2', '待献血化验');
INSERT INTO `t_orderstate` VALUES ('3', '血化验不合格');
INSERT INTO `t_orderstate` VALUES ('4', '献血化验通过');
INSERT INTO `t_orderstate` VALUES ('5', '献血完毕');

-- ----------------------------
-- Table structure for `t_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `user_name` varchar(30) NOT NULL COMMENT 'user_name',
  `password` varchar(30) NOT NULL COMMENT '登录密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `cardNumber` varchar(30) NOT NULL COMMENT '身份证号',
  `birthDate` varchar(20) default NULL COMMENT '出生日期',
  `userPhoto` varchar(60) NOT NULL COMMENT '用户照片',
  `telephone` varchar(20) NOT NULL COMMENT '联系电话',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `address` varchar(80) default NULL COMMENT '家庭地址',
  `regTime` varchar(20) default NULL COMMENT '注册时间',
  PRIMARY KEY  (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------
INSERT INTO `t_userinfo` VALUES ('user1', '123', '张晓芬', '女', '512982199812119843', '1998-07-21', 'upload/c4445671-cff1-464b-ab72-d020998010b5.jpg', '13599809834', 'xiaofen@163.com', '四川达州', '2018-08-15 23:51:34');
INSERT INTO `t_userinfo` VALUES ('user2', '123', '李达', '男', '513042199810229433', '2018-08-02', 'upload/NoImage.jpg', '13985193432', 'lida@163.com', '四川成都成华区', '2018-08-19 13:35:48');

-- ----------------------------
-- Table structure for `t_userorder`
-- ----------------------------
DROP TABLE IF EXISTS `t_userorder`;
CREATE TABLE `t_userorder` (
  `orderId` int(11) NOT NULL auto_increment COMMENT '订单id',
  `userObj` varchar(30) NOT NULL COMMENT '预约人',
  `mobilePhone` varchar(20) NOT NULL COMMENT '手机号',
  `cardType` varchar(20) NOT NULL COMMENT '证件类型',
  `cardNumber` varchar(30) NOT NULL COMMENT '证件号码',
  `orderDate` varchar(20) default NULL COMMENT '预约日期',
  `orderStateObj` int(11) NOT NULL COMMENT '预约状态',
  `orderTime` varchar(20) default NULL COMMENT '提交时间',
  `orderMemo` varchar(500) default NULL COMMENT '订单备注',
  PRIMARY KEY  (`orderId`),
  KEY `userObj` (`userObj`),
  KEY `orderStateObj` (`orderStateObj`),
  CONSTRAINT `t_userorder_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`),
  CONSTRAINT `t_userorder_ibfk_2` FOREIGN KEY (`orderStateObj`) REFERENCES `t_orderstate` (`stateId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userorder
-- ----------------------------
INSERT INTO `t_userorder` VALUES ('1', 'user1', '13599809834', '身份证', '512982199812119843', '2018-08-06', '5', '2018-08-13 23:51:59', '我想献血');
INSERT INTO `t_userorder` VALUES ('2', 'user2', '13980831143', '身份证', '513042199810229433', '2018-08-21', '2', '2018-08-19 14:39:30', '我大概21日下午2点来\r\n医生回复：你可以来，有位置');
INSERT INTO `t_userorder` VALUES ('3', 'user1', '13980831143', '身份证', '512982199812119843', '2018-09-30', '2', '2018-09-19 13:23:13', '再来献一次血');
