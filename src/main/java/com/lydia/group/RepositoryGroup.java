package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;
import org.springframework.stereotype.Repository;

/**
 * 分组存在注解: @Repository
 *
 * @author Lydia
 * @version 2.1.0
 */
public class RepositoryGroup implements PluginClassGroup {

    /**
     * spring @Repository 注解bean
     */
    public static final String GROUP_ID = "spring_repository";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, Repository.class);
    }
}
