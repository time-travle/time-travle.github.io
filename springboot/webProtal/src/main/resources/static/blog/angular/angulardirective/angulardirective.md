<a href="#" onclick="refreshContent('angular')">返回</a>

# Angular 使用到的指令

指令：

    ng-app 指令定义一个 AngularJS 应用程序。 指令告诉 AngularJS，<div> 元素是 AngularJS 应用程序 的"所有者"。
    
    ng-model 指令把元素值（比如输入域的值）绑定到应用程序。 指令把输入域的值绑定到应用程序变量
    
    ng-bind 指令把应用程序数据绑定到 HTML 视图。 指令把应用程序变量 name 绑定到某个段落的 innerHTML
    
    ng-include 指令来包含 HTML 内容:
    
    ng-init 指令初始化 AngularJS 应用程序变量
    
    ng-repeat 指令会重复一个 HTML 元素
    
    ng-disabled 是否可用
    
    placeholder 展示名字


    
    AngularJS 指令            描述
    
    <html ng-app            为 <html> 元素定义一个应用(未命名) 
    <body ng-controller     为 <body> 元素定义一个控制器 
    <tr ng-repeat           循环 users 对象数组，每个 user 对象放在 <tr> 元素中。 
    <button ng-click        当点击 <button> 元素时调用函数 editUser() 
    <h3 ng-show             如果 edit = true 显示 <h3> 元素 
    <h3 ng-hide             如果 edit = true 隐藏 <h3> 元素 
    <input ng-model         为应用程序绑定 <input> 元素 
    <button ng-disabled     如果发生错误或者 ncomplete = true 禁用 <button>  
    

===========================================================

表达式：

    AngularJS 表达式写在双大括号内：{{ expression }}。
    
    AngularJS 表达式把数据绑定到 HTML，这与 ng-bind 指令有异曲同工之妙


===========================================================

事件：

    ng-click 指令定义了 AngularJS 点击事件
    
    ng-hide 指令用于设置应用部分是否可见。 
        ng-hide="true" 设置 HTML 元素不可见。
        ng-hide="false" 设置 HTML 元素可见
    
    ng-show 指令可用于设置应用中的一部分是否可见 。
        ng-show="false" 可以设置 HTML 元素 不可见。
        ng-show="true" 可以以设置 HTML 元素可见
    
    toggle() 函数用于切换变量的值（true 和 false）。
    
    ng-click
    ng-dbl-click
    ng-mousedown
    ng-mouseenter
    ng-mouseleave
    ng-mousemove
    ng-keydown
    ng-keyup
    ng-keypress
    ng-change
    
    ng-href；
    ng-src；
    ng-disabled；
    ng-checked；
    ng-readonly；
    ng-selected；
    ng-class；
    ng-style
===========================================================

表单：

    input 元素
    select 元素
    button 元素
    textarea 元素

demo:

    <div ng-app="myApp" ng-controller="formCtrl">
        <form novalidate>
            First Name:<br>
            <input type="text" ng-model="user.firstName"><br>
            Last Name:<br>
            <input type="text" ng-model="user.lastName">
             <br><br>
            <button ng-click="reset()">RESET</button>
        </form>
        
        <p>form = {{user}}</p>
        <p>master = {{master}}</p>
    </div>

    <script>
        var app = angular.module('myApp', []);
        
        app.controller('formCtrl', function($scope) {
            $scope.master = {firstName: "John", lastName: "Doe"};
            $scope.reset = function() {
                $scope.user = angular.copy($scope.master);
            };
            $scope.reset();
        });
    </script>


实例解析

    ng-app 指令定义了 AngularJS 应用。

    ng-controller 指令定义了应用控制器。

    ng-model 指令绑定了两个 input 元素到模型的 user 对象。

    formCtrl 函数设置了 master 对象的初始值，并定义了 reset() 方法。

    reset() 方法设置了 user 对象等于 master 对象。 

    ng-click 指令调用了 reset() 方法，且在点击按钮时调用。

    novalidate 属性在应用中不是必须的，但是你需要在 AngularJS 表单中使用，用于重写标准的 HTML5 验证


