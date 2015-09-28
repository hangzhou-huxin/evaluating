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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `holland_apply` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `manage_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;