<p>
    <a href="#" onclick="refreshContent('redis')">返回目录</a>
</p>

#redis 笔记：
---
####Redis是一个键值存储仓库，经常被称为 NoSQL 数据库。
	是一个高性能的key-value数据库
	键值存储仓库的本质是有能力按照一个键映射一个值的方式存储一些数据，然后你可以只通过这个键寻找到你之前通过这个键存储的值
	
####可以使用命令SET将值『fido』存储在键『server:name』中：
	SET server:name "fido"

####查询key对应的值	
	GET server:name # => "fido"
	
####数据过期设置
	SET resource:lock "Redis Demo"  ---存入
	EXPIRE resource:lock 120 ---存入120s

	TTL resource:lock # => 120  --- 查看这个键还剩余多少时间
	# after 122s later
	TTL resource:lock # => -2  --- 已经过期了 2s
	
	这里的 -2 是指 resource:lock 已经不存在了，如果返回值是 -1 说明这个键永远不会过 期。
	注意：当你使用 SET 重新设置一个键， 它对应的 TTL 就会被重置

####redis列表 相关
	RPUSH 将值添加到列表的末尾
	LPUSH 将值添加到列表的开始
	LRANGE是从列表中去一个指定范围的子集。它通过你想取的范围的第一个元素的下标和 最后一个元素的下标作为参数。
			将 -1 作为参数意味着取值到列表的最后。
	LLEN 返回指定列表的长度
	LPOP 从列表中删除第一个元素，并将它作为返回值
	RPOP 从列表中删除最后一个元素，并将它作为返回值
	
####redis集合 相关	
	SADD 将给定的值添加到集合中
	SREM 从集合中移除指定的值
	SISMEMBER 检查一个值是否在集合中，返回0不在，返回1在。
	SMEMBERS 返回集合中所有的元素
	SUNION 合并两个或者更多个集合，并且将所有的元素返回。

####Redis 设计与实现 https://www.w3cschool.cn/hdclil/cnv2lozt.html 


####Redis常用管理命令
    # dbsize 返回当前数据库 key 的数量。
    # info 返回当前 redis 服务器状态和一些统计信息。
    # monitor 实时监听并返回redis服务器接收到的所有请求信息。
    # shutdown 把数据同步保存到磁盘上，并关闭redis服务。
    # config get parameter 获取一个 redis 配置参数信息。（个别参数可能无法获取）
    # config set parameter value 设置一个 redis 配置参数信息。（个别参数可能无法获取）
    # config resetstat 重置 info 命令的统计信息。（重置包括：keyspace 命中数、
    # keyspace 错误数、 处理命令数，接收连接数、过期 key 数）
    # debug object key 获取一个 key 的调试信息。
    # debug segfault 制造一次服务器当机。
    # flushdb 删除当前数据库中所有 key,此方法不会失败。小心慎用
    # flushall 删除全部数据库中所有 key，此方法不会失败。小心慎用

####Reids工具命令
    #redis-server：Redis 服务器的 daemon 启动程序
    #redis-cli：Redis 命令行操作工具。当然，你也可以用 telnet 根据其纯文本协议来操作
    #redis-benchmark：Redis 性能测试工具，测试 Redis 在你的系统及你的配置下的读写性能
    $redis-benchmark -n 100000 –c 50
    #模拟同时由 50 个客户端发送 100000 个 SETs/GETs 查询
    #redis-check-aof：更新日志检查
    #redis-check-dump：本地数据库检查



####Java中使用 demo:
    1、连接到 redis 服务
        实例
        import redis.clients.jedis.Jedis;
         
        public class RedisJava {
            public static void main(String[] args) {
                //连接本地的 Redis 服务
                Jedis jedis = new Jedis("localhost");
                System.out.println("连接成功");
                //查看服务是否运行
                System.out.println("服务正在运行: "+jedis.ping());
            }
        }
        编译以上 Java 程序，确保驱动包的路径是正确的。

        连接成功
        服务正在运行: PONG
    2、Redis Java String(字符串) 实例
        实例
        import redis.clients.jedis.Jedis;
         
        public class RedisStringJava {
            public static void main(String[] args) {
                //连接本地的 Redis 服务
                Jedis jedis = new Jedis("localhost");
                System.out.println("连接成功");
                //设置 redis 字符串数据
                jedis.set("runoobkey", "www.runoob.com");
                // 获取存储的数据并输出
                System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
            }
        }
        编译以上程序。

        连接成功
        redis 存储的字符串为: www.runoob.com
    3、Redis Java List(列表) 实例
        实例
        import java.util.List;
        import redis.clients.jedis.Jedis;
         
        public class RedisListJava {
            public static void main(String[] args) {
                //连接本地的 Redis 服务
                Jedis jedis = new Jedis("localhost");
                System.out.println("连接成功");
                //存储数据到列表中
                jedis.lpush("site-list", "Runoob");
                jedis.lpush("site-list", "Google");
                jedis.lpush("site-list", "Taobao");
                // 获取存储的数据并输出
                List<String> list = jedis.lrange("site-list", 0 ,2);
                for(int i=0; i<list.size(); i++) {
                    System.out.println("列表项为: "+list.get(i));
                }
            }
        }
        编译以上程序。

        连接成功
        列表项为: Taobao
        列表项为: Google
        列表项为: Runoob
    4、Redis Java Keys 实例
        实例
        import java.util.Iterator;
        import java.util.Set;
        import redis.clients.jedis.Jedis;
         
        public class RedisKeyJava {
            public static void main(String[] args) {
                //连接本地的 Redis 服务
                Jedis jedis = new Jedis("localhost");
                System.out.println("连接成功");
         
                // 获取数据并输出
                Set<String> keys = jedis.keys("*"); 
                Iterator<String> it=keys.iterator() ;   
                while(it.hasNext()){   
                    String key = it.next();   
                    System.out.println(key);   
                }
            }
        }
        编译以上程序。

        连接成功
        runoobkey
        site-list
