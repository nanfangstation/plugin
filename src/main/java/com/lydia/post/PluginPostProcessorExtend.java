package com.lydia.post;

import com.lydia.util.OrderPriority;

/**
 * 后置插件处理者
 *
 * @author Lydia
 * @since 2020-03-01 下午2:55
 **/
public interface PluginPostProcessorExtend extends PluginPostProcessor {

    /**
     * 扩展key
     * @return String
     */
    String key();

    /**
     * 执行顺序
     * @return OrderPriority
     */
    OrderPriority order();
}
