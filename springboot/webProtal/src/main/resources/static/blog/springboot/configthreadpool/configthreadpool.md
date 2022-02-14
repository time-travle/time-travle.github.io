<p>
<a href="#" onclick="refreshContent('springboot')">返回</a>
</p>

---

# Spring Boot 线程池配置

## 重新定义线程池的配置 （不自定义时 使用的是springboot默认的）

    实现接口
    public  class  AsyncTaskExecutePool  implements  AsyncConfigurer

## 线程池配置的拒绝策略

    AbortPolicy ：直接抛出 java.util.concurrent.RejectedExecutionException 异常 。
    CallerRunsPolicy ：主线程直接执行该任务，执行完之后尝试添加下一个任务到线程
        池中，这样可以有效降低向线程池内添加任务的速度 。
        建议大家用 CallerRunsPolicy 策略，因为当队列中的任务满了之后，如果直接抛异常，
        那么这个任务就会被丢弃。 如果是 CallerRunsPolicy 策略，则会用主线程去执行，就是同步
        执行，这样最起码任务不会被丢弃

## demo：

    @Configuration
    @ConfigurationProperties(prefix  = ” spring.task.pool")
    public  class  TaskThreadPoolConfig  {
    ／／ 核心线程数
    private  int  corePoolSize  =  5;
    ／／ 最大线程数
    private  int  maxPoolSize  =  50;
    ／／ 线程池维护线程所允许的 空闲时间
    private  int  keepAliveSeconds  =  60;
    ／／ 队列长度
    private  int  queueCapacity  =  10000;
    ／／ 线程名称前缀
    private  String  threadNamePrefix  =”FSH-AsyncTask-”;
    // get  set
    }
    

    @Configuration 
    public  class  AsyncTaskExecutePool  implements  AsyncConfigurer  { 
    private  Logger  logger  = 
    LoggerFactory.getLogger(AsyncTaskExecutePool.class); 
    @Autowired 
    private  TaskThreadPoolConfig  config; 
        @Override 
        public  Executor  getAsyncExecutor()  { 
            ThreadPoolTaskExecutor  executor=  new  ThreadPoolTaskExecutor(); 
            executor.setCorePoolSize(config.getCorePoolSize()); 
            executor.setMaxPoolSize(config.getMaxPoolSize()); 
            executor.setQueueCapacity(config.getQueueCapacity()); 
            executor.setKeepAliveSeconds(con丑g.getKeepAliveSeconds());
            executor.setThreadNamePre丑x(con丑g.ge t ThreadNamePre丑x() ); 
            executor.setRejectedExecutionHandler(new 
            ThreadPoolExecutor . CallerRunsPolicy()); 
            executor.initialize(); 
            return  executor; 
        }
        @Override 
        public  AsyncUncaughtExceptionHandler 
        getAsyncUncaughtExceptionHandler()  { 
            ／／ 异步任务中异常处理
            return  new  AsyncUncaughtExceptionHandler()  { 
                    @Override 
                    public  void  handleUncaughtException(Throwable  argO, 
                    Method  argl,  Object . ..  arg2)  { 
                    logger.error (”==========================" 
                    +argO.getMessage ()+”=======================”, argO); 
                    logger.error (”exception  method :”+ argl.getName()); 
                    }
                };  
        }
    }