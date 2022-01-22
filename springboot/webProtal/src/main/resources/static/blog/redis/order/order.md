<p>
    <a href="#" onclick="refreshContent('redis')">返回目录</a>
</p>

#redis order命令：
---
###命令：
	CLIENT LIST		返回连接到 redis 服务的客户端列表
	CLIENT SETNAME	设置当前连接的名称
	CLIENT GETNAME	获取通过 CLIENT SETNAME 命令设置的服务名称
	CLIENT PAUSE	挂起客户端连接，指定挂起的时间以毫秒计
	CLIENT KILL		关闭客户端连接
####启动客户端：
	$ redis-cli
####在远程服务上执行命令    
	$ redis-cli -h host -p port -a password
		演示如何连接到主机为 127.0.0.1，端口为 6379 ，密码为 mypass 的 redis 服务上。
			$redis-cli -h 127.0.0.1 -p 6379 -a "mypass"


####Redis 命令
    https://www.w3cschool.cn/redis/redis-commands.html
    https://www.runoob.com/redis/redis-commands.html


####Redis 键(key)
    语法：Redis 键命令的基本语法如下：
        redis 127.0.0.1:6379> COMMAND KEY_NAME
####命令：   
    序号	
    命令及描述
    1	DEL key
        该命令用于在 key 存在时删除 key。
    2	DUMP key
        序列化给定 key ，并返回被序列化的值。
    3	EXISTS key
        检查给定 key 是否存在。
    4	EXPIRE key seconds
        为给定 key 设置过期时间，以秒计。
    5	EXPIREAT key timestamp
        EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
    6	PEXPIRE key milliseconds
        设置 key 的过期时间以毫秒计。
    7	PEXPIREAT key milliseconds-timestamp
        设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
    8	KEYS pattern
        查找所有符合给定模式( pattern)的 key 。
    9	MOVE key db
        将当前数据库的 key 移动到给定的数据库 db 当中。
    10	PERSIST key
        移除 key 的过期时间，key 将持久保持。
    11	PTTL key
        以毫秒为单位返回 key 的剩余的过期时间。
    12	TTL key
        以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
    13	RANDOMKEY
        从当前数据库中随机返回一个 key 。
    14	RENAME key newkey
        修改 key 的名称
    15	RENAMENX key newkey
        仅当 newkey 不存在时，将 key 改名为 newkey 。
    16	SCAN cursor [MATCH pattern] [COUNT count]
        迭代数据库中的数据库键。
    17	TYPE key
        返回 key 所储存的值的类型。


####Redis 字符串命令
    列出了常用的 redis 字符串命令：

        序号	命令及描述
        1	SET key value
        设置指定 key 的值
        2	GET key
        获取指定 key 的值。
        3	GETRANGE key start end
        返回 key 中字符串值的子字符
        4	GETSET key value
        将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
        5	GETBIT key offset
        对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
        6	MGET key1 [key2..]
        获取所有(一个或多个)给定 key 的值。
        7	SETBIT key offset value
        对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
        8	SETEX key seconds value
        将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
        9	SETNX key value
        只有在 key 不存在时设置 key 的值。
        10	SETRANGE key offset value
        用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
        11	STRLEN key
        返回 key 所储存的字符串值的长度。
        12	MSET key value [key value ...]
        同时设置一个或多个 key-value 对。
        13	MSETNX key value [key value ...]
        同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
        14	PSETEX key milliseconds value
        这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
        15	INCR key
        将 key 中储存的数字值增一。
        16	INCRBY key increment
        将 key 所储存的值加上给定的增量值（increment） 。
        17	INCRBYFLOAT key increment
        将 key 所储存的值加上给定的浮点增量值（increment） 。
        18	DECR key
        将 key 中储存的数字值减一。
        19	DECRBY key decrement
        key 所储存的值减去给定的减量值（decrement） 。
        20	APPEND key value
        如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。


