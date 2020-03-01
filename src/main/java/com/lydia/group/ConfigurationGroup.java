package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;
import org.springframework.context.annotation.Configuration;

/**
 * 分组存在注解: @Configuration
 *
 * @author Lydia
 * @version 2.1.0
 */
public class ConfigurationGroup implements PluginClassGroup {

    /**
     * spring @CONFIGURATION 注解bean
     */
    public static final String GROUP_ID = "spring_configuration";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, Configuration.class);
    }
}
