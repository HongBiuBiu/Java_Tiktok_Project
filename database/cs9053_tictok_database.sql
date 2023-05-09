SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(24) NOT NULL,
  `vloger_id` varchar(24) NOT NULL COMMENT 'Which author (vlogger) is associated with the video commented on id',
  `father_comment_id` varchar(24) NOT NULL COMMENT 'If it is a reply message, then this article is a sub-message and needs to be associated with the query',
  `vlog_id` varchar(24) NOT NULL COMMENT 'The video id of the reply',
  `comment_user_id` varchar(24) NOT NULL COMMENT 'User id who posted the message',
  `content` varchar(128) NOT NULL COMMENT 'Message content',
  `like_counts` int(20) NOT NULL COMMENT 'Total number of likes of messages',
  `create_time` datetime NOT NULL COMMENT 'Message Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Comment form';

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES ('211004K23D53K4ZC', '211003H4SG5Y1ZF8', '0', '2110048SH1BRRP94', '211003H4SG5Y1ZF7', 'good', 0, '2023-05-04 23:55:34');
INSERT INTO `comment` VALUES ('21100580D993C3XP', '211003H4SG5Y1ZF8', '211004K23D53K4ZC', '2110048SH1BRRP94', '211003H4SG5Y1ZF8, 'NYU', 0, '2023-05-04 23:55:34');
INSERT INTO `comment` VALUES ('21100580H3HS24PH', '211003H4SG5Y1ZF8', '0', '2110057Z0WAZWBC0', '211003H4SG5Y1ZF7', 'Java', 0, '2023-05-04 23:55:34');
COMMIT;

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `id` varchar(24) NOT NULL,
  `vloger_id` varchar(24) NOT NULL COMMENT 'Writer user id',
  `fan_id` varchar(24) NOT NULL COMMENT 'fan user id',
  `is_fan_friend_of_mine` int(1) NOT NULL COMMENT 'Whether the fan is a friend of the vlogger, if it becomes a friend, this field needs to be set to 1 for both sides of this table, if one person takes off, then both sides need to be set to 0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `writer_id` (`vloger_id`,`fan_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='follower table';

-- ----------------------------
-- Records of fans
-- ----------------------------
BEGIN;
INSERT INTO `fans` VALUES ('2110057Y9ADR26A8', '211003H4SG5Y1ZF7', '211003H4SG5Y1ZF8', 1);
INSERT INTO `fans` VALUES ('2110057YB9CM344H', '211003H4SG5Y1ZF8', '211003H4SG5Y1ZF7', 1);
COMMIT;

-- ----------------------------
-- Table structure for my_liked_vlog
-- ----------------------------
DROP TABLE IF EXISTS `my_liked_vlog`;
CREATE TABLE `my_liked_vlog` (
  `id` varchar(24) NOT NULL,
  `user_id` varchar(24) NOT NULL COMMENT 'user id',
  `vlog_id` varchar(24) NOT NULL COMMENT 'liked vlog id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `writer_id` (`user_id`,`vlog_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Like short video association table';

-- ----------------------------
-- Records of my_liked_vlog
-- ----------------------------
BEGIN;
INSERT INTO `my_liked_vlog` VALUES ('211004HM4NYMM9YW', '211003H4SG5Y1ZF7', '2110048SH1BRRP94');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(24) NOT NULL,
  `mobile` varchar(32) NOT NULL COMMENT 'phone',
  `nickname` varchar(16) NOT NULL COMMENT 'nickname',
  `imooc_num` varchar(16) NOT NULL COMMENT 'tictok id',
  `face` varchar(128) NOT NULL COMMENT 'pic',
  `gender` int(11) NOT NULL COMMENT 'gender 1:male  0:female  2:private',
  `birthday` date NOT NULL COMMENT 'birthday',
  `country` varchar(32) DEFAULT NULL COMMENT 'country',
  `province` varchar(32) DEFAULT NULL COMMENT 'province',
  `city` varchar(32) DEFAULT NULL COMMENT 'city',
  `district` varchar(32) DEFAULT NULL COMMENT 'district',
  `description` varchar(100) NOT NULL COMMENT 'description',
  `bg_img` varchar(255) DEFAULT NULL COMMENT 'background pic',
  `can_tictok_num_be_updated` int(1) NOT NULL COMMENT 'modify id 1：default,can be modified；0，cannot be modified',
  `created_time` datetime NOT NULL COMMENT 'creation time',
  `updated_time` datetime NOT NULL COMMENT 'update time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='user table';

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('211003H4SG5Y1ZF7', '13912345678', 'mingkang1230', 'mingkang9053', 'http://39.105.38.113:9000/abc/1001/1632969079.jpg', 0, '1999-12-30', 'China', '', '', '', 'nothing', 'http://39.105.38.113:9000/abc/1001/1632969089.jpg', 1, '2023-04-29 23:44:51', '2023-04-29 23:44:51');
INSERT INTO `users` VALUES ('211003H4SG5Y1ZF8', '13961888888', 'Qingyue923', 'qingyue923', 'http://122.152.205.72:88/group1/M00/00/05/CpoxxF6ZUySASMbOAABBAXhjY0Y649.png', 0, '1999-09-23', 'China', '', '', '', 'nothing', NULL, 1, '2023-04-29 22:39:32', '2023-04-29 22:39:32');
COMMIT;

-- ----------------------------
-- Table structure for vlog
-- ----------------------------
DROP TABLE IF EXISTS `vlog`;
CREATE TABLE `vlog` (
  `id` varchar(24) NOT NULL,
  `vloger_id` varchar(24) NOT NULL COMMENT 'Corresponding to user table id, vlog video publisher',
  `url` varchar(255) NOT NULL COMMENT 'Video Playback Address',
  `cover` varchar(255) NOT NULL COMMENT 'Video Cover',
  `title` varchar(128) DEFAULT NULL COMMENT 'Video title, can be empty',
  `width` int(6) NOT NULL COMMENT 'width',
  `height` int(6) NOT NULL COMMENT 'height',
  `like_counts` int(12) NOT NULL COMMENT 'Total number of likes',
  `comments_counts` int(12) NOT NULL COMMENT 'Total number of comments',
  `is_private` int(1) NOT NULL COMMENT 'Whether private or not, the user can set private, so that it can not be public than people to see',
  `created_time` datetime NOT NULL COMMENT 'Creation time Creation time',
  `updated_time` datetime NOT NULL COMMENT 'Update Time Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Short video table';

-- ----------------------------
-- Records of vlog
-- ----------------------------
BEGIN;
INSERT INTO `vlog` VALUES ('2110048SH1BRRP94', '211003H4SG5Y1ZF8', 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-a510f11a-afcf-48f9-bcff-848ce1501f2f/ebab5889-8dd4-4705-b5a9-2e02a4504457.mp4', 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-a510f11a-afcf-48f9-bcff-848ce1501f2f/ebab5889-8dd4-4705-b5a9-2e02a4504457.mp4?x-oss-process=video/snapshot,t_1,f_jpg,ar_auto', '', 772, 540, 1, 3, 0, '2023-04-30 10:30:13', '2023-04-30 10:30:13');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
