package com.how2java.tmall.service;

import com.how2java.tmall.dao.PropertyDAO;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 商品分类属性对应的业务层
 */
@Service
public class PropertyService {

    @Autowired
    PropertyDAO propertyDAO;
    @Autowired
    CategoryService categoryService;

    /**
     * 新增操作
     * @param bean 属性实体类
     */
    public void add(Property bean){
        propertyDAO.save(bean);
    }

    /**
     * 删除操作
     * @param id 要删除的属性id
     */
    public void delete(int id){
        propertyDAO.delete(id);
    }

    /**
     * 查询操作
     * @param id 要查询的属性id
     * @return  对应实体类的数据
     */
    public Property get(int id){
        return propertyDAO.findOne(id);
    }

    /**
     * 修改操作
     * @param bean 要修改的实体属性
     */
    public void update(Property bean){
        propertyDAO.save(bean);
    }

    /**
     * 对分类中属性进行分页
     * @param  cid 分类的id
     * @param start 开始页
     * @param size 每页显示的条数
     * @param navigatePages 分页导航最多显示多少页
     * @return 分页后的属性
     */
    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages){
        Category category = categoryService.get(cid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Property> pageFromJPA = propertyDAO.findByCategory(category,pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /**
     * 通过分类获取所有属性集合
     * @param  category 分类数据
     * @return  分类下的属性
     */
    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}
