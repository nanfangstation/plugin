package com.lydia.post;

import com.lydia.pipe.PluginRegistryInfo;

import java.util.List;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:54
 **/
public interface PluginPostProcessor {

    /**
     * 初始化
     * @throws Exception 初始化异常
     */
    void initialize() throws Exception;


    /**
     * 处理该插件的注册
     * @param pluginRegistryInfos 插件注册的信息
     * @throws Exception 处理异常
     */
    void registry(List<PluginRegistryInfo> pluginRegistryInfos) throws Exception;


    /**
     * 处理该插件的卸载
     * @param pluginRegistryInfos 插件注册的信息
     * @throws Exception 处理异常
     */
    void unRegistry(List<PluginRegistryInfo> pluginRegistryInfos) throws Exception;
}
