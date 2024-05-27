package org.example.support;

import org.example.exception.BeansException;
import org.example.inter.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory 继承自 AbstractAutowireCapableBeanFactory 并实现 BeanDefinitionRegistry 接口。
 * 这使得它不仅能创建和管理Bean，还能注册和维护Bean定义。
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    // 使用HashMap来存储容器中所有的Bean定义。键是Bean的名字，值是BeanDefinition对象。
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // 注册一个Bean定义到容器中。这个方法通过接口BeanDefinitionRegistry提供。
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        // 将Bean的名称和定义放入map中，实现注册。
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    // 从容器中获取一个Bean的定义。如果指定名称的Bean不存在，则抛出异常。
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        // 从map中获取Bean定义。
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        // 如果获取的Bean定义为空，则抛出BeansException异常，说明请求的Bean名称在容器中未定义。
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        // 返回获取到的Bean定义。
        return beanDefinition;
    }
}

