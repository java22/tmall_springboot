package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 凌风的MI
 * 产品对应的控制器
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    /**
     * 获取分类下对应的产品
     * @param  cid 分类对应的id
     * @param start 开始页
     * @param size 每页最多显示的条数
     * @return 分页后的数据
     */
    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid,
                                        @RequestParam(value = "start", defaultValue = "0") int start,
                                        @RequestParam(value = "size" ,defaultValue = "5") int size ) throws Exception{
        start = start<0 ? 0:start;
        Page4Navigator<Product> page = productService.list(cid, start, size, 5);

        productImageService.setFirstProductImages(page.getContent());

        return page;
    }

    /**
     * 获取产品
     * @param id 产品对应的id
     * @return  对应的数据
     */
    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception{
        Product bean = productService.get(id);
        return bean;
    }

    /**
     * 新增产品
     * @param bean 新增的产品数据
     * @return 新增后的数据
     */
    @PostMapping("/products")
    public Object add(@RequestBody Product bean) throws Exception{
        bean.setCreateDate(new Date());
        productService.add(bean);
        return bean;
    }

    /**
     * 删除产品
     * @param id 删除的产品id
     */
    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        productService.delete(id);
        return null;
    }

    /**
     * 修改产品
     * @param bean 修改的产品属性
     * @return 修改后的数据
     */
    @PutMapping("/products")
    public Object update(@RequestBody Product bean) throws Exception{
        productService.update(bean);
        return bean;
    }
}
