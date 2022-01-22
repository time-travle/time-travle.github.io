<ul><a href="#" onclick="refreshDBConnectContent('mybatis')">返回</a></ul>

##一对一（association） 
http://www.mybatis.cn/archives/40.html

	这时我们的返回对象一般不是直接进行返回的，而是通过类似级联的形式进行关联的
	demo:人和身份证是一对一的关系	
		public class Card implements Serializable{
			private static final long serialVersionUID = 1L;
			private Integer id; // 主键id
			private String code; // 身份证编号
		}
		public class Person implements Serializable{
			private static final long serialVersionUID = 1L;
			private Integer id; // 主键id
			private String name; // 姓名
			private String sex; // 性别
			private Integer age; // 年龄
			// 人和身份证是一对一的关系，即一个人只有一个身份证
			private Card card;
		}
		
		<mapper namespace="cn.mybatis.mydemo.mapper.CardMapper">
		  <!-- 根据id查询Card，返回Card对象 -->
		  <select id="selectCardById" parameterType="int" resultType="cn.mybatis.mydemo.domain.Card">
			  select * from tb_card where id = #{id} 
		  </select>
		</mapper>

		<mapper namespace="cn.mybatis.mydemo.mapper.PersonMapper">
			<!-- 根据id查询Person，返回resultMap -->
			<select id="selectPersonById" parameterType="int" resultMap="personMapper">
				select * from tb_person where id = #{id}
			</select>
			<!-- 映射Peson对象的resultMap -->
			<resultMap type="cn.mybatis.mydemo.domain.Person" id="personMapper">
				<id property="id" column="id" />
				<result property="name" column="name" />
				<result property="sex" column="sex" />
				<result property="age" column="age" />
				<!-- 一对一关联映射:association -->
				<association property="card" column="card_id"
					select="cn.mybatis.mydemo.mapper.CardMapper.selectCardById"
					javaType="cn.mybatis.mydemo.domain.Card" />
			</resultMap>
		</mapper>

##一对多（collection） 
http://www.mybatis.cn/archives/42.html

	在一对一的基础上改动关键字 association 到 collection 
	demo:学生和班级是多对一的关系
		public class Clazz implements Serializable{
			private static final long serialVersionUID = 1L;
			private Integer id; // 班级id，主键
			private String code; // 班级编号
			private String name; // 班级名称
		}
		public class Student implements Serializable{
			private static final long serialVersionUID = 1L;
			private Integer id; // 学生id，主键
			private String name; // 姓名
			private String sex; // 性别
			private Integer age; // 年龄
		}
		
	<mapper namespace="cn.mybatis.mydemo2.mapper.StudentMapper">
		<!-- 根据id查询学生信息，多表连接，返回resultMap -->
		<select id="selectStudentById" parameterType="int"
			resultMap="studentResultMap">
			SELECT * FROM tb_clazz c,tb_student s
			WHERE c.id = s.clazz_id
			AND s.id = #{id}
		</select>
		<!-- 根据班级id查询学生信息，返回resultMap -->
		<select id="selectStudentByClazzId" parameterType="int"
			resultMap="studentResultMap">
			SELECT * FROM tb_student WHERE clazz_id = #{id}
		</select>
		<!-- 映射Student对象的resultMap -->
		<resultMap type="cn.mybatis.mydemo2.domain.Student"
			id="studentResultMap">
			<id property="id" column="id" />
			<result property="name" column="name" />
			<result property="sex" column="sex" />
			<result property="age" column="age" />
			<!-- 多对一关联映射:association -->
			<association property="clazz"
				javaType="cn.mybatis.mydemo2.domain.Clazz">
				<id property="id" column="id" />
				<result property="code" column="code" />
				<result property="name" column="name" />
			</association>
		</resultMap>
	</mapper>
	
	<mapper namespace="cn.mybatis.mydemo2.mapper.ClazzMapper">
		<!-- 根据id查询班级信息，返回resultMap -->
		<select id="selectClazzById" parameterType="int"
			resultMap="clazzResultMap">
			SELECT * FROM tb_clazz WHERE id = #{id}
		</select>

		<!-- 映射Clazz对象的resultMap -->
		<resultMap type="cn.mybatis.mydemo2.domain.Clazz"
			id="clazzResultMap">
			<id property="id" column="id" />
			<result property="code" column="code" />
			<result property="name" column="name" />
			<!-- 一对多关联映射:collection 
				fetchType="lazy"表示懒加载 该属性的取值有eager和lazy，
				eager表示立即加载 lazy表示懒加载，其不会立即查询，而是到使用时才会查询 -->
			<collection property="students" javaType="ArrayList"
				column="id" ofType="cn.mybatis.mydemo2.domain.Student"
				select="cn.mybatis.mydemo2.mapper.StudentMapper.selectStudentByClazzId"
				fetchType="lazy">
				<id property="id" column="id" />
				<result property="name" column="name" />
				<result property="sex" column="sex" />
				<result property="age" column="age" />
			</collection>
		</resultMap>
	</mapper>
	
	使用懒加载还需要在mybatis-config.xm 中增加如下配置：
		<settings>
			<!-- 要使延迟加载生效必须配置下面两个属性 -->
			<setting name="lazyLoadingEnabled" value="true"/>
			<setting name="aggressiveLazyLoading" value="false"/>
		</settings>

		lazyLoadingEnabled属性表示延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。默认为false。
		agressiveLazyLoading属性启用时，会使带有延迟加载属性的对象立即加载; 反之，每种属性将会按需加载。
			默认为true,所以这里需要设置成false
	
##多对多 
http://www.mybatis.cn/archives/43.html

	两个一对多 彼此包容彼此

