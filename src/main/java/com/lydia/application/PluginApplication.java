package com.lydia.application;

import com.lydia.PluginOperation;
import com.lydia.extension.AbstractExtension;
import com.lydia.listener.PluginInitializerListener;
import com.lydia.listener.PluginListenerContext;
import com.lydia.user.PluginUser;
import org.springframework.context.ApplicationContext;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:58
 **/
public interface PluginApplication extends PluginListenerContext {

    /**
     * 初始化
     *
     * @param applicationContext Spring上下文
     * @param listener           插件初始化监听者
     */
    void initialize(ApplicationContext applicationContext, PluginInitializerListener listener);


    /**
     * 获得插插件操作者
     *
     * @return 插件操作者
     */
    PluginOperation getPluginOperation();

    /**
     * 获得插插件操作者
     * @return 插件操作者
     */
    PluginUser getPluginUser();

    /**
     * 添加扩展
     *
     * @param extension 扩展类
     */
    void addExtension(AbstractExtension extension);
}