####Redis hash 命令
    下表列出了 redis hash 基本的相关命令：

        序号	命令及描述
        1	HDEL key field1 [field2]
        删除一个或多个哈希表字段
        2	HEXISTS key field
        查看哈希表 key 中，指定的字段是否存在。
        3	HGET key field
        获取存储在哈希表中指定字段的值。
        4	HGETALL key
        获取在哈希表中指定 key 的所有字段和值
        5	HINCRBY key field increment
        为哈希表 key 中的指定字段的整数值加上增量 increment 。
        6	HINCRBYFLOAT key field increment
        为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
        7	HKEYS key
        获取所有哈希表中的字段
        8	HLEN key
        获取哈希表中字段的数量
        9	HMGET key field1 [field2]
        获取所有给定字段的值
        10	HMSET key field1 value1 [field2 value2 ]
        同时将多个 field-value (域-值)对设置到哈希表 key 中。
        11	HSET key field value
        将哈希表 key 中的字段 field 的值设为 value 。
        12	HSETNX key field value
        只有在字段 field 不存在时，设置哈希表字段的值。
        13	HVALS key
        获取哈希表中所有值。
        14	HSCAN key cursor [MATCH pattern] [COUNT count]
        迭代哈希表中的键值对。

####Redis 列表命令
    下表列出了列表相关的基本命令：

        序号	命令及描述
        1	BLPOP key1 [key2 ] timeout
        移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
        2	BRPOP key1 [key2 ] timeout
        移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
        3	BRPOPLPUSH source destination timeout
        从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
        4	LINDEX key index
        通过索引获取列表中的元素
        5	LINSERT key BEFORE|AFTER pivot value
        在列表的元素前或者后插入元素
        6	LLEN key
        获取列表长度
        7	LPOP key
        移出并获取列表的第一个元素
        8	LPUSH key value1 [value2]
        将一个或多个值插入到列表头部
        9	LPUSHX key value
        将一个值插入到已存在的列表头部
        10	LRANGE key start stop
        获取列表指定范围内的元素
        11	LREM key count value
        移除列表元素
        12	LSET key index value
        通过索引设置列表元素的值
        13	LTRIM key start stop
        对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
        14	RPOP key
        移除列表的最后一个元素，返回值为移除的元素。
        15	RPOPLPUSH source destination
        移除列表的最后一个元素，并将该元素添加到另一个列表并返回
        16	RPUSH key value1 [value2]
        在列表中添加一个或多个值
        17	RPUSHX key value
        为已存在的列表添加值

####Redis 集合命令
    下表列出了 Redis 集合基本命令：

        序号	命令及描述
        1	SADD key member1 [member2]
        向集合添加一个或多个成员
        2	SCARD key
        获取集合的成员数
        3	SDIFF key1 [key2]
        返回给定所有集合的差集
        4	SDIFFSTORE destination key1 [key2]
        返回给定所有集合的差集并存储在 destination 中
        5	SINTER key1 [key2]
        返回给定所有集合的交集
        6	SINTERSTORE destination key1 [key2]
        返回给定所有集合的交集并存储在 destination 中
        7	SISMEMBER key member
        判断 member 元素是否是集合 key 的成员
        8	SMEMBERS key
        返回集合中的所有成员
        9	SMOVE source destination member
        将 member 元素从 source 集合移动到 destination 集合
        10	SPOP key
        移除并返回集合中的一个随机元素
        11	SRANDMEMBER key [count]
        返回集合中一个或多个随机数
        12	SREM key member1 [member2]
        移除集合中一个或多个成员
        13	SUNION key1 [key2]
        返回所有给定集合的并集
        14	SUNIONSTORE destination key1 [key2]
        所有给定集合的并集存储在 destination 集合中
        15	SSCAN key cursor [MATCH pattern] [COUNT count]
        迭代集合中的元素


