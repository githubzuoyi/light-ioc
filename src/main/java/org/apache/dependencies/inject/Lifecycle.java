package org.apache.dependencies.inject;

/**
 *
 * Ioc 生命周期接口
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public interface Lifecycle {

    void initial();

    void close();

}
