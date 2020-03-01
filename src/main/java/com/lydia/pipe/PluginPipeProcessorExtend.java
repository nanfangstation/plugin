package com.lydia.pipe;

import com.lydia.util.OrderPriority;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:52
 **/
public interface PluginPipeProcessorExtend extends PluginPipeProcessor {

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
