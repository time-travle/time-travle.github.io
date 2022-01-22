<ul><a href="#" onclick="refreshDBConnectContent('mybatis')">返回</a></ul>


##动态sql
    
    在mapper文件中我们也是可以通过条件判断来确定是不是拼装一些条件的，其中的 if 、choose、 trim、foreach
    
###if语法：
    <if test='判断条件'>
        判断条件为true时 就会将这里面的东西包括起来
    </if>
    
    备注：这个就是金蛋的可以类比java 的if判断语法

###choose语法：
    这个关键字是一个组合出现的，而不是一个单独的出现
    <choose>
        <when test='判断条件'></when>
        <when test='判断条件'></when>
        ...
        <ohterwise></otherwise>
    </choose>
    
    备注：这个可以类比java中的 if - else if - else 语法

###foreach元素
    主要有 item，index，collection，open，separator，close。
	https://www.cnblogs.com/fangyu19900812/p/6046209.html
    item表示集合中每一个元素进行迭代时的别名，
    index指 定一个名字，用于表示在迭代过程中，每次迭代到的位置，
    open表示该语句以什么开始，
    separator表示在每次进行迭代之间以什么符号作为分隔 符，
    close表示以什么结束。
	
    这个一般是用来操作集合处理动作的，在批量插入或者更新时都是可以使用的，这样我们就不想用一条条的进行操作了
    格式：
        <foreach item="集合中的一项" index="索引变量" list="集合" open="整个拼装之后最左边的符号" 
            separator="没一个集合项拼接之后的，他们之间的连接符号" close="整个拼装之后最右边的符号">
            #{集合中的一项} 
        </foreach>

###trim 语法：
    
    查询：
    我们在进行查询条件拼接时是有可能 where后面的条件都是不满足的，这时如果不对where这个对应的判断条件进行处理，那么我们在进行sql执行时
    是有可能会报错的。这是我们就需要对where的判断条件进行特殊的处理逻辑了，就是说如果我们的判断条件都是不满足的条件下我们就不再进行对
    where关键字先进拼接了，就是说这时我们进全量查询。
    
    where 元素只会在至少有一个子元素的条件返回 SQL 子句的情况下才去插入“WHERE”子句。而且，若语句的开头为“AND”或“OR”，where 元素也会将它们去除。
        
    格式：    
    <trim prefix="where" prefixOverride="and || or ">
        ...  
    </trim>
    
    这样操作我们就可以保证在where条件至少有一个时才会拼装where 关键字 
    这时的prefixOverride 中的空格也是必要的，会在编译时将其进行拼接，
    
    插入：
        插入时我们使用的set的比较多，这是我们进行批量字段插入时也是可以trim进行使用的
        格式：
        <trim prefix="SET" suffixOverrides=" ">
                ...  
        </trim>        
    更新：
         进行批量字段更新时以为是可以使用trim的 用法可插入一样的操作
         若是不是使用 trim我们也可以直接使用set标签
         <set>
         </set>   
    备注：
    这个一般是用来处理批量类似的数据时的语法，处理单条数据时是用不到的，或者是不需要的。
    