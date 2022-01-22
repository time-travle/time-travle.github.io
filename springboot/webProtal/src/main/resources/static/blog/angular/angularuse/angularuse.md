<a href="#" onclick="refreshContent('angular')">返回</a>

# Angular 使用
- https://www.xttblog.com/?p=848
- https://www.xttblog.com/?p=850
- https://www.xttblog.com/?p=852
- https://www.xttblog.com/?p=854
- https://www.xttblog.com/?p=856
- https://www.xttblog.com/?p=858

============================AngularJS begin============================

AngularJS 是一个 JavaScript 框架。
    
    它可通过 <script> 标签添加到 HTML 页面。

AngularJS 是专门为应用程序设计的 HTML
AngularJS 通过 指令 扩展了 HTML，且通过 表达式 绑定数据到 HTML

    引入js库
    <script src="http://apps.bdimg.com/libs/angular.js/1.3.9/angular.min.js"></script>

我们建议把脚本放在 <body> 元素的底部。 
    
    这会提高网页加载速度，因为 HTML 加载不受制于脚本加载
 
AngularJS 通过 ng-directives 扩展了 HTML。

            ng-app 指令定义一个 AngularJS 应用程序。
            ng-model 指令把元素值（比如输入域的值）绑定到应用程序。
            ng-bind 指令把应用程序数据绑定到 HTML 视图。
            ng-init 指令初始化 AngularJS 应用程序变量
        
            当网页加载完毕，AngularJS 自动开启。
            ng-app 指令告诉 AngularJS，<div> 元素是 AngularJS 应用程序 的"所有者"。
            ng-model 指令把输入域的值绑定到应用程序变量 name。
            ng-bind 指令把应用程序变量 name 绑定到某个段落的 innerHTML。
        
            如果您移除了 ng-app 指令，HTML 将直接把表达式显示出来，不会去计算表达式的结果。
            HTML5 允许扩展的（自制的）属性，以 data- 开头。
            AngularJS 属性以 ng- 开头，但是您可以使用 data-ng- 来让网页对 HTML5 有效。
            AngularJS 模块（Module） 定义了 AngularJS 应用。
            AngularJS 控制器（Controller） 用于控制 AngularJS 应用。
            ng-app指令定义了应用, ng-controller 定义了控制器。


AngularJS 实例

            <div ng-app="myApp" ng-controller="myCtrl">
             名: <input type="text" ng-model="firstName"><br>
             姓: <input type="text" ng-model="lastName"><br>
                <br>
            
             姓名: {{firstName + " " + lastName}}
            </div>
            <script>
                // AngularJS 模块定义应用
                var app = angular.module('myApp', []);
                // AngularJS 控制器控制应用
                app.controller('myCtrl', function($scope) {
                    $scope.firstName= "John";
                    $scope.lastName= "Doe";
                });
            
            </script>





<html> 元素包含了 AngularJS 应用 (ng-app=)。

<div> 元素定义了 AngularJS 控制器的作用域 (ng-controller=)。

在一个应用可以由很多控制器。

应用文件(my...App.js) 定义了应用模型代码。

一个或多个控制器文件 (my...Ctrl.js) 定义了控制器代码



============================AngularJS end!!============================

angularjs和vuejs的区别

            在 Angular 1 中，当 watcher 越来越多时会变得越来越慢，因为作用域内的每一次变化，所有 watcher 都要重新计算。
            并且，如果一些 watcher 触发另一个更新，脏检查循环（digest cycle）可能要运行多次。Angular 用户常常要使用深奥的技术，
            以解决脏检查循环的问题。有时没有简单的办法来优化有大量 watcher 的作用域。
            
            Vue 则根本没有这个问题，因为它使用基于依赖追踪的观察系统并且异步队列更新，所有的数据变化都是独立触发，除非它们之间有明确的依赖关系
            
            
	
	
AngularJS 初始化加载流程

            1、浏览器载入HTML，然后把它解析成DOM。
            2、浏览器载入angular.js脚本。
            3、AngularJS等到DOMContentLoaded事件触发。
            4、AngularJS寻找ng-app指令，这个指令指示了应用的边界。
            5、使用ng-app中指定的模块来配置注入器($injector)。
            6、注入器($injector)是用来创建“编译服务($compile service)”和“根作用域($rootScope)”的。
            7、编译服务($compile service)是用来编译DOM并把它链接到根作用域($rootScope)的。
            8、ng-init指令将“World”赋给作用域里的name这个变量。
            9、通过{{name}}的替换，整个表达式变成了“Hello World”。	
	
AngularJS 事件广播与接收　

            发送消息： $scope.$emit(name, data) 或者 $scope.$broadcast(name, data);
            接收消息： $scope.on(name,function(event,data){ });
            区别： $emit 广播给父controller   $broadcast 广播给子controller
        
            broadcast 是从发送者向他的子scope广播一个事件。
            这里就是ParentController发送， ParentController 和 ChildController 会接受到, 而MainController是不会收到的
        
            $emit 广播给父controller，父controller 是可以收到消息
            $on 有两个参数function(event,msg)  第一个参数是事件对象，第二个参数是接收到消息信息	
        
                angular.module('onBroadcastEvent', ['ng'])
                .controller('MainController', function($scope) {
                    $scope.$on('To-MainController', function(event,msg) {
                     console.log('MainController received:' + msg);
                    });
                })
                .controller('ParentController', function($scope) {
                    $scope.click = function (msg) {
                     $scope.$emit('To-MainController',msg + ',from ParentController to MainController');
                     $scope.$broadcast('To-ChildController', msg + ',from ParentController to ChildController');
                     $scope.$broadcast('To-BrotherController', msg + ',from ParentController to BrotherController');
                    }
                })
                .controller('ChildController', function($scope){
                    $scope.$on('To-ChildController', function(event,msg) {
                     console.log('ChildController received:' + msg);
                    });
                })
                .controller('BrotherController', function($scope){
                    $scope.$on('To-BrotherController', function(event, msg) {
                     console.log('BrotherController received:' + msg);
                    });
                });