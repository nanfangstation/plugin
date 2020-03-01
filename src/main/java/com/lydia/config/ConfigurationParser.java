package com.lydia.config;

import com.lydia.base.BasePlugin;

/**
 * 配置解析
 *
 * @author Lydia
 * @since 2020-03-01 下午4:42
 **/
public interface ConfigurationParser {

    /**
     * 配置解析
     * @param basePlugin 插件信息
     * @param pluginConfigDefinition 插件配置定义
     * @return 解析后映射值的对象
     * @throws Exception 抛出配置解析异常
     */
    Object parse(BasePlugin basePlugin, PluginConfigDefinition pluginConfigDefinition) throws Exception;
}
