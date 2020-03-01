package com.lydia.group;

import com.lydia.annotation.Supplier;
import com.lydia.base.BasePlugin;
import com.lydia.util.AnnotationsUtil;


/**
 * 分组存在注解: @Supplier
 *
 * @author Lydia
 * @version 2.1.0
 */
public class SupplierGroup implements PluginClassGroup {

    /**
     * 自定义 @Supplier
     */
    public static final String GROUP_ID = "supplier";


    @Override
    public String groupId() {
        return GROUP_ID;
    }

    @Override
    public void initialize(BasePlugin basePlugin) {

    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtil.haveAnnotations(aClass, false, Supplier.class);
    }

}
