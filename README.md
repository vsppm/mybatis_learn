# xml文件

- 全局配置文件
- Sql映射文件


## 全局配置文件

> 依赖的语法规则在"mybatis-3-config.dtd"文件中定义

### properties

> 引入外部properties文件的内容

- resource
- url

## Sql映射文件

> 依赖的语法规则在"mybatis-3-mapper.dtd"文件中定义


# 线程安全

## SqlSession

> 非线程安全


# 插件

> MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括如下

## Executor

> update, query, flushStatements, commit, rollback, getTransaction, close, isClosed

## ParameterHandler

> getParameterObject, setParameters

## ResultSetHandler

> handleResultSets, handleOutputParameters

## StatementHandler

> prepare, parameterize, batch, update, query


# 事务管理器

> 如果你正在使用 Spring + MyBatis，则没有必要配置事务管理器， 因为 Spring 模块会使用自带的管理器来覆盖前面的配置

## JDBC

## MANAGED

## 自定义

> 实现TransationFactory接口即可

# 数据源

## UNPOOLED

> 这个数据源的实现只是每次被请求时打开和关闭连接。虽然有点慢，但对于在数据库连接可用性方面没有太高要求的简单应用程序来说，是一个很好的选择。 不同的数据库在性能方面的表现也是不一样的，对于某些数据库来说，使用连接池并不重要，这个配置就很适合这种情形。

## POOLED

- poolMaximumActiveConnections – 在任意时间可以存在的活动（也就是正在使用）连接数量，默认值：10
- poolMaximumIdleConnections – 任意时间可能存在的空闲连接数。
- poolMaximumCheckoutTime – 在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000 毫秒（即 20 秒）
- poolTimeToWait – 这是一个底层设置，如果获取连接花费了相当长的时间，连接池会打印状态日志并重新尝试获取一个连接（避免在误配置的情况下一直安静的失败），默认值：20000 毫秒（即 20 秒）。
- poolMaximumLocalBadConnectionTolerance – 这是一个关于坏连接容忍度的底层设置， 作用于每一个尝试从缓存池获取连接的线程. 如果这个线程获取到的是一个坏的连接，那么这个数据源允许这个线程尝试重新获取一个新的连接，但是这个重新尝试的次数不应该超过 poolMaximumIdleConnections 与 poolMaximumLocalBadConnectionTolerance 之和。 默认值：3 (新增于 3.4.5)
- poolPingQuery – 发送到数据库的侦测查询，用来检验连接是否正常工作并准备接受请求。默认是“NO PING QUERY SET”，这会导致多数数据库驱动失败时带有一个恰当的错误消息。
- poolPingEnabled – 是否启用侦测查询。若开启，需要设置 poolPingQuery 属性为一个可执行的 SQL 语句（最好是一个速度非常快的 SQL 语句），默认值：false。
- poolPingConnectionsNotUsedFor – 配置 poolPingQuery 的频率。可以被设置为和数据库连接超时时间一样，来避免不必要的侦测，默认值：0（即所有连接每一时刻都被侦测 — 当然仅当 poolPingEnabled 为 true 时适用）。

## JNDI

> 这个数据源的实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。

## 自定义

> 实现DataSourceFactory接口


# 多数据库厂商(databaseIdProvider)

> MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性,DB_VERDOR是一个别名,对应的java类是VendorDatabaseIdProvider。

# mapper注册

- mapper resource(基于xml的方式)

> 推荐xml方式

- mapper class(基于注解的方式)

> 适合于简单、不复杂的sql

- package 批量注册

> 基于xml的方式的dao类,需要将同类名的xml文件放到同一个注册的包路经下面,基于注解的方式,不需要任何处理.



# mapper标签

## cache
> 给定命名空间的缓存配置。

## cache-ref
> 其他命名空间缓存配置的引用。

## resultMap
> 是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象。

## sql
> 可被其他语句引用的可重用语句块，如果被其他引用的话,需要include标签

## insert
> 映射插入语句

