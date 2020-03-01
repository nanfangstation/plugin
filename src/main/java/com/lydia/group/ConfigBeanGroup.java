package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.base.ConfigBean;
import org.springframework.util.ClassUtils;

import java.util.Set;

/**
 * 对接口ConfigBean实现的类分组
 *
 * @author Lydia
 * @version 2.2.2
 * @see ConfigBean
 */
public class ConfigBeanGroup implements PluginClassGroup {


    public static final String GROUP_ID = "config_bean";


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
        return allInterfacesForClassAsSet.contains(ConfigBean.class);
    }

}
