##问题
###网页禁止复制粘贴怎么解决教程,网页禁止复制粘贴怎么办
  方法1：

        打开目标网页，选中网页的地址栏 在地址栏输入下行代码，全部输入。
            javascript：void($={});
        按下回车键(Enter)，破解完成。(回车后不会跳转网页), 注意的是，如果网页被刷新，限制会恢复，需要重新输入代码
###如何禁止 网页的左键复制功能
  方法1：

        在html.tpl.php 加入一行
        <script language="Javascript">
        document.οncοntextmenu=new Function("event.returnValue=false");
        document.onselectstart=new Function("event.returnValue=false");
        </script>
  参考：https://blog.csdn.net/for023/article/details/77452718
###禁止页面另存为
####jsp
        <body>标签加入以下代码：
        <noscript>
        <iframe src=”*.htm”></iframe>
        </noscript>

###实现禁止复制粘贴
####脚本1
        <script type="text/javascript">
            // 禁止右键菜单
            document.oncontextmenu = function(){ return false; };
            // 禁止文字选择
            document.onselectstart = function(){ return false; };
            // 禁止复制
            document.oncopy = function(){ return false; };
            // 禁止剪切
            document.oncut = function(){ return false; };
            // 禁止粘贴
            document.onpaste = function(){ return false; };
        </script>
    弊端：执行了一次就会每次都会执行了
   参考： https://www.cnblogs.com/momo798/p/6797670.html
####脚本2   
        <script type="text/javascript">
            // 禁用右键菜单、复制、选择
            $(document).bind("contextmenu copy selectstart", function() {
             return false;
            });
            // 禁用Ctrl+C和Ctrl+V（所有浏览器均支持）
            $(document).keydown(function(e) {
             if(e.ctrlKey && (e.keyCode == 65 || e.keyCode == 67)) {
               return false;
             }
            });
        </script>
    弊端：执行了一次就会每次都会执行了
  参考：https://www.jianshu.com/p/5550da3fad49