package org.example.support;

import cn.hutool.core.bean.BeanUtil;
import org.example.exception.BeansException;
import org.example.inter.InstantiationStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 抽象的自动装配能力Bean工厂
 * 继承自抽象Bean工厂
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    // 实例化策略，默认使用CGLIB子类化实例化策略
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    // 根据给定的BeanDefinition创建Bean实例
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // 使用反射实例化对象
            bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
            applyPropertyValues(beanName, bean, beanDefinition);
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
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将实例化的Bean添加到单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 根据给定的BeanDefinition和参数创建Bean实例
     *
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param args           构造函数参数
     * @return 创建的Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 将属性值应用于Bean实例
     *
     * @param beanName       Bean名称
     * @param bean           Bean实例
     * @param beanDefinition Bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    /**
     * 获取实例化策略
     *
     * @return 实例化策略
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化策略
     *
     * @param instantiationStrategy 实例化策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
