package org.example.inter;

import org.example.exception.BeansException;
import org.example.support.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    /**
     * 构造函数实例
     * @param beanDefinition BeanDefinition
     * @param beanName String
     * @param ctor Constructor
     * @param args Object[]
     * @return Object
     * @throws BeansException BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
