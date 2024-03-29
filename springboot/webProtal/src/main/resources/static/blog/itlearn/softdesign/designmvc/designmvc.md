<p>
    <a href="#" onclick="showITLearnPage('softdesign')">返回设计模式目录</a>
</p>

# MVC模式

## 什么是MVC的开发模式：

	MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写
		Model（模型）表示应用程序核心（比如数据库记录列表）。
			应用程序中用于处理应用程序数据逻辑的部分。通常模型对象负责在数据库中存取数据。
		View（视图）显示数据（数据库记录）。
			应用程序中处理数据显示的部分。通常视图是依据模型数据创建的。
		Controller（控制器）处理输入（写入数据库记录）。
			应用程序中处理用户交互的部分。通常控制器负责从视图读取数据，控制用户输入，并向模型发送数据

## MVC优点：

	1.降低代码耦合性。在MVC模式中，三个层各施其职，所以如果一旦哪一层的需求发生了变化，
		就只需要更改相应的层中的代码而不会影响到其他层中的代码。  
	2.有利于分工合作。在MVC模式中，由于按层把系统分开，那么就能更好的实现开发中的分工。
		网页设计人员可进行开发视图层中的JSP，而对业务熟悉的人员可开发业务层，而其他开发人员可开发控制层。
	3.有利于组件的重用。如控制层可独立成一个能用的组件，表示层也可做成通用的操作界面。可以为一个模型在运行时同时建立和使用多个视图。

## MVC缺点：

	1.增加了系统结构和实现的复杂性。对于简单的界面，严格遵循MVC，使模型、视图与控制器分离，会增加结构的复杂性，
		并可能产生过多的更新操作，降低运行效率。  
	2.视图层展示依赖与模型层，视图层需要很少的字段，但是模型层全部提供，性能上有一定影响		

## MVC工作流程

	第一步：浏览者 -> 调用控制器，对它发出指令
	第二步：控制器 -> 按指令选取一个合适的模型
	第三步：模型 -> 按控制器指令取相应数据
	第四步：控制器 -> 按指令选取相应的视图
	第五步：视图 -> 把第三步取到的数据按用户想要的样子显示出来	

## SpringMVC工作原理

<a href="https://blog.csdn.net/qq_43378945/article/details/90907427#" target="_blank">https://blog.csdn.net/qq_43378945/article/details/90907427 </a>

	1.用户发送请求至前端控制器DispatcherServlet(也叫中央处理器).
	2.DispatcherServlet收到请求调用HandlerMappering处理器映射器
	3.处理器映射器找到具体的处理器（可以根据xml配置、注解进行查找），生成处理器对象及处理器拦截器（如果有则生成）一并返回给DispatcherServlet.
	4.DispatcherServlet调用HandlerAdapter处理器适配器。
	5.HandlerAdapter经过适配调用具体的处理器（Controller,也叫后端控制器）。
	6.Controller执行完成返回ModelAndView.
	7.HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet.
	8.DisPatcherServlet将ModelAndView传给ViewReslover视图解析器。
	9.ViewReslover解析后返回具体View.
	10.DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。
	11.DispatcherServlet响应用户。

## 组件说明：

	以下组件通常使用框架提供实现：

	DispatcherServlet：作为前端控制器，整个流程控制的中心，控制其它组件执行，统一调度，降低组件之间的耦合性，提高每个组件的扩展性。

	HandlerMapping：通过扩展处理器映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。

	HandlAdapter：通过扩展处理器适配器，支持更多类型的处理器。

	ViewResolver：通过扩展视图解析器，支持更多类型的视图解析，例如：jsp、freemarker、pdf、excel等。

## 组件：

	1、前端控制器DispatcherServlet（不需要工程师开发）,由框架提供
	作用：接收请求，响应结果，相当于转发器，中央处理器。有了dispatcherServlet减少了其它组件之间的耦合度。
	用户请求到达前端控制器，它就相当于mvc模式中的c，dispatcherServlet是整个流程控制的中心，由它调用其它组件处理用户的请求，dispatcherServlet的存在降低了组件之间的耦合性。

	2、处理器映射器HandlerMapping(不需要工程师开发),由框架提供
	作用：根据请求的url查找Handler
	HandlerMapping负责根据用户请求找到Handler即处理器，springmvc提供了不同的映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。

	3、处理器适配器HandlerAdapter
	作用：按照特定规则（HandlerAdapter要求的规则）去执行Handler
	通过HandlerAdapter对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处理器进行执行。

	4、处理器Handler(需要工程师开发)
	注意：编写Handler时按照HandlerAdapter的要求去做，这样适配器才可以去正确执行Handler
	Handler 是继DispatcherServlet前端控制器的后端控制器，在DispatcherServlet的控制下Handler对具体的用户请求进行处理。
	由于Handler涉及到具体的用户业务请求，所以一般情况需要工程师根据业务需求开发Handler。

	5、视图解析器View resolver(不需要工程师开发),由框架提供
	作用：进行视图解析，根据逻辑视图名解析成真正的视图（view）
	View Resolver负责将处理结果生成View视图，View Resolver首先根据逻辑视图名解析成物理视图名即具体的页面地址，再生成View视图对象，最后对View进行渲染将处理结果通过页面展示给用户。 springmvc框架提供了很多的View视图类型，包括：jstlView、freemarkerView、pdfView等。
	一般情况下需要通过页面标签或页面模版技术将模型数据通过页面展示给用户，需要由工程师根据业务需求开发具体的页面。

	6、视图View(需要工程师开发jsp…)
	View是一个接口，实现类支持不同的View类型（jsp、freemarker、pdf…）

