package com.lydia.listener;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:57
 **/
public interface PluginListener {

    /**
     * 注册插件
     * @param pluginId 插件id
     */
    void registry(String pluginId);

    /**
     * 卸载插件
     * @param pluginId 插件id
     */
    void unRegistry(String pluginId);

    /**
     * 失败监听
     * @param pluginId 插件id
     * @param throwable 异常信息
     */
    void failure(String pluginId, Throwable throwable);
}
