package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 凌风的MI
 * 订单项对应的持久层
 */
public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {

    /**
     * 获取某个订单的订单项集合
     */
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    /**
     * 获取某个产品的订单项集合
     */
    List<OrderItem> findByProduct(Product product);

    /**
     * 通过用户查询订单
     */
    List<OrderItem> findByUserAndOrderIsNull(User user);
}
