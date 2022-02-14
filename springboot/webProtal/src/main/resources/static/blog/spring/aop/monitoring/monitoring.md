<p>
<a href="#" onclick="refreshSpringContent('aop')">返回</a>&emsp;&emsp;&emsp;
</p>

---

# 监控

demo：

        代码中定义了三种类型的通知， 
        使用@Before注解标识前置通知，打印“beforeAdvice...”， 
        使用@After注解标识后置通知，打印“AfterAdvice...”，
        使用@Around注解标识环绕通知，在方法执行前和执行之后分别打印“before”和“after”。 

这样一个切面就定义好了，代码如下：

    @Aspect
    @Component
    public class AopAdvice {
        @Pointcut("execution (* com.shangguan.aop.controller.*.*(..))")
        public void test() { }
    
        @Before("test()")
        public void beforeAdvice() {
            System.out.println("beforeAdvice...");
        }
    
        @After("test()")
        public void afterAdvice() {
            System.out.println("afterAdvice...");
        }
    
        @Around("test()")
        public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
            System.out.println("before");
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            System.out.println("after");
        }
    }

使用后结果：

![avatar](../blog/spring/aop/imag/aop3.png)

