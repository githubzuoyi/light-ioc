package org.apache.dependencies.inject.context.resource;

import org.apache.dependencies.inject.context.AbstractComponentContext;

import java.util.List;

/**
 *
 * 外部化文件方式组件上下文
 *
 * @author: feizuo
 * @since: 1.0.0
 */
public class ResourceComponentContext extends AbstractComponentContext {

    @Override
    protected List<String> listComponentNames() {
        return null;
    }

    @Override
    protected Object getResolvedComponent(String name) {
        return null;
    }
}
