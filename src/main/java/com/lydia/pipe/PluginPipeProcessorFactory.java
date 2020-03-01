package com.lydia.pipe;

import com.lydia.extension.ExtensionInitializer;
import com.lydia.group.PluginClassProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件的pipe处理者工厂
 *
 * @author Lydia
 * @since 2020-03-01 下午3:25
 **/
public class PluginPipeProcessorFactory implements PluginPipeProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ApplicationContext applicationContext;
    private final List<PluginPipeProcessor> pluginPipeProcessors = new ArrayList<>();

    public PluginPipeProcessorFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public void initialize() throws Exception {
        // OneselfListenerStopEventProcessor 触发停止事件, 必须第一个执行
        pluginPipeProcessors.add(new OneselfListenerStopEventProcessor(applicationContext));
        pluginPipeProcessors.add(new PluginClassProcess());
        // 配置文件在所有bean中第一个初始化。
        pluginPipeProcessors.add(new ConfigFileBeanProcessor(applicationContext));
        // 接下来初始化插件中配置bean的初始化
        pluginPipeProcessors.add(new ConfigBeanProcessor(applicationContext));
        pluginPipeProcessors.add(new BasicBeanProcessor(applicationContext));
        // 添加扩展
        pluginPipeProcessors.addAll(ExtensionInitializer.getPipeProcessorExtends());

        // 进行初始化
        for (PluginPipeProcessor pluginPipeProcessor : pluginPipeProcessors) {
            pluginPipeProcessor.initialize();
        }
    }

    @Override
    public void registry(PluginRegistryInfo pluginRegistryInfo) throws Exception {
        for (PluginPipeProcessor pluginPipeProcessor : pluginPipeProcessors) {
            pluginPipeProcessor.registry(pluginRegistryInfo);
        }
    }

    @Override
    public void unRegistry(PluginRegistryInfo pluginRegistryInfo) throws Exception {
        for (PluginPipeProcessor pluginPipeProcessor : pluginPipeProcessors) {
            pluginPipeProcessor.unRegistry(pluginRegistryInfo);
        }
    }
}
