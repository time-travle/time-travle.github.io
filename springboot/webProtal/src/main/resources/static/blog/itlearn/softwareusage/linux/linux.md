<p>
<a href="#" onclick="showITLearnPage('softwareusage')">返回</a>&emsp;&emsp;&emsp;
</p>

---

<p>
<a href="#" onclick="refreshLinuxContent('virtualmachine')">虚拟机使用 </a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshLinuxContent('linuxsys')">Linux 系统说明</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshLinuxContent('linuxorder')">Linux 命令</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshLinuxContent('installsoftware')">Linux 安装软件</a>&emsp;&emsp;&emsp;
</p>

---

查看文件权限配置 文件权限简介：'r' 代表可读（4），'w' 代表可写（2），'x' 代表执行权限（1），括号内代表"8421法"

## 文件权限信息示例：-rwxrw-r--

- 第一位：'-'就代表是文件，'d'代表是文件夹
- 第一组三位：拥有者的权限
- 第二组三位：拥有者所在的组，组员的权限
- 第三组三位：代表的是其他用户的权限

         权限范围：
         u ：目录或者文件的当前的用户
         g ：目录或者文件的当前的群组
         o ：除了目录或者文件的当前用户或群组之外的用户或者群组
         a ：所有的用户及群组

         权限代号：
         r ：读权限，用数字4表示
         w ：写权限，用数字2表示
         x ：执行权限，用数字1表示
         - ：删除权限，用数字0表示
         s ：特殊权限

普通授权 chmod +x a.txt    
8421法 chmod 777 a.txt //1+2+4=7，"7"说明授予所有权限

### chown命令用于改变文件的所有权

    用法：chown ...[OPTION] [OWNER] [:[GROUP]] FILE... 
    常用参数：
        不带参数 改变单个或多个文件的属主和属组。 
        -r 改变一个目录及其下所有文件（和子目录）的所有权设置。

### chgrp 用于单独设置文件的属组

    用法：chgrp ...[GROUP] FILE...
    
    改变文件权限chmod
        chmod用于改变一个的权限。它以“用户组 +/-权限”的表达方式来增加/删除相应的权限。具体来说，用户组包括了文件属主（u），文件属组（g），其他人（o）和所有人（a），而权限则包括读取（r,w,x）。

        用法： chmod ...[OPTION]...[FILE]...

        demo:
            chmod g+w,o+w cpplint.py
            可以发现 cpplint.py文件组和其他人的访问权限多个写的权限。
