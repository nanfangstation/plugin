package com.lydia.pipe;

/**
 * 插件管道处理者接口
 *
 * @author Lydia
 * @since 2020-03-01 下午2:51
 **/
public interface PluginPipeProcessor {

    /**
     * 初始化
     * @throws Exception 初始化异常
     */
    void initialize() throws Exception;


    /**
     * 处理该插件的注册
     * @param pluginRegistryInfo 插件注册的信息
     * @throws Exception 处理异常
     */
    void registry(PluginRegistryInfo pluginRegistryInfo) throws Exception;


    /**
     * 处理该插件的卸载
     * @param pluginRegistryInfo 插件注册的信息
     * @throws Exception 处理异常
     */
    void unRegistry(PluginRegistryInfo pluginRegistryInfo) throws Exception;
}
