package com.lydia.loader;

import com.lydia.base.BasePlugin;
import com.lydia.util.OrderPriority;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:42
 **/
public interface PluginResourceLoader {

    /**
     * 加载者的key
     * @return String
     */
    String key();


    /**
     * 加载资源
     * @param basePlugin 插件对象
     * @return 资源包装对象
     * @throws Exception Exception
     */
    ResourceWrapper load(BasePlugin basePlugin) throws Exception;

    /**
     * 卸载时的操作
     * @param basePlugin 插件对象
     * @param resourceWrapper 资源包装者
     * @throws Exception 卸载异常
     */
    void unload(BasePlugin basePlugin, ResourceWrapper resourceWrapper) throws Exception;


    /**
     * 执行顺序
     * @return OrderPriority
     */
    OrderPriority order();
}
