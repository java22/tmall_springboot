package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 凌风的MI
 * 属性值对应的持久层
 */
public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {

    /**
     * 根据产品查询
     */
    List<PropertyValue> findByProductOrderByIdDesc(Product product);

    /**
     * 根据产品和属性获取PropertyValue对象
     */
    PropertyValue getByPropertyAndProduct(Property property, Product product);

}
