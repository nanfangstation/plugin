package com.lydia.post;

import com.lydia.application.PluginApplication;
import com.lydia.base.BasePlugin;
import com.lydia.base.OneselfListener;
import com.lydia.pipe.PluginRegistryInfo;
import com.lydia.user.PluginUser;
import com.lydia.util.AopUtil;
import com.lydia.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Objects;

/**
 * 插件中 OneselfListener 监听者处理者。主要执行监听器的启动事件。
 *
 * @author Lydia
 * @version 2.1.0
 * @see OneselfListenerStopEventProcessor 触发停止事件
 */
public class PluginOneselfStartEventProcessor implements PluginPostProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PluginUser pluginUser;

    public PluginOneselfStartEventProcessor(ApplicationContext applicationContext) {
        Objects.requireNonNull(applicationContext);
        PluginApplication pluginApplication = applicationContext.getBean(PluginApplication.class);
        this.pluginUser = pluginApplication.getPluginUser();
    }


    @Override
    public void initialize() throws Exception {

    }

    @Override
    public void registry(List<PluginRegistryInfo> pluginRegistryInfos) throws Exception {
        for (PluginRegistryInfo pluginRegistryInfo : pluginRegistryInfos) {
            AopUtil.resolveAop(pluginRegistryInfo.getPluginWrapper());
            try {
                BasePlugin basePlugin = pluginRegistryInfo.getBasePlugin();
                String pluginId = basePlugin.getWrapper().getPluginId();

                List<OneselfListener> oneselfListeners = pluginUser.getPluginBeans(pluginId, OneselfListener.class);
                oneselfListeners.stream()
                        .sorted(CommonUtil.orderPriority(oneselfListener -> oneselfListener.order()))
                        .forEach(oneselfListener -> {
                            try {
                                oneselfListener.startEvent(basePlugin);
                            } catch (Exception e) {
                                log.error("OneselfListener {} execute startEvent exception. {}",
                                        oneselfListener.getClass().getName(), e.getMessage(), e);
                            }
                        });
            } finally {
                AopUtil.recoverAop();
            }
        }
    }


    @Override
    public void unRegistry(List<PluginRegistryInfo> pluginRegistryInfos) {
        // 此处不卸载调用。
    }


}
