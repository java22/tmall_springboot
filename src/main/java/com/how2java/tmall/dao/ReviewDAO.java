package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 凌风的MI
 * 评价对应的持久层
 */
public interface ReviewDAO extends JpaRepository<Review, Integer> {

    /**
     * 获取某个产品对应的评价集合
     */
    List<Review> findByProductOrderByIdDesc(Product product);

    /**
     * 获取某个产品对应的评价数量
     */
    int countByProduct(Product product);

}
