package com.lydia.util;

import java.lang.annotation.Annotation;

/**
 * @author Lydia
 * @since 2020-03-01 下午4:36
 **/
public class AnnotationsUtil {

    private AnnotationsUtil() {

    }


    /**
     * 存在注解判断
     *
     * @param aClass            类
     * @param isAllMatch        是否匹配全部注解
     * @param annotationClasses 注解类
     * @return boolean
     */
    public static boolean haveAnnotations(Class<?> aClass, boolean isAllMatch,
                                          Class<? extends Annotation>... annotationClasses) {
        if (aClass == null) {
            return false;
        }
        if (annotationClasses == null) {
            return false;
        }
        for (Class<? extends Annotation> annotationClass : annotationClasses) {
            Annotation annotation = aClass.getAnnotation(annotationClass);
            if (isAllMatch) {
                if (annotation == null) {
                    return false;
                }
            } else {
                if (annotation != null) {
                    return true;
                }
            }
        }
        if (isAllMatch) {
            return true;
        } else {
            return false;
        }
    }
}
