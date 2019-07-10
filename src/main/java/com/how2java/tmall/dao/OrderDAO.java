package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 凌风的MI
 * 订单对应的持久层
 */
public interface OrderDAO extends JpaRepository<Order,Integer> {

    /**
     * 获取某个用户的订单(delete除外)
     */
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
