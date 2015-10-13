CREATE TABLE `eval_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` varchar(255) DEFAULT NULL COMMENT '评估编号',
  `name` varchar(255) DEFAULT NULL COMMENT '申请人姓名',
  `qq` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL COMMENT '专业',
  `school_year` varchar(255) DEFAULT NULL COMMENT '入学年份',
  `career` varchar(255) DEFAULT NULL COMMENT '职业',
  `education` int(11) DEFAULT NULL COMMENT '学历',
  `ip` varchar(255) DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL,
  `is_processed` bit(1) DEFAULT b'0' COMMENT '是否已处理',
  `process_result` varchar(2000) DEFAULT NULL COMMENT '处理结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `eval_id` (`eval_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `holland_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eval_id` varchar(255) DEFAULT NULL COMMENT '评测编号',
  `eval_name` varchar(255) DEFAULT NULL COMMENT '评测姓名',
  `eval_qq` varchar(255) DEFAULT NULL COMMENT '评测QQ',
  `eval_content` text COMMENT '测评内容',
  `talents_type` varchar(255) DEFAULT NULL COMMENT '人才类型',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `r_value` int(11) DEFAULT NULL,
  `s_value` int(11) DEFAULT NULL,
  `i_value` int(11) DEFAULT NULL,
  `e_value` int(11) DEFAULT NULL,
  `a_value` int(11) DEFAULT NULL,
  `c_value` int(11) DEFAULT NULL,
  `eval_ip` varchar(255) DEFAULT NULL COMMENT '评估的IP地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_holland_result_eval_Id` (`eval_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;