package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.base.OneselfListener;
import org.springframework.util.ClassUtils;

import java.util.Set;

/**
 * 插件自己的监听器分组
 *
 * @author Lydia
 * @version 2.2.1
 */
public class OneselfListenerGroup implements PluginClassGroup {

    public static final String GROUP_ID = "oneself_listener";

    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        if (aClass == null) {
            return false;
        }
        Set<Class<?>> allInterfacesForClassAsSet = ClassUtils.getAllInterfacesForClassAsSet(aClass);
        return allInterfacesForClassAsSet.contains(OneselfListener.class);
    }
}
