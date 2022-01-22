<a href="#" onclick="refreshContent('angular')">返回</a>

# Angular 常见的问题

angularJs的四大特性

	AngularJS核心特性1---前端MVC
		Model:数据模型,其实就是angular变量($scope.XX,$rootScope.XX);
		View:视图层,Html+Directive(指令);
		Controller:业务逻辑和控制逻辑,就是function,数据的增删改查;
		
	AngularJS核心特性2---模块化
		html
		<div ng-app="myApp"></div><!--myApp是模块名，ng-app相当于c中的main函数，程序加载到ng-app时就知道后续的代码交由angular来管理了，
		因此在任一个单页angularjs 应用中只能出现一个ng-app-->

		js
		var myApp = angular.module('myApp', []);//定义一个模块

	AngularJS核心特性3---指令系统
		html
			<html ng-app="MyModule">
			<body>
				<hello></hello><--自定义指令-->
			</body>
			</html>
		js
			var myModule=angular.module("MyModule",[]);
			myModule.directive("hello",function(){//定义指令
				return {
				   restrict:'E',
				   template:'<div>Hello world</div>',
				   replace:true
				}
			});

	AngularJS核心特性4---双向数据绑定
		视图与数据是对应的，当视图发生变化、数据模型也立即发生变化；数据模型发生变化，视图自动更新
		<html ng-app>
		<head>
			<meta charset="UTF-8">
			<title></title>
		</head>
		<body>
			<input ng-model="greeting.text" /><!--ng-model双向数据绑定-->
			<p>{{greeting.text}},AngularJS</p>
		</body>
		<script src="../js/angular.min.js"></script>
		</html>



Angular与react和vue的简单对比

	与react对比：
		速度：react更新dom的次数少，并且更新的是虚拟dom，速度非常快。angular采用了一种新的变更检测算法，可以说与react不相上下。
		FLUX架构：es6语法的支持，数据的单向更新等，angular都以支持。
		服务器端渲染：单页应用的缺陷是对搜索引擎有很大的限制。
		
		react是一款UI组件，通常需要和其他框架组合使用，并不适合单独作为一个完整的框架。第三方组件也不如angular多。
		而angular满足上述所有优点

	与vue的对比：
		vue的优点：
			简单：国内大牛开发，中文文档，入手简单快速
			灵活：构建灵活
			性能：用了类似于react的虚拟dom，处理很快，性能很好。
		vue缺点：
			个人主导
			只关注web：angular可以开发web和客户端应用
			服务器端渲染：vue只能由第三方插件实现，angular由官方提供的服务段渲染提供支持，可以解决前端框架无法解决的一些痛点。



angularjs缺点：

	1。本身的脏值检查机制，当页面数据发生变化时，就会触发检查机制，当页面绑定的数据越来越多时，
		就会造成程式不断的去触发脏值检查机制，程序的相应就会越来越慢
	2.路由，子路由不可嵌套，提供了一个第三方解决方式uirouter,但是使用起来非常不稳定。
	3.作用域$scope作用域的限制，使得很多原生的事件不能使用。比如click事件，必须使用指令来实现
	4.表单校验的时候必须写一个指令来提示错误信息，很麻烦		


			
Angular和AngularJS之间的区别是什么？

	1、AngularJS是用JavaScript编写的；而Angular用Microsoft的TypeScript语言编写，是ECMAScript 6（ES6）的超集。
		所以angularJS和angular从设定之初就是不一样的；
	2、AngularJS在设计之初主要是针对pc端的，对移动端支持较少；而Angular提供移动支持。
	3、AngularJS使用$ routeprovider.when()进行路由配置；而Angular使用@Route Config {(...)}进行路由配置。
	4、AngularJS不使用依赖注入；而Angular使用具有单向基于树的变化检测的分层依赖注入系统。
	5、AngularJS的核心概念是$scope，但是angular中没有$scope。			


	
