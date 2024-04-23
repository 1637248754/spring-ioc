package org.example.inter;

import org.example.exception.BeansException;

public interface BeanFactory {

    /**
     * 获取Bean
     * @param name String
     * @return Bean
     */
    Object getBean(String name) throws BeansException;
}