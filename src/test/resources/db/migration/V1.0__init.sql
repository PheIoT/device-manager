-- ----------------------------
-- Table structure for t_datapoint
-- ----------------------------
DROP TABLE IF EXISTS `t_productproperty`;
CREATE TABLE `t_productproperty`
(
  `id`           bigint NOT NULL AUTO_INCREMENT,
  `code`         varchar(255) DEFAULT NULL,
  `displayName`  varchar(255) DEFAULT NULL,
  `dataType`     varchar(255) DEFAULT NULL,
  `dataTypeConf` varchar(255) DEFAULT NULL,
  `functionType` varchar(255) DEFAULT NULL,
  `pkey`         varchar(32)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`
(
  `id`          bigint NOT NULL AUTO_INCREMENT,
  `dkey`        varchar(32)  DEFAULT NULL,
  `secret`      varchar(32)  DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `dsn`         varchar(255) DEFAULT NULL,
  `isOnline`    bit          DEFAULT 0,
  `isEnabled`   bit          DEFAULT 0,
  `remark`      varchar(255) DEFAULT NULL,
  `createAt`    datetime     DEFAULT NULL,
  `updateAt`    datetime     DEFAULT NULL,
  `pkey`        varchar(32)  DEFAULT NULL,
  `token`       varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`
(
  `id`          bigint NOT NULL AUTO_INCREMENT,
  `pkey`        varchar(255) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `secret`      varchar(255) DEFAULT NULL,
  `nodeType`    varchar(255) DEFAULT NULL,
  `commType`    varchar(255) DEFAULT NULL,
  `isEnabled`   bit          DEFAULT 0,
  `createAt`    datetime     DEFAULT NULL,
  `updateAt`    datetime     DEFAULT NULL,
  `remark`      varchar(255) DEFAULT NULL,
  `uid`        varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO t_product
VALUES (1, 'testkey-12345678901234567890abcd', '测试项目', 'testkey-12345678901234567890abcd', 'secret-12345', 'wifi', 0,
        '2019-03-29 10:00:03', null, 'remark test', 'user-12345678901234567890abcdgef');

INSERT INTO t_productproperty
VALUES (1, 'LED-ONOFF', 'LED开关', 'boolean', '', 'rw',
        'testkey-12345678901234567890abcd');

INSERT INTO t_productproperty
VALUES (2, 'LED-RED', 'LED颜色-red', 'number', '{ "addition": 1, "max": 255, "ratio": 1, "min": 0 }', 'rw',
        'testkey-12345678901234567890abcd');

INSERT INTO t_productproperty
VALUES (3, 'LED-YELLOW', 'LED颜色-yellow', 'number', '{ "addition": 1, "max": 255, "ratio": 1, "min": 0 }', 'rw',
        'testkey-12345678901234567890abcd');

INSERT INTO t_productproperty
VALUES (4, 'LED-BLUE', 'LED颜色-blue', 'number', '{ "addition": 1, "max": 255, "ratio": 1, "min": 0 }', 'rw',
        'testkey-12345678901234567890abcd');