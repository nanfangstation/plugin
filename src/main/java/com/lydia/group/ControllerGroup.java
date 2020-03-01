package com.lydia.group;

import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分组存在注解: @Controller、@RestController
 *
 * @author Lydia
 * @version 2.1.0
 */
public class ControllerGroup implements PluginClassGroup {


    /**
     * spring @Controller @RestController 注解bean
     */
    public static final String GROUP_ID = "spring_controller";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, RestController.class, Controller.class);
    }
}