- id 命名空间中的唯一标识符，可被用来代表这条语句。
- parameterType	将要传入语句的参数的完全限定类名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。
- parameterMap	这是引用外部 parameterMap 的已经被废弃的方法。使用内联参数映射和 parameterType 属性。
- flushCache	将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：true（对应插入、更新和删除语句）。
- timeout	这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。
- statementType	STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。
- useGeneratedKeys	（仅对 insert 和 update 有用）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系数据库管理系统的自动递增字段），默认值：false。
- keyProperty	（仅对 insert 和 update 有用）唯一标记一个属性，MyBatis 会通过 getGeneratedKeys 的返回值或者通过 insert 语句的 selectKey 子元素设置它的键值，默认：unset。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
- keyColumn	（仅对 insert 和 update 有用）通过生成的键值设置表中的列名，这个设置仅在某些数据库（像 PostgreSQL）是必须的，当主键列不是表中的第一列的时候需要设置。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
- databaseId	如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。

## update
> 映射更新语句

## delete
> 映射删除语句

## select
> 映射查询语句

- id	在命名空间中唯一的标识符，可以被用来引用这条语句。
- parameterType	将会传入这条语句的参数类的完全限定名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。
- parameterMap	这是引用外部 parameterMap 的已经被废弃的方法。使用内联参数映射和 parameterType 属性。
- resultType	从这条语句中返回的期望类型的类的完全限定名或别名。注意如果是集合情形，那应该是集合可以包含的类型，而不能是集合本身。使用 resultType 或 resultMap，但不能同时使用。
- resultMap	外部 resultMap 的命名引用。结果集的映射是 MyBatis 最强大的特性，对其有一个很好的理解的话，许多复杂映射的情形都能迎刃而解。使用 resultMap 或 resultType，但不能同时使用。
- flushCache	将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false。
- useCache	将其设置为 true，将会导致本条语句的结果被二级缓存，默认值：对 select 元素为 true。
- timeout	这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。
- fetchSize	这是尝试影响驱动程序每次批量返回的结果行数和这个设置值相等。默认值为 unset（依赖驱动）。
- statementType	STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED。
- resultSetType	FORWARD_ONLY，SCROLL_SENSITIVE 或 SCROLL_INSENSITIVE 中的一个，默认值为 unset （依赖驱动）。
- databaseId	如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。
- resultOrdered	这个设置仅针对嵌套结果 select 语句适用：如果为 true，就是假设包含了嵌套结果集或是分组了，这样的话当返回一个主结果行的时候，就不会发生有对前面结果集的引用的情况。这就使得在获取嵌套的结果集的时候不至于导致内存不够用。默认值：false。
- resultSets	这个设置仅对多结果集的情况适用，它将列出语句执行后返回的结果集并每个结果集给一个名称，名称是逗号分隔的。


# 多参数查询

> 多参数最终会转会为一个map传到mapper xml文件中的sql语句中,这样接口类的方法参数的展现形式有多种

- @Param注解方式,直接声明参数的key
- Pojo类
- Map类


# 内置变量

- _databaseId

> 如果mybatis-config.xml中提供了databaseIdProvider，那么mapper xml中_databaseId会被赋值为
environments设置的当前environment数据库访问驱动的别名,由此可以根据_databaseId的数值,在mapper xml中写不同的sql语句

- _parameter

> 可以代替传入的参数,亦可以进行遍历

# 缓存机制

> mybatis默认两级缓存

- 第一级缓存(sqlSession级别)是默认开启的
- 第二级缓存是namespace级别的缓存,需要手动开启和配置,可以通过Cache接口自定义二级缓存,是全局缓存

> 特殊说明二级缓存的工作机制:
1.当sqlSession对象查询一条数据,那么数据会保存在一级缓存中,
2.如果这个会话关闭,那么数据会从一级缓存流向二级缓存进行保存,新的sqlSession连接再次发起相同业务请求的时候,
会从二级缓存提取数据.
3.不同的namespace查询出的数据会放在对应的缓存中


> 可用的收回策略有:

- LRU – 最近最少使用的:移除最长时间不被使用的对象。
- FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
- SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
- WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。

> 默认的是 LRU。