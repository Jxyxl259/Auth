/*
Navicat MySQL Data Transfer

Source Server         : auth(new)
Source Server Version : 50635
Source Host           : 10.1.135.155:3307
Source Database       : auth_dev

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-08-20 10:40:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_company
-- ----------------------------
DROP TABLE IF EXISTS `app_company`;
CREATE TABLE `app_company` (
  `COMPANY_ID` varchar(32) NOT NULL COMMENT '机构ID',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `COMPANY_CNAME` varchar(200) DEFAULT NULL COMMENT '机构简体名称',
  `COMPANY_ENAME` varchar(200) DEFAULT NULL COMMENT '机构英文名称',
  `COMPANY_TNAME` varchar(200) DEFAULT NULL COMMENT '机构繁体名称',
  `COMPANY_LEVEL` int(10) DEFAULT NULL COMMENT '菜单层级',
  `PARENT_COMPANY_ID` varchar(32) DEFAULT NULL COMMENT '上级菜单ID',
  `VALID_FLAG` char(1) DEFAULT NULL COMMENT '是否有效：0无效，1 有效',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `FLAG` varchar(10) DEFAULT NULL COMMENT '其他标志',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统机构表';

-- ----------------------------
-- Table structure for app_job
-- ----------------------------
DROP TABLE IF EXISTS `app_job`;
CREATE TABLE `app_job` (
  `JOB_ID` varchar(32) NOT NULL COMMENT '任务ID',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `JOB_NAME` varchar(600) NOT NULL COMMENT '任务名称',
  `JOB_GROUP_NAME` varchar(600) NOT NULL COMMENT '任务组',
  `JOB_STATUS` varchar(3) NOT NULL COMMENT '任务状态 0 未运行 1 运行中 2 暂停 3 作废',
  `CRON_EXPRESSION` varchar(100) NOT NULL COMMENT '任务表达式',
  `JOB_CLASS` varchar(150) DEFAULT NULL COMMENT '任务执行类全名',
  `SPRING_ID` varchar(100) DEFAULT NULL COMMENT '任务执行类在SPRING配置中的ID',
  `METHOD_NAME` varchar(100) DEFAULT NULL COMMENT '任务执行方法，无参。【注：选择JOB_CLASS或SPRING_ID时必录】',
  `RESTFUL_URL` varchar(400) DEFAULT NULL COMMENT '任务执行RESTFUL服务URL，无参。【注：JOB_CLASS、SPRING_ID和RESTFUL_URL方式三选一】',
  `REMARK` varchar(600) DEFAULT NULL COMMENT '任务说明',
  `FLAG` varchar(10) DEFAULT NULL COMMENT '其他标志',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='定时任务表';

-- ----------------------------
-- Table structure for app_parameter
-- ----------------------------
DROP TABLE IF EXISTS `app_parameter`;
CREATE TABLE `app_parameter` (
  `PARAMETER_ID` varchar(64) NOT NULL COMMENT '参数ID',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `PARAMETER_TYPE` varchar(100) NOT NULL COMMENT '参数类型',
  `PARAMETER_CODE` varchar(100) NOT NULL COMMENT '参数代码',
  `PARAMETER_CNAME` varchar(50) DEFAULT NULL COMMENT '参数简体名称',
  `PARAMETER_ENAME` varchar(50) DEFAULT NULL COMMENT '参数英文名称',
  `PARAMETER_TNAME` varchar(50) DEFAULT NULL COMMENT '参数繁体名称',
  `VALID_FLAG` int(10) DEFAULT NULL COMMENT '是否有效：0无效，1 有效',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `FLAG` varchar(10) DEFAULT NULL COMMENT '其他标志',
  PRIMARY KEY (`PARAMETER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Table structure for app_parameter_type
-- ----------------------------
DROP TABLE IF EXISTS `app_parameter_type`;
CREATE TABLE `app_parameter_type` (
  `PARAMETER_TYPE_ID` varchar(64) NOT NULL COMMENT '参数类型ID',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `PARAMETER_TYPE` varchar(100) NOT NULL COMMENT '参数类型类型',
  `PARAMETER_TYPE_CNAME` varchar(50) DEFAULT NULL COMMENT '参数类型简体名称',
  `PARAMETER_TYPE_ENAME` varchar(50) DEFAULT NULL COMMENT '参数类型英文名称',
  `PARAMETER_TYPE_TNAME` varchar(50) DEFAULT NULL COMMENT '参数类型繁体名称',
  `VALID_FLAG` char(1) DEFAULT NULL COMMENT '是否有效：0无效，1 有效',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `FLAG` varchar(10) DEFAULT NULL COMMENT '其他标志',
  PRIMARY KEY (`PARAMETER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数类型表';

-- ----------------------------
-- Table structure for app_resource
-- ----------------------------
DROP TABLE IF EXISTS `app_resource`;
CREATE TABLE `app_resource` (
  `RESOURCE_ID` varchar(32) NOT NULL COMMENT '资源ID',
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `RESOURCE_NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `RESOURCE_TYPE` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `RESOURCE_LEVEL` decimal(2,0) DEFAULT NULL COMMENT '资源层级',
  `PARENT_RESOURCE_ID` varchar(32) DEFAULT NULL COMMENT '上级资源ID',
  `RESOURCE_ICON_CLASS` varchar(100) DEFAULT NULL COMMENT '资源图标CLASS',
  `ACTION_URL` varchar(100) DEFAULT NULL COMMENT '提交URL',
  `END_FLAG` char(1) DEFAULT NULL COMMENT '节点标志',
  `DISPLAY_ORDER` int(10) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `VALID_FLAG` int(10) DEFAULT NULL COMMENT '是否有效：0无效，1 有效',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Table structure for app_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `app_role_resource`;
CREATE TABLE `app_role_resource` (
  `ROLE_RESOURCE_ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '角色资源关联ID',
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `RESOURCE_ID` varchar(32) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`ROLE_RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2500 DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '员工ID',
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `USER_CODE` varchar(50) NOT NULL COMMENT '员工代码',
  `USER_CNAME` varchar(50) DEFAULT NULL COMMENT '员工简体中文名称',
  `USER_TNAME` varchar(50) DEFAULT NULL COMMENT '员工繁体中文名称',
  `USER_ENAME` varchar(50) DEFAULT NULL COMMENT '员工英文名称',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `SALT` varchar(100) DEFAULT NULL COMMENT '加密密码的盐',
  `SEAL` varchar(100) DEFAULT NULL COMMENT '印鉴',
  `PASSWORD_SET_DATE` datetime DEFAULT NULL COMMENT '密码设置日期',
  `PASSWORD_EXPIRE_DATE` datetime NOT NULL COMMENT '密码过期日期',
  `COMPANY_CODE` varchar(10) NOT NULL COMMENT '归属机构代码',
  `PHONE` varchar(18) DEFAULT NULL COMMENT '电话号码',
  `MOBILE` varchar(18) DEFAULT NULL COMMENT '手机号码',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '通信地址',
  `POST_CODE` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `VALID_FLAG` char(1) NOT NULL COMMENT '1-有效；0-无效',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `FLAG` varchar(255) DEFAULT NULL COMMENT '标志字段',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工代码表';

-- ----------------------------
-- Table structure for app_user_exclude_resource
-- ----------------------------
DROP TABLE IF EXISTS `app_user_exclude_resource`;
CREATE TABLE `app_user_exclude_resource` (
  `USER_EXCLUDE_RESOURCE_ID` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户资源排除关联ID',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `RESOURCE_ID` varchar(32) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`USER_EXCLUDE_RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户资源排除关联表';

-- ----------------------------
-- Table structure for app_user_role
-- ----------------------------
DROP TABLE IF EXISTS `app_user_role`;
CREATE TABLE `app_user_role` (
  `USER_ROLE_ID` varchar(32) NOT NULL COMMENT '用户角色关联ID',
  `CREATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for qz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_blob_triggers`;
CREATE TABLE `qz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qz_calendars`;
CREATE TABLE `qz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_cron_triggers`;
CREATE TABLE `qz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_fired_triggers`;
CREATE TABLE `qz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qz_job_details`;
CREATE TABLE `qz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qz_locks`;
CREATE TABLE `qz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qz_paused_trigger_grps`;
CREATE TABLE `qz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qz_scheduler_state`;
CREATE TABLE `qz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_simple_triggers`;
CREATE TABLE `qz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_simprop_triggers`;
CREATE TABLE `qz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qz_triggers`;
CREATE TABLE `qz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `account_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_name` varchar(80) DEFAULT NULL COMMENT '用户名称',
  `app_id` varchar(50) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `app_code` varchar(50) DEFAULT NULL,
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=644 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_account_auth_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_account_auth_mapping`;
CREATE TABLE `t_account_auth_mapping` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mapping_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_auth_callback_info
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_callback_info`;
CREATE TABLE `t_auth_callback_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `app_id` varchar(64) NOT NULL COMMENT 'app_id',
  `project_code` varchar(50) DEFAULT NULL COMMENT '协议号',
  `data_source` varchar(50) DEFAULT NULL COMMENT '渠道代号',
  `content` text NOT NULL COMMENT '回调内容',
  `business_no` varchar(64) NOT NULL COMMENT '业务单号',
  `deal_type` varchar(2) NOT NULL COMMENT '处理类型 01:支付成功回调',
  `deal_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '处理状态:0未处理成功,1已处理成功',
  `deal_count` int(11) NOT NULL DEFAULT '0' COMMENT '处理次数',
  `callback_url` varchar(50) NOT NULL COMMENT '第三方回调地址',
  `created_user` varchar(50) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新人',
  `updated_date` datetime NOT NULL COMMENT '更新时间',
  `invalid_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有效:0正常,1作废值',
  PRIMARY KEY (`id`),
  KEY `idx_callback_invalid_flag` (`invalid_flag`) USING BTREE,
  KEY `idx_callback_created_date` (`created_date`) USING BTREE,
  KEY `idx_callback_deal_status` (`deal_status`) USING BTREE,
  KEY `idx_callback_deal_count` (`deal_count`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8076 DEFAULT CHARSET=utf8 COMMENT='回调信息表';

-- ----------------------------
-- Table structure for t_auth_common_token
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_common_token`;
CREATE TABLE `t_auth_common_token` (
  `token_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) NOT NULL,
  `app_secret` varchar(50) DEFAULT NULL,
  `open_id` varchar(50) DEFAULT NULL,
  `access_token` varchar(50) DEFAULT NULL,
  `refresh_token` varchar(50) DEFAULT NULL,
  `expiretime` datetime DEFAULT NULL,
  `ref_expire_time` datetime DEFAULT NULL,
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `open_id` (`open_id`),
  UNIQUE KEY `access_token` (`access_token`),
  UNIQUE KEY `refresh_token` (`refresh_token`),
  UNIQUE KEY `app_id` (`app_id`,`app_secret`)
) ENGINE=InnoDB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_auth_encrypt
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_encrypt`;
CREATE TABLE `t_auth_encrypt` (
  `auth_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `auth_type` varchar(30) DEFAULT NULL COMMENT '鉴权类型',
  `auth_param1` varchar(500) DEFAULT NULL,
  `auth_param2` varchar(500) DEFAULT NULL,
  `encrypt_type` varchar(30) DEFAULT NULL COMMENT '加密类型',
  `encrypt_param1` varchar(500) DEFAULT NULL,
  `encrypt_param2` varchar(500) DEFAULT NULL,
  `app_id` varchar(50) DEFAULT NULL COMMENT 'app_id',
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_auth_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_mapping`;
CREATE TABLE `t_auth_mapping` (
  `mapping_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '方案id',
  `request_type` varchar(30) DEFAULT NULL COMMENT '请求类型',
  `request_url` varchar(100) DEFAULT NULL COMMENT '请求url',
  `server_id` int(11) DEFAULT NULL COMMENT '服务id',
  `auth_id` int(11) DEFAULT NULL COMMENT '用于兼容旧渠道有加密情况',
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`mapping_id`),
  KEY `FK_t_auth_mapping` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1341 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_auth_pub_code
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_pub_code`;
CREATE TABLE `t_auth_pub_code` (
  `parameter_id` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `parameter_name` varchar(50) NOT NULL COMMENT '参数名',
  `parameter_value` varchar(100) NOT NULL COMMENT '参数值',
  `parameter_describe` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `parameter_type` varchar(50) NOT NULL COMMENT '参数类型',
  `type_describe` varchar(255) DEFAULT NULL,
  `parent_id` int(6) DEFAULT NULL COMMENT '参数父级，null或主键id中的值',
  `level` int(1) DEFAULT NULL COMMENT '参数级别。parent_id为null时为1。依次类推',
  `valid_flag` int(1) NOT NULL COMMENT '是否有效。1：有效；0：无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_user` varchar(30) DEFAULT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`parameter_id`),
  UNIQUE KEY `key` (`parameter_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10160 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_callback_url
-- ----------------------------
DROP TABLE IF EXISTS `t_callback_url`;
CREATE TABLE `t_callback_url` (
  `callback_url_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `callback_env` varchar(10) DEFAULT NULL COMMENT '环境类型',
  `callback_type` varchar(30) DEFAULT NULL COMMENT '回调类型',
  `callback_url` varchar(500) DEFAULT NULL COMMENT '回调路径',
  `callback_method` varchar(20) DEFAULT NULL,
  `app_id` varchar(50) DEFAULT NULL,
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`callback_url_id`),
  KEY `fk_t_callback_url_t_project1_idx` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=742 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_data_source
-- ----------------------------
DROP TABLE IF EXISTS `t_data_source`;
CREATE TABLE `t_data_source` (
  `data_source_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `data_source` varchar(50) NOT NULL COMMENT '渠道代码',
  `source_name` varchar(100) NOT NULL COMMENT '渠道名称',
  `app_id` varchar(50) DEFAULT NULL COMMENT 'app_id',
  `auth_id` int(11) DEFAULT NULL COMMENT '鉴权id,t_auth表主键',
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`data_source_id`),
  UNIQUE KEY `idx_data_source_source_flag` (`data_source`,`valid_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_parameters_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_parameters_dictionary`;
CREATE TABLE `t_parameters_dictionary` (
  `parameter_id` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `parameter_name` varchar(30) NOT NULL COMMENT '参数名',
  `parameter_value` varchar(100) NOT NULL COMMENT '参数值',
  `parameter_describe` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `parameter_type` varchar(30) NOT NULL COMMENT '参数类型',
  `type_describe` varchar(255) DEFAULT NULL,
  `parent_id` int(6) DEFAULT NULL COMMENT '参数父级，null或主键id中的值',
  `level` int(1) DEFAULT NULL COMMENT '参数级别。parent_id为null时为1。依次类推',
  `valid_flag` int(1) NOT NULL COMMENT '是否有效。1：有效；0：无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_user` varchar(30) DEFAULT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`parameter_id`),
  UNIQUE KEY `key` (`parameter_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10077 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `project_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_code` varchar(45) DEFAULT NULL COMMENT '方案代码',
  `project_name` varchar(50) DEFAULT NULL COMMENT '方案名称',
  `account_id` int(11) DEFAULT NULL COMMENT 'app_id',
  `auth_id` int(11) DEFAULT NULL COMMENT '鉴权id,t_auth表主键',
  `data_source_id` int(11) DEFAULT NULL COMMENT '渠道id',
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `is_default` char(1) NOT NULL DEFAULT '0' COMMENT '是否默认方案,1是默认方案0不是',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=486 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_server
-- ----------------------------
DROP TABLE IF EXISTS `t_server`;
CREATE TABLE `t_server` (
  `server_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `server_type` varchar(10) DEFAULT NULL COMMENT '服务类型',
  `server_version` varchar(10) DEFAULT NULL COMMENT '服务版本号',
  `server_env` varchar(10) DEFAULT NULL COMMENT '环境类型',
  `server_status` varchar(5) DEFAULT NULL COMMENT '服务状态',
  `system_name` varchar(20) DEFAULT NULL,
  `server_url` varchar(500) DEFAULT NULL COMMENT '服务url',
  `valid_flag` char(1) NOT NULL COMMENT '有效标识,1有效,0无效',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认插入时间',
  `created_user` varchar(50) NOT NULL COMMENT '创建用户,插入用户',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间,默认插入更新时间',
  `updated_user` varchar(50) NOT NULL COMMENT '更新用户',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1194 DEFAULT CHARSET=utf8;
