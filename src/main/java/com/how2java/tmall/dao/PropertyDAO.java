package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 凌风的MI
 * 商品分类属性对应的持久层接口
 */
public interface PropertyDAO extends JpaRepository<Property,Integer> {

    /**
     * 获取分类下属性的分页数据
     */
    Page<Property> findByCategory(Category category, Pageable pageable);

    /**
     * 获取所有属性的集合
     */
    List<Property> findByCategory(Category category);
}