## 核心架构的具体流程步骤如下：

	1、首先用户发送请求——>DispatcherServlet，前端控制器收到请求后自己不进行处理，而是委托给其他的解析器进行处理，作为统一访问点，进行全局的流程控制；
	2、DispatcherServlet——>HandlerMapping， HandlerMapping 将会把请求映射为HandlerExecutionChain 对象（包含一个Handler 处理器（页面控制器）对象、多个HandlerInterceptor 拦截器）对象，通过这种策略模式，很容易添加新的映射策略；
	3、DispatcherServlet——>HandlerAdapter，HandlerAdapter 将会把处理器包装为适配器，从而支持多种类型的处理器，即适配器设计模式的应用，从而很容易支持很多类型的处理器；
	4、HandlerAdapter——>处理器功能处理方法的调用，HandlerAdapter 将会根据适配的结果调用真正的处理器的功能处理方法，完成功能处理；并返回一个ModelAndView 对象（包含模型数据、逻辑视图名）；
	5、ModelAndView的逻辑视图名——> ViewResolver， ViewResolver 将把逻辑视图名解析为具体的View，通过这种策略模式，很容易更换其他视图技术；
	6、View——>渲染，View会根据传进来的Model模型数据进行渲染，此处的Model实际是一个Map数据结构，因此很容易支持其他视图技术；
	7、返回控制权给DispatcherServlet，由DispatcherServlet返回响应给用户，到此一个流程结束。

## 下边两个组件通常情况下需要开发：

    Handler：处理器，即后端控制器用controller表示。
    View：视图，即展示给用户的界面，视图中通常需要标签语言展示模型数据

## DispatcherServlet 的工作流程

    向服务器发送 HTTP 请求，请求被前端控制器 DispatcherServlet 捕获。

    DispatcherServlet 根据 -servlet.xml 中的配置对请求的 URL 进行解析，得到请求资源标识符（URI）。
    然后根据该 URI，调用 HandlerMapping 获得该 Handler 配置的所有相关的对象（包括 Handler 对象以及 Handler 对象对应的拦截器），最后以HandlerExecutionChain 对象的形式返回。

    DispatcherServlet 根据获得的Handler，选择一个合适的 HandlerAdapter。（附注：如果成功获得HandlerAdapter后，此时将开始执行拦截器的 preHandler(...)方法）。

    提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)。 在填充Handler的入参过程中，根据你的配置，Spring 将帮你做一些额外的工作：
        HttpMessageConveter： 将请求消息（如 Json、xml 等数据）转换成一个对象，将对象转换为指定的响应信息。
        数据转换：对请求消息进行数据转换。如String转换成Integer、Double等。
        数据根式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等。
        数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中。

    Handler(Controller)执行完成后，向 DispatcherServlet 返回一个 ModelAndView 对象；

    根据返回的ModelAndView，选择一个适合的 ViewResolver（必须是已经注册到 Spring 容器中的ViewResolver)返回给DispatcherServlet。

    ViewResolver 结合Model和View，来渲染视图。

    视图负责将渲染结果返回给客户端。

## Spring框架支持以下五种bean的作用域：

    singleton : bean在每个Spring ioc 容器中只有一个实例。
    prototype：一个bean的定义可以有多个实例。
    request：每次http请求都会创建一个bean，该作用域仅在基于web的Spring ApplicationContext情形下有效。
    session：在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
    global-session：在一个全局的HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring ApplicationContext情形下有效。
    缺省的Spring bean 的作用域是Singleton.


## Spring Bean 的生命周期来说：
    
    实例化 Instantiation
    属性赋值 Populate
    初始化 Initialization
    销毁 Destruction

实例化 -> 属性赋值 -> 初始化 -> 销毁












