# Angular 知识点

<p>
<a href="#" onclick="refreshAngularContent('angularorder')">Angular 使用到的命令</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshAngularContent('angularquestion')">Angular 常见的问题</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshAngularContent('angularuse')">Angular 的使用</a>&emsp;&emsp;&emsp;
<a href="#" onclick="refreshAngularContent('angulardirective')">Angular 指令的使用</a>&emsp;&emsp;&emsp;
</p>

---


我们要是使用 AngularJS 的框架

在对应的要使用这个框架的页面时我们需要将这个 angularJS 对应的js 库， 不管是通过连接引入还是将这个版本库对应js 引进来，只要是能保证应用到就OK，

        <!DOCTYPE html>
        <html ng-app>
            <head>
                <title>Simple app</title>
                <!--网络版-->
                <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.13/angular.js"></script>
                <!--本地版-->
                <script src="location.../angular.js"></script>
            </head>
            <body>
                <input ng-model="name" type="text" placeholder="Your name">
                <h1>Hello {{ name }}</h1>
            </body>
        </html>