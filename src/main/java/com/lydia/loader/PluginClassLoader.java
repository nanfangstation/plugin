package com.lydia.loader;

import com.lydia.base.BasePlugin;
import com.lydia.util.OrderPriority;
import com.lydia.util.ScanUtil;
import org.pf4j.RuntimeMode;

import java.util.Set;

/**
 * 插件类文件加载者
 *
 * @author Lydia
 * @since 2020-03-01 下午2:46
 **/
public class PluginClassLoader implements PluginResourceLoader {

    public static final String KEY = "PluginClassProcess";

    @Override
    public String key() {
        return KEY;
    }

    @Override
    public ResourceWrapper load(BasePlugin basePlugin) throws Exception {
        RuntimeMode runtimeMode = basePlugin.getWrapper().getRuntimeMode();
        Set<String> classPackageName = null;
        if (runtimeMode == RuntimeMode.DEPLOYMENT) {
            // 生产环境
            classPackageName = ScanUtil.scanClassPackageName(
                    basePlugin.scanPackage(), basePlugin.getWrapper().getPluginClassLoader());

        } else if (runtimeMode == RuntimeMode.DEVELOPMENT) {
            // 开发环境
            classPackageName = ScanUtil.scanClassPackageName(
                    basePlugin.scanPackage(), basePlugin.getClass());
        }
        ResourceWrapper resourceWrapper = new ResourceWrapper();
        resourceWrapper.addClassPackageNames(classPackageName);
        return resourceWrapper;
    }

    @Override
    public void unload(BasePlugin basePlugin, ResourceWrapper resourceWrapper) throws Exception {
        // Do nothing
    }

    @Override
    public OrderPriority order() {
        return OrderPriority.getHighPriority();
    }
}
