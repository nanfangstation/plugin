package com.lydia.post;

import com.lydia.extension.ExtensionInitializer;
import com.lydia.pipe.PluginRegistryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lydia
 * @since 2020-03-01 下午3:27
 **/
public class PluginPostProcessorFactory implements PluginPostProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final List<PluginPostProcessor> pluginPostProcessors = new ArrayList<>();
    private final ApplicationContext applicationContext;

    public PluginPostProcessorFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize() throws Exception {

        // 添加扩展
        pluginPostProcessors.addAll(ExtensionInitializer.getPostProcessorExtends());

        // 以下顺序不能更改。
        pluginPostProcessors.add(new PluginConfigurationPostProcessor(applicationContext));
        pluginPostProcessors.add(new PluginInvokePostProcessor(applicationContext));

        pluginPostProcessors.add(new PluginControllerPostProcessor(applicationContext));
        // 主要触发启动监听事件，因此在最后一个执行。配合 OneselfListenerStopEventProcessor 该类触发启动、停止事件。
        pluginPostProcessors.add(new PluginOneselfStartEventProcessor(applicationContext));


        // 进行初始化
        for (PluginPostProcessor pluginPostProcessor : pluginPostProcessors) {
            pluginPostProcessor.initialize();
        }
    }


    @Override
    public void registry(List<PluginRegistryInfo> pluginRegistryInfos) throws Exception {
        for (PluginPostProcessor pluginPostProcessor : pluginPostProcessors) {
            pluginPostProcessor.registry(pluginRegistryInfos);
        }
    }

    @Override
    public void unRegistry(List<PluginRegistryInfo> pluginRegistryInfos) throws Exception {
        for (PluginPostProcessor pluginPostProcessor : pluginPostProcessors) {
            pluginPostProcessor.unRegistry(pluginRegistryInfos);
        }
    }
}
