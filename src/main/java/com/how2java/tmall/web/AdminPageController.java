package com.how2java.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 凌风的MI
 * 负责后台页面跳转
 */
@Controller
public class AdminPageController {

    /**
     * 用于重定向
     */
    @GetMapping(value = "/admin")
    public String admin(){
        return "redirect:admin_category_list";
    }

    /**
     * 访问admin/listCategory.html文件
     * 获取分类数据
     */
    @GetMapping(value = "/admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }

    /**
     * 访问admin/editCategory.html文件
     * 编辑分类
     */
    @GetMapping(value = "/admin_category_edit")
    public String editCategory(){
        return "admin/editCategory";
    }

    /**
     * 访问admin/listProperty.html文件
     * 获取属性数据
     */
    @GetMapping(value = "/admin_property_list")
    public String listProperty(){
        return "admin/listProperty";
    }

    /**
     * 访问admin/editProperty.html文件
     * 编辑属性
     */
    @GetMapping(value = "/admin_property_edit")
    public String editProperty(){
        return "admin/editProperty";
    }

    /**
     * 访问admin/listProduct.html文件
     * 获取产品数据
     */
    @GetMapping(value = "/admin_product_list")
    public String listProduct(){
        return "admin/listProduct";
    }

    /**
     * 访问admin/editProduct.html文件
     * 编辑产品
     */
    @GetMapping(value = "/admin_product_edit")
    public String editProduct(){
        return "admin/editProduct";
    }

    /**
     * 访问admin/listProductImage.html文件
     * 设置产品图片
     */
    @GetMapping(value = "/admin_productImage_list")
    public String listProductImage(){
        return "admin/listProductImage";
    }

    /**
     * 访问admin/editPropertyValue.html文件
     * 设置产品属性值
     */
    @GetMapping(value = "/admin_propertyValue_edit")
    public String editPropertyValue(){
        return "admin/editPropertyValue";
    }

    /**
     * 访问admin/listUser.html文件
     * 获取用户数据
     */
    @GetMapping(value = "/admin_user_list")
    public String listUser(){
        return "admin/listUser";
    }

    /**
     * 访问admin/listOrder.html文件
     * 获取订单数据
     */
    @GetMapping(value = "/admin_order_list")
    public String listOrder(){
        return "admin/listOrder";
    }
}
