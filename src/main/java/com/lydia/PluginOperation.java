package com.lydia;

import com.lydia.listener.PluginInitializerListener;
import org.pf4j.PluginWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Lydia
 * @since 2020-03-01 下午2:20
 **/
public interface PluginOperation {

    boolean initPlugins(PluginInitializerListener pluginInitializerListener) throws Exception;

    boolean install(Path path) throws Exception;

    boolean uninstall(String pluginId, boolean isBackup) throws Exception;

    boolean start(String pluginId) throws Exception;

    boolean stop(String pluginId) throws Exception;

    boolean uploadPluginAndStart(MultipartFile pluginFile) throws Exception;

    List<PluginWrapper> getPluginWrapper();

    PluginWrapper getPluginWrapper(String pluginId);
}