####Redis 发布订阅命令
    下表列出了 redis 发布订阅常用命令：

        序号	命令及描述
        1	PSUBSCRIBE pattern [pattern ...]
        订阅一个或多个符合给定模式的频道。
        2	PUBSUB subcommand [argument [argument ...]]
        查看订阅与发布系统状态。
        3	PUBLISH channel message
        将信息发送到指定的频道。
        4	PUNSUBSCRIBE [pattern [pattern ...]]
        退订所有给定模式的频道。
        5	SUBSCRIBE channel [channel ...]
        订阅给定的一个或多个频道的信息。
        6	UNSUBSCRIBE [channel [channel ...]]
        指退订给定的频道。

####Redis 事务命令
    下表列出了 redis 事务的相关命令：

        序号	命令及描述
        1	DISCARD
        取消事务，放弃执行事务块内的所有命令。
        2	EXEC
        执行所有事务块内的命令。
        3	MULTI
        标记一个事务块的开始。
        4	UNWATCH
        取消 WATCH 命令对所有 key 的监视。
        5	WATCH key [key ...]
        监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。

####Redis 连接命令
    下表列出了 redis 连接的基本命令：

        序号	命令及描述
        1	AUTH password
        验证密码是否正确
        2	ECHO message
        打印字符串
        3	PING
        查看服务是否运行
        4	QUIT
        关闭当前连接
        5	SELECT index
        切换到指定的数据库


####Redis 服务器命令
    下表列出了 redis 服务器的相关命令:

        序号	命令及描述
        1	BGREWRITEAOF
        异步执行一个 AOF（AppendOnly File） 文件重写操作
        2	BGSAVE
        在后台异步保存当前数据库的数据到磁盘
        3	CLIENT KILL [ip:port] [ID client-id]
        关闭客户端连接
        4	CLIENT LIST
        获取连接到服务器的客户端连接列表
        5	CLIENT GETNAME
        获取连接的名称
        6	CLIENT PAUSE timeout
        在指定时间内终止运行来自客户端的命令
        7	CLIENT SETNAME connection-name
        设置当前连接的名称
        8	CLUSTER SLOTS
        获取集群节点的映射数组
        9	COMMAND
        获取 Redis 命令详情数组
        10	COMMAND COUNT
        获取 Redis 命令总数
        11	COMMAND GETKEYS
        获取给定命令的所有键
        12	TIME
        返回当前服务器时间
        13	COMMAND INFO command-name [command-name ...]
        获取指定 Redis 命令描述的数组
        14	CONFIG GET parameter
        获取指定配置参数的值
        15	CONFIG REWRITE
        对启动 Redis 服务器时所指定的 redis.conf 配置文件进行改写
        16	CONFIG SET parameter value
        修改 redis 配置参数，无需重启
        17	CONFIG RESETSTAT
        重置 INFO 命令中的某些统计数据
        18	DBSIZE
        返回当前数据库的 key 的数量
        19	DEBUG OBJECT key
        获取 key 的调试信息
        20	DEBUG SEGFAULT
        让 Redis 服务崩溃
        21	FLUSHALL
        删除所有数据库的所有key
        22	FLUSHDB
        删除当前数据库的所有key
        23	INFO [section]
        获取 Redis 服务器的各种信息和统计数值
        24	LASTSAVE
        返回最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示
        25	MONITOR
        实时打印出 Redis 服务器接收到的命令，调试用
        26	ROLE
        返回主从实例所属的角色
        27	SAVE
        同步保存数据到硬盘
        28	SHUTDOWN [NOSAVE] [SAVE]
        异步保存数据到硬盘，并关闭服务器
        29	SLAVEOF host port
        将当前服务器转变为指定服务器的从属服务器(slave server)
        30	SLOWLOG subcommand [argument]
        管理 redis 的慢日志
        31	SYNC
        用于复制功能(replication)的内部命令


####常用命令：
    
    $ wget   下载
    $ tar    压缩解压
    $ make   创建
 




