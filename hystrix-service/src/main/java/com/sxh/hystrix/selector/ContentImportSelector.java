package com.sxh.hystrix.selector;

import com.sxh.hystrix.annotation.EnableContentService;
import com.sxh.hystrix.service.impl.CoreContentService;
import com.sxh.hystrix.service.impl.SimpleContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 根据EnableContentService注解里的policy加载不同的bean
 * @author sxh
 * @date 2020/3/27
 */
public class ContentImportSelector implements ImportSelector {
    private Logger logger = LoggerFactory.getLogger(ContentImportSelector.class);
    
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Class<EnableContentService> annotationType = EnableContentService.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(annotationType.getName(), false));
        String policy = attributes.getString("policy");
        
        // 注入ContentService的实现类，注入过程会在程序启动时完成，类似于SpringBoot的自动注入
        if ("core".equals(policy)) {
            logger.info("import CoreContentConfiguration ");
            return new String[]{CoreContentService.class.getName()};
        } else {
            logger.info("import SimpleContentConfiguration ");
            return new String[] {SimpleContentService.class.getName()};
        }

//        return new String[0];
    }
}
