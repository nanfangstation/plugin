package com.lydia.application;

import com.lydia.DefaultPluginOperation;
import com.lydia.IntegrationConfiguration;
import com.lydia.PluginOperation;
import com.lydia.listener.PluginInitializerListener;
import com.lydia.pf4j.DefaultPf4jFactory;
import com.lydia.pf4j.Pf4jFactory;
import com.lydia.user.DefaultPluginUser;
import com.lydia.user.PluginUser;
import org.pf4j.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * 默认的插件 PluginApplication
 *
 * @author Lydia
 * @version 2.2.0
 */
public class DefaultPluginApplication extends AbstractPluginApplication {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected Pf4jFactory integrationFactory;

    private PluginUser pluginUser;
    private PluginOperation pluginOperator;

    private AtomicBoolean beInitialized = new AtomicBoolean(false);

    public DefaultPluginApplication() {
    }

    public DefaultPluginApplication(Pf4jFactory integrationFactory) {
        this.integrationFactory = integrationFactory;
    }


    @Override
    public synchronized void initialize(ApplicationContext applicationContext,
                                        PluginInitializerListener listener) {
        Objects.requireNonNull(applicationContext, "ApplicationContext can't be null");
        if (beInitialized.get()) {
            throw new RuntimeException("Plugin has been initialized");
        }
        IntegrationConfiguration configuration = getConfiguration(applicationContext);
        if (integrationFactory == null) {
            integrationFactory = new DefaultPf4jFactory(configuration);
        }
        PluginManager pluginManager = integrationFactory.getPluginManager();
        pluginUser = createPluginUser(applicationContext, pluginManager);
        pluginOperator = createPluginOperator(applicationContext, pluginManager, configuration);
        try {
            pluginOperator.initPlugins(listener);
            beInitialized.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建插件使用者。子类可扩展
     *
     * @param applicationContext Spring ApplicationContext
     * @param pluginManager      插件管理器
     * @return PluginUser
     */
    protected PluginUser createPluginUser(ApplicationContext applicationContext,
                                          PluginManager pluginManager) {
        return new DefaultPluginUser(applicationContext, pluginManager);
    }

    /**
     * 创建插件操作者。子类可扩展
     *
     * @param applicationContext Spring ApplicationContext
     * @param pluginManager      插件管理器
     * @param configuration      当前集成的配置
     * @return PluginOperator
     */
    protected PluginOperation createPluginOperator(ApplicationContext applicationContext,
                                                   PluginManager pluginManager,
                                                   IntegrationConfiguration configuration) {
        return new DefaultPluginOperation(
                applicationContext,
                configuration,
                pluginManager,
                this.listenerFactory
        );
    }


    @Override
    public PluginOperation getPluginOperation() {
        assertInjected();
        return pluginOperator;
    }

    @Override
    public PluginUser getPluginUser() {
        assertInjected();
        return pluginUser;
    }

    /**
     * 检查注入
     */
    private void assertInjected() {
        if (this.pluginUser == null) {
            throw new RuntimeException("PluginUser is null, Please check whether the DefaultPluginApplication is injected");
        }
        if (this.pluginOperator == null) {
            throw new RuntimeException("PluginOperator is null, Please check whether the DefaultPluginApplication is injected");
        }
    }


}
