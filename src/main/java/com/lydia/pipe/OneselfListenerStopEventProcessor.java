package com.lydia.pipe;

import com.lydia.application.PluginApplication;
import com.lydia.base.BasePlugin;
import com.lydia.base.OneselfListener;
import com.lydia.user.PluginUser;
import com.lydia.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Objects;

/**
 * 执行插件自监听器的停止事件的处理者。必须在所有处理者中第一个执行。否则会导致所依赖的bean被卸载。
 *
 * @author Lydia
 * @since 2020-03-01 下午3:28
 **/
public class OneselfListenerStopEventProcessor implements PluginPipeProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PluginUser pluginUser;

    public OneselfListenerStopEventProcessor(ApplicationContext applicationContext) {
        Objects.requireNonNull(applicationContext);
        PluginApplication pluginApplication = applicationContext.getBean(PluginApplication.class);
        this.pluginUser = pluginApplication.getPluginUser();
    }


    @Override
    public void initialize() throws Exception {

    }

    @Override
    public void registry(PluginRegistryInfo pluginRegistryInfo) throws Exception {
    }

    @Override
    public void unRegistry(PluginRegistryInfo pluginRegistryInfo) throws Exception {
        BasePlugin basePlugin = pluginRegistryInfo.getBasePlugin();
        String pluginId = basePlugin.getWrapper().getPluginId();
        List<OneselfListener> oneselfListeners = pluginUser.getPluginBeans(pluginId, OneselfListener.class);
        oneselfListeners.stream()
                .sorted(CommonUtil.orderPriority(oneselfListener -> oneselfListener.order()))
                .forEach(oneselfListener -> {
                    try {
                        oneselfListener.stopEvent(basePlugin);
                    } catch (Exception e) {
                        log.error("OneselfListener {} execute stopEvent exception. {}",
                                oneselfListener.getClass().getName(), e.getMessage(), e);
                    }
                });
    }
}
