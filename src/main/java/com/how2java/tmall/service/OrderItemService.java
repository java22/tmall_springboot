package com.how2java.tmall.service;

import com.how2java.tmall.dao.OrderItemDAO;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 订单项对应的业务层
 */
@Service
public class OrderItemService {

    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    /**
     * 统计订单项
     */
    public void fill(List<Order> orders){
        for (Order order : orders) {
            fill(order);
        }
    }

    /**
     * 修改订单项
     */
    public void update(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    /**
     * 计算总订单数和总金额
     */
    public void fill(Order order){
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi :orderItems) {
            total += oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    /**
     * 新增订单项
     */
    public void add(OrderItem orderItem) {
        orderItemDAO.save(orderItem);
    }

    /**
     * 获取订单项
     */
    public OrderItem get(int id) {
        return orderItemDAO.findOne(id);
    }

    /**
     * 通过id删除订单项
     */
    public void delete(int id) {
        orderItemDAO.delete(id);
    }

    /**
     * 获取销量
     */
    public int getSaleCount(Product product) {
        List<OrderItem> ois = listByProduct(product);
        int result =0;
        for (OrderItem oi : ois) {
            if(null!=oi.getOrder()) {
                if (null != oi.getOrder() && null != oi.getOrder().getPayDate()) {
                    result += oi.getNumber();
                }
            }
        }
        return result;
    }

    /**
     * 通过产品获取订单项
     */
    public List<OrderItem> listByProduct(Product product) {
        return orderItemDAO.findByProduct(product);
    }

    /**
     * 通过订单获取订单项
     */
    public List<OrderItem> listByOrder(Order order){
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }

    /**
     * 通过用户查询订单
     */
    public List<OrderItem> listByUser(User user) {
        return orderItemDAO.findByUserAndOrderIsNull(user);
    }
}
