package com.lydia;

import com.lydia.listener.PluginListenerContext;
import org.pf4j.PluginWrapper;

/**
 * @author Lydia
 * @since 2020-03-01 下午3:18
 **/
public interface PluginFactory extends PluginListenerContext {

    /**
     * 工厂初始化
     *
     * @throws Exception 初始化异常
     */
    void initialize() throws Exception;

    /**
     * 注册插件。
     *
     * @param pluginWrapper 插件
     * @return 插件工厂
     * @throws Exception 插件工厂异常
     */
    PluginFactory registry(PluginWrapper pluginWrapper) throws Exception;

    /**
     * 注销插件。
     *
     * @param pluginId 插件id
     * @return 插件工厂
     * @throws Exception 插件工厂异常
     */
    PluginFactory unRegistry(String pluginId) throws Exception;

    /**
     * 注册或者注销后的构建调用
     *
     * @throws Exception 插件工厂异常
     */
    void build() throws Exception;
}
