<a href="#" onclick="refreshContent('vue')">返回</a>

## Vue 的使用

1、新建一个实例

	<div id="app">
		{{msg}}
	</div>
	<script>
	var vm = new vue{(
		el:"app",
		data:{
			msg:"masg"
		}，
		methods:{// 函数
			function1:function(){
			
			},
			function2:function(){
			
			}
		}
		directives :{// 自定义指令
		
		}
		filters:{
			filter1:function(){
			}
		}
		computed:{//计算属性
		}
		comments: { // 注册组件
		}
		watch：{
		}
	)};
	</script>
	
2、watch方法 监控一个属性的变化 并获得变化前后的值

	<script type = "text/javascript">
		var vm = new Vue({
			el: '#app',
			data: {
				counter: 1
			}
		});
		vm.$watch('counter', function(nval, oval) {
			alert('计数器值的变化 :' + oval + ' 变为 ' + nval + '!');
		});
	</script>

3、生命周期 11种

	创建前
	beforeCreate:function(){	}
	创建
	created:function(){	}
	挂载前
	beforeMount:function(){	}
	挂载
	mounted:function(){	}
	更新前
	beforeUpdate:function(){	}
	更新
	updated:function(){	}
	激活
	activated:function(){	}
	停激活时
	deactivated:function(){	}
	销毁前
	beforeDestroy:function(){	}
	销毁
	destroyed:function(){	}
	报错
	errorCaptured:function(){	}

4、定时 触发的一个

	setTimeout(function(){
		3秒之后的时
	},3000);
	
5、指令

	v-once ：	仅加载一次 能执行一次性地插值，当数据改变时，插值处的内容不会更新
	v-html ：	渲染为html用来将html的字符串渲染为对应的html
	v-bind:属性 : 	绑定动态的属性   缩写为 ：
		v-bind:class="{red:1==2,green:1==3}" 	动态绑定对应的class
	v-if:	条件指令
	v-else-if:		和v-if 同步使用
	v-else:		和v-if 同步使用
	v-show: 	渲染之后 判断是不是展示
	v-for='one in ones':		列表渲染
		v-for 可以绑定数据到数组来渲染一个列表：
		<div id="app">
		  <ol>
			<li v-for="site in sites">
			  {{ site.name }}
			</li>
		  </ol>
		</div>
		<script>
		new Vue({
		  el: '#app',
		  data: {
			sites: [
			  { name: 'Runoob' },
			  { name: 'Google' },
			  { name: 'Taobao' }
			]
		  }
		})
		</script>
		v-for 可以通过一个对象的属性来迭代数据：
		<div id="app">
		  <ul>
			<li v-for="value in object">
			{{ value }}
			</li>
		  </ul>
		</div>
		<div id="app">
		  <ul>
			<li v-for="(value, key) in object">
			{{ key }} : {{ value }}
			</li>
		  </ul>
		</div>
		<div id="app">
		  <ul>
			<li v-for="(value, key, index) in object">
			 {{ index }}. {{ key }} : {{ value }}
			</li>
		  </ul>
		</div>
		<script>
		new Vue({
		  el: '#app',
		  data: {
			object: {
			  name: '菜鸟教程',
			  url: 'http://www.runoob.com',
			  slogan: '学的不仅是技术，更是梦想！'
			}
		  }
		})
		</script>
		
	v-on:	监听事件 缩写为 @
	v-model:  实现双向绑定
	
6、修饰符

	事件修饰符
		.stop : 当前事件触发之后立即停止不再向外传递
		.prevent
		.capture
		.self
		.once
			<!-- 阻止单击事件冒泡 -->
			<a v-on:click.stop="doThis"></a>
			<!-- 提交事件不再重载页面 -->
			<form v-on:submit.prevent="onSubmit"></form>
			<!-- 修饰符可以串联  -->
			<a v-on:click.stop.prevent="doThat"></a>
			<!-- 只有修饰符 -->
			<form v-on:submit.prevent></form>
			<!-- 添加事件侦听器时使用事件捕获模式 -->
			<div v-on:click.capture="doThis">...</div>
			<!-- 只当事件在该元素本身（而不是子元素）触发时触发回调 -->
			<div v-on:click.self="doThat">...</div>
			<!-- click 事件只能点击一次，2.1.4版本新增 -->
			<a v-on:click.once="doThis"></a>
	按键修饰符
		.enter
		.tab
		.delete (捕获 "删除" 和 "退格" 键)
		.esc
		.space
		.up
		.down
		.left
		.right
		.ctrl
		.alt
		.shift
		.meta
	表单修饰符
		.lazy 	在默认情况下， v-model 在 input 事件中同步输入框的值与数据，但你可以添加一个修饰符 lazy ，从而转变为在 change 事件中同步
		.number 如果想自动将用户的输入值转为 Number 类型（如果原值的转换结果为 NaN 则返回原值）， 
				可以添加一个修饰符 number 给 v-model 来处理输入值
		.trim	如果要自动过滤用户输入的首尾空格，可以添加 trim 修饰符到 v-model 上过滤输入
	
