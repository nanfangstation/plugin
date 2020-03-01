package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.extension.ExtensionInitializer;
import com.lydia.group.PluginClassGroup;
import com.lydia.loader.PluginClassLoader;
import com.lydia.loader.PluginResourceLoadFactory;
import com.lydia.loader.ResourceWrapper;
import com.lydia.pipe.PluginPipeProcessor;
import com.lydia.pipe.PluginRegistryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 插件类加载处理者
 *
 * @author Lydia
 * @since 2020-03-01 下午4:33
 **/
public class PluginClassProcess implements PluginPipeProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 其他类
     */
    public static final String OTHER = "other";


    private final List<PluginClassGroup> pluginClassGroups = new ArrayList<>();


    public PluginClassProcess() {
    }


    @Override
    public void initialize() {
//        pluginClassGroups.add(new ComponentGroup());
//        pluginClassGroups.add(new ControllerGroup());
//        pluginClassGroups.add(new RepositoryGroup());
//        pluginClassGroups.add(new ConfigurationGroup());
//        pluginClassGroups.add(new ConfigDefinitionGroup());
//        pluginClassGroups.add(new ConfigBeanGroup());
//        pluginClassGroups.add(new SupplierGroup());
//        pluginClassGroups.add(new CallerGroup());
//        pluginClassGroups.add(new OneselfListenerGroup());
//        // 添加扩展
        pluginClassGroups.addAll(ExtensionInitializer.getClassGroupExtends());
    }

    @Override
    public void registry(PluginRegistryInfo pluginRegistryInfo) throws Exception {
        BasePlugin basePlugin = pluginRegistryInfo.getBasePlugin();
        PluginResourceLoadFactory pluginResourceLoadFactory = basePlugin.getBasePluginExtend()
                .getPluginResourceLoadFactory();
        ResourceWrapper resourceWrapper = pluginResourceLoadFactory.getPluginResources(PluginClassLoader.KEY);
        if (resourceWrapper == null) {
            return;
        }
        List<Resource> pluginResources = resourceWrapper.getResources();
        if (pluginResources == null) {
            return;
        }
        for (PluginClassGroup pluginClassGroup : pluginClassGroups) {
            try {
                pluginClassGroup.initialize(basePlugin);
            } catch (Exception e) {
                log.error("PluginClassGroup {} initialize exception. {}", pluginClassGroup.getClass(),
                        e.getMessage(), e);
            }
        }
        Set<String> classPackageNames = resourceWrapper.getClassPackageNames();
        for (String classPackageName : classPackageNames) {
            Class<?> aClass = Class.forName(classPackageName, false,
                    basePlugin.getWrapper().getPluginClassLoader());
            if (aClass == null) {
                continue;
            }
            boolean findGroup = false;
            for (PluginClassGroup pluginClassGroup : pluginClassGroups) {
                if (pluginClassGroup == null || StringUtils.isEmpty(pluginClassGroup.groupId())) {
                    continue;
                }
                if (pluginClassGroup.filter(aClass)) {
                    pluginRegistryInfo.addGroupClasses(pluginClassGroup.groupId(), aClass);
                    findGroup = true;
                }
            }
            if (!findGroup) {
                pluginRegistryInfo.addGroupClasses(OTHER, aClass);
            }
            pluginRegistryInfo.addClasses(aClass);
        }
    }

    @Override
    public void unRegistry(PluginRegistryInfo registerPluginInfo) throws Exception {
        registerPluginInfo.cleanClasses();
    }
}
