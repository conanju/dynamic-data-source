
CREATE database dynamic_master;
use dynamic_master;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `userinfo` VALUES ('1', 'master', 'master@qq.com');
