package com.how2java.tmall.service;

import com.how2java.tmall.dao.PropertyValueDAO;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 属性值对应的业务层
 */
@Service
public class PropertyValueService {

    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyService propertyService;

    /**
     * 修改属性值
     * @param  bean 要修改的属性值
     */
    public void update(PropertyValue bean){
        propertyValueDAO.save(bean);
    }

    /**
     * 初始化
     * 通过初始化进行自动增加,便于后面修改
     * 根据产品获取分类,获取该分类下的所有属性集合
     * 然后用属性id和产品id查询,该属性和该产品是否已经存在属性值
     * 若不存在,则去创建属性值,并设置其属性和产品,插入到数据库中
     * @param
     * @return
     */
    public void init(Product product){
        List<Property> properties = propertyService.listByCategory(product.getCategory());
        for (Property property: properties) {
            PropertyValue propertyValue = getByPropertyAndProduct(product,property);
            if (null == propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    public PropertyValue getByPropertyAndProduct(Product product, Property property){
        return propertyValueDAO.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product){
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }
}