7、事件

	点击事件 click
	变更时事件
	鼠标滑动事件

8、过滤器filters 

	格式类似
	<!-- 在两个大括号中 -->
	{{ message | capitalize }}
	<!-- 在 v-bind 指令中 -->
	<div v-bind:id="rawId | formatId"></div>
	
9、组件 component

	组件是可以服用的vue实例 和正常的实例不同的的不能接受el属性

	创建组件
  	Vue.component("name",{
		props:{},//为组件定义属性  在模板内容中可以使用的 在组件调用处 也可以对属性进行赋值
		data:function(){
		},
		// 这个模板内容中必须有个一个对应的根节点 同时可以是使用<slot></slot> 来为组件创建插槽 为引用出提供自定义的内容
		template:"组件的模板内容",
		methods:{
			sendMassage:function(){
				this.$emit("eventName",参数);
			}
		},
		watch:{
		},
		computed:{
			// 里面的是一些方法供直接调用 为一个属性直接绑定方法
			message2: function () {
			  return this.message.split('').reverse().join('')
			},
			// computed 属性默认只有 getter ，不过在需要时也可以提供一个 setter
			myUrl: {
			  cache: false,// 为一个特殊的计算属性开关缓存支持
			  get: function () {
				return 'zhangershuai'
			  },
			  set: function (value) {

			  }
			}
		}
	})
	
	组件的注册
		1、全局注册
		Vue.component('name',{。。。。。}) 
			弊端：
				要求每个组件的名字不能重复
				字符串模板不高亮
				不支持css
				没有构建步骤，只能使用html和ES5 javascript 不能预处理 
		2、局部注册
			实例化对象时注册
			new Vue({
				components:{
					组件名：{
						template:"",
						...
					}
				}
			})
			导入模块组件注册
			<script>
			import name from url 
			
			export default {
				name :"app" ,
				components:{
					name
				}
			}
			</script>
		3、在模块中局部注册
		一般写在vue的文件中
		在使用的地方使用 import  from 导入 
	
	单文件组件：
	<template></template>--模板部分
	<script></script>--脚本部分
	<style></style>--样式部分
	
10、vue目录结构 说明

	build/---项目构建(webpack)相关代码
	config/---配置目录，包括端口号等
	node_modules/---npm 加载的项目依赖模块
	public/---发布到生产环境的目录
	src/---开发目录
		components/---目录里面放了一个组件文件，可以不用
		assets/---放置一些图片，如logo等
		App.vue---入口文件
		main.js---项目的核心文件
	static/	---静态资源目录，如图片、字体
	package.json---项目配置文件。
	package-lock.json
	index.html	---首页入口文件


-----

语法：

1、

2、模板语法

3、条件语句

	v-if
	v-else-if 
	v-else
	
	v-show

4、循环语句

	v-for 	这个既可以遍历 对象集合 也可以遍历的对象的属性

5、计算属性 是不是进行变化 是基于缓存的数据是不是发生了变化

	实例化对象时 使用计算属性关键字 computed (默认值只有get的方法，若是set需要则可以定义)
	
6、监听属性

	watch
	
7、样式绑定  支持对象 数组  三目运算

	v-bind:class=""
	直挂样式
	<div v-bind:class="{ active: isActive }"></div>
	<div v-bind:class="classObject"></div>
	<div class="static" v-bind:class="{ active: isActive, 'text-danger': hasError }"></div>
	<div v-bind:class="[activeClass, errorClass]"></div>
	<div v-bind:class="[errorClass ,isActive ? activeClass : '']"></div>
	内联样式
    <div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }">菜鸟教程</div>

8、事件处理

	监听事件 v-on
	click 点击事件
	keyup 键盘事件
	
	通过修饰符 响应事件
	
9、表单

	通过 v-model 实现数据的双向绑定
	
