package com.lydia.config;

import java.util.Objects;

/**
 * @author Lydia
 * @since 2020-03-01 下午4:42
 **/
public class PluginConfigDefinition {

    /**
     *  插件中的配置文件名称
     */
    private final String fileName;

    /**
     * 配置文件实现类的Class定义
     */
    private final Class<?> configClass;



    public PluginConfigDefinition(String fileName, Class<?> configClass) {
        this.fileName = fileName;
        this.configClass = configClass;
    }

    public String getFileName() {
        return fileName;
    }

    public Class<?> getConfigClass() {
        return configClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof PluginConfigDefinition)){
            return false;
        }
        PluginConfigDefinition that = (PluginConfigDefinition) o;
        return getFileName().equals(that.getFileName()) &&
                getConfigClass().equals(that.getConfigClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getConfigClass());
    }
}
