package org.example.inter;

public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     * @param beanName String
     * @return bean
     */
    Object getSingleton(String beanName);

}
