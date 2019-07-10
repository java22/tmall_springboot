package com.how2java.tmall.service;

import com.how2java.tmall.dao.ReviewDAO;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 评价对应的业务层
 */
@Service
public class ReviewService {

    @Autowired
    ReviewDAO reviewDAO;
    @Autowired
    ProductService productService;

    /**
     * 增加评价
     */
    public void add(Review review) {
        reviewDAO.save(review);
    }

    /**
     * 获取某产品的评价
     */
    public List<Review> list(Product product){
        List<Review> result =  reviewDAO.findByProductOrderByIdDesc(product);
        return result;
    }

    /**
     * 统计某产品的评价数
     */
    public int getCount(Product product) {
        return reviewDAO.countByProduct(product);
    }
}
