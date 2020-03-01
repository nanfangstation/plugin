package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 分组存在注解: Component、Service
 *
 * @author Lydia
 * @version 2.1.0
 */
public class ComponentGroup implements PluginClassGroup {

    /**
     * spring 组件bean.
     * 包括Component、Service
     */
    public static final String GROUP_ID = "spring_component";

    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, Component.class, Service.class);
    }
}
