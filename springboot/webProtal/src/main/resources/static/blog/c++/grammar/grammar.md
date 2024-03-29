# C ++ 语言 语法

<p>
<a href="#" onclick="refreshContent('c++')">返回</a>

</p>

---

## 1、文件中的#ifndef

头件的中的#ifndef，这是一个很关键的东西。

比如你有两个C文件，这两个C文件都include了同一个头文件。

而编译时，这两个C文件要一同编译成一个可运行文件，于是问题来了，大量的声明冲突。

还是把头文件的内容都放在#ifndef和#endif中吧。

不管你的头文件会不会被多个文件引用，你都要加上这个。一般格式是这样的：

	#ifndef <标识>
	#define <标识>
	......
	......
	#endif

	<标识>在理论上来说可以是自由命名的，但每个头文件的这个“标识”都应该是唯一的。标识的命名规则一般是头文件名全大写，前后加下划线，并把文件名中的“.”也变成下划线，如：stdio.h

	#ifndef _STDIO_H_ 
	#define _STDIO_H_
	......
	#endif

### 举个例子：

	假设你的工程里面有4个文件，分别是Stock.cpp,     b.h,      c.h,     d.h。
	Stock.cpp的头部是：
	　　#include "b.h "
	　　#include "c.h "

	b.h和c.h的头部都是:
	#include "d.h "
	而d.h里面有class Stock的定义。

	运行:
	编译器编译Stock.cpp的时候，先根据头文件#include "b.h "去编译b.h这个问题，再根据b.h里面的#include "d.h "，去编译d.h的这个文件，这样就把d.h里面的class D编译了；
	然后再根据Stock.cpp的第二句#include "c.h "，去编译c.h，最终还是会找到的d.h里面的class D，但是class D之前已经编译过了，所以就会报重定义错误。

	加上ifndef/define/endif，就可以防止这种重定义错误。

## 2、include 用法

    #include是C++ 的编译预处理命令，它的作用包含对应的文件，
        #include 的两种不同的写法，#include<***.h>和#include"***.h".


	采用“<>”方式进行包含的头文件表示让编译器在编译器的预设标准路径下去搜索相应的头文件，如果找不到就报错。

	采用“”表示先在工程所在路径下搜索，如果失败，再到系统标准路径下搜索。
	所以，特别要注意的是，如果是标准库头文件，那么既可以采用<>的方式，又可以采用" "的方式，而用户自定义的头文件只能采用" "的方式。

    #include其实不过是将指定文件内容展开，然后再编译而已，那肯定能轻松理解这个赢巧奇技

## 3、C++中定义常量可以用#define 、const 这两种方法。

	例如:

	#define PRICE 10 //定义单价常量10
	const int PRICE = 10; //定义单价常量10

## 4、C++ 中define（宏定义）

### 1、define是宏定义bai，

    程序在预处理阶段将du用define定义的内容进行了替换。
    因此在程序运行时，常zhi量表中dao并没有用define定义的常量，系统不为它分配内存。
    而const定义的常量，在程序运行时，存在常量表中，且系统为它分配内存。

### 2、define定义的常量，预处理时只是直接进行了替换，因此在编译时不能进行数据类型检验。而const定义的常量，在编译时进行严格的类型检验，可以避免出错。

### 3、define定义表达式时要注意“边缘效应”。

		例如：
		#defineN1+2；
		floata=N/2.0;
		按照常规做法，可能会认为结果是3/2=1.5；
		但是实际上，结果应该为1+2/2.0=2.0；
		若想要实现3/2，则#defineN(1+2)；
		即为避免边缘效应，一定要加括号。

	C++宏定义

		#define命令是C++语言中的宏定义命令，它用于将标识符定义为字符串，标识符称为宏名，定义的字符串称为替换文本。
		1、简单的宏定义：
		#define <宏名> <字符串>
		例： #define PI 3.1415926
		2、带参数的宏定义
		#define <宏名> (<参数表>) <宏体>
		例： #define A(x) x

## 5、using namespace std;

告诉编译器使用 std 命名空间。命名空间是 C++ 中一个相对新的概念。

## 6、C++ 中，有两种简单的定义常量的方式：

    使用 #define 预处理器。
        #define identifier value

	使用 const 关键字
		const type variable = value;

## 7、指针

    每一个变量都有一个内存位置，每一个内存位置都定义了可使用连字号（&）运算符访问的地址，它表示了在内存中的一个地址。

	demo:
		#include <iostream>

		using namespace std;

		int main ()
		{
		   int  var1;
		   char var2[10];

		   cout << "var1 变量的地址： ";
		   cout << &var1 << endl;

		   cout << "var2 变量的地址： ";
		   cout << &var2 << endl;

		   return 0;
		}
    


    指针是一个变量，其值为另一个变量的地址，即，内存位置的直接地址。
    就像其他变量或常量一样，您必须在使用指针存储其他变量地址之前，对其进行声明。
    指针变量声明的一般形式为： type *var-name;
    int    *ip;    /* 一个整型的指针 */
    double *dp;    /* 一个 double 型的指针 */
    float  *fp;    /* 一个浮点型的指针 */
    char   *ch     /* 一个字符型的指针 */

## 8、引用

	demo:
		#include <iostream>
		using namespace std;
		 
		int main ()
		{
		   // 声明简单的变量
		   int    i;
		   double d;
		 
		   // 声明引用变量
		   int&    r = i;
		   double& s = d;
		   
		   i = 5;
		   cout << "Value of i : " << i << endl;
		   cout << "Value of i reference : " << r  << endl;
		 
		   d = 11.7;
		   cout << "Value of d : " << d << endl;
		   cout << "Value of d reference : " << s  << endl;
		   
		   return 0;
		}

## 9、虚函数，纯虚函数

    虚函数
    是在基类中使用关键字 virtual 声明的函数。在派生类中重新定义基类中定义的虚函数时，会告诉编译器不要静态链接到该函数。
    我们想要的是在程序中任意点可以根据所调用的对象类型来选择调用的函数，这种操作被称为动态链接，或后期绑定。

	纯虚函数
	您可能想要在基类中定义虚函数，以便在派生类中重新定义该函数更好地适用于对象，但是您在基类中又不能对虚函数给出有意义的实现，
	这个时候就会用到纯虚函数

## 10、C++ 命名空间

    定义命名空间
    命名空间的定义使用关键字 namespace，后跟命名空间的名称，如下所示：

		namespace namespace_name {
		   // 代码声明
		}
		
	为了调用带有命名空间的函数或变量，需要在前面加上命名空间的名称，如下所示：

	name::code;  // code 可以是变量或函数
	来看看命名空间如何为变量或函数等实体定义范围：

	#include <iostream>
	using namespace std;

	// 第一个命名空间
	namespace first_space{
	   void func(){
		  cout << "Inside first_space" << endl;
	   }
	}
	// 第二个命名空间
	namespace second_space{
	   void func(){
		  cout << "Inside second_space" << endl;
	   }
	}
	int main ()
	{
	 
	   // 调用第一个命名空间中的函数
	   first_space::func();
	   
	   // 调用第二个命名空间中的函数
	   second_space::func(); 

	   return 0;
	}
	
	
	