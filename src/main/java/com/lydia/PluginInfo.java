package com.lydia;

import org.pf4j.PluginDescriptor;
import org.pf4j.PluginState;

/**
 * 插件信息
 *
 * @author Lydia
 * @since 2020-03-01 下午2:25
 **/
public class PluginInfo {

    /**
     * 插件基本信息
     */
    private PluginDescriptor pluginDescriptor;

    /**
     * 插件状态
     */
    private PluginState pluginState;

    /**
     * 插件路径
     */
    private String path;

    public PluginInfo(PluginDescriptor pluginDescriptor, PluginState pluginState, String path) {
        this.pluginDescriptor = pluginDescriptor;
        this.pluginState = pluginState;
        this.path = path;
    }
}