angularjs和vuejs的区别

	1.vue仅仅是mvvm中的view层，只是一个如jquery般的工具库，而不是框架，而angular是mvvm框架。
	2.vue的双向邦定是基于ES5 的 getter/setter来实现的，而angular而是由自己实现一套模版编译规则，需要进行所谓的“脏”检查，vue则不需要。
		因此，vue在性能上更高效，但是代价是对于ie9以下的浏览器无法支持。
	3.在 vue 中指令和组件分得更清晰。指令只封装 DOM 操作，而组件代表一个自给自足的独立单元 —— 有自己的视图和数据逻辑。
		在 angular 中两者有不少相混的地方。
	4.在 API 与设计两方上 vue比 angular 简单得多，因此你可以快速地掌握它的全部特性并投入开发。
	5.vue是一个更加灵活开放的解决方案。它允许你以希望的方式组织应用程序，而不是在任何时候都必须遵循 angular制定的规则，
		这让 vue 能适用于各种项目。
	6.angular用的指令是ng-前缀的，而vue是v-，风格其实一样的，数据绑定的方式也是一样的两个{}



AngularJS

	1、MVVM（Model）(View)(View-model)；
	2、模块化（Module）控制器（Contoller）依赖注入；
	3、双向数据绑定：界面的操作能实时反映到数据，数据的变更能实时展现到界面；
	4、指令(ng-click ng-model ng-href ng-src ng-if...)；
	5、服务Service($compile $filter $interval $timeout $http...)。



ng-show/ng-hide 与 ng-if的区别？

	我们都知道ng-show/ng-hide实际上是通过display来进行隐藏和显示的。而ng-if实际上控制dom节点的增删除来实现的。
	因此如果我们是根据不同的条件来进行dom节点的加载的话，那么ng-if的性能好过ng-show.


	
$rootScrope以及和$scope的区别？

	$rootScrope 页面所有$scope的父节点
	step1:Angular解析ng-app然后在内存中创建$rootScope。
	step2:angular回继续解析，找到{{}}表达式，并解析成变量。
	step3:接着会解析带有ng-controller的div然后指向到某个controller函数。这个时候在这个controller函数变成一个$scope对象实例



如何取消 $timeout, 以及停止一个$watch()?

	停止 $timeout我们可以用cancel：
		var customTimeout = $timeout(function () {  
		  // your code
		}, 1000);
		$timeout.cancel(customTimeout);
		
	停掉一个$watch：
		// .$watch() 会返回一个停止注册的函数
		function that we store to a variable  
		var deregisterWatchFn = $rootScope.$watch(‘someGloballyAvailableProperty’, function (newVal) {  
		  if (newVal) {
			// we invoke that deregistration function, to disable the watch
			deregisterWatchFn();
			...
		  }
		});



自定义指令
	
	angular.module('docsIsolationExample', []).controller('Controller', ['$scope', function($scope) {
	  $scope.alertName = function() {
		  alert('directive scope &');
	  }
	}]).directive('myCustomer', function() {
	  return {
		restrict: 'E',
		scope: {
		  clickHandle: '&'
		},
		template: '<button ng-click="testClick()">Click Me</button>',
		controller: function($scope) {
	 
		  $scope.testClick = function() {
			$scope.clickHandle();
	 
		  }  
		}
	  };
	});
	
	<div ng-app="docsIsolationExample">  
		<div ng-controller="Controller">  
		  <my-customer click-handle="alertName()"></my-customer>
		</div>  
	</div>
		
	restrict中可以分别设置:

	A匹配属性
	E匹配标签
	C匹配class
	M 匹配注释
	当然你可以设置多个值比如AEC,进行多个匹配。

	在scope中，@,=,&在进行值绑定时分别表示

	@获取一个设置的字符串，它可以自己设置的也可以使用{{yourModel}}进行绑定的;
	= 双向绑定，绑定scope上的一些属性；
	& 用于执行父级scope上的一些表达式，常见我们设置一些需要执行的函数



ng-repeat迭代数组的时候，如果数组中有相同值，会有什么问题，如何解决？

	会提示 Duplicates in a repeater are not allowed. 
	
	加 track by $index 可解决。当然，也可以 trace by 任何一个普通的值，
	只要能唯一性标识数组中的每一项即可（建立 dom 和数据之间的关联）。


	
ng-click 中写的表达式，能使用 JS 原生对象上的方法吗？

	不止是 ng-click 中的表达式，只要是在页面中，都不能直接调用原生的 JS 方法，因为这些并不存在于与页面对应的 Controller 的 $scope 中。
	举个栗子：
	<p>{{parseInt(55.66)}}<p>
	会发现，什么也没有显示。
	但如果在 $scope 中添加了这个函数：

	$scope.parseInt = function(x){
	  returnparseInt(x);
	}
	这样自然是没什么问题了。


	
