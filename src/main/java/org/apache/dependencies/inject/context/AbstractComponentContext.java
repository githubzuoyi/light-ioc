package org.apache.dependencies.inject.context;

import org.apache.dependencies.inject.FieldComponent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * 抽象组件上下文
 * <p>
 * 抽象出核心功能点
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public abstract class AbstractComponentContext implements ComponentContext {

    List<String> componentNamesCache = new LinkedList<String>();

    Map<String, Object> componentsCache = new ConcurrentHashMap<String, Object>();

    // TODO PostDestory bean 信息保存，用于销毁后处理

    /**
     * main operation function
     */

    /**
     * @param beanName
     * @return
     */
    @Override
    public Object getComponent(String beanName) {
        return this.componentsCache.get(beanName);
    }

    @Override
    public Object getComponent(Class<?> Type) {

        return null;
    }


    @Override
    public List<Object> listComponents() {
        List components = new LinkedList();
        //this.componentsCache.values().stream().forEach();
        return null;
    }

    /**
     * context initialize
     */

    /**
     * 组件上下文初始化
     * <p>
     * 加载 bean 信息
     * 实例化 bean
     * 初始化 bean
     */
    public void initial() {
        // 加载 bean 信息
        initialComponentNames();
        // 实例化 bean
        instantiationComponets(componentNamesCache);
        // 初始化 bean
        initializationComponent(componentsCache);
    }

    /**
     * 初始化 components
     * <p>
     * 生命周期接口
     *  包括
     *      bean 自身的生命周期过程控制
     *          @PostConstruct
     *          @PostDestory
     *      {实例化后，初始化前} 容器周期周期过程控制
     */
    private void initializationComponent(Map<String, Object> componentsCache) {
    }

    /**
     * 实例化 components
     * <p>
     * 实例化
     * 依赖注入 -- 对于 field 域中注解 @fieldComponent ，进行属性注入
     * 生命周期接口
     *
     * @param componentNamesCache
     */
    private void instantiationComponets(List<String> componentNamesCache) {
        componentNamesCache.stream().forEach(this::instantiateComponent);
        // TODO 依赖注入前引入钩子函数，可以对注入过程进行干预
        componentsCache.values().stream().forEach(this::injectComponent);
        // TODO 依赖注入后引入钩子函数，可以对注入过程进行干预
    }

    /**
     * 依赖注入
     * <p>
     * TODO 循环依赖问题处理
     *
     * @param component
     */
    private void injectComponent(Object component) {
        injectComponent(component, component.getClass());
    }

    /**
     * 过滤掉接口类
     *
     * 对 @fieldComponent 注解属性进行注入
     *
     * @param component
     * @param componentClass
     */
    private void injectComponent(Object component, Class componentClass) {
        Stream.of(componentClass.getDeclaredFields()).filter(field -> {
            return !componentClass.isInterface();
        }).forEach(field -> {
            FieldComponent fieldComponent = field.getAnnotation(FieldComponent.class);
            String injectComponentName = fieldComponent.name();
            Object injectComponent = getComponent(injectComponentName);

            // 通过feild反射 实现component依赖注入
            try {
                field.set(component,injectComponent);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });
    }

    private List<String> initialComponentNames() {
        if (componentNamesCache.size() == 0) {
            listComponentNames();
        }
        return this.componentNamesCache;
    }

    private void instantiateComponent(String beanName) {
        this.componentsCache.put(beanName, this.getResolvedComponent(beanName));
    }

    /**
     * operation of manner
     *
     * @param beanName
     * @param bean
     * @return
     */
    @Override
    public boolean registerComponent(String beanName, Object bean) {
        return false;
    }

    @Override
    public boolean disRegisterComponent(String beanName) {
        return false;
    }

    @Override
    public void close() {

    }

    /**
     * subClass implement
     */

    /**
     * 获取 bean Names
     *
     * @return
     */
    protected abstract List<String> listComponentNames();

    /**
     * 通过 bean names 获取初步的组件bean
     *
     * @param name
     * @return
     */
    protected abstract Object getResolvedComponent(String name);


}
