package com.lydia.extension;

import com.lydia.application.PluginApplication;
import com.lydia.group.PluginClassGroupExtend;
import com.lydia.loader.PluginResourceLoader;
import com.lydia.pipe.PluginPipeProcessorExtend;
import com.lydia.post.PluginPostProcessorExtend;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:56
 **/
public abstract class AbstractExtension {

    protected PluginApplication pluginApplication;


    public void setPluginApplication(PluginApplication pluginApplication) {
        this.pluginApplication = pluginApplication;
    }

    /**
     * 扩展key
     *
     * @return String
     */
    public abstract String key();

    /**
     * 该扩展初始化的操作
     *
     * @throws Exception 初始化异常
     */
    public void initialize(ApplicationContext applicationContext) throws Exception {
    }


    /**
     * 得到插件的资源加载者
     *
     * @return List PluginResourceLoader
     */
    public List<PluginResourceLoader> getPluginResourceLoader() {
        return null;
    }

    /**
     * 得到扩展的插件中的类分组器
     *
     * @param applicationContext 主程序ApplicationContext
     * @return List PluginPipeProcessorExtend
     */
    public List<PluginClassGroupExtend> getPluginClassGroup(ApplicationContext applicationContext) {
        return null;
    }


    /**
     * 得到扩展的流插件处理者
     *
     * @param applicationContext 主程序ApplicationContext
     * @return List PluginPipeProcessorExtend
     */
    public List<PluginPipeProcessorExtend> getPluginPipeProcessor(ApplicationContext applicationContext) {
        return null;
    }

    /**
     * 得到扩展的插件后置处理者
     *
     * @param applicationContext 主程序ApplicationContext
     * @return List PluginPostProcessorExtend
     */
    public List<PluginPostProcessorExtend> getPluginPostProcessor(ApplicationContext applicationContext) {
        return null;
    }
}