10、组件   https://cn.vuejs.org/v2/guide/components-dynamic-async.html

	组件命名 两种格式  kebab-case    PascalCase
	
	
	全局组件注册  所有实例都能用全局组件。
		Vue.component(tagName, options)

	局部注册 关键字components
		在实例中进行注册
		<div id="app">
			<runoob></runoob>
		</div>
		 
		<script>
		var Child = {
		  template: '<h1>自定义组件!</h1>'
		}
		 
		// 创建根实例
		new Vue({
		  el: '#app',
		  components: {
			// <runoob> 将只在父模板可用
			'runoob': Child
		  }
		})
		</script>
	组件传值 关键字 props
		是子组件用来接受父组件传递过来的数据的一个自定义属性
		父组件的数据需要通过 props 把数据传给子组件，子组件需要显式地用 props 选项声明 "prop"：
		
		父传子 props
		子传父 
			子组件发事件 	使用 $emit(eventName) 触发事件 
			父组件响应事件	使用 $on(eventName) 监听事件
	住件中的data不是一个对象而是一个方法
		若不是方法 则 多个应用时 会出现重复的情况
		若是方法 则不管多个引用时 现象都是不各自独立的

		
11、自定义指令 directive

	全局指令注册
		<div id="app">
			<div v-runoob="{ color: 'green', text: '菜鸟教程!' }"></div>
		</div>
		 
		<script>
		Vue.directive('runoob', function (el, binding) {
			// 简写方式设置文本及背景颜色
			el.innerHTML = binding.value.text
			el.style.backgroundColor = binding.value.color
		})
		new Vue({
		  el: '#app'
		})
		</script>
	局部指令注册
		// 创建根实例
		new Vue({
		  el: '#app',
		  directives: {
			// 注册一个局部的自定义指令 v-focus
			focus: {
			  // 指令的定义
			  inserted: function (el) {
				// 聚焦元素
				el.focus()
			  }
			}
		  }
		})
	
12、路由

	<router-link> 是一个组件，该组件用于设置一个导航链接，切换不同 HTML 内容。 to 属性为目标地址， 即要显示的内容
	<router-link> 相关属性
		to
			表示目标路由的链接。 当被点击后，内部会立刻把 to 的值传到 router.push()
		replace
			设置 replace 属性的话，当点击时，会调用 router.replace() 而不是 router.push()，导航后不会留下 history 记录	
		append
			设置 append 属性后，则在当前 (相对) 路径前添加其路径。
			例如，我们从 /a 导航到一个相对路径 b，如果没有配置 append，则路径为 /b，如果配了，则为 /a/b	
		tag
			有时候想要 <router-link> 渲染成某种标签，例如 <li>
		active-class
			设置 链接激活时使用的 CSS 类名
		exact-active-class
			配置当链接被精确匹配的时候应该激活的 class。
		event
			声明可以用来触发导航的事件。可以是一个字符串或是一个包含字符串的数组
	demo:
		<html>
		<head>
		<meta charset="utf-8">
		<title>Vue 测试实例 - 菜鸟教程(runoob.com)</title>
		<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
		<script src="https://cdn.staticfile.org/vue-router/2.7.0/vue-router.min.js"></script>
		</head>
		<body>
		<div id="app">
		  <h1>Hello App!</h1>
		  <p>
			<!-- 使用 router-link 组件来导航. -->
			<!-- 通过传入 `to` 属性指定链接. -->
			<!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
			<router-link to="/foo">Go to Foo</router-link>
			<router-link to="/bar">Go to Bar</router-link>
		  </p>
		  <!-- 路由出口 -->
		  <!-- 路由匹配到的组件将渲染在这里 -->
		  <router-view></router-view>
		</div>
		<script>
		// 0. 如果使用模块化机制编程，導入Vue和VueRouter，要调用 Vue.use(VueRouter)
		// 1. 定义（路由）组件。
		// 可以从其他文件 import 进来
		const Foo = { template: '<div>foo</div>' }
		const Bar = { template: '<div>bar</div>' }
		// 2. 定义路由
		// 每个路由应该映射一个组件。 其中"component" 可以是
		// 通过 Vue.extend() 创建的组件构造器，
		// 或者，只是一个组件配置对象。
		// 我们晚点再讨论嵌套路由。
		const routes = [
		  { path: '/foo', component: Foo },
		  { path: '/bar', component: Bar }
		]
		// 3. 创建 router 实例，然后传 `routes` 配置
		// 你还可以传别的配置参数, 不过先这么简单着吧。
		const router = new VueRouter({
		  routes // （缩写）相当于 routes: routes
		})
		// 4. 创建和挂载根实例。
		// 记得要通过 router 配置参数注入路由，
		// 从而让整个应用都有路由功能
		const app = new Vue({
		  router
		}).$mount('#app')
		// 现在，应用已经启动了！
		</script>
		</body>
		
