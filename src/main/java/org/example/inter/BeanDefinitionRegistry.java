package org.example.inter;

import org.example.support.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     * @param beanName String
     * @param beanDefinition BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
