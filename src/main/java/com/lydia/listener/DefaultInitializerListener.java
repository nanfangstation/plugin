package com.lydia.listener;

import com.lydia.extension.ExtensionInitializer;
import org.springframework.context.ApplicationContext;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:40
 **/
public class DefaultInitializerListener implements PluginInitializerListener {

    public final ApplicationContext applicationContext;

    public DefaultInitializerListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public void before() {
        // 初始化扩展注册信息
        ExtensionInitializer.initialize(applicationContext);
    }

    @Override
    public void complete() {

    }

    @Override
    public void failure(Throwable throwable) {

    }
}
