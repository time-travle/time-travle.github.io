
#CORS 跨域
<p>
    <a href="#" onclick="refreshContent('crossorigin')">返回</a>
    
</p>

---

SpringBoot下实现CORS的四种方式

介绍四种实现CORS的方法，两种是全局配置，两种是局部接口生效的配置。
一般来说，SpringBoot项目采用其中一种方式实现CORS即可。


1.使用CorsFilter进行全局跨域配置

    @Configuration
    public class GlobalCorsConfig {
        @Bean
        public CorsFilter corsFilter() {
    
            CorsConfiguration config = new CorsConfiguration();
            //开放哪些ip、端口、域名的访问权限，星号表示开放所有域
            config.addAllowedOrigin("*");
            //是否允许发送Cookie信息
            config.setAllowCredentials(true);
            //开放哪些Http方法，允许跨域访问
            config.addAllowedMethod("GET","POST", "PUT", "DELETE");
            //允许HTTP请求中的携带哪些Header信息
            config.addAllowedHeader("*");
            //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            config.addExposedHeader("*");
    
            //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
            UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
            configSource.registerCorsConfiguration("/**", config);
    
            return new CorsFilter(configSource);
        }
    }
    
2.重写WebMvcConfigurer的addCorsMappings方法（全局跨域配置）

    @Configuration
    public class GlobalCorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                            .allowedOrigins("*")    //开放哪些ip、端口、域名的访问权限
                            .allowCredentials(true)  //是否允许发送Cookie信息 
                            .allowedMethods("GET","POST", "PUT", "DELETE")     //开放哪些Http方法，允许跨域访问
                            .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
                            .exposedHeaders("*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                }
            };
        }
    }
    
3.使用CrossOrigin注解（局部跨域配置）

    将CrossOrigin注解加在Controller层的方法上，该方法定义的RequestMapping端点将支持跨域访问
    将CrossOrigin注解加在Controller层的类定义处，整个类所有的方法对应的RequestMapping端点都将支持跨域访问
        @RequestMapping("/cors")
        @ResponseBody
        @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600) 
        public String cors( ){
            return "cors";
        }
4 使用HttpServletResponse设置响应头(局部跨域配置)

    这种方式略显麻烦，不建议在SpringBoot项目中使用。

    @RequestMapping("/cors")
    @ResponseBody
    public String cors(HttpServletResponse response){
        //使用HttpServletResponse定义HTTP请求头，最原始的方法也是最通用的方法
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        return "cors";
    }