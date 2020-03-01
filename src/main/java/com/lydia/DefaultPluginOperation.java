package com.lydia;

import com.lydia.listener.PluginInitializerListener;
import com.lydia.listener.PluginInitializerListenerFactory;
import com.lydia.listener.PluginListenerFactory;
import com.lydia.util.GlobalRegistryInfo;
import com.lydia.util.PluginFileUtil;
import com.lydia.util.PluginOperationInfo;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:37
 **/
public class DefaultPluginOperation implements PluginOperation {

    private boolean isInit = false;

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected final IntegrationConfiguration integrationConfiguration;
    protected final PluginManager pluginManager;
    protected final PluginFactory pluginFactory;
    protected final PluginInitializerListenerFactory pluginInitializerListenerFactory;

    public DefaultPluginOperation(ApplicationContext applicationContext,
                                  IntegrationConfiguration integrationConfiguration,
                                  PluginManager pluginManager,
                                  PluginListenerFactory pluginListenerFactory) {
        Objects.requireNonNull(integrationConfiguration, "IntegrationConfiguration can't be null");
        Objects.requireNonNull(pluginManager, "PluginManager can't be null");
        this.integrationConfiguration = integrationConfiguration;
        this.pluginManager = pluginManager;
        this.pluginFactory = new DefaultPluginFactory(applicationContext, pluginListenerFactory);
        this.pluginInitializerListenerFactory = new PluginInitializerListenerFactory(applicationContext);

//        this.pluginLegalVerify = new DefaultPluginVerify(pluginManager);
    }

    @Override
    public boolean initPlugins(PluginInitializerListener pluginInitializerListener) throws Exception {
        if (isInit) {
            throw new RuntimeException("Plugins Already initialized. Cannot be initialized again");
        }
        try {
            // 启动前, 清除空文件
            PluginFileUtil.cleanEmptyFile(pluginManager.getPluginsRoot());

            pluginInitializerListenerFactory.addPluginInitializerListeners(pluginInitializerListener);
            log.info("Plugins start initialize of root path '{}'", pluginManager.getPluginsRoot().toString());
            // 触发插件初始化监听器
            pluginInitializerListenerFactory.before();
            // 开始初始化插件工厂
            pluginFactory.initialize();
            // 开始加载插件
            pluginManager.loadPlugins();
            pluginManager.startPlugins();
            List<PluginWrapper> pluginWrappers = pluginManager.getStartedPlugins();
            if (pluginWrappers == null || pluginWrappers.isEmpty()) {
                log.warn("Not found plugin!");
                return false;
            }
            boolean isFoundException = false;
            for (PluginWrapper pluginWrapper : pluginWrappers) {
                String pluginId = pluginWrapper.getPluginId();
                GlobalRegistryInfo.addOperatorPluginInfo(pluginId,
                        PluginOperationInfo.OperationType.INSTALL, false);
                try {
                    // 依次注册插件信息到Spring boot
                    pluginFactory.registry(pluginWrapper);
                } catch (Exception e) {
                    log.error("Plugin '{}' registry failure. Reason : {}", pluginId, e.getMessage(), e);
                    isFoundException = true;
                }
            }
            pluginFactory.build();
            isInit = true;
            if (isFoundException) {
                log.error("Plugins initialize failure");
                return false;
            } else {
                log.info("Plugins initialize success");
                pluginInitializerListenerFactory.complete();
                return true;
            }
        } catch (Exception e) {
            pluginInitializerListenerFactory.failure(e);
            throw e;
        }
    }

    @Override
    public boolean install(Path path) throws Exception {
        return false;
    }

    @Override
    public boolean uninstall(String pluginId, boolean isBackup) throws Exception {
        return false;
    }

    @Override
    public boolean start(String pluginId) throws Exception {
        return false;
    }

    @Override
    public boolean stop(String pluginId) throws Exception {
        return false;
    }

    @Override
    public boolean uploadPluginAndStart(MultipartFile pluginFile) throws Exception {
        return false;
    }

    @Override
    public List<PluginWrapper> getPluginWrapper() {
        return null;
    }

    @Override
    public PluginWrapper getPluginWrapper(String pluginId) {
        return null;
    }
}
