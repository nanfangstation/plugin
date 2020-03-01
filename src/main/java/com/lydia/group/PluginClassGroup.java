package com.lydia.group;

import com.lydia.base.BasePlugin;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:53
 **/
public interface PluginClassGroup {

    /**
     * 组id
     * @return 组id
     */
    String groupId();

    /**
     * 初始化。每处理一个插件, 该方法调用一次。
     * @param basePlugin 当前插件信息
     */
    void initialize(BasePlugin basePlugin);


    /**
     * 过滤类。
     * @param aClass 类
     * @return 返回true.说明符合该分组器。false不符合该分组器
     */
    boolean filter(Class<?> aClass);
}
