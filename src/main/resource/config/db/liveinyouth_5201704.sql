/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : liveinyouth

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-05-20 17:04:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `ALBUM_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '专辑表ID',
  `ARTIST_ID` int(10) unsigned DEFAULT NULL COMMENT '歌手ID',
  `ALBUM_NAME` varchar(50) DEFAULT NULL COMMENT '专辑名',
  `ARTIST_NAME` varchar(50) DEFAULT NULL COMMENT '歌手名',
  `ALBUM_IMG_SRC` text COMMENT '专辑图片链接',
  `RELEASE_DATE` date DEFAULT NULL COMMENT '专辑发行日期，date类型是年/月/日',
  `INTRO` text COMMENT '专辑介绍',
  `BRIEF_INTRO` varchar(255) DEFAULT NULL COMMENT '专辑简要介绍',
  `THUMP_UP_COUNT` int(10) unsigned DEFAULT '0' COMMENT '专辑的赞数',
  PRIMARY KEY (`ALBUM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='专辑表ALBUM';

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('1', '4', '美妙生活', '林宥嘉', null, null, null, null, '0');
INSERT INTO `album` VALUES ('2', '1', '谢谢', '范晓萱', 'http://localhost:8081/LiveInYouth/images/hitSong/albumImg/fanxiaoxuan_fangkong.png', null, null, null, '0');
INSERT INTO `album` VALUES ('3', '6', '小梦大半', '陈粒', 'http://localhost:8081/LiveInYouth/images/hitSong/albumImg/chenli_xuni.png', null, null, null, '0');
INSERT INTO `album` VALUES ('4', '2', 'Solidays', '陈奕迅', 'http://localhost:8081/LiveInYouth/images/hitSong/albumImg/chenyixun_luohualiushui.png', null, null, null, '0');

-- ----------------------------
-- Table structure for artist
-- ----------------------------
DROP TABLE IF EXISTS `artist`;
CREATE TABLE `artist` (
  `ARTIST_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '歌手ID',
  `ARTIST_NAME` varchar(50) DEFAULT NULL COMMENT '歌手名',
  `NATION` varchar(50) DEFAULT NULL COMMENT '国籍',
  `INTRO` text COMMENT '介绍',
  `BRIEF_INTRO` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`ARTIST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='歌手表ARTIST';

-- ----------------------------
-- Records of artist
-- ----------------------------
INSERT INTO `artist` VALUES ('1', '范晓萱', '中国', '测试测试测试测试测试测试测试测试测试测试', '小魔女');
INSERT INTO `artist` VALUES ('2', '陈奕迅', '中国香港', 'testtesttest', 'eason');
INSERT INTO `artist` VALUES ('3', '窦靖童', '中国', '啊啊啊啊啊啊啊啊啊', '窦唯王菲之女');
INSERT INTO `artist` VALUES ('4', '林宥嘉', '中国台湾', '拉近距离空间链接拉进来了', 'yoga');
INSERT INTO `artist` VALUES ('5', '小苏菲', '中国', '啊啊老实交代放辣椒来得及', '高冷的小苏菲');
INSERT INTO `artist` VALUES ('6', '陈粒', '中国', '奇妙能力歌', '盼我封魔还盼我孑孓又独活');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `COLLECT_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏表id',
  `COLLECT_TYPE` tinyint(1) unsigned NOT NULL COMMENT '收藏类型的描述：1 for 歌曲；2 for 歌单； 3 for MV',
  `SONG_ID` int(10) unsigned DEFAULT NULL COMMENT '被收藏的歌曲ID',
  `SONG_LIST_ID` int(10) unsigned DEFAULT NULL COMMENT '被收藏的歌单ID',
  `USER_ID` int(10) unsigned DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '收藏用户名',
  PRIMARY KEY (`COLLECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='收藏表，只有歌单能被收藏';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('14', '2', '0', '1', '2', 'ixenos');
INSERT INTO `collect` VALUES ('23', '2', '0', '1', '11', 'lierya');
INSERT INTO `collect` VALUES ('27', '2', '0', '3', '26', 'qqqqqq');
INSERT INTO `collect` VALUES ('29', '2', '0', '2', '27', 'rongxin');
INSERT INTO `collect` VALUES ('30', '2', '0', '5', '2', 'ixenos');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `COMMENT_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论表ID',
  `SONG_ID` int(10) unsigned DEFAULT NULL COMMENT '歌曲表ID',
  `SONG_NAME` varchar(50) NOT NULL COMMENT '评论的歌曲名或专辑名',
  `USER_ID` int(10) unsigned DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(16) DEFAULT NULL COMMENT '评论的用户名',
  `COMMENT_TYPE` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '评论类型：0 for 歌曲评论；1 for 专辑评论',
  `CONTENT` varchar(255) NOT NULL COMMENT '评论内容',
  `THUMP_UP_COUNT` int(10) unsigned DEFAULT '0' COMMENT '评论的赞数',
  PRIMARY KEY (`COMMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='评论表COMMENT';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '放空', '12', 'Yuban', '0', '只要她还肯露面，只要她还肯唱歌，只要她开心，她怎样都行。', '3756');
