/*Table structure for table `locale_config` */

DROP TABLE IF EXISTS `locate_config`;

CREATE TABLE `locate_config` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `locale_code` VARCHAR(10) NOT NULL COMMENT '国家化的标识',
  `locale_name` VARCHAR(20) DEFAULT NULL COMMENT '那个国家',
  `area` VARCHAR(10) DEFAULT NULL COMMENT '使用地区',
  `add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(4) DEFAULT '0' COMMENT '是不是使用标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `locale_code` (`locale_code`,`del_flag`)
) ENGINE=INNODB AUTO_INCREMENT=2788 DEFAULT CHARSET=utf8;

/*Data for the table `locale_config` */

LOCK TABLES `locate_config` WRITE;

INSERT  INTO `locate_config`(`id`,`locale_code`,`locale_name`,`area`,`add_time`,`update_time`,`del_flag`) VALUES (1,'zh_CN','中文简体','中国','2018-07-15 12:50:36','2018-07-15 12:50:36',0),(2,'zh_TW','中文繁体','中国台湾','2018-07-15 12:58:24','2018-07-15 12:58:24',0),(3,'en_US','英语','美国','2018-07-15 12:39:46','2018-07-15 12:39:46',0),(4,'tr_TR','土耳其语','土耳其','2018-07-15 12:59:52','2018-07-15 12:59:52',0),(5,'sv_SE','瑞典语','瑞典','2018-07-15 13:00:08','2018-07-15 13:00:08',0),(6,'pt_PT','葡萄牙语','葡萄牙','2018-07-15 13:00:28','2018-07-15 13:00:28',0),(7,'no_NO_B','挪威语(Bokm?l)','挪威','2018-07-15 13:01:18','2018-07-15 13:01:18',0),(8,'no_NO','挪威语(Nynorsk)','挪威','2018-07-15 13:01:32','2018-07-15 13:01:32',0),(9,'nl_NL','荷兰语','荷兰','2018-07-15 13:02:08','2018-07-15 13:02:08',0),(10,'da_DK','丹麦语','丹麦','2018-07-15 13:05:05','2018-07-15 13:05:05',0),(12,'DE_AT','德语','奥地利','2018-07-15 13:07:13','2018-07-15 13:07:13',0),(49,'DE_CH','德语','瑞士','2018-07-15 13:14:27','2018-07-15 13:14:27',0),(50,'DE_DE','德语','德国','2018-07-15 13:14:37','2018-07-15 13:14:37',0),(51,'el_GR','希腊语','希腊','2018-07-15 13:14:37','2018-07-15 13:14:37',0),(67,'en_CA','英语','加拿大','2018-07-15 13:15:24','2018-07-15 13:15:24',0),(68,'en_GB','英语','联合王国','2018-07-15 13:15:24','2018-07-15 13:15:24',0),(74,'en_IE','英语','爱尔兰','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(75,'es_ES','西班牙语','西班牙','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(76,'fi_FI','芬兰语','芬兰','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(77,'fr_BE','法语','比利时','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(78,'fr_CA','法语','加拿大','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(79,'fr_CH','法语','瑞士','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(80,'fr_FR','法语','法国','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(81,'it_CH','意大利语','瑞士','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(82,'it_IT','意大利语','意大利','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(83,'ja_JP','日语','日本','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(84,'ko_KR','韩国语','韩国','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(85,'nl_BE','荷兰语','比利时','2018-07-15 13:15:46','2018-07-15 13:15:46',0),(1202,'ar','阿拉伯文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1203,'ar_AE','阿拉伯文','阿拉伯联合酋长国','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1204,'ar_BH','阿拉伯文','巴林','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1205,'ar_DZ','阿拉伯文','阿尔及利亚','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1206,'ar_EG','阿拉伯文','埃及','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1207,'ar_IQ','阿拉伯文','伊拉克','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1208,'ar_JO','阿拉伯文','约旦','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1209,'ar_KW','阿拉伯文','科威特','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1210,'ar_LB','阿拉伯文','黎巴嫩','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1211,'ar_LY','阿拉伯文','利比亚','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1212,'ar_MA','阿拉伯文','摩洛哥','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1213,'ar_OM','阿拉伯文','阿曼','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1214,'ar_QA','阿拉伯文','卡塔尔','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1215,'ar_SA','阿拉伯文','沙特阿拉伯','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1216,'ar_SD','阿拉伯文','苏丹','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1217,'ar_SY','阿拉伯文','叙利亚','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1218,'ar_TN','阿拉伯文','突尼斯','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1219,'ar_YE','阿拉伯文','也门','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1220,'be','白俄罗斯文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1221,'be_BY','白俄罗斯文','白俄罗斯','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1222,'bg','保加利亚文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1223,'bg_BG','保加利亚文','保加利亚','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1224,'ca','加泰罗尼亚文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1225,'ca_ES','加泰罗尼亚文','西班牙','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1226,'ca_ES_EURO','加泰罗尼亚文','西班牙Euro','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1227,'cs','捷克文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1228,'cs_CZ','捷克文','捷克共和国','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1229,'da','丹麦文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1230,'de','德文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1231,'de_AT_EURO','德文','奥地利Euro','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1232,'de_DE_EURO','德文','德国Euro','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1233,'de_LU','德文','卢森堡','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1234,'de_LU_EURO','德文','卢森堡Euro','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1235,'el','希腊文','','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1236,'en_AU','英文','澳大利亚','2018-07-15 14:05:28','2018-07-15 14:05:28',0),(1845,'en_IE_EURO','英文','爱尔兰Euro','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1846,'en_NZ','英文','新西兰','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1847,'en_ZA','英文','南非','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1848,'es','西班牙文','','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1849,'es_BO','西班牙文','玻利维亚','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1850,'es_AR','西班牙文','阿根廷','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1851,'es_CL','西班牙文','智利','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1852,'es_CO','西班牙文','哥伦比亚','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1853,'es_CR','西班牙文','哥斯达黎加','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1854,'es_DO','西班牙文','多米尼加共和国','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1855,'es_EC','西班牙文','厄瓜多尔','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1856,'es_ES_EURO','西班牙文','西班牙Euro','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1857,'es_GT','西班牙文','危地马拉','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1858,'es_HN','西班牙文','洪都拉斯','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1859,'es_MX','西班牙文','墨西哥','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1860,'es_NI','西班牙文','尼加拉瓜','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1861,'et','爱沙尼亚文','','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1862,'es_PA','西班牙文','巴拿马','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1863,'es_PE','西班牙文','秘鲁','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1864,'es_PR','西班牙文','波多黎哥','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1865,'es_PY','西班牙文','巴拉圭','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1866,'es_SV','西班牙文','萨尔瓦多','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1867,'es_UY','西班牙文','乌拉圭','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1868,'es_VE','西班牙文','委内瑞拉','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1869,'et_EE','爱沙尼亚文','爱沙尼亚','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1870,'fi','芬兰文','','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1871,'fi_FI_EURO','芬兰文','芬兰Euro','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(1872,'fr','法文','','2018-07-15 14:06:51','2018-07-15 14:06:51',0),(2633,'fr_BE_EURO','法文','比利时Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2634,'fr_FR_EURO','法文','法国Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2635,'fr_LU','法文','卢森堡','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2636,'fr_LU_EURO','法文','卢森堡Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2637,'hr','克罗地亚文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2638,'hr_HR','克罗地亚文','克罗地亚','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2639,'hu','匈牙利文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2640,'hu_HU','匈牙利文','匈牙利','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2641,'is','冰岛文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2642,'is_IS','冰岛文','冰岛','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2643,'it','意大利文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2644,'it_IT_EURO','意大利文','意大利Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2645,'iw','希伯来文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2646,'iw_IL','希伯来文','以色列','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2647,'ja','日文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2648,'ko','朝鲜文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2649,'lt','立陶宛文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2650,'lt_LT','立陶宛文','立陶宛','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2651,'lv','拉托维亚文列托','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2652,'lv_LV','拉托维亚文列托','拉脱维亚','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2653,'mk','马其顿文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2654,'mk_MK','马其顿文','马其顿王国','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2655,'nl','荷兰文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2656,'nl_BE_EURO','荷兰文','比利时Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2657,'nl_NL_EURO','荷兰文','荷兰Euro','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2658,'no','挪威文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2659,'pl','波兰文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2660,'pl_PL','波兰文','波兰','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2661,'pt','葡萄牙文','','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2662,'pt_BR','葡萄牙文','巴西','2018-07-15 14:09:36','2018-07-15 14:09:36',0),(2765,'pt_PT_EURO','葡萄牙文','葡萄牙Euro','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2766,'ro','罗马尼亚文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2767,'ro_RO','罗马尼亚文','罗马尼亚','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2768,'ru','俄文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2769,'ru_RU','俄文','俄罗斯','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2770,'sh','塞波尼斯-克罗地亚文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2771,'sh_YU','塞波尼斯-克罗地亚文','南斯拉夫','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2772,'sk','斯洛伐克文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2773,'sk_SK','斯洛伐克文','斯洛伐克','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2774,'sl','斯洛文尼亚文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2775,'sl_SI','斯洛文尼亚文','斯洛文尼亚','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2776,'sq','阿尔巴尼亚文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2777,'sq_AL','阿尔巴尼亚文','阿尔巴尼亚','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2778,'sr','塞尔维亚文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2779,'sr_YU','塞尔维亚文','南斯拉夫','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2780,'sv','瑞典文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2781,'th','泰文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2782,'th_TH','泰文','泰国','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2783,'tr','土耳其文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2784,'uk','乌克兰文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2785,'uk_UA','乌克兰文','乌克兰','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2786,'zh','中文','','2018-07-15 14:10:39','2018-07-15 14:10:39',0),(2787,'zh_HK','中文','香港','2018-07-15 14:10:39','2018-07-15 14:10:39',0);

UNLOCK TABLES;