过滤器：

    过滤器         描述
    
    currency    格式化数字为货币格式。 
    filter      从数组项中选择一个子集。 
    lowercase   格式化字符串为小写。 
    orderBy     根据某个表达式排列数组。 
    uppercase   格式化字符串为大写 
    date        {{ today | date:'medium' }} <!-- Aug 09, 2013 12:09:02 PM -->
                {{ today | date:'short' }} <!-- 8/9/1312:09PM -->
                {{ today | date:'fullDate' }} <!-- Thursday, August 09, 2013 -->
                {{ today | date:'longDate' }} <!-- August 09, 2013 -->
                {{ today | date:'mediumDate' }}<!-- Aug 09, 2013 -->
                {{ today | date:'shortDate' }} <!-- 8/9/13 -->
                {{ today | date:'mediumTime' }}<!-- 12:09:02 PM -->
                {{ today | date:'shortTime' }} <!-- 12:09 PM -->
    


方法：

    AngularJS $watch() , $digest() and $apply()
    http://tutorials.jenkov.com/angularjs/watch-digest-apply.html

$watch方法监视Model的变化

$apply方法传播Model的变化  

    scope.apply()似乎就是一个使得bindings得到更新的普普通通的方法
    什么时候用$apply()
         还是那个问题，那我们到底什么时候需要去调用apply()方法呢？情况非常少，实际上几乎我们所有的代码都包在scope.apply()里面，
         像ng−click，controller的初始化，http的回调函数等。在这些情况下，我们不需要自己调用，实际上我们也不能自己调用，
         否则在apply()方法里面再调用apply()方法会抛出错误。如果我们需要在一个新的执行序列中运行代码时才真正需要用到它，
         而且当且仅当这个新的执行序列不是被angular JS的库的方法创建的，这个时候我们需要将代码用scope.apply()包起来
$digest 脏数据检查

    $digest循环不会只运行一次。在当前的一次循环结束后，它会再执行一次循环用来检查是否有models发生了变化。
    这就是脏检查(Dirty Checking)，它用来处理在listener函数被执行时可能引起的model变化。
    因此，$digest循环会持续运行直到model不再发生变化，或者$digest循环的次数达到了10次。
    因此，尽可能地不要在listener函数中修改model。
    
        Note: $digest循环最少也会运行两次，即使在listener函数中并没有改变任何model。正如上面讨论的那样，它会多运行一次来确保models没有变化
===========================================================

输入验证：


属性 描述

    $dirty 表单有填写记录 
    $valid 字段内容合法的 
    $invalid 字段内容是非法的 
    $pristine 表单没有填写记录 

===========================================================

API

    angular.lowercase() 转换字符串为小写 
    angular.uppercase() 转换字符串为大写 
    angular.isString() 判断给定的对象是否为字符串，如果是返回 true。  
    angular.isNumber() 判断给定的对象是否为数字，如果是返回 true。 
    angular.isArray() 如果引用的是数组返回 true 
    angular.isDate() 如果引用的是日期返回 true 
    angular.isDefined() 如果引用的已定义返回 true 
    angular.isElement() 如果引用的是 DOM 元素返回 true 
    angular.isFunction() 如果引用的是函数返回 true 
    angular.isObject() 如果引用的是对象返回 true 
    angular.isUndefined() 如果引用的未定义返回 true 
    angular.equals() 如果两个对象相等返回 true 
    angular.fromJSON() 反系列化 JSON 字符串 
    angular.toJSON() 系列化 JSON 字符串 
    angular.extends() 把一个或多个对象中的方法和属性扩展到一个目的对象中，使得这个对象拥有其他对象相同的方法和属性
                        非递归的，也就是说：如果扩展的属性中有对象，那么二者同时引用同一个对象
    angular.copy() 可以把一个空对象｛｝作为第一个对象传入


AngularJS $http

    AngularJS $http 是一个用于读取web服务器上数据的服务。
    $http.get(url) 是用于读取服务器数据的函数

demo:

        self.tableParams = new NgTableParams({}, {
          getData: function (params) {
            $http.post("rest/staff/page", $scope.req).success(function (data) {
              if (data != null && data != undefined) {
                $scope.staffs = data.data;
                params.total($scope.totalPage);
              }
            }).error(function (data) {
              $("#serverErrorModal").modal({show: true});
            });
          
            return $scope.staffs;
          }
        });
        
        示例中，getData方法要得到通过$http请求返回的值，然而在angularjs中$http永远都是异步的，也就是说return的值将一直为空；
        
        self.tableParams = new NgTableParams({'count': 10} , {
          getData: function (params) {
            var promise = $http.post("rest/staff/page", $scope.req);
            return promise.then(function (resut) {
              var response = resut.data;
              var total = response.otherData[0];
              params.total(total);
              return response.data;
            });
          }
        });
        
        直接这样return就可以实现同步获取数据了

