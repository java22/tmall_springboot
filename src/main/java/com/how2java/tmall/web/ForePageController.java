package com.how2java.tmall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 凌风的MI
 * 负责前台页面跳转
 */
@Controller
public class ForePageController {

    @GetMapping(value="/")
    public String index(){
        return "redirect:home";
    }

    /**
     * 首页
     */
    @GetMapping(value="/home")
    public String home(){
        return "fore/home";
    }

    /**
     * 注册
     */
    @GetMapping(value="/register")
    public String register(){
        return "fore/register";
    }

    /**
     * 支付
     */
    @GetMapping(value="/alipay")
    public String alipay(){
        return "fore/alipay";
    }

    /**
     * 已购买
     */
    @GetMapping(value="/bought")
    public String bought(){
        return "fore/bought";
    }

    /**
     * 正在购买
     */
    @GetMapping(value="/buy")
    public String buy(){
        return "fore/buy";
    }

    /**
     * 购物车
     */
    @GetMapping(value="/cart")
    public String cart(){
        return "fore/cart";
    }

    /**
     * 分类
     */
    @GetMapping(value="/category")
    public String category(){
        return "fore/category";
    }

    /**
     * 确认支付
     */
    @GetMapping(value="/confirmPay")
    public String confirmPay(){
        return "fore/confirmPay";
    }

    /**
     * 登录
     */
    @GetMapping(value="/login")
    public String login(){
        return "fore/login";
    }

    /**
     * 订单确认
     */
    @GetMapping(value="/orderConfirmed")
    public String orderConfirmed(){
        return "fore/orderConfirmed";
    }

    /**
     * 已支付
     */
    @GetMapping(value="/payed")
    public String payed(){
        return "fore/payed";
    }

    /**
     * 产品
     */
    @GetMapping(value="/product")
    public String product(){
        return "fore/product";
    }

    /**
     * 注册成功
     */
    @GetMapping(value="/registerSuccess")
    public String registerSuccess(){
        return "fore/registerSuccess";
    }

    /**
     * 评论
     */
    @GetMapping(value="/review")
    public String review(){
        return "fore/review";
    }

    /**
     * 搜索
     */
    @GetMapping(value="/search")
    public String searchResult(){
        return "fore/search";
    }

    /**
     * 退出
     */
    @GetMapping("/forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:home";
    }
}
