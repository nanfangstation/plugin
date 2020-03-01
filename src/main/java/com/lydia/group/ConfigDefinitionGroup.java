package com.lydia.group;

import com.lydia.annotation.ConfigDefinition;
import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;

/**
 * 分组存在注解: @ConfigDefinition
 *
 * @author Lydia
 * @version 2.1.0
 */
public class ConfigDefinitionGroup implements PluginClassGroup {

    /**
     * 自定义插件配置文件bean @ConfigDefinition
     */
    public static final String GROUP_ID= "config_definition";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, ConfigDefinition.class);
    }

}
