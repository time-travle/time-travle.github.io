<p>
<a href="#" onclick="refreshContent('springboot')">返回</a>
</p>

---

# Spring Boot 随机端口获得

微服务中随机端口的获取：

在实际的开发过程中，每个项目的端口都是定好的，通过 s e凹er.port 可以指定端口 。 当一个服务想要启动多个实例时，就需要去改变端口，特别是在我们后面进行 Spring Cloud 学习
的时候，服务都会注册到注册中心里面去，为了能够让服务随时都可以扩容，在服务启动的时候能随机生成一个可以使用的端口是最好不过的。 在 Spring Boot 中，可以通过${random ｝来生成随机数字，我们可以这样使用：
server.port=${random.int[2000,8000]}

通过 random.int 方法，指定随机数的访问 生成一个在 2 000 到 8000 之间的数字，这样每次启动的端口就都不一样了 。 其实上面的方法虽然能够达到预期的效果，但是也存在问题：如果这个端口已经在使用了，那么启动必然会报错。

所以我们可以通过代码的方式来随机生成一个端口，然后检测是否被使用，这样就能生成一个没有被使用的端口 。 编写一个启动参数设置类

    public  class  StartCommand  {
        private  Logger  logger=  LoggerFactory.getLogger(StartCommand.class);
        public  StartCommand(String[]  args)  {
            Boolean  isServerPort  =  false;
            String  serverPort  =””;
            if ( args  ! =  null )  {
                for  (String  arg  :  args)  {
                    if (StringUtils.hasText(arg)  && arg.startsWith (”--server.port”)){
                        isServerPort  =  true;
                        serverPort  =  arg;
                        break;
                    }
                }
                ／／ 没有指定端口 ， 则随机生成一个可用的端口
                if  ( !isServerPort)  {
                    int  port= ServerPortUtils .getAvailablePort();
                    logger.info (”current  server.port=”+ port);
                    System.setProperty (”server.port”, String.valueOf(port));
                }  else  {
                    logger.info("current  server.port=”+  serverPort.split(”=”) [ l]);
                    System.setProperty (”server.port”, serverPort.split (”=”) [ l]);  
                }
            }
        }
    }      

通过对启动参数进行遍历判断，如果有指定启动端口，后续就不自动生成了； 如果没有指定，就通过 ServerPortUtils 获取一个可以使用的端口，然后设置到环境变量 中 。 在application.properties
中通过下面的方式获取端口： server.port=${server.port}

关于获取可用端口的代码如代码

    public  static  int  getAvailablePort()  {
        int max  =  65535;
        int min  =  2000;
        Random  random  =  new  Random();
        int  port  =  random .nextint(max ）%（ max -min+l) +  min;
        boolean  using=  NetUtils.isLoclePortUsing(port);
        
        if (using)  { 
            return  getAvailablePort(); 
        }  else  { 
            return  port; 
        }
    }

主要逻辑是指定一个范围 然后是生成随机数字，最后通过 NetUti ls 来检查端口是否 可用，如果获取到可用的端口 则 直接返回，没有获取到可用的端口 则执行回调逻辑，重新 获取 。 检测端口是否可用主要是用 Socket
来判断这个端口是否可以被链接。

最后在启动类中调用端口即可使用

    public class ServiceApplication { 
        public static void main(String[]  args)  { 
            ／／ 启动参数设置 ， 比如自动生成端口 
            new StartCommand(args); 
            SpringApplication.run(ServiceApplication.class, args); 
        }
    }
