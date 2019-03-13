
-- ----------------------------
-- Table structure for t_datapoint
-- ----------------------------
DROP TABLE IF EXISTS `t_datapoint`;
CREATE TABLE `t_datapoint`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `kay`        varchar(255) DEFAULT NULL,
  `name`       varchar(255) DEFAULT NULL,
  `dataType`   varchar(255) DEFAULT NULL,
  `productKey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`
(
  `id`         int(10) NOT NULL AUTO_INCREMENT,
  `name`       varchar(255) DEFAULT NULL,
  `kay`        varchar(255) DEFAULT NULL,
  `mac`        varchar(255) DEFAULT NULL,
  `isOnline`   varchar(255) DEFAULT NULL,
  `isEnabled`  varchar(255) DEFAULT NULL,
  `remark`     varchar(255) DEFAULT NULL,
  `createAt`   datetime     DEFAULT NULL,
  `updateAt`   datetime     DEFAULT NULL,
  `productKey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_deviceshadow
-- ----------------------------
DROP TABLE IF EXISTS `t_deviceshadow`;
CREATE TABLE `t_deviceshadow`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `productKey` varchar(255) DEFAULT NULL,
  `devKey`     varchar(255) DEFAULT NULL,
  `updateAt`   datetime     DEFAULT NULL,
  `value`      text,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_msgchannel
-- ----------------------------
DROP TABLE IF EXISTS `t_msgchannel`;
CREATE TABLE `t_msgchannel`
(
  `id`          int(11) NOT NULL AUTO_INCREMENT,
  `product_key` varchar(255) DEFAULT NULL,
  `path`        varchar(255) DEFAULT NULL,
  `mod`         varchar(255) DEFAULT NULL,
  `qos`         int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`
(
  `id`        int(11) NOT NULL AUTO_INCREMENT,
  `kay`       varchar(255) DEFAULT NULL,
  `name`      varchar(255) DEFAULT NULL,
  `secret`    varchar(255) DEFAULT NULL,
  `type`      varchar(255) DEFAULT NULL,
  `isEnabled` varchar(255) DEFAULT NULL,
  `createAt`  datetime     DEFAULT NULL,
  `remark`    varchar(255) DEFAULT NULL,
  `userKey`   varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;