angul 中的过滤器  filter

	自定义 filter
		// 形式
		app.filter('过滤器名称',function(){
		  returnfunction(需要过滤的对象,过滤器参数1,过滤器参数2,...){
			//...做一些事情
			return处理后的对象;
		  }
		});
		// 栗子
		app.filter('timesFilter', function(){
			return function(item, times){
				var result = '';
				for(var i = 0; i < times; i++){
					result += item;
				}
				return result;
			}
		})
	ng 内置的 filter 有九种：
		date（日期）
		currency（货币）
		limitTo（限制数组或字符串长度）
		orderBy（排序）
		lowercase（小写）
		uppercase（大写）
		number（格式化数字，加上千位分隔符，并接收参数限定小数点位数）
		filter（处理一个数组，过滤出含有某个子串的元素）
		json（格式化 json 对象）



angular 的数据绑定采用什么机制？详述原理

	脏检查机制。
		双向数据绑定是 AngularJS 的核心机制之一。当 view 中有任何数据变化时，会更新到 model ，
		当 model 中数据有变化时，view 也会同步更新，显然，这需要一个监控。
		
		原理就是，Angular 在 scope 模型上设置了一个 监听队列，用来监听数据变化并更新 view 。
		每次绑定一个东西到 view 上时 AngularJS 就会往 $watch 队列里插入一条 $watch ，用来检测它监视的 model 里是否有变化的东西。
		当浏览器接收到可以被 angular context 处理的事件时， $digest 循环就会触发，遍历所有的 $watch ，最后更新 dom	
		
		
		
前端主流框架angularJS、vueJS、react的区别	
	
	一、Vue.js：
		其实Vue.js不是一个框架，因为它只聚焦视图层，是一个构建数据驱动的Web界面的库。
		Vue.js通过简单的API（应用程序编程接口）提供高效的数据绑定和灵活的组件系统。
		Vue.js的特性如下：
		　　 1.轻量级的框架
		　　 2.双向数据绑定
		　　 3.指令
		　　 4.插件化
		优点 1. 简单：官方文档很清晰，比 Angular 简单易学。
	　　　　 2. 快速：异步批处理方式更新 DOM。
	　　　　 3. 组合：用解耦的、可复用的组件组合你的应用程序。
	　　　　 4. 紧凑：~18kb min+gzip，且无依赖。
	　　　　 5. 强大：表达式 & 无需声明依赖的可推导属性 (computed properties)。
	　　　　 6. 对模块友好：可以通过 NPM、Bower 或 Duo 安装，不强迫你所有的代码都遵循 Angular 的各种规定，使用场景更加灵活。
		缺点：1. 新生儿：Vue.js是一个新的项目，没有angular那么成熟。
	　　　　　2. 影响度不是很大：google了一下，有关于Vue.js多样性或者说丰富性少于其他一些有名的库。
	　　　　　3. 不支持IE8：

	二、angularJS：
		　　angularJS是一款优秀的前端JS框架，已经被用于Google的多款产品当中。
		　　angularJS的特性如下：
		　　　　1.良好的应用程序结构
		　　　　2.双向数据绑定
		　　　　3.指令
		　　　　4.HTML模板
		　　　　5.可嵌入、注入和测试
		优点： 1. 模板功能强大丰富，自带了极其丰富的angular指令。
		　　　 2. 是一个比较完善的前端框架，包含服务，模板，数据双向绑定，模块化，路由，过滤器，依赖注入等所有功能；
		　　　 3. 自定义指令，自定义指令后可以在项目中多次使用。
		　　　 4. ng模块化比较大胆的引入了Java的一些东西（依赖注入），能够很容易的写出可复用的代码，对于敏捷开发的团队来说非常有帮助。
		　　　 5. angularjs是互联网巨人谷歌开发，这也意味着他有一个坚实的基础和社区支持。
		缺点： 1. angular 入门很容易 但深入后概念很多, 学习中较难理解.
		　　   2. 文档例子非常少, 官方的文档基本只写了api, 一个例子都没有, 很多时候具体怎么用都是google来的, 或直接问misko,angular的作者.
		　　　 3. 对IE6/7 兼容不算特别好, 就是可以用jQuery自己手写代码解决一些.
		　　   4. 指令的应用的最佳实践教程少, angular其实很灵活, 如果不看一些作者的使用原则,很容易写出 四不像的代码, 
					例如js中还是像jQuery的思想有很多dom操作.
		　　　 5. DI 依赖注入 如果代码压缩需要显示声明.

	三、React：
		React主要用于构建UI。你可以在React里传递多种类型的参数，如声明代码，帮助你渲染出UI、也可以是静态的HTML DOM元素、
			也可以传递动态变量、甚至是可交互的应用组件。
		React特性如下：　
		　　　　1.声明式设计：React采用声明范式，可以轻松描述应用。
		　　　　2.高效：React通过对DOM的模拟，最大限度地减少与DOM的交互。
		　　　　3.灵活：React可以与已知的库或框架很好地配合。

		优点： 1. 速度快：在UI渲染过程中，React通过在虚拟DOM中的微操作来实现对实际DOM的局部更新。
	　　　　　 2. 跨浏览器兼容：虚拟DOM帮助我们解决了跨浏览器问题，它为我们提供了标准化的API，甚至在IE8中都是没问题的。
	　　　　　 3. 模块化：为你程序编写独立的模块化UI组件，这样当某个或某些组件出现问题是，可以方便地进行隔离。
	　　　　 　4. 单向数据流：Flux是一个用于在JavaScript应用中创建单向数据层的架构，它随着React视图库的开发而被Facebook概念化。
	　　　　　 5. 同构、纯粹的javascript：因为搜索引擎的爬虫程序依赖的是服务端响应而不是JavaScript的执行，
					预渲染你的应用有助于搜索引擎优化。
	　　　　　 6. 兼容性好：比如使用RequireJS来加载和打包，而Browserify和Webpack适用于构建大型应用。
					它们使得那些艰难的任务不再让人望而生畏。

		缺点： 1. React本身只是一个V而已，并不是一个完整的框架，所以如果是大型项目想要一套完整的框架的话，
				基本都需要加上ReactRouter和Flux才能写大型应用。

	链接：https://www.jianshu.com/p/e400c3339711
	


