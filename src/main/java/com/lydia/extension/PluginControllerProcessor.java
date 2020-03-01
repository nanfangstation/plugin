package com.lydia.extension;


/**
 * controller 处理者
 *
 * @author Lydia
 * @version 1.0
 */
public interface PluginControllerProcessor {

    /**
     * 注册
     *
     * @param pluginId        插件id
     * @param controllerClass controller 类
     * @throws Exception 异常
     */
    void registry(String pluginId, Class controllerClass) throws Exception;

    /**
     * 注册
     *
     * @param pluginId        插件id
     * @param controllerClass controller 类
     * @throws Exception 异常
     */
    void unRegistry(String pluginId, Class controllerClass) throws Exception;
}