INSERT INTO `comment` VALUES ('2', '1', '放空', '13', 'Dada', '0', '放空 感同身受 有时候觉得融不进大家 觉得好难受 会一瞬间抽离 这是什么病？ 快乐到一半刹住 觉得空洞洞的', '2193');
INSERT INTO `comment` VALUES ('3', '1', '放空', '14', 'Allen', '0', '属于范晓萱迷幻的味道，像是喝了杯红酒有些微醺，慢慢的放空自己。', '930');
INSERT INTO `comment` VALUES ('4', '6', '放空', '2', 'ixenos', '0', '我不愿意去触碰那些我不喜欢的身体，回应毫无感觉的词句，去拥抱那些我未为之心动过的灵魂', '5683');
INSERT INTO `comment` VALUES ('5', '5', '虚拟', '2', 'ixenos', '0', '你是我朝夕相伴触手可及的虚拟 你是我未曾拥有无法捕捉的亲昵', '3452');
INSERT INTO `comment` VALUES ('6', '2', '落花流水', '2', 'ixenos', '0', '流水很清楚 惜花这个责任 真的身份不过送运 这趟旅行若算开心 亦是无负这一生', '2352');
INSERT INTO `comment` VALUES ('7', '2', '落花流水', '11', 'lierya', '0', '不说话 你喜欢我吗', '8868');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `SONG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '歌曲表ID',
  `SONG_NAME` varchar(50) DEFAULT NULL COMMENT '歌曲名，非空',
  `MEDIA_TYPE` tinyint(1) unsigned DEFAULT '0' COMMENT '媒体类型：0 for audio音频，1 for video视频，默认音频',
  `SONG_LIST_ID_SET` varchar(255) DEFAULT NULL COMMENT '歌曲所在歌单的id的集合,以逗号隔开id',
  `ARTIST_ID` int(10) unsigned DEFAULT NULL COMMENT '歌曲对应歌手,为空时歌手名为"佚名“',
  `ARTIST_NAME` varchar(50) DEFAULT '佚名' COMMENT '歌手名，为空时为”佚名“',
  `ALBUM_ID` int(10) unsigned DEFAULT NULL COMMENT '歌曲对应专辑，为空时专辑名为”未名“',
  `ALBUM_NAME` varchar(50) DEFAULT NULL COMMENT '专辑名，可为空',
  `SONG_SRC` text COMMENT '歌曲源,存储数据链接，非空，可能是长url，故用text',
  `COPYRIGHT` tinyint(1) DEFAULT NULL COMMENT '版权：0 for 无；1 for 有；',
  `SOLE_COPYRIGHT` tinyint(1) unsigned DEFAULT '0' COMMENT '独家版权: 0 for 否， 1 for 是, 默认否',
  `THUMP_UP_COUNT` int(10) unsigned DEFAULT '0' COMMENT '点赞数',
  `CLICK_COUNT` int(10) unsigned DEFAULT '0' COMMENT '点击量',
  `SONG_LIST_VIA_COUNT` int(10) unsigned DEFAULT '0' COMMENT '收藏量（歌单收录量），被歌单收录时同时增1',
  `COMMENT_COUNT` int(10) unsigned DEFAULT '0' COMMENT '评论数',
  PRIMARY KEY (`SONG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='歌曲表SONG';

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('1', '放空', '1', null, '1', '范晓萱', '2', '谢谢', '/LiveInYouth/video/mp4/范晓萱 - 放空.mp4', '1', '1', '123', '99999', '7777', '6666');
INSERT INTO `song` VALUES ('2', '落花流水', '0', '1,2,3,4,6,9', '2', '陈奕迅', '4', 'Solidays', '/LiveInYouth/audio/mp3/陈奕迅 - 落花流水.mp3', '1', '0', '234', '2222', '3333', '4444');
INSERT INTO `song` VALUES ('3', '拥有', '0', '1,3,4,6,7', '4', '林宥嘉', '1', '美妙生活', '/LiveInYouth/audio/mp3/林宥嘉 - 拥有.mp3', '1', '0', '345', '2323', '3434', '4545');
INSERT INTO `song` VALUES ('5', '虚拟', '0', '1,2,4,5,7', '6', '陈粒', '3', '小梦大半', '/LiveInYouth/audio/mp3/陈粒 - 虚拟.mp3', '1', '0', '567', '93734', '2857', '8384');
INSERT INTO `song` VALUES ('6', '放空', '0', '1,2,6,7,8,9', '1', '范晓萱', '2', '谢谢', '/LiveInYouth/audio/mp3/范晓萱 - 放空.mp3', '1', '0', '6565', '6767', '5656', '7676');
INSERT INTO `song` VALUES ('15', '浪费', '0', null, '0', '林宥嘉', '0', '大小说家', '/LiveInYouth/audio/mp3/1743311633林宥嘉 - 浪费.mp3', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list` (
  `SONG_LIST_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '歌单表ID',
  `LIST_NAME` varchar(50) DEFAULT NULL COMMENT '歌单名,所谓歌单取出关键字',
  `SONG_ID_SET` varchar(255) DEFAULT NULL COMMENT '歌单歌曲ID的集合,以逗号隔开',
  `USER_ID` int(10) DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `LIST_TYPE` tinyint(1) unsigned DEFAULT '0' COMMENT '歌单类型：0，默认歌单；1，用户歌单',
  `THUMP_UP_COUNT` int(10) unsigned DEFAULT '0' COMMENT '歌单的赞数',
  `SONG_LIST_IMG_SRC` text COMMENT '歌单封面图片链接',
  `LIST_INTRO` varchar(255) DEFAULT NULL COMMENT '歌单简介',
  PRIMARY KEY (`SONG_LIST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8 COMMENT='歌单表，取歌单时，以歌单名为准，一首歌在这里为单条记录，以歌单名字段区分，而不是ID；\r\n一人只有一个歌单';

-- ----------------------------
-- Records of song_list
-- ----------------------------
INSERT INTO `song_list` VALUES ('1', 'live', '2,3,5,6', '3', 'Alexs', '0', '1000', '/LiveInYouth/images/discoverSongList/songList/-31657787314752-106.jpg', 'liveinyouth');
INSERT INTO `song_list` VALUES ('2', '尔雅', '2,5,6', '2', 'ixenos', '1', '999', '/LiveInYouth/images/discoverSongList/songList/907200370323951-106.jpg', '温文尔雅');
INSERT INTO `song_list` VALUES ('3', '花白', '2,3', '4', 'Angelababy', '1', '888', '/LiveInYouth/images/discoverSongList/songList/songList-3.jpg', '纯白无暇');
INSERT INTO `song_list` VALUES ('4', '暌隔', '2,3,5', '5', 'YogaLin', '1', '777', '/LiveInYouth/images/discoverSongList/songList/songList-4.jpg', '闲梦远');
INSERT INTO `song_list` VALUES ('5', '境', '5', '6', 'cesei', '1', '666', '/LiveInYouth/images/discoverSongList/songList/songList-5.jpg', '如也');
INSERT INTO `song_list` VALUES ('6', '天尽头', '2,3,6', '14', 'Allen', '1', '555', '/LiveInYouth/images/discoverSongList/songList/songList-6.jpg', '何处有相求');
INSERT INTO `song_list` VALUES ('7', '感觉体验馆', '3,5,6', '10', 'Yanlin', '1', '444', '/LiveInYouth/images/discoverSongList/songList/songList-1.jpg', '眼聆');
INSERT INTO `song_list` VALUES ('8', '没有人', '6', '11', 'lierya', '1', '333', '/LiveInYouth/images/discoverSongList/songList/songList-8.jpg', '在等着没有人');
INSERT INTO `song_list` VALUES ('9', '玉陨', '2,6', '12', 'Yuban', '1', '222', '/LiveInYouth/images/discoverSongList/songList/songList-9.jpg', '香消');
INSERT INTO `song_list` VALUES ('130', 'loce', null, '25', 'white', '1', '111', '/LiveInYouth/images/discoverSongList/songList/-191006878614752-106.jpg', 'lovelvoe');
INSERT INTO `song_list` VALUES ('131', '星星点灯', null, '27', 'rongxin', '1', '110', '/LiveInYouth/images/discoverSongList/songList/87877000P70518-215810.jpg', '着凉我的前程');

-- ----------------------------
-- Table structure for song_list_map
-- ----------------------------
DROP TABLE IF EXISTS `song_list_map`;
CREATE TABLE `song_list_map` (
  `SONG_LIST_MAP_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'song list map 的id',
  `SONG_LIST_ID` int(11) unsigned DEFAULT NULL COMMENT '歌单表ID',
  `LIST_NAME` varchar(255) DEFAULT NULL COMMENT '歌单名',
  `SONG_ID` int(11) unsigned DEFAULT NULL COMMENT '歌曲表ID',
  `SONG_NAME` varchar(255) DEFAULT NULL COMMENT '歌曲名',
  `SRC_FLAG` tinyint(1) DEFAULT '0' COMMENT '有无src的标志, 0 无， 1有',
  PRIMARY KEY (`SONG_LIST_MAP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='歌曲与歌单的映射表';

-- ----------------------------
-- Records of song_list_map
-- ----------------------------
INSERT INTO `song_list_map` VALUES ('1', '1', '聆', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('2', '2', '尔雅', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('3', '3', '花白', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('4', '4', '暌隔', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('5', '6', '天尽头', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('6', '9', '玉陨', '2', '落花流水', '1');
INSERT INTO `song_list_map` VALUES ('7', '1', '聆', '3', '拥有', '1');
INSERT INTO `song_list_map` VALUES ('8', '3', '花白', '3', '拥有', '1');
INSERT INTO `song_list_map` VALUES ('9', '4', '睽隔', '3', '拥有', '1');
INSERT INTO `song_list_map` VALUES ('10', '6', '天尽头', '3', '拥有', '1');
INSERT INTO `song_list_map` VALUES ('11', '7', '感觉体验馆', '3', '拥有', '1');
INSERT INTO `song_list_map` VALUES ('12', '1', '聆', '5', '虚拟', '1');
INSERT INTO `song_list_map` VALUES ('13', '4', '睽隔', '5', '虚拟', '1');
INSERT INTO `song_list_map` VALUES ('14', '5', '境', '5', '虚拟', '1');
INSERT INTO `song_list_map` VALUES ('15', '7', '感觉体验馆', '5', '虚拟', '1');
INSERT INTO `song_list_map` VALUES ('16', '1', '聆', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('17', '6', '天尽头', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('18', '7', '感觉体验馆', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('19', '8', '没有人', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('20', '9', '玉殒', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('21', '2', '尔雅', '5', '虚拟', '1');
INSERT INTO `song_list_map` VALUES ('22', '2', '尔雅', '6', '放空', '1');
INSERT INTO `song_list_map` VALUES ('25', '2', '雅', '15', '浪费', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户表唯一ID，自增',
  `USER_NAME` varchar(16) NOT NULL COMMENT '用户名，字母开头，允许5-16字节，允许字母数字下划线，前端限制其不可为邮箱，否则有二义性',
  `USER_PASSWORD` varchar(16) NOT NULL COMMENT '用户密码，以字母开头，长度在6-16之间',
  `USER_EMAIL` varchar(20) NOT NULL COMMENT '用户邮箱，同时作为用户账户',
  `USER_POWER` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户权限：0：user，1：administrator',
  `SUBS_FLAG` tinyint(1) unsigned DEFAULT NULL COMMENT 'email订阅标志：0 for 不订阅； 1 for 订阅',
  `AVATAR_SRC` text NOT NULL COMMENT '头像链接',
  `SONG_LIST_ID` int(10) unsigned DEFAULT NULL COMMENT '用户唯一歌单表id',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户表USER';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'admin@qq.com', '1', '0', '/LiveInYouth/images/avatar/person1.jpg', '0');
INSERT INTO `user` VALUES ('2', 'ixenos', '123456', 'ixenos@qq.com', '0', '1', '/LiveInYouth/images/avatar/483423216825b0d5jw1f2jthzp7ghj22c02c01ky.jpg', '2');
INSERT INTO `user` VALUES ('3', 'Alexs', '123456', 'Alexs@qq.com', '0', '1', '/LiveInYouth/images/avatar/-213263911992264-106.jpg', '1');
INSERT INTO `user` VALUES ('4', 'Angelababy', '123456', 'Angelababy@qq.com', '0', '0', '/LiveInYouth/images/avatar/person4.jpg', '3');
INSERT INTO `user` VALUES ('5', 'YogaLin', '123456', 'YogaLin@qq.com', '0', '0', '/LiveInYouth/images/avatar/-100321709995335-106.jpg', '4');
INSERT INTO `user` VALUES ('6', 'cesei', '123456', 'cesei@qq.com', '0', '0', '/LiveInYouth/images/avatar/1826676671124402-106.jpg', '5');
INSERT INTO `user` VALUES ('9', 'q123123q', '123456', 'q123123q@qq.com', '0', '0', '/LiveInYouth/images/avatar/738368951264214-106.jpg', '6');
INSERT INTO `user` VALUES ('10', 'Yanlin', '123456', 'Yanlin@qq.com', '0', '0', '/LiveInYouth/images/avatar/138502533021475-106.jpg', '7');
INSERT INTO `user` VALUES ('11', 'lierya', '123456', 'lierya@qq.com', '0', '0', '/LiveInYouth/images/avatar/person1.jpg', '8');
INSERT INTO `user` VALUES ('12', 'Yuban', '123456', 'Yuban@qq.com', '0', '0', '/LiveInYouth/images/avatar/person2.jpg', '9');
INSERT INTO `user` VALUES ('13', 'Dada', '123456', 'Dada@qq.com', '0', '0', '/LiveInYouth/images/avatar/person3.jpg', '0');
INSERT INTO `user` VALUES ('14', 'Allen', '123456', 'Allen@qq.com', '0', '0', '/LiveInYouth/images/avatar/person1.jpg', '0');
INSERT INTO `user` VALUES ('22', 'poppop', '123456', 'poppop@qq.com', '0', '0', '/LiveInYouth/images/avatar/person3.jpg', '0');
INSERT INTO `user` VALUES ('23', 'bangi', '123456', 'bangi@qq.com', '0', '0', '/LiveInYouth/images/avatar/person2.jpg', '0');
INSERT INTO `user` VALUES ('24', 'faker', '123456', 'faker@qq.com', '0', '0', '/LiveInYouth/images/avatar/person4.jpg', '0');
INSERT INTO `user` VALUES ('25', 'white', '123456', 'white@qq.com', '0', '0', '/LiveInYouth/images/avatar/person3.jpg', '130');