什么是MVC、MVVM？

	MVC:Model-View-Controller
		MVC是一种表现模式，是一种软件架构模式。其中有几个重要的概念：
		M，Model， 引用系统数据，管理系统功能并通知View更改用户操作。
		V，View，就是用户接口，用于显示数据。
		C，Controller ，将用户操作映射到Model，并操作视图。
		对MVC而言，分离是最大的优点，尤其是Model将不依赖于Controller和View，对于隔离应用、进行UI测试打下很好的架构级支持。
	MVVM:Model-View-ViewModel
		Model就是我们常说的数据模型，用于数据的构造，数据驱动， 主要提供基础实体的属性以及每个属性的验证逻辑.
		View主要用于界面呈现，与用户输入设备进行交互
		ViewModel是MVVM架构中最重要的部分，ViewModel中包含属性，命令，方法，事件，属性验证等逻辑，用于逻辑实现，负责View与Model之间的通信

	链接：https://www.jianshu.com/p/af9f5de988be
	
	
	
什么是ng-app, ng-init和ng-model?

	ng-app:初始化应用程序。
	ng-model:将HTML控件绑定到应用程序数据。
	ng-Controller:将控制器类附加到视图。
	ng-repeat:绑定重复的数据HTML元素。它就像一个for循环。
	ng-if:用条件绑定HTML元素。
	ng-show:用于显示HTML元素。
	ng-hide:用于隐藏HTML元素。
	ng-class:用于分配CSS类。
	ng-src:用于传递URL图像等。	
	
	
	
AngularJS可以在一个页面中有多个ng-app指令吗?

	不。每个HTML文档只能自动引导一个AngularJS应用程序。在文档中找到的第一个ngApp将被用来定义根元素来作为一个应用程序自动引导。
	如果另一个ng-app指令已经被放置，它将不会被AngularJS处理，我们将需要手动引导第二个应用程序，而不是使用第二个ng-app指令	
	
	
	