13、过渡  transition 

	过渡其实就是一个淡入淡出的效果。Vue在元素显示与隐藏的过渡中，提供了 6 个 class 来切换：
		v-enter：
			定义进入过渡的开始状态。在元素被插入之前生效，在元素被插入之后的下一帧移除。
		v-enter-active：
			定义进入过渡生效时的状态。在整个进入过渡的阶段中应用，在元素被插入之前生效，在过渡/动画完成之后移除。
			这个类可以被用来定义进入过渡的过程时间，延迟和曲线函数。
		v-enter-to: 
			2.1.8版及以上 定义进入过渡的结束状态。在元素被插入之后下一帧生效 (与此同时 v-enter 被移除)，在过渡/动画完成之后移除。
		v-leave: 
			定义离开过渡的开始状态。在离开过渡被触发时立刻生效，下一帧被移除。
		v-leave-active：
			定义离开过渡生效时的状态。在整个离开过渡的阶段中应用，在离开过渡被触发时立刻生效，在过渡/动画完成之后移除。
			这个类可以被用来定义离开过渡的过程时间，延迟和曲线函数。
		v-leave-to: 
			2.1.8版及以上 定义离开过渡的结束状态。在离开过渡被触发之后下一帧生效 (与此同时 v-leave 被删除)，在过渡/动画完成之后移除。

14、动画

	自定义过渡的类名
		enter-class
		enter-active-class
		enter-to-class (2.1.8+)
		leave-class
		leave-active-class
		leave-to-class (2.1.8+)

15、混入

16、Ajax(axios)   https://www.runoob.com/vue2/vuejs-ajax-axios.html

	Axios 是一个基于 Promise 的 HTTP 库
	 
	demo：
		axios.get('https://www.runoob.com/try/ajax/json_demo.json')
		  .then(response => (this.info = response))
		  .catch(function (error) { // 请求失败处理
			console.log(error);
		  });
		axios.post('https://www.runoob.com/try/ajax/demo_axios_post.php')
		  .then(response => (this.info = response))
		  .catch(function (error) { // 请求失败处理
			console.log(error);
		  });
	  使用参数：  
		// 直接在 URL 上添加参数 ID=12345
		axios.get('/user?ID=12345').then(function (response) {
			console.log(response);
		  }).catch(function (error) {
			console.log(error);
		  });
		 
		// 也可以通过 params 设置参数：
		axios.get('/user', {
				params: {
				ID: 12345
				}
			}).then(function (response) {
				console.log(response);
			}).catch(function (error) {
				console.log(error);
			});
		axios.post('/user', {
			firstName: 'Fred',        // 参数 firstName
			lastName: 'Flintstone'    // 参数 lastName
		  }).then(function (response) {
			console.log(response);
		  }).catch(function (error) {
			console.log(error);
		  });
		  
		  
	执行多个并发请求
		实例
		function getUserAccount() {
		  return axios.get('/user/12345');
		}
		 
		function getUserPermissions() {
		  return axios.get('/user/12345/permissions');
		}
		axios.all([getUserAccount(), getUserPermissions()])
		  .then(axios.spread(function (acct, perms) {
			// 两个请求现在都执行完成
		  }));	  

17、Ajax(vue-resource)  https://www.runoob.com/vue2/vuejs-ajax.html

	//发送get请求
		this.$http.get('/try/ajax/ajax_info.txt').then(function(res){
			document.write(res.body);    
		},function(){
			console.log('请求失败处理');
		});
		如果需要传递数据，可以使用 this.$http.get('get.php',{params : jsonData}) 格式，第二个参数 jsonData 就是传到后端的数据。

		this.$http.get('get.php',{params : {a:1,b:2}}).then(function(res){
			document.write(res.body);    
		},function(res){
			console.log(res.status);
		});

	// post 发送数据到后端，需要第三个参数 {emulateJSON:true}。
		emulateJSON 的作用： 如果Web服务器无法处理编码为 application/json 的请求，你可以启用 emulateJSON 选项
	//发送 post 请求
		this.$http.post('/try/ajax/demo_test_post.php',
						{name:"菜鸟教程",url:"http://www.runoob.com"},
						{emulateJSON:true}
		).then(function(res){
			document.write(res.body);    
		},function(res){
			console.log(res.status);
		});
		
	使用全局对象方式 Vue.http 或者在一个 Vue 实例的内部使用 this.$http来发起 HTTP 请求。

		// 基于全局Vue对象使用http
		Vue.http.get('/someUrl', [options]).then(successCallback, errorCallback);
		Vue.http.post('/someUrl', [body], [options]).then(successCallback, errorCallback);

		// 在一个Vue实例内使用$http
		this.$http.get('/someUrl', [options]).then(successCallback, errorCallback);
		this.$http.post('/someUrl', [body], [options]).then(successCallback, errorCallback)


19、过滤器

20、插槽 slot   https://cn.vuejs.org/v2/guide/components-slots.html

	demo:
	Vue.component('alert-box', {
	  template:"
		<div class="demo-alert-box">
		  <strong>Error!</strong>
		  <slot></slot>
		</div>"
	})
	
21、插件




		
