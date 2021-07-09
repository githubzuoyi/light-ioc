package org.apache.dependencies.inject.context;

import org.apache.dependencies.inject.Lifecycle;

import java.util.List;

/**
 *
 * 组件上下文
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public interface ComponentContext extends Lifecycle {

    Object getComponent(String beanName);

    Object getComponent(Class<?> Type);

    boolean registerComponent(String beanName, Object bean);

    boolean disRegisterComponent(String beanName);

    List<Object> listComponents();

}
