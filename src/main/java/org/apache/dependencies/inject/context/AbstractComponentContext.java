package org.apache.dependencies.inject.context;

import java.util.List;

/**
 * 抽象组件上下文
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public abstract class AbstractComponentContext implements ComponentContext {


    public Object getComponent(String beanName) {
        return null;
    }

    public Object getComponent(Class<?> Type) {
        return null;
    }

    public boolean registerComponent(Object bean) {
        return false;
    }

    public boolean disRegisterComponent(String beanName) {
        return false;
    }

    public List<Object> listComponents() {
        return null;
    }

    public void initial() {

    }

    public void close() {

    }

}
