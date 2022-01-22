# 定时任务的

##使用SpringBoot创建定时任务非常简单，目前主要有以下三种创建方式：

    一、基于注解(@Scheduled)
    二、基于接口（SchedulingConfigurer） 前者相信大家都很熟悉，但是实际使用中我们往往想从数据库中读取指定时间来动态执行定时任务，
        这时候基于接口的定时任务就派上用场了。
    三、基于注解设定多线程定时任务

##Java 系统中主要有三种方式来实现定时任务：
    
    Timer和 TimerTask
    ScheduledExecutorService
    三方框架 Quartz


    @Scheduled(cron = "0/5 * * * * ?") Cron表达式参数分别表示：

        秒（0~59） 例如0/5表示每5秒  例如1/5表示 从第一秒开始 每5秒触发一次
        分（0~59）
        时（0~23）
        日（0~31）的某天，需计算
        月（0~11）
        周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）


##四种方式：Timer、ScheduledExecutorService、SpringTask、Quartz。

####使用java的 Timer
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date date = dateFormat.parse("2018-07-11 12:00:00.000");
            new Timer("testTimer1").scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("TimerTask");
                }
            }, date,2000);
        } catch (ParseException e) {
            e.printStackTrace();
        }者是在任务开始就计算时间，有并发的情况
        
        解释：date是开始时间，2000ms是定时任务周期，每2s执行一次
        timer有2中方法 schedule 和 scheduleAtFixedRate，前者会等任务结束在开始计算时间间隔，后
####使用ScheduledExecutorService
        scheduledExecutorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledTask");
                    }
                },1, TimeUnit.SECONDS);
        解释：延迟1s启动，执行一次

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
                scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledTask");
                    }
                }, 1, 1, TimeUnit.SECONDS);
        解释：延迟1s启动，每隔1s执行一次，是前一个任务开始时就开始计算时间间隔，但是会等上一个任务结束在开始下一个

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
                scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledTask");
                    }
                }, 1, 1, TimeUnit.SECONDS);
        解释：延迟1s启动，在前一个任务执行完成之后，延迟1s在执行  

####使用 SpringTask
        写任务类
            import org.slf4j.Logger;
            import org.slf4j.LoggerFactory;
            import org.springframework.scheduling.annotation.Scheduled;
            import org.springframework.stereotype.Service;

            @Service
            public class SpringTask {
                private static final Logger log = LoggerFactory.getLogger(SpringTask.class);

                @Scheduled(cron = "1/5 * * * * *")
                public void task1(){
                    log.info("springtask 定时任务！");
                }
                
                @Scheduled(initialDelay = 1000,fixedRate = 1*1000)
                public void task2(){
                    log.info("springtask 定时任务！");
                }
            }
            解释：
            task1 是每隔5s执行一次，{秒} {分} {时} {日期} {月} {星期}
            task2 是延迟1s,每隔1S执行一次
        配置文件修改
            （1）简单版
            <task:annotation-driven/>
            （2）任务池版
            <task:executor id="executor" pool-size="10" />
            <task:scheduler id="scheduler" pool-size="10" />
            <task:annotation-driven executor="executor" scheduler="scheduler" />
            （3）解释
            假如只有一个定时任务，可以用简单版；如果有多个定时任务，则要用任务池，不然它会顺序执行。

            两个任务的时间间隔为：执行时间+设定的定时间隔

            例子：(这个任务8s执行一次)

            @Scheduled(cron = "1/4 * * * * *")
            public void task2(){
                log.info("springtask 定时任务2！");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        
        
##框架 Quartz       https://www.cnblogs.com/zhangbin1989/p/9294114.html
        
    加依赖
        <!-- quartz -->
        <dependency>
          <groupId>org.quartz-scheduler</groupId>
          <artifactId>quartz</artifactId>
          <version>2.3.0</version>
        </dependency>
        <!--调度器核心包-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>4.3.4.RELEASE</version>
        </dependency>   
        
    Job实现    
        import org.quartz.Job;
        import org.quartz.JobExecutionContext;
        import org.quartz.JobExecutionException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        public class HelloWorldJob implements Job {
            @Override
            public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());
                System.out.println( strTime + ":Hello World！");
            }
        }
        
    调度器(可以用listener在项目启动时执行)
        import org.quartz.*;
        import org.quartz.impl.StdSchedulerFactory;

        public class MyScheduler {
            public static void main(String[] args) throws SchedulerException {
                //创建调度器Schedule
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                Scheduler scheduler = schedulerFactory.getScheduler();
                //创建JobDetail实例，并与HelloWordlJob类绑定
                JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class).withIdentity("job1", "jobGroup1")
                        .build();
                //创建触发器Trigger实例(立即执行，每隔1S执行一次)
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("trigger1", "triggerGroup1")
                        .startNow()
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                        .build();
                //开始执行
                scheduler.scheduleJob(jobDetail, trigger);
                scheduler.start();
            }
        }
        解释：上面用的是简单触发器，也可以用Con触发器，如下

        Trigger cronTrigger = TriggerBuilder.newTrigger()
                        .withIdentity("trigger2", "triggerGroup2")
                        .startNow()
                        .withSchedule(cronSchedule("0 42 10 * * ?"))
                        .build();    
        
    整合spring
        也可以直接把上面的调度器写成配置文件，整合spring

        （1）job
            package com.zb.quartz;
            import java.text.SimpleDateFormat;
            import java.util.Date;

            public class QuarFirstJob {
                public void first() {
                    String strTime = new SimpleDateFormat("HH-mm-ss").format(new Date());
                    System.out.println( strTime + ":Hello World！");
                }
            }
        （2）配置文件
            <bean id="QuarFirstJob" class="com.zb.quartz.QuarFirstJob" />

            <bean id="jobDetail"
                  class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
                <property name="group" value="quartzGroup1" />
                <property name="name" value="quartzJob1" />
                <!--false表示等上一个任务执行完后再开启新的任务 -->
                <property name="concurrent" value="false" />
                <property name="targetObject">
                    <ref bean="QuarFirstJob" />
                </property>
                <property name="targetMethod">
                    <value>first</value>
                </property>
            </bean>

            <!-- 调度触发器 -->
            <bean id="myTrigger"
                  class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
                <property name="name" value="trigger1" />
                <property name="group" value="group1" />
                <property name="jobDetail">
                    <ref bean="jobDetail" />
                </property>
                <property name="cronExpression">
                    <value>0/5 * * * * ?</value>
                </property>
            </bean>

            <!-- 调度工厂 -->
            <bean id="scheduler"
                  class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
                <property name="triggers">
                    <list>
                        <ref bean="myTrigger" />
                    </list>
                </property>
            </bean>    
