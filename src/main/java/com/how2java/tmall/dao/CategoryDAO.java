package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 凌风的MI
 * 商品分类对应的持久层接口
 */
public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
