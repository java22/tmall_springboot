package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 凌风的MI
 * 产品对应的持久层
 */
public interface ProductDAO extends JpaRepository<Product,Integer> {

    /**
     * 通过分类获取所有产品
     */
    Page<Product> findByCategory(Category category, Pageable pageable);

    /**
     * 通过分类获取所有产品(不需要分页)
     */
    List<Product> findByCategoryOrderById(Category category);

    /**
     * 通过商品名模糊查询
     */
    List<Product> findByNameLike(String keyword, Pageable pageable);
}
