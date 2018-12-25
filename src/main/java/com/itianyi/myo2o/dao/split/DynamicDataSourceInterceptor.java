package com.itianyi.myo2o.dao.split;

import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}),
	@Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class DynamicDataSourceInterceptor implements Interceptor{

	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);
	private final String REGEX =  ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
	private String lookupKey = DynamicDataSourceHolder.DB_MASTER;
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		//获取参数
		System.out.println("开始进入Interceptor");
		Object[] objects = invocation.getArgs();
		MappedStatement ms = (MappedStatement) objects[0];
		//判断当前是不是事务类型的
		boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
		if(synchronizationActive != true){
			if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
				//selectKey为自增id查询主键(SELECT LAST_INSERT_ID 方法,使用主库
				if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
					lookupKey = DynamicDataSourceHolder.DB_MASTER;
				}else{
					BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
					String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
					if(sql.matches(REGEX)){
						//增删改用主库
						lookupKey = DynamicDataSourceHolder.DB_MASTER;
					}else{
						lookupKey = DynamicDataSourceHolder.DB_SLAVE;
					}
				}
			}
		}else{
			lookupKey = DynamicDataSourceHolder.DB_MASTER;
		}
		//日志记录到底用的什么样的库
		logger.debug("设置方法[{}] use [{}] Strtegy, SqlCommandType[{}]..",ms.getId(),lookupKey,
				ms.getSqlCommandType().name());

		DynamicDataSourceHolder.setDbType(lookupKey);
		//继续后续的方法，该调用什么调用什么
		return invocation.proceed();
	}

	@Override
	/**
	 * 返回是本体还是代理
	 */
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		//看目标请求是不是ibatis.Executor类型的（crud操作)，如果是，那么就把this,即DynamicDataSource这个类包装进去
		//如果不是这种请求，那么直接放行。
		if(target instanceof Executor){
			System.out.println("是Executor方法，进入拦截器");
			return Plugin.wrap(target, this);
		}else{
			System.out.println("不是Executor方法，放行！");
			return target;
		}
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
