package org.example.support;

import org.example.inter.BeanFactory;
import org.example.exception.BeansException;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);

        return createBean(name, beanDefinition, args);
    }

    /**
     * 获取BeanDefinition
     * @param beanName String
     * @return beanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean
     * @param beanName String
     * @param beanDefinition BeanDefinition
     * @return beanEntity
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 有参创建bean
     * @param beanName String
     * @param beanDefinition BeanDefinition
     * @param ags Object[]
     * @return beanEntity
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] ags) throws BeansException;

}
