<p>
<a href="#" onclick="refreshSpringContent('aop')">返回</a>&emsp;&emsp;&emsp;
</p>

---
#日志记录 demo    
首先我们需要自定义一个全局日志记录的切面类GlobalLogAspect
然后在该类添加@Aspect注解，然后在定义一个公共的切入点（Pointcut），指向需要处理的包，然后在定义一个前置通知(添加@Before注解)，后置通知(添加@AfterReturning)和环绕通知（添加@Around）方法实现即可


    import cn.hutool.core.date.DateUtil;
    import cn.hutool.core.date.TimeInterval;
    import cn.hutool.json.JSONUtil;
    import cn.soboys.core.utils.HttpContextUtil;
    import io.swagger.annotations.ApiOperation;
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.AfterThrowing;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.stereotype.Component;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestParam;
    
    import javax.servlet.http.HttpServletRequest;
    import java.lang.reflect.Method;
    import java.lang.reflect.Parameter;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    
    /**
    * @author kenx
    * @version 1.0
    * @date 2021/6/18 15:22
    * 全局日志记录器
    */
    @Slf4j
    @Aspect
    @Component
    public class GlobalLogAspect extends BaseAspectSupport {
    /**
    * 定义切面Pointcut  拦截 cn.soboys.mallapi.controller报下的全部类
    */
    @Pointcut("execution(public * cn.soboys.mallapi.controller.*.*(..))")
        public void log() {}
    
    
        /**
         * 环绕通知
         *
         * @param joinPoint
         * @return
         */
        @Around("log()")
        public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    
            LogSubject logSubject = new LogSubject();
            //记录时间定时器
            TimeInterval timer = DateUtil.timer(true);
            //执行结果
            Object result = joinPoint.proceed();
            logSubject.setResult(result);
            //执行消耗时间
            String endTime = timer.intervalPretty();
            logSubject.setSpendTime(endTime);
            //执行参数
            Method method = resolveMethod(joinPoint);
            logSubject.setParameter(getParameter(method, joinPoint.getArgs()));
    
            HttpServletRequest request = HttpContextUtil.getRequest();
            // 接口请求时间
            logSubject.setStartTime(DateUtil.now());
            //请求链接
            logSubject.setUrl(request.getRequestURL().toString());
            //请求方法GET,POST等
            logSubject.setMethod(request.getMethod());
            //请求设备信息
            logSubject.setDevice(HttpContextUtil.getDevice());
            //请求地址
            logSubject.setIp(HttpContextUtil.getIpAddr());
            //接口描述
            if (method.isAnnotationPresent(ApiOperation.class)) {
                ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
                logSubject.setDescription(apiOperation.value());
            }
    
            String a = JSONUtil.toJsonPrettyStr(logSubject);
            log.info(a);
            return result;
        }
        
        /**
         * 根据方法和传入的参数获取请求参数
         */
        private Object getParameter(Method method, Object[] args) {
            List<Object> argList = new ArrayList<>();
            Parameter[] parameters = method.getParameters();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < parameters.length; i++) {
                //将RequestBody注解修饰的参数作为请求参数
                RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
                //将RequestParam注解修饰的参数作为请求参数
                RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                String key = parameters[i].getName();
                if (requestBody != null) {
                    argList.add(args[i]);
                } else if (requestParam != null) {
                    map.put(key, args[i]);
                } else {
                    map.put(key, args[i]);
                }
            }
            if (map.size() > 0) {
                argList.add(map);
            }
            if (argList.size() == 0) {
                return null;
            } else if (argList.size() == 1) {
                return argList.get(0);
            } else {
                return argList;
            }
        }
    }


    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.reflect.MethodSignature;
    import java.lang.reflect.Method;
    
    /**
    * @author kenx
    * @version 1.0
    * @date 2021/6/18 14:52
    * 切面
    */
    public class BaseAspectSupport {
    public Method resolveMethod(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();        
           Method method = getDeclaredMethod(targetClass, signature.getName(), signature.getMethod().getParameterTypes());
           if (method == null) {
               throw new IllegalStateException("无法解析目标方法: " + signature.getMethod().getName());
           }
           return method;
    }
        
    private Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
            try {
                return clazz.getDeclaredMethod(name, parameterTypes);
            } catch (NoSuchMethodException e) {
                Class<?> superClass = clazz.getSuperclass();
                if (superClass != null) {
                    return getDeclaredMethod(superClass, name, parameterTypes);
                }
            }
                return null;
            }
    }

    import lombok.Data;
    /**
    * @author kenx
    * @version 1.0
    * @date 2021/6/18 18:48
    * 日志信息
    */
    @Data
    public class LogSubject {
        。。。
    }
  
