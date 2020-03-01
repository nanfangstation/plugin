package com.lydia.listener;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:21
 **/
public interface PluginInitializerListener {

    /**
     * 初始化之前
     */
    void before();


    /**
     * 初始化完成
     */
    void complete();

    /**
     * 初始化失败
     * @param throwable 异常
     */
    void failure(Throwable throwable);
}
