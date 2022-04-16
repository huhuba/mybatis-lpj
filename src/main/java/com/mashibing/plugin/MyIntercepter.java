package com.mashibing.plugin;


import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

@Intercepts(
  {
    @Signature(type = ParameterHandler.class,method = "",args = {})
  }
)
public class MyIntercepter implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    System.out.println("你好，我是张帅齐");
    return invocation.proceed();
  }
}
