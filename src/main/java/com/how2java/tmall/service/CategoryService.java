package com.how2java.tmall.service;

import com.how2java.tmall.dao.CategoryDAO;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 凌风的MI
 * 商品分类对应的业务层
 */
@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    /**
     * 增加带参的list方法
     */
    public Page4Navigator<Category> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = categoryDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    /**
     * 创建Sort对象,并通过id倒序排序
     */
    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    /**
     * 新增分类
     * @param bean 分类实体类
     */
    public void add(Category bean) {
        categoryDAO.save(bean);
    }
    
    /**
     * 删除分类
     * @param id 要删除的节点id 
     */
    public void delete(int id){
        categoryDAO.delete(id);
    }

    /**
     * 编辑分类
     * @param id 要编辑的节点id
     * @return  获取节点对应的数据
     */
    public Category get(int id){
        Category category = categoryDAO.findOne(id);
        return category;
    }

    /**
     * 编辑并真正修改
     * @param bean 修改传进来的数据
     */
    public void update(Category bean){
        categoryDAO.save(bean);
    }

    /**
     * 删除Product对象上的分类(与orderItem上的订单信息道理是相同的)
     */
    public void removeCategoryFromProduct(List<Category> cs){
        for (Category category : cs) {
            removeCategoryFromProduct(category);
        }
    }

    public void removeCategoryFromProduct(Category category){
        List<Product> products = category.getProducts();
        if (null != products){
            for (Product product : products){
                product.setCategory(null);
            }
        }

        List<List<Product>> productsByRow = category.getProductsByRow();
        if (null != productsByRow){
            for (List<Product> ps : productsByRow){
                for (Product p : ps){
                    p.setCategory(null);
                }
            }
        }
    }
}
