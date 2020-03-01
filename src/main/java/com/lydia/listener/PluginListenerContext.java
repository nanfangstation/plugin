package com.lydia.listener;

import java.util.List;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:56
 **/
public interface PluginListenerContext {

    /**
     * 添加监听者
     *
     * @param pluginListener 插件 bean 监听者
     */
    void addListener(PluginListener pluginListener);


    /**
     * 添加监听者
     *
     * @param pluginListenerClass 插件监听者Class类
     * @param <T>                 继承PluginListener的子类
     */
    <T extends PluginListener> void addListener(Class<T> pluginListenerClass);

    /**
     * 追加多个监听者
     *
     * @param pluginListeners 插件 bean 监听者集合
     */
    void addListener(List<PluginListener> pluginListeners);
}
