package org.example.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性值
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性值
     * @param pv PropertyValue
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取属性值
     * @return PropertyValue[]
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名获取属性值
     * @param propertyName String
     * @return PropertyValue
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
