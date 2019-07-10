package com.how2java.tmall.service;

import com.how2java.tmall.dao.UserDAO;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author 凌风的MI
 * 用户对应的业务层
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    /**
     * 判断该用户名是否已注册
     */
    public boolean isExist(String name) {
        User user = getByName(name);
        return null!=user;
    }

    /**
     * 通过用户名获取用户
     */
    public User getByName(String name) {
        return userDAO.findByName(name);
    }

    /**
     * 执行登录
     */
    public User get(String name, String password) {
        return userDAO.getByNameAndPassword(name,password);
    }

    /**
     * 分页显示
     */
    public Page4Navigator<User> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = userDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /**
     * 执行注册
     */
    public void add(User user) {
        userDAO.save(user);
    }
}
