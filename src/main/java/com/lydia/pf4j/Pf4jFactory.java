package com.lydia.pf4j;

import org.pf4j.PluginManager;

/**
 * Pf4j 集成工厂。获取Pf4j的PluginManager对象
 * @author Lydia
 * @since 2020-03-01 下午5:04
 **/
public interface Pf4jFactory {

    /**
     * 得到插件管理者
     * @return 插件管理者
     */
    PluginManager getPluginManager();
}
