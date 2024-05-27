package org.example.support;

import org.example.exception.BeansException;
import org.example.inter.InstantiationStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 可自动装配的Bean工厂抽象类
 * 该类实现了BeanFactory接口，提供了根据BeanDefinition创建Bean实例的方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 实例化策略，默认使用CGLIB子类化实例化策略
    private final InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    // 根据给定的BeanDefinition创建Bean实例
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // 使用反射实例化对象
            bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 将实例化的Bean添加到单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }


    @Override
    // 根据给定的BeanDefinition和参数创建Bean实例
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            // 根据参数选择合适的构造函数
            Constructor constructorToUse = null;
            Class<?> beanClass = beanDefinition.getBeanClass();
            Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
            for (Constructor ctor : declaredConstructors) {
                if (null != args && ctor.getParameterTypes().length == args.length) {
                    constructorToUse = ctor;
                    break;
                }
            }
            // 使用实例化策略创建Bean实例
            bean = instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将实例化的Bean添加到单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }
}
