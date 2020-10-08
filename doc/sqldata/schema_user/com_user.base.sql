#mysql:
CREATE DATABASE IF NOT EXISTS com_user DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_bin;

DROP USER 'com_user'@'%';
CREATE USER 'com_user'@'%' IDENTIFIED BY 'joven_com123$';

# GRANT privileges ON com_user.* TO 'com_user'@'%';
#或者
revoke ALL PRIVILEGES on com_user.* from 'com_user'@'%';
GRANT ALL privileges ON com_user.* TO 'com_user'@'%';

# grant all privileges on 库名.表名 to '用户名'@'IP地址' identified by '密码' with grant option;
flush privileges;