$apply()和 $digest()的区别

	安全性：$apply()可以接收一个参数作为function()，这个 function 会被包装到一个 try … catch 块中，所以一旦有异常发生，
	该异常会被 $exceptionHandler service 处理。

	$apply会使ng进入 $digest cycle , 并从$rootScope开始遍历(深度优先)检查数据变更。
	$digest仅会检查该scope和它的子scope，当你确定当前操作仅影响它们时，用$digest可以稍微提升性能。



angular的数据绑定采用什么机制？

	angular进行双向数据绑定主要是他的脏检查机制。只负责对发生于AngularJS上下文环境中的变更会做出自动地响应，
	在里面触发进入angular的digest流程，进入$digest cycle

	1）当你往UI界面上绑定数据时，angular就会把它加入到$watch队列
	2）当你绑定的事件触发时，就会触发$apply，apply会调用$rootScope.$digest(),进入angular context,触发digest循环
	3）当$digest循环开始后，它会检查watcher队列中的每个watcher。这些watchers会检查当前的值和之前的值是否一样，有没有发生改变，
	如果发生了改变，则调用回调函数，将新的值更新到界面上。假设你将ng-click指令关联到了一个button上，并传入了一个function名到ng-click上。
	当该button被点击时，AngularJS会将此function包装到一个wrapping function中，然后传入到$scope.$apply()。
	因此，你的function会正常被执行，修改models(如果需要的话)，此时一轮$digest循环也会被触发，用来确保view也会被更新。
	$digest循环不会只运行一次。在当前的一次循环结束后，它会再执行一次循环用来检查是否有models发生了变化。
	这就是脏检查(Dirty Checking)，它用来处理在listener函数被执行时可能引起的model变化。
	因此，$digest循环会持续运行直到model不再发生变化，或者$digest循环的次数达到了10次。因此，尽可能地不要在listener函数中修改model。 

	Note: $digest循环最少也会运行两次，即使在listener函数中并没有改变任何model。正如上面讨论的那样，它会多运行一次来确保models没有变化。



AngularJS中的$watch方法

	$scope.$watch(
		function(scope) { return scope.someValue; },
		function(newValue, oldValue, scope) { // listener code defined here }
	);
	$watch方法的第一个参数是一个函数，它通常被称为watch函数，它的返回值声明需要监听的变量；第二个参数是listener，也就是回调函数，
		在变量发生改变的时候会被调用。
	$digest函数中，会逐个检查$watch方法中注册的watch函数，如果该函数返回的值和上一次检查中返回的值不一样的话，
		就会触发对应的listener函数。拿{{ }}表达式作为例子，该表达式编译得到的listener的行为就是将后台的最新变量给同步到前端。这么一来，就完成了一个简单的数据绑定。
	$apply方法。这个方法能够触发$digest方法。$digest方法的执行就标志着一轮Digest Cycle的开始。
	
	Note: $scope.$apply()会自动地调用$rootScope.$digest()。$apply()方法有两种形式。第一种会接受一个function作为参数，
		执行该function并且触发一轮$digest循环。第二种会不接受任何参数，只是触发一轮$digest循环。
	angularjs只负责对发生于AngularJS上下文环境中的变更会做出自动地响应(即，在$apply()方法中发生的对于models的更改)。
		AngularJS的built-in指令就是这样做的，所以任何的model变更都会被反映到view中。
		但是，如果你在AngularJS上下文之外的任何地方修改了model，那么你就需要通过手动调用$apply()来通知AngularJS。
		这就像告诉AngularJS，你修改了一些models，希望AngularJS帮你触发watchers来做出正确的响应。
	比如，如果你使用了JavaScript中的setTimeout()来更新一个scope model，那么AngularJS就没有办法知道你更改了什么。
		这种情况下，调用$apply()就是你的责任了，通过调用它来触发一轮$digest循环。
		类似地，如果你有一个指令用来设置一个DOM事件listener并且在该listener中修改了一些models，
		那么你也需要通过手动调用$apply()来确保变更会被正确的反映到view中。

	需要记住的是你总是应该使用接受一个function作为参数的$apply()方法。这是因为当你传入一个function到$apply()中的时候，
		这个function会被包装到一个try…catch块中，所以一旦有异常发生，该异常会被$exceptionHandler service处理。






	