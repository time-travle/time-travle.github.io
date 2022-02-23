<p>
    <a href="#" onclick="refreshContent('cached')">返回目录</a>
</p>

---

# Spring Cache

---

Spring Cache 是 Spring 提供的一整套的缓存解决方案。虽然它本身并没有提供缓存的实现，但是它提供了一整套的接口和代码规范、配置、注解等，这样它就可以整合各种缓存方案了，比如 Redis、Ehcache，


Spring 提供了四个注解来声明缓存规则。@Cacheable，@CachePut，@CacheEvict，@Caching。

![avatar](../blog/cached/springcache/imgs/img.png)
