package org.apache.dependencies.inject.context.jndi;


import org.apache.dependencies.inject.context.AbstractComponentContext;

import java.util.List;

/**
 * Ioc Context BASE ON jndi
 *
 * jndi 基础操作
 * COMPONENT_ENV_CONTEXT_NAME = "java:comp/env"
 *
 * 初始化 jndi
 * Context context = null;
 * context = new InitialContext();
 *
 * 获取 jndi 上下文
 * this.envContext = (Context) context.lookup(COMPONENT_ENV_CONTEXT_NAME);
 *
 *
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public class JndiComponentContext extends AbstractComponentContext {


    @Override
    protected List<String> listComponentNames() {
        return null;
    }

    @Override
    protected Object getResolvedComponent(String name) {
        return null;
    }
}
