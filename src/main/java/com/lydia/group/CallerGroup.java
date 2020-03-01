package com.lydia.group;

import com.lydia.annotation.Caller;
import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;

/**
 * 分组存在注解: @Caller
 *
 * @author Lydia
 * @version 2.1.0
 */
public class CallerGroup implements PluginClassGroup {


    /**
     * 自定义 @Caller
     */
    public static final String GROUP_ID = "caller";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, Caller.class);
    }

}
