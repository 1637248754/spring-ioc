package org.example.inter;

import org.example.exception.BeansException;

public interface BeanFactory {

    /**
     * 获取Bean
     * @param name String
     * @return Bean
     */
    Object getBean(String name) throws BeansException;

    /**
     * 有参获取bean
     * @param name
     * @param args
     * @return Object
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;
